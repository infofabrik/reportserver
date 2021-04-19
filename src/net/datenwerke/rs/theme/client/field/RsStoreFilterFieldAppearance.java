package net.datenwerke.rs.theme.client.field;

import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.core.client.GWT;


public class RsStoreFilterFieldAppearance extends
		RsTriggerFieldAppearance {

	public RsStoreFilterFieldAppearance() {
		this(GWT.<Css3TriggerFieldResources>create(Css3TriggerFieldResources.class));
	}

	public RsStoreFilterFieldAppearance(Css3TriggerFieldResources resources) {
		super(resources);
		setTriggerIcon(BaseIcon.TIMES);
	}
}
