package net.datenwerke.dtoservices.dtogenerator.analizer;

import java.util.Collection;
import java.util.HashSet;

import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;

public class TypeAnalizer {

   private final TypeMirror type;
   private final DtoAnnotationProcessor dtoAnnotationProcessor;

   public TypeAnalizer(DtoAnnotationProcessor dtoAnnotationProcessor, TypeMirror type) {
      this.dtoAnnotationProcessor = dtoAnnotationProcessor;
      this.type = type;
   }

   public boolean isCollection() {
      if (type instanceof DeclaredType)
         return null != SourceFileGenerationUtils.isCollection((DeclaredType) type);

      return false;
   }

   public boolean isMap() {
      if (type instanceof DeclaredType)
         return null != SourceFileGenerationUtils.isMap((DeclaredType) type);

      return false;
   }

   public boolean isPoso() {
      if (type instanceof DeclaredType)
         return SourceFileGenerationUtils.isPoso((DeclaredType) type);

      return false;
   }

   public boolean isPosoClass() {
      if (type instanceof DeclaredType)
         return SourceFileGenerationUtils.isPosoClass((DeclaredType) type);

      return false;
   }

   public boolean isPosoEnum() {
      if (type instanceof DeclaredType)
         return SourceFileGenerationUtils.isPosoEnum((DeclaredType) type);

      return false;
   }

   public PosoAnalizer getPoso() {
      if (!isPoso())
         throw new IllegalStateException(type + " does not reference a poso");

      return dtoAnnotationProcessor.getPosoAnalizerFor(((DeclaredType) type).asElement());
   }

   public boolean isString() {
      if (type instanceof DeclaredType)
         return "java.lang.String"
               .equals(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments((DeclaredType) type));

      return false;
   }

   public boolean isSet() {
      if (type instanceof DeclaredType) {
         return SourceFileGenerationUtils.isSet((DeclaredType) type);
      }

      return false;
   }

   public boolean isList() {
      if (type instanceof DeclaredType) {
         return SourceFileGenerationUtils.isList((DeclaredType) type);
      }

      return false;
   }

   public boolean isPosoCollection() {
      if (!isCollection())
         return false;

      return SourceFileGenerationUtils.isPosoCollection((DeclaredType) type);
   }

   public PosoAnalizer getPosoReferencedInCollection() {
      if (!isPosoCollection())
         throw new IllegalStateException("This field does not reference a collection of posos");

      return SourceFileGenerationUtils.getPosoInCollection((DeclaredType) type);
   }

   public String getTypesSimpleName() {
      if (!(type instanceof DeclaredType))
         return type.toString();

      return ((DeclaredType) type).asElement().getSimpleName().toString();
   }

   public String getKnownDtoType() {
      return getKnownDtoType(new HashSet<String>());
   }

   public String getKnownDtoType(Collection<String> referenceAccu) {
      if (isCollection()) {
         DeclaredType collection = (DeclaredType) type;

         /* add collection to referenced classes */
         referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments(collection));

         if (!isPosoCollection())
            return SourceFileGenerationUtils.getSimpleTypeName(collection);
         else {
            /* add poso to references */
            referenceAccu.add(getPosoReferencedInCollection().getDtoInformation().getFullyQualifiedClassName());

            /* build collection type */
            String type = SourceFileGenerationUtils.getSimpleNameWithoutTypeArguments(collection);
            return type + "<" + getPosoReferencedInCollection().getDtoInformation().getClassName() + ">";
         }
      } else if (isMap()) {
         DeclaredType map = (DeclaredType) type;

         referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments(map));

         return SourceFileGenerationUtils.getSimpleTypeName(map);
      } else if (isPoso()) {
         referenceAccu.add(getPoso().getDtoInformation().getFullyQualifiedClassName());
         return getPoso().getDtoInformation().getClassName();
      }

      if (type instanceof DeclaredType)
         referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments((DeclaredType) type));

      return getTypesSimpleName();
   }

   public String getCorrectParameterType() {
      return getCorrectParameterType(new HashSet<String>());
   }

   public String getCorrectParameterType(Collection<String> referenceAccu) {
      if (type instanceof DeclaredType) {
         if (null != SourceFileGenerationUtils.isCollection((DeclaredType) type)) {
            DeclaredType collection = (DeclaredType) type;

            /* add collection to referenced classes */
            referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments(collection));

            if (!SourceFileGenerationUtils.isPosoCollection(collection))
               return SourceFileGenerationUtils.getSimpleTypeName(collection);
            else {
               /* add poso to references */
               PosoAnalizer poso = SourceFileGenerationUtils.getPosoInCollection(collection);
               referenceAccu.add(poso.getDtoInformation().getFullyQualifiedClassName());

               /* build collection type */
               String typeName = SourceFileGenerationUtils.getSimpleNameWithoutTypeArguments(collection);
               return typeName + "<" + poso.getDtoInformation().getClassName() + ">";
            }
         } else if (SourceFileGenerationUtils.isPoso((DeclaredType) type)) {
            PosoAnalizer poso = SourceFileGenerationUtils.getPoso((DeclaredType) type);
            referenceAccu.add(poso.getDtoInformation().getFullyQualifiedClassName());
            return poso.getDtoInformation().getClassName();
         }

         referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments((DeclaredType) type));
         return ((DeclaredType) type).asElement().getSimpleName().toString();
      }

      return type.toString();
   }
}
