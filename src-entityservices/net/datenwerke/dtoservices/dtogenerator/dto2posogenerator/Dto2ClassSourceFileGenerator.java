package net.datenwerke.dtoservices.dtogenerator.dto2posogenerator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.lang.model.type.DeclaredType;

import net.datenwerke.annotationprocessing.utils.MethodBuilder;
import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoFieldDescriptor;
import net.datenwerke.dtoservices.dtogenerator.analizer.PropertyValidatorInformation;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientKey;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * 
 *
 */
public class Dto2ClassSourceFileGenerator extends Dto2PosoSourceFileGenerator {

   public Dto2ClassSourceFileGenerator(PosoAnalizer posoAnalizer, DtoAnnotationProcessor dtoAnnotationProcessor) {
      super(posoAnalizer, dtoAnnotationProcessor);
   }

   protected void addConstructorArguments(Map<String, String> arguments) {
      arguments.put("reflectionService", ReflectionService.class.getSimpleName());

      for (PosoFieldDescriptor field : posoAnalizer.getAllExposedFields()) {
         PropertyValidatorInformation validator = field.getPropertValidator();

         int nr = 1;
         for (String customValidator : validator.getCustomValidators()) {
            String name = getValidatorName(nr, field);
            arguments.put(name, DtoPropertyValidator.class.getSimpleName());

            nr++;
         }
      }
   }

   protected void addAdditionalMemberVariables(StringBuilder sourceBuilder) {
      sourceBuilder.append("\tprivate final " + ReflectionService.class.getSimpleName() + " reflectionService;\n");

      for (PosoFieldDescriptor field : posoAnalizer.getAllExposedFields()) {
         PropertyValidatorInformation validator = field.getPropertValidator();

         int nr = 1;
         for (String customValidator : validator.getCustomValidators()) {
            sourceBuilder.append("\tprivate final " + DtoPropertyValidator.class.getSimpleName() + " "
                  + getValidatorName(nr, field) + ";\n");
            nr++;
         }

         if (nr > 1)
            sourceBuilder.append("\n");
      }
   }

   private String getValidatorName(int nr, PosoFieldDescriptor field) {
      return "customValidator_" + nr + "_" + field.getName();
   }

   @Override
   protected void addCustomMethods(StringBuilder sourceBuilder) {
      addValidatorMethods(sourceBuilder);
   }

   private void addValidatorMethods(StringBuilder sourceBuilder) {
      for (PosoFieldDescriptor field : posoAnalizer.getAllExposedFields()) {
         if (field.isValidatorPresent()) {
            addValidatorMethodFor(field, sourceBuilder);
         }
      }
   }

   private void addValidatorMethodFor(PosoFieldDescriptor field, StringBuilder sourceBuilder) {
      PropertyValidatorInformation validator = field.getPropertValidator();

      MethodBuilder method = new MethodBuilder(validator.getValidatorMethodName(), "boolean", getDtoType() + " dto",
            getPosoType() + " poso");
      method.setThrows(ExpectedException.class.getSimpleName());

      /* wrap everything in a try catch block if we should simply return a boolean */
      if (validator.isIgnoreOnFailure())
         method.beginTryBlock();

      /* get property value */
      method.addBodyLine("Object propertyValue = dto." + field.getGetMethodForDto() + "();");
      method.addBodyLine();

      /* allow null */
      method.addBodyComment("allow null");
      method.beginOneLineBlock("if(null == propertyValue)");
      method.addBodyLine("return true;");

      /* add type check */
      if (validator.hasTypeCheck()) {
         DeclaredType type = validator.getTypeForTypeCheck();
         String qName = SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments(type);

         method.addBodyComment("make sure passed element has superclass " + qName);
         method.beginOneLineBlock("if(! " + qName + ".class.isAssignableFrom(propertyValue.getClass()))");
         throwValidationFailedException(method, "type check failed for property " + field.getName(),
               "expected " + qName);
      }

      /* add string checks */
      PropertyValidatorInformation.StringValidator sValidator = validator.getStringValidator();
      if (!sValidator.isBypass()) {
         /* validate type */
         method.addBodyLine();
         method.addBodyComment("make sure property is string");
         method.beginOneLineBlock("if(! java.lang.String.class.isAssignableFrom(propertyValue.getClass()))");
         throwValidationFailedException(method, "String validation failed for " + field.getName(), "expected a String");

         /* validate properties */
         if (sValidator.getMinLength() != -1) {
            method.addBodyLine();
            method.beginOneLineBlock("if(((String)propertyValue).length() < " + sValidator.getMinLength() + ") ");
            throwValidationFailedException(method, "String validation failed for " + field.getName(),
                  "passed String is too short. Expected minimum length is: " + sValidator.getMinLength());
         }
         if (sValidator.getMaxLength() != -1) {
            method.addBodyLine();
            method.beginOneLineBlock("if(((String)propertyValue).length() > " + sValidator.getMaxLength() + ") ");
            throwValidationFailedException(method, "String validation failed for " + field.getName(),
                  "passed String is too long. Expected maximum length is: " + sValidator.getMaxLength());
         }
         if (!"".equals(sValidator.getRegex())) {
            method.addBodyLine();
            method.beginOneLineBlock(
                  "if(! ((String)propertyValue).matches(\"" + sValidator.getRegex().replace("\\", "\\\\") + "\"))");
            throwValidationFailedException(method, "String validation failed for " + field.getName(),
                  " Regex test failed.");
         }
      }

      /* add number checks */
      PropertyValidatorInformation.NumberValidator nValidator = validator.getNumberValidator();
      if (!nValidator.isBypass()) {

      }

      /* add custom validator checks */
      int nr = 1;
      for (String customValidator : validator.getCustomValidators()) {
         if (nr == 1) {
            method.addBodyLine();
            method.addBodyComment("custom validation");
         }

         method.addBodyLine(getValidatorName(nr, field) + ".validateProperty(propertyValue, dto, poso);");
         nr++;
      }

      /* complete method */
      method.addBodyLine();
      method.addBodyComment("all went well");
      method.addBodyLine("return true;");

      /* complete try block */
      if (validator.isIgnoreOnFailure()) {
         method.beginCatchBlock(ValidationFailedException.class.getSimpleName() + " e");
         referenceAccu.add(ValidationFailedException.class.getName());
         method.addBodyLine("return false;");
         method.endBodyBlock();
      }

      sourceBuilder.append(method).append("\n");
   }

   private void throwValidationFailedException(MethodBuilder method, String header, String msg) {
      referenceAccu.add(ValidationFailedException.class.getName());
      method.addBodyLine("throw new ValidationFailedException(\"" + header + "\", \"" + msg + "\");");
   }

   @Override
   protected void addMergePoso(StringBuilder sourceBuilder) {
      MethodBuilder method = new MethodBuilder("mergePoso", "void", getDtoType() + " dto",
            "final " + getPosoType() + " poso");
      method.setThrows(ExpectedException.class.getSimpleName());

      method.beginOneLineBlock("if(dto.isDtoProxy())");
      method.addBodyLine("mergeProxy2Poso(dto, poso);");
      method.addOneLineElse();
      method.addBodyLine("mergePlainDto2Poso(dto, poso);");

      sourceBuilder.append(method).append("\n");

      addMergePlainDto(sourceBuilder, false);
      addMergeProxy(sourceBuilder, false);
   }

   @Override
   protected void addMergeUnmanagedPoso(StringBuilder sourceBuilder) {
      MethodBuilder method = new MethodBuilder("mergeUnmanagedPoso", "void", getDtoType() + " dto",
            "final " + getPosoType() + " poso");
      method.setThrows(ExpectedException.class.getSimpleName());

      method.beginOneLineBlock("if(dto.isDtoProxy())");
      method.addBodyLine("mergeProxy2UnmanagedPoso(dto, poso);");
      method.addOneLineElse();
      method.addBodyLine("mergePlainDto2UnmanagedPoso(dto, poso);");

      sourceBuilder.append(method).append("\n");

      addMergePlainDto(sourceBuilder, true);
      addMergeProxy(sourceBuilder, true);
   }

   private void addMergePlainDto(StringBuilder sourceBuilder, boolean unmanaged) {
      MethodBuilder method = null;
      if (unmanaged)
         method = new MethodBuilder("mergePlainDto2UnmanagedPoso", "void", getDtoType() + " dto",
               "final " + getPosoType() + " poso");
      else
         method = new MethodBuilder("mergePlainDto2Poso", "void", getDtoType() + " dto",
               "final " + getPosoType() + " poso");
      method.setProtectedModifier();
      method.setThrows(ExpectedException.class.getSimpleName());

      /* prepare sections */
      List<SetPropertyInPosoSectionCreator> setPropertyCreators = new ArrayList<SetPropertyInPosoSectionCreator>();
      for (PosoFieldDescriptor field : posoAnalizer.getAllExposedFields()) {
         if (!field.hasIgnoredMergeBackSetMethod()) {
            SetPropertyInPosoSectionCreator propertyCreator = new SetPropertyInPosoSectionCreator(posoAnalizer, field);
            propertyCreator.setMerge2Unmanaged(unmanaged);
            setPropertyCreators.add(propertyCreator);
         }
      }

      for (SetPropertyInPosoSectionCreator setCreator : setPropertyCreators)
         setCreator.addSetSection(referenceAccu, method);

      sourceBuilder.append(method).append("\n");
   }

   private void addMergeProxy(StringBuilder sourceBuilder, boolean unmanaged) {
      MethodBuilder method = null;
      if (unmanaged)
         method = new MethodBuilder("mergeProxy2UnmanagedPoso", "void", getDtoType() + " dto",
               "final " + getPosoType() + " poso");
      else
         method = new MethodBuilder("mergeProxy2Poso", "void", getDtoType() + " dto",
               "final " + getPosoType() + " poso");
      method.setProtectedModifier();
      method.setThrows(ExpectedException.class.getSimpleName());

      /* prepare sections */
      List<SetPropertyInPosoSectionCreator> setPropertyCreators = new ArrayList<SetPropertyInPosoSectionCreator>();

      for (PosoFieldDescriptor field : posoAnalizer.getAllExposedFields()) {
         if (!field.hasIgnoredMergeBackSetMethod()) {
            SetPropertyInPosoSectionCreator setCreator = new SetPropertyInPosoSectionCreator(posoAnalizer, field);
            setCreator.setUseProxy();
            setCreator.setMerge2Unmanaged(unmanaged);
            setPropertyCreators.add(setCreator);
         }
      }

      for (SetPropertyInPosoSectionCreator setCreator : setPropertyCreators)
         setCreator.addSetSection(referenceAccu, method);

      sourceBuilder.append(method).append("\n");
   }

   @Override
   protected void addloadAndMergePoso(StringBuilder sourceBuilder) {
      MethodBuilder method = new MethodBuilder("loadAndMergePoso", getPosoType(), getDtoType() + " dto");
      method.setThrows(ExpectedException.class.getSimpleName());

      method.addBodyLine(getPosoType() + " poso = loadPoso(dto);");

      method.beginBodyBlock("if(null != poso)");
      method.addBodyLine("mergePoso(dto, poso);");
      method.addBodyLine("return poso;");
      method.endBodyBlock();

      method.addBodyLine("return createPoso(dto);");

      sourceBuilder.append(method).append("\n");
   }

   @Override
   protected void addLoadPoso(StringBuilder sourceBuilder) {
      MethodBuilder method = new MethodBuilder("loadPoso", getPosoType(), getDtoType() + " dto");

      if (!posoAnalizer.isJpaEntity()) {
         method.addBodyComment("Poso is not an entity .. so what should I load?");
         method.addBodyLine("return null;");
      } else {
         if (null == posoAnalizer.getIdField() || !posoAnalizer.isJpaEntity()) {
            method.addBodyComment("Poso does not specify an ID field .. or is no entity.. so what should I load?");

            method.addBodyLine("return null;");
         } else {
            method.beginOneLineBlock("if(null == dto)");
            method.addBodyLine("return null;");

            method.addBodyLine();
            method.addBodyComment("get id");
            method.addBodyLine("Object id = dto." + posoAnalizer.getIdField().getGetMethodForDto() + "();");

            method.beginOneLineBlock("if(null == id)");
            method.addBodyLine("return null;");

            method.addBodyLine();
            method.addBodyComment("load poso from db");
            method.addBodyLine("EntityManager entityManager = " + ENTITY_MANAGER_PROVIDER_NAME + ".get();");
            method.addBodyLine(getPosoType() + " poso = entityManager.find(" + getPosoType() + ".class, id);");

            method.addBodyLine("return poso;");
         }
      }

      sourceBuilder.append(method).append("\n");
   }

   @Override
   protected void addInstantiatePoso(StringBuilder sourceBuilder) {
      MethodBuilder method = new MethodBuilder("instantiatePoso", getPosoType());

      method.addBodyLine(getPosoType() + " poso = new " + getPosoType() + "();");

      method.addBodyLine("return poso;");

      sourceBuilder.append(method).append("\n");
   }

   @Override
   protected void addCreatePoso(StringBuilder sourceBuilder) {
      MethodBuilder method = new MethodBuilder("createPoso", getPosoType(), getDtoType() + " dto");
      method.setThrows(ExpectedException.class.getSimpleName());

      method.addBodyLine(getPosoType() + " poso = new " + getPosoType() + "();");

      method.addBodyLine();
      method.addBodyComment("merge data");
      method.addBodyLine("mergePoso(dto, poso);");

      method.addBodyLine("return poso;");

      sourceBuilder.append(method).append("\n");
   }

   @Override
   protected void addCreateUnmanagedPoso(StringBuilder sourceBuilder) {
      MethodBuilder method = new MethodBuilder("createUnmanagedPoso", getPosoType(), getDtoType() + " dto");
      method.setThrows(ExpectedException.class.getSimpleName());

      method.addBodyLine(getPosoType() + " poso = new " + getPosoType() + "();");

      method.addBodyLine();

      if (null != posoAnalizer.getIdField() || posoAnalizer.hasIdFieldInHeritage()) {
         PosoFieldDescriptor idDescriptor = posoAnalizer.getIdField();

         method.addBodyComment("store old id");
         method.beginBodyBlock("if(null != dto." + idDescriptor.getGetMethodForDto() + "())");
         {
            method.addBodyLine("Field transientIdField = reflectionService.getFieldByAnnotation(poso, "
                  + TransientID.class.getSimpleName() + ".class);");
            method.beginBodyBlock("if(null != transientIdField)");
            {
               method.addBodyLine("transientIdField.setAccessible(true);");

               method.beginTryBlock();
               method.addBodyLine("transientIdField.set(poso, dto." + idDescriptor.getGetMethodForDto() + "());");
               method.beginCatchBlock("Exception e");
               method.endBodyBlock();
            }
            method.endBodyBlock();
         }
         method.endBodyBlock();

         method.addBodyLine();
      }

      if (null != posoAnalizer.getKeyField() || posoAnalizer.hasKeyFieldInHeritage()) {
         PosoFieldDescriptor keyDescriptor = posoAnalizer.getKeyField();

         method.addBodyComment("store old key");
         method.beginBodyBlock("if(null != dto." + keyDescriptor.getGetMethodForDto() + "())");
         {
            method.addBodyLine("Field transientKeyField = reflectionService.getFieldByAnnotation(poso, "
                  + TransientKey.class.getSimpleName() + ".class);");
            method.beginBodyBlock("if(null != transientKeyField)");
            {
               method.addBodyLine("transientKeyField.setAccessible(true);");

               method.beginTryBlock();
               method.addBodyLine("transientKeyField.set(poso, dto." + keyDescriptor.getGetMethodForDto() + "());");
               method.beginCatchBlock("Exception e");
               method.endBodyBlock();
            }
            method.endBodyBlock();
         }
         method.endBodyBlock();

         method.addBodyLine();
      }

      method.addBodyLine("mergePlainDto2UnmanagedPoso(dto,poso);");

      method.addBodyLine();

      method.addBodyLine("return poso;");

      sourceBuilder.append(method).append("\n");
   }

   protected Collection<String> getReferencedClasses() {
      Collection<String> imports = super.getReferencedClasses();

      imports.add(ReflectionService.class.getName());
      imports.add(DtoMainService.class.getName());
      imports.add(ExpectedException.class.getName());
      imports.add(DtoPropertyValidator.class.getName());
      imports.add(TransientID.class.getName());
      imports.add(Field.class.getName());
      imports.add(Exception.class.getName());

      return imports;
   }

}
