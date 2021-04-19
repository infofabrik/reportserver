package net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.LongSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.LongSelectionListEditorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.LongSelectionListEditor;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.LongSelectionListEditor2DtoGenerator;

/**
 * Poso2DtoGenerator for LongSelectionListEditor
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class LongSelectionListEditor2DtoGenerator implements Poso2DtoGenerator<LongSelectionListEditor,LongSelectionListEditorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public LongSelectionListEditor2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public LongSelectionListEditorDtoDec instantiateDto(LongSelectionListEditor poso)  {
		LongSelectionListEditorDtoDec dto = new LongSelectionListEditorDtoDec();
		return dto;
	}

	public LongSelectionListEditorDtoDec createDto(LongSelectionListEditor poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final LongSelectionListEditorDtoDec dto = new LongSelectionListEditorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set forceSelection */
			dto.setForceSelection(poso.isForceSelection() );

			/*  set valueMap */
			dto.setValueMap(poso.getValueMap() );

			/*  set values */
			List<Long> col_values = new ArrayList<Long>();
			if( null != poso.getValues()){
				for(Long obj : poso.getValues())
					col_values.add((Long) obj);
				dto.setValues(col_values);
			}

		}

		return dto;
	}


}
