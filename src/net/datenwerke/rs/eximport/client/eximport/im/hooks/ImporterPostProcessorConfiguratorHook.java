package net.datenwerke.rs.eximport.client.eximport.im.hooks;

import net.datenwerke.gxtdto.client.dialog.properties.PropertiesDialogCard;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportPostProcessConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;

public interface ImporterPostProcessorConfiguratorHook extends Hook {

	ImportPostProcessConfigDto getConfiguration() throws NotProperlyConfiguredException;

	String getPostProcessorId();

	PropertiesDialogCard getCard();

	void reset();

}
