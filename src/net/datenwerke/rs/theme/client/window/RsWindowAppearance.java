package net.datenwerke.rs.theme.client.window;

import com.sencha.gxt.theme.neptune.client.base.window.Css3WindowAppearance;
import com.sencha.gxt.widget.core.client.Header.HeaderAppearance;

import net.datenwerke.rs.theme.client.panel.RsHeaderAppearance;

public class RsWindowAppearance extends Css3WindowAppearance {

	@Override
	public HeaderAppearance getHeaderAppearance() {
		return new RsHeaderAppearance();
	}

}
