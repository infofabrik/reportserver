package net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.FavoriteListEntryDto;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteList;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.FavoriteListEntry;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.dtogen.Dto2FavoriteListGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for FavoriteList
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2FavoriteListGenerator implements Dto2PosoGenerator<FavoriteListDto,FavoriteList> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2FavoriteListGenerator(
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

	public FavoriteList loadPoso(FavoriteListDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		FavoriteList poso = entityManager.find(FavoriteList.class, id);
		return poso;
	}

	public FavoriteList instantiatePoso()  {
		FavoriteList poso = new FavoriteList();
		return poso;
	}

	public FavoriteList createPoso(FavoriteListDto dto)  throws ExpectedException {
		FavoriteList poso = new FavoriteList();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public FavoriteList createUnmanagedPoso(FavoriteListDto dto)  throws ExpectedException {
		FavoriteList poso = new FavoriteList();

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

	public void mergePoso(FavoriteListDto dto, final FavoriteList poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(FavoriteListDto dto, final FavoriteList poso)  throws ExpectedException {
		/*  set referenceEntries */
		final List<FavoriteListEntry> col_referenceEntries = new ArrayList<FavoriteListEntry>();
		/* load new data from dto */
		Collection<FavoriteListEntryDto> tmpCol_referenceEntries = dto.getReferenceEntries();

		/* load current data from poso */
		if(null != poso.getReferenceEntries())
			col_referenceEntries.addAll(poso.getReferenceEntries());

		/* remove non existing data */
		List<FavoriteListEntry> remDto_referenceEntries = new ArrayList<FavoriteListEntry>();
		for(FavoriteListEntry ref : col_referenceEntries){
			boolean found = false;
			for(FavoriteListEntryDto refDto : tmpCol_referenceEntries){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_referenceEntries.add(ref);
		}
		for(FavoriteListEntry ref : remDto_referenceEntries)
			col_referenceEntries.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_referenceEntries, "referenceEntries");

		/* merge or add data */
		List<FavoriteListEntry> new_col_referenceEntries = new ArrayList<FavoriteListEntry>();
		for(FavoriteListEntryDto refDto : tmpCol_referenceEntries){
			boolean found = false;
			for(FavoriteListEntry ref : col_referenceEntries){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_referenceEntries.add((FavoriteListEntry) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_referenceEntries.add((FavoriteListEntry) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(referenceEntries) ");
		}
		poso.setReferenceEntries(new_col_referenceEntries);

	}

	protected void mergeProxy2Poso(FavoriteListDto dto, final FavoriteList poso)  throws ExpectedException {
		/*  set referenceEntries */
		if(dto.isReferenceEntriesModified()){
			final List<FavoriteListEntry> col_referenceEntries = new ArrayList<FavoriteListEntry>();
			/* load new data from dto */
			Collection<FavoriteListEntryDto> tmpCol_referenceEntries = null;
			tmpCol_referenceEntries = dto.getReferenceEntries();

			/* load current data from poso */
			if(null != poso.getReferenceEntries())
				col_referenceEntries.addAll(poso.getReferenceEntries());

			/* remove non existing data */
			List<FavoriteListEntry> remDto_referenceEntries = new ArrayList<FavoriteListEntry>();
			for(FavoriteListEntry ref : col_referenceEntries){
				boolean found = false;
				for(FavoriteListEntryDto refDto : tmpCol_referenceEntries){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_referenceEntries.add(ref);
			}
			for(FavoriteListEntry ref : remDto_referenceEntries)
				col_referenceEntries.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_referenceEntries, "referenceEntries");

			/* merge or add data */
			List<FavoriteListEntry> new_col_referenceEntries = new ArrayList<FavoriteListEntry>();
			for(FavoriteListEntryDto refDto : tmpCol_referenceEntries){
				boolean found = false;
				for(FavoriteListEntry ref : col_referenceEntries){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_referenceEntries.add((FavoriteListEntry) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_referenceEntries.add((FavoriteListEntry) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(referenceEntries) ");
			}
			poso.setReferenceEntries(new_col_referenceEntries);
		}

	}

	public void mergeUnmanagedPoso(FavoriteListDto dto, final FavoriteList poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(FavoriteListDto dto, final FavoriteList poso)  throws ExpectedException {
		/*  set referenceEntries */
		final List<FavoriteListEntry> col_referenceEntries = new ArrayList<FavoriteListEntry>();
		/* load new data from dto */
		Collection<FavoriteListEntryDto> tmpCol_referenceEntries = dto.getReferenceEntries();

		/* merge or add data */
		for(FavoriteListEntryDto refDto : tmpCol_referenceEntries){
			col_referenceEntries.add((FavoriteListEntry) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setReferenceEntries(col_referenceEntries);

	}

	protected void mergeProxy2UnmanagedPoso(FavoriteListDto dto, final FavoriteList poso)  throws ExpectedException {
		/*  set referenceEntries */
		if(dto.isReferenceEntriesModified()){
			final List<FavoriteListEntry> col_referenceEntries = new ArrayList<FavoriteListEntry>();
			/* load new data from dto */
			Collection<FavoriteListEntryDto> tmpCol_referenceEntries = null;
			tmpCol_referenceEntries = dto.getReferenceEntries();

			/* merge or add data */
			for(FavoriteListEntryDto refDto : tmpCol_referenceEntries){
				col_referenceEntries.add((FavoriteListEntry) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setReferenceEntries(col_referenceEntries);
		}

	}

	public FavoriteList loadAndMergePoso(FavoriteListDto dto)  throws ExpectedException {
		FavoriteList poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(FavoriteListDto dto, FavoriteList poso)  {
	}


	public void postProcessCreateUnmanaged(FavoriteListDto dto, FavoriteList poso)  {
	}


	public void postProcessLoad(FavoriteListDto dto, FavoriteList poso)  {
	}


	public void postProcessMerge(FavoriteListDto dto, FavoriteList poso)  {
	}


	public void postProcessInstantiate(FavoriteList poso)  {
	}



}
