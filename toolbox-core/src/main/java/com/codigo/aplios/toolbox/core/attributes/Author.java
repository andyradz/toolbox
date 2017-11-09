package com.codigo.aplios.toolbox.core.attributes;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Author {

    /**
     * Atrybut przedstawia imię i nazwisko autora.
     *
     * @return Wartość tekstowa.
     */
    String name() default "andrzej radziszewski";

    /**
     * Atrybut przedstawia login autora.
     *
     * @return Wartość tekstowa.
     */
    String login() default "andrzej.radziszewski";

    /**
     * Atrybut przedstawia email autora.
     *
     * @return Wartość tekstowa.
     */
    String email() default "ar.radziszewski@gmail.com";

    /**
     * Atrybut przedsatwaia zawód autora.
     *
     * @return Wartość tekstowa.
     */
    String profession() default "developer";

}
