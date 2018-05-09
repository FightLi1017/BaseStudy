package com.com.daggerdemo.DaggerStudy1;

import dagger.Component;

/**
 * @author：lichenxi
 * @date 2018/3/12 00
 * email：525603977@qq.com
 * Fighting
 */
@TodoScope
@Component(modules = SaladModule.class)
public interface SaladComponent {
    //注意：下面这三个方法，返回值必须是从上面指定的依赖库SaladModule.class中取得的对象
    //注意：而方法名不一致也行，但是方便阅读，建议一致，因为它主要是根据返回值类型来找依赖的
    //★注意：下面这三个方法也可以不写，但是如果要写，就按照这个格式来
    //但是当Component要被别的Component依赖时，
    //这里就必须写这个方法，不写代表不向别的Component暴露此依赖
//    Pear providePear();
//
//    Banana provideBanana();
//
//    SaladSauce provideSaladSauce();

    @Type("normal")//关键靠这个限定符来区分调用哪个构造函数
    Apple provideNormalApple();//这个方法名并不重要，只要是provide开头就行，但是建议和module里一致

    @Type("color")
    Apple provideColorApple();

    String provideString();

    //注意：下面的这个方法，表示要将以上的三个依赖注入到某个类中
    //这里我们把上面的三个依赖注入到Salad中
    //因为我们要做沙拉
    void  inject(Salad salad);
}
