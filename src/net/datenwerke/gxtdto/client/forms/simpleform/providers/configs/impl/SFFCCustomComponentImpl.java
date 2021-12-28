package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCustomComponent;

public class SFFCCustomComponentImpl implements SFFCCustomComponent {

	private final Widget widget;

	public SFFCCustomComponentImpl(Widget widget) {
		this.widget = widget;
	}
	
	@Override
	public Widget getComponent() {
		return widget;
	}

}
