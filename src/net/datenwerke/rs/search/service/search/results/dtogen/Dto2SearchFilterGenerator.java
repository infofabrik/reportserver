package net.datenwerke.rs.search.service.search.results.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.search.client.search.dto.SearchFilterDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.service.search.results.SearchFilter;
import net.datenwerke.rs.search.service.search.results.SearchResultTag;
import net.datenwerke.rs.search.service.search.results.dtogen.Dto2SearchFilterGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for SearchFilter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2SearchFilterGenerator implements Dto2PosoGenerator<SearchFilterDto,SearchFilter> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.rs.search.service.search.results.post.Dto2SearchFilterPost postProcessor_1;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2SearchFilterGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.rs.search.service.search.results.post.Dto2SearchFilterPost postProcessor_1,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
		this.reflectionService = reflectionService;
	}

	public SearchFilter loadPoso(SearchFilterDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public SearchFilter instantiatePoso()  {
		SearchFilter poso = new SearchFilter();
		return poso;
	}

	public SearchFilter createPoso(SearchFilterDto dto)  throws ExpectedException {
		SearchFilter poso = new SearchFilter();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public SearchFilter createUnmanagedPoso(SearchFilterDto dto)  throws ExpectedException {
		SearchFilter poso = new SearchFilter();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(SearchFilterDto dto, final SearchFilter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(SearchFilterDto dto, final SearchFilter poso)  throws ExpectedException {
		/*  set limit */
		try{
			poso.setLimit(dto.getLimit() );
		} catch(NullPointerException e){
		}

		/*  set offset */
		try{
			poso.setOffset(dto.getOffset() );
		} catch(NullPointerException e){
		}

		/*  set showEntriesWithUnaccessibleHistoryPath */
		try{
			poso.setShowEntriesWithUnaccessibleHistoryPath(dto.isShowEntriesWithUnaccessibleHistoryPath() );
		} catch(NullPointerException e){
		}

		/*  set tags */
		final Set<SearchResultTag> col_tags = new HashSet<SearchResultTag>();
		/* load new data from dto */
		Collection<SearchResultTagDto> tmpCol_tags = dto.getTags();

		for(SearchResultTagDto refDto : tmpCol_tags){
			if(null != refDto )
				col_tags.add((SearchResultTag) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setTags(col_tags);

	}

	protected void mergeProxy2Poso(SearchFilterDto dto, final SearchFilter poso)  throws ExpectedException {
		/*  set limit */
		if(dto.isLimitModified()){
			try{
				poso.setLimit(dto.getLimit() );
			} catch(NullPointerException e){
			}
		}

		/*  set offset */
		if(dto.isOffsetModified()){
			try{
				poso.setOffset(dto.getOffset() );
			} catch(NullPointerException e){
			}
		}

		/*  set showEntriesWithUnaccessibleHistoryPath */
		if(dto.isShowEntriesWithUnaccessibleHistoryPathModified()){
			try{
				poso.setShowEntriesWithUnaccessibleHistoryPath(dto.isShowEntriesWithUnaccessibleHistoryPath() );
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
				if(null != refDto )
					col_tags.add((SearchResultTag) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setTags(col_tags);
		}

	}

	public void mergeUnmanagedPoso(SearchFilterDto dto, final SearchFilter poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(SearchFilterDto dto, final SearchFilter poso)  throws ExpectedException {
		/*  set limit */
		try{
			poso.setLimit(dto.getLimit() );
		} catch(NullPointerException e){
		}

		/*  set offset */
		try{
			poso.setOffset(dto.getOffset() );
		} catch(NullPointerException e){
		}

		/*  set showEntriesWithUnaccessibleHistoryPath */
		try{
			poso.setShowEntriesWithUnaccessibleHistoryPath(dto.isShowEntriesWithUnaccessibleHistoryPath() );
		} catch(NullPointerException e){
		}

		/*  set tags */
		final Set<SearchResultTag> col_tags = new HashSet<SearchResultTag>();
		/* load new data from dto */
		Collection<SearchResultTagDto> tmpCol_tags = dto.getTags();

		for(SearchResultTagDto refDto : tmpCol_tags){
			if(null != refDto )
				col_tags.add((SearchResultTag) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setTags(col_tags);

	}

	protected void mergeProxy2UnmanagedPoso(SearchFilterDto dto, final SearchFilter poso)  throws ExpectedException {
		/*  set limit */
		if(dto.isLimitModified()){
			try{
				poso.setLimit(dto.getLimit() );
			} catch(NullPointerException e){
			}
		}

		/*  set offset */
		if(dto.isOffsetModified()){
			try{
				poso.setOffset(dto.getOffset() );
			} catch(NullPointerException e){
			}
		}

		/*  set showEntriesWithUnaccessibleHistoryPath */
		if(dto.isShowEntriesWithUnaccessibleHistoryPathModified()){
			try{
				poso.setShowEntriesWithUnaccessibleHistoryPath(dto.isShowEntriesWithUnaccessibleHistoryPath() );
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
				if(null != refDto )
					col_tags.add((SearchResultTag) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setTags(col_tags);
		}

	}

	public SearchFilter loadAndMergePoso(SearchFilterDto dto)  throws ExpectedException {
		SearchFilter poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(SearchFilterDto dto, SearchFilter poso)  {

		this.postProcessor_1.posoCreated(dto, poso);
	}


	public void postProcessCreateUnmanaged(SearchFilterDto dto, SearchFilter poso)  {

		this.postProcessor_1.posoCreatedUnmanaged(dto, poso);
	}


	public void postProcessLoad(SearchFilterDto dto, SearchFilter poso)  {

		this.postProcessor_1.posoLoaded(dto, poso);
	}


	public void postProcessMerge(SearchFilterDto dto, SearchFilter poso)  {

		this.postProcessor_1.posoMerged(dto, poso);
	}


	public void postProcessInstantiate(SearchFilter poso)  {

		this.postProcessor_1.posoInstantiated(poso);
	}



}
