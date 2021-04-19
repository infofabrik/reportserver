package net.datenwerke.rs.base.ext.service.datasourcemanager.eximport;

import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class DatasourceManagerExporter extends TreeNodeExporter {

	private static final String EXPORTER_ID = "DatasourceManagerExporter";

	@Override
	public String getExporterId() {
		return EXPORTER_ID;
	}
	
	@Override
	protected Class<? extends AbstractNode<?>> getTreeType() {
		return AbstractDatasourceManagerNode.class;
	}
	
	@Override
	protected Class<?>[] getExportableTypes() {
		return new Class<?>[]{DatasourceFolder.class, DatasourceDefinition.class};
	}


}
