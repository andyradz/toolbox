package com.codigo.aplios.toolbox.core.gui.sheet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation used together with {@link AnnotatedBeanInfo} expose only those properties which are annotated with.
 *
 * @author Bartosz Firyn (SarXos)
 * @see AnnotatedBeanInfo
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface EnumValueInfo {

	String value() default "#default";

}
