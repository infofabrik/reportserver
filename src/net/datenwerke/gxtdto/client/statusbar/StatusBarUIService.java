package net.datenwerke.gxtdto.client.statusbar;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.Viewport;

public interface StatusBarUIService {

	public Widget getStatusBarWidget();
	
	void addRight(String text, String icon);

	void addLeft(String text, String icon);

	void addRight(Widget comp);

	void addLeft(Widget comp);

	void removeRight(Widget comp);
	void removeLeft(Widget comp);

	public void setContainer(Viewport container);

	public void clearLeft();
	public void clearRight();
}
