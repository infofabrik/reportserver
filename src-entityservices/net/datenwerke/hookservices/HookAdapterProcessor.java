package net.datenwerke.hookservices;

import java.io.IOException;
import java.io.Writer;
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
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

import net.datenwerke.annotationprocessing.utils.SourceFileGenerator;
import net.datenwerke.annotationprocessing.utils.SourceFileGeneratorImpl;
import net.datenwerke.hookservices.annotations.HookConfig;

/**
 * 
 *
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({ "javax.persistence.Entity", "javax.persistence.MappedSuperclass" })
@SupportedOptions({ HookAdapterProcessor.BUILD_HOOK_ADAPTORS })
public class HookAdapterProcessor extends AbstractProcessor {

   public static final String BUILD_HOOK_ADAPTORS = "buildHookAdapters";

   public static final String name = "HookAdaptorAnnotationProcessor";
   public static final String version = "0.1";

   private String buildAdaptersOption;
   private boolean processed = false;

   /**
    * Initializes the processor.
    * 
    */
   @Override
   public synchronized void init(ProcessingEnvironment processingEnv) {
      super.init(processingEnv);

      /* options */
      Map<String, String> options = processingEnv.getOptions();
      this.buildAdaptersOption = options.get(BUILD_HOOK_ADAPTORS);

      System.out.println("Build Hook Adapters: " + (buildAdaptersOption == null ? "false" : buildAdaptersOption));

   }

   @Override
   public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
      /* only do one round of processing */
      if (!roundEnv.processingOver() && !processed && "true".equals(buildAdaptersOption)) {
         /* loop over posos */
         for (Element element : roundEnv.getElementsAnnotatedWith(HookConfig.class)) {
            SourceFileGeneratorImpl generator = new FileGenerator(this, element);

            if (!decoratorExists(generator))
               generateSourceFile(generator);
         }

         /* we are done and we do not need another round */
         processed = true;
      }

      /* do not claim annotations */
      return false;
   }

   public ProcessingEnvironment getProcessingEnvironment() {
      return processingEnv;
   }

   private boolean decoratorExists(SourceFileGeneratorImpl generator) {
      try {
         FileObject file = processingEnv.getFiler().getResource(StandardLocation.SOURCE_OUTPUT,
               generator.getPackageName(), generator.getClassName() + ".java");
         file.getCharContent(true);
         return true;
      } catch (IOException e) {
      }

      return false;
   }

   public void generateSourceFile(SourceFileGenerator generator) {
      generator.generateSource();

      try {
         System.out.println("adapter for: " + generator.getClassName());
         /* create source file */
         JavaFileObject jfo = processingEnv.getFiler().createSourceFile(generator.getFullyQualifiedClassName());
         Writer writer = jfo.openWriter();

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

}
