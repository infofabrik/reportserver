package net.datenwerke.dtoservices.dtogenerator.dtosourcegenerator;

import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;

public class DecoratorFileGenerator extends DtoClassFileGenerator {

   public DecoratorFileGenerator(PosoAnalizer posoAnalizer, DtoAnnotationProcessor dtoAnnotationProcessor) {
      super(posoAnalizer, dtoAnnotationProcessor);
   }

   @Override
   public String getPackageName() {
      return posoAnalizer.getDtoInformation().getPackageNameForDecorator();
   }

   @Override
   public String getClassName() {
      return posoAnalizer.getDtoInformation().getClassNameForDecorator();
   }

   @Override
   public String getFullyQualifiedClassName() {
      return posoAnalizer.getDtoInformation().getFullyQualifiedClassNameForDecorator();
   }

   @Override
   protected boolean addGeneratedAnnotation() {
      return false;
   }

   @Override
   protected void addClassComment(StringBuilder sourceBuilder) {
      referenceAccu.add(posoAnalizer.getFullyQualifiedClassName());

      sourceBuilder.append("\n/**\n").append(" * Dto Decorator for {@link ")
            .append(posoAnalizer.getDtoInformation().getClassName()).append("}\n").append(" *\n").append(" */\n");
   }

   @Override
   protected void addClassBody(StringBuilder sourceBuilder) {
      sourceBuilder.append("\n\tprivate static final long serialVersionUID = 1L;\n");

      addConstructors(sourceBuilder);
   }

   @Override
   protected String getExtendedClass() {
      referenceAccu.add(posoAnalizer.getDtoInformation().getFullyQualifiedClassName());
      return posoAnalizer.getDtoInformation().getClassName();
   }

   @Override
   protected boolean isAbstract() {
      return posoAnalizer.getDtoInformation().isAbstractDto();
   }

}
