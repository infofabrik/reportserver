package net.datenwerke.dtoservices.dtogenerator.analizer.comparators;

import java.util.Comparator;

import javax.lang.model.element.TypeElement;

public class TypeElementComparator implements Comparator<TypeElement> {

   @Override
   public int compare(TypeElement o1, TypeElement o2) {
      return o1.getQualifiedName().toString().compareTo(o2.getQualifiedName().toString());
   }

}
