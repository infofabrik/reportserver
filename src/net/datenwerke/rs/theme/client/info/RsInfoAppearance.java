package net.datenwerke.rs.theme.client.info;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.dom.XElement;
import com.sencha.gxt.widget.core.client.info.Info.InfoAppearance;

public class RsInfoAppearance implements InfoAppearance {

	interface Template extends XTemplates {
		@XTemplate("<div class='rs-info-w'><div class='rs-info'></div></div>")
		SafeHtml render();
	}

	private final Template template;

	public RsInfoAppearance() {
		this.template = GWT.create(Template.class);
	}

	@Override
	public XElement getContentElement(XElement parent) {
		return parent.getFirstChildElement().getFirstChildElement().cast();
	}

	@Override
	public void render(SafeHtmlBuilder sb) {
		sb.append(template.render());
	}

}
