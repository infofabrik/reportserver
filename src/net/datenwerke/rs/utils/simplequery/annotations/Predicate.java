package net.datenwerke.rs.utils.simplequery.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.rs.utils.simplequery.PredicateType;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Predicate {

	PredicateType type() default PredicateType.EQUAL;
	String attribute() default "";
	
	String value() default "";
	
}
