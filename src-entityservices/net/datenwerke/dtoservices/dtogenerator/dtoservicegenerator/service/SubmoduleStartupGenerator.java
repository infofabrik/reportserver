package net.datenwerke.dtoservices.dtogenerator.dtoservicegenerator.service;

import java.util.Collection;
import java.util.Collections;

import com.google.inject.Inject;

import net.datenwerke.annotationprocessing.utils.SourceFileGeneratorImpl;
import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.dtoservicegenerator.DTOServiceGeneratorFacilitator;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;

public class SubmoduleStartupGenerator extends SourceFileGeneratorImpl {

   private DtoAnnotationProcessor dtoAnnotationProcessor;

   public SubmoduleStartupGenerator(DtoAnnotationProcessor dtoAnnotationProcessor) {
      super(dtoAnnotationProcessor);
      this.dtoAnnotationProcessor = dtoAnnotationProcessor;
   }

   @Override
   protected void addClassBody(StringBuilder sourceBuilder) {
      /* constructor */
      sourceBuilder.append("\t@Inject\n");
      sourceBuilder.append("\tpublic " + dtoAnnotationProcessor.getDtoServiceBaseName()
            + DTOServiceGeneratorFacilitator.SUB_MODULE_STARTUP + "(\n");

      sourceBuilder.append("\t\t" + DTOServiceGeneratorFacilitator.SERVICE_INTERFACE_NAME + " mainService,\n");
      sourceBuilder.append("\t\t" + dtoAnnotationProcessor.getDtoServiceBaseName()
            + DTOServiceGeneratorFacilitator.SERVICE_IMPLEMENTATION_NAME + " subService\n");

      sourceBuilder.append("\t){\n");

      sourceBuilder.append("\t\t((" + DTOServiceGeneratorFacilitator.SERVICE_MAIN_INTERFACE_NAME + ")mainService)."
            + MainInterfaceGenerator.ADD_SUB_MODULE_METHOD + "(subService);\n");

      sourceBuilder.append("\t}\n");
   }

   @Override
   protected String getExtendedClass() {
      return null;
   }

   @Override
   protected Collection<String> getImplementedInterfaces() {
      return Collections.emptySet();
   }

   @Override
   protected boolean isInterface() {
      return false;
   }

   @Override
   protected Collection<String> getReferencedClasses() {
      Collection<String> references = super.getReferencedClasses();

      references.add(Inject.class.getName());
      references.add(DtoMainService.class.getName());
      references.add(DtoService.class.getName());

      return references;
   }

   public String getClassName() {
      return dtoAnnotationProcessor.getDtoServiceBaseName() + DTOServiceGeneratorFacilitator.SUB_MODULE_STARTUP;
   }

   public String getPackageName() {
      return dtoAnnotationProcessor.getOptionDtoServicePackage();
   }

}
