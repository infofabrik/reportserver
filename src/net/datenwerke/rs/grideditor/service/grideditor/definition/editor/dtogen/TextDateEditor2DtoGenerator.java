package net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.TextDateEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextDateEditorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextDateEditor;
import net.datenwerke.rs.grideditor.service.grideditor.definition.editor.dtogen.TextDateEditor2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for TextDateEditor
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TextDateEditor2DtoGenerator implements Poso2DtoGenerator<TextDateEditor,TextDateEditorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TextDateEditor2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TextDateEditorDtoDec instantiateDto(TextDateEditor poso)  {
		TextDateEditorDtoDec dto = new TextDateEditorDtoDec();
		return dto;
	}

	public TextDateEditorDtoDec createDto(TextDateEditor poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TextDateEditorDtoDec dto = new TextDateEditorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set dateFormat */
			dto.setDateFormat(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDateFormat(),8192)));

		}

		return dto;
	}


}
