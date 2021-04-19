package net.datenwerke.rs.theme.client.panel;

import com.sencha.gxt.theme.neptune.client.base.panel.Css3ContentPanelAppearance;
import com.sencha.gxt.widget.core.client.Header.HeaderAppearance;

public class RsContentPanelAppearance extends Css3ContentPanelAppearance {

	@Override
	public HeaderAppearance getHeaderAppearance() {
		return new RsHeaderAppearance();
	}
}
