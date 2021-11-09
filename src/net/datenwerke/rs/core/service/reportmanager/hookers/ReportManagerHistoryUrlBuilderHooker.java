package net.datenwerke.rs.core.service.reportmanager.hookers;

import com.google.inject.Inject;

import net.datenwerke.rs.core.client.reportmanager.ReportManagerUIModule;
import net.datenwerke.rs.core.service.genrights.reportmanager.ReportManagerAdminViewSecurityTarget;
import net.datenwerke.rs.core.service.history.helper.TreePanelHistoryUrlBuilderHooker;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.locale.ReportManagerMessages;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.rights.Read;

public class ReportManagerHistoryUrlBuilderHooker extends TreePanelHistoryUrlBuilderHooker {

	private final ReportManagerMessages messages = LocalizationServiceImpl.getMessages(ReportManagerMessages.class);
	
	private final static String HISTORY_BUILDER_NAME = "Reportmanager";

	private final SecurityService securityService;

	@Inject
	public ReportManagerHistoryUrlBuilderHooker(SecurityService securityService){
		this.securityService = securityService;
		
	}
	
	@Override
	public boolean consumes(Object o) {
		if(! (o instanceof AbstractReportManagerNode))
			return false;
		
         if (securityService.checkRights(ReportManagerAdminViewSecurityTarget.class, Read.class))
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
		return ReportManagerUIModule.REPORTMANAGER_FAV_HISTORY_TOKEN;
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
