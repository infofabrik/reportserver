package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.dnd.core.client.GridDropTarget;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.editing.ClicksToEdit;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;

import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.gxtdto.client.utils.StringEscapeUtils;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.FilterRangeDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.helpers.validator.IntegerFieldValidator;
import net.datenwerke.rs.base.client.reportengines.table.helpers.validator.NumericalFieldValidator;
import net.datenwerke.rs.base.client.reportengines.table.helpers.validator.SqlDateValidator;
import net.datenwerke.rs.base.client.reportengines.table.helpers.validator.SqlTimestampValidator;


/**
 * 
 *
 */
public class TwoColumnGridView extends GridView<FilterRangeDto>{

	private static FilterRangeDtoPA frPa = GWT.create(FilterRangeDtoPA.class);
	
	public TwoColumnGridView(ListStore<FilterRangeDto> store, ColumnDto column, SelectionPanel<FilterRangeDto> selectionPanel) {
		super(store, column, selectionPanel);
	}

	@Override
	protected Grid<FilterRangeDto> createGrid(ListStore<FilterRangeDto> store) {
		List<ColumnConfig<FilterRangeDto,?>> columns = new ArrayList<ColumnConfig<FilterRangeDto,?>>();
		
		ColumnConfig<FilterRangeDto,String> columnFrom = new ColumnConfig<FilterRangeDto,String>(frPa.rangeFrom(), 180, FilterMessages.INSTANCE.rangeFrom()); 
		columnFrom.setMenuDisabled(true);

		
		columnFrom.setCell(new AbstractCell<String>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					String value, SafeHtmlBuilder sb) {
				if(null != value)
					sb.appendEscaped(StringEscapeUtils.escapeAngleBrackets(value));
			}
		});
		columns.add(columnFrom);
		
		ColumnConfig<FilterRangeDto,String> columnTo = new ColumnConfig<FilterRangeDto,String>(frPa.rangeTo(), 180, FilterMessages.INSTANCE.rangeTo());
		columnTo.setMenuDisabled(true);
		columnTo.setCell(new AbstractCell<String>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					String value, SafeHtmlBuilder sb) {
				if(null != value)
					sb.appendEscaped(StringEscapeUtils.escapeAngleBrackets(value));
			}
		});
		columns.add(columnTo);

		Grid<FilterRangeDto> grid = new Grid<FilterRangeDto>(store, new ColumnModel<FilterRangeDto>(columns));
		grid.setSelectionModel(new GridSelectionModel<FilterRangeDto>());
		
		GridInlineEditing<FilterRangeDto> editing = new GridInlineEditing<FilterRangeDto>(grid);
		editing.setClicksToEdit(ClicksToEdit.TWO);
		
		TextField fromField = new TextField();
		Integer type = this.column.getType();
		if(SqlTypes.isInteger(type))
			fromField.addValidator(new IntegerFieldValidator());
		else if(SqlTypes.isNumerical(type))
			fromField.addValidator(new NumericalFieldValidator());
		else if(SqlTypes.isTimeStamp(type))
			fromField.addValidator(new SqlTimestampValidator());
		else if(SqlTypes.isDate(type))
			fromField.addValidator(new SqlDateValidator());
		
		editing.addEditor(columnFrom, new Converter<String, String>() {
			@Override
			public String convertFieldValue(String object) {
				return parseFromToValue(object);
			}

			@Override
			public String convertModelValue(String object) {
				return object;
			}
		}, fromField);
		
		TextField toField = new TextField();
		if(SqlTypes.isInteger(type))
			toField.addValidator(new IntegerFieldValidator());
		else if(SqlTypes.isNumerical(type))
			toField.addValidator(new NumericalFieldValidator());
		else if(SqlTypes.isTimeStamp(type))
			toField.addValidator(new SqlTimestampValidator());
		else if(SqlTypes.isDate(type))
			toField.addValidator(new SqlDateValidator());
		
		editing.addEditor(columnTo, new Converter<String, String>() {
			@Override
			public String convertFieldValue(String object) {
				return parseFromToValue(object);
			}

			@Override
			public String convertModelValue(String object) {
				return object;
			}
		}, toField);
		
		grid.getView().setShowDirtyCells(false);
		return grid;
	}
	
	protected String parseFromToValue(String value) {
		  if(null == value)
			  return null;
   	  
   		  value = ((String)value).trim();
   		  if("".equals(value))
   			  return null;
   		  return value;
	}

	@Override
	protected void createGridDropTarget(Grid<FilterRangeDto> grid) {
		new GridDropTarget<FilterRangeDto>(grid){
			@Override
			protected void onDragDrop(DndDropEvent e) {
				Object data = e.getData();
				List<Object> models = prepareDropData(data, true);

				for(Object m : models){
					if(m instanceof StringBaseModel){
						selectionPanel.insertElement((StringBaseModel) m);
					}
				}
				insertIndex = -1;
				activeItem = null;
			}
		};
	}
}
