package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.gxtdto.client.utils.handlers.GenericStoreHandler;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterRangeDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.helpers.validator.IntegerFieldValidator;
import net.datenwerke.rs.base.client.reportengines.table.helpers.validator.NumericalFieldValidator;
import net.datenwerke.rs.base.client.reportengines.table.helpers.validator.SqlDateValidator;
import net.datenwerke.rs.base.client.reportengines.table.helpers.validator.SqlTimestampValidator;

import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.dnd.core.client.DndDropEvent;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.form.Validator;


/**
 * 
 *
 */
public class TwoColumnTextView extends TextView<FilterRangeDto>{

	public TwoColumnTextView(final ListStore<FilterRangeDto> store, ColumnDto column, SelectionPanel<FilterRangeDto> selectionPanel, TabPanel tabPanel) {
		super(store, selectionPanel, column, tabPanel);

		store.addStoreHandlers(new GenericStoreHandler<FilterRangeDto>(){
			@Override
			protected void handleDataChangeEvent() {
				StringBuffer buf = new StringBuffer();
				for(Object v : store.getAll()){
					if(v instanceof FilterRangeDto){
						FilterRangeDto f = (FilterRangeDto)v;
						if(null != f.getRangeFrom())
							buf.append(f.getRangeFrom());
						buf.append(" - "); //$NON-NLS-1$
						if(null != f.getRangeTo())
							buf.append(f.getRangeTo());
						buf.append("\r\n"); //$NON-NLS-1$
					}
					
				}
				textArea.setValue(buf.toString());
			}
		});
	}

	@Override
	protected List<FilterRangeDto> tryParseText() throws RuntimeException{
		ArrayList<FilterRangeDto> rangeDtos = new ArrayList<FilterRangeDto>();
		String s = textArea.getValue();
		
		Validator<String> validator = null;
		if(SqlTypes.isInteger(this.column.getType()))
			validator = new IntegerFieldValidator();
		else if(SqlTypes.isNumerical(column.getType()))
			validator = new NumericalFieldValidator();
		else if(SqlTypes.TIMESTAMP == this.column.getType())
			validator = new SqlTimestampValidator();
		else if(SqlTypes.DATE == this.column.getType())
			validator = new SqlDateValidator();
		
		if(null != s && ! "".equals(s)){
			String[] lines = s.split("\r?\n\r?"); //$NON-NLS-1$
			for(String line : lines){
				if("".equals(line.trim()))
					continue;
				
				FilterRangeDto dto = new FilterRangeDtoDec();
				String from = null;
				String to = null;

				if(! line.contains("-"))
					throw new RuntimeException(FilterMessages.INSTANCE.errorRangeNoHyphen(line)); 
				
				String[] tokens = line.split(" - "); //$NON-NLS-1$
				if(tokens.length == 2){
					from = tokens[0].trim();
					to = tokens[1].trim();
				} else if(line.matches("^\\s*-..*")){
					to = line.substring(line.indexOf('-') + 1).trim();
				} else if(line.matches(".*\\s*-\\s*$")){
					from = line.substring(0, line.lastIndexOf('-')).trim();
				} else
					throw new RuntimeException(FilterMessages.INSTANCE.errorRangeParseTextLine(line)); 
				
				if(null != from && ! "".equals(from)){
					if(null != validator){
						List<EditorError> err = validator.validate(null, from);
						if(null != err && ! err.isEmpty())
							throw new RuntimeException(err.get(0).getMessage());
					}
					
					dto.setRangeFrom(filterService.getStringValue(from, column.getType()));
				} if(null != to && ! "".equals(to)){
					if(null != validator){
						List<EditorError> err = validator.validate(null, to);
						if(null != err && ! err.isEmpty())
							throw new RuntimeException(err.get(0).getMessage());
					}
					
					dto.setRangeTo(filterService.getStringValue(to, column.getType()));
				}
				
				rangeDtos.add(dto);
			}
		}
		return rangeDtos;
	}

	@Override
	protected void handleDroppedData(List<Object> models, DndDropEvent e) {
		for(Object m : models){
			if(m instanceof StringBaseModel){
				selectionPanel.insertElement((StringBaseModel) m);
			}
		}
	}
}
