package net.datenwerke.rs.base.client.reportengines.table.prefilter.hookers;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwFlowContainer;
import net.datenwerke.gxtdto.client.forms.selection.SingleSelectionField;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.base.client.reportengines.table.TableReportUtilityDao;
import net.datenwerke.rs.base.client.reportengines.table.dto.BinaryColumnFilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.BinaryOperatorDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterSpecDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.BinaryColumnFilterDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.helpers.ColumnSelector;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.hooks.PreFilterConfiguratorHook;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.locale.PreFilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.propertywidgets.PreFilterView;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.propertywidgets.PreFilterView.EditPreFilterCallback;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.propertywidgets.PreFilterView.InstantiatePreFilterCallback;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.StringLabelProvider;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;

import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;

public class BinaryColumnFilterConfiguratorHooker implements
		PreFilterConfiguratorHook {

	private static final PreFilterMessages messages = GWT.create(PreFilterMessages.class);
	
	private final TableReportUtilityDao tableReportUtilityDao;
	
	private ColumnSelector selectorA;
	private ColumnSelector selectorB;

	private SingleSelectionField<ColumnDto> columnASelector;
	private SingleSelectionField<ColumnDto> columnBSelector;
	
	@Inject
	public BinaryColumnFilterConfiguratorHooker(
		TableReportUtilityDao tableReportUtilityDao	
		){
	
		/* store objects */
		this.tableReportUtilityDao = tableReportUtilityDao;
	}
	
	@Override
	public String getHeadline() {
		return messages.binaryColumnFilterHeadline();
	}

	@Override
	public ImageResource getIcon() {
		return BaseIcon.BALANCE_SCALE.toImageResource();
	}

	@Override
	public void instantiateFilter(TableReportDto report, String executeToken, final PreFilterView.InstantiatePreFilterCallback callback) {
		displayDialog(report, new BinaryColumnFilterDtoDec(), executeToken, callback, null);
	}

	private void displayDialog(TableReportDto report,
			final BinaryColumnFilterDtoDec filter,
			String executeToken, 
			final InstantiatePreFilterCallback callback, final EditPreFilterCallback editCallback) {

		if(null == selectorA)
			selectorA = new ColumnSelector(tableReportUtilityDao, report, executeToken);
		if(null == selectorB)
			selectorB = new ColumnSelector(tableReportUtilityDao, report, executeToken);
		
		final DwWindow dialog = new DwWindow();
		dialog.setHeading(messages.binaryColumnFilterHeadline());
		dialog.setHeaderIcon(BaseIcon.BALANCE_SCALE);
		dialog.setSize(350, 180);
		dialog.setClosable(false);
		dialog.setOnEsc(false);
		
		FormPanel form = new FormPanel();
		form.setBorders(false);
		
		DwFlowContainer fieldWrapper = new DwFlowContainer();
		form.add(fieldWrapper, new MarginData(10));
		
		if(null == columnASelector)
			columnASelector = new SingleSelectionField<ColumnDto>(selectorA);
		if(null == columnBSelector)
			columnBSelector = new SingleSelectionField<ColumnDto>(selectorB);
		
		columnASelector.setValue(filter.getColumnA());
		columnBSelector.setValue(filter.getColumnB());
		
		final SimpleComboBox<String> combo = new SimpleComboBox<String>(new StringLabelProvider<String>());
		combo.setForceSelection(true);
		combo.setTriggerAction(TriggerAction.ALL);

		for(BinaryOperatorDto operator : BinaryOperatorDto.values()){
			combo.add(BinaryColumnFilterDtoDec.getOperatorSymbol(operator));
		}
		
		if(null != filter.getOperator())
			combo.setValue(BinaryColumnFilterDtoDec.getOperatorSymbol(filter.getOperator()));
		
		fieldWrapper.add(new FieldLabel(columnASelector, messages.binaryColumnFilterColumnALabel()));
		fieldWrapper.add(new FieldLabel(columnBSelector, messages.binaryColumnFilterColumnBLabel()));
		fieldWrapper.add(new FieldLabel(combo, messages.binaryColumnFilterOperatorLabel()));
		
		dialog.add(form);
		
		DwTextButton cancelButton = new DwTextButton(BaseMessages.INSTANCE.cancel());
		cancelButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if(null != callback)
					callback.filterInstantiated(null);
				else
					editCallback.editDone();
				dialog.hide();
			}
		});
		dialog.addButton(cancelButton);
		
		DwTextButton okButton = new DwTextButton(BaseMessages.INSTANCE.ok());
		okButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				ColumnDtoDec colA = (ColumnDtoDec) columnASelector.getValue();
				ColumnDtoDec colB = (ColumnDtoDec) columnBSelector.getValue();
				
				if(null == colA)
					return;
				if(null == colB)
					return;
				BinaryOperatorDto operator = BinaryColumnFilterDtoDec.getOperatorForSymbol(combo.getValue());
				if(null == operator)
					return;
				
				filter.setColumnA(colA.cloneColumnForSelection());
				filter.setColumnB(colB.cloneColumnForSelection());
				filter.setOperator(operator);
				
				if(null != callback)
					callback.filterInstantiated(filter);
				else
					editCallback.editDone();
				dialog.hide();
			}
		});
		dialog.addButton(okButton);
		
		dialog.show();
		
	}

	@Override
	public boolean consumes(FilterSpecDto item) {
		return item instanceof BinaryColumnFilterDto;
	}

	@Override
	public void displayFilter(TableReportDto report, FilterSpecDto filter, String executeToken, final EditPreFilterCallback callback) {
		displayDialog(report, (BinaryColumnFilterDtoDec) filter, executeToken, null, callback);
	}
	
	@Override
	public void filterInstantiated(TableReportDto report, FilterSpecDto filter,
			String executeToken, EditPreFilterCallback callback) {
		callback.editDone();
	}

}
