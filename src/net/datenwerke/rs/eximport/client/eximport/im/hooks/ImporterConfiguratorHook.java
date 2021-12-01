package net.datenwerke.rs.eximport.client.eximport.im.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.eximport.client.eximport.im.ui.ImportMainPanel;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

public interface ImporterConfiguratorHook extends Hook {

	ImageResource getImporterIcon();
	
	String getImporterName();
	
	Collection<String> getSupportedExporters();
	
	Widget initConfigPanel(ImportMainPanel importMainPanel);
	
	ImportConfigDto getConfiguration() throws NotProperlyConfiguredException;
	
	String getImporterId();
	
	void reset();
	

}
