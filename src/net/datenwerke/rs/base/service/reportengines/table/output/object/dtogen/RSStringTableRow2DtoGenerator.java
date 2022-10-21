package net.datenwerke.rs.base.service.reportengines.table.output.object.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSStringTableRowDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.dtogen.RSStringTableRow2DtoGenerator;

/**
 * Poso2DtoGenerator for RSStringTableRow
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RSStringTableRow2DtoGenerator implements Poso2DtoGenerator<RSStringTableRow,RSStringTableRowDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public RSStringTableRow2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public RSStringTableRowDto instantiateDto(RSStringTableRow poso)  {
		RSStringTableRowDto dto = new RSStringTableRowDto();
		return dto;
	}

	public RSStringTableRowDto createDto(RSStringTableRow poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final RSStringTableRowDto dto = new RSStringTableRowDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set stringRow */
			List<String> col_stringRow = new ArrayList<String>();
			if( null != poso.getStringRow()){
				for(String obj : poso.getStringRow())
					col_stringRow.add((String) obj);
				dto.setStringRow(col_stringRow);
			}

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
