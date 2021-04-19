package net.datenwerke.rs.search.service.search.results.post;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchResultEntryDtoDec;
import net.datenwerke.rs.search.service.search.results.SearchResultEntry;

import com.google.inject.Inject;

public class SearchEntry2DtoPost implements Poso2DtoPostProcessor<SearchResultEntry, SearchResultEntryDto> {

	private final DtoService dtoService;
	
	@Inject
	public SearchEntry2DtoPost(DtoService dtoService) {
		this.dtoService = dtoService;
	}

	@Override
	public void dtoCreated(SearchResultEntry p, SearchResultEntryDto d) {
		if(null != p.getResultObject()){
			try{
				Object r = dtoService.createDto(p.getResultObject());
				if(r instanceof DwModel)
					((SearchResultEntryDtoDec)d).setResultObject((DwModel) r);
			}catch(Exception e){}
		}
	}

	@Override
	public void dtoInstantiated(SearchResultEntry arg0,
			SearchResultEntryDto arg1) {
		
	}

}
