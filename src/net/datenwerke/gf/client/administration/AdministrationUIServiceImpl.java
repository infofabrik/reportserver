package net.datenwerke.gf.client.administration;

import net.datenwerke.gf.client.administration.locale.AdministrationMessages;
import net.datenwerke.gf.client.administration.ui.AdministrationPanel;
import net.datenwerke.gf.client.homepage.modules.ui.ClientModuleSelector;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class AdministrationUIServiceImpl implements AdministrationUIService {
	
	final private Provider<AdministrationPanel> adminPanelProvider;
	
	@Inject
	public AdministrationUIServiceImpl(
		Provider<AdministrationPanel> adminPanel	
		){
		this.adminPanelProvider = adminPanel;
		
	}

	@Override
	public Widget getMainWidget() {
		return adminPanelProvider.get();
	}

	@Override
	public ImageResource getIcon() {
		return BaseIcon.COG.toImageResource();
	}
	
	@Override
	public String getModuleName() {
		return AdministrationMessages.INSTANCE.administration();
	}

	@Override
	public void selected() {
	}

	@Override
	public void setClientModuleSelector(
			ClientModuleSelector moduleManagerModuleSelector) {
		// we do not care
	}
	
	@Override
	public String getToolTip() {
		return null;
	}
	
	@Override
	public boolean isRecyclable() {
		return true;
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
