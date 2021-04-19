package net.datenwerke.dtoservices.dtogenerator.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PropertyValidator {

	/**
	 * The validator will not interfere if bypass is true
	 */
	boolean bypass() default false;
	
	/**
	 * Tells the validator to simply ignore the property on failure. 
	 * 
	 * Default is to throw a validation failed exception
	 */
	boolean ignoreOnFailure() default false;
	
	/**
	 * The expected Supertype
	 */
	Class<?> type() default Object.class;
	
	/**
	 * The expected property is a string
	 */
	StringValidator string() default @StringValidator(bypass=true);
	
	/**
	 * The expected property is a number. 
	 */
	NumberValidator number() default @NumberValidator(bypass=true);
	
	/**
	 * Returns an array containing validators for a specific property 
	 */
	Class<? extends DtoPropertyValidator>[] validators() default {};
}
