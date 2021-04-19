package net.datenwerke.rs.base.service.reportengines.table.output.object.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.dtogen.RSTableRow2DtoGenerator;

/**
 * Poso2DtoGenerator for RSTableRow
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RSTableRow2DtoGenerator implements Poso2DtoGenerator<RSTableRow,RSTableRowDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public RSTableRow2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public RSTableRowDto instantiateDto(RSTableRow poso)  {
		RSTableRowDto dto = new RSTableRowDto();
		return dto;
	}

	public RSTableRowDto createDto(RSTableRow poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final RSTableRowDto dto = new RSTableRowDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set tableDefinition */
			Object tmpDtoTableDefinitionDtogetTableDefinition = dtoServiceProvider.get().createDto(poso.getTableDefinition(), referenced, referenced);
			dto.setTableDefinition((TableDefinitionDto)tmpDtoTableDefinitionDtogetTableDefinition);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoTableDefinitionDtogetTableDefinition, poso.getTableDefinition(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setTableDefinition((TableDefinitionDto)refDto);
				}
			});

		}

		return dto;
	}


}
