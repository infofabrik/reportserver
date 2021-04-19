package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.grideditor.client.grideditor.dto.TextAreaEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.TextAreaEditorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.EditorDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.TextAreaEditor.class)
public interface TextAreaEditorDtoPA extends EditorDtoPA {


	public static final TextAreaEditorDtoPA INSTANCE = GWT.create(TextAreaEditorDtoPA.class);


	/* Properties */
	public ValueProvider<TextAreaEditorDto,Integer> height();
	public ValueProvider<TextAreaEditorDto,Integer> width();


}
