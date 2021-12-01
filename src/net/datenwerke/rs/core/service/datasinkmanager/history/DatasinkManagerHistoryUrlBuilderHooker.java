package net.datenwerke.rs.core.service.datasinkmanager.history;

import com.google.inject.Inject;

import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIModule;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.core.service.datasinkmanager.locale.DatasinkManagerMessages;
import net.datenwerke.rs.core.service.genrights.datasinks.DatasinkManagerAdminViewSecurityTarget;
import net.datenwerke.rs.core.service.history.helper.TreePanelHistoryUrlBuilderHooker;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.rights.Read;

public class DatasinkManagerHistoryUrlBuilderHooker extends TreePanelHistoryUrlBuilderHooker {

	private final DatasinkManagerMessages messages = LocalizationServiceImpl.getMessages(DatasinkManagerMessages.class);

	private final static String HISTORY_BUILDER_NAME = "Datasinkmanager";
	
	private final SecurityService securityService;
	
	@Inject
	public DatasinkManagerHistoryUrlBuilderHooker(
		SecurityService securityService	
		){
		this.securityService = securityService;
	}
	
    @Override
    public boolean consumes(Object o) {
       if (!(o instanceof AbstractDatasinkManagerNode))
          return false;

       if (securityService.checkRights(DatasinkManagerAdminViewSecurityTarget.class, Read.class))
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
		return DatasinkUIModule.DATASINK_FAV_HISTORY_TOKEN;
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
