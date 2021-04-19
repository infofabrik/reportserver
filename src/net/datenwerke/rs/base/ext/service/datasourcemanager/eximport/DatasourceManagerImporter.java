package net.datenwerke.rs.base.ext.service.datasourcemanager.eximport;

import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporter;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

import com.google.inject.Inject;

/**
 * 
 *
 */
public class DatasourceManagerImporter extends TreeNodeImporter {

	public static final String IMPORTER_ID = "DatasourceManagerImporter";
	
	private final DatasourceService datasourceService;
	
	@Inject
	public DatasourceManagerImporter(
		DatasourceService datasourceService
		){
		
		/* store objects */
		this.datasourceService = datasourceService;
	}
	
	@Override
	public Class<?>[] getRecognizedExporters() {
		return new Class<?>[]{DatasourceManagerExporter.class};
	}

	@Override
	protected TreeDBManager getTreeDBManager() {
		return datasourceService;
	}

	@Override
	public String getId() {
		return IMPORTER_ID;
	}
}
