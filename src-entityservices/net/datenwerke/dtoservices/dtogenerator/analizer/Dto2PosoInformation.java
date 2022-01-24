package net.datenwerke.dtoservices.dtogenerator.analizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.Elements;

import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl;

/**
 * Provides information on a dto generator for a given poso.
 * 
 *
 */
public class Dto2PosoInformation {

   private PosoAnalizer posoAnalizer;
   private DtoAnnotationProcessor dtoAnnotationProcessor;

   public Dto2PosoInformation(PosoAnalizer posoAnalizer, DtoAnnotationProcessor dtoAnnotationProcessor) {
      super();
      this.posoAnalizer = posoAnalizer;
      this.dtoAnnotationProcessor = dtoAnnotationProcessor;
   }

   public String getSubPackageFor(Element element) {
      /* get properties from annotation new file */
      GenerateDto geAnno = element.getAnnotation(GenerateDto.class);
      if (null == geAnno)
         throw new IllegalArgumentException(
               element.getSimpleName() + " is not annotated with " + GenerateDto.class.getSimpleName());

      return geAnno.poso2DtoGeneratorPackage();
   }

   public String getClassName() {
      return "Dto2" + posoAnalizer.getSimpleName() + "Generator";
   }

   public String getPackage() {
      String subsubpackage = posoAnalizer.getPackage();
      String subpackage = posoAnalizer.getGenerateDtoAnnotation().poso2DtoGeneratorPackage();

      return "".equals(subpackage) ? subsubpackage : subsubpackage + "." + subpackage;
   }

   public String getFullyQualifiedClassName() {
      return getPackage() + "." + getClassName();
   }

   public boolean generateGenerator() {
      return (posoAnalizer.isPosoClass() || posoAnalizer.isPosoEnum()) && !posoAnalizer.isAbstract()
            && posoAnalizer.getGenerateDtoAnnotation().generateDto2Poso();
   }

   public boolean hasPostProcessors() {
      AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();
      for (ExecutableElement key : gdAnno.getElementValues().keySet())
         if (key.toString().equals("dto2PosoPostProcessors()"))
            return true;

      if (null != posoAnalizer.getParentPoso())
         return dtoAnnotationProcessor.getPosoAnalizerFor(posoAnalizer.getParentPoso()).getDto2PosoInformation()
               .hasPostProcessors();

      return false;
   }

   public List<DeclaredType> getPostProcessors() {
      List<DeclaredType> processors = new ArrayList<DeclaredType>();

      /* get parent processors */
      if (null != posoAnalizer.getParentPoso()) {
         PosoAnalizer parent = dtoAnnotationProcessor.getPosoAnalizerFor(posoAnalizer.getParentPoso());
         processors.addAll(parent.getDto2PosoInformation().getPostProcessors());
      }

      AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();
      for (ExecutableElement key : gdAnno.getElementValues().keySet()) {
         if (key.toString().equals("dto2PosoPostProcessors()")) {
            AnnotationValue value = gdAnno.getElementValues().get(key);
            Object postProcessors = value.getValue();

            if (postProcessors instanceof Collection) {
               for (AnnotationValue attr : (Collection<AnnotationValue>) postProcessors) {
                  DeclaredType type = (DeclaredType) attr.getValue();
                  processors.add(type);
               }
            }
            break;
         }
      }

      return processors;
   }

   public DeclaredType getDto2PosoSupervisor() {
      Elements elementUtils = dtoAnnotationProcessor.getProcessingEnvironment().getElementUtils();

      AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();

      for (ExecutableElement key : elementUtils.getElementValuesWithDefaults(gdAnno).keySet()) {
         if (key.toString().equals("dto2PosoSupervisor()")) {
            AnnotationValue value = elementUtils.getElementValuesWithDefaults(gdAnno).get(key);
            Object supervisor = value.getValue();

            dtoAnnotationProcessor.debug("supervisor candidate: " + supervisor);

            if (supervisor instanceof DeclaredType) {
               if (Dto2PosoSupervisorDefaultImpl.class.getName()
                     .equals(((DeclaredType) supervisor).asElement().toString())) {
                  if (null != posoAnalizer.getParentPoso()) {
                     PosoAnalizer parent = dtoAnnotationProcessor.getPosoAnalizerFor(posoAnalizer.getParentPoso());
                     return parent.getDto2PosoInformation().getDto2PosoSupervisor();
                  }
               }
               return (DeclaredType) supervisor;
            }
            break;
         }
      }

      throw new IllegalStateException("Could not find supervisor");
   }

}
