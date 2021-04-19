package net.datenwerke.gf.client.treedb.simpleform;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;

public interface SFFCGenericTreeNode extends SimpleFormFieldConfiguration {

	public UITree getTreeForPopup();
}
