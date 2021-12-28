package net.datenwerke.rs.theme.client.fieldset;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.core.client.GXT;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.theme.neptune.client.base.fieldset.Css3FieldSetAppearance;
import com.sencha.gxt.widget.core.client.button.IconButton;
import com.sencha.gxt.widget.core.client.button.IconButton.IconConfig;
import com.sencha.gxt.widget.core.client.button.ToolButton;

public class RsFieldSetAppearance extends Css3FieldSetAppearance {

	public interface Template extends XTemplates {
		@XTemplate(source = "RsFieldSet.html")
		SafeHtml render(FieldSetStyle style, boolean isGecko);
	}

	private Css3FieldSetStyle style;
	private Template template;

	public RsFieldSetAppearance() {
		this(GWT.<Css3FieldSetResources>create(Css3FieldSetResources.class));
	}

	public RsFieldSetAppearance(Css3FieldSetResources resources) {
		super(resources);
		
		this.style = resources.css();
		   
	    this.template = GWT.create(Template.class);
	}

	@Override
	public void render(SafeHtmlBuilder sb) {
		sb.append(template.render(style, GXT.isGecko()));
	}
	
	 @Override
	  public IconConfig collapseIcon() {
	    return ToolButton.COLLAPSE;
	  }

	  @Override
	  public IconButton.IconConfig expandIcon() {
	    return ToolButton.EXPAND;
	  }
}
