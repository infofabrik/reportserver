package net.datenwerke.gf.client.homepage.modules;

import net.datenwerke.gf.client.homepage.modules.ui.ClientModuleSelector;

public abstract class ClientMainModuleImpl implements ClientMainModule {

	protected ClientModuleSelector moduleSelector;
	
	
	@Override
	public void selected() {
	}

	@Override
	public void setClientModuleSelector(
			ClientModuleSelector moduleManagerModuleSelector) {
		this.moduleSelector = moduleManagerModuleSelector;
	}
	
	@Override
	public String getToolTip() {
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isRecyclable() ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return isRecyclable();
	}

}
