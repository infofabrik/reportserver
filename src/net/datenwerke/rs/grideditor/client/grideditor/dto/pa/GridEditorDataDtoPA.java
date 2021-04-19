package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorDataDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.GridEditorDataDtoDec;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorData.class)
public interface GridEditorDataDtoPA extends PropertyAccess<GridEditorDataDto> {


	public static final GridEditorDataDtoPA INSTANCE = GWT.create(GridEditorDataDtoPA.class);


	/* Properties */
	public ValueProvider<GridEditorDataDto,GridEditorConfigDto> config();
	public ValueProvider<GridEditorDataDto,List<GridEditorRecordDto>> dataRecords();


}
