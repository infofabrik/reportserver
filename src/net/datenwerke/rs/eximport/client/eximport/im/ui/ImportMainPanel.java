package net.datenwerke.rs.eximport.client.eximport.im.ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer.BorderLayoutData;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.tree.Tree;

import net.datenwerke.gf.client.upload.FileUploadUiService;
import net.datenwerke.gf.client.upload.FileUploadUiService.UploadHandler;
import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadProperties;
import net.datenwerke.gf.client.upload.simpleform.FileUpload;
import net.datenwerke.gf.client.upload.simpleform.SFFCFileUpload;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwBorderContainer;
import net.datenwerke.gxtdto.client.baseex.widget.layout.DwNorthSouthContainer;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.dialog.error.SimpleErrorDialog;
import net.datenwerke.gxtdto.client.dialog.properties.PropertiesDialog;
import net.datenwerke.gxtdto.client.dialog.properties.PropertiesDialogCard;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCTextArea;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavItemSelectionCallback;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavigationModelData;
import net.datenwerke.gxtdto.client.ui.helper.nav.NavigationPanelHelper;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwToolBar;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.client.eximport.RsExImportUiModule;
import net.datenwerke.rs.eximport.client.eximport.im.ImporterDao;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportPostProcessConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterConfiguratorHook;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterPostProcessorConfiguratorHook;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterToolbarHook;
import net.datenwerke.rs.eximport.client.eximport.locale.ExImportMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class ImportMainPanel extends DwContentPanel {

   private final HookHandlerService hookHandler;
   private final ImporterDao importerDao;
   private final NavigationPanelHelper navPanelHelper;
   private final Provider<PropertiesDialog> propertiesDialogProvider;
   private final ToolbarService toolbarService;
   private final FileUploadUiService fileUploadService;

   private ToolBar mainToolbar;

   private DwContentPanel importerNavPanel;
   private DwContentPanel importerMainPanel;

   private Set<ImporterConfiguratorHook> importers;
   private Map<ImporterConfiguratorHook, Widget> componentMap = new HashMap<ImporterConfiguratorHook, Widget>();

   private Set<ImporterPostProcessorConfiguratorHook> postProcessors;

   @Inject
   public ImportMainPanel(HookHandlerService hookHandler, ImporterDao importerDao, NavigationPanelHelper navPanelHelper,
         Provider<PropertiesDialog> propertiesDialogProvider, ToolbarService toolbarService,
         FileUploadUiService fileUploadService, UtilsUIService utilsUIService) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.importerDao = importerDao;
      this.navPanelHelper = navPanelHelper;
      this.propertiesDialogProvider = propertiesDialogProvider;
      this.toolbarService = toolbarService;
      this.fileUploadService = fileUploadService;

      /* init ui */
      initializeUI();
   }

   private void initializeUI() {
      addClassName("rs-import-view");

      /* init us */
      setHeading(ExImportMessages.INSTANCE.importPanelHeader());

      final DwNorthSouthContainer nsContainer = new DwNorthSouthContainer();
      setWidget(nsContainer);

      /* init toolbar */
      initializeToolbar();
      nsContainer.setNorthWidget(mainToolbar);

      /* init inner panels */
      instantiateImporterNavPanel();
      instantiateImporterMainPanel();

      /* init layout */
      BorderLayoutContainer borderContainer = new DwBorderContainer();
      nsContainer.setWidget(borderContainer);

      BorderLayoutData westData = new BorderLayoutData(180);
      westData.setSplit(true);
      westData.setCollapsible(false);
      westData.setMargins(new Margins(0, 2, 0, 0));

      /* add panels */
      borderContainer.setWestWidget(importerNavPanel, westData);
      borderContainer.setWidget(importerMainPanel);

      /* mask panel */
      showBeginToolbar();
      importerNavPanel.clear();
      importerMainPanel.clear();
      componentMap.clear();
      unmask();

      Scheduler.get().scheduleDeferred(new ScheduledCommand() {
         @Override
         public void execute() {
            importerNavPanel.mask();
            importerMainPanel.mask(ExImportMessages.INSTANCE.noImportData());
         }
      });

   }

   private void initializeToolbar() {
      mainToolbar = new DwToolBar();
   }

   private void showBeginToolbar() {
      mainToolbar.clear();

      /* start import button */
      DwTextButton beginImportBtn = toolbarService
            .createSmallButtonLeft(ExImportMessages.INSTANCE.beginImportButtonLabel(), BaseIcon.IMPORT);
      beginImportBtn.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            displayBeginImport();
         }
      });
      mainToolbar.add(beginImportBtn);

      mainToolbar.forceLayout();
   }

   private void initToolbar() {
      mainToolbar.clear();

      /* main options */
      DwTextButton mainOptionsBtn = toolbarService
            .createSmallButtonLeft(ExImportMessages.INSTANCE.mainOptionsButtonLabel(), BaseIcon.COG);
      mainOptionsBtn.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            displayMainOptions();
         }
      });
      mainToolbar.add(mainOptionsBtn);

      mainToolbar.add(new SeparatorToolItem());

      /* abort import button */
      DwTextButton resetImportBtn = toolbarService
            .createSmallButtonLeft(ExImportMessages.INSTANCE.importResetButtonLabel(), BaseIcon.ROTATE_RIGHT);
      resetImportBtn.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            importerDao.reset(new RsAsyncCallback<Void>() {
               @Override
               public void onSuccess(Void result) {
                  resetConfig();
               }
            });
         }
      });
      mainToolbar.add(resetImportBtn);

      DwTextButton abortImportBtn = toolbarService
            .createSmallButtonLeft(ExImportMessages.INSTANCE.importAbortButtonLabel(), BaseIcon.DELETE);
      abortImportBtn.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            importerDao.invalidateConfig(new RsAsyncCallback<Void>() {
               @Override
               public void onSuccess(Void result) {
                  abort();
               }
            });
         }
      });
      mainToolbar.add(abortImportBtn);

      mainToolbar.add(new SeparatorToolItem());

      for (ImporterToolbarHook toolbarHooker : hookHandler.getHookers(ImporterToolbarHook.class))
         toolbarHooker.enhance(mainToolbar, this);

      mainToolbar.add(new FillToolItem());
      DwTextButton submitImportBtn = toolbarService
            .createSmallButtonLeft(ExImportMessages.INSTANCE.importSubmitButtonLabel(), BaseIcon.IMPORT);
      submitImportBtn.addSelectHandler(new SelectHandler() {

         @Override
         public void onSelect(SelectEvent event) {
            configureAndImport();
         }
      });
      mainToolbar.add(submitImportBtn);

      mainToolbar.forceLayout();
   }

   protected void displayMainOptions() {
      final PropertiesDialog dialog = propertiesDialogProvider.get();
      dialog.setSize(640, 480);
      dialog.setHeading(ExImportMessages.INSTANCE.mainOptionsButtonLabel());
      dialog.setHeaderIcon(BaseIcon.COG);

      for (ImporterPostProcessorConfiguratorHook processor : postProcessors) {
         PropertiesDialogCard card = processor.getCard();
         if (null != card)
            dialog.addCard(card);
      }

      dialog.show();
   }

   protected void configureAndImport() {
      mask(ExImportMessages.INSTANCE.performImport());

      Map<String, ImportConfigDto> configMap = new HashMap<String, ImportConfigDto>();
      for (ImporterConfiguratorHook importer : importers) {
         try {
            ImportConfigDto config = importer.getConfiguration();
            if (null != config)
               configMap.put(importer.getImporterId(), config);
         } catch (NotProperlyConfiguredException e) {
            SimpleErrorDialog dialog = new SimpleErrorDialog(BaseMessages.INSTANCE.error(), e.getMessage());
            dialog.show();
            unmask();
            return;
         }
      }

      Map<String, ImportPostProcessConfigDto> postProcessorMap = new HashMap<String, ImportPostProcessConfigDto>();

      for (ImporterPostProcessorConfiguratorHook postProcessor : postProcessors) {
         try {
            ImportPostProcessConfigDto config = postProcessor.getConfiguration();
            if (null != config)
               postProcessorMap.put(postProcessor.getPostProcessorId(), config);
         } catch (NotProperlyConfiguredException e) {
            SimpleErrorDialog dialog = new SimpleErrorDialog(BaseMessages.INSTANCE.error(), e.getMessage());
            dialog.show();
            unmask();
            return;
         }
      }

      importerDao.performImport(configMap, postProcessorMap,
            new NotamCallback<Void>(ExImportMessages.INSTANCE.importSuccess()) {
               @Override
               public void doOnSuccess(Void result) {
                  clearConfig();
               }

               @Override
               public void doOnFailure(Throwable caught) {
                  unmask();
               }
            });
   }

   protected void clearConfig() {
      ConfirmMessageBox cmb = new DwConfirmMessageBox(ExImportMessages.INSTANCE.clearConfigTitle(),
            ExImportMessages.INSTANCE.clearConfigMsg());
      cmb.addDialogHideHandler(new DialogHideHandler() {

         @Override
         public void onDialogHide(DialogHideEvent event) {
            if (event.getHideButton() == PredefinedButton.YES)
               resetConfig();
         }
      });
      cmb.show();
      unmask();
   }

   public void resetConfig() {
      for (ImporterConfiguratorHook config : importers)
         config.reset();
      for (ImporterPostProcessorConfiguratorHook config : postProcessors)
         config.reset();
   }

   public void abort() {
      showBeginToolbar();
      importerNavPanel.clear();
      importerMainPanel.clear();
      componentMap.clear();
      importerNavPanel.mask();
      importerMainPanel.mask(ExImportMessages.INSTANCE.noImportData());
      unmask();
   }

   private void instantiateImporterMainPanel() {
      importerMainPanel = DwContentPanel.newInlineInstance();
      importerMainPanel.addClassName("rs-import-view-main");
   }

   private void instantiateImporterNavPanel() {
      importerNavPanel = DwContentPanel.newInlineInstance();
      importerNavPanel.addClassName("rs-import-view-nav");
   }

   private void initImporterNavPanel(Collection<String> ids) {
      /* display modules in tree */
      TreeStore<NavigationModelData<ImporterConfiguratorHook>> store = new TreeStore<NavigationModelData<ImporterConfiguratorHook>>(
            new BasicObjectModelKeyProvider<NavigationModelData<ImporterConfiguratorHook>>());

      /* add modules */
      for (ImporterConfiguratorHook importer : importers) {
         store.add(new NavigationModelData<ImporterConfiguratorHook>(importer.getImporterName(),
               importer.getImporterIcon(), importer));
      }

      /* build tree */
      final Tree<NavigationModelData<ImporterConfiguratorHook>, String> tree = navPanelHelper
            .createNavigationTreePanel(store, new NavItemSelectionCallback<ImporterConfiguratorHook>() {
               @Override
               public void selected(ImporterConfiguratorHook model) {
                  showImporter(model);
               }
            });

      /* add tree */
      importerNavPanel.add(tree);

      /* redo layout */
      importerNavPanel.forceLayout();

   }

   protected void showImporter(ImporterConfiguratorHook model) {
      importerMainPanel.clear();

      if (!componentMap.containsKey(model))
         componentMap.put(model, model.initConfigPanel(this));
      importerMainPanel.add(componentMap.get(model));

      importerMainPanel.forceLayout();
   }

   protected void init(Collection<String> ids) {
      loadImporters(ids);
      loadPostProcessors();
      initImporterNavPanel(ids);
      initToolbar();

      importerNavPanel.unmask();
      importerMainPanel.unmask();
   };

   private void loadPostProcessors() {
      postProcessors = new HashSet<ImporterPostProcessorConfiguratorHook>();
      for (ImporterPostProcessorConfiguratorHook postProcessor : hookHandler
            .getHookers(ImporterPostProcessorConfiguratorHook.class))
         postProcessors.add(postProcessor);
   }

   private void loadImporters(Collection<String> ids) {
      importers = new HashSet<ImporterConfiguratorHook>();
      for (ImporterConfiguratorHook importer : hookHandler.getHookers(ImporterConfiguratorHook.class)) {
         if (null != importer.getSupportedExporters()) {
            for (String supportedExporter : importer.getSupportedExporters()) {
               if (ids.contains(supportedExporter)) {
                  importers.add(importer);
                  break;
               }
            }
         }
      }
   }

   protected void displayBeginImport() {
      final DwWindow window = new DwWindow();
      window.setSize(640, 520);
      window.setHeading(ExImportMessages.INSTANCE.beginImportButtonLabel());
      window.setHeaderIcon(BaseIcon.IMPORT);
      window.setModal(true);

      final SimpleForm form = SimpleForm.getInlineInstance();

      fileUploadService.prepareForUpload(form);

      window.add(form, new MarginData(5));
      form.setLabelAlign(LabelAlign.TOP);

      final String fileUploadKey = form.addField(FileUpload.class, RsExImportUiModule.IMPORT_DATA_FILE_NAME,
            ExImportMessages.INSTANCE.uploadImportFileDataLabel(), new SFFCFileUpload() {
               @Override
               public UploadProperties getProperties() {
                  UploadProperties uploadProperties = new UploadProperties("importdata",
                        RsExImportUiModule.UPLOAD_HANDLER_ID);
                  return uploadProperties;
               }

               @Override
               public void filesUploaded(List<FileToUpload> list) {
               }

               @Override
               public boolean enableHTML5() {
                  return true;
               }
            });

      final String dataKey = form.addField(String.class, ExImportMessages.INSTANCE.uploadImportDataLabel(),
            new SFFCTextArea() {

               @Override
               public int getWidth() {
                  return 580;
               }

               @Override
               public int getHeight() {
                  return 250;
               }
            });

      form.loadFields();

      /* attach submit listener */
      fileUploadService.handleUploadReturn(form, new UploadHandler() {
         @Override
         public void onSuccess(JSONValue result) {
            form.unmask();
            importerDao.initViaFile(new RsAsyncCallback<Collection<String>>() {
               public void onSuccess(Collection<String> ids) {
                  window.hide();
                  init(ids);
               }
            });
         }

         @Override
         public void onError() {
            form.unmask();
         }
      });

      DwTextButton okButton = new DwTextButton(BaseMessages.INSTANCE.submit());
      window.addButton(okButton);
      okButton.addSelectHandler(new SelectHandler() {
         @Override
         public void onSelect(SelectEvent event) {
            form.mask(BaseMessages.INSTANCE.storingMsg());
            String fileName = (String) form.getValue(fileUploadKey);
            if (null != fileName && !"".equals(fileName)) {
               /* import via file */
               form.submit();
            } else {
               /* import via textfield */

               String data = (String) form.getValue(dataKey);

               if (null != data && !"".equals(data)) {
                  importerDao.uploadXML(data, new RsAsyncCallback<Collection<String>>() {
                     public void onSuccess(Collection<String> ids) {
                        window.hide();
                        init(ids);
                     }

                     public void onFailure(Throwable caught) {
                        form.unmask();
                     };
                  });
               }
            }
         }
      });

      window.show();
   }

}
