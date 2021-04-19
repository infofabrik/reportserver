package net.datenwerke.gf.client.homepage.modules;

import net.datenwerke.gf.client.homepage.ui.DwMainViewport;


public abstract class ClientTempModuleImpl implements ClientTempModule {

	protected DwMainViewport viewport;

	@Override
	public void selected() {
	}

	@Override
	public String getToolTip() {
		return null;
	}

	public void setViewport(DwMainViewport dwMainViewport) {
		this.viewport = dwMainViewport;
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
