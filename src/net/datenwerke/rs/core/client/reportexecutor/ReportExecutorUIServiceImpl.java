package net.datenwerke.rs.core.client.reportexecutor;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import javax.inject.Provider;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Component;

import net.datenwerke.gf.client.dispatcher.DispatcherService;
import net.datenwerke.gf.client.homepage.DwMainViewportUiService;
import net.datenwerke.gf.client.homepage.modules.ClientTempModule;
import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dialog.error.SimpleErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.waitonevent.AsynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.events.CompositeExecutorEventHandler;
import net.datenwerke.rs.core.client.reportexecutor.events.ExecutorEventHandler;
import net.datenwerke.rs.core.client.reportexecutor.events.ReportExecutorEvent;
import net.datenwerke.rs.core.client.reportexecutor.events.VariantChangedEvent;
import net.datenwerke.rs.core.client.reportexecutor.events.VariantCreatedEvent;
import net.datenwerke.rs.core.client.reportexecutor.events.VariantDeletedEvent;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;
import net.datenwerke.rs.core.client.reportexecutor.module.ReportExecuteAreaModule;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportViewConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.ImageReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.JsViewerReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.NativeReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportexecutor.variantstorer.VariantStorerConfig;
import net.datenwerke.rs.core.client.reportexecutor.variantstorer.VariantStorerConfigImpl;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;

public class ReportExecutorUIServiceImpl implements ReportExecutorUIService {

   private final LoginService loginService;
   private final HookHandlerService hookHandler;
   private final Provider<ReportExecuteAreaModule> executeModuleProvider;
   private final DwMainViewportUiService viewportService;
   private final ReportExecutorDao reportExecutorDao;
   private final Provider<VariantStorerConfigImpl> variantStorerConfigProvider;
   private final Provider<ImageReportPreviewView> imagePreviewProvider;
   private final Provider<JsViewerReportPreviewView> jsPreviewProvider;
   private final Provider<NativeReportPreviewView> nativePreviewProvider;
   private final TsDiskDao tsDiskDao;

   private String previewMode;

   private Integer defaultColumnWidth;

   private Integer maxColumnWidth;

   @Inject
   public ReportExecutorUIServiceImpl(HookHandlerService hookHandler, LoginService loginService,
         Provider<ReportExecuteAreaModule> executeModuleProvider, DwMainViewportUiService viewportService,
         final ReportExecutorDao reportExecutorDao, Provider<VariantStorerConfigImpl> variantStorerConfigProvider,
         WaitOnEventUIService waitOnEventService, Provider<ImageReportPreviewView> imagePreviewProvider,
         Provider<JsViewerReportPreviewView> jsPreviewProvider, Provider<NativeReportPreviewView> nativePreviewProvider,
         TsDiskDao tsDiskDao) {

      /* store object */
      this.loginService = loginService;
      this.hookHandler = hookHandler;
      this.executeModuleProvider = executeModuleProvider;
      this.viewportService = viewportService;
      this.reportExecutorDao = reportExecutorDao;
      this.variantStorerConfigProvider = variantStorerConfigProvider;

      this.imagePreviewProvider = imagePreviewProvider;
      this.jsPreviewProvider = jsPreviewProvider;
      this.nativePreviewProvider = nativePreviewProvider;
      this.tsDiskDao = tsDiskDao;

      waitOnEventService.callbackOnEvent(DispatcherService.REPORTSERVER_EVENT_USER_LOGGED_IN_APPLICATION_LOADED,
            new AsynchronousCallbackOnEventTrigger() {
               public void doExecute() {
                  reportExecutorDao.getPreviewMode(new AsyncCallback<String>() {
                     @Override
                     public void onSuccess(final String result) {
                        previewMode = result;
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                     }
                  });

                  reportExecutorDao.getDefaultColumnWidth(new AsyncCallback<Integer>() {

                     @Override
                     public void onSuccess(Integer result) {
                        defaultColumnWidth = result;
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                     }
                  });

                  reportExecutorDao.getMaxColumnWidth(new AsyncCallback<Integer>() {

                     @Override
                     public void onSuccess(Integer result) {
                        maxColumnWidth = result;
                     }

                     @Override
                     public void onFailure(Throwable caught) {
                     }
                  });

               }
            });
   }

   @Override
   public void executeReport(ReportDto report) {
      History.newItem(ReportExecutorUIModule.REPORT_EXECUTOR_HISTORY_TOKEN + "/uuid:" + report.getUuid(), false);
      executeReport(report, null, null);
   }

   @Override
   public void executeReport(final ReportDto report, final ExecutorEventHandler eventHandler,
         final ExecuteReportConfiguration config, final ReportViewConfiguration... viewConfigs) {
      String addParams = null != config ? config.getUrlParameters() : null;
      History.newItem(ReportExecutorUIModule.REPORT_EXECUTOR_HISTORY_TOKEN + "/uuid:" + report.getUuid()
            + (null != addParams ? "&" + addParams : ""), false);

      viewportService.setLoadingMask();

      reportExecutorDao.loadFullReportForExecution(report, true, new RsAsyncCallback<ReportDto>() {
         public void onSuccess(final ReportDto result) {
            executeReportDirectly(result, eventHandler, config, viewConfigs);
         }

         @Override
         public void onFailure(Throwable caught) {
            try {
               if (null == config || !config.handleError(caught))
                  new SimpleErrorDialog(BaseMessages.INSTANCE.error(), caught.getMessage()).show();
            } finally {
               viewportService.unmask();
            }

         }
      });

   }

   @Override
   public void executeReportDirectly(ReportDto report) {
      executeReportDirectly(report, null, null);
   }

   public ReportExecuteAreaModule getActiveReportExecuteAreaModule() {
      ReportExecuteAreaModule module = null;
      for (ClientTempModule m : viewportService.getTempModules()) {
         if (m instanceof ReportExecuteAreaModule) {
            module = (ReportExecuteAreaModule) m;
            return module;
         }
      }
      return null;
   }

   @Override
   public void executeReportDirectly(ReportDto report, final ExecutorEventHandler eventHandler,
         final ExecuteReportConfiguration config, final ReportViewConfiguration... viewConfigs) {
      viewportService.unmask();

      /* create or load module */
      ReportExecuteAreaModule module = getActiveReportExecuteAreaModule();
      if (null == module)
         module = executeModuleProvider.get();

      /* create config */
      final ReportExecuteAreaModule theModule = module;
      CompositeExecutorEventHandler theEventHandler = new CompositeExecutorEventHandler(new ExecutorEventHandler() {
         @Override
         public void handleEvent(ReportExecutorEvent event) {
            if (event instanceof VariantCreatedEvent)
               handleCreated((VariantCreatedEvent) event);
            else if (event instanceof VariantDeletedEvent)
               handleDeleted((VariantDeletedEvent) event);
            else if (event instanceof VariantChangedEvent)
               handleChanged((VariantChangedEvent) event);

         }

         private void handleChanged(VariantChangedEvent event) {
            theModule.forceCloseCurrent();
            executeReport(event.getVariant(), eventHandler, config, viewConfigs);
         }

         private void handleDeleted(VariantDeletedEvent event) {
            theModule.forceCloseCurrent();
         }

         private void handleCreated(VariantCreatedEvent event) {
            theModule.forceCloseCurrent();
            executeReport(event.getVariant(), eventHandler, config, viewConfigs);
         }
      });
      if (null != eventHandler)
         theEventHandler.addHandler(eventHandler);

      ExecuteReportConfiguration theConfig = config;
      if (null == theConfig) {
         theConfig = new ExecuteReportConfiguration() {
            @Override
            public VariantStorerConfig getVariantStorerConfig() {
               return variantStorerConfigProvider.get();
            }

            @Override
            public String getViewId() {
               return null;
            };

            @Override
            public boolean handleError(Throwable t) {
               return false;
            }

            @Override
            public boolean acceptView(String viewId) {
               return true;
            }

            @Override
            public String getUrlParameters() {
               return null;
            }
         };
      }

      /* get display component, add it to module and display module */
      final Component displayComponent = getExecuteReportComponent(report, theEventHandler, theConfig, viewConfigs);
      viewportService.addTempModule(module);

      theModule.addExecutionComponent(report, displayComponent, theConfig.getUrlParameters());
   }

   @Override
   public Component getExecuteReportComponent(ReportDto report) {
      return getExecuteReportComponent(report, false);
   }

   @Override
   public Component getExecuteReportComponent(ReportDto report, final boolean showVariantStorer) {
      ExecutorEventHandler eventHandler = new ExecutorEventHandler() {
         @Override
         public void handleEvent(ReportExecutorEvent event) {
         }
      };

      ExecuteReportConfiguration config = new ExecuteReportConfiguration() {
         @Override
         public VariantStorerConfig getVariantStorerConfig() {
            VariantStorerConfigImpl config = variantStorerConfigProvider.get();
            config.setDisplay(showVariantStorer);
            return config;
         }

         @Override
         public String getViewId() {
            return null;
         };

         @Override
         public boolean handleError(Throwable t) {
            return false;
         }

         @Override
         public boolean acceptView(String viewId) {
            return true;
         }

         @Override
         public String getUrlParameters() {
            return null;
         }
      };

      return getExecuteReportComponent(report, eventHandler, config);
   }

   @Override
   public Component getExecuteReportComponent(ReportDto report, ExecutorEventHandler eventHandler,
         ExecuteReportConfiguration config, ReportViewConfiguration... viewConfigs) {
      String token = createExecuteReportToken(report);
      return new ReportExecutorMainPanel(report, eventHandler, token, config, viewConfigs);
   }

   @Override
   public PreviewViewFactory getPreviewReportComponent(final ReportDto report) {
      return hookHandler.getHookers(ReportViewHook.class)
         .stream()
         .map(ReportViewHook::getViewFactory)
         .filter(factory -> factory instanceof PreviewViewFactory && factory.consumes(report))
         .map(factory -> (PreviewViewFactory) factory)
         .findAny()
         .orElse(null);
   }

   @Override
   public String createExecuteReportToken(ReportDto report) {
      // should come from the server
      Random r = new Random(new Date().getTime());
      String token = report.getId() + "-" + loginService.getCurrentUser().getId() + "-" + new Date().getTime() + "-"
            + r.nextInt();
      return token;
   }

   @Override
   public AbstractReportPreviewView getPdfPreviewView() {
      AbstractReportPreviewView view;

      if ("image".equals(previewMode)) {
         view = imagePreviewProvider.get();

      } else if ("native".equals(previewMode)) {
         view = nativePreviewProvider.get();

      } else if ("jsviewer".equals(previewMode)) {
         view = jsPreviewProvider.get();

      } else {
         view = jsPreviewProvider.get();
      }

      return view;
   }

   @Override
   public void createNewVariant(ReportDto report, TeamSpaceDto teamSpace, TsDiskFolderDto folder, String executeToken,
         String name, String description, final AsyncCallback<ReportDto> callback) {
      if (null != teamSpace)
         tsDiskDao.createAndImportVariant(teamSpace, folder, report, executeToken, name, description,
               new RsAsyncCallback<TsDiskReportReferenceDto>() {
                  @Override
                  public void onSuccess(TsDiskReportReferenceDto result) {
                     callback.onSuccess(result.getReport());
                  }

                  @Override
                  public void onFailure(Throwable caught) {
                     new DetailErrorDialog(caught).show();
                  }
               });
      else
         reportExecutorDao.createNewVariant(report, executeToken, name, description, callback);
   }

   @Override
   public Integer getDefaultColumnWidth() {
      return defaultColumnWidth;
   }

   @Override
   public Integer getMaxColumnWidth() {
      return maxColumnWidth;
   }

   @Override
   public boolean getEffectiveReportPropertyAsBoolean(ReportDto report, String property) {
      Optional<ReportPropertyDto> prop = getProperty(report, property);

      if (prop.isPresent()) 
         return Boolean.parseBoolean(((ReportStringPropertyDto) prop.get()).getStrValue());
         
      if (report instanceof ReportVariantDto) {
         // try with parent
         ReportDto parent = ((ReportVariantDto) report).getBaseReport();
         prop = getProperty(parent, property);
         
         if (prop.isPresent()) 
            return Boolean.parseBoolean(((ReportStringPropertyDto) prop.get()).getStrValue());
      }
      
      return false;
   }
   
   private Optional<ReportPropertyDto> getProperty(ReportDto report, String property) {
      return report.getReportProperties().stream()
         .filter((p -> p instanceof ReportStringPropertyDto
               && p.getName().equals(property)))
         .findAny();
   }

}
