package net.datenwerke.rs.dsbundle.service.dsbundle.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundleEntry;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.dtogen.Dto2DatabaseBundleEntryGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DatabaseBundleEntry
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatabaseBundleEntryGenerator implements Dto2PosoGenerator<DatabaseBundleEntryDto,DatabaseBundleEntry> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DatabaseBundleEntryGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		Provider<EntityManager> entityManagerProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.reflectionService = reflectionService;
	}

	public DatabaseBundleEntry loadPoso(DatabaseBundleEntryDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DatabaseBundleEntry poso = entityManager.find(DatabaseBundleEntry.class, id);
		return poso;
	}

	public DatabaseBundleEntry instantiatePoso()  {
		DatabaseBundleEntry poso = new DatabaseBundleEntry();
		return poso;
	}

	public DatabaseBundleEntry createPoso(DatabaseBundleEntryDto dto)  throws ExpectedException {
		DatabaseBundleEntry poso = new DatabaseBundleEntry();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DatabaseBundleEntry createUnmanagedPoso(DatabaseBundleEntryDto dto)  throws ExpectedException {
		DatabaseBundleEntry poso = new DatabaseBundleEntry();

		/* store old id */
		if(null != dto.getId()){
			Field transientIdField = reflectionService.getFieldByAnnotation(poso, TransientID.class);
			if(null != transientIdField){
				transientIdField.setAccessible(true);
				try{
					transientIdField.set(poso, dto.getId());
				} catch(Exception e){
				}
			}
		}

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(DatabaseBundleEntryDto dto, final DatabaseBundleEntry poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DatabaseBundleEntryDto dto, final DatabaseBundleEntry poso)  throws ExpectedException {
		/*  set database */
		AbstractDatasourceManagerNodeDto tmpDto_database = dto.getDatabase();
		if(null != tmpDto_database && null != tmpDto_database.getId()){
			if(null != poso.getDatabase() && null != poso.getDatabase().getId() && ! poso.getDatabase().getId().equals(tmpDto_database.getId())){
				AbstractDatasourceManagerNode newPropertyValue = (AbstractDatasourceManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_database);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDatabase(), newPropertyValue, "database");
				poso.setDatabase(newPropertyValue);
			} else if(null == poso.getDatabase()){
				poso.setDatabase((AbstractDatasourceManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_database));
			}
		} else if(null != tmpDto_database && null == tmpDto_database.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_database, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (database)");
					poso.setDatabase((AbstractDatasourceManagerNode)refPoso);
				}
			});
		} else if(null == tmpDto_database){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDatabase(), null, "database");
			poso.setDatabase(null);
		}

		/*  set key */
		poso.setKey(dto.getKey() );

	}

	protected void mergeProxy2Poso(DatabaseBundleEntryDto dto, final DatabaseBundleEntry poso)  throws ExpectedException {
		/*  set database */
		if(dto.isDatabaseModified()){
			AbstractDatasourceManagerNodeDto tmpDto_database = dto.getDatabase();
			if(null != tmpDto_database && null != tmpDto_database.getId()){
				if(null != poso.getDatabase() && null != poso.getDatabase().getId() && ! poso.getDatabase().getId().equals(tmpDto_database.getId())){
					AbstractDatasourceManagerNode newPropertyValue = (AbstractDatasourceManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_database);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDatabase(), newPropertyValue, "database");
					poso.setDatabase(newPropertyValue);
				} else if(null == poso.getDatabase()){
					poso.setDatabase((AbstractDatasourceManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_database));
				}
			} else if(null != tmpDto_database && null == tmpDto_database.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_database, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (database)");
						poso.setDatabase((AbstractDatasourceManagerNode)refPoso);
					}
			});
			} else if(null == tmpDto_database){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getDatabase(), null, "database");
				poso.setDatabase(null);
			}
		}

		/*  set key */
		if(dto.isKeyModified()){
			poso.setKey(dto.getKey() );
		}

	}

	public void mergeUnmanagedPoso(DatabaseBundleEntryDto dto, final DatabaseBundleEntry poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DatabaseBundleEntryDto dto, final DatabaseBundleEntry poso)  throws ExpectedException {
		/*  set database */
		AbstractDatasourceManagerNodeDto tmpDto_database = dto.getDatabase();
		if(null != tmpDto_database && null != tmpDto_database.getId()){
			AbstractDatasourceManagerNode newPropertyValue = (AbstractDatasourceManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_database);
			poso.setDatabase(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_database, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setDatabase((AbstractDatasourceManagerNode)refPoso);
				}
			});
		} else if(null != tmpDto_database && null == tmpDto_database.getId()){
			final AbstractDatasourceManagerNodeDto tmpDto_database_final = tmpDto_database;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_database, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setDatabase((AbstractDatasourceManagerNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_database_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setDatabase((AbstractDatasourceManagerNode)refPoso);
					}
				}
			});
		} else if(null == tmpDto_database){
			poso.setDatabase(null);
		}

		/*  set key */
		poso.setKey(dto.getKey() );

	}

	protected void mergeProxy2UnmanagedPoso(DatabaseBundleEntryDto dto, final DatabaseBundleEntry poso)  throws ExpectedException {
		/*  set database */
		if(dto.isDatabaseModified()){
			AbstractDatasourceManagerNodeDto tmpDto_database = dto.getDatabase();
			if(null != tmpDto_database && null != tmpDto_database.getId()){
				AbstractDatasourceManagerNode newPropertyValue = (AbstractDatasourceManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_database);
				poso.setDatabase(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_database, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setDatabase((AbstractDatasourceManagerNode)refPoso);
					}
			});
			} else if(null != tmpDto_database && null == tmpDto_database.getId()){
				final AbstractDatasourceManagerNodeDto tmpDto_database_final = tmpDto_database;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_database, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setDatabase((AbstractDatasourceManagerNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_database_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setDatabase((AbstractDatasourceManagerNode)refPoso);
						}
					}
			});
			} else if(null == tmpDto_database){
				poso.setDatabase(null);
			}
		}

		/*  set key */
		if(dto.isKeyModified()){
			poso.setKey(dto.getKey() );
		}

	}

	public DatabaseBundleEntry loadAndMergePoso(DatabaseBundleEntryDto dto)  throws ExpectedException {
		DatabaseBundleEntry poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DatabaseBundleEntryDto dto, DatabaseBundleEntry poso)  {
	}


	public void postProcessCreateUnmanaged(DatabaseBundleEntryDto dto, DatabaseBundleEntry poso)  {
	}


	public void postProcessLoad(DatabaseBundleEntryDto dto, DatabaseBundleEntry poso)  {
	}


	public void postProcessMerge(DatabaseBundleEntryDto dto, DatabaseBundleEntry poso)  {
	}


	public void postProcessInstantiate(DatabaseBundleEntry poso)  {
	}



}
