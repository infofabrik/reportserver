package net.datenwerke.security.service.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.rs.utils.interfaces.NullIndicatorInterface;

/**
 * 
 *
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.METHOD,ElementType.TYPE})
@Inherited
public @interface SecurityChecked {

	public boolean superUserRequired() default false;
	
	public Class<?> location() default NullIndicatorInterface.class; 
	
	/**
	 * Sets whether a user has to be logged in to call the method
	 */
	public boolean loginRequired() default true;
	
	/**
	 * Defines whether the caller has to have authenticated himself with a certificate.
	 */
	public boolean certificateRequired() default true;
	
	/**
	 * An array of methods that are not affected by security checks (when used as class level annotation)
	 */
	public BypassMethod[] bypassMethods() default {};
	
	/**
	 * Defines whether the security checks should be disabled.
	 */
	public boolean bypass() default false;

	/**
	 * Defines whether inherited methods should not be checked.
	 */
	public boolean bypassInheritedMethods() default false;

	/**
	 * If this flag is set the interceptor returns true or false depending on the security check.
	 */
	public boolean returnBooleanOnCheck() default false;
	
	/**
	 * Tells the interceptor to return an instance of the methods return type on failure.
	 * 
	 * <p>
	 * Common interfaces such as Collection, List, Set, Map, will be automatically mapped.
	 * If you want to manually set the type to be instantiated use 
	 * 
	 */
	public boolean returnObjectInstanceOnFailure() default false;
	
	public boolean returnFalseOnFailure() default false;
	
	public boolean returnNullOnFailure() default false;
	
	/**
	 * 
	 * @see #returnObjectInstanceOnFailure()
	 */
	public Class<?> returnTypeOnFailue() default NullIndicatorInterface.class;
	
	/**
	 * Additional generic target verifications
	 */
	public GenericTargetVerification[] genericTargetVerification() default {};
	
	/**
	 * Request verification for parameters.
	 */
	public ArgumentVerification[] argumentVerification() default {};
	
	/**
	 * Requests verification for return 
	 */
	public ReturnObjectValidation[] returnObjectValidation() default {};

	
}
