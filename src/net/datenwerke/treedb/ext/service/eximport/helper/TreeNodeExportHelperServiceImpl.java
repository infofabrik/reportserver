package net.datenwerke.treedb.ext.service.eximport.helper;

import com.google.inject.Inject;

import net.datenwerke.eximport.ExportService;
import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExportItemConfig;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class TreeNodeExportHelperServiceImpl {

	private final ExportService exportService;
	
	@Inject
	public TreeNodeExportHelperServiceImpl(ExportService exportService) {
		this.exportService = exportService;
	}

	public String export(AbstractNode node, boolean addChildren,
			String name) {
		/* export report */
		ExportConfig exportConfig = new ExportConfig();
		exportConfig.setName(name);
		exportConfig.addItemConfig(new TreeNodeExportItemConfig(node));
		
		if(addChildren)
			addChildren(exportConfig, node);
		
		return exportService.exportIndent(exportConfig);
	}

	private void addChildren(ExportConfig exportConfig, AbstractNode node) {
		for(Object o : node.getChildren()){
			AbstractNode childNode = (AbstractNode) o;
			exportConfig.addItemConfig(new TreeNodeExportItemConfig(childNode));
			addChildren(exportConfig, childNode);
		}
	}

}
