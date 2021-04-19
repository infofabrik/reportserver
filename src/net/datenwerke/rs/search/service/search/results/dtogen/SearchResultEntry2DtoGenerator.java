package net.datenwerke.rs.search.service.search.results.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchResultEntryDtoDec;
import net.datenwerke.rs.search.service.search.results.SearchResultEntry;
import net.datenwerke.rs.search.service.search.results.SearchResultTag;
import net.datenwerke.rs.search.service.search.results.dtogen.SearchResultEntry2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for SearchResultEntry
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class SearchResultEntry2DtoGenerator implements Poso2DtoGenerator<SearchResultEntry,SearchResultEntryDtoDec> {

	private final net.datenwerke.rs.search.service.search.results.post.SearchEntry2DtoPost postProcessor_1;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public SearchResultEntry2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.rs.search.service.search.results.post.SearchEntry2DtoPost postProcessor_1	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
	}

	public SearchResultEntryDtoDec instantiateDto(SearchResultEntry poso)  {
		SearchResultEntryDtoDec dto = new SearchResultEntryDtoDec();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		return dto;
	}

	public SearchResultEntryDtoDec createDto(SearchResultEntry poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final SearchResultEntryDtoDec dto = new SearchResultEntryDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set iconSmall */
			dto.setIconSmall(StringEscapeUtils.escapeXml(StringUtils.left(poso.getIconSmall(),8192)));

			/*  set lastModified */
			dto.setLastModified(poso.getLastModified() );

			/*  set links */
			final List<HistoryLinkDto> col_links = new ArrayList<HistoryLinkDto>();
			if( null != poso.getLinks()){
				for(HistoryLink refPoso : poso.getLinks()){
					final Object tmpDtoHistoryLinkDtogetLinks = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_links.add((HistoryLinkDto) tmpDtoHistoryLinkDtogetLinks);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoHistoryLinkDtogetLinks, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (links)");
							int tmp_index = col_links.indexOf(tmpDtoHistoryLinkDtogetLinks);
							col_links.set(tmp_index,(HistoryLinkDto) dto);
						}
					});
				}
				dto.setLinks(col_links);
			}

			/*  set objectId */
			dto.setObjectId(poso.getObjectId() );

			/*  set tags */
			final Set<SearchResultTagDto> col_tags = new HashSet<SearchResultTagDto>();
			if( null != poso.getTags()){
				for(SearchResultTag refPoso : poso.getTags()){
					final Object tmpDtoSearchResultTagDtogetTags = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_tags.add((SearchResultTagDto) tmpDtoSearchResultTagDtogetTags);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoSearchResultTagDtogetTags, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (tags)");
							col_tags.remove(tmpDtoSearchResultTagDtogetTags);
							col_tags.add((SearchResultTagDto) dto);
						}
					});
				}
				dto.setTags(col_tags);
			}

			/*  set title */
			dto.setTitle(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTitle(),8192)));

		}

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);

		return dto;
	}


}
