package net.datenwerke.dtoservices.dtogenerator.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.google.gwt.i18n.client.Messages;

import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoPostProcessor;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisor;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

/**
 * 
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface GenerateDto {

   boolean proxyableDto() default true;

   Class<? extends Poso2DtoPostProcessor>[] poso2DtoPostProcessors() default {};

   Class<? extends Dto2PosoPostProcessor>[] dto2PosoPostProcessors() default {};

   Class<? extends Dto2PosoSupervisor> dto2PosoSupervisor() default Dto2PosoSupervisorDefaultImpl.class;

   /**
    * Defines additional interfaces the dto should implement
    */
   Class<?>[] dtoImplementInterfaces() default {};

   String dtoPackage();

   boolean abstractDto() default false;

   boolean generateDto() default true;

   Class<?> dtoExtends() default Dto.class;

   String poso2DtoGeneratorPackage() default "dtogen";

   boolean generatePoso2Dto() default true;

   String dto2posoGeneratorPackage() default "dtogen";

   boolean generateDto2Poso() default true;;

   /**
    * SerialVersionUID for dto class
    */
   long dtoSerialVersionUID() default 0;

   boolean createDecorator() default false;

   String icon() default "file";

   /**
    * Can be used to define a more complex display title
    */
   String displayTitle() default "";

   Class<?>[] additionalImports() default {};

   Class<?>[] whitelist() default {};

   AdditionalField[] additionalFields() default {};

   Class<? extends Messages> typeDescriptionMsg() default BaseMessages.class;

   String typeDescriptionKey() default "unknown";

}
