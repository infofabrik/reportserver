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
import net.datenwerke.rs.grideditor.client.grideditor.dto.IntSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntSelectionListEditorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.IntSelectionListEditor;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.IntSelectionListEditor2DtoGenerator;

/**
 * Poso2DtoGenerator for IntSelectionListEditor
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class IntSelectionListEditor2DtoGenerator implements Poso2DtoGenerator<IntSelectionListEditor,IntSelectionListEditorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public IntSelectionListEditor2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public IntSelectionListEditorDtoDec instantiateDto(IntSelectionListEditor poso)  {
		IntSelectionListEditorDtoDec dto = new IntSelectionListEditorDtoDec();
		return dto;
	}

	public IntSelectionListEditorDtoDec createDto(IntSelectionListEditor poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final IntSelectionListEditorDtoDec dto = new IntSelectionListEditorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set forceSelection */
			dto.setForceSelection(poso.isForceSelection() );

			/*  set valueMap */
			dto.setValueMap(poso.getValueMap() );

			/*  set values */
			List<Integer> col_values = new ArrayList<Integer>();
			if( null != poso.getValues()){
				for(Integer obj : poso.getValues())
					col_values.add((Integer) obj);
				dto.setValues(col_values);
			}

		}

		return dto;
	}


}
