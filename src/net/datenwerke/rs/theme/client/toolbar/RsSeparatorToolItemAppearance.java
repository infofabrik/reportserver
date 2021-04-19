package net.datenwerke.rs.theme.client.toolbar;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem.SeparatorToolItemAppearance;


public class RsSeparatorToolItemAppearance implements SeparatorToolItemAppearance {

	@Override
	public void render(SafeHtmlBuilder sb) {
		sb.appendHtmlConstant("<span><div class=\"rs-tbar-sep\"></div></span>");
	}

}
