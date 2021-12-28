package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;

import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.FilterService;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;

/**
 * 
 *
 * @param <M>
 */
public abstract class AbstractFilterAspect<M> {

	@Inject
	protected static FilterService filterService;
	
	@Inject
	protected static SqlTypes sqlTypes;
	
	protected final ColumnDto column;
	
	protected DistinctSelectorPanel<M> distinctSelectorPanel;
	protected SelectionPanel<M> selectionPanel;
	
	private String titleString;


	public AbstractFilterAspect(String titleString, TableReportDto report, ColumnDto column, FilterType type, String executeToken) {
		this.column = column;
		this.titleString = titleString;

		selectionPanel = createSelectionPanel(column);
		distinctSelectorPanel = new DistinctSelectorPanel<M>(report, column, selectionPanel, type, executeToken + getClass().getName());
	}

	public String getTitleString(){
		return titleString;
	}
	
	protected abstract SelectionPanel<M> createSelectionPanel(ColumnDto column);

	public Widget getComponent(){
		BorderLayoutContainer container = new BorderLayoutContainer();

		BorderLayoutData westData = new BorderLayoutData(250);
		westData.setSplit(true);
		container.setWestWidget(distinctSelectorPanel.getComponent(), westData);
		
		Widget component = selectionPanel.getComponent();
		doConfigureSelectionComponent(component);
		
		container.setCenterWidget(component);

		return container;
	}

	protected void doConfigureSelectionComponent(Widget component) {
		// TODO Auto-generated method stub
		
	}

	abstract public void storeConfiguration();

	abstract public void loadConfiguration(FilterDto filter);

	public void tryParseText(){
		selectionPanel.tryParseText();
	}

	public void cleanup() {
		distinctSelectorPanel.cleanup();
	}
	
	public void onSelection() {
		distinctSelectorPanel.onSelection();
	}

	public void setForceConsistency(boolean force) {
		distinctSelectorPanel.setForceConsistency(force);
	}

	public void hideForceConsistency() {
		distinctSelectorPanel.hideForceConsistency();
	}

	public void validate() {
		selectionPanel.validate();
	}
}
