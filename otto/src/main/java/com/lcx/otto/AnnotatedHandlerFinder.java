/*
 * Copyright (C) 2012 Square, Inc.
 * Copyright (C) 2007 The Guava Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lcx.otto;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Helper methods for finding methods annotated with {@link Produce} and {@link Subscribe}.
 *
 * @author Cliff Biffle
 * @author Louis Wasserman
 * @author Jake Wharton
 */
final class AnnotatedHandlerFinder {

  /** Cache event bus producer methods for each class. */
  private static final ConcurrentMap<Class<?>, Map<Class<?>, Method>> PRODUCERS_CACHE =
    new ConcurrentHashMap<Class<?>, Map<Class<?>, Method>>();

  /** Cache event bus subscriber methods for each class. */
  private static final ConcurrentMap<Class<?>, Map<Class<?>, Set<Method>>> SUBSCRIBERS_CACHE =
    new ConcurrentHashMap<Class<?>, Map<Class<?>, Set<Method>>>();

  //加载目标类里面所有@Produce的注解方法
  private static void loadAnnotatedProducerMethods(Class<?> listenerClass,
      Map<Class<?>, Method> producerMethods) {
    Map<Class<?>, Set<Method>> subscriberMethods = new HashMap<Class<?>, Set<Method>>();
    loadAnnotatedMethods(listenerClass, producerMethods, subscriberMethods);
  }
  //加载目标类里面所有@Subscriber的注解方法
  private static void loadAnnotatedSubscriberMethods(Class<?> listenerClass,
      Map<Class<?>, Set<Method>> subscriberMethods) {
    Map<Class<?>, Method> producerMethods = new HashMap<Class<?>, Method>();
    loadAnnotatedMethods(listenerClass, producerMethods, subscriberMethods);
  }

  /**
   * Load all methods annotated with {@link Produce} or {@link Subscribe} into their respective caches for the
   * specified class.
   */
  private static void loadAnnotatedMethods(Class<?> listenerClass,
      Map<Class<?>, Method> producerMethods, Map<Class<?>, Set<Method>> subscriberMethods) {
     //getDeclaredMethods 获取目标类所有的声明的方法 不限定权限符
    for (Method method : listenerClass.getDeclaredMethods()) {
      // The compiler sometimes creates synthetic bridge methods as part of the
      // type erasure process. As of JDK8 these methods now include the same
      // annotations as the original declarations. They should be ignored for
      // subscribe/produce.
      //是否桥接方法 因为泛型的原因 编译器会给我们重写一个自带object参数的方法 是为了编译器好用的 但是这里我们遍历的时候
      //并不需要判断这个方法
      if (method.isBridge()) {
        continue;
      }
      //处理Subscribe
      if (method.isAnnotationPresent(Subscribe.class)) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1) {
          throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation but requires "
              + parameterTypes.length + " arguments.  Methods must require a single argument.");
        }

        Class<?> eventType = parameterTypes[0];
        if (eventType.isInterface()) {
          throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + eventType
              + " which is an interface.  Subscription must be on a concrete class type.");
        }

        if ((method.getModifiers() & Modifier.PUBLIC) == 0) {
          throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + eventType
              + " but is not 'public'.");
        }

        Set<Method> methods = subscriberMethods.get(eventType);
        if (methods == null) {
          methods = new HashSet<Method>();
          subscriberMethods.put(eventType, methods);
        }
        methods.add(method);
         //处理Produce
      } else if (method.isAnnotationPresent(Produce.class)) {
         //得到方法的参数
        Class<?>[] parameterTypes = method.getParameterTypes();
        /*
            判断参数 Produce必须是空参数 而且必须有返回值 不能是Void
         */
        if (parameterTypes.length != 0) {
          throw new IllegalArgumentException("Method " + method + "has @Produce annotation but requires "
              + parameterTypes.length + " arguments.  Methods must require zero arguments.");
        }
        if (method.getReturnType() == Void.class) {
          throw new IllegalArgumentException("Method " + method
              + " has a return type of void.  Must declare a non-void type.");
        }

        //得到方法的返回类型 并且执行检测
        Class<?> eventType = method.getReturnType();
        //如果返回类型是一个接口 则抛出异常信息 返回类型必须是一个具体的类
        if (eventType.isInterface()) {
          throw new IllegalArgumentException("Method " + method + " has @Produce annotation on " + eventType
              + " which is an interface.  Producers must return a concrete class type.");
        }
        //返回类型不能是void
        if (eventType.equals(Void.TYPE)) {
          throw new IllegalArgumentException("Method " + method + " has @Produce annotation but has no return type.");
        }
        //方法的可见型修饰符必须是public的 （其实从逻辑上来讲 不一定非要public才可以 反正我们调用方法都是反射调用）
        if ((method.getModifiers() & Modifier.PUBLIC) == 0) {
          throw new IllegalArgumentException("Method " + method + " has @Produce annotation on " + eventType
              + " but is not 'public'.");
        }
          //限制同一个类中 只能有一个相同类型的生产者
        if (producerMethods.containsKey(eventType)) {
          throw new IllegalArgumentException("Producer for type " + eventType + " has already been registered.");
        }
        //通过HashMap 来保存生产者的类和对应的方法  一一对应的效果
        producerMethods.put(eventType, method);
      }
    }

    PRODUCERS_CACHE.put(listenerClass, producerMethods);
    SUBSCRIBERS_CACHE.put(listenerClass, subscriberMethods);
  }

  /** This implementation finds all methods marked with a {@link Produce} annotation.
   *
   *
   * */
  static Map<Class<?>, EventProducer> findAllProducers(Object listener) {
    //object得到对应的class文件
    final Class<?> listenerClass = listener.getClass();
    Map<Class<?>, EventProducer> handlersInMethod = new HashMap<Class<?>, EventProducer>();
    //得到目标class的缓存
    Map<Class<?>, Method> methods = PRODUCERS_CACHE.get(listenerClass);
    if (null == methods) {
      methods = new HashMap<Class<?>, Method>();
      loadAnnotatedProducerMethods(listenerClass, methods);
    }
    //操作集合 进行包装 将目标类和目标方法 封装成EventProducer
    if (!methods.isEmpty()) {
      for (Map.Entry<Class<?>, Method> e : methods.entrySet()) {
        EventProducer producer = new EventProducer(listener, e.getValue());
        handlersInMethod.put(e.getKey(), producer);
      }
    }

    return handlersInMethod;
  }

  /** This implementation finds all methods marked with a {@link Subscribe} annotation. */
  static Map<Class<?>, Set<EventHandler>> findAllSubscribers(Object listener) {
    Class<?> listenerClass = listener.getClass();
    //通过这个 可以发现 一个目标类 可以有多个Subscribe
    Map<Class<?>, Set<EventHandler>> handlersInMethod = new HashMap<Class<?>, Set<EventHandler>>();
   //
    Map<Class<?>, Set<Method>> methods = SUBSCRIBERS_CACHE.get(listenerClass);
    if (null == methods) {
      methods = new HashMap<Class<?>, Set<Method>>();
      loadAnnotatedSubscriberMethods(listenerClass, methods);
    }
    if (!methods.isEmpty()) {
      for (Map.Entry<Class<?>, Set<Method>> e : methods.entrySet()) {
        Set<EventHandler> handlers = new HashSet<EventHandler>();
        for (Method m : e.getValue()) {
          handlers.add(new EventHandler(listener, m));
        }
        handlersInMethod.put(e.getKey(), handlers);
      }
    }

    return handlersInMethod;
  }

  private AnnotatedHandlerFinder() {
    // No instances.
  }

}
