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
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorRecordDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorRecord;
import net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorRecordEntry;
import net.datenwerke.rs.grideditor.service.grideditor.definition.dtogen.GridEditorRecord2DtoGenerator;

/**
 * Poso2DtoGenerator for GridEditorRecord
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class GridEditorRecord2DtoGenerator implements Poso2DtoGenerator<GridEditorRecord,GridEditorRecordDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public GridEditorRecord2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public GridEditorRecordDtoDec instantiateDto(GridEditorRecord poso)  {
		GridEditorRecordDtoDec dto = new GridEditorRecordDtoDec();
		return dto;
	}

	public GridEditorRecordDtoDec createDto(GridEditorRecord poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final GridEditorRecordDtoDec dto = new GridEditorRecordDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set data */
			final List<GridEditorRecordEntryDto> col_data = new ArrayList<GridEditorRecordEntryDto>();
			if( null != poso.getData()){
				for(GridEditorRecordEntry refPoso : poso.getData()){
					final Object tmpDtoGridEditorRecordEntryDtogetData = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_data.add((GridEditorRecordEntryDto) tmpDtoGridEditorRecordEntryDtogetData);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoGridEditorRecordEntryDtogetData, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (data)");
							int tmp_index = col_data.indexOf(tmpDtoGridEditorRecordEntryDtogetData);
							col_data.set(tmp_index,(GridEditorRecordEntryDto) dto);
						}
					});
				}
				dto.setData(col_data);
			}

			/*  set id */
			dto.setId(poso.getId() );

		}

		return dto;
	}


}
