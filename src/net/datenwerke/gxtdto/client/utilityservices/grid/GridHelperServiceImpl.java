package net.datenwerke.gxtdto.client.utilityservices.grid;

import java.util.Comparator;

import net.datenwerke.gxtdto.client.utilityservices.form.combo.ComparatorWithEmpty;
import net.datenwerke.gxtdto.client.utilityservices.form.combo.ConverterWithEmpty;
import net.datenwerke.gxtdto.client.utilityservices.form.combo.EmptyOption;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.Store.StoreSortInfo;
import com.sencha.gxt.data.shared.StringLabelProvider;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;


/**
 * 
 *
 */
public class GridHelperServiceImpl implements GridHelperService {

		
	@Override
	public <D, V extends Enum<?>> CCContainer<D, V> createComboBoxColumnConfig(
			final V[] values, ValueProvider<D, V> vp, boolean nullable, SortDir sortDir,
			int width){
		Converter<V,Object> converter = new ConverterWithEmpty<V>();
		Comparator<Object> comparator = new ComparatorWithEmpty();
		
		return createComboBoxColumnConfig(values, vp, converter, new StringLabelProvider<Object>(), comparator, nullable, sortDir, width);
	}
	
	@Override
	public <D, V> CCContainer<D, V> createComboBoxColumnConfig(
			final V[] values, ValueProvider<D, V> vp, Converter<V, Object> converter, Comparator<Object> comparator,  boolean nullable, SortDir sortDir,
			int width){
		return createComboBoxColumnConfig(values, vp, converter, new StringLabelProvider<Object>(), comparator, nullable, sortDir, width);
	}
	
	@Override
	public <D> CCContainer<D, Boolean> createBooleanComboBoxColumnConfig(
			ValueProvider<D, Boolean> vp, boolean nullable, boolean trueFirst,
			int width, final String tString, final String fString){
		
		return createComboBoxColumnConfig( 
			trueFirst ? new Boolean[]{true,false} : new Boolean[]{false, true}, 
			vp, 
			new Converter<Boolean, Object>() {
				@Override
				public Boolean convertFieldValue(Object object) {
					if(object instanceof EmptyOption)
						return null;
					return (Boolean) object;
				}

				@Override
				public Object convertModelValue(Boolean object) {
					return object;
				}
			}, 
			new LabelProvider<Object>() {
				@Override
				public String getLabel(Object item) {
					if(Boolean.TRUE.equals(item))
						return tString;
					if(Boolean.FALSE.equals(item))
						return fString;
					return null != item ? item.toString() : "";
				}
			}, 
			null, nullable, null, width);
	}
	
	
	@Override
	public <D, V> CCContainer<D, V> createComboBoxColumnConfig(final V[] values, ValueProvider<D, V> vp, Converter<V, Object> converter, LabelProvider<? super Object> labelProvider, Comparator<Object> comparator, boolean nullable, SortDir sortDir,
			int width){
	    final SimpleComboBox<Object> combo = new SimpleComboBox<Object>(labelProvider);  
	    if(nullable){
	    	combo.setAllowBlank(true);
	    	combo.add(new EmptyOption());
	    }
	    combo.setForceSelection(true);
	    combo.setTriggerAction(TriggerAction.ALL);
	    combo.addSelectionHandler(new SelectionHandler<Object>() {
			@Override
			public void onSelection(SelectionEvent<Object> event) {
				combo.finishEditing();
			}
		});
	    
	    for(V e : values)
	    	combo.add(e);
	    
	    if(null != sortDir)
	    	combo.getStore().addSortInfo(new StoreSortInfo<Object>(comparator, sortDir));
	    
	    ColumnConfig<D,V> column = new ColumnConfig<D,V>(vp);  
	    column.setWidth(width);  
	    column.setSortable(false);
	    column.setMenuDisabled(true);
	    
	    CCContainer<D, V> ccContainer = new CCContainer<D,V>(column, combo, converter);
//	    editing.addEditor(column, converter, combo);	

	    return ccContainer;
	}
}
