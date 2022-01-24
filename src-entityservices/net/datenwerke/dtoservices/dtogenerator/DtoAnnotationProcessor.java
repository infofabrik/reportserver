package net.datenwerke.dtoservices.dtogenerator;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.Diagnostic.Kind;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

import net.datenwerke.annotationprocessing.utils.SourceFileGenerator;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.Dto2ClassSourceFileGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.Dto2EnumSourceFileGenerator;
import net.datenwerke.dtoservices.dtogenerator.dtoinformationservice.DtoClientInfoServiceGeneratorFacilitator;
import net.datenwerke.dtoservices.dtogenerator.dtoservicegenerator.DTOServiceGeneratorFacilitator;
import net.datenwerke.dtoservices.dtogenerator.dtosourcegenerator.DecoratorFileGenerator;
import net.datenwerke.dtoservices.dtogenerator.dtosourcegenerator.DtoClassFileGenerator;
import net.datenwerke.dtoservices.dtogenerator.dtosourcegenerator.DtoInterfaceFileGenerator;
import net.datenwerke.dtoservices.dtogenerator.dtosourcegenerator.DtoPropertyAccessGenerator;
import net.datenwerke.dtoservices.dtogenerator.dtosourcegenerator.EnumDtoSourceFileGenerator;
import net.datenwerke.dtoservices.dtogenerator.dtosourcegenerator.posomap.Dto2PosoMapGenerator;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.Class2DtoSourceFileGenerator;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.Enum2DtoSourceFileGenerator;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;

/**
 * 
 *
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto")
@SupportedOptions({ DtoAnnotationProcessor.BUILD_DTOS_OPTION, DtoAnnotationProcessor.DTO_SERVICE_PACKAGE_OPTION,
      DtoAnnotationProcessor.DTO_INFO_SERVICE_PACKAGE_OPTION, DtoAnnotationProcessor.DEBUG_OPTION,
      DtoAnnotationProcessor.DTO_SUPERCLASS_OPTION, DtoAnnotationProcessor.DTO_DISPLAY_STRING_DEFAULT_OPTION,
      DtoAnnotationProcessor.DTO_SERVICE_BASE_NAME_OPTION, DtoAnnotationProcessor.DTO_SERVICE_IS_MAIN_OPTION,
      DtoAnnotationProcessor.DTO_MAIN_SERVICE_PACKAGE, DtoAnnotationProcessor.DTO_MAIN_INFO_SERVICE_PACKAGE })
public class DtoAnnotationProcessor extends AbstractProcessor {

   public static final String DTO_EXTENSION = "Dto";
   public static final String DTO_POSO_MAP_EXTENSION = "Dto2PosoMap";
   public static final String DTO_PROPERTY_ACCESS_EXTENSION = "DtoPA";
   public static final String DTO_DECORATOR_EXTENSION = "DtoDec";

   public static final String DEBUG_OPTION = "debug";
   public static final String BUILD_DTOS_OPTION = "buildDtos";
   public static final String DTO_SERVICE_PACKAGE_OPTION = "dtoServicePackage";
   public static final String DTO_INFO_SERVICE_PACKAGE_OPTION = "dtoInfoServicePackage";
   public static final String DTO_SUPERCLASS_OPTION = "dtoSuperClass";
   public static final String DTO_DISPLAY_STRING_DEFAULT_OPTION = "dtoToDisplayDefault";
   public static final String DTO_SERVICE_BASE_NAME_OPTION = "dtoServiceBaseName";
   public static final String DTO_SERVICE_IS_MAIN_OPTION = "dtoIsMainService";
   public static final String DTO_MAIN_SERVICE_PACKAGE = "dtoMainServicePackage";
   public static final String DTO_MAIN_INFO_SERVICE_PACKAGE = "dtoMainInfoServicePackage";

   public static final String DTO_PROPERTY_PREFIX = "PROPERTY_";

   public static final String name = "DtoAnnotationProcessor";
   public static final String version = "0.1";
   public static final int CUT_OFF_CLOBS_SIZE = 8192;
   public static final String FIELD_MODIFIED_INDICTATOR_POSTFIX = "_m";
   public static final String FIELD_PROPERTY_ACCESSOR_POSTFIX = "_pa";

   private String buildDtosOption;
   private String optionDtoServicePackage;
   private String optionDtoInfoServicePackage;
   private String optionDtoSuperClass;
   private boolean optionDebug = false;

   private String dtoServiceBaseName = "";
   private String dtoMainServicePackage = "";
   private String dtoMainInfoServicePackage = "";
   private boolean isDtoMainserviceOption = false;

   private Map<Element, PosoAnalizer> posos = new HashMap<Element, PosoAnalizer>();

   private DTOServiceGeneratorFacilitator dtoServiceGenerator;
   private DtoClientInfoServiceGeneratorFacilitator dtoInfoServiceGenerator;

   private boolean processedPosos = false;

   /**
    * Initializes the processor.
    * 
    */
   @Override
   public synchronized void init(ProcessingEnvironment processingEnv) {
      super.init(processingEnv);

      /* options */
      Map<String, String> options = processingEnv.getOptions();
      this.buildDtosOption = options.get(BUILD_DTOS_OPTION);
      this.optionDtoServicePackage = options.get(DTO_SERVICE_PACKAGE_OPTION);
      this.optionDtoInfoServicePackage = options.get(DTO_INFO_SERVICE_PACKAGE_OPTION);
      this.optionDtoSuperClass = options.get(DTO_SUPERCLASS_OPTION);
      this.dtoServiceBaseName = options.get(DTO_SERVICE_BASE_NAME_OPTION);
      this.dtoMainServicePackage = options.get(DTO_MAIN_SERVICE_PACKAGE);
      this.dtoMainInfoServicePackage = options.get(DTO_MAIN_INFO_SERVICE_PACKAGE);
      if (null != options.get(DTO_SERVICE_IS_MAIN_OPTION))
         this.isDtoMainserviceOption = options.get(DTO_SERVICE_IS_MAIN_OPTION).equals("true");
      if (null != options.get(DEBUG_OPTION))
         this.optionDebug = options.get(DEBUG_OPTION).equals("true");

      /* initialize objects */
      dtoServiceGenerator = new DTOServiceGeneratorFacilitator(this);
      dtoInfoServiceGenerator = new DtoClientInfoServiceGeneratorFacilitator(this);
      SourceFileGenerationUtils.setAnnotationProcessor(this);

      /* done initializing */
      printInitializationMessage();
   }

   /**
    * Validates the set options and returns an error in case of an error.
    */
   private void validateOptions() {
      if (null == buildDtosOption) {
         processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
               "Property " + BUILD_DTOS_OPTION + " needs to be true or false");
         throw new IllegalStateException();
      }
   }

   /**
    * prints some information about the processor
    */
   private void printInitializationMessage() {
      StringBuilder infoString = new StringBuilder("Initialized ").append(name).append(" version ").append(version)
            .append(";\n");
      infoString.append("------- OPTIONS ---------\n").append(DTO_SERVICE_PACKAGE_OPTION).append(":\t")
            .append(getOptionDtoServicePackage()).append("\n");

      processingEnv.getMessager().printMessage(Kind.NOTE, infoString);
   }

   @Override
   public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
      System.out.println("blub: process: " + annotations);

      /* only do one round of processing */
      if (!roundEnv.processingOver() && !processedPosos && "true".equals(buildDtosOption)) {
         /* validate configuration */
         validateOptions();

         /* loop over posos */
         for (Element element : roundEnv.getElementsAnnotatedWith(GenerateDto.class)) {
            PosoAnalizer posoAnalizer = new PosoAnalizer(this, element);
            posos.put(element, posoAnalizer);

            if (posoAnalizer.isPosoClass())
               debug("Process Class Poso: " + element);
            else if (posoAnalizer.isPosoEnum())
               debug("Process Enum Poso: " + element);
            else if (posoAnalizer.isPosoInterface())
               debug("Process Interface Poso: " + element);
            else
               error("Unknown Poso Type: " + element);
         }

         /* loop over analizers */
         Iterator<PosoAnalizer> it = new ArrayList(posos.values()).iterator();
         while (it.hasNext()) {
            PosoAnalizer posoAnalizer = it.next();

            /* generate dto */
            generateDto(posoAnalizer);

            /* generate dto2posoMap */
            generateDto2PosoMap(posoAnalizer);

            /* generate dtopropertyAccess */
            generateDtoPropertyAccess(posoAnalizer);

            /* generate dto generator (and vice versa) */
            generatePoso2DtoGenerator(posoAnalizer);
            generateDto2PosoGenerator(posoAnalizer);

            /* add poso to dtoservice generator */
            dtoServiceGenerator.addPoso(posoAnalizer);
            dtoInfoServiceGenerator.addPoso(posoAnalizer);
         }

         /* create dtoservice */
         if (null != getOptionDtoServicePackage())
            dtoServiceGenerator.createService();
         if (null != getOptionDtoInfoServicePackage())
            dtoInfoServiceGenerator.createService();

         /* we are done and we do not need another round */
         processedPosos = true;
      }

      /* do not claim annotations */
      return false;
   }

   private void generateDto2PosoGenerator(PosoAnalizer posoAnalizer) {
      if (!posoAnalizer.getDto2PosoInformation().generateGenerator()) {
         processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
               "Dto2PosoGenerator for " + posoAnalizer.getSimpleName() + " not to be generated automatically");
         return;
      }

      SourceFileGenerator generator = null;
      if (posoAnalizer.isPosoClass())
         generator = new Dto2ClassSourceFileGenerator(posoAnalizer, this);
      else if (posoAnalizer.isPosoEnum())
         generator = new Dto2EnumSourceFileGenerator(posoAnalizer, this);
      else {
         debug("Do not generate a dto2poso converter for: " + posoAnalizer.getFullyQualifiedClassName());
         return;
      }

      generateSourceFile(generator);
   }

   private void generatePoso2DtoGenerator(PosoAnalizer posoAnalizer) {
      if (!posoAnalizer.getPoso2DtoInformation().generateGenerator()) {
         processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE,
               "Poso2DtoGenerator for " + posoAnalizer.getSimpleName() + " not to be generated automatically");
         return;
      }

      SourceFileGenerator generator = null;
      if (posoAnalizer.isPosoClass())
         generator = new Class2DtoSourceFileGenerator(posoAnalizer, this);
      else if (posoAnalizer.isPosoEnum())
         generator = new Enum2DtoSourceFileGenerator(posoAnalizer, this);
      else {
         debug("Do not generate a poso2dto converter for: " + posoAnalizer.getFullyQualifiedClassName());
         return;
      }

      generateSourceFile(generator);
   }

   private void generateDto2PosoMap(PosoAnalizer posoAnalizer) {
      debug("Generate DTO2PosoMap for " + posoAnalizer.getSimpleName());

      /* generate base dto */
      SourceFileGenerator generator = new Dto2PosoMapGenerator(posoAnalizer, this);
      generateSourceFile(generator);
   }

   private void generateDtoPropertyAccess(PosoAnalizer posoAnalizer) {
      debug("Generate DTOPropertyAccess for " + posoAnalizer.getSimpleName());

      /* generate base dto */
      if (posoAnalizer.isPosoClass()) {
         SourceFileGenerator generator = new DtoPropertyAccessGenerator(posoAnalizer, this);
         generateSourceFile(generator);
      }
   }

   private void generateDto(PosoAnalizer posoAnalizer) {
      debug("Generate DTO for " + posoAnalizer.getSimpleName());

      if (!posoAnalizer.getDtoInformation().generateDto()) {
         debug("DTO " + posoAnalizer.getSimpleName() + " not to be generated automatically");
         return;
      }

      /* generate base dto */
      SourceFileGenerator generator = null;
      if (posoAnalizer.isPosoClass())
         generator = new DtoClassFileGenerator(posoAnalizer, this);
      else if (posoAnalizer.isPosoInterface())
         generator = new DtoInterfaceFileGenerator(posoAnalizer, this);
      else if (posoAnalizer.isPosoEnum())
         generator = new EnumDtoSourceFileGenerator(posoAnalizer, this);
      generateSourceFile(generator);

      /* generate decorator */
      if (posoAnalizer.getDtoInformation().hasDecorator() && !decoratorExists(posoAnalizer)) {
         System.out.println("Generate Decorator for " + posoAnalizer.getSimpleName());
         SourceFileGenerator decoratorGen = new DecoratorFileGenerator(posoAnalizer, this);
         generateSourceFile(decoratorGen);
      }
   }

   private boolean decoratorExists(PosoAnalizer posoAnalizer) {
      try {
         FileObject file = processingEnv.getFiler().getResource(StandardLocation.SOURCE_OUTPUT,
               posoAnalizer.getDtoInformation().getPackageNameForDecorator(),
               posoAnalizer.getDtoInformation().getClassNameForDecorator() + ".java");
         file.getCharContent(true);
         debug("Decorator for " + posoAnalizer.getSimpleName() + " already created.");
         return true;
      } catch (IOException e) {
      }

      return false;
   }

   public void generateSourceFile(SourceFileGenerator generator) {
      generator.generateSource();

      try {
         /* create source file */
         debug("Generate sourcefile for " + generator.getFullyQualifiedClassName());
         JavaFileObject jfo = processingEnv.getFiler().createSourceFile(generator.getFullyQualifiedClassName());
         Writer writer = new OutputStreamWriter(jfo.openOutputStream(), "UTF-8");

         /* write file and close it */
         writer.write(generator.getSource());
         writer.close();
      } catch (IOException e) {
         processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
               "Could not create source file: " + e.getMessage());

         RuntimeException re = new RuntimeException();
         re.initCause(e);
         throw re;
      }
   }

   public void debug(Object o) {
      if (optionDebug)
         System.out.println("Debug: " + o.toString());
   }

   public void warning(Object o) {
      System.out.println("Warning: " + o.toString());
   }

   public void error(String msg) {
      processingEnv.getMessager().printMessage(Kind.ERROR, msg);
   }

   public String getOptionDtoServicePackage() {
      return optionDtoServicePackage;
   }

   public String getOptionDtoSuperClass() {
      return optionDtoSuperClass;
   }

   public boolean isDebug() {
      return optionDebug;
   }

   public ProcessingEnvironment getProcessingEnvironment() {
      return processingEnv;
   }

   public JavaFileObject createSourceFile(String fullyQualifiedClassName) throws IOException {
      return processingEnv.getFiler().createSourceFile(fullyQualifiedClassName, (Element[]) null);
   }

   public Collection<PosoAnalizer> getAnalyzedPosos() {
      return posos.values();
   }

   public PosoAnalizer getPosoAnalizerFor(Element element) {
      if (null == element)
         return null;
      if (!posos.containsKey(element)) {
         System.out.println(element.toString());
         return new PosoAnalizer(this, processingEnv.getElementUtils().getTypeElement(element.toString()));
      }

      return posos.get(element);
   }

   public String getOptionDtoInfoServicePackage() {
      return optionDtoInfoServicePackage;
   }

   public boolean isDtoMainserviceOption() {
      return isDtoMainserviceOption;
   }

   public String getDtoServiceBaseName() {
      return dtoServiceBaseName;
   }

   public String getDtoMainServicePackage() {
      return dtoMainServicePackage;
   }

   public String getDtoMainInfoServicePackage() {
      return dtoMainInfoServicePackage;
   }

}
