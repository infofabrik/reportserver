package net.datenwerke.rs.utils.entitycloner.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Marks a property of an entity as an enclosed entity.
 * 
 * <p>Should be set on to the set and get methods of the entity</p>
 * 
 *
 */
@Documented
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RUNTIME)
public @interface EnclosedEntity {

	
}
