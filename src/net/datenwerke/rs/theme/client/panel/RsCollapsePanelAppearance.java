package net.datenwerke.rs.theme.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.Style.LayoutRegion;
import com.sencha.gxt.core.client.resources.StyleInjectorHelper;
import com.sencha.gxt.theme.neptune.client.base.widget.Css3CollapsePanelAppearance;

public class RsCollapsePanelAppearance extends Css3CollapsePanelAppearance {

	private Css3CollapsePanelResources resources;
	private Css3CollapsePanelStyle style;


	public RsCollapsePanelAppearance() {
		this(GWT.<Css3CollapsePanelResources>create(Css3CollapsePanelResources.class));
	}

	public RsCollapsePanelAppearance(Css3CollapsePanelResources resources) {
		super();
		this.resources = resources;
		this.style = this.resources.style();

		StyleInjectorHelper.ensureInjected(style, true);
	}


	@Override
	public void render(SafeHtmlBuilder sb, LayoutRegion region, boolean header) {
		String cls = style.panel();

		switch (region) {
		case WEST:
			cls += " " + style.west();
			break;
		case EAST:
			cls += " " + style.east();
			break;
		case NORTH:
			cls += " " + style.north();
			break;
		case SOUTH:
			cls += " " + style.south();
			break;
		}
		
		if (!header) {
			cls += " " + style.noHeader();
		}


		sb.appendHtmlConstant("<div class='" + cls + " rs-cp-collapse'>");
		sb.appendHtmlConstant("<div class='" + style.iconWrap() + "'></div>");
		sb.appendHtmlConstant("<div class='" + style.textWrap() + "'></div>");
		sb.appendHtmlConstant("</div>");
	}

}
