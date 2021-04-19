package net.datenwerke.rs.theme.client.tools;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.theme.neptune.client.base.button.Css3IconButtonAppearance;

public class RsIconButtonAppearance extends Css3IconButtonAppearance {


	public RsIconButtonAppearance() {
		super();
	}

	public RsIconButtonAppearance(Css3ToolButtonResources resources) {
		super(resources);
	}

	@Override
	public void render(SafeHtmlBuilder sb) {
		sb.appendHtmlConstant("<i></i>");
	}
}
