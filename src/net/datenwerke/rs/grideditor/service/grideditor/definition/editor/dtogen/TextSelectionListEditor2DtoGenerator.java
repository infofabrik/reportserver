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
import net.datenwerke.rs.grideditor.client.grideditor.dto.TextSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextSelectionListEditorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextSelectionListEditor;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.TextSelectionListEditor2DtoGenerator;

/**
 * Poso2DtoGenerator for TextSelectionListEditor
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TextSelectionListEditor2DtoGenerator implements Poso2DtoGenerator<TextSelectionListEditor,TextSelectionListEditorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TextSelectionListEditor2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TextSelectionListEditorDtoDec instantiateDto(TextSelectionListEditor poso)  {
		TextSelectionListEditorDtoDec dto = new TextSelectionListEditorDtoDec();
		return dto;
	}

	public TextSelectionListEditorDtoDec createDto(TextSelectionListEditor poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TextSelectionListEditorDtoDec dto = new TextSelectionListEditorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set forceSelection */
			dto.setForceSelection(poso.isForceSelection() );

			/*  set valueMap */
			dto.setValueMap(poso.getValueMap() );

			/*  set values */
			List<String> col_values = new ArrayList<String>();
			if( null != poso.getValues()){
				for(String obj : poso.getValues())
					col_values.add((String) obj);
				dto.setValues(col_values);
			}

		}

		return dto;
	}


}
