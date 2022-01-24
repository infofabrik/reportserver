package net.datenwerke.dtoservices.dtogenerator.dtosourcegenerator;

import java.util.Collection;
import java.util.TreeSet;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;

import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.ExposedClientMethod;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.analizer.TypeAnalizer;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;

/**
 * 
 *
 */
public class DtoInterfaceFileGenerator extends DtoCreator {

   public static final String ID_FIELD_NAME = "dtoId";

   public DtoInterfaceFileGenerator(PosoAnalizer posoAnalizer, DtoAnnotationProcessor dtoAnnotationProcessor) {
      super(posoAnalizer, dtoAnnotationProcessor);
   }

   protected void addMethods(StringBuilder sourceBuilder) {
      /* add methods */
      for (ExposedClientMethod getMethod : posoAnalizer.getExposedInterfaceMethods()) {
         addInterfaceMethods(sourceBuilder, getMethod);
      }
   }

   /**
    * 
    * @param sourceBuilder
    * @param exposedMethod Either a get or set method
    */
   protected void addInterfaceMethods(StringBuilder sourceBuilder, ExposedClientMethod exposedMethod) {
      /* prepare return type */
      String returnType = exposedMethod.getReturnTypeAnalizer().getKnownDtoType(referenceAccu);

      /* create method */
      sourceBuilder.append("\tpublic " + returnType + " " + exposedMethod.getSimpleName() + "(");

      boolean first = true;
      for (VariableElement parameter : exposedMethod.getParameters()) {
         String parameterName = parameter.getSimpleName().toString();
         String type = new TypeAnalizer(dtoAnnotationProcessor, parameter.asType())
               .getCorrectParameterType(referenceAccu);

         if (first)
            first = false;
         else
            sourceBuilder.append(", ");

         sourceBuilder.append(type + " " + parameterName);
      }

      sourceBuilder.append(");").append("\n\n");
   }

   @Override
   protected void addClassComment(StringBuilder sourceBuilder) {
      referenceAccu.add(posoAnalizer.getFullyQualifiedClassName());

      sourceBuilder.append("\n/**\n").append(" * Dto for {@link ").append(posoAnalizer.getSimpleName()).append("}\n")
            .append(" *\n").append(" * This file was automatically created by ").append(DtoAnnotationProcessor.name)
            .append(", version ").append(DtoAnnotationProcessor.version).append("\n").append(" */\n");
   }

   @Override
   public String getPackageName() {
      return posoAnalizer.getDtoInformation().getPackageName();
   }

   @Override
   public String getClassName() {
      return posoAnalizer.getDtoInformation().getClassName();
   }

   @Override
   public String getFullyQualifiedClassName() {
      return posoAnalizer.getDtoInformation().getFullyQualifiedClassName();
   }

   @Override
   protected void addClassBody(StringBuilder sourceBuilder) {
      addMethods(sourceBuilder);
   }

   @Override
   protected String getExtendedClass() {
      /* test for specific extend */
      AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();
      for (ExecutableElement key : gdAnno.getElementValues().keySet()) {
         if (key.toString().equals("dtoExtends()")) {
            AnnotationValue value = gdAnno.getElementValues().get(key);
            Object toExtend = value.getValue();
            if (!(toExtend instanceof DeclaredType))
               throw new IllegalStateException(toExtend + " is not of type DeclaredType but a " + toExtend.getClass());

            DeclaredType type = (DeclaredType) toExtend;

            referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments(type));
            return SourceFileGenerationUtils.getSimpleNameWithoutTypeArguments(type);
         }
      }

      /* use poso */
      if (posoAnalizer.extendsPoso()) {
         PosoAnalizer parentAnalizer = dtoAnnotationProcessor.getPosoAnalizerFor(posoAnalizer.getParentPoso());

         /* decorator */
         if (parentAnalizer.getDtoInformation().hasDecorator()) {
            referenceAccu.add(parentAnalizer.getDtoInformation().getFullyQualifiedClassNameForDecorator());
            return parentAnalizer.getDtoInformation().getClassNameForDecorator();
         }

         /* plain old server object */
         referenceAccu.add(parentAnalizer.getDtoInformation().getFullyQualifiedClassName());
         return parentAnalizer.getDtoInformation().getClassName();
      }

      return null;
   }

   @Override
   protected Collection<String> getReferencedClasses() {
      /* references */
      Collection<String> imports = super.getReferencedClasses();

      imports.addAll(referenceAccu);

      return imports;
   }

   @Override
   protected Collection<String> getImplementedInterfaces() {
      Collection<String> interfaces = new TreeSet<String>();

      /* additional interfaces */
      AnnotationMirror gdAnno = posoAnalizer.getGenerateDtoAnnotationMirror();
      for (ExecutableElement key : gdAnno.getElementValues().keySet()) {
         if (key.toString().equals("dtoImplementInterfaces()")) {
            AnnotationValue value = gdAnno.getElementValues().get(key);
            Object toImplement = value.getValue();

            if (toImplement instanceof Collection) {
               for (AnnotationValue attr : (Collection<AnnotationValue>) toImplement) {
                  dtoAnnotationProcessor.debug("Interface: " + attr.getValue());
                  if (!(attr.getValue() instanceof DeclaredType))
                     continue;
                  DeclaredType type = (DeclaredType) attr.getValue();

                  referenceAccu.add(SourceFileGenerationUtils.getQualifiedNameWithoutTypeArguments(type));
                  interfaces.add(SourceFileGenerationUtils.getSimpleNameWithoutTypeArguments(type));
               }
            } else
               throw new IllegalStateException(
                     "I do not recognize the interface: " + toImplement + " - " + toImplement.getClass().getName());

            break;
         }
      }

      return interfaces;
   }

   @Override
   protected boolean isAbstract() {
      return false;
   }

   @Override
   protected boolean isInterface() {
      return true;
   }

}
