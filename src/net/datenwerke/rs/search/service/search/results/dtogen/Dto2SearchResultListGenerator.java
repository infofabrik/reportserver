package net.datenwerke.rs.search.service.search.results.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.service.search.results.SearchResultEntry;
import net.datenwerke.rs.search.service.search.results.SearchResultList;
import net.datenwerke.rs.search.service.search.results.SearchResultTag;
import net.datenwerke.rs.search.service.search.results.dtogen.Dto2SearchResultListGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for SearchResultList
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2SearchResultListGenerator implements Dto2PosoGenerator<SearchResultListDto,SearchResultList> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2SearchResultListGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public SearchResultList loadPoso(SearchResultListDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public SearchResultList instantiatePoso()  {
		SearchResultList poso = new SearchResultList();
		return poso;
	}

	public SearchResultList createPoso(SearchResultListDto dto)  throws ExpectedException {
		SearchResultList poso = new SearchResultList();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public SearchResultList createUnmanagedPoso(SearchResultListDto dto)  throws ExpectedException {
		SearchResultList poso = new SearchResultList();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(SearchResultListDto dto, final SearchResultList poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(SearchResultListDto dto, final SearchResultList poso)  throws ExpectedException {
		/*  set data */
		final List<SearchResultEntry> col_data = new ArrayList<SearchResultEntry>();
		/* load new data from dto */
		Collection<SearchResultEntryDto> tmpCol_data = dto.getData();

		/* load current data from poso */
		if(null != poso.getData())
			col_data.addAll(poso.getData());

		/* remove non existing data */
		List<SearchResultEntry> remDto_data = new ArrayList<SearchResultEntry>();
		for(SearchResultEntry ref : col_data){
			boolean found = false;
			for(SearchResultEntryDto refDto : tmpCol_data){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_data.add(ref);
		}
		for(SearchResultEntry ref : remDto_data)
			col_data.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_data, "data");

		/* merge or add data */
		List<SearchResultEntry> new_col_data = new ArrayList<SearchResultEntry>();
		for(SearchResultEntryDto refDto : tmpCol_data){
			boolean found = false;
			for(SearchResultEntry ref : col_data){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_data.add((SearchResultEntry) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_data.add((SearchResultEntry) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(data) ");
		}
		poso.setData(new_col_data);

		/*  set offset */
		try{
			poso.setOffset(dto.getOffset() );
		} catch(NullPointerException e){
		}

		/*  set tags */
		final Set<SearchResultTag> col_tags = new HashSet<SearchResultTag>();
		/* load new data from dto */
		Collection<SearchResultTagDto> tmpCol_tags = dto.getTags();

		for(SearchResultTagDto refDto : tmpCol_tags){
			col_tags.add((SearchResultTag) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setTags(col_tags);

		/*  set totalLength */
		try{
			poso.setTotalLength(dto.getTotalLength() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(SearchResultListDto dto, final SearchResultList poso)  throws ExpectedException {
		/*  set data */
		if(dto.isDataModified()){
			final List<SearchResultEntry> col_data = new ArrayList<SearchResultEntry>();
			/* load new data from dto */
			Collection<SearchResultEntryDto> tmpCol_data = null;
			tmpCol_data = dto.getData();

			/* load current data from poso */
			if(null != poso.getData())
				col_data.addAll(poso.getData());

			/* remove non existing data */
			List<SearchResultEntry> remDto_data = new ArrayList<SearchResultEntry>();
			for(SearchResultEntry ref : col_data){
				boolean found = false;
				for(SearchResultEntryDto refDto : tmpCol_data){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_data.add(ref);
			}
			for(SearchResultEntry ref : remDto_data)
				col_data.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_data, "data");

			/* merge or add data */
			List<SearchResultEntry> new_col_data = new ArrayList<SearchResultEntry>();
			for(SearchResultEntryDto refDto : tmpCol_data){
				boolean found = false;
				for(SearchResultEntry ref : col_data){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_data.add((SearchResultEntry) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_data.add((SearchResultEntry) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(data) ");
			}
			poso.setData(new_col_data);
		}

		/*  set offset */
		if(dto.isOffsetModified()){
			try{
				poso.setOffset(dto.getOffset() );
			} catch(NullPointerException e){
			}
		}

		/*  set tags */
		if(dto.isTagsModified()){
			final Set<SearchResultTag> col_tags = new HashSet<SearchResultTag>();
			/* load new data from dto */
			Collection<SearchResultTagDto> tmpCol_tags = null;
			tmpCol_tags = dto.getTags();

			for(SearchResultTagDto refDto : tmpCol_tags){
				col_tags.add((SearchResultTag) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setTags(col_tags);
		}

		/*  set totalLength */
		if(dto.isTotalLengthModified()){
			try{
				poso.setTotalLength(dto.getTotalLength() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(SearchResultListDto dto, final SearchResultList poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(SearchResultListDto dto, final SearchResultList poso)  throws ExpectedException {
		/*  set data */
		final List<SearchResultEntry> col_data = new ArrayList<SearchResultEntry>();
		/* load new data from dto */
		Collection<SearchResultEntryDto> tmpCol_data = dto.getData();

		/* merge or add data */
		for(SearchResultEntryDto refDto : tmpCol_data){
			col_data.add((SearchResultEntry) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setData(col_data);

		/*  set offset */
		try{
			poso.setOffset(dto.getOffset() );
		} catch(NullPointerException e){
		}

		/*  set tags */
		final Set<SearchResultTag> col_tags = new HashSet<SearchResultTag>();
		/* load new data from dto */
		Collection<SearchResultTagDto> tmpCol_tags = dto.getTags();

		for(SearchResultTagDto refDto : tmpCol_tags){
			col_tags.add((SearchResultTag) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setTags(col_tags);

		/*  set totalLength */
		try{
			poso.setTotalLength(dto.getTotalLength() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(SearchResultListDto dto, final SearchResultList poso)  throws ExpectedException {
		/*  set data */
		if(dto.isDataModified()){
			final List<SearchResultEntry> col_data = new ArrayList<SearchResultEntry>();
			/* load new data from dto */
			Collection<SearchResultEntryDto> tmpCol_data = null;
			tmpCol_data = dto.getData();

			/* merge or add data */
			for(SearchResultEntryDto refDto : tmpCol_data){
				col_data.add((SearchResultEntry) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setData(col_data);
		}

		/*  set offset */
		if(dto.isOffsetModified()){
			try{
				poso.setOffset(dto.getOffset() );
			} catch(NullPointerException e){
			}
		}

		/*  set tags */
		if(dto.isTagsModified()){
			final Set<SearchResultTag> col_tags = new HashSet<SearchResultTag>();
			/* load new data from dto */
			Collection<SearchResultTagDto> tmpCol_tags = null;
			tmpCol_tags = dto.getTags();

			for(SearchResultTagDto refDto : tmpCol_tags){
				col_tags.add((SearchResultTag) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setTags(col_tags);
		}

		/*  set totalLength */
		if(dto.isTotalLengthModified()){
			try{
				poso.setTotalLength(dto.getTotalLength() );
			} catch(NullPointerException e){
			}
		}

	}

	public SearchResultList loadAndMergePoso(SearchResultListDto dto)  throws ExpectedException {
		SearchResultList poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(SearchResultListDto dto, SearchResultList poso)  {
	}


	public void postProcessCreateUnmanaged(SearchResultListDto dto, SearchResultList poso)  {
	}


	public void postProcessLoad(SearchResultListDto dto, SearchResultList poso)  {
	}


	public void postProcessMerge(SearchResultListDto dto, SearchResultList poso)  {
	}


	public void postProcessInstantiate(SearchResultList poso)  {
	}



}
