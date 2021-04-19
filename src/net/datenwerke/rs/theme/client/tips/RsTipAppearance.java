package net.datenwerke.rs.theme.client.tips;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.theme.neptune.client.base.tips.Css3TipAppearance;

public class RsTipAppearance extends Css3TipAppearance {

	public interface Css3TipTemplate extends XTemplates {
		@XTemplate("<div class='{style.tipWrap} rs-tip-w'><div class='{style.tip} rs-tip'>" +
				"<div class='{style.tools} rs-tip-t'></div>" +
				"<div class='{style.headingWrap} rs-tip-h'><span class='{style.heading}'></span></div>" +
				"<div class='{style.textWrap} rs-tip-t'><span class='{style.text}'></span></div>" +
				"</div></div>")
		SafeHtml render(Css3TipStyle style);
	}

	private final Css3TipResources resources;
	private final Css3TipStyle style;
	private final Css3TipTemplate template = GWT.create(Css3TipTemplate.class);

	public RsTipAppearance() {
		this(GWT.<Css3TipResources>create(Css3TipResources.class));
	}

	public RsTipAppearance(Css3TipResources resources) {
		super(resources);
		this.resources = resources;
		style = resources.style();
		style.ensureInjected();
	}

	@Override
	public void render(SafeHtmlBuilder sb) {
		sb.append(template.render(style));
	}
}
