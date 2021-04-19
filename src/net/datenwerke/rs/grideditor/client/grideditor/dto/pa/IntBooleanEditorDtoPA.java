package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.grideditor.client.grideditor.dto.IntBooleanEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.IntBooleanEditorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.EditorDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.IntBooleanEditor.class)
public interface IntBooleanEditorDtoPA extends EditorDtoPA {


	public static final IntBooleanEditorDtoPA INSTANCE = GWT.create(IntBooleanEditorDtoPA.class);


	/* Properties */
	public ValueProvider<IntBooleanEditorDto,Integer> falseInt();
	public ValueProvider<IntBooleanEditorDto,Integer> trueInt();


}
