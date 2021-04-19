package net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen;

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
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListEntry;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.Dto2FavoriteListEntryGenerator;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for FavoriteListEntry
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2FavoriteListEntryGenerator implements Dto2PosoGenerator<FavoriteListEntryDto,FavoriteListEntry> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2FavoriteListEntryGenerator(
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

	public FavoriteListEntry loadPoso(FavoriteListEntryDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		FavoriteListEntry poso = entityManager.find(FavoriteListEntry.class, id);
		return poso;
	}

	public FavoriteListEntry instantiatePoso()  {
		FavoriteListEntry poso = new FavoriteListEntry();
		return poso;
	}

	public FavoriteListEntry createPoso(FavoriteListEntryDto dto)  throws ExpectedException {
		FavoriteListEntry poso = new FavoriteListEntry();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public FavoriteListEntry createUnmanagedPoso(FavoriteListEntryDto dto)  throws ExpectedException {
		FavoriteListEntry poso = new FavoriteListEntry();

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

	public void mergePoso(FavoriteListEntryDto dto, final FavoriteListEntry poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(FavoriteListEntryDto dto, final FavoriteListEntry poso)  throws ExpectedException {
		/*  set referenceEntry */
		AbstractTsDiskNodeDto tmpDto_referenceEntry = dto.getReferenceEntry();
		if(null != tmpDto_referenceEntry && null != tmpDto_referenceEntry.getId()){
			if(null != poso.getReferenceEntry() && null != poso.getReferenceEntry().getId() && ! poso.getReferenceEntry().getId().equals(tmpDto_referenceEntry.getId())){
				AbstractTsDiskNode newPropertyValue = (AbstractTsDiskNode)dtoServiceProvider.get().loadPoso(tmpDto_referenceEntry);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReferenceEntry(), newPropertyValue, "referenceEntry");
				poso.setReferenceEntry(newPropertyValue);
			} else if(null == poso.getReferenceEntry()){
				poso.setReferenceEntry((AbstractTsDiskNode)dtoServiceProvider.get().loadPoso(tmpDto_referenceEntry));
			}
		} else if(null != tmpDto_referenceEntry && null == tmpDto_referenceEntry.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_referenceEntry, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (referenceEntry)");
					poso.setReferenceEntry((AbstractTsDiskNode)refPoso);
				}
			});
		} else if(null == tmpDto_referenceEntry){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReferenceEntry(), null, "referenceEntry");
			poso.setReferenceEntry(null);
		}

	}

	protected void mergeProxy2Poso(FavoriteListEntryDto dto, final FavoriteListEntry poso)  throws ExpectedException {
		/*  set referenceEntry */
		if(dto.isReferenceEntryModified()){
			AbstractTsDiskNodeDto tmpDto_referenceEntry = dto.getReferenceEntry();
			if(null != tmpDto_referenceEntry && null != tmpDto_referenceEntry.getId()){
				if(null != poso.getReferenceEntry() && null != poso.getReferenceEntry().getId() && ! poso.getReferenceEntry().getId().equals(tmpDto_referenceEntry.getId())){
					AbstractTsDiskNode newPropertyValue = (AbstractTsDiskNode)dtoServiceProvider.get().loadPoso(tmpDto_referenceEntry);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReferenceEntry(), newPropertyValue, "referenceEntry");
					poso.setReferenceEntry(newPropertyValue);
				} else if(null == poso.getReferenceEntry()){
					poso.setReferenceEntry((AbstractTsDiskNode)dtoServiceProvider.get().loadPoso(tmpDto_referenceEntry));
				}
			} else if(null != tmpDto_referenceEntry && null == tmpDto_referenceEntry.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_referenceEntry, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (referenceEntry)");
						poso.setReferenceEntry((AbstractTsDiskNode)refPoso);
					}
			});
			} else if(null == tmpDto_referenceEntry){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getReferenceEntry(), null, "referenceEntry");
				poso.setReferenceEntry(null);
			}
		}

	}

	public void mergeUnmanagedPoso(FavoriteListEntryDto dto, final FavoriteListEntry poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(FavoriteListEntryDto dto, final FavoriteListEntry poso)  throws ExpectedException {
		/*  set referenceEntry */
		AbstractTsDiskNodeDto tmpDto_referenceEntry = dto.getReferenceEntry();
		if(null != tmpDto_referenceEntry && null != tmpDto_referenceEntry.getId()){
			AbstractTsDiskNode newPropertyValue = (AbstractTsDiskNode)dtoServiceProvider.get().loadPoso(tmpDto_referenceEntry);
			poso.setReferenceEntry(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_referenceEntry, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setReferenceEntry((AbstractTsDiskNode)refPoso);
				}
			});
		} else if(null != tmpDto_referenceEntry && null == tmpDto_referenceEntry.getId()){
			final AbstractTsDiskNodeDto tmpDto_referenceEntry_final = tmpDto_referenceEntry;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_referenceEntry, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setReferenceEntry((AbstractTsDiskNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_referenceEntry_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setReferenceEntry((AbstractTsDiskNode)refPoso);
					}
				}
			});
		} else if(null == tmpDto_referenceEntry){
			poso.setReferenceEntry(null);
		}

	}

	protected void mergeProxy2UnmanagedPoso(FavoriteListEntryDto dto, final FavoriteListEntry poso)  throws ExpectedException {
		/*  set referenceEntry */
		if(dto.isReferenceEntryModified()){
			AbstractTsDiskNodeDto tmpDto_referenceEntry = dto.getReferenceEntry();
			if(null != tmpDto_referenceEntry && null != tmpDto_referenceEntry.getId()){
				AbstractTsDiskNode newPropertyValue = (AbstractTsDiskNode)dtoServiceProvider.get().loadPoso(tmpDto_referenceEntry);
				poso.setReferenceEntry(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_referenceEntry, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setReferenceEntry((AbstractTsDiskNode)refPoso);
					}
			});
			} else if(null != tmpDto_referenceEntry && null == tmpDto_referenceEntry.getId()){
				final AbstractTsDiskNodeDto tmpDto_referenceEntry_final = tmpDto_referenceEntry;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_referenceEntry, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setReferenceEntry((AbstractTsDiskNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_referenceEntry_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setReferenceEntry((AbstractTsDiskNode)refPoso);
						}
					}
			});
			} else if(null == tmpDto_referenceEntry){
				poso.setReferenceEntry(null);
			}
		}

	}

	public FavoriteListEntry loadAndMergePoso(FavoriteListEntryDto dto)  throws ExpectedException {
		FavoriteListEntry poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(FavoriteListEntryDto dto, FavoriteListEntry poso)  {
	}


	public void postProcessCreateUnmanaged(FavoriteListEntryDto dto, FavoriteListEntry poso)  {
	}


	public void postProcessLoad(FavoriteListEntryDto dto, FavoriteListEntry poso)  {
	}


	public void postProcessMerge(FavoriteListEntryDto dto, FavoriteListEntry poso)  {
	}


	public void postProcessInstantiate(FavoriteListEntry poso)  {
	}



}
