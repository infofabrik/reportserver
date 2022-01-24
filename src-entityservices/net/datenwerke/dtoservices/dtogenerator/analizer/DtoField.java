package net.datenwerke.dtoservices.dtogenerator.analizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;

public class DtoField {

   private String name;
   private String modIndicator;
   private String propertyAccessor;
   private final String setMethod;
   private final String getMethod;
   private final String isModifiedMethod;
   private final String getPropertyAccessorMethod;
   private final TypeMirror realType;
   private final List<DeclaredType> genericTypes;
   private final DtoView dtoView;
   private boolean idField;
   private final String visibility;

   public DtoField(TypeMirror realType, String name, DtoView dtoView, String modIndicator, String propertyAccessor,
         String getMethod, String setMethod, String isModifiedMethod, String getPropertyAccessorMethod,
         String visibility) {
      this.realType = realType;
      this.name = name;
      this.dtoView = dtoView;
      this.modIndicator = modIndicator;
      this.propertyAccessor = propertyAccessor;
      this.getMethod = getMethod;
      this.setMethod = setMethod;
      this.isModifiedMethod = isModifiedMethod;
      this.getPropertyAccessorMethod = getPropertyAccessorMethod;
      this.visibility = visibility;
      genericTypes = new ArrayList<DeclaredType>();
   }

   public DtoField(String name, DeclaredType type, List<DeclaredType> genericTypes, String visibility) {
      this.name = name;
      this.realType = type;
      this.dtoView = DtoView.MINIMAL;
      this.modIndicator = name + DtoAnnotationProcessor.FIELD_MODIFIED_INDICTATOR_POSTFIX;
      this.propertyAccessor = name + DtoAnnotationProcessor.FIELD_PROPERTY_ACCESSOR_POSTFIX;
      this.getMethod = SourceFileGenerationUtils.getGetMethodForField(getName(), type);
      this.setMethod = SourceFileGenerationUtils.getSetMethodForField(getName());
      this.isModifiedMethod = "is" + getName().substring(0, 1).toUpperCase() + getName().substring(1) + "Modified";
      ;
      this.getPropertyAccessorMethod = "get" + getName().substring(0, 1).toUpperCase() + getName().substring(1)
            + "PropertyAccessor";

      this.genericTypes = genericTypes;
      this.visibility = visibility;
   }

   public DtoView getDtoView() {
      return dtoView;
   }

   public String getName() {
      return name;
   }

   public String getPropertyIdName() {
      return DtoAnnotationProcessor.DTO_PROPERTY_PREFIX
            + SourceFileGenerationUtils.camelCaseToUnderscoreUpperCase(getName());
   }

   public String getModIndicator() {
      return modIndicator;
   }

   public String getPropertyAccessor() {
      return propertyAccessor;
   }

   public String getSetMethod() {
      return setMethod;
   }

   public String getGetMethod() {
      return getMethod;
   }

   public String getIsModifiedMethod() {
      return isModifiedMethod;
   }

   public String getGetPropertyAccessorMethod() {
      return getPropertyAccessorMethod;
   }

   public TypeMirror getType() {
      return realType;
   }

   public TypeAnalizer getTypeAnalizer(DtoAnnotationProcessor dtoAnnotationProcessor) {
      return new TypeAnalizer(dtoAnnotationProcessor, getType());
   }

   public String getKnownDtoType(DtoAnnotationProcessor dtoAnnotationProcessor, Set<String> referenceAccu) {
      String result = getTypeAnalizer(dtoAnnotationProcessor).getKnownDtoType(referenceAccu);

      if (!genericTypes.isEmpty()) {
         result += "<";
         boolean first = true;
         for (DeclaredType generic : genericTypes) {
            referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments(generic));
            if (first)
               first = false;
            else
               result += ", ";
            result += SourceFileGenerationUtils.getSimpleNameWithoutTypeArguments(generic);
         }
         result += ">";
      }

      return result;
   }

   public boolean isCollection(DtoAnnotationProcessor dtoAnnotationProcessor) {
      return getTypeAnalizer(dtoAnnotationProcessor).isCollection();
   }

   public boolean isPosoCollection(DtoAnnotationProcessor dtoAnnotationProcessor) {
      if (!isCollection(dtoAnnotationProcessor))
         return false;

      if (genericTypes.isEmpty())
         return getTypeAnalizer(dtoAnnotationProcessor).isPosoCollection();

      for (DeclaredType generic : genericTypes)
         if (SourceFileGenerationUtils.isPoso(generic))
            return true;

      return false;
   }

   public PosoAnalizer getPosoReferencedInCollection(DtoAnnotationProcessor dtoAnnotationProcessor) {
      if (!isPosoCollection(dtoAnnotationProcessor))
         return null;

      if (genericTypes.isEmpty())
         return getTypeAnalizer(dtoAnnotationProcessor).getPosoReferencedInCollection();

      for (DeclaredType generic : genericTypes)
         if (SourceFileGenerationUtils.isPoso(generic))
            return SourceFileGenerationUtils.getPoso(generic);

      return null;
   }

   public boolean isPosoClass(DtoAnnotationProcessor dtoAnnotationProcessor) {
      return getTypeAnalizer(dtoAnnotationProcessor).isPosoClass();
   }

   public boolean isSet(DtoAnnotationProcessor dtoAnnotationProcessor) {
      return getTypeAnalizer(dtoAnnotationProcessor).isSet();
   }

   public boolean isList(DtoAnnotationProcessor dtoAnnotationProcessor) {
      return getTypeAnalizer(dtoAnnotationProcessor).isList();
   }

   public String getGenericTypeString() {
      if (genericTypes.isEmpty())
         return null;

      String result = "";
      boolean first = true;
      for (DeclaredType generic : genericTypes) {
         if (first)
            first = false;
         else
            result += ", ";
         result += SourceFileGenerationUtils.getSimpleNameWithoutTypeArguments(generic);
      }

      return result;
   }

   public void setIsIdField(boolean b) {
      idField = b;
   }

   public boolean isIdField() {
      return idField;
   }

   public String getVisibility() {
      return visibility;
   }

}
