package net.datenwerke.dtoservices.dtogenerator.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.gxtdto.client.dtomanager.DtoView;

/**
 * Exposes a get/set method to the client. 
 * 
 * In the dto both get and set methods will be generated. If added to 
 * a set method the dto's value can be merged back to the poso.
 * 
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface ExposeMethodToClient {

	/**
	 * The minimal clearance needed to see this property.
	 */
	DtoView view() default DtoView.NORMAL;
	
}
