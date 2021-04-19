package net.datenwerke.rs.tsreportarea.service.tsreportarea.eximport;

import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExporter;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class TsDiskExporter extends TreeNodeExporter {

	public static final String EXPORTER_ID = "TsDiskExporter";
	
	@Override
	public String getExporterId() {
		return EXPORTER_ID;
	}
	
	@Override
	protected Class<? extends AbstractNode<?>> getTreeType() {
		return AbstractTsDiskNode.class;
	}
	
	@Override
	protected Class<?>[] getExportableTypes() {
		return new Class<?>[]{AbstractTsDiskNode.class};
	}


}
