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
import net.datenwerke.rs.grideditor.client.grideditor.dto.DoubleSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DoubleSelectionListEditorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.DoubleSelectionListEditor;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.DoubleSelectionListEditor2DtoGenerator;

/**
 * Poso2DtoGenerator for DoubleSelectionListEditor
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DoubleSelectionListEditor2DtoGenerator implements Poso2DtoGenerator<DoubleSelectionListEditor,DoubleSelectionListEditorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DoubleSelectionListEditor2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DoubleSelectionListEditorDtoDec instantiateDto(DoubleSelectionListEditor poso)  {
		DoubleSelectionListEditorDtoDec dto = new DoubleSelectionListEditorDtoDec();
		return dto;
	}

	public DoubleSelectionListEditorDtoDec createDto(DoubleSelectionListEditor poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DoubleSelectionListEditorDtoDec dto = new DoubleSelectionListEditorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set forceSelection */
			dto.setForceSelection(poso.isForceSelection() );

			/*  set valueMap */
			dto.setValueMap(poso.getValueMap() );

			/*  set values */
			List<Double> col_values = new ArrayList<Double>();
			if( null != poso.getValues()){
				for(Double obj : poso.getValues())
					col_values.add((Double) obj);
				dto.setValues(col_values);
			}

		}

		return dto;
	}


}
