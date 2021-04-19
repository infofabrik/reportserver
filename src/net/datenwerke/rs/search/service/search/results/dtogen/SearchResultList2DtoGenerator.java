package net.datenwerke.rs.search.service.search.results.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchResultListDtoDec;
import net.datenwerke.rs.search.service.search.results.SearchResultEntry;
import net.datenwerke.rs.search.service.search.results.SearchResultList;
import net.datenwerke.rs.search.service.search.results.SearchResultTag;
import net.datenwerke.rs.search.service.search.results.dtogen.SearchResultList2DtoGenerator;

/**
 * Poso2DtoGenerator for SearchResultList
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class SearchResultList2DtoGenerator implements Poso2DtoGenerator<SearchResultList,SearchResultListDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public SearchResultList2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public SearchResultListDtoDec instantiateDto(SearchResultList poso)  {
		SearchResultListDtoDec dto = new SearchResultListDtoDec();
		return dto;
	}

	public SearchResultListDtoDec createDto(SearchResultList poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final SearchResultListDtoDec dto = new SearchResultListDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set data */
			final List<SearchResultEntryDto> col_data = new ArrayList<SearchResultEntryDto>();
			if( null != poso.getData()){
				for(SearchResultEntry refPoso : poso.getData()){
					final Object tmpDtoSearchResultEntryDtogetData = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_data.add((SearchResultEntryDto) tmpDtoSearchResultEntryDtogetData);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoSearchResultEntryDtogetData, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (data)");
							int tmp_index = col_data.indexOf(tmpDtoSearchResultEntryDtogetData);
							col_data.set(tmp_index,(SearchResultEntryDto) dto);
						}
					});
				}
				dto.setData(col_data);
			}

			/*  set offset */
			dto.setOffset(poso.getOffset() );

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

			/*  set totalLength */
			dto.setTotalLength(poso.getTotalLength() );

		}

		return dto;
	}


}
