package net.datenwerke.rs.theme.client.icon;

import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtml;

public interface CssIconContainer {

	SafeHtml getCssIcon();
	
	Element getCssElement();

	SafeHtml getCssIcon(int size);
}
