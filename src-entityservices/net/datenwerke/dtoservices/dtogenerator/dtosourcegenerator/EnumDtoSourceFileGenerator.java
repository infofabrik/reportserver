package net.datenwerke.dtoservices.dtogenerator.dtosourcegenerator;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import net.datenwerke.annotationprocessing.utils.SourceFileGeneratorImpl;
import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.EnumAnalizer;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.annotations.EnumLabel;
import net.datenwerke.dtoservices.dtogenerator.util.SourceFileGenerationUtils;

public class EnumDtoSourceFileGenerator extends SourceFileGeneratorImpl {

   private DtoAnnotationProcessor dtoAnnotationProcessor;
   private PosoAnalizer posoAnalizer;

   /* used to accumulate references */
   private Set<String> referenceAccu = new TreeSet<String>();

   public EnumDtoSourceFileGenerator(PosoAnalizer posoAnalizer, DtoAnnotationProcessor dtoAnnotationProcessor) {
      super(dtoAnnotationProcessor);
      this.posoAnalizer = posoAnalizer;
      this.dtoAnnotationProcessor = dtoAnnotationProcessor;
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
   public String getPackageName() {
      return posoAnalizer.getDtoInformation().getPackageName();
   }

   @Override
   protected void addClassBody(StringBuilder sourceBuilder) {
      boolean first = true;
      for (EnumAnalizer enumAnalizer : posoAnalizer.getEnumConstants()) {
         if (first)
            first = false;
         else
            sourceBuilder.append(",\n");

         sourceBuilder.append("\t").append(enumAnalizer.getSimpleName());

         if (enumAnalizer.hasLabelAnnotation()) {
            EnumLabel label = enumAnalizer.getLabelAnnotation();
            String iFace = SourceFileGenerationUtils.getSimpleTypeName(enumAnalizer.getLabelMsgInterface());

            referenceAccu.add(enumAnalizer.getLabelMsgInterface().toString());
            sourceBuilder.append(" {\n").append("\t\tpublic String toString(){\n").append("\t\t\treturn ").append(iFace)
                  .append(".INSTANCE.").append(label.key()).append("();\n").append("\t\t}\n").append("\t}");

         }

      }

      sourceBuilder.append("\n");
   }

   @Override
   protected ClassType getClassType() {
      return ClassType.Enum;
   }

   @Override
   protected Collection<String> getReferencedClasses() {
      /* references */
      Collection<String> imports = super.getReferencedClasses();

      imports.addAll(referenceAccu);

      return imports;
   }

}
