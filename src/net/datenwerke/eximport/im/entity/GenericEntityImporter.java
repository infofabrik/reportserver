package net.datenwerke.eximport.im.entity;

import javax.persistence.EntityManager;

import net.datenwerke.eximport.im.ImporterImpl;
import net.datenwerke.eximport.im.objectimporters.BasicObjectImporter;
import net.datenwerke.eximport.im.objectimporters.BasicObjectImporterFactory;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.rs.utils.jpa.EntityUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;

public abstract class GenericEntityImporter extends ImporterImpl<EntityImportItemConfig> {

	public static final String IMPORTER_ID = "GenericEntityImporter";
	
	protected BasicObjectImporterFactory objectImporterFactory;
	protected EntityUtils jpaService;
	protected Provider<EntityManager> entityManagerProvider;

	@Override
	public String getId() {
		return IMPORTER_ID;
	}
	
	@Inject
	public void setObjectImporterFactory(BasicObjectImporterFactory objectImporterFactory){
		this.objectImporterFactory = objectImporterFactory;
	}
	
	@Inject
	public void setJpaServices(EntityUtils jpaService){
		this.jpaService = jpaService;
	}
	
	@Inject
	public void setEntityManagerProvider(Provider<EntityManager> entityManagerProvider){
		this.entityManagerProvider = entityManagerProvider;
	}
	
	@Override
	protected void doImportCreateMode(final EntityImportItemConfig itemConfig) {
		/* create importer */
		ExportedItem exportedItem = importSupervisor.getExportedItemFor(itemConfig);
		BasicObjectImporter importer = objectImporterFactory.create(importSupervisor, exportedItem);
		importer.importObject();
	}
	
	@Override
	protected void doImportReferenceMode(EntityImportItemConfig itemConfig) {
		importSupervisor.registerExternalReference(itemConfig.getId(), itemConfig.getReferenceObject());
	}
	
	@Override
	public boolean postProcess(String id, Object object, boolean enclosed) {
		if(jpaService.isEntity(object)){
			try{
				if(null == jpaService.getId(object))
					entityManagerProvider.get().persist(object);
				return true;
			} catch(Exception e){
				return false;
			}
		}
		
		return true;
	}
}
