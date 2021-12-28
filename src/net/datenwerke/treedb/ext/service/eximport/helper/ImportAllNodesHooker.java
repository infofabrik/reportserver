package net.datenwerke.treedb.ext.service.eximport.helper;

import com.google.inject.Inject;

import net.datenwerke.eximport.ExportDataAnalyzerService;
import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.eximport.im.ImportConfig;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.eximport.obj.ItemProperty;
import net.datenwerke.eximport.obj.ReferenceItemProperty;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExImportOptions;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImporterConfig;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public abstract class ImportAllNodesHooker<N extends AbstractNode<N>> implements ImportAllHook {

	private final TreeDBManager<N> treeManager;
	private final Class<? extends Exporter> exporterType;
	
	private ExportDataAnalyzerService analizerService;
	
	public ImportAllNodesHooker(
		TreeDBManager<N> treeManager,
		Class<? extends Exporter> exporterType
		) {
		
		/* store objects */
		this.treeManager = treeManager;
		this.exporterType = exporterType;
	}
	
	@Inject
	public void setAnalizerService(ExportDataAnalyzerService analizerService) {
		this.analizerService = analizerService;
	}

	@Override
	public void configure(ImportConfig config) {
		N root = treeManager.getRoots().get(0);
		
		TreeNodeImporterConfig specConfig = new TreeNodeImporterConfig();
		specConfig.addExImporterOptions(TreeNodeExImportOptions.INCLUDE_OWNER, TreeNodeExImportOptions.INCLUDE_SECURITY);
		config.addSpecificImporterConfigs(specConfig);
		
		try {
			String rootId = "";
			for(ExportedItem item : analizerService.getExportedItemsFor(config.getExportDataProvider(), exporterType))
				if(null == item.getPropertyByName("parent"))
					rootId = item.getId();
			
			for(ExportedItem item : analizerService.getExportedItemsFor(config.getExportDataProvider(), exporterType)){
				if(null != item.getPropertyByName("parent")){
					TreeNodeImportItemConfig itemConfig = new TreeNodeImportItemConfig(item.getId());
					ItemProperty parentProp =item.getPropertyByName("parent");
					if(parentProp instanceof ReferenceItemProperty && rootId.equals(((ReferenceItemProperty)parentProp).getReferenceId()))
						itemConfig.setParent(root);
					config.addItemConfig(itemConfig);
				}
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
