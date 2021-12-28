package net.datenwerke.gf.client.treedb.simpleform;

import net.datenwerke.gf.client.treedb.selection.SelectionFilter;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;

public interface SFFCTreeNodeSelectionFilter extends SimpleFormFieldConfiguration {

   public SelectionFilter getFilter();

}
