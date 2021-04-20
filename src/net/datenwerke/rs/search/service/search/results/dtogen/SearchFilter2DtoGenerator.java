package net.datenwerke.rs.search.service.search.results.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.search.client.search.dto.SearchFilterDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec;
import net.datenwerke.rs.search.service.search.results.SearchFilter;
import net.datenwerke.rs.search.service.search.results.SearchResultTag;
import net.datenwerke.rs.search.service.search.results.dtogen.SearchFilter2DtoGenerator;

/**
 * Poso2DtoGenerator for SearchFilter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class SearchFilter2DtoGenerator implements Poso2DtoGenerator<SearchFilter,SearchFilterDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public SearchFilter2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public SearchFilterDtoDec instantiateDto(SearchFilter poso)  {
		SearchFilterDtoDec dto = new SearchFilterDtoDec();
		return dto;
	}

	public SearchFilterDtoDec createDto(SearchFilter poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final SearchFilterDtoDec dto = new SearchFilterDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set limit */
			dto.setLimit(poso.getLimit() );

			/*  set offset */
			dto.setOffset(poso.getOffset() );

			/*  set showEntriesWithUnaccessibleHistoryPath */
			dto.setShowEntriesWithUnaccessibleHistoryPath(poso.isShowEntriesWithUnaccessibleHistoryPath() );

			/*  set tags */
			final Set<SearchResultTagDto> col_tags = new HashSet<SearchResultTagDto>();
			if( null != poso.getTags()){
				for(SearchResultTag refPoso : poso.getTags()){
					final Object tmpDtoSearchResultTagDtogetTags = dtoServiceProvider.get().createDto(refPoso, referenced, referenced);
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

		}

		return dto;
	}


}
