package com.com.daggerdemo.DaggerStudy1;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author：lichenxi
 * @date 2018/3/20 00
 * email：525603977@qq.com
 * Fighting
 */
@Scope
@Documented
@Retention(RUNTIME)
public @interface TodoScope {
}
