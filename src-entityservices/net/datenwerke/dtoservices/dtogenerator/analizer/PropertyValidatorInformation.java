package net.datenwerke.dtoservices.dtogenerator.analizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.Elements;

import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.annotations.PropertyValidator;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;

/**
 * 
 *
 */
public class PropertyValidatorInformation {

   private PosoFieldDescriptor fieldDescriptor;
   private PropertyValidator validatorAnno;

   private DtoAnnotationProcessor dtoAnnotationProcessor;

   private AnnotationValue typeCheck;

   private StringValidator stringValidator = new StringValidator();
   private NumberValidator numberValidator = new NumberValidator();

   private List<String> customValidators = new ArrayList<String>();

   /**
    * Wraps information on
    * {@link net.datenwerke.dtoservices.dtogenerator.annotations.NumberValidator}
    *
    */
   public class NumberValidator {
      private boolean bypass = true;

      public boolean isBypass() {
         return bypass;
      }

      public void setBypass(boolean bypass) {
         this.bypass = bypass;
      }
   }

   /**
    * Wraps information on
    * {@link net.datenwerke.dtoservices.dtogenerator.annotations.StringValidator}
    *
    */
   public class StringValidator {
      private boolean bypass = true;
      private int maxLength;
      private int minLength;
      private String regex;

      public boolean isBypass() {
         return bypass;
      }

      public void setBypass(boolean bypass) {
         this.bypass = bypass;
      }

      public int getMaxLength() {
         return maxLength;
      }

      public void setMaxLength(int maxLength) {
         this.maxLength = maxLength;
      }

      public int getMinLength() {
         return minLength;
      }

      public void setMinLength(int minLength) {
         this.minLength = minLength;
      }

      public String getRegex() {
         return regex;
      }

      public void setRegex(String regex) {
         this.regex = regex;
      }
   }

   /**
    * Process the annotation and store information in member variables.
    * 
    * @param field
    * @param dtoAnnotationProcessor
    */
   public PropertyValidatorInformation(PosoFieldDescriptor field, DtoAnnotationProcessor dtoAnnotationProcessor) {
      this.fieldDescriptor = field;
      this.dtoAnnotationProcessor = dtoAnnotationProcessor;

      /* load elements helper utils */
      Elements elementUtils = dtoAnnotationProcessor.getProcessingEnvironment().getElementUtils();

      /* we only process information if field is exposed to dto */
      if (fieldDescriptor.isExposedToClient()) {
         this.validatorAnno = field.getExposeToClientAnno().validateDtoProperty();

         /* loop over annotation to find the interesting validation annotation */
         AnnotationMirror mirror = fieldDescriptor.getExposeToClientAnnoMirror();
         for (ExecutableElement key : mirror.getElementValues().keySet()) {

            /* did we find the validation annotation? */
            if (key.toString().equals("validateDtoProperty()")) {
               /* get value */
               AnnotationValue value = elementUtils.getElementValuesWithDefaults(mirror).get(key);

               AnnotationMirror propertyValidatorMirror = (AnnotationMirror) value.getValue();
               for (ExecutableElement propValidatorKey : elementUtils
                     .getElementValuesWithDefaults(propertyValidatorMirror).keySet()) {
                  if ("type()".equals(propValidatorKey.toString()))
                     typeCheck = elementUtils.getElementValuesWithDefaults(propertyValidatorMirror)
                           .get(propValidatorKey);
                  else if ("string()".equals(propValidatorKey.toString())) {
                     /* build string validator */
                     AnnotationValue stringCheck = elementUtils.getElementValuesWithDefaults(propertyValidatorMirror)
                           .get(propValidatorKey);
                     AnnotationMirror stringAnnoMirror = (AnnotationMirror) stringCheck.getValue();

                     for (ExecutableElement stringValKey : elementUtils.getElementValuesWithDefaults(stringAnnoMirror)
                           .keySet()) {
                        AnnotationValue stringValValue = elementUtils.getElementValuesWithDefaults(stringAnnoMirror)
                              .get(stringValKey);

                        if ("bypass()".equals(stringValKey.toString()))
                           stringValidator.setBypass(Boolean.TRUE.equals(stringValValue.getValue()));
                        else if ("minLength()".equals(stringValKey.toString()))
                           stringValidator.setMinLength((Integer) stringValValue.getValue());
                        else if ("maxLength()".equals(stringValKey.toString()))
                           stringValidator.setMaxLength((Integer) stringValValue.getValue());
                        else if ("regex()".equals(stringValKey.toString()))
                           stringValidator.setRegex((String) stringValValue.getValue());
                     }
                  } else if ("number()".equals(propValidatorKey.toString())) {
                     /* build number validator */
                     AnnotationValue numberCheck = elementUtils.getElementValuesWithDefaults(propertyValidatorMirror)
                           .get(propValidatorKey);
                     AnnotationMirror numberAnnoMirror = (AnnotationMirror) numberCheck.getValue();

                     for (ExecutableElement numberValKey : elementUtils.getElementValuesWithDefaults(numberAnnoMirror)
                           .keySet()) {
                        AnnotationValue numberValValue = elementUtils.getElementValuesWithDefaults(numberAnnoMirror)
                              .get(numberValKey);

                        if ("bypass()".equals(numberValKey.toString()))
                           numberValidator.setBypass(Boolean.TRUE.equals(numberValValue.getValue()));
                     }
                  } else if ("validators()".equals(propValidatorKey.toString())) {
                     AnnotationValue customValidatorsAnno = elementUtils
                           .getElementValuesWithDefaults(propertyValidatorMirror).get(propValidatorKey);

                     for (AnnotationValue attr : (Collection<AnnotationValue>) customValidatorsAnno.getValue()) {
                        DeclaredType type = (DeclaredType) attr.getValue();

                        customValidators.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments(type));
                     }
                  }
               }
            }
         }
      }
   }

   public boolean isBypass() {
      if (null == validatorAnno)
         return true;

      return validatorAnno.bypass();
   }

   public String getValidatorMethodName() {
      return "validate" + fieldDescriptor.getName().substring(0, 1).toUpperCase()
            + fieldDescriptor.getName().substring(1) + "Property";
   }

   public boolean isIgnoreOnFailure() {
      return validatorAnno.ignoreOnFailure();
   }

   public boolean hasTypeCheck() {
      return !Object.class.getName().equals(
            SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments((DeclaredType) typeCheck.getValue()));
   }

   public DeclaredType getTypeForTypeCheck() {
      return (DeclaredType) typeCheck.getValue();
   }

   public StringValidator getStringValidator() {
      return stringValidator;
   }

   public NumberValidator getNumberValidator() {
      return numberValidator;
   }

   public List<String> getCustomValidators() {
      return customValidators;
   }
}
