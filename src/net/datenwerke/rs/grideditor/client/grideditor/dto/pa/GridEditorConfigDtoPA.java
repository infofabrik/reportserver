package net.datenwerke.rs.grideditor.client.grideditor.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorColumnConfigDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorConfigDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.grideditor.service.grideditor.definition.GridEditorConfig.class)
public interface GridEditorConfigDtoPA extends PropertyAccess<GridEditorConfigDto> {


	public static final GridEditorConfigDtoPA INSTANCE = GWT.create(GridEditorConfigDtoPA.class);


	/* Properties */
	public ValueProvider<GridEditorConfigDto,Boolean> canAddRecords();
	public ValueProvider<GridEditorConfigDto,Boolean> canDuplicateRecords();
	public ValueProvider<GridEditorConfigDto,Boolean> canRemoveRecords();
	public ValueProvider<GridEditorConfigDto,List<GridEditorColumnConfigDto>> columnConfigs();
	public ValueProvider<GridEditorConfigDto,Integer> defaultPageSize();
	public ValueProvider<GridEditorConfigDto,Boolean> filterable();
	public ValueProvider<GridEditorConfigDto,Boolean> hasForm();
	public ValueProvider<GridEditorConfigDto,Integer> maxPageSize();
	public ValueProvider<GridEditorConfigDto,Boolean> paging();
	public ValueProvider<GridEditorConfigDto,Boolean> sortable();
	public ValueProvider<GridEditorConfigDto,Integer> totalNumberOfRecords();


}
