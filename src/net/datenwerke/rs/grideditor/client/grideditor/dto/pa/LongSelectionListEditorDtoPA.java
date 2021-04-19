package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.List;
import java.util.Map;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.grideditor.client.grideditor.dto.LongSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.LongSelectionListEditorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.SelectionListEditorDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.LongSelectionListEditor.class)
public interface LongSelectionListEditorDtoPA extends SelectionListEditorDtoPA {


	public static final LongSelectionListEditorDtoPA INSTANCE = GWT.create(LongSelectionListEditorDtoPA.class);


	/* Properties */
	public ValueProvider<LongSelectionListEditorDto,Map<String, Long>> valueMap();
	public ValueProvider<LongSelectionListEditorDto,List<Long>> values();


}
