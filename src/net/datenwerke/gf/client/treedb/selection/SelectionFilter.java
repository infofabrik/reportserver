package net.datenwerke.gf.client.treedb.selection;

import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public interface SelectionFilter {

   public static SelectionFilter DUMMY_FILTER = new SelectionFilter() {

      @Override
      public String allowSelectionOf(AbstractNodeDto node) {
         return null;
      }

   };

   public String allowSelectionOf(AbstractNodeDto node);
}
