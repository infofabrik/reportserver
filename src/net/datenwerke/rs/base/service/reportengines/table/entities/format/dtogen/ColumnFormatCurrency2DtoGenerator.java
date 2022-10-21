package net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatCurrencyDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.CurrencyTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatCurrencyDtoDec;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatCurrency;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.ColumnFormatCurrency2DtoGenerator;

/**
 * Poso2DtoGenerator for ColumnFormatCurrency
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ColumnFormatCurrency2DtoGenerator implements Poso2DtoGenerator<ColumnFormatCurrency,ColumnFormatCurrencyDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ColumnFormatCurrency2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ColumnFormatCurrencyDtoDec instantiateDto(ColumnFormatCurrency poso)  {
		ColumnFormatCurrencyDtoDec dto = new ColumnFormatCurrencyDtoDec();
		return dto;
	}

	public ColumnFormatCurrencyDtoDec createDto(ColumnFormatCurrency poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ColumnFormatCurrencyDtoDec dto = new ColumnFormatCurrencyDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set currencyType */
			Object tmpDtoCurrencyTypeDtogetCurrencyType = dtoServiceProvider.get().createDto(poso.getCurrencyType(), referenced, referenced);
			dto.setCurrencyType((CurrencyTypeDto)tmpDtoCurrencyTypeDtogetCurrencyType);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoCurrencyTypeDtogetCurrencyType, poso.getCurrencyType(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setCurrencyType((CurrencyTypeDto)refDto);
				}
			});

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
