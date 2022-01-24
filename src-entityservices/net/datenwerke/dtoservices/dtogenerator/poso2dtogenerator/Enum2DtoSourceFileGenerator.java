package net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator;

import net.datenwerke.annotationprocessing.utils.MethodBuilder;
import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.DtoInformation;
import net.datenwerke.dtoservices.dtogenerator.analizer.EnumAnalizer;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;

/**
 * 
 *
 */
public class Enum2DtoSourceFileGenerator extends Poso2DtoSourceFileGenerator {

   public Enum2DtoSourceFileGenerator(PosoAnalizer posoAnalizer, DtoAnnotationProcessor dtoAnnotationProcessor) {
      super(posoAnalizer, dtoAnnotationProcessor);
   }

   @Override
   protected void addCreateDtoMethod(StringBuilder sourceBuilder) {
      DtoInformation dtoInfo = posoAnalizer.getDtoInformation();

      MethodBuilder createDtoMethod = new MethodBuilder(CREATE_DTO_METHOD_NAME, dtoInfo.getClassName(),
            posoAnalizer.getSimpleName() + " poso", "DtoView here", "DtoView referenced");

      createDtoMethod.beginBodyBlock("switch(poso)");

      for (EnumAnalizer enumConstant : posoAnalizer.getEnumConstants()) {
         createDtoMethod.addBodyLine("case " + enumConstant.getSimpleName() + ":");
         createDtoMethod.addBodyLine(
               "return " + posoAnalizer.getDtoInformation().getClassName() + "." + enumConstant.getSimpleName() + ";",
               1);
      }

      createDtoMethod.endBodyBlock();

      referenceAccu.add(IllegalArgumentException.class.getName());
      createDtoMethod.addBodyLine("throw new IllegalArgumentException(\"unknown enum type for: \" + poso);");

      /* method end */
      sourceBuilder.append(createDtoMethod).append("\n");
   }

   @Override
   protected void addInstantiateDtoMethod(StringBuilder sourceBuilder) {
      DtoInformation dtoInfo = posoAnalizer.getDtoInformation();

      MethodBuilder method = new MethodBuilder(INSTANTIATE_DTO_METHOD_NAME, dtoInfo.getClassName(),
            posoAnalizer.getSimpleName() + " poso");

      method.addBodyComment("Simply return the first enum!");

      EnumAnalizer enumConstant = posoAnalizer.getEnumConstants().get(0);

      method.addBodyLine(dtoInfo.getClassName() + " dto = " + posoAnalizer.getDtoInformation().getClassName() + "."
            + enumConstant.getSimpleName() + ";");
      addPostProcessingMethod(method, "dtoInstantiated", "poso, dto");
      method.addBodyLine("return dto;");

      /* method end */
      sourceBuilder.append(method).append("\n");
   }

}
