package net.datenwerke.rs.utils.simplequery.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Join {

	public enum Type{
		INNER,
		LEFT,
		RIGHT
	}
	
	String joinAttribute();
	
	Predicate[] where() default {};
	
	Join.Type type() default Join.Type.INNER; 
}
