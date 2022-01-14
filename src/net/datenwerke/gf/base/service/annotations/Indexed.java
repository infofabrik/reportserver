package net.datenwerke.gf.base.service.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
/**
 * Specifies that an entity is to be indexed
 */
public @interface Indexed {
   /**
    * @return The filename of the index. Default to empty string
    */
   String index() default "";

}
