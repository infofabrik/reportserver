package net.datenwerke.rs.theme.client.info;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig.DefaultInfoConfigAppearance;

public class RsDefaultInfoConfigAppearance implements DefaultInfoConfigAppearance {

	  interface Template extends XTemplates {
	    @XTemplate("<div class='rs-info-title'>{title}</div><div class='rs-info-msg'>{message}</div>")
	    SafeHtml render(SafeHtml title, SafeHtml message);
	  }

	  private Template template;

	  public RsDefaultInfoConfigAppearance() {
	    template = GWT.create(Template.class);
	  }

	  @Override
	  public void render(SafeHtmlBuilder sb, SafeHtml title, SafeHtml message) {
	    sb.append(template.render(title, message));
	  }
}
