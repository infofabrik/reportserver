package net.datenwerke.rs.base.client.reportengines.table.columnfilter.hookers;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonArrowAlign;
import com.sencha.gxt.cell.core.client.ButtonCell.ButtonScale;
import com.sencha.gxt.cell.core.client.ButtonCell.IconAlign;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwToggleButton;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.eventbus.events.ObjectChangedEvent;
import net.datenwerke.gxtdto.client.eventbus.handlers.ObjectChangedEventHandler;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.hooks.FilterViewEnhanceToolbarHook;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.propertywidgets.FilterView;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ToolbarEnhancerEditFilter implements FilterViewEnhanceToolbarHook {

	private final ToolbarService toolbarService;

	private FilterView filterView;
	private TableReportDto report;
	
	@Inject
	public ToolbarEnhancerEditFilter(
		ToolbarService toolbarService	
		){
		
		/* store obejcts */
		this.toolbarService = toolbarService;
	}
	
	@Override
	public void initialize(FilterView filterView, TableReportDto report) {
		this.filterView = filterView;
		this.report = report;
	}
	
	@Override
	public void enhanceToolbarLeft(ToolBar toolbar) {
		/* */
		DwTextButton editFormat = toolbarService.createSmallButtonLeft(FilterMessages.INSTANCE.editColumnFormat(), BaseIcon.FORMAT);
		editFormat.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				filterView.displayColumnFormatDialog();
			}
		});
		toolbar.add(editFormat);
		
		
		/* display filter dialog */
		DwTextButton editFilter = toolbarService.createSmallButtonLeft(FilterMessages.INSTANCE.editFilter(), BaseIcon.FILTER);
		editFilter.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				filterView.displayFilterDialog();
			}
		});
		toolbar.add(editFilter);

		
		/* set distinct */
		final CheckBox distinctCheckBox = new CheckBox();
		distinctCheckBox.setBoxLabel(FilterMessages.INSTANCE.distinctFilter());
		distinctCheckBox.setValue(report.isDistinctFlag());
		distinctCheckBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			@Override
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				report.setDistinctFlag(distinctCheckBox.getValue());
			}
		});

		if(((TableReportDtoDec)report).hasAggregateColumn())
			distinctCheckBox.disable();

		toolbar.add(distinctCheckBox);
		
		/* */
		final DwToggleButton editSubtotals = new DwToggleButton(FilterMessages.INSTANCE.editSubtotals());
		editSubtotals.setIcon(BaseIcon.SUBTOTALS);
		editSubtotals.setIconAlign(IconAlign.LEFT);
		editSubtotals.setArrowAlign(ButtonArrowAlign.RIGHT);
		editSubtotals.setScale(ButtonScale.SMALL);
		editSubtotals.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				filterView.displaySubtotalsDialog();
			}
		});
		if(report.isEnableSubtotals())
			editSubtotals.setValue(true);
		toolbar.add(editSubtotals);
		
		report.addInstanceChangedHandler(new ObjectChangedEventHandler<Dto>() {
			
			@Override
			public void onObjectChangedEvent(ObjectChangedEvent<Dto> event) {
				if(((TableReportDtoDec)report).hasAggregateColumn()){
					distinctCheckBox.setValue(false, true);
					if(report.isDistinctFlag())
						report.setDistinctFlag(false);
					distinctCheckBox.disable();
				} else
					distinctCheckBox.enable();
				
				boolean silent = report.isSilenceEvents();
				report.silenceEvents(true);
				if(((TableReportDtoDec)report).hasSubtotalGroupColumn()){
					report.setEnableSubtotals(true);
					editSubtotals.setValue(true, true);
				} else {
					report.setEnableSubtotals(false);
					editSubtotals.setValue(false, true);
				}
				report.silenceEvents(silent);
			}
		});
		
	}

	@Override
	public void enhanceToolbarRight(ToolBar toolbar) {

	}

}
