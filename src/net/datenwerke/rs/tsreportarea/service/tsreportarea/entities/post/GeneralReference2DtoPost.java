package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.post;

import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.decorator.TsDiskGeneralReferenceDtoDec;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskGeneralReference;

public class GeneralReference2DtoPost implements Poso2DtoPostProcessor<TsDiskGeneralReference, TsDiskGeneralReferenceDto> {

	@Override
	public void dtoCreated(TsDiskGeneralReference poso,
			TsDiskGeneralReferenceDto dto) {
		((TsDiskGeneralReferenceDtoDec)dto).setReferenceLastUpdated(poso.getReferenceLastUpdated());
	}

	@Override
	public void dtoInstantiated(TsDiskGeneralReference arg0,
			TsDiskGeneralReferenceDto arg1) {
		
	}


}
