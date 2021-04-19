package net.datenwerke.rs.base.ext.service.security.eximport;

import net.datenwerke.eximport.im.entity.EntityImportItemConfig;
import net.datenwerke.eximport.im.entity.GenericEntityImporter;
import net.datenwerke.eximport.im.objectimporters.BasicObjectImporter;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.entities.Ace;
import net.datenwerke.security.service.security.entities.GenericSecurityTargetEntity;

import com.google.inject.Inject;

public class GenericRightsImporter extends GenericEntityImporter {

	public static final String IMPORTER_ID = "GenericRightsImporter";
	
	private SecurityService securityService;
	
	@Inject
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	@Override
	public String getId() {
		return IMPORTER_ID;
	}

	@Override
	public Class<?>[] getRecognizedExporters() {
		return new Class<?>[]{GenericRightsExporter.class};
	}
	
	@Override
	protected void doImportMergeMode(EntityImportItemConfig itemConfig) {
		GenericSecurityTargetEntity securityTarget = (GenericSecurityTargetEntity) itemConfig.getReferenceObject();
		
		ExportedItem exportedItem = importSupervisor.getExportedItemFor(itemConfig);
		BasicObjectImporter importer = objectImporterFactory.create(importSupervisor, exportedItem);
		importer.setRegisterImportedObject(false);
		GenericSecurityTargetEntity imported = (GenericSecurityTargetEntity) importer.importObject();
		
		for(Ace ace : imported.getAcl().getAces()){
			securityTarget.getAcl().addAce(ace);
			securityService.persist(ace);
		}
		
		securityService.merge(securityTarget);
		
		/* register as external reference */
		importSupervisor.registerExternalReference(itemConfig.getId(), securityTarget);
	}
}
