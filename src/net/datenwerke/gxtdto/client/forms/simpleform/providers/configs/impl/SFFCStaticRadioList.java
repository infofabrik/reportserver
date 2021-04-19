package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl;

import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCStaticList;

import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public abstract class SFFCStaticRadioList<M> extends SFFCStaticList<M> {

	@Override
	public net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCStaticList.TYPE getType() {
		return TYPE.Radio;
	}

	public Container getRadioContainer(){
		return new VerticalLayoutContainer();
	}
	
}
