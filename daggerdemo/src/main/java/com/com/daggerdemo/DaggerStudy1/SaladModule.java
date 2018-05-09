package com.com.daggerdemo.DaggerStudy1;

import dagger.Module;
import dagger.Provides;

/**
 * @author：lichenxi
 * @date 2018/3/11 23
 * email：525603977@qq.com
 * Fighting
 * 管理所有需要的依赖 一个对象的生成 需要什么依赖 都从这里定义好
 */
@Module
public class SaladModule {
    //方法名称必须以provideXXX开头
    @Provides
    @TodoScope
    public Pear providePear(){
        return new Pear();
    }

    @Provides
    public Orange provideOrange(Knife knife){
        return new Orange(knife);
    }
    @Provides
    public Knife provideKnife(){
        return new Knife();

    }
    @Provides
    public SaladSauce provideSaladSauce(){
        return new SaladSauce();
    }
    @Provides
    public Banana provideBanana(){
        return new Banana();
    }

    @Type("normal")//使用限定符来区别使用哪个构造函数返回对象
    @Provides
    public Apple provideNormalApple() {
        return new Apple();
    }

    @Type("color")//使用限定符来区别使用哪个构造函数返回对象
    @Provides
    public Apple provideColorApple(String color) {
        return new Apple(color);
    }

    @Provides
    public String provideString(){
        return "黄色";
    }
}
