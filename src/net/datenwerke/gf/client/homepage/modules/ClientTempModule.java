package net.datenwerke.gf.client.homepage.modules;


import com.google.gwt.event.dom.client.MouseEvent;

import net.datenwerke.gf.client.homepage.ui.DwMainViewport;


/**
 * 
 *
 */
public interface ClientTempModule extends ClientModule {

	public void setViewport(DwMainViewport dwMainViewport);

	public void onMouseOver(MouseEvent be);

}
