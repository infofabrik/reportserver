package net.datenwerke.dtoservices.dtogenerator.analizer.comparators;

import java.util.Comparator;

import net.datenwerke.dtoservices.dtogenerator.analizer.PosoFieldDescriptor;

public class PosoFieldDescriptorComparator implements Comparator<PosoFieldDescriptor> {

   @Override
   public int compare(PosoFieldDescriptor o1, PosoFieldDescriptor o2) {
      return o1.getName().compareTo(o2.getName());
   }

}
