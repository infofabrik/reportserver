package net.datenwerke.gxtdto.client.ui.helper.nav;

import com.google.gwt.dom.client.Element;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;

import net.datenwerke.rs.theme.client.icon.CssIconContainer;

public class CssIconNavModelData<M> extends NavigationModelData<M> implements CssIconContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CssIconNavModelData(int id, String name, ImageResource icon,
			M component) {
		super(id, name, icon, component);
	}

	public CssIconNavModelData(String name, ImageResource icon, M component) {
		super(name, icon, component);
	}

	public CssIconNavModelData(String name, M component) {
		super(name, component);
	}

	@Override
	public SafeHtml getCssIcon() {
		return null;
	}
	
	@Override
	public SafeHtml getCssIcon(int size) {
		return null;
	}
	
	@Override
	public Element getCssElement() {
		return null;
	}

	
}
