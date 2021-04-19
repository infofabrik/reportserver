package net.datenwerke.gf.client.administration.interfaces;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;


public interface AdminModule {

	public String getNavigationText();

	public ImageResource getNavigationIcon();
	
	public Widget getMainWidget();

	public void notifyOfSelection();
}
