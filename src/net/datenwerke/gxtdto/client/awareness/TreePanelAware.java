package net.datenwerke.gxtdto.client.awareness;

import com.sencha.gxt.widget.core.client.tree.Tree;

public interface TreePanelAware<X> {

   public void makeAwareOfTreePanel(Tree<X, String> panel);
}
