package net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Dto2FilterGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for Filter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2FilterGenerator implements Dto2PosoGenerator<FilterDto,Filter> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2FilterGenerator(
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

	public Filter loadPoso(FilterDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		Filter poso = entityManager.find(Filter.class, id);
		return poso;
	}

	public Filter instantiatePoso()  {
		Filter poso = new Filter();
		return poso;
	}

	public Filter createPoso(FilterDto dto)  throws ExpectedException {
		Filter poso = new Filter();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public Filter createUnmanagedPoso(FilterDto dto)  throws ExpectedException {
		Filter poso = new Filter();

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

	public void mergePoso(FilterDto dto, final Filter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(FilterDto dto, final Filter poso)  throws ExpectedException {
		/*  set caseSensitive */
		poso.setCaseSensitive(dto.isCaseSensitive() );

		/*  set excludeRanges */
		final List<FilterRange> col_excludeRanges = new ArrayList<FilterRange>();
		/* load new data from dto */
		Collection<FilterRangeDto> tmpCol_excludeRanges = dto.getExcludeRanges();

		/* load current data from poso */
		if(null != poso.getExcludeRanges())
			col_excludeRanges.addAll(poso.getExcludeRanges());

		/* remove non existing data */
		List<FilterRange> remDto_excludeRanges = new ArrayList<FilterRange>();
		for(FilterRange ref : col_excludeRanges){
			boolean found = false;
			for(FilterRangeDto refDto : tmpCol_excludeRanges){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_excludeRanges.add(ref);
		}
		for(FilterRange ref : remDto_excludeRanges)
			col_excludeRanges.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_excludeRanges, "excludeRanges");

		/* merge or add data */
		List<FilterRange> new_col_excludeRanges = new ArrayList<FilterRange>();
		for(FilterRangeDto refDto : tmpCol_excludeRanges){
			boolean found = false;
			for(FilterRange ref : col_excludeRanges){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_excludeRanges.add((FilterRange) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_excludeRanges.add((FilterRange) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(excludeRanges) ");
		}
		poso.setExcludeRanges(new_col_excludeRanges);

		/*  set excludeValues */
		poso.setExcludeValues(dto.getExcludeValues() );

		/*  set includeRanges */
		final List<FilterRange> col_includeRanges = new ArrayList<FilterRange>();
		/* load new data from dto */
		Collection<FilterRangeDto> tmpCol_includeRanges = dto.getIncludeRanges();

		/* load current data from poso */
		if(null != poso.getIncludeRanges())
			col_includeRanges.addAll(poso.getIncludeRanges());

		/* remove non existing data */
		List<FilterRange> remDto_includeRanges = new ArrayList<FilterRange>();
		for(FilterRange ref : col_includeRanges){
			boolean found = false;
			for(FilterRangeDto refDto : tmpCol_includeRanges){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_includeRanges.add(ref);
		}
		for(FilterRange ref : remDto_includeRanges)
			col_includeRanges.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_includeRanges, "includeRanges");

		/* merge or add data */
		List<FilterRange> new_col_includeRanges = new ArrayList<FilterRange>();
		for(FilterRangeDto refDto : tmpCol_includeRanges){
			boolean found = false;
			for(FilterRange ref : col_includeRanges){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_includeRanges.add((FilterRange) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_includeRanges.add((FilterRange) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(includeRanges) ");
		}
		poso.setIncludeRanges(new_col_includeRanges);

		/*  set includeValues */
		poso.setIncludeValues(dto.getIncludeValues() );

	}

	protected void mergeProxy2Poso(FilterDto dto, final Filter poso)  throws ExpectedException {
		/*  set caseSensitive */
		if(dto.isCaseSensitiveModified()){
			poso.setCaseSensitive(dto.isCaseSensitive() );
		}

		/*  set excludeRanges */
		if(dto.isExcludeRangesModified()){
			final List<FilterRange> col_excludeRanges = new ArrayList<FilterRange>();
			/* load new data from dto */
			Collection<FilterRangeDto> tmpCol_excludeRanges = null;
			tmpCol_excludeRanges = dto.getExcludeRanges();

			/* load current data from poso */
			if(null != poso.getExcludeRanges())
				col_excludeRanges.addAll(poso.getExcludeRanges());

			/* remove non existing data */
			List<FilterRange> remDto_excludeRanges = new ArrayList<FilterRange>();
			for(FilterRange ref : col_excludeRanges){
				boolean found = false;
				for(FilterRangeDto refDto : tmpCol_excludeRanges){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_excludeRanges.add(ref);
			}
			for(FilterRange ref : remDto_excludeRanges)
				col_excludeRanges.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_excludeRanges, "excludeRanges");

			/* merge or add data */
			List<FilterRange> new_col_excludeRanges = new ArrayList<FilterRange>();
			for(FilterRangeDto refDto : tmpCol_excludeRanges){
				boolean found = false;
				for(FilterRange ref : col_excludeRanges){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_excludeRanges.add((FilterRange) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_excludeRanges.add((FilterRange) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(excludeRanges) ");
			}
			poso.setExcludeRanges(new_col_excludeRanges);
		}

		/*  set excludeValues */
		if(dto.isExcludeValuesModified()){
			poso.setExcludeValues(dto.getExcludeValues() );
		}

		/*  set includeRanges */
		if(dto.isIncludeRangesModified()){
			final List<FilterRange> col_includeRanges = new ArrayList<FilterRange>();
			/* load new data from dto */
			Collection<FilterRangeDto> tmpCol_includeRanges = null;
			tmpCol_includeRanges = dto.getIncludeRanges();

			/* load current data from poso */
			if(null != poso.getIncludeRanges())
				col_includeRanges.addAll(poso.getIncludeRanges());

			/* remove non existing data */
			List<FilterRange> remDto_includeRanges = new ArrayList<FilterRange>();
			for(FilterRange ref : col_includeRanges){
				boolean found = false;
				for(FilterRangeDto refDto : tmpCol_includeRanges){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_includeRanges.add(ref);
			}
			for(FilterRange ref : remDto_includeRanges)
				col_includeRanges.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_includeRanges, "includeRanges");

			/* merge or add data */
			List<FilterRange> new_col_includeRanges = new ArrayList<FilterRange>();
			for(FilterRangeDto refDto : tmpCol_includeRanges){
				boolean found = false;
				for(FilterRange ref : col_includeRanges){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_includeRanges.add((FilterRange) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_includeRanges.add((FilterRange) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(includeRanges) ");
			}
			poso.setIncludeRanges(new_col_includeRanges);
		}

		/*  set includeValues */
		if(dto.isIncludeValuesModified()){
			poso.setIncludeValues(dto.getIncludeValues() );
		}

	}

	public void mergeUnmanagedPoso(FilterDto dto, final Filter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(FilterDto dto, final Filter poso)  throws ExpectedException {
		/*  set caseSensitive */
		poso.setCaseSensitive(dto.isCaseSensitive() );

		/*  set excludeRanges */
		final List<FilterRange> col_excludeRanges = new ArrayList<FilterRange>();
		/* load new data from dto */
		Collection<FilterRangeDto> tmpCol_excludeRanges = dto.getExcludeRanges();

		/* merge or add data */
		for(FilterRangeDto refDto : tmpCol_excludeRanges){
			col_excludeRanges.add((FilterRange) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setExcludeRanges(col_excludeRanges);

		/*  set excludeValues */
		poso.setExcludeValues(dto.getExcludeValues() );

		/*  set includeRanges */
		final List<FilterRange> col_includeRanges = new ArrayList<FilterRange>();
		/* load new data from dto */
		Collection<FilterRangeDto> tmpCol_includeRanges = dto.getIncludeRanges();

		/* merge or add data */
		for(FilterRangeDto refDto : tmpCol_includeRanges){
			col_includeRanges.add((FilterRange) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setIncludeRanges(col_includeRanges);

		/*  set includeValues */
		poso.setIncludeValues(dto.getIncludeValues() );

	}

	protected void mergeProxy2UnmanagedPoso(FilterDto dto, final Filter poso)  throws ExpectedException {
		/*  set caseSensitive */
		if(dto.isCaseSensitiveModified()){
			poso.setCaseSensitive(dto.isCaseSensitive() );
		}

		/*  set excludeRanges */
		if(dto.isExcludeRangesModified()){
			final List<FilterRange> col_excludeRanges = new ArrayList<FilterRange>();
			/* load new data from dto */
			Collection<FilterRangeDto> tmpCol_excludeRanges = null;
			tmpCol_excludeRanges = dto.getExcludeRanges();

			/* merge or add data */
			for(FilterRangeDto refDto : tmpCol_excludeRanges){
				col_excludeRanges.add((FilterRange) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setExcludeRanges(col_excludeRanges);
		}

		/*  set excludeValues */
		if(dto.isExcludeValuesModified()){
			poso.setExcludeValues(dto.getExcludeValues() );
		}

		/*  set includeRanges */
		if(dto.isIncludeRangesModified()){
			final List<FilterRange> col_includeRanges = new ArrayList<FilterRange>();
			/* load new data from dto */
			Collection<FilterRangeDto> tmpCol_includeRanges = null;
			tmpCol_includeRanges = dto.getIncludeRanges();

			/* merge or add data */
			for(FilterRangeDto refDto : tmpCol_includeRanges){
				col_includeRanges.add((FilterRange) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setIncludeRanges(col_includeRanges);
		}

		/*  set includeValues */
		if(dto.isIncludeValuesModified()){
			poso.setIncludeValues(dto.getIncludeValues() );
		}

	}

	public Filter loadAndMergePoso(FilterDto dto)  throws ExpectedException {
		Filter poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(FilterDto dto, Filter poso)  {
	}


	public void postProcessCreateUnmanaged(FilterDto dto, Filter poso)  {
	}


	public void postProcessLoad(FilterDto dto, Filter poso)  {
	}


	public void postProcessMerge(FilterDto dto, Filter poso)  {
	}


	public void postProcessInstantiate(Filter poso)  {
	}



}
