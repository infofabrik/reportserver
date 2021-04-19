package net.datenwerke.gxtdto.client.objectinformation.hooks;

import net.datenwerke.gxtdto.client.ui.helper.info.InfoWindow;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface ObjectInfoAdditionalInfoProvider extends Hook {

	public boolean consumes(Object object);

	public void addInfoFor(Object object, InfoWindow window);
	
	
}
