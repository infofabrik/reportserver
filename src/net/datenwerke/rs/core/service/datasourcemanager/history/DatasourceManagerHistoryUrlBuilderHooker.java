package net.datenwerke.rs.core.service.datasourcemanager.history;

import com.google.inject.Inject;

import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIModule;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.locale.DatasourceManagerMessages;
import net.datenwerke.rs.core.service.genrights.datasources.DatasourceManagerAdminViewSecurityTarget;
import net.datenwerke.rs.core.service.history.helper.TreePanelHistoryUrlBuilderHooker;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.rights.Read;

public class DatasourceManagerHistoryUrlBuilderHooker extends TreePanelHistoryUrlBuilderHooker {

	private final DatasourceManagerMessages messages = LocalizationServiceImpl.getMessages(DatasourceManagerMessages.class);

	private final static String HISTORY_BUILDER_NAME = "Datasourcemanager";
	
	private final SecurityService securityService;
	
	@Inject
	public DatasourceManagerHistoryUrlBuilderHooker(
		SecurityService securityService	
		){
		this.securityService = securityService;
	}
	
	@Override
	public boolean consumes(Object o) {
		if(! ( o instanceof AbstractDatasourceManagerNode))
			return false;
		
         if (securityService.checkRights(DatasourceManagerAdminViewSecurityTarget.class, Read.class))
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
		return DatasourceUIModule.DATASOURCE_FAV_HISTORY_TOKEN;
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
