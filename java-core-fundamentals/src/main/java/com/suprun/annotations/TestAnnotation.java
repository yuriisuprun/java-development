package com.suprun.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * TestAnnotation is a marker annotation for demonstrating custom annotation usage.
 * This annotation can only be applied to types (classes, interfaces, enums) and is retained at runtime.
 *
 * @author Yurii_Suprun
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
}
