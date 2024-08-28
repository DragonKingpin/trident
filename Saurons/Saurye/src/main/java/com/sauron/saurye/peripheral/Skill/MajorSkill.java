package com.sauron.saurye.peripheral.Skill;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MajorSkill {
    boolean value() default true;
}
