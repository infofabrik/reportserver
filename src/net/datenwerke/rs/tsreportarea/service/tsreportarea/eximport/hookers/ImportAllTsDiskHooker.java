package net.datenwerke.rs.tsreportarea.service.tsreportarea.eximport.hookers;

import net.datenwerke.eximport.ExportDataAnalyzerService;
import net.datenwerke.eximport.im.ImportConfig;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.eximport.TsDiskExporter;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;

import com.google.inject.Inject;

public class ImportAllTsDiskHooker implements ImportAllHook {

	private final TsDiskService tsDiskService;
	private final ExportDataAnalyzerService analizerService;
	
	@Inject
	public ImportAllTsDiskHooker(
		TsDiskService tsDiskService,
		ExportDataAnalyzerService analizerService) {
		this.tsDiskService = tsDiskService;
		this.analizerService = analizerService;
	}



	@Override
	public void configure(ImportConfig config) {
		try {
			for(ExportedItem item : analizerService.getExportedItemsFor(config.getExportDataProvider(), TsDiskExporter.class)){
				TreeNodeImportItemConfig itemConfig = new TreeNodeImportItemConfig(item.getId());
				config.addItemConfig(itemConfig);
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
