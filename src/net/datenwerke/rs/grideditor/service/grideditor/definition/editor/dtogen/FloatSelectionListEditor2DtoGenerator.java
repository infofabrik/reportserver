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
import net.datenwerke.rs.grideditor.client.grideditor.dto.FloatSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FloatSelectionListEditorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.FloatSelectionListEditor;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.FloatSelectionListEditor2DtoGenerator;

/**
 * Poso2DtoGenerator for FloatSelectionListEditor
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FloatSelectionListEditor2DtoGenerator implements Poso2DtoGenerator<FloatSelectionListEditor,FloatSelectionListEditorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public FloatSelectionListEditor2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public FloatSelectionListEditorDtoDec instantiateDto(FloatSelectionListEditor poso)  {
		FloatSelectionListEditorDtoDec dto = new FloatSelectionListEditorDtoDec();
		return dto;
	}

	public FloatSelectionListEditorDtoDec createDto(FloatSelectionListEditor poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final FloatSelectionListEditorDtoDec dto = new FloatSelectionListEditorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set forceSelection */
			dto.setForceSelection(poso.isForceSelection() );

			/*  set valueMap */
			dto.setValueMap(poso.getValueMap() );

			/*  set values */
			List<Float> col_values = new ArrayList<Float>();
			if( null != poso.getValues()){
				for(Float obj : poso.getValues())
					col_values.add((Float) obj);
				dto.setValues(col_values);
			}

		}

		return dto;
	}


}
