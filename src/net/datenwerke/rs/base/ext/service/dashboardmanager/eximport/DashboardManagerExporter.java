package net.datenwerke.rs.base.ext.service.dashboardmanager.eximport;

import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardFolder;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class DashboardManagerExporter extends TreeNodeExporter {

	private static final String EXPORTER_ID = "DashboardManagerExporter";

	@Override
	public String getExporterId() {
		return EXPORTER_ID;
	}

	@Override
	protected Class<? extends AbstractNode<?>> getTreeType() {
		return AbstractDashboardManagerNode.class;
	}

	@Override
	protected Class<?>[] getExportableTypes() {
		return new Class<?>[] { DashboardFolder.class, DashboardNode.class, DadgetNode.class };
	}

}
