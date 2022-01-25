package net.datenwerke.dtoservices.dtogenerator.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.datenwerke.dtoservices.dtogenerator.Visibility;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;

/**
 * 
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExposeToClient {

   /**
    * Defines if this field acts as id for this poso.
    */
   boolean id() default false;

   /**
    * Defines if this field acts as key for this poso.
    */
   boolean key() default false;

   /**
    * The minimal view this property belongs to.
    */
   DtoView view() default DtoView.NORMAL;

   /**
    * Defines if the server value is copied to the client on dto generation
    * 
    */
   boolean exposeValueToClient() default true;

   /**
    * String values are encoded using StringEscapeUtils htmlEncode. By setting this
    * to true, no encoding will be performed.
    * 
    */
   boolean disableHtmlEncode() default false;

   boolean enableSimpleHtmlPolicy() default false;

   /**
    * Very large objects might compromise the application (dos). Strings are per
    * default cut off at 8192 characters. To circumvent this, set this option.
    */
   boolean allowArbitraryLobSize() default false;

   /**
    * Allows to disable merging of a certain property when merging dto and poso.
    * 
    */
   boolean mergeDtoValueBack() default true;

   /**
    * If true, the property's content will be used as display title.
    * 
    * @see GenerateDto#displayTitle()
    */
   boolean displayTitle() default false;

   /**
    * Returns an array containing validators for a specific property
    */
   PropertyValidator validateDtoProperty() default @PropertyValidator(bypass = true);

   /**
    * Allows an existing enclosed poso to be "moved".
    */
   boolean allowForeignPosoForEnclosed() default false;

   public Visibility visibility() default Visibility.PRIVATE;

   /**
    * Uses the "here" view for the generation of a subentity (as for
    * EnclosedEntities)
    */
   public boolean inheritDtoView() default false;
}
