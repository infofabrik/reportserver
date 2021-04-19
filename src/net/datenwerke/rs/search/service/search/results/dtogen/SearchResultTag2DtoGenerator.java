package net.datenwerke.rs.search.service.search.results.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto;
import net.datenwerke.rs.search.service.search.results.SearchResultTag;
import net.datenwerke.rs.search.service.search.results.dtogen.SearchResultTag2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for SearchResultTag
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class SearchResultTag2DtoGenerator implements Poso2DtoGenerator<SearchResultTag,SearchResultTagDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public SearchResultTag2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public SearchResultTagDto instantiateDto(SearchResultTag poso)  {
		SearchResultTagDto dto = new SearchResultTagDto();
		return dto;
	}

	public SearchResultTagDto createDto(SearchResultTag poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final SearchResultTagDto dto = new SearchResultTagDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set display */
			dto.setDisplay(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDisplay(),8192)));

			/*  set type */
			Object tmpDtoSearchResultTagTypeDtogetType = dtoServiceProvider.get().createDto(poso.getType(), here, referenced);
			dto.setType((SearchResultTagTypeDto)tmpDtoSearchResultTagTypeDtogetType);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoSearchResultTagTypeDtogetType, poso.getType(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setType((SearchResultTagTypeDto)refDto);
				}
			});

			/*  set value */
			dto.setValue(StringEscapeUtils.escapeXml(StringUtils.left(poso.getValue(),8192)));

		}

		return dto;
	}


}
