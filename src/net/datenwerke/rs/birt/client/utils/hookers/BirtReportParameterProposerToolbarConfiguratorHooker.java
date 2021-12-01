package net.datenwerke.rs.birt.client.utils.hookers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.window.SimpleDialogWindow;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.ui.helper.grid.DeletableRowsGrid;
import net.datenwerke.gxtdto.client.ui.helper.wrapper.ToolbarWrapperPanel;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.client.reportengines.locale.BirtMessages;
import net.datenwerke.rs.birt.client.utils.BirtUtilsDao;
import net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto;
import net.datenwerke.rs.birt.client.utils.dto.pa.BirtParameterProposalDtoPA;
import net.datenwerke.rs.core.client.parameters.ParameterUIService;
import net.datenwerke.rs.core.client.parameters.backend.ParameterView;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator.ParameterType;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.incubator.client.jasperutils.locale.JasperMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.StringLabelProvider;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.SimpleComboBox;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.GridSelectionModel;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class BirtReportParameterProposerToolbarConfiguratorHooker implements MainPanelViewToolbarConfiguratorHook {
	
	private static BirtParameterProposalDtoPA birtPa = GWT.create(BirtParameterProposalDtoPA.class);
	
	private ToolbarService toolbarUtils;
	private BirtUtilsDao birtUtilsDao;
	private ParameterUIService parameterService;

	@Inject
	public BirtReportParameterProposerToolbarConfiguratorHooker(
			ToolbarService toolbarUtils, 
			BirtUtilsDao birtUtilsDao, 
			ParameterUIService parameterService) {
		
		this.toolbarUtils = toolbarUtils;
		this.birtUtilsDao = birtUtilsDao;
		this.parameterService = parameterService;
	}

	@Override
	public void mainPanelViewToolbarConfiguratorHook_addLeft(final MainPanelView view, ToolBar toolbar, AbstractNodeDto selectedNode) {
		
		if(! (selectedNode instanceof BirtReportDto))
			return;
		if(! (view instanceof ParameterView))
			return;
		
		final BirtReportDto report = (BirtReportDto) selectedNode;


		/* add parameter */
		DwTextButton createPreviewBtn = toolbarUtils.createSmallButtonLeft(BirtMessages.INSTANCE.parameterProposalBtn(), BaseIcon.LIGHTBULB);
		createPreviewBtn.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				birtUtilsDao.proposeParametersFor(report, new RsAsyncCallback<List<BirtParameterProposalDto>>() {

					@Override
					public void onSuccess(List<BirtParameterProposalDto> result) {
						if(null != result && ! result.isEmpty()){
							displayResults((ParameterView) view, report, result);
						}else{ 
							new DwAlertMessageBox(BirtMessages.INSTANCE.noProposalsFoundTitle(), BirtMessages.INSTANCE.noProposalsFoundText()).show();
						}
					}
				});
			}
		});
		toolbar.add(createPreviewBtn);
		
	}

	@Override
	public void mainPanelViewToolbarConfiguratorHook_addRight(
			MainPanelView view, ToolBar toolbar, AbstractNodeDto selectedNode) {
		// TODO Auto-generated method stub

	}
	
	protected void displayResults(final ParameterView view, final BirtReportDto report, List<BirtParameterProposalDto> proposals) {
		/* create store */
		final ListStore<BirtParameterProposalDto> proposalStore = new ListStore<BirtParameterProposalDto>(birtPa.dtoId());
		proposalStore.setAutoCommit(true);
		for(BirtParameterProposalDto proposal : proposals){
			for(ParameterConfigurator configurator : parameterService.getAvailableParameterConfigurators()){
				if(configurator.canHandle(proposal)){
					proposal.setParameterProposal(configurator.getNewDto(proposal, report));
					break;
				}
			}
			proposalStore.add(proposal);
		}
		
		/* configure columns */ 
		List<ColumnConfig<BirtParameterProposalDto,?>> columns = new ArrayList<ColumnConfig<BirtParameterProposalDto,?>>();
		
		/* Name column */
		ColumnConfig<BirtParameterProposalDto,String> nameConfig = new ColumnConfig<BirtParameterProposalDto,String>(birtPa.name(), 200, JasperMessages.INSTANCE.name()); 
		columns.add(nameConfig);
		
		/* Key column */
		ColumnConfig<BirtParameterProposalDto,String> keyConfig = new ColumnConfig<BirtParameterProposalDto,String>(birtPa.key(), 200, JasperMessages.INSTANCE.key()); 
		columns.add(keyConfig);
		
		/* Proposal column */
	    final SimpleComboBox<ParameterConfigurator> proposalCombo = new SimpleComboBox<ParameterConfigurator>(new StringLabelProvider<ParameterConfigurator>(){
	    	@Override
	    	public String getLabel(ParameterConfigurator item) {
	    		return item.getName();
	    	}
	    });  
	    proposalCombo.setForceSelection(true);
	    proposalCombo.setAllowBlank(true);
	    proposalCombo.setTriggerAction(TriggerAction.ALL);
	    for(ParameterConfigurator configurator : parameterService.getAvailableParameterConfigurators())
	    	if(ParameterType.Normal == configurator.getType())
	    		proposalCombo.add(configurator);
	  
		ColumnConfig<BirtParameterProposalDto,ParameterDefinitionDto> proposalConfig = new ColumnConfig<BirtParameterProposalDto,ParameterDefinitionDto>(birtPa.parameterProposal(), 200, JasperMessages.INSTANCE.proposal());
		proposalConfig.setCell(new AbstractCell<ParameterDefinitionDto>() {
			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					ParameterDefinitionDto model, SafeHtmlBuilder sb) {
				if(null != model)
					sb.appendEscaped(parameterService.getConfigurator(model).getName());
			}
		});
		columns.add(proposalConfig);
		
		/* create grid */
		final DeletableRowsGrid<BirtParameterProposalDto> grid = new DeletableRowsGrid<BirtParameterProposalDto>(proposalStore, new ColumnModel<BirtParameterProposalDto>(columns));
		
		grid.setSelectionModel(new GridSelectionModel<BirtParameterProposalDto>());
		grid.getSelectionModel().setSelectionMode(SelectionMode.MULTI);
		grid.getView().setShowDirtyCells(false);
		grid.setHeight(20);
		
		// edit //
		GridEditing<BirtParameterProposalDto> editing = new GridInlineEditing<BirtParameterProposalDto>(grid);
		editing.addEditor(nameConfig, new TextField());
		editing.addEditor(keyConfig, new TextField());
		
		editing.addEditor(proposalConfig, new Converter<ParameterDefinitionDto, ParameterConfigurator>() {
			@Override
			public ParameterDefinitionDto convertFieldValue(ParameterConfigurator object) {
				if(null == object)
		    		  return null;
		    	  
				return object.getNewDto(report);
			}

			@Override
			public ParameterConfigurator convertModelValue(ParameterDefinitionDto object) {
				if(null == object)
		    		  return null;
				
				return parameterService.getConfigurator(object);
			}
		}, proposalCombo);
		
		/* create toolbar wrapper */
		ToolbarWrapperPanel wrapper = new ToolbarWrapperPanel(grid){

			@Override
			protected void removeAllButtonClicked(com.google.gwt.event.logical.shared.SelectionEvent<com.sencha.gxt.widget.core.client.menu.Item> event) {
				proposalStore.clear();
			}
			
			@Override
			protected void removeButtonClicked(SelectEvent ce) {
				for(BirtParameterProposalDto proposal : grid.getSelectionModel().getSelectedItems())
					proposalStore.remove(proposal);
			}
		};
		wrapper.addRemoveButtons();
		
		/* create window */
		DwWindow window = new SimpleDialogWindow(){
			@Override
			protected void submitButtonClicked() {
				addParametersFor(view, report, proposalStore.getAll());
				super.submitButtonClicked();
			}
		};
		window.setHeading(JasperMessages.INSTANCE.windowTitle());
		window.setWidth(800);
		window.setHeight(600);
		window.add(wrapper);
		window.show();
	}


	protected void addParametersFor(final ParameterView view, BirtReportDto report, List<BirtParameterProposalDto> proposalDtos) {
		birtUtilsDao.addParametersFor(report, proposalDtos, new RsAsyncCallback<BirtReportDto>() {
			@Override
			public void onSuccess(BirtReportDto result) {
				if(null != result)
					view.updateStore(result);
			}
		});
	}

}
