package net.datenwerke.rs.utils.instancedescription.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.rs.utils.localization.Messages;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface InstanceDescription {

	Class<? extends Messages> msgLocation();
	String objNameKey() default "";
	
	String title() default "";
	String description() default "";
	String[] fields() default {};
	
	String icon() default "file";
}
