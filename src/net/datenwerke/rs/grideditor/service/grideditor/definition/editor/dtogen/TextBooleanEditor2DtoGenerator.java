package net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.TextBooleanEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextBooleanEditorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextBooleanEditor;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.TextBooleanEditor2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for TextBooleanEditor
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TextBooleanEditor2DtoGenerator implements Poso2DtoGenerator<TextBooleanEditor,TextBooleanEditorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TextBooleanEditor2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TextBooleanEditorDtoDec instantiateDto(TextBooleanEditor poso)  {
		TextBooleanEditorDtoDec dto = new TextBooleanEditorDtoDec();
		return dto;
	}

	public TextBooleanEditorDtoDec createDto(TextBooleanEditor poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TextBooleanEditorDtoDec dto = new TextBooleanEditorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set falseText */
			dto.setFalseText(StringEscapeUtils.escapeXml(StringUtils.left(poso.getFalseText(),8192)));

			/*  set trueText */
			dto.setTrueText(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTrueText(),8192)));

		}

		return dto;
	}


}
