package net.datenwerke.rs.utils.simplequery.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface OrderBy {

	public enum Direction{
		ASC,
		DESC
	}
	
	Direction dir() default Direction.ASC;
	String attribute();
}
