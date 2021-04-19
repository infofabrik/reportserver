package net.datenwerke.rs.base.ext.service.dashboardmanager.eximport;

import com.google.inject.Inject;

import net.datenwerke.rs.dashboard.service.dashboard.DashboardService;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

/**
 * 
 *
 */
public class DashboardManagerImporter extends TreeNodeImporter {

	public static final String IMPORTER_ID = "DashboardManagerImporter";
	
	private final DashboardService dashboardService;
	
	@Inject
	public DashboardManagerImporter(
		DashboardService dashboardService
		){
		
		/* store objects */
		this.dashboardService = dashboardService;
	}
	
	@Override
	public Class<?>[] getRecognizedExporters() {
		return new Class<?>[]{DashboardManagerExporter.class};
	}

	@Override
	protected TreeDBManager getTreeDBManager() {
		return dashboardService;
	}

	@Override
	public String getId() {
		return IMPORTER_ID;
	}
}
