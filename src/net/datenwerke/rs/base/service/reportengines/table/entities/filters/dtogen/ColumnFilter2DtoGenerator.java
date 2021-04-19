package net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFilterDtoDec;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.ColumnFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.ColumnFilter2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ColumnFilter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ColumnFilter2DtoGenerator implements Poso2DtoGenerator<ColumnFilter,ColumnFilterDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ColumnFilter2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ColumnFilterDtoDec instantiateDto(ColumnFilter poso)  {
		ColumnFilterDtoDec dto = new ColumnFilterDtoDec();
		return dto;
	}

	public ColumnFilterDtoDec createDto(ColumnFilter poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ColumnFilterDtoDec dto = new ColumnFilterDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set column */
			Object tmpDtoColumnDtogetColumn = dtoServiceProvider.get().createDto(poso.getColumn(), here, referenced);
			dto.setColumn((ColumnDto)tmpDtoColumnDtogetColumn);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoColumnDtogetColumn, poso.getColumn(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setColumn((ColumnDto)refDto);
				}
			});

		}

		return dto;
	}


}
