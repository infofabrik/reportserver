package net.datenwerke.dtoservices.dtogenerator.analizer.comparators;

import java.util.Comparator;

import net.datenwerke.dtoservices.dtogenerator.analizer.ExposedClientMethod;

public class ExposedClientMethodComparator implements Comparator<ExposedClientMethod> {

   @Override
   public int compare(ExposedClientMethod o1, ExposedClientMethod o2) {
      return o1.getSimpleName().compareTo(o2.getSimpleName());
   }

}
