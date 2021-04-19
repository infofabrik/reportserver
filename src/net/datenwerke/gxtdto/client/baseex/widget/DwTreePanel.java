package net.datenwerke.gxtdto.client.baseex.widget;

import net.datenwerke.gxtdto.client.theme.CssClassConstant;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.theme.client.icon.CssIconContainer;
import net.datenwerke.rs.theme.client.icon.CssIconImageResource;
import net.datenwerke.rs.theme.client.tree.RsTreeAppearance;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.DomEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.tree.Tree;
import com.sencha.gxt.widget.core.client.tree.TreeStyle;
import com.sencha.gxt.widget.core.client.tree.TreeView;

public class DwTreePanel<M> extends Tree<M, String> {
	
	@CssClassConstant
	public static final String CSS_NAME = "rs-t";
	
	public static class DwTreeStyle extends TreeStyle {
		
		@Override
		public ImageResource getJointCloseIcon() {
			return new CssIconImageResource(BaseIcon.CARET_RIGHT);
		}

		@Override
		public ImageResource getJointOpenIcon() {
			return new CssIconImageResource(BaseIcon.CARET_DOWN);
		}

		@Override
		public ImageResource getLeafIcon() {
			return super.getLeafIcon();
		}

		@Override
		public ImageResource getNodeCloseIcon() {
			return new CssIconImageResource(BaseIcon.FOLDER_O);
		}

		@Override
		public ImageResource getNodeOpenIcon() {
			return new CssIconImageResource(BaseIcon.FOLDER_OPEN_O);
		}
	}
	
	class DwTreeView<Y> extends TreeView<Y> {
		@Override //identical to get to getImage
		public void onIconStyleChange(TreeNode<Y> node, ImageResource icon) {
			Element iconEl = getIconElement(node);
			if (iconEl != null) {
				Element e;
				if (icon != null) {
					e = getImage(icon);
				} else {
					e = DOM.createSpan();
				}
				// do not override class .. do it properly
				// e.setClassName(iconEl.getClassName());
				e.addClassName(((RsTreeAppearance)getAppearance()).getIconStyle());

				node.setIconElement((Element) getElement(node).getFirstChild().insertBefore(e, iconEl));
				iconEl.removeFromParent();
			}
		}
		
		protected Element getImage(ImageResource ir) {
			if(ir instanceof CssIconContainer){
				return ((CssIconContainer)ir).getCssElement();
			}
			return AbstractImagePrototype.create(ir).createElement();
		}
	}
	
	public DwTreePanel(TreeStore<M> store, ValueProvider<? super M, String> vp) {
		super(store, vp);
		init();
	}

	public DwTreePanel(TreeStore<M> store,
			ValueProvider<? super M, String> valueProvider,
			RsTreeAppearance app) {
		super(store, valueProvider, app);
		init();
	}
	
	private void init() {
		setStyle(new DwTreeStyle());
		setView(new DwTreeView<M>());
		
		getElement().addClassName(getCssName());
	}

	public String getCssName() {
		return CSS_NAME;
	}
	
	public void addClassName(String name) {
		getElement().addClassName(name);
	}
	
	public M getEventTargetNode(DomEvent<?> e){
		com.sencha.gxt.widget.core.client.tree.Tree.TreeNode<M> node = findNode(e.getNativeEvent().getEventTarget().<Element> cast());
	    if(null != node)
	    	return (M) node.getModel();
    	return null;
	}
	
	
	
}
