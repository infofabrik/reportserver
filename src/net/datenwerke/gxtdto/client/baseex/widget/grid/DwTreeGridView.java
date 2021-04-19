package net.datenwerke.gxtdto.client.baseex.widget.grid;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.widget.core.client.tree.Tree.TreeNode;
import com.sencha.gxt.widget.core.client.treegrid.TreeGridView;

import net.datenwerke.rs.theme.client.icon.CssIconImageResource;

public class DwTreeGridView<M> extends TreeGridView<M> {

	@Override
	public void onIconStyleChange(TreeNode<M> node, ImageResource icon) {
		if(! (icon instanceof CssIconImageResource))
			super.onIconStyleChange(node, icon);
		else {
			Element iconEl = getIconElement(node);
			if (iconEl != null) {
				Element e = ((CssIconImageResource)icon).getCssElement();
				e.addClassName(iconEl.getClassName());
				node.setIconElement((Element) iconEl.getParentElement().insertBefore(e, iconEl));
				iconEl.removeFromParent();
			}
		}
	}


}
