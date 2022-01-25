package net.datenwerke.dtoservices.dtogenerator.dtosourcegenerator;

import java.util.Set;
import java.util.TreeSet;

import net.datenwerke.annotationprocessing.utils.SourceFileGeneratorImpl;
import net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor;
import net.datenwerke.dtoservices.dtogenerator.analizer.PosoAnalizer;

public abstract class DtoCreator extends SourceFileGeneratorImpl {

   public static final String ID_FIELD_NAME = "dtoId";
   public static final String PROPERTY_ACCESS_METHOD = "instantiatePropertyAccess";

   protected DtoAnnotationProcessor dtoAnnotationProcessor;
   protected PosoAnalizer posoAnalizer;
   protected Set<String> referenceAccu = new TreeSet<String>();

   public DtoCreator(PosoAnalizer posoAnalizer, DtoAnnotationProcessor dtoAnnotationProcessor) {
      super(dtoAnnotationProcessor);
      this.posoAnalizer = posoAnalizer;
      this.dtoAnnotationProcessor = dtoAnnotationProcessor;
   }

}