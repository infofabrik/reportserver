package net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatNumberDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatNumberDtoDec;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatNumber;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.ColumnFormatNumber2DtoGenerator;

/**
 * Poso2DtoGenerator for ColumnFormatNumber
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ColumnFormatNumber2DtoGenerator implements Poso2DtoGenerator<ColumnFormatNumber,ColumnFormatNumberDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ColumnFormatNumber2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ColumnFormatNumberDtoDec instantiateDto(ColumnFormatNumber poso)  {
		ColumnFormatNumberDtoDec dto = new ColumnFormatNumberDtoDec();
		return dto;
	}

	public ColumnFormatNumberDtoDec createDto(ColumnFormatNumber poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ColumnFormatNumberDtoDec dto = new ColumnFormatNumberDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set numberOfDecimalPlaces */
			dto.setNumberOfDecimalPlaces(poso.getNumberOfDecimalPlaces() );

			/*  set thousandSeparator */
			dto.setThousandSeparator(poso.isThousandSeparator() );

			/*  set type */
			Object tmpDtoNumberTypeDtogetType = dtoServiceProvider.get().createDto(poso.getType(), referenced, referenced);
			dto.setType((NumberTypeDto)tmpDtoNumberTypeDtogetType);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoNumberTypeDtogetType, poso.getType(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setType((NumberTypeDto)refDto);
				}
			});

		}

		return dto;
	}


}
