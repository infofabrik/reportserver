package net.datenwerke.rs.search.service.search.results.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.search.client.search.dto.SearchResultTagTypeDto;
import net.datenwerke.rs.search.service.search.results.SearchResultTagType;
import net.datenwerke.rs.search.service.search.results.dtogen.SearchResultTagType2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for SearchResultTagType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class SearchResultTagType2DtoGenerator implements Poso2DtoGenerator<SearchResultTagType,SearchResultTagTypeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public SearchResultTagType2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public SearchResultTagTypeDto instantiateDto(SearchResultTagType poso)  {
		SearchResultTagTypeDto dto = new SearchResultTagTypeDto();
		return dto;
	}

	public SearchResultTagTypeDto createDto(SearchResultTagType poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final SearchResultTagTypeDto dto = new SearchResultTagTypeDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set display */
			dto.setDisplay(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDisplay(),8192)));

			/*  set type */
			dto.setType(StringEscapeUtils.escapeXml(StringUtils.left(poso.getType(),8192)));

		}

		return dto;
	}


}
