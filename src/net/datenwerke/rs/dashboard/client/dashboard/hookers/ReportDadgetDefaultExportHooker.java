package net.datenwerke.rs.dashboard.client.dashboard.hookers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.ContentPanel;

import net.datenwerke.gxtdto.client.dtomanager.ClientDtoManagerService;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.ShowHideFieldAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.SimpleFormCondition;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCFancyStaticList;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.MainPanelAwareView;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.SelectionAwareView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.ReportDadgetProcessor;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.ReportDadgetExportHook;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;

public abstract class ReportDadgetDefaultExportHooker implements ReportDadgetExportHook {

   protected static final String FULL = "full";
   protected static final String HTML = "html";
   protected static final String PREVIEW = "preview";

   protected final ReportExecutorUIService reportExecutorService;
   protected final ReportExporterUIService reportExportService;
   protected final ReportExecutorDao reportExecutorDao;
   protected final ReportExporterDao reportExporterDao;

   @Inject
   protected ClientDtoManagerService dtoManager;

   public ReportDadgetDefaultExportHooker(ReportExecutorUIService reportExecutorService,
         ReportExporterUIService reportExportService, ReportExecutorDao reportExecutorDao,
         ReportExporterDao reportExporterDao) {
      super();
      this.reportExecutorService = reportExecutorService;
      this.reportExportService = reportExportService;
      this.reportExecutorDao = reportExecutorDao;
      this.reportExporterDao = reportExporterDao;
   }

   @Override
   public boolean consumes(ReportDadgetDto dadget) {
      ReportDto report = getReportOrReference(dadget);
      return null == report || isSupportedReport(report);
   }

   protected ReportDto getReportOrReference(ReportDadgetDto dadget) {
      ReportDto report = dadget.getReport();
      if (null == report && null != dadget.getReportReference())
         return dadget.getReportReference().getReport();
      return report;
   }

   @Override
   public void configureDisplayConfigDialog(ReportDadgetDto dadget, SimpleForm form) {
      form.addField(List.class, getPropertyName(), DashboardMessages.INSTANCE.reportDadgetFormat(),
            new SFFCFancyStaticList<String>() {

               @Override
               public Map<String, String> getValues() {
                  return getValueMap();
               }

               @Override
               public int getHeight() {
                  return 36;
               }

               @Override
               public int getWidth() {
                  return 300;
               }

               @Override
               public Map<String, ImageResource> getIconMap() {
                  return ReportDadgetDefaultExportHooker.this.getIconMap();
               }

               @Override
               public Map<String, String> getDescriptionMap() {
                  LinkedHashMap<String, String> descriptions = new LinkedHashMap<String, String>();
                  return descriptions;
               }
            });
      if (consumes(dadget))
         form.setValue(getPropertyName(), dadget.getConfig());

      form.addCondition(ReportDadgetProcessor.REPORT_PROPERTY_KEY, new SimpleFormCondition() {
         @Override
         public boolean isMet(Widget formField, FormFieldProviderHook responsibleHook, SimpleForm form) {
            Object obj = form.getValue(ReportDadgetProcessor.REPORT_PROPERTY_KEY);
            ReportDto report = null;
            if (obj instanceof ReportDto)
               report = (ReportDto) obj;
            else if (obj instanceof TsDiskReportReferenceDto) {
               TsDiskReportReferenceDto ref = (TsDiskReportReferenceDto) obj;
               report = ref.getReport();
            }
            return null != report && isSupportedReport(report);
         }
      }, new ShowHideFieldAction(getPropertyName()));

   }

   protected Map<String, ImageResource> getIconMap() {
      LinkedHashMap<String, ImageResource> icons = new LinkedHashMap<String, ImageResource>();

      icons.put(DashboardMessages.INSTANCE.reportDadgetFormatFull(), BaseIcon.NO_ICON.toImageResource(1));
      icons.put(DashboardMessages.INSTANCE.reportDadgetFormatHtml(), BaseIcon.NO_ICON.toImageResource(1));
      icons.put(DashboardMessages.INSTANCE.reportDadgetFormatPreview(), BaseIcon.NO_ICON.toImageResource(1));

      return icons;
   }

   protected Map<String, String> getValueMap() {
      Map<String, String> map = new LinkedHashMap<String, String>();

      map.put(DashboardMessages.INSTANCE.reportDadgetFormatPreview(), PREVIEW);
      map.put(DashboardMessages.INSTANCE.reportDadgetFormatHtml(), HTML);
      map.put(DashboardMessages.INSTANCE.reportDadgetFormatFull(), FULL);

      return map;
   }

   @Override
   public abstract String getPropertyName();

   abstract protected boolean isSupportedReport(ReportDto report);

   @Override
   public void storeConfig(ReportDadgetDto dadget, SimpleForm form) {
      if (form.isField(getPropertyName()))
         ((ReportDadgetDto) dadget).setConfig((String) form.getValue(getPropertyName()));
   }

   @Override
   public void displayReport(final ReportDadgetDto rDadget, ReportDto report, final DadgetPanel panel,
         final Set<ParameterInstanceDto> parameterInstancesProxied) {
      final String config = rDadget.getConfig();
      if (null == config)
         return;

      panel.mask(BaseMessages.INSTANCE.loadingMsg());
      final Set<ParameterInstanceDto> parameterInstances = (Set<ParameterInstanceDto>) dtoManager
            .unproxy(parameterInstancesProxied);
      reportExecutorDao.loadFullReportForExecution(report, new RsAsyncCallback<ReportDto>() {
         @Override
         public void onSuccess(ReportDto result) {
            panel.unmask();

            /* use dadget parameter instances */
            if (null != parameterInstances && !parameterInstances.isEmpty())
               result.setParameterInstances(parameterInstances);

            if (FULL.equals(config)) {
               Component executeReportComponent = reportExecutorService.getExecuteReportComponent(result);
               if (executeReportComponent instanceof ContentPanel)
                  ((ContentPanel) executeReportComponent).setHeaderVisible(false);
               executeReportComponent.setBorders(false);

               panel.add(executeReportComponent);
            } else if (PREVIEW.equals(config)) {
               PreviewViewFactory factory = reportExecutorService.getPreviewReportComponent(result);
               ReportExecutorMainPanelView view = factory.newInstance(result, Collections.EMPTY_SET);

               if (view instanceof AbstractReportPreviewView)
                  ((AbstractReportPreviewView) view).setDelayModalWindowOnExecution(10000);

               Widget component = view.getViewComponent();

               view.setExecuteReportToken(reportExecutorService.createExecuteReportToken(result));
               if (view instanceof MainPanelAwareView)
                  ((MainPanelAwareView) view).makeAwareOfMainPanel(panel);
               if (view instanceof SelectionAwareView)
                  ((SelectionAwareView) view).makeAwareOfSelection();

               panel.add(component);
            } else {
               /* generate or get export token */
               final String exportToken = generateOrObtainExportToken(result);

               /* use dadget parameter instances */
               if (null != parameterInstances && !parameterInstances.isEmpty())
                  result.setParameterInstances(parameterInstances);

               reportExporterDao.storeInSessionForExport(result, exportToken,
                     null != config ? config.toUpperCase() : config, new RsAsyncCallback<Void>() {
                        @Override
                        public void onSuccess(Void result) {
                           panel.setUrl(reportExportService.getExportServletPath() + "&tid=" + exportToken
                                 + "&download=false");
                        }
                     });
            }
            panel.forceLayout();
         }

         @Override
         public void onFailure(Throwable caught) {
            super.onFailure(caught);
            panel.unmask();
         }
      });

   }

   protected String generateOrObtainExportToken(ReportDto report) {
      return reportExecutorService.createExecuteReportToken(report);
   }

}
