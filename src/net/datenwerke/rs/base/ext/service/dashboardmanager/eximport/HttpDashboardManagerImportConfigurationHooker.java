package net.datenwerke.rs.base.ext.service.dashboardmanager.eximport;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.exceptions.IllegalImportConfigException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardFolderDto;
import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.service.eximport.im.http.HttpImportService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportConfigDto;
import net.datenwerke.treedb.ext.service.eximport.http.HttpImportConfigurationProviderHookImplForTrees;

public class HttpDashboardManagerImportConfigurationHooker extends
		HttpImportConfigurationProviderHookImplForTrees<AbstractDashboardManagerNode, AbstractDashboardManagerNodeDto> {

	@Inject
	public HttpDashboardManagerImportConfigurationHooker(DtoService dtoService,
			Provider<HttpImportService> httpImportServiceProvider, SecurityService securityService) {
		super(dtoService, httpImportServiceProvider, securityService);
	}

	@Override
	public boolean consumes(String id) {
		return DashboardManagerImporter.IMPORTER_ID.equals(id);
	}

	@Override
	public void validate(ImportConfigDto config) throws IllegalImportConfigException {
		TreeImportConfigDto<AbstractDashboardManagerNodeDto> treeConfig = (TreeImportConfigDto<AbstractDashboardManagerNodeDto>) config;

		if (null != treeConfig.getParent() && !(treeConfig.getParent() instanceof DashboardFolderDto))
			throw new IllegalImportConfigException("Illegal dadget library destination. Has to be a folder, but was: "
					+ treeConfig.getParent().getClass());
	}
}
