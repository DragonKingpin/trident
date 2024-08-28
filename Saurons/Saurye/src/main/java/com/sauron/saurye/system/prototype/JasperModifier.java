package com.sauron.saurye.system.prototype;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JasperModifier {
    boolean value() default true;
}
