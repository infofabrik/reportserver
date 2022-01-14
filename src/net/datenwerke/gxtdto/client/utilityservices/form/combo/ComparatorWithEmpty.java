package net.datenwerke.gxtdto.client.utilityservices.form.combo;

import java.util.Comparator;

public class ComparatorWithEmpty implements Comparator<Object> {

   @Override
   public int compare(Object arg0, Object arg1) {
      if (arg0 instanceof EmptyOption)
         return -1;
      if (arg1 instanceof EmptyOption)
         return 1;
      if (null != arg0 && !(arg0 instanceof Comparable))
         throw new IllegalArgumentException("Expect Comparable, got " + arg0.getClass());

      return null == arg0 && null == arg1 ? 0 : null == arg1 ? -1 : ((Comparable) arg0).compareTo(arg1);
   }

}
