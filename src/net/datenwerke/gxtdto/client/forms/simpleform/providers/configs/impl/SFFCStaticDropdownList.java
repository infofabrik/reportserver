package net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl;

import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCStaticList;

public abstract class SFFCStaticDropdownList<M> extends SFFCStaticList<M> {

	@Override
	public net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCStaticList.TYPE getType() {
		return TYPE.Dropdown;
	}

	public int getWidth(){
		return 210;
	}
	
	@Override
	public boolean allowTextSelection() {
		return false;
	}
}
