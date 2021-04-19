package net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
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
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterBlockDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2FilterBlockGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for FilterBlock
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2FilterBlockGenerator implements Dto2PosoGenerator<FilterBlockDto,FilterBlock> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2FilterBlockGenerator(
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

	public FilterBlock loadPoso(FilterBlockDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		FilterBlock poso = entityManager.find(FilterBlock.class, id);
		return poso;
	}

	public FilterBlock instantiatePoso()  {
		FilterBlock poso = new FilterBlock();
		return poso;
	}

	public FilterBlock createPoso(FilterBlockDto dto)  throws ExpectedException {
		FilterBlock poso = new FilterBlock();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public FilterBlock createUnmanagedPoso(FilterBlockDto dto)  throws ExpectedException {
		FilterBlock poso = new FilterBlock();

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

	public void mergePoso(FilterBlockDto dto, final FilterBlock poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(FilterBlockDto dto, final FilterBlock poso)  throws ExpectedException {
		/*  set childBlocks */
		final Set<FilterBlock> col_childBlocks = new HashSet<FilterBlock>();
		/* load new data from dto */
		Collection<FilterBlockDto> tmpCol_childBlocks = dto.getChildBlocks();

		/* load current data from poso */
		if(null != poso.getChildBlocks())
			col_childBlocks.addAll(poso.getChildBlocks());

		/* remove non existing data */
		Set<FilterBlock> remDto_childBlocks = new HashSet<FilterBlock>();
		for(FilterBlock ref : col_childBlocks){
			boolean found = false;
			for(FilterBlockDto refDto : tmpCol_childBlocks){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_childBlocks.add(ref);
		}
		for(FilterBlock ref : remDto_childBlocks)
			col_childBlocks.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_childBlocks, "childBlocks");

		/* merge or add data */
		Set<FilterBlock> new_col_childBlocks = new HashSet<FilterBlock>();
		for(FilterBlockDto refDto : tmpCol_childBlocks){
			boolean found = false;
			for(FilterBlock ref : col_childBlocks){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_childBlocks.add((FilterBlock) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_childBlocks.add((FilterBlock) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				new_col_childBlocks.add((FilterBlock) dtoServiceProvider.get().loadAndMergePoso(refDto));
		}
		poso.setChildBlocks(new_col_childBlocks);

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set filters */
		final Set<FilterSpec> col_filters = new HashSet<FilterSpec>();
		/* load new data from dto */
		Collection<FilterSpecDto> tmpCol_filters = dto.getFilters();

		/* load current data from poso */
		if(null != poso.getFilters())
			col_filters.addAll(poso.getFilters());

		/* remove non existing data */
		Set<FilterSpec> remDto_filters = new HashSet<FilterSpec>();
		for(FilterSpec ref : col_filters){
			boolean found = false;
			for(FilterSpecDto refDto : tmpCol_filters){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_filters.add(ref);
		}
		for(FilterSpec ref : remDto_filters)
			col_filters.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_filters, "filters");

		/* merge or add data */
		Set<FilterSpec> new_col_filters = new HashSet<FilterSpec>();
		for(FilterSpecDto refDto : tmpCol_filters){
			boolean found = false;
			for(FilterSpec ref : col_filters){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_filters.add((FilterSpec) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_filters.add((FilterSpec) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				new_col_filters.add((FilterSpec) dtoServiceProvider.get().loadAndMergePoso(refDto));
		}
		poso.setFilters(new_col_filters);

	}

	protected void mergeProxy2Poso(FilterBlockDto dto, final FilterBlock poso)  throws ExpectedException {
		/*  set childBlocks */
		if(dto.isChildBlocksModified()){
			final Set<FilterBlock> col_childBlocks = new HashSet<FilterBlock>();
			/* load new data from dto */
			Collection<FilterBlockDto> tmpCol_childBlocks = null;
			tmpCol_childBlocks = dto.getChildBlocks();

			/* load current data from poso */
			if(null != poso.getChildBlocks())
				col_childBlocks.addAll(poso.getChildBlocks());

			/* remove non existing data */
			Set<FilterBlock> remDto_childBlocks = new HashSet<FilterBlock>();
			for(FilterBlock ref : col_childBlocks){
				boolean found = false;
				for(FilterBlockDto refDto : tmpCol_childBlocks){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_childBlocks.add(ref);
			}
			for(FilterBlock ref : remDto_childBlocks)
				col_childBlocks.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_childBlocks, "childBlocks");

			/* merge or add data */
			Set<FilterBlock> new_col_childBlocks = new HashSet<FilterBlock>();
			for(FilterBlockDto refDto : tmpCol_childBlocks){
				boolean found = false;
				for(FilterBlock ref : col_childBlocks){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_childBlocks.add((FilterBlock) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_childBlocks.add((FilterBlock) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					new_col_childBlocks.add((FilterBlock) dtoServiceProvider.get().loadAndMergePoso(refDto));
			}
			poso.setChildBlocks(new_col_childBlocks);
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set filters */
		if(dto.isFiltersModified()){
			final Set<FilterSpec> col_filters = new HashSet<FilterSpec>();
			/* load new data from dto */
			Collection<FilterSpecDto> tmpCol_filters = null;
			tmpCol_filters = dto.getFilters();

			/* load current data from poso */
			if(null != poso.getFilters())
				col_filters.addAll(poso.getFilters());

			/* remove non existing data */
			Set<FilterSpec> remDto_filters = new HashSet<FilterSpec>();
			for(FilterSpec ref : col_filters){
				boolean found = false;
				for(FilterSpecDto refDto : tmpCol_filters){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_filters.add(ref);
			}
			for(FilterSpec ref : remDto_filters)
				col_filters.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_filters, "filters");

			/* merge or add data */
			Set<FilterSpec> new_col_filters = new HashSet<FilterSpec>();
			for(FilterSpecDto refDto : tmpCol_filters){
				boolean found = false;
				for(FilterSpec ref : col_filters){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_filters.add((FilterSpec) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_filters.add((FilterSpec) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					new_col_filters.add((FilterSpec) dtoServiceProvider.get().loadAndMergePoso(refDto));
			}
			poso.setFilters(new_col_filters);
		}

	}

	public void mergeUnmanagedPoso(FilterBlockDto dto, final FilterBlock poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(FilterBlockDto dto, final FilterBlock poso)  throws ExpectedException {
		/*  set childBlocks */
		final Set<FilterBlock> col_childBlocks = new HashSet<FilterBlock>();
		/* load new data from dto */
		Collection<FilterBlockDto> tmpCol_childBlocks = dto.getChildBlocks();

		/* merge or add data */
		for(FilterBlockDto refDto : tmpCol_childBlocks){
			col_childBlocks.add((FilterBlock) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setChildBlocks(col_childBlocks);

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set filters */
		final Set<FilterSpec> col_filters = new HashSet<FilterSpec>();
		/* load new data from dto */
		Collection<FilterSpecDto> tmpCol_filters = dto.getFilters();

		/* merge or add data */
		for(FilterSpecDto refDto : tmpCol_filters){
			col_filters.add((FilterSpec) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setFilters(col_filters);

	}

	protected void mergeProxy2UnmanagedPoso(FilterBlockDto dto, final FilterBlock poso)  throws ExpectedException {
		/*  set childBlocks */
		if(dto.isChildBlocksModified()){
			final Set<FilterBlock> col_childBlocks = new HashSet<FilterBlock>();
			/* load new data from dto */
			Collection<FilterBlockDto> tmpCol_childBlocks = null;
			tmpCol_childBlocks = dto.getChildBlocks();

			/* merge or add data */
			for(FilterBlockDto refDto : tmpCol_childBlocks){
				col_childBlocks.add((FilterBlock) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setChildBlocks(col_childBlocks);
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set filters */
		if(dto.isFiltersModified()){
			final Set<FilterSpec> col_filters = new HashSet<FilterSpec>();
			/* load new data from dto */
			Collection<FilterSpecDto> tmpCol_filters = null;
			tmpCol_filters = dto.getFilters();

			/* merge or add data */
			for(FilterSpecDto refDto : tmpCol_filters){
				col_filters.add((FilterSpec) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setFilters(col_filters);
		}

	}

	public FilterBlock loadAndMergePoso(FilterBlockDto dto)  throws ExpectedException {
		FilterBlock poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(FilterBlockDto dto, FilterBlock poso)  {
	}


	public void postProcessCreateUnmanaged(FilterBlockDto dto, FilterBlock poso)  {
	}


	public void postProcessLoad(FilterBlockDto dto, FilterBlock poso)  {
	}


	public void postProcessMerge(FilterBlockDto dto, FilterBlock poso)  {
	}


	public void postProcessInstantiate(FilterBlock poso)  {
	}



}
