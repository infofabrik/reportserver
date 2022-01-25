package net.datenwerke.dtoservices.dtogenerator.dtosourcegenerator.posomap;

import java.util.Collection;

import javax.annotation.processing.AbstractProcessor;

import net.datenwerke.annotationprocessing.utils.SourceFileGeneratorImpl;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;

public class Dto2PosoMapGenerator extends SourceFileGeneratorImpl {

   private final PosoAnalizer posoAnalizer;

   public Dto2PosoMapGenerator(PosoAnalizer posoAnalizer, AbstractProcessor processor) {
      super(processor);

      this.posoAnalizer = posoAnalizer;
   }

   @Override
   public String getPackageName() {
      return posoAnalizer.getDtoInformation().getDto2PosoMapPackageName();
   }

   @Override
   public String getClassName() {
      return posoAnalizer.getDtoInformation().getDto2PosoMapClassName();
   }

   @Override
   protected void addClassBody(StringBuilder sourceBuilder) {

   }

   @Override
   protected void addAnnotations(StringBuilder sourceBuilder) {
      super.addAnnotations(sourceBuilder);
      if (addGeneratedAnnotation())
         sourceBuilder.append("@").append(CorrespondingPoso.class.getSimpleName()).append("(")
               .append(posoAnalizer.getFullyQualifiedClassName()).append(".class)\n");

   }

   @Override
   protected Collection<String> getImplementedInterfaces() {
      Collection<String> ifaces = super.getImplementedInterfaces();

      ifaces.add(Dto2PosoMapper.class.getSimpleName());

      return ifaces;
   }

   @Override
   protected Collection<String> getReferencedClasses() {
      Collection<String> references = super.getReferencedClasses();

      references.add(CorrespondingPoso.class.getName());
      references.add(Dto2PosoMapper.class.getName());

      return references;
   }

}
