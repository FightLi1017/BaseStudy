package android.com.pluginstudy.CustomClassLoader;

import java.lang.reflect.Array;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * @author：lichenxi
 * @date 2018/9/3 19
 * email：525603977@qq.com
 * Fighting
 */
public class DexUtil {
    public static void insertDex(DexClassLoader dexClassLoader) throws Exception {
      //首先通过反射技术 得到宿主的所有DexElement
        Object baseDexElements = getDexElements(getPathList(getPathClassLoader()));
        Object newDexElements = getDexElements(getPathList(dexClassLoader));
        //将两个dex合并
        Object combineElements=combineArray(baseDexElements,newDexElements);
        //将新的dex插入到pathList里面去
        Object pathList= getPathList(getPathClassLoader());
        ReflectUtil.setField(pathList.getClass(),pathList,"dexElements",combineElements);

    }

    private static Object combineArray(Object firstArray, Object secondArray) {
        Class<?> localClass = firstArray.getClass().getComponentType();
        int firstArrayLength = Array.getLength(firstArray);
        int allLength = firstArrayLength + Array.getLength(secondArray);
        Object result = Array.newInstance(localClass, allLength);
        for (int k = 0; k < allLength; ++k) {
            if (k < firstArrayLength) {
                Array.set(result, k, Array.get(firstArray, k));
            } else {
                Array.set(result, k, Array.get(secondArray, k - firstArrayLength));
            }
        }
        return result;
    }

    private static PathClassLoader getPathClassLoader() {
        PathClassLoader pathClassLoader = (PathClassLoader) DexUtil.class.getClassLoader();
        return pathClassLoader;
    }

    private static Object getDexElements(Object pathList) throws Exception {
        return ReflectUtil.getField(pathList.getClass(), pathList, "dexElements");
    }

    private static Object getPathList(Object baseDexClassLoader) throws Exception {
        return ReflectUtil.getField(Class.forName("dalvik.system.BaseDexClassLoader"), baseDexClassLoader, "pathList");
    }
}
