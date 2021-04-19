package net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.IntBooleanEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntBooleanEditorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.IntBooleanEditor;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.IntBooleanEditor2DtoGenerator;

/**
 * Poso2DtoGenerator for IntBooleanEditor
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class IntBooleanEditor2DtoGenerator implements Poso2DtoGenerator<IntBooleanEditor,IntBooleanEditorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public IntBooleanEditor2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public IntBooleanEditorDtoDec instantiateDto(IntBooleanEditor poso)  {
		IntBooleanEditorDtoDec dto = new IntBooleanEditorDtoDec();
		return dto;
	}

	public IntBooleanEditorDtoDec createDto(IntBooleanEditor poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final IntBooleanEditorDtoDec dto = new IntBooleanEditorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set falseInt */
			dto.setFalseInt(poso.getFalseInt() );

			/*  set trueInt */
			dto.setTrueInt(poso.getTrueInt() );

		}

		return dto;
	}


}
