package com.masaiqi.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description token鉴权注解 在方法/类上加上该注解表示需要鉴权
 */
@Target({ElementType.METHOD, ElementType.TYPE})//注解可能出现在Java程序中的语法位置
@Retention(RetentionPolicy.RUNTIME)//生存周期 运行时
public @interface AuthToken {

}
