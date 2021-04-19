package net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTextDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTextDtoDec;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatText;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.ColumnFormatText2DtoGenerator;

/**
 * Poso2DtoGenerator for ColumnFormatText
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ColumnFormatText2DtoGenerator implements Poso2DtoGenerator<ColumnFormatText,ColumnFormatTextDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ColumnFormatText2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ColumnFormatTextDtoDec instantiateDto(ColumnFormatText poso)  {
		ColumnFormatTextDtoDec dto = new ColumnFormatTextDtoDec();
		return dto;
	}

	public ColumnFormatTextDtoDec createDto(ColumnFormatText poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ColumnFormatTextDtoDec dto = new ColumnFormatTextDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}

		return dto;
	}


}
