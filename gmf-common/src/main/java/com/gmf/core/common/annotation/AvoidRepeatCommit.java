package com.gmf.core.common.annotation;

import java.lang.annotation.*;

/**
 * @author gmf
 * @date 2021/1/15 9:59
 * @descrition: 重复提交限制注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AvoidRepeatCommit {
    /**
     * 指定时间内不可重复提交
     *
     * @return
     */
    long timeout() default 10;
}
