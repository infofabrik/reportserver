package net.datenwerke.dtoservices.dtogenerator.analizer;

import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeMethodToClient;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

/**
 * Represents a method that is exposed to the client.
 * 
 * @see ExposeMethodToClient
 *
 */
public class ExposedClientMethod {

   private DtoAnnotationProcessor dtoAnnotationProcessor;
   private ExecutableElement method;

   public ExposedClientMethod(DtoAnnotationProcessor dtoAnnotationProcessor, ExecutableElement method) {
      if (!isExposedClientMethod(method))
         throw new IllegalArgumentException("Not an exposed method: " + method);

      this.dtoAnnotationProcessor = dtoAnnotationProcessor;
      this.method = method;
   }

   public static boolean isExposedClientMethod(ExecutableElement method) {
      if (null != method.getAnnotation(ExposeMethodToClient.class))
         return true;

      return false;
   }

   public ExecutableElement getMethod() {
      return method;
   }

   public boolean isGetMethod() {
      return null != method.getAnnotation(ExposeMethodToClient.class)
            && (method.getSimpleName().toString().startsWith("get")
                  || method.getSimpleName().toString().startsWith("is"));
   }

   public boolean isSetMethod() {
      return null != method.getAnnotation(ExposeMethodToClient.class)
            && method.getSimpleName().toString().startsWith("set");
   }

   public List<? extends VariableElement> getParameters() {
      return method.getParameters();
   }

   public String getSimpleName() {
      return method.getSimpleName().toString();
   }

   public TypeMirror getReturnType() {
      return method.getReturnType();
   }

   public String getFieldName() {
      if (!isGetMethod())
         throw new IllegalStateException("Not a get method: " + method);

      if (SourceFileGenerationUtils.isBooleanType(getReturnType()))
         return getSimpleName().toString().substring(2).substring(0, 1).toLowerCase()
               + getSimpleName().toString().substring(2).substring(1);
      else
         return getSimpleName().toString().substring(3).substring(0, 1).toLowerCase()
               + getSimpleName().toString().substring(3).substring(1);
   }

   public String getFieldModifiedIndicator() {
      return getFieldName() + DtoAnnotationProcessor.FIELD_MODIFIED_INDICTATOR_POSTFIX;
   }

   public String getFieldPropertyAccessor() {
      return getFieldName() + DtoAnnotationProcessor.FIELD_PROPERTY_ACCESSOR_POSTFIX;
   }

   public String getConstantFieldName() {
      if (!isGetMethod())
         throw new IllegalStateException("Not a get method: " + method);

      String name;
      if (SourceFileGenerationUtils.isBooleanType(getReturnType()))
         name = getSimpleName().toString().substring(2);
      else
         name = getSimpleName().toString().substring(3);

      return DtoAnnotationProcessor.DTO_PROPERTY_PREFIX
            + SourceFileGenerationUtils.camelCaseToUnderscoreUpperCase(name);
   }

   public boolean isGetSetMethod() {
      return isGetMethod() || isSetMethod();
   }

   public boolean returnsPoso() {
      TypeMirror type = method.getReturnType();
      if (type instanceof DeclaredType)
         return SourceFileGenerationUtils.isPoso((DeclaredType) type);

      return false;
   }

   public boolean returnsPosoClass() {
      TypeMirror type = method.getReturnType();
      if (type instanceof DeclaredType)
         return SourceFileGenerationUtils.isPosoClass((DeclaredType) type);

      return false;
   }

   public boolean returnsPosoEnum() {
      TypeMirror type = method.getReturnType();
      if (type instanceof DeclaredType)
         return SourceFileGenerationUtils.isPosoEnum((DeclaredType) type);

      return false;
   }

   public PosoAnalizer getPoso() {
      if (!returnsPoso())
         throw new IllegalStateException("Method " + method + " does not reference a poso");

      return dtoAnnotationProcessor.getPosoAnalizerFor(((DeclaredType) method.getReturnType()).asElement());
   }

   public boolean returnsCollection() {
      TypeMirror type = method.getReturnType();
      if (type instanceof DeclaredType) {
         return null != SourceFileGenerationUtils.isCollection((DeclaredType) type);
      }

      return false;
   }

   public boolean returnsPosoCollection() {
      if (!returnsCollection())
         return false;

      /* get collection type */
      DeclaredType type = (DeclaredType) method.getReturnType();

      return SourceFileGenerationUtils.isPosoCollection((DeclaredType) type);
   }

   public PosoAnalizer getPosoReferencedInReturnedCollection() {
      if (!returnsPosoCollection())
         throw new IllegalStateException("This field does not reference a collection of posos");

      /* get collection type */
      DeclaredType type = (DeclaredType) method.getReturnType();

      DeclaredType arg = (DeclaredType) type.getTypeArguments().get(0);

      return dtoAnnotationProcessor.getPosoAnalizerFor(arg.asElement());
   }

   public String getGetMethod() {
      if (isGetMethod())
         return method.getSimpleName().toString();

      return null;
   }

   public DtoView getDtoView() {
      if (isGetSetMethod())
         return method.getAnnotation(ExposeMethodToClient.class).view();

      throw new IllegalStateException("Not a get/set method: " + method);
   }

   public String getSetMethodForDto() {
      if (isGetMethod()) {
         if (SourceFileGenerationUtils.isBooleanType(getReturnType()))
            return SourceFileGenerationUtils.getSetMethodForField(getSimpleName().substring(2));
         else
            return SourceFileGenerationUtils.getSetMethodForField(getSimpleName().substring(3));
      } else if (isSetMethod()) {
         return SourceFileGenerationUtils.getSetMethodForField(getSimpleName().substring(3));
      }
      throw new IllegalStateException("Not a get/set method: " + method);
   }

   public String getIsPropertyModifiedMethodForDto() {
      if (isGetMethod()) {
         if (SourceFileGenerationUtils.isBooleanType(getReturnType()))
            return SourceFileGenerationUtils.getIsModifiedMethodForField(getSimpleName().substring(2));
         else
            return SourceFileGenerationUtils.getIsModifiedMethodForField(getSimpleName().substring(3));
      } else if (isSetMethod()) {
         return SourceFileGenerationUtils.getIsModifiedMethodForField(getSimpleName().substring(3));
      }
      throw new IllegalStateException("Not a get/set/isModified method: " + method);
   }

   public String getPropertyAccessorMethodForDto() {
      if (isGetMethod()) {
         if (SourceFileGenerationUtils.isBooleanType(getReturnType()))
            return SourceFileGenerationUtils.getPropertyAccessorMethodForField(getSimpleName().substring(2));
         else
            return SourceFileGenerationUtils.getPropertyAccessorMethodForField(getSimpleName().substring(3));
      } else if (isSetMethod()) {
         return SourceFileGenerationUtils.getPropertyAccessorMethodForField(getSimpleName().substring(3));
      }
      throw new IllegalStateException("Not a get/set/isModified/propertyAccessor method: " + method);
   }

   public String getGetMethodForDto() {
      if (isGetMethod())
         if (SourceFileGenerationUtils.isBooleanType(getReturnType()))
            return SourceFileGenerationUtils.getGetMethodForField(getSimpleName().substring(2), getReturnType());
         else
            return SourceFileGenerationUtils.getGetMethodForField(getSimpleName().substring(3), getReturnType());
      else if (isSetMethod()) {
         return SourceFileGenerationUtils.getGetMethodForField(getSimpleName().substring(3),
               getParameters().get(0).asType());
      }
      throw new IllegalStateException("Not a get/set method: " + method);
   }

   /**
    * Returns a get/set methods base name.
    * 
    * If the methods name is getFoo, then Foo is returned.
    * 
    */
   public String getBaseName() {
      if (!isGetSetMethod())
         throw new IllegalStateException("Not a get/set method: " + method);

      return getSimpleName().substring(3);
   }

   public boolean referencesEnclosedPoso() {
      return null != method.getAnnotation(EnclosedEntity.class);
   }

   public boolean returnsSet() {
      TypeMirror type = getReturnType();
      if (type instanceof DeclaredType) {
         return SourceFileGenerationUtils.isSet((DeclaredType) type);
      }

      return false;
   }

   public boolean returnsList() {
      TypeMirror type = getReturnType();
      if (type instanceof DeclaredType) {
         return SourceFileGenerationUtils.isList((DeclaredType) type);
      }

      return false;
   }

   public TypeAnalizer getReturnTypeAnalizer() {
      return new TypeAnalizer(dtoAnnotationProcessor, getReturnType());
   }

}
