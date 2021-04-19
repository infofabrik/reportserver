package net.datenwerke.rs.base.ext.service.security.eximport.hookers;

import net.datenwerke.eximport.ExportDataAnalyzerService;
import net.datenwerke.eximport.im.ImportConfig;
import net.datenwerke.eximport.im.ImportMode;
import net.datenwerke.eximport.im.entity.EntityImportItemConfig;
import net.datenwerke.eximport.obj.ComplexItemProperty;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.eximport.obj.ItemProperty;
import net.datenwerke.rs.base.ext.service.security.eximport.GenericRightsExporter;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.entities.GenericSecurityTargetEntity;

import com.google.inject.Inject;

public class ImportAllGenericRightsHooker implements ImportAllHook {

	private final ExportDataAnalyzerService analizerService;
	private final SecurityService securityService;
	
	
	@Inject
	public ImportAllGenericRightsHooker(
		ExportDataAnalyzerService analizerService,
		SecurityService securityService
		) {
		this.analizerService = analizerService;
		this.securityService = securityService;
	}

	@Override
	public void configure(ImportConfig config) {
		try {
			for(ExportedItem item : analizerService.getExportedItemsFor(config.getExportDataProvider(), GenericRightsExporter.class)){
				ItemProperty prop = item.getPropertyByName("targetIdentifier");
				String targetIdentifier = ((ComplexItemProperty)prop).getElement().getValue();
				GenericSecurityTargetEntity securityTarget = securityService.loadGenericTargetByIdentifier(targetIdentifier);
				
				EntityImportItemConfig itemConfig;
				if(null != securityTarget)
					itemConfig = new EntityImportItemConfig(item.getId(), ImportMode.MERGE, securityTarget);
				else
					itemConfig = new EntityImportItemConfig(item.getId());
				
				config.addItemConfig(itemConfig);
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(e);
		}
	}

}
