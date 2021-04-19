package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.DwTabPanel;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.container.HasLayout;

/**
 * 
 *
 */
public class LikeFilterComponent extends DwTabPanel{
	
	private List<AbstractFilterAspect> filterAspects;

	private final ColumnDto column;
	
	public LikeFilterComponent(final TableReportDto report, final ColumnDto column, String executeToken, boolean forceConsistency, boolean showConsistency) {
		super();
		
		this.column = column;
		
		/* configure window properties */
		setHeight(500);
		
		/* create all filter aspects */
		filterAspects = new ArrayList<AbstractFilterAspect>();
		filterAspects.add(new IncludeValuesFilterAspect(report, column, executeToken));
		filterAspects.add(new IncludeRangesFilterAspect(report, column, executeToken));
		filterAspects.add(new ExcludeValuesFilterAspect(report, column, executeToken));
		filterAspects.add(new ExcludeRangesFilterAspect(report, column, executeToken));
		
		setForceConsistency(forceConsistency);
		if(! showConsistency)
			hideForceConsistency();
		
		/* load data */
		loadData();
		
		for(final AbstractFilterAspect filterAspect : filterAspects){
			TabItemConfig filterAspectTab = new TabItemConfig(filterAspect.getTitleString());
			this.add(filterAspect.getComponent(), filterAspectTab);
		}

		addSelectionHandler(new SelectionHandler<Widget>() {
			@Override
			public void onSelection(SelectionEvent<Widget> event) {
				AbstractFilterAspect aspect = filterAspects.get(getWidgetIndex(getActiveWidget()));
				aspect.onSelection();
				if(event.getSelectedItem() instanceof HasLayout)
					((HasLayout)event.getSelectedItem()).forceLayout();
			}
		});
		
		/* tell first aspect its selected */
		filterAspects.get(0).onSelection();
	}
	
	private void loadData() {
		if(null == column.getFilter())
			createLikeFilter(column);
		else 
			loadLikeFilter(column.getFilter());
	}
	
	public boolean validate() {
		try{
			for(AbstractFilterAspect filter : filterAspects){
				filter.validate();
			}
			for(AbstractFilterAspect filter : filterAspects){
				filter.cleanup();
				filter.tryParseText();
			}
		} catch (RuntimeException e) {
			new DwAlertMessageBox(FilterMessages.INSTANCE.textViewInvalidMessage(), e.getMessage()).show();
			return false;
		}
		
		return true;
	}
	
	public void cleanup(){
		for(AbstractFilterAspect filter : filterAspects){
			filter.cleanup();
		}
	}

	private void createLikeFilter(ColumnDto column){
		FilterDto filter = new FilterDtoDec(); 
		column.setFilter(filter);
	}
	
	private void loadLikeFilter(FilterDto filter){
		for(AbstractFilterAspect filterAspect : filterAspects)
			filterAspect.loadConfiguration(filter);
	}

	public void setForceConsistency(boolean force) {
		for(AbstractFilterAspect aspect : filterAspects)
			aspect.setForceConsistency(force);
	}

	public void hideForceConsistency() {
		for(AbstractFilterAspect aspect : filterAspects)
			aspect.hideForceConsistency();		
	}

}
