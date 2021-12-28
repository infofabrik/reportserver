package net.datenwerke.rs.utils.entitycloner.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * A field annotated with this annotation will be used to store the id, of a
 * "cloned" entity.
 * 
 *
 */
@Target(ElementType.FIELD)
@Retention(RUNTIME)
@Documented
public @interface TransientID {

}
