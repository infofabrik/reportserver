package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.grideditor.client.grideditor.dto.DecimalSelectionListEditorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.DecimalSelectionListEditorDtoDec;
import net.datenwerke.rs.grideditor.client.grideditor.dto.pa.SelectionListEditorDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.definition.editor.DecimalSelectionListEditor.class)
public interface DecimalSelectionListEditorDtoPA extends SelectionListEditorDtoPA {


	public static final DecimalSelectionListEditorDtoPA INSTANCE = GWT.create(DecimalSelectionListEditorDtoPA.class);


	/* Properties */
	public ValueProvider<DecimalSelectionListEditorDto,Map<String, BigDecimal>> valueMap();
	public ValueProvider<DecimalSelectionListEditorDto,List<BigDecimal>> values();


}
