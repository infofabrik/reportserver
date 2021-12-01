package net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.hookers;

import net.datenwerke.rs.base.ext.service.datasourcemanager.eximport.DatasourceManagerExporter;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.treedb.ext.service.eximport.helper.ImportAllNodesHooker;

import com.google.inject.Inject;

public class ImportAllDatasourcesHooker extends ImportAllNodesHooker<AbstractDatasourceManagerNode>{

	@Inject
	public ImportAllDatasourcesHooker(
		DatasourceService treeDbManager
		) {
		super(treeDbManager, DatasourceManagerExporter.class);
	}

}
