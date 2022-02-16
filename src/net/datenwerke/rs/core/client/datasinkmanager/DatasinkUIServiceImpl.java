package net.datenwerke.rs.core.client.datasinkmanager;

import static net.datenwerke.rs.core.client.datasinkmanager.helper.forms.simpleform.DatasinkSelectionFieldProvider.extractSingleTreeSelectionField;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCAllowBlank;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCDatasinkDao;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.config.DatasinkDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionFieldFactory;
import net.datenwerke.rs.core.client.helper.ObjectHolder;
import net.datenwerke.rs.core.client.helper.simpleform.ExportTypeSelection;
import net.datenwerke.rs.core.client.helper.simpleform.config.SFFCExportTypeSelector;
import net.datenwerke.rs.core.client.reportexecutor.hooks.PrepareReportModelForStorageOrExecutionHook;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.eximport.client.eximport.locale.ExImportMessages;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.locale.ScheduleAsFileMessages;
import net.datenwerke.rs.scheduler.client.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public class DatasinkUIServiceImpl implements DatasinkUIService {

   private final HookHandlerService hookHandlerService;

   private Map<Class<? extends DatasinkDefinitionDto>, Provider<? extends DatasinkDefinitionConfigConfigurator>> configuratorLookup;
   private final DatasinkSelectionFieldFactory fieldFactory;

   private final Provider<DatasinkDao> datasinkDaoProvider;

   @Inject
   public DatasinkUIServiceImpl(HookHandlerService hookHandlerService, DatasinkSelectionFieldFactory fieldFactory,
         Provider<DatasinkDao> datasinkDaoProvider) {

      /* store objects */
      this.hookHandlerService = hookHandlerService;
      this.fieldFactory = fieldFactory;
      this.datasinkDaoProvider = datasinkDaoProvider;
   }

   public DatasinkDefinitionConfigConfigurator getConfigurator(Class<? extends DatasinkDefinitionDto> configClazz) {
      if (null == configuratorLookup)
         initConfigurator();

      Provider<? extends DatasinkDefinitionConfigConfigurator> provider = configuratorLookup.get(configClazz);
      if (null == provider)
         throw new IllegalStateException("I should probably know the provider for " + configClazz.getName()); //$NON-NLS-1$
      return provider.get();
   }

   protected void initConfigurator() {
      configuratorLookup = new HashMap<Class<? extends DatasinkDefinitionDto>, Provider<? extends DatasinkDefinitionConfigConfigurator>>();

   }

   @Override
   public DatasinkSelectionField getSelectionField(Provider<? extends HasDefaultDatasink> datasinkDaoProvider,
         BaseIcon defaultDatasinkIcon, Container container, Provider<UITree> datasinkTreeProvider,
         Class<? extends DatasinkDefinitionDto>... types) {
      return fieldFactory.create(datasinkDaoProvider, defaultDatasinkIcon, container, datasinkTreeProvider.get(),
            types);
   }

   @Override
   public DatasinkSelectionField getSelectionField(Provider<? extends HasDefaultDatasink> datasinkDaoProvider,
         BaseIcon defaultDatasinkIcon, Container container, Provider<UITree> datasinkTreeProvider) {
      return fieldFactory.create(datasinkDaoProvider, defaultDatasinkIcon, container, datasinkTreeProvider.get(),
            new Class[] {});
   }

   @Override
   public DatasinkSelectionField getSelectionField(Provider<? extends HasDefaultDatasink> datasinkDaoProvider,
         BaseIcon defaultDatasinkIcon, Container container, UITree datasinkTree) {
      return fieldFactory.create(datasinkDaoProvider, defaultDatasinkIcon, container, datasinkTree, new Class[] {});
   }

   @Override
   public void displaySendToDatasinkDialog(
         final Class<? extends DatasinkDefinitionDto> datasinkType,
         final String filename, 
         final Provider<UITree> treeProvider,
         final Provider<? extends HasDefaultDatasink> datasinkDaoProvider, 
         final AbstractNodeDto toExport,
         final Optional<ReportExecutorInformation> reportInfo,
         final AsyncCallback<Map<String, Object>> onSelectHandler
         ) {
      if (!(toExport instanceof AbstractFileServerNodeDto) && !(toExport instanceof ReportDto))
         throw new IllegalArgumentException(toExport.getClass() + " not supported");

      if (toExport instanceof ReportDto && !reportInfo.isPresent())
         throw new IllegalArgumentException("Report info not available");

      final Optional<DatasinkSendToFormConfiguratorHook> formConfiguratorOpt = hookHandlerService
            .getHookers(DatasinkSendToFormConfiguratorHook.class)
            .stream()
            .filter(hooker -> hooker.consumes(datasinkType))
            .findAny();
      if (!formConfiguratorOpt.isPresent())
         throw new IllegalStateException("no form configurator found for datasink " + datasinkType);
      final DatasinkSendToFormConfiguratorHook formConfigurator = formConfiguratorOpt.get();

      final DwWindow window = new DwWindow();
      window.setHeaderIcon(formConfigurator.getIcon());
      window.setHeading(formConfigurator.getWindowTitle());
      window.setWidth(500);
      window.setHeight(formConfigurator.getWindowHeight());
      window.setCenterOnShow(true);

      DwContentPanel wrapper = new DwContentPanel();
      wrapper.setHeading(BaseMessages.INSTANCE.configuration());
      wrapper.setLightDarkStyle();
      wrapper.setBodyBorder(false);
      wrapper.setBorders(false);

      VerticalLayoutContainer formWrapper = new VerticalLayoutContainer();

      /* configure form */
      final SimpleForm form = SimpleForm.getInlineInstance();

      formWrapper.add(form);

      form.setFieldWidth(215);
      form.beginFloatRow();

      final String datasinkKey = form.addField(DatasinkSelectionField.class, formConfigurator.getWindowTitle(),
            new SFFCGenericTreeNode() {
               @Override
               public UITree getTreeForPopup() {
                  return treeProvider.get();
               }
            }, new SFFCAllowBlank() {
               @Override
               public boolean allowBlank() {
                  return false;
               }
            }, new SFFCDatasinkDao() {
               @Override
               public Provider<? extends HasDefaultDatasink> getDatasinkDaoProvider() {
                  return datasinkDaoProvider;
               }

               @Override
               public BaseIcon getIcon() {
                  return formConfigurator.getIcon();
               }
            });

      final ObjectHolder<String> folderKey = new ObjectHolder<>();
      if (formConfigurator.isFolderedDatasink()) {
         folderKey.set(form.addField(String.class, ScheduleAsFileMessages.INSTANCE.folder(), new SFFCAllowBlank() {
            @Override
            public boolean allowBlank() {
               return false;
            }
         }));
      }

      form.endRow();
      form.setFieldWidth(1);

      final ObjectHolder<String> formatKey = new ObjectHolder<>();
      if (toExport instanceof ReportDto) {
         formatKey.set(form.addField(ExportTypeSelection.class, ScheduleAsFileMessages.INSTANCE.exportTypeLabel(),
               new SFFCExportTypeSelector() {
                  @Override
                  public ReportDto getReport() {
                     return (ReportDto) toExport;
                  }

                  @Override
                  public String getExecuteReportToken() {
                     return reportInfo.get().getExecuteReportToken();
                  }

                  @Override
                  public Optional<Class<? extends DatasinkDefinitionDto>> getDatasinkType() {
                     return Optional.of(datasinkType);
                  }
               }));
      }

      final String nameKey = form.addField(String.class, ScheduleAsFileMessages.INSTANCE.nameLabel(),
            new SFFCAllowBlank() {
               @Override
               public boolean allowBlank() {
                  return false;
               }
            });

      /* add additional fields */
      formConfigurator.installAdditionalFields(form);

      final ObjectHolder<String> compressedKey = new ObjectHolder<>();

      if (formConfigurator.isCanCompress()) {
         if (toExport instanceof FileServerFileDto || toExport instanceof ReportDto) {
            compressedKey.set(form.addField(Boolean.class, "", new SFFCBoolean() {
               @Override
               public String getBoxLabel() {
                  if (toExport instanceof FileServerFileDto)
                     return FileServerMessages.INSTANCE.fileCompress();
                  else
                     return SchedulerMessages.INSTANCE.reportCompress();
               }
            }));
         }
      }

      wrapper.setWidget(formWrapper);
      window.add(wrapper, new MarginData(10));

      /* set properties */
      if (filename.contains("."))
         form.setValue(nameKey, filename.substring(0, filename.lastIndexOf(".")));
      else
         form.setValue(nameKey, filename);

      /* load fields */
      form.loadFields();

      final SingleTreeSelectionField datasinkField = extractSingleTreeSelectionField(form.getField(datasinkKey));

      if (formConfigurator.isFolderedDatasink()) {
         datasinkField.addValueChangeHandler(event -> {
            if (null == event.getValue())
               return;

            this.datasinkDaoProvider.get().getDefaultFolder((DatasinkDefinitionDto) event.getValue(),
                  new RsAsyncCallback<String>() {
                     @Override
                     public void onSuccess(String result) {
                        form.setValue(folderKey.get(), result);
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                        super.onFailure(caught);
                     }
                  });
         });
      }

      window.addCancelButton();

      DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
      submitBtn.addSelectHandler(event -> {
         form.clearInvalid();

         if (!form.isValid())
            return;

         if (toExport instanceof ReportDto) {
            ExportTypeSelection type = (ExportTypeSelection) form.getValue(formatKey.get());

            if (!type.isConfigured()) {
               new DwAlertMessageBox(BaseMessages.INSTANCE.error(),
                     ReportExporterMessages.INSTANCE.exportTypeNotConfigured()).show();
               return;
            }

            hookHandlerService.getHookers(PrepareReportModelForStorageOrExecutionHook.class)
               .stream()
               .filter(hooker -> hooker.consumes((ReportDto) toExport))
               .forEach(hooker -> hooker
                        .prepareForExecutionOrStorage((ReportDto) toExport, reportInfo.get().getExecuteReportToken()));
         }

         InfoConfig infoConfig = new DefaultInfoConfig(ExImportMessages.INSTANCE.quickExportProgressTitle(),
               ExImportMessages.INSTANCE.exportWait());
         infoConfig.setWidth(350);
         infoConfig.setDisplay(3500);
         Info.display(infoConfig);

         Map<String, Object> values = new HashMap<>();
         values.put(DatasinkUIModule.DATASINK_KEY, form.getValue(datasinkKey));
         values.put(DatasinkUIModule.DATASINK_FILENAME, ((String) form.getValue(nameKey)).trim());
         if (formConfigurator.isFolderedDatasink())
            values.put(DatasinkUIModule.DATASINK_FOLDER, ((String) form.getValue(folderKey.get())).trim());
         if (toExport instanceof ReportDto)
            values.put(DatasinkUIModule.REPORT_FORMAT_KEY, ((ExportTypeSelection) form.getValue(formatKey.get())));
         if (formConfigurator.isCanCompress()) {
            if (toExport instanceof FileServerFileDto || toExport instanceof ReportDto)
               values.put(DatasinkUIModule.DATASINK_COMPRESSED_KEY, (boolean) form.getValue(compressedKey.get()));
            else
               values.put(DatasinkUIModule.DATASINK_COMPRESSED_KEY, true);
         } else {
            values.put(DatasinkUIModule.DATASINK_COMPRESSED_KEY, false);
         }

         /* add additional values */
         Optional<Map<String, Object>> additionalValues = formConfigurator.getAdditionalFieldsValues(form);
         if (additionalValues.isPresent())
            values.putAll(additionalValues.get());

         onSelectHandler.onSuccess(values);
         window.hide();
      });
      window.addButton(submitBtn);

      window.show();
   }

}
