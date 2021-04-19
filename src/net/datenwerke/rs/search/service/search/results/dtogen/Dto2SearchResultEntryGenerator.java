package net.datenwerke.rs.search.service.search.results.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
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
import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.service.search.results.SearchResultEntry;
import net.datenwerke.rs.search.service.search.results.SearchResultTag;
import net.datenwerke.rs.search.service.search.results.dtogen.Dto2SearchResultEntryGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for SearchResultEntry
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2SearchResultEntryGenerator implements Dto2PosoGenerator<SearchResultEntryDto,SearchResultEntry> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2SearchResultEntryGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public SearchResultEntry loadPoso(SearchResultEntryDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public SearchResultEntry instantiatePoso()  {
		SearchResultEntry poso = new SearchResultEntry();
		return poso;
	}

	public SearchResultEntry createPoso(SearchResultEntryDto dto)  throws ExpectedException {
		SearchResultEntry poso = new SearchResultEntry();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public SearchResultEntry createUnmanagedPoso(SearchResultEntryDto dto)  throws ExpectedException {
		SearchResultEntry poso = new SearchResultEntry();

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

	public void mergePoso(SearchResultEntryDto dto, final SearchResultEntry poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(SearchResultEntryDto dto, final SearchResultEntry poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set iconSmall */
		poso.setIconSmall(dto.getIconSmall() );

		/*  set lastModified */
		poso.setLastModified(dto.getLastModified() );

		/*  set links */
		final List<HistoryLink> col_links = new ArrayList<HistoryLink>();
		/* load new data from dto */
		Collection<HistoryLinkDto> tmpCol_links = dto.getLinks();

		for(HistoryLinkDto refDto : tmpCol_links){
			col_links.add((HistoryLink) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setLinks(col_links);

		/*  set objectId */
		poso.setObjectId(dto.getObjectId() );

		/*  set tags */
		final Set<SearchResultTag> col_tags = new HashSet<SearchResultTag>();
		/* load new data from dto */
		Collection<SearchResultTagDto> tmpCol_tags = dto.getTags();

		for(SearchResultTagDto refDto : tmpCol_tags){
			col_tags.add((SearchResultTag) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setTags(col_tags);

		/*  set title */
		poso.setTitle(dto.getTitle() );

	}

	protected void mergeProxy2Poso(SearchResultEntryDto dto, final SearchResultEntry poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set iconSmall */
		if(dto.isIconSmallModified()){
			poso.setIconSmall(dto.getIconSmall() );
		}

		/*  set lastModified */
		if(dto.isLastModifiedModified()){
			poso.setLastModified(dto.getLastModified() );
		}

		/*  set links */
		if(dto.isLinksModified()){
			final List<HistoryLink> col_links = new ArrayList<HistoryLink>();
			/* load new data from dto */
			Collection<HistoryLinkDto> tmpCol_links = null;
			tmpCol_links = dto.getLinks();

			for(HistoryLinkDto refDto : tmpCol_links){
				col_links.add((HistoryLink) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setLinks(col_links);
		}

		/*  set objectId */
		if(dto.isObjectIdModified()){
			poso.setObjectId(dto.getObjectId() );
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

		/*  set title */
		if(dto.isTitleModified()){
			poso.setTitle(dto.getTitle() );
		}

	}

	public void mergeUnmanagedPoso(SearchResultEntryDto dto, final SearchResultEntry poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(SearchResultEntryDto dto, final SearchResultEntry poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set iconSmall */
		poso.setIconSmall(dto.getIconSmall() );

		/*  set lastModified */
		poso.setLastModified(dto.getLastModified() );

		/*  set links */
		final List<HistoryLink> col_links = new ArrayList<HistoryLink>();
		/* load new data from dto */
		Collection<HistoryLinkDto> tmpCol_links = dto.getLinks();

		for(HistoryLinkDto refDto : tmpCol_links){
			col_links.add((HistoryLink) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setLinks(col_links);

		/*  set objectId */
		poso.setObjectId(dto.getObjectId() );

		/*  set tags */
		final Set<SearchResultTag> col_tags = new HashSet<SearchResultTag>();
		/* load new data from dto */
		Collection<SearchResultTagDto> tmpCol_tags = dto.getTags();

		for(SearchResultTagDto refDto : tmpCol_tags){
			col_tags.add((SearchResultTag) dtoServiceProvider.get().createPoso(refDto));
		}
		poso.setTags(col_tags);

		/*  set title */
		poso.setTitle(dto.getTitle() );

	}

	protected void mergeProxy2UnmanagedPoso(SearchResultEntryDto dto, final SearchResultEntry poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set iconSmall */
		if(dto.isIconSmallModified()){
			poso.setIconSmall(dto.getIconSmall() );
		}

		/*  set lastModified */
		if(dto.isLastModifiedModified()){
			poso.setLastModified(dto.getLastModified() );
		}

		/*  set links */
		if(dto.isLinksModified()){
			final List<HistoryLink> col_links = new ArrayList<HistoryLink>();
			/* load new data from dto */
			Collection<HistoryLinkDto> tmpCol_links = null;
			tmpCol_links = dto.getLinks();

			for(HistoryLinkDto refDto : tmpCol_links){
				col_links.add((HistoryLink) dtoServiceProvider.get().createPoso(refDto));
			}
			poso.setLinks(col_links);
		}

		/*  set objectId */
		if(dto.isObjectIdModified()){
			poso.setObjectId(dto.getObjectId() );
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

		/*  set title */
		if(dto.isTitleModified()){
			poso.setTitle(dto.getTitle() );
		}

	}

	public SearchResultEntry loadAndMergePoso(SearchResultEntryDto dto)  throws ExpectedException {
		SearchResultEntry poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(SearchResultEntryDto dto, SearchResultEntry poso)  {
	}


	public void postProcessCreateUnmanaged(SearchResultEntryDto dto, SearchResultEntry poso)  {
	}


	public void postProcessLoad(SearchResultEntryDto dto, SearchResultEntry poso)  {
	}


	public void postProcessMerge(SearchResultEntryDto dto, SearchResultEntry poso)  {
	}


	public void postProcessInstantiate(SearchResultEntry poso)  {
	}



}
