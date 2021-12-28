package net.datenwerke.rs.incubator.client.jasperutils.hookers;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.Converter;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.StringLabelProvider;
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

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.window.SimpleDialogWindow;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.ui.helper.grid.DeletableRowsGrid;
import net.datenwerke.gxtdto.client.ui.helper.wrapper.ToolbarWrapperPanel;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.base.client.jasperutils.dto.JasperParameterProposalDto;
import net.datenwerke.rs.base.client.jasperutils.dto.pa.JasperParameterProposalDtoPA;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.core.client.parameters.ParameterUIService;
import net.datenwerke.rs.core.client.parameters.backend.ParameterView;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator.ParameterType;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.incubator.client.jasperutils.JasperUtilsDao;
import net.datenwerke.rs.incubator.client.jasperutils.locale.JasperMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class JasperReportParameterProposerToolbarConfiguratorHooker implements MainPanelViewToolbarConfiguratorHook {

   private static JasperParameterProposalDtoPA jasperPa = GWT.create(JasperParameterProposalDtoPA.class);

   private final ToolbarService toolbarUtils;
   private final JasperUtilsDao jasperUtilsDao;
   private final ParameterUIService parameterService;

   @Inject
   public JasperReportParameterProposerToolbarConfiguratorHooker(ToolbarService toolbarUtils,
         JasperUtilsDao jasperUtilsDao, ParameterUIService parameterService) {

      /* store objects */
      this.toolbarUtils = toolbarUtils;
      this.jasperUtilsDao = jasperUtilsDao;
      this.parameterService = parameterService;
   }

   public void mainPanelViewToolbarConfiguratorHook_addLeft(final MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
      if (!(selectedNode instanceof JasperReportDto))
         return;
      if (!(view instanceof ParameterView))
         return;

      final JasperReportDto report = (JasperReportDto) selectedNode;

      /* add parameter */
      DwTextButton createPreviewBtn = toolbarUtils.createSmallButtonLeft(JasperMessages.INSTANCE.parameterProposalBtn(),
            BaseIcon.LIGHTBULB);
      createPreviewBtn.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            jasperUtilsDao.proposeParametersFor(report, new RsAsyncCallback<List<JasperParameterProposalDto>>() {

               @Override
               public void onSuccess(List<JasperParameterProposalDto> result) {
                  if (null != result && !result.isEmpty())
                     displayResults((ParameterView) view, report, result);
                  else
                     new DwAlertMessageBox(JasperMessages.INSTANCE.noProposalsFoundTitle(),
                           JasperMessages.INSTANCE.noProposalsFoundText()).show();
               }
            });
         }
      });
      toolbar.add(createPreviewBtn);
   }

   public void mainPanelViewToolbarConfiguratorHook_addRight(MainPanelView view, ToolBar toolbar,
         AbstractNodeDto selectedNode) {
   }

   protected void displayResults(final ParameterView view, final JasperReportDto report,
         List<JasperParameterProposalDto> proposals) {
      /* create store */
      final ListStore<JasperParameterProposalDto> proposalStore = new ListStore<JasperParameterProposalDto>(
            jasperPa.dtoId());
      proposalStore.setAutoCommit(true);
      for (JasperParameterProposalDto proposal : proposals) {
         for (ParameterConfigurator configurator : parameterService.getAvailableParameterConfigurators()) {
            if (configurator.canHandle(proposal)) {
               proposal.setParameterProposal(configurator.getNewDto(proposal, report));
               break;
            }
         }
         proposalStore.add(proposal);
      }

      /* configure columns */
      List<ColumnConfig<JasperParameterProposalDto, ?>> columns = new ArrayList<ColumnConfig<JasperParameterProposalDto, ?>>();

      /* Name column */
      ColumnConfig<JasperParameterProposalDto, String> nameConfig = new ColumnConfig<JasperParameterProposalDto, String>(
            jasperPa.name(), 200, JasperMessages.INSTANCE.name());
      columns.add(nameConfig);

      /* Key column */
      ColumnConfig<JasperParameterProposalDto, String> keyConfig = new ColumnConfig<JasperParameterProposalDto, String>(
            jasperPa.key(), 200, JasperMessages.INSTANCE.key());
      columns.add(keyConfig);

      /* Proposal column */
      final SimpleComboBox<ParameterConfigurator> proposalCombo = new SimpleComboBox<ParameterConfigurator>(
            new StringLabelProvider<ParameterConfigurator>() {
               @Override
               public String getLabel(ParameterConfigurator item) {
                  return item.getName();
               }
            });
      proposalCombo.setForceSelection(true);
      proposalCombo.setAllowBlank(true);
      proposalCombo.setTriggerAction(TriggerAction.ALL);
      for (ParameterConfigurator configurator : parameterService.getAvailableParameterConfigurators())
         if (ParameterType.Normal == configurator.getType())
            proposalCombo.add(configurator);

      ColumnConfig<JasperParameterProposalDto, ParameterDefinitionDto> proposalConfig = new ColumnConfig<JasperParameterProposalDto, ParameterDefinitionDto>(
            jasperPa.parameterProposal(), 200, JasperMessages.INSTANCE.proposal());
      proposalConfig.setCell(new AbstractCell<ParameterDefinitionDto>() {
         @Override
         public void render(com.google.gwt.cell.client.Cell.Context context, ParameterDefinitionDto model,
               SafeHtmlBuilder sb) {
            if (null != model)
               sb.appendEscaped(parameterService.getConfigurator(model).getName());
         }
      });
      columns.add(proposalConfig);

      /* create grid */
      final DeletableRowsGrid<JasperParameterProposalDto> grid = new DeletableRowsGrid<JasperParameterProposalDto>(
            proposalStore, new ColumnModel<JasperParameterProposalDto>(columns));

      grid.setSelectionModel(new GridSelectionModel<JasperParameterProposalDto>());
      grid.getSelectionModel().setSelectionMode(SelectionMode.MULTI);
      grid.getView().setShowDirtyCells(false);
      grid.setHeight(20);

      // edit //
      GridEditing<JasperParameterProposalDto> editing = new GridInlineEditing<JasperParameterProposalDto>(grid);
      editing.addEditor(nameConfig, new TextField());
      editing.addEditor(keyConfig, new TextField());

      editing.addEditor(proposalConfig, new Converter<ParameterDefinitionDto, ParameterConfigurator>() {
         @Override
         public ParameterDefinitionDto convertFieldValue(ParameterConfigurator object) {
            if (null == object)
               return null;

            return object.getNewDto(report);
         }

         @Override
         public ParameterConfigurator convertModelValue(ParameterDefinitionDto object) {
            if (null == object)
               return null;

            return parameterService.getConfigurator(object);
         }
      }, proposalCombo);

      /* create toolbar wrapper */
      ToolbarWrapperPanel wrapper = new ToolbarWrapperPanel(grid) {

         @Override
         protected void removeAllButtonClicked(
               com.google.gwt.event.logical.shared.SelectionEvent<com.sencha.gxt.widget.core.client.menu.Item> event) {
            proposalStore.clear();
         }

         @Override
         protected void removeButtonClicked(SelectEvent ce) {
            for (JasperParameterProposalDto proposal : grid.getSelectionModel().getSelectedItems())
               proposalStore.remove(proposal);
         }
      };
      wrapper.addRemoveButtons();

      /* create window */
      DwWindow window = new SimpleDialogWindow() {
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

   protected void addParametersFor(final ParameterView view, JasperReportDto report,
         List<JasperParameterProposalDto> proposalDtos) {
      jasperUtilsDao.addParametersFor(report, proposalDtos, new RsAsyncCallback<JasperReportDto>() {
         @Override
         public void onSuccess(JasperReportDto result) {
            if (null != result)
               view.updateStore(result);
         }
      });
   }

}
