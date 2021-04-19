package net.datenwerke.rs.search.service.search.results.post;

import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoPostProcessor;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.search.client.search.dto.SearchFilterDto;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchFilterDtoDec;
import net.datenwerke.rs.search.service.search.results.SearchFilter;

import com.google.inject.Inject;

public class Dto2SearchFilterPost implements Dto2PosoPostProcessor<SearchFilterDto, SearchFilter> {

	private final DtoService dtoService;
	
	@Inject
	public Dto2SearchFilterPost(DtoService dtoService) {
		this.dtoService = dtoService;
	}

	@Override
	public void posoCreated(SearchFilterDto d, SearchFilter poso) {
		SearchFilterDtoDec dto = (SearchFilterDtoDec) d;
		Dto base = dto.getBaseType();
		if(null != base){
			Object fin = dtoService.createDto(base);
			poso.setBaseType(fin.getClass());
		}
	}

	@Override
	public void posoCreatedUnmanaged(SearchFilterDto arg0, SearchFilter arg1) {
	}

	@Override
	public void posoInstantiated(SearchFilter arg0) {
	}

	@Override
	public void posoLoaded(SearchFilterDto arg0, SearchFilter arg1) {
	}

	@Override
	public void posoMerged(SearchFilterDto arg0, SearchFilter arg1) {
	}

}
