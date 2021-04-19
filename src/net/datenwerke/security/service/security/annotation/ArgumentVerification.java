package net.datenwerke.security.service.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.inject.name.Named;

/**
 * 
 *
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.TYPE)
public @interface ArgumentVerification {

	/**
	 * The parameter's name (as set with {@link Named})
	 */
	public String name();
	
	/**
	 * Specifys whether the parameter is a dto.
	 */
	public boolean isDto() default false;
	
	/**
	 * The list of rights to be verifyed.
	 */
	public RightsVerification[] verify() default {};
	
	public RightsVerification[] parentChecks() default {};
}
