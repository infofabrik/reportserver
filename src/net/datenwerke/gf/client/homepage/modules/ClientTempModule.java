package net.datenwerke.gf.client.homepage.modules;


import net.datenwerke.gf.client.homepage.ui.DwMainViewport;

import com.google.gwt.event.dom.client.MouseEvent;


/**
 * 
 *
 */
public interface ClientTempModule extends ClientModule {

	public void setViewport(DwMainViewport dwMainViewport);

	public void onMouseOver(MouseEvent be);

}
