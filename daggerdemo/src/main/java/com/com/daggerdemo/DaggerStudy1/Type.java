package com.com.daggerdemo.DaggerStudy1;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author：lichenxi
 * @date 2018/3/19 17
 * email：525603977@qq.com
 * Fighting
 */
@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Type {
    String value() default "";//默认值为""
}
