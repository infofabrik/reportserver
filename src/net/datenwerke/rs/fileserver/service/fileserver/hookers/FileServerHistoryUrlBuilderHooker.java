package net.datenwerke.rs.fileserver.service.fileserver.hookers;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.history.helper.TreePanelHistoryUrlBuilderHooker;
import net.datenwerke.rs.fileserver.client.fileserver.FileServerUiModule;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.genrights.FileServerManagerAdminViewSecurityTarget;
import net.datenwerke.rs.fileserver.service.fileserver.locale.FileserverMessages;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.rights.Read;

public class FileServerHistoryUrlBuilderHooker extends TreePanelHistoryUrlBuilderHooker {

	private final FileserverMessages messages = LocalizationServiceImpl.getMessages(FileserverMessages.class);
	
	private final static String HISTORY_BUILDER_NAME = "FileServerManager";

	private final SecurityService securityService;

	@Inject
	public FileServerHistoryUrlBuilderHooker(SecurityService securityService){
		this.securityService = securityService;
		
	}
	
	@Override
	public boolean consumes(Object o) {
		if(! (o instanceof AbstractFileServerNode))
			return false;
		
         if (securityService.checkRights(FileServerManagerAdminViewSecurityTarget.class, Read.class))
            return true;
         else {
            if (!(o instanceof SecurityTarget))
               return false;
            else
               return securityService.checkRights((SecurityTarget) o, Read.class);
         }
	}

	@Override
	protected String getTokenName() {
		return FileServerUiModule.FILESERVER_HISTORY_TOKEN;
	}

	@Override
	protected String getBuilderId() {
		return HISTORY_BUILDER_NAME;
	}

	@Override
	protected String getNameFor(Object o) {
		return messages.historyUrlBuilderName();
	}

	@Override
	protected String getIconFor(Object o) {
		return messages.historyUrlBuilderIcon();
	}

}
