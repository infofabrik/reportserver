package net.datenwerke.usermanager.ext.service.history;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.history.helper.TreePanelHistoryUrlBuilderHooker;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.ext.client.usermanager.UserManagerUIModule;
import net.datenwerke.security.service.genrights.usermanager.UserManagerAdminViewSecurityTarget;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.usermanager.ext.service.locale.UserManagerMessages;

public class UserManagerHistoryUrlBuilderHooker extends TreePanelHistoryUrlBuilderHooker {

	private final UserManagerMessages messages = LocalizationServiceImpl.getMessages(UserManagerMessages.class);
	
	private final static String HISTORY_BUILDER_NAME = "Usermanager";

	private final SecurityService securityService;
	
	@Inject
	public UserManagerHistoryUrlBuilderHooker(SecurityService securityService){
		this.securityService = securityService;
		
	}
	
	@Override
	public boolean consumes(Object o) {
		if(!( o instanceof AbstractUserManagerNode))
			return false;
		
         if (securityService.checkRights(UserManagerAdminViewSecurityTarget.class, Read.class))
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
		return UserManagerUIModule.USERMANAGER_FAV_HISTORY_TOKEN;
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
		return "users";
	}

}
