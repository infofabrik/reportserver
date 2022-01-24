package net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.annotationprocessing.utils.MethodBuilder;
import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.DtoInformation;
import net.datenwerke.dtoservices.dtogenerator.analizer.ExposedClientMethod;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoFieldDescriptor;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;

/**
 * 
 *
 */
public class Class2DtoSourceFileGenerator extends Poso2DtoSourceFileGenerator {

   public Class2DtoSourceFileGenerator(PosoAnalizer posoAnalizer, DtoAnnotationProcessor dtoAnnotationProcessor) {
      super(posoAnalizer, dtoAnnotationProcessor);
   }

   @Override
   protected void addCreateDtoMethod(StringBuilder sourceBuilder) {
      DtoInformation dtoInfo = posoAnalizer.getDtoInformation();

      /* find the correct dto class */
      String dtoClass;

      if (posoAnalizer.getDtoInformation().hasDecorator()) {
         referenceAccu.add(posoAnalizer.getDtoInformation().getFullyQualifiedClassNameForDecorator());
         dtoClass = posoAnalizer.getDtoInformation().getClassNameForDecorator();
      } else
         dtoClass = posoAnalizer.getDtoInformation().getClassName();

      MethodBuilder createDtoMethod = new MethodBuilder(CREATE_DTO_METHOD_NAME, dtoClass,
            posoAnalizer.getSimpleName() + " poso", "DtoView here", "DtoView referenced");

      /* method body */

      /* create dto and set access restriction */
      createDtoMethod.addBodyComment("create dto and set view");
      createDtoMethod.addBodyLine("final " + dtoClass + " dto = new " + dtoClass + "();");
      createDtoMethod.addBodyLine("dto.setDtoView(here);");
      createDtoMethod.addBodyLine();

      /* prepare set sections */
      List<SetPropertyInDtoSectionCreator> setSections = new ArrayList<SetPropertyInDtoSectionCreator>();
      for (PosoFieldDescriptor field : posoAnalizer.getAllExposedFields()) {
         /* test whether to ignore field */
         if (!field.isExposeValueToClient())
            continue;

         setSections.add(new SetPropertyInDtoSectionCreator(field));
      }

      for (ExposedClientMethod getMethod : posoAnalizer.getExposedGetMethods())
         setSections.add(new SetPropertyInDtoSectionCreator(getMethod));

      for (PosoAnalizer iFaces : posoAnalizer.getImplementedPosos()) {
         for (ExposedClientMethod method : iFaces.getAllExposedInterfaceMethods()) {
            if (method.isGetMethod()) {

               /*
                * make sure that an annotation at a field wins over an annotation at an
                * implementing class
                */
               String methodName = method.getGetMethod();
               boolean found = false;
               for (PosoFieldDescriptor field : posoAnalizer.getAllExposedFields()) {
                  if (methodName.equals(field.getGetMethod())) {
                     found = true;
                     break;
                  }
               }
               if (!found)
                  setSections.add(new SetPropertyInDtoSectionCreator(method));
            }
         }
      }

      /* add set sections for green fields */
      for (DtoView view : DtoView.values())
         addSetSectionForRestrictionLevel(createDtoMethod, setSections, view);

      addPostProcessingMethod(createDtoMethod, "dtoCreated", "poso, dto");

      /* return dto */
      createDtoMethod.addBodyLine();
      createDtoMethod.addBodyLine("return dto;");

      /* method end */
      sourceBuilder.append(createDtoMethod).append("\n");
   }

   private void addSetSectionForRestrictionLevel(MethodBuilder createDtoMethod,
         List<SetPropertyInDtoSectionCreator> setSections, DtoView view) {
      /* prepare set */
      List<SetPropertyInDtoSectionCreator> relevantSetSections = new ArrayList<SetPropertyInDtoSectionCreator>();
      for (SetPropertyInDtoSectionCreator setSection : setSections)
         if (view == setSection.getDtoView())
            relevantSetSections.add(setSection);

      if (relevantSetSections.isEmpty())
         return;

      /* add section */
      createDtoMethod
            .beginBodyBlock("if(here.compareTo(" + view.getClass().getSimpleName() + "." + view.name() + ") >= 0)");
      for (SetPropertyInDtoSectionCreator setSection : relevantSetSections)
         setSection.addSetSection(referenceAccu, createDtoMethod);
      createDtoMethod.endBodyBlock();
   }

   @Override
   protected void addInstantiateDtoMethod(StringBuilder sourceBuilder) {
      /* find the correct dto class */
      String dtoClass;

      if (posoAnalizer.getDtoInformation().hasDecorator()) {
         referenceAccu.add(posoAnalizer.getDtoInformation().getFullyQualifiedClassNameForDecorator());
         dtoClass = posoAnalizer.getDtoInformation().getClassNameForDecorator();
      } else
         dtoClass = posoAnalizer.getDtoInformation().getClassName();

      MethodBuilder method = new MethodBuilder(INSTANTIATE_DTO_METHOD_NAME, dtoClass,
            posoAnalizer.getSimpleName() + " poso");

      method.addBodyLine(dtoClass + " dto = new " + dtoClass + "();");

      addPostProcessingMethod(method, "dtoInstantiated", "poso, dto");

      method.addBodyLine("return dto;");

      /* method end */
      sourceBuilder.append(method).append("\n");
   }

}
