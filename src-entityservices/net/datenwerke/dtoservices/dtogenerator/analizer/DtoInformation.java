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

/**
 * Provides information on a dto for a given poso.
 * 
 *
 */
public class DtoInformation {

   private PosoAnalizer posoAnalizer;
   private DtoAnnotationProcessor dtoAnnotationProcessor;

   public DtoInformation(PosoAnalizer posoAnalizer, DtoAnnotationProcessor dtoAnnotationProcessor) {
      super();
      this.posoAnalizer = posoAnalizer;
      this.dtoAnnotationProcessor = dtoAnnotationProcessor;
   }

   public String getPackageName() {
      return posoAnalizer.getGenerateDtoAnnotation().dtoPackage();
   }

   public String getClassName() {
      return posoAnalizer.getSimpleName() + DtoAnnotationProcessor.DTO_EXTENSION;
   }

   public String getDto2PosoMapClassName() {
      return posoAnalizer.getSimpleName() + DtoAnnotationProcessor.DTO_POSO_MAP_EXTENSION;
   }

   public String getFullyQualifiedDto2PosoMapClassName() {
      return getDto2PosoMapPackageName() + "." + getDto2PosoMapClassName();
   }

   public String getDto2PropertyAccessClassName() {
      return posoAnalizer.getSimpleName() + DtoAnnotationProcessor.DTO_PROPERTY_ACCESS_EXTENSION;
   }

   public String getFullyQualifiedPropertyAccesClassName() {
      return getDto2PropertyAccessPackageName() + "." + getDto2PropertyAccessClassName();
   }

   public String getFullyQualifiedClassName() {
      return getPackageName() + "." + getClassName();
   }

   public Long getSerialVersionUID() {
      return posoAnalizer.getGenerateDtoAnnotation().dtoSerialVersionUID() == 0 ? 1L
            : posoAnalizer.getGenerateDtoAnnotation().dtoSerialVersionUID();
   }

   public boolean isAbstractDto() {
      return posoAnalizer.getGenerateDtoAnnotation().abstractDto();
   }

   public boolean isEnum() {
      return posoAnalizer.isPosoEnum();
   }

   public boolean isInterface() {
      return posoAnalizer.isPosoInterface();
   }

   public boolean hasDecorator() {
      return posoAnalizer.getGenerateDtoAnnotation().createDecorator();
   }

   public String getPackageNameForDecorator() {
      return getPackageName() + ".decorator";
   }

   public String getClassNameForDecorator() {
      return posoAnalizer.getSimpleName() + DtoAnnotationProcessor.DTO_DECORATOR_EXTENSION;
   }

   public String getFullyQualifiedClassNameForDecorator() {
      return getPackageNameForDecorator() + "." + getClassNameForDecorator();
   }

   public boolean generateDto() {
      return posoAnalizer.getGenerateDtoAnnotation().generateDto();
   }

   public boolean hasSpecializedDisplayTitle() {
      return !"".equals(posoAnalizer.getGenerateDtoAnnotation().displayTitle())
            || null != posoAnalizer.getDisplayTitleField();
   }

   public boolean isProxyable() {
      PosoAnalizer poso = posoAnalizer;
      while (null != poso) {
         if (!poso.getGenerateDtoAnnotation().proxyableDto())
            return false;
         poso = dtoAnnotationProcessor.getPosoAnalizerFor(poso.getParentPoso());
      }
      return null != posoAnalizer.getIdField();
   }

   public String getDto2PosoMapPackageName() {
      return getPackageName() + ".posomap";
   }

   public String getDto2PropertyAccessPackageName() {
      return getPackageName() + ".pa";
   }

   public boolean hasAdditionalImports() {
      AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();
      for (ExecutableElement key : gdAnno.getElementValues().keySet())
         if (key.toString().equals("additionalImports()"))
            return true;

      return false;
   }

   public List<DeclaredType> getAdditionalImports() {
      List<DeclaredType> whitelist = new ArrayList<DeclaredType>();

      AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();
      for (ExecutableElement key : gdAnno.getElementValues().keySet()) {
         if (key.toString().equals("additionalImports()")) {
            AnnotationValue value = gdAnno.getElementValues().get(key);
            Object postProcessors = value.getValue();

            if (postProcessors instanceof Collection) {
               for (AnnotationValue attr : (Collection<AnnotationValue>) postProcessors) {
                  DeclaredType type = (DeclaredType) attr.getValue();
                  whitelist.add(type);
               }
            }
            break;
         }
      }

      return whitelist;
   }

   public boolean hasWhitelist() {
      AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();
      for (ExecutableElement key : gdAnno.getElementValues().keySet())
         if (key.toString().equals("whitelist()"))
            return true;

      return false;
   }

   public List<DeclaredType> getWhitelist() {
      List<DeclaredType> whitelist = new ArrayList<DeclaredType>();

      AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();
      for (ExecutableElement key : gdAnno.getElementValues().keySet()) {
         if (key.toString().equals("whitelist()")) {
            AnnotationValue value = gdAnno.getElementValues().get(key);
            Object postProcessors = value.getValue();

            if (postProcessors instanceof Collection) {
               for (AnnotationValue attr : (Collection<AnnotationValue>) postProcessors) {
                  DeclaredType type = (DeclaredType) attr.getValue();
                  whitelist.add(type);
               }
            }
            break;
         }
      }

      return whitelist;
   }

   public DeclaredType getTypeDescriptionMsgInterface() {
      AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();
      Elements elementUtils = dtoAnnotationProcessor.getProcessingEnvironment().getElementUtils();

      for (ExecutableElement key : gdAnno.getElementValues().keySet()) {
         if (key.toString().equals("typeDescriptionMsg()")) {
            AnnotationValue value = elementUtils.getElementValuesWithDefaults(gdAnno).get(key);
            System.out
                  .println("lalala: " + getClassName() + " -- " + String.valueOf(value) + " -- " + value.getClass());
//				if(value.getValue() instanceof DeclaredType)
            return (DeclaredType) value.getValue();
//				try{
//					posoAnalizer.getGenerateDtoAnnotation().typeDescriptionMsg();
//				} catch(MirroredTypeException e){
//					return (DeclaredType) e.getTypeMirror();
//				} catch(AnnotationTypeMismatchException e ){
//					System.out.println("lalal: " + e.foundType());
//					throw e;
//				}
         }
      }

      return null;
   }

}
