package net.datenwerke.gxtdto.client.baseex.widget.menu;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.widget.core.client.menu.MenuItem;


public class DwMenuItem extends MenuItem {

	public DwMenuItem() {
		super();
	}
	
	public DwMenuItem(String label) {
		super(label);
	}
	
	public DwMenuItem(String label, ImageResource icon) {
		super(label, icon);
	}

	public DwMenuItem(String label,
			SelectionHandler<MenuItem> selectionHandler) {
		super(label, selectionHandler);
	}
	
	public DwMenuItem(String label, BaseIcon icon) {
		this(label, icon.toImageResource());
	}
	
	public void setIcon(BaseIcon icon) {
		setIcon(icon.toImageResource());
	}

	

	

	
	

	
}
