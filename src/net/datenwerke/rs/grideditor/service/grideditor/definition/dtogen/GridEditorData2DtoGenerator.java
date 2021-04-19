package net.datenwerke.rs.grideditor.service.grideditor.definition.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorDataDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorDataDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorData;
import net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorRecord;
import net.datenwerke.rs.grideditor.service.grideditor.definition.dtogen.GridEditorData2DtoGenerator;

/**
 * Poso2DtoGenerator for GridEditorData
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class GridEditorData2DtoGenerator implements Poso2DtoGenerator<GridEditorData,GridEditorDataDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public GridEditorData2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public GridEditorDataDtoDec instantiateDto(GridEditorData poso)  {
		GridEditorDataDtoDec dto = new GridEditorDataDtoDec();
		return dto;
	}

	public GridEditorDataDtoDec createDto(GridEditorData poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final GridEditorDataDtoDec dto = new GridEditorDataDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set config */
			Object tmpDtoGridEditorConfigDtogetConfig = dtoServiceProvider.get().createDto(poso.getConfig(), here, referenced);
			dto.setConfig((GridEditorConfigDto)tmpDtoGridEditorConfigDtogetConfig);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoGridEditorConfigDtogetConfig, poso.getConfig(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setConfig((GridEditorConfigDto)refDto);
				}
			});

			/*  set dataRecords */
			final List<GridEditorRecordDto> col_dataRecords = new ArrayList<GridEditorRecordDto>();
			if( null != poso.getDataRecords()){
				for(GridEditorRecord refPoso : poso.getDataRecords()){
					final Object tmpDtoGridEditorRecordDtogetDataRecords = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_dataRecords.add((GridEditorRecordDto) tmpDtoGridEditorRecordDtogetDataRecords);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoGridEditorRecordDtogetDataRecords, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (dataRecords)");
							int tmp_index = col_dataRecords.indexOf(tmpDtoGridEditorRecordDtogetDataRecords);
							col_dataRecords.set(tmp_index,(GridEditorRecordDto) dto);
						}
					});
				}
				dto.setDataRecords(col_dataRecords);
			}

		}

		return dto;
	}


}
