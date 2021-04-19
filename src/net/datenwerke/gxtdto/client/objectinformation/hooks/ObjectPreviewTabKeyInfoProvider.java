package net.datenwerke.gxtdto.client.objectinformation.hooks;

import java.util.Collection;

import net.datenwerke.gxtdto.client.objectinformation.ObjectPreviewTabPanel;

public interface ObjectPreviewTabKeyInfoProvider extends ObjectPreviewTabProviderHook {

	public Collection<?> getSubtypes(Object object);
	
	public void setInfoPanel(ObjectPreviewTabPanel infoPanel, Object object);
}
