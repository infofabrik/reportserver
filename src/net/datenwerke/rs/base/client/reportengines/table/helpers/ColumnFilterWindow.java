package net.datenwerke.rs.base.client.reportengines.table.helpers;

import java.util.Arrays;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.widget.core.client.button.ToolButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dtomanager.redoundo.UndoManager;
import net.datenwerke.gxtdto.client.forms.binding.HasValueFieldBinding;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.form.combo.EmptyableSimpleComboBox;
import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.gxtdto.client.widgets.SeparatorTextLabel;
import net.datenwerke.rs.base.client.AvailableReportProperties;
import net.datenwerke.rs.base.client.reportengines.BaseReportEngineUiModule;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.NullHandlingDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.TableReportDtoDec;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.ColumnDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.dto.pa.FilterDtoPA;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.LikeFilterComponent;
import net.datenwerke.rs.core.client.contexthelp.ContextHelpUiService;
import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ColumnFilterWindow extends DwWindow {

	@Inject
	private static ContextHelpUiService contextHelpService;
	
	private LikeFilterComponent filterComponent;
	private String executeToken;
	
	public ColumnFilterWindow(
			TableReportDto report, ColumnDto column, String executeToken	
			){
		this(report, column, executeToken, 
			"enable".equals(((TableReportDtoDec)report).getEffectiveReportProperty(AvailableReportProperties.PROPERTY_DL_FILTER_DEFAULT_CONSISTENCY.getValue(), "enable")),  
			"true".equals(((TableReportDtoDec)report).getEffectiveReportProperty(AvailableReportProperties.PROPERTY_DL_FILTER_SHOW_CONSISTENCY.getValue(), "true")));
	}
	
	public ColumnFilterWindow(
		TableReportDto report, ColumnDto column, String executeToken, boolean forceConsistency, boolean showConsistency	
		){
	
		this.executeToken = executeToken;
		initUi(report, column, forceConsistency, showConsistency);
	}

	private void initUi(TableReportDto report, final ColumnDto column, boolean forceConsistency, boolean showConsistency) {
		setSize(900, 650);
		addClassName("rs-column-filter");
		setModal(true);
		setOnEsc(false);
		setClosable(false);
		setHeaderIcon(BaseIcon.TABLE_EDIT);
		setHeading(FilterMessages.INSTANCE.filterDialogHeading(column.getName(), null != column.getType() ? SqlTypes.getName(column.getType()) : ""));
		setBodyBorder(true);
		setButtonAlign(BoxLayoutPack.START);

		if(contextHelpService.isEnabled()){
			ContextHelpInfo contextHelpInfo = new ContextHelpInfo("dynamiclist/columnfilter");
			ToolButton infoBtn = contextHelpService.createToolButton(contextHelpInfo);
			addTool(infoBtn);
		}
		
		/* LikeFilter Component */
		filterComponent = new LikeFilterComponent(report, column, executeToken, forceConsistency, showConsistency); 
		add(filterComponent);

		/* null handling */
		Label nullHandlingLabel = SeparatorTextLabel.createSmallText(FilterMessages.INSTANCE.nullHandlingLabel());
		addButton(nullHandlingLabel);
		addButton(createNullHandlingCombo(column));
		
		/* case sensitive */
		addButton(new SeparatorToolItem());
		addCaseSensitiveBox(getButtonBar(), column);
		
		final UndoManager undoer = new UndoManager(column);
		
		/* submit */
		DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.apply());
		submitBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if(filterComponent.validate()){
					undoer.clear();
					hide();
				}
			}
		});
		
		
		DwTextButton cancelBtn = new DwTextButton(BaseMessages.INSTANCE.cancel());
		cancelBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				filterComponent.cleanup();
				undoer.rollback();
				hide();
			}
		});
		
		addButton(new FillToolItem());
		addButton(cancelBtn);
		addButton(submitBtn);
	}
	
	private void addCaseSensitiveBox(ToolBar filterDialogToolbar, final ColumnDto column) {
		final CheckBox box = new CheckBox();
		filterDialogToolbar.add(box);
		
		Label textLabel = SeparatorTextLabel.createSmallText(FilterMessages.INSTANCE.caseSensitiveLabel());
		textLabel.addDomHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(null == box.getValue() || ! box.getValue())
					box.setValue(true, true);
				else
					box.setValue(false, true);
			}
		}, ClickEvent.getType());
		filterDialogToolbar.add(textLabel);
		
		
		if(null == column.getFilter())
			column.setFilter(new FilterDtoDec());
		new HasValueFieldBinding(box, column.getFilter(), FilterDtoPA.INSTANCE.caseSensitive());
	}

	private SimpleComboBox<Object> createNullHandlingCombo(final ColumnDto column) {
		final EmptyableSimpleComboBox<NullHandlingDto> combo = new EmptyableSimpleComboBox<NullHandlingDto>();
		
		combo.setWidth(180);
		combo.setTriggerAction(TriggerAction.ALL);
		
		combo.addAll(Arrays.asList(NullHandlingDto.values()));

		combo.createValueBinding(column, ColumnDtoPA.INSTANCE.nullHandling());
		

		return combo;
	}

	public void setForceConsistency(boolean force) {
		filterComponent.setForceConsistency(force);
	}

	public void hideForceConsistency() {
		filterComponent.hideForceConsistency();
	}
}
