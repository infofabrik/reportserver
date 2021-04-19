package net.datenwerke.security.service.security.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.security.client.security.dto.AccessTypeDto;
import net.datenwerke.security.client.security.dto.AceAccessMapDto;
import net.datenwerke.security.client.security.dto.AceDto;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.service.security.entities.AccessType;
import net.datenwerke.security.service.security.entities.Ace;
import net.datenwerke.security.service.security.entities.AceAccessMap;
import net.datenwerke.security.service.security.entities.dtogen.Dto2AceGenerator;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;

/**
 * Dto2PosoGenerator for Ace
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2AceGenerator implements Dto2PosoGenerator<AceDto,Ace> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2AceGenerator(
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

	public Ace loadPoso(AceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		Ace poso = entityManager.find(Ace.class, id);
		return poso;
	}

	public Ace instantiatePoso()  {
		Ace poso = new Ace();
		return poso;
	}

	public Ace createPoso(AceDto dto)  throws ExpectedException {
		Ace poso = new Ace();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public Ace createUnmanagedPoso(AceDto dto)  throws ExpectedException {
		Ace poso = new Ace();

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

	public void mergePoso(AceDto dto, final Ace poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(AceDto dto, final Ace poso)  throws ExpectedException {
		/*  set accessMaps */
		final Set<AceAccessMap> col_accessMaps = new HashSet<AceAccessMap>();
		/* load new data from dto */
		Collection<AceAccessMapDto> tmpCol_accessMaps = dto.getAccessMaps();

		/* load current data from poso */
		if(null != poso.getAccessMaps())
			col_accessMaps.addAll(poso.getAccessMaps());

		/* remove non existing data */
		Set<AceAccessMap> remDto_accessMaps = new HashSet<AceAccessMap>();
		for(AceAccessMap ref : col_accessMaps){
			boolean found = false;
			for(AceAccessMapDto refDto : tmpCol_accessMaps){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_accessMaps.add(ref);
		}
		for(AceAccessMap ref : remDto_accessMaps)
			col_accessMaps.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_accessMaps, "accessMaps");

		/* merge or add data */
		Set<AceAccessMap> new_col_accessMaps = new HashSet<AceAccessMap>();
		for(AceAccessMapDto refDto : tmpCol_accessMaps){
			boolean found = false;
			for(AceAccessMap ref : col_accessMaps){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_accessMaps.add((AceAccessMap) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_accessMaps.add((AceAccessMap) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(accessMaps) ");
		}
		poso.setAccessMaps(new_col_accessMaps);

		/*  set accesstype */
		AccessTypeDto tmpDto_accesstype = dto.getAccesstype();
		poso.setAccesstype((AccessType)dtoServiceProvider.get().createPoso(tmpDto_accesstype));

		/*  set folk */
		AbstractUserManagerNodeDto tmpDto_folk = dto.getFolk();
		if(null != tmpDto_folk && null != tmpDto_folk.getId()){
			if(null != poso.getFolk() && null != poso.getFolk().getId() && ! poso.getFolk().getId().equals(tmpDto_folk.getId())){
				AbstractUserManagerNode newPropertyValue = (AbstractUserManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_folk);
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFolk(), newPropertyValue, "folk");
				poso.setFolk(newPropertyValue);
			} else if(null == poso.getFolk()){
				poso.setFolk((AbstractUserManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_folk));
			}
		} else if(null != tmpDto_folk && null == tmpDto_folk.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_folk, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso)
						throw new IllegalArgumentException("referenced dto should have an id (folk)");
					poso.setFolk((AbstractUserManagerNode)refPoso);
				}
			});
		} else if(null == tmpDto_folk){
			dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFolk(), null, "folk");
			poso.setFolk(null);
		}

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(AceDto dto, final Ace poso)  throws ExpectedException {
		/*  set accessMaps */
		if(dto.isAccessMapsModified()){
			final Set<AceAccessMap> col_accessMaps = new HashSet<AceAccessMap>();
			/* load new data from dto */
			Collection<AceAccessMapDto> tmpCol_accessMaps = null;
			tmpCol_accessMaps = dto.getAccessMaps();

			/* load current data from poso */
			if(null != poso.getAccessMaps())
				col_accessMaps.addAll(poso.getAccessMaps());

			/* remove non existing data */
			Set<AceAccessMap> remDto_accessMaps = new HashSet<AceAccessMap>();
			for(AceAccessMap ref : col_accessMaps){
				boolean found = false;
				for(AceAccessMapDto refDto : tmpCol_accessMaps){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_accessMaps.add(ref);
			}
			for(AceAccessMap ref : remDto_accessMaps)
				col_accessMaps.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_accessMaps, "accessMaps");

			/* merge or add data */
			Set<AceAccessMap> new_col_accessMaps = new HashSet<AceAccessMap>();
			for(AceAccessMapDto refDto : tmpCol_accessMaps){
				boolean found = false;
				for(AceAccessMap ref : col_accessMaps){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_accessMaps.add((AceAccessMap) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_accessMaps.add((AceAccessMap) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(accessMaps) ");
			}
			poso.setAccessMaps(new_col_accessMaps);
		}

		/*  set accesstype */
		if(dto.isAccesstypeModified()){
			AccessTypeDto tmpDto_accesstype = dto.getAccesstype();
			poso.setAccesstype((AccessType)dtoServiceProvider.get().createPoso(tmpDto_accesstype));
		}

		/*  set folk */
		if(dto.isFolkModified()){
			AbstractUserManagerNodeDto tmpDto_folk = dto.getFolk();
			if(null != tmpDto_folk && null != tmpDto_folk.getId()){
				if(null != poso.getFolk() && null != poso.getFolk().getId() && ! poso.getFolk().getId().equals(tmpDto_folk.getId())){
					AbstractUserManagerNode newPropertyValue = (AbstractUserManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_folk);
					dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFolk(), newPropertyValue, "folk");
					poso.setFolk(newPropertyValue);
				} else if(null == poso.getFolk()){
					poso.setFolk((AbstractUserManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_folk));
				}
			} else if(null != tmpDto_folk && null == tmpDto_folk.getId()){
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_folk, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso)
							throw new IllegalArgumentException("referenced dto should have an id (folk)");
						poso.setFolk((AbstractUserManagerNode)refPoso);
					}
			});
			} else if(null == tmpDto_folk){
				dto2PosoSupervisor.referencedObjectRemoved(dto, poso, poso.getFolk(), null, "folk");
				poso.setFolk(null);
			}
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(AceDto dto, final Ace poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(AceDto dto, final Ace poso)  throws ExpectedException {
		/*  set accessMaps */
		final Set<AceAccessMap> col_accessMaps = new HashSet<AceAccessMap>();
		/* load new data from dto */
		Collection<AceAccessMapDto> tmpCol_accessMaps = dto.getAccessMaps();

		/* merge or add data */
		for(AceAccessMapDto refDto : tmpCol_accessMaps){
			col_accessMaps.add((AceAccessMap) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setAccessMaps(col_accessMaps);

		/*  set accesstype */
		AccessTypeDto tmpDto_accesstype = dto.getAccesstype();
		poso.setAccesstype((AccessType)dtoServiceProvider.get().createPoso(tmpDto_accesstype));

		/*  set folk */
		AbstractUserManagerNodeDto tmpDto_folk = dto.getFolk();
		if(null != tmpDto_folk && null != tmpDto_folk.getId()){
			AbstractUserManagerNode newPropertyValue = (AbstractUserManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_folk);
			poso.setFolk(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_folk, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null != refPoso)
						poso.setFolk((AbstractUserManagerNode)refPoso);
				}
			});
		} else if(null != tmpDto_folk && null == tmpDto_folk.getId()){
			final AbstractUserManagerNodeDto tmpDto_folk_final = tmpDto_folk;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_folk, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
				public void callback(Object refPoso){
					if(null == refPoso){
						try{
							poso.setFolk((AbstractUserManagerNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_folk_final));
						} catch(ExpectedException e){
							throw new RuntimeException(e);
						}
					} else {
						poso.setFolk((AbstractUserManagerNode)refPoso);
					}
				}
			});
		} else if(null == tmpDto_folk){
			poso.setFolk(null);
		}

		/*  set n */
		try{
			poso.setN(dto.getN() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(AceDto dto, final Ace poso)  throws ExpectedException {
		/*  set accessMaps */
		if(dto.isAccessMapsModified()){
			final Set<AceAccessMap> col_accessMaps = new HashSet<AceAccessMap>();
			/* load new data from dto */
			Collection<AceAccessMapDto> tmpCol_accessMaps = null;
			tmpCol_accessMaps = dto.getAccessMaps();

			/* merge or add data */
			for(AceAccessMapDto refDto : tmpCol_accessMaps){
				col_accessMaps.add((AceAccessMap) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setAccessMaps(col_accessMaps);
		}

		/*  set accesstype */
		if(dto.isAccesstypeModified()){
			AccessTypeDto tmpDto_accesstype = dto.getAccesstype();
			poso.setAccesstype((AccessType)dtoServiceProvider.get().createPoso(tmpDto_accesstype));
		}

		/*  set folk */
		if(dto.isFolkModified()){
			AbstractUserManagerNodeDto tmpDto_folk = dto.getFolk();
			if(null != tmpDto_folk && null != tmpDto_folk.getId()){
				AbstractUserManagerNode newPropertyValue = (AbstractUserManagerNode)dtoServiceProvider.get().loadPoso(tmpDto_folk);
				poso.setFolk(newPropertyValue);
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_folk, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null != refPoso)
							poso.setFolk((AbstractUserManagerNode)refPoso);
					}
			});
			} else if(null != tmpDto_folk && null == tmpDto_folk.getId()){
				final AbstractUserManagerNodeDto tmpDto_folk_final = tmpDto_folk;
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onPosoCreation(tmpDto_folk, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnPosoCreation(){
					public void callback(Object refPoso){
						if(null == refPoso){
							try{
								poso.setFolk((AbstractUserManagerNode)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_folk_final));
							} catch(ExpectedException e){
								throw new RuntimeException(e);
							}
						} else {
							poso.setFolk((AbstractUserManagerNode)refPoso);
						}
					}
			});
			} else if(null == tmpDto_folk){
				poso.setFolk(null);
			}
		}

		/*  set n */
		if(dto.isNModified()){
			try{
				poso.setN(dto.getN() );
			} catch(NullPointerException e){
			}
		}

	}

	public Ace loadAndMergePoso(AceDto dto)  throws ExpectedException {
		Ace poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(AceDto dto, Ace poso)  {
	}


	public void postProcessCreateUnmanaged(AceDto dto, Ace poso)  {
	}


	public void postProcessLoad(AceDto dto, Ace poso)  {
	}


	public void postProcessMerge(AceDto dto, Ace poso)  {
	}


	public void postProcessInstantiate(Ace poso)  {
	}



}
