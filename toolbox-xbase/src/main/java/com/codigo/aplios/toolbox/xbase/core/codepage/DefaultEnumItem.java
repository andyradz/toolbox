package com.codigo.aplios.toolbox.xbase.core.codepage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Adnotacja ustawia metadane dla definicji rodzaj typem.
 * Wskazuje na domyślny standard kodowania znaków przy braku wskazanego rodzaju
 * kodowania znaków.
 *
 * @author andrzej.radziszewski
 * @version 1.0.0.0
 * @since 2017
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@interface DefaultEnumItem {

    /**
     * Właściwość wskazuje domyślny rodzaj kodowania znaków
     *
     * @return kodowania znaków w standardzie ASCII
     */
    XbCodePages value() default XbCodePages.ASCII;

}
