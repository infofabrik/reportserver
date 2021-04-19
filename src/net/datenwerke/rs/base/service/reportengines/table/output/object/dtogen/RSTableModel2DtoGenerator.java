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
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableRowDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableDefinitionDto;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.dtogen.RSTableModel2DtoGenerator;

/**
 * Poso2DtoGenerator for RSTableModel
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RSTableModel2DtoGenerator implements Poso2DtoGenerator<RSTableModel,RSTableModelDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public RSTableModel2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public RSTableModelDto instantiateDto(RSTableModel poso)  {
		RSTableModelDto dto = new RSTableModelDto();
		return dto;
	}

	public RSTableModelDto createDto(RSTableModel poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final RSTableModelDto dto = new RSTableModelDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set data */
			final List<RSTableRowDto> col_data = new ArrayList<RSTableRowDto>();
			if( null != poso.getData()){
				for(RSTableRow refPoso : poso.getData()){
					final Object tmpDtoRSTableRowDtogetData = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_data.add((RSTableRowDto) tmpDtoRSTableRowDtogetData);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoRSTableRowDtogetData, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (data)");
							int tmp_index = col_data.indexOf(tmpDtoRSTableRowDtogetData);
							col_data.set(tmp_index,(RSTableRowDto) dto);
						}
					});
				}
				dto.setData(col_data);
			}

			/*  set tableDefinition */
			Object tmpDtoTableDefinitionDtogetTableDefinition = dtoServiceProvider.get().createDto(poso.getTableDefinition(), here, referenced);
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
