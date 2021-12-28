package net.datenwerke.rs.saiku.client.saiku.hookers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import com.google.gwt.http.client.URL;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.ShowHideFieldAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.SimpleFormCondition;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCFancyStaticList;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.ReportDadgetProcessor;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.hookers.ReportDadgetDefaultExportHooker;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetPanel;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.RECSaikuChartDtoDec;
import net.datenwerke.rs.saiku.client.saiku.locale.SaikuMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;

public class ReportDadgetSaikuExportHooker extends ReportDadgetDefaultExportHooker {

   private static final String PROPERTY_NAME = "saikuProperty";
   protected static final String BAR = "bar";
   protected static final String STACKED_BAR = "stackedBar";
   protected static final String STACKED_BAR_100 = "stackedBar100";
   protected static final String MULTIPLE_BAR = "multiplebar";
   protected static final String LINE = "line";
   protected static final String AREA = "area";
   protected static final String HEAT_GRID = "heatgrid";
   protected static final String DOT = "dot";
   protected static final String WATERFALL = "waterfall";
   protected static final String PIE = "pie";

   @Inject
   public ReportDadgetSaikuExportHooker(ReportExecutorUIService reportExecutorService,
         ReportExporterUIService reportExportService, ReportExecutorDao reportExecutorDao,
         ReportExporterDao reportExporterDao) {
      super(reportExecutorService, reportExportService, reportExecutorDao, reportExporterDao);
   }

   @Override
   public boolean consumes(ReportDadgetDto dadget) {
      ReportDto report = getReportOrReference(dadget);
      return null == report || (report instanceof SaikuReportDto
            || (report instanceof TableReportDto && ((TableReportDto) report).isCubeFlag()));
   }

   @Override
   public void configureDisplayConfigDialog(ReportDadgetDto dadget, SimpleForm form) {
      form.addField(List.class, PROPERTY_NAME, DashboardMessages.INSTANCE.reportDadgetFormat(),
            new SFFCFancyStaticList<String>() {

               @Override
               public Map<String, String> getValues() {
                  Map<String, String> map = new LinkedHashMap<String, String>();

                  map.put(DashboardMessages.INSTANCE.reportDadgetFormatPreview(), PREVIEW);
                  map.put(DashboardMessages.INSTANCE.reportDadgetFormatHtml(), HTML);
                  map.put(DashboardMessages.INSTANCE.reportDadgetFormatFull(), FULL);

                  map.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Bar"), BAR);
                  map.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Stacked Bar"), STACKED_BAR);
                  map.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Stacked Bar 100%"), STACKED_BAR_100);
                  map.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Multiple Bar"), MULTIPLE_BAR);
                  map.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Line"), LINE);
                  map.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Area"), AREA);
                  map.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Heat Grid"), HEAT_GRID);
                  map.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Dot"), DOT);
                  map.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Waterfall"), WATERFALL);
                  map.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Pie"), PIE);

                  return map;
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
                  LinkedHashMap<String, ImageResource> icons = new LinkedHashMap<String, ImageResource>();

                  icons.put(DashboardMessages.INSTANCE.reportDadgetFormatFull(), BaseIcon.NO_ICON.toImageResource(1));
                  icons.put(DashboardMessages.INSTANCE.reportDadgetFormatHtml(), BaseIcon.NO_ICON.toImageResource(1));
                  icons.put(DashboardMessages.INSTANCE.reportDadgetFormatPreview(),
                        BaseIcon.NO_ICON.toImageResource(1));

                  icons.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Bar"),
                        BaseResources.INSTANCE.saikuChartBar());
                  icons.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Stacked Bar"),
                        BaseResources.INSTANCE.saikuChartStackedbar());
                  icons.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Stacked Bar 100%"),
                        BaseResources.INSTANCE.saikuChart100bar());
                  icons.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Multiple Bar"),
                        BaseResources.INSTANCE.saikuChartMultiple());
                  icons.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Line"),
                        BaseResources.INSTANCE.saikuChartLine());
                  icons.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Area"),
                        BaseResources.INSTANCE.saikuChartArea());
                  icons.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Heat Grid"),
                        BaseResources.INSTANCE.saikuChartHeat());
                  icons.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Dot"),
                        BaseResources.INSTANCE.saikuChartDot());
                  icons.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Waterfall"),
                        BaseResources.INSTANCE.saikuChartWaterfall());
                  icons.put(SaikuMessages.INSTANCE.reportDadgetFormatChart("Pie"),
                        BaseResources.INSTANCE.saikuChartPie());

                  return icons;
               }

               @Override
               public Map<String, String> getDescriptionMap() {
                  LinkedHashMap<String, String> descriptions = new LinkedHashMap<String, String>();
                  return descriptions;
               }
            });
      if (consumes(dadget))
         form.setValue(PROPERTY_NAME, dadget.getConfig());

      form.addCondition(ReportDadgetProcessor.REPORT_PROPERTY_KEY, new SimpleFormCondition() {
         @Override
         public boolean isMet(Widget formField, FormFieldProviderHook responsibleHook, SimpleForm form) {
            Object repOrRef = form.getValue(ReportDadgetProcessor.REPORT_PROPERTY_KEY);
            if (null == repOrRef)
               return false;

            ReportDto report = repOrRef instanceof ReportDto ? (ReportDto) repOrRef
                  : ((TsDiskReportReferenceDto) repOrRef).getReport();
            return null != report && isSupportedReport(report);
         }
      }, new ShowHideFieldAction(PROPERTY_NAME));
   }

   @Override
   public void storeConfig(ReportDadgetDto dadget, SimpleForm form) {
      ((ReportDadgetDto) dadget).setConfig((String) form.getValue(PROPERTY_NAME));
   }

   @Override
   public void displayReport(final ReportDadgetDto rDadget, ReportDto report, final DadgetPanel panel,
         final Set<ParameterInstanceDto> parameterInstances) {
      final String config = rDadget.getConfig();
      if (null == config)
         return;

      if (FULL.equals(config) || PREVIEW.equals(config) || HTML.equals(config)) {
         super.displayReport(rDadget, report, panel, parameterInstances);
         return;
      } else {
         reportExecutorDao.loadFullReportForExecution(report, new RsAsyncCallback<ReportDto>() {
            @Override
            public void onSuccess(ReportDto result) {
               panel.unmask();

               /* generate or get export token */
               final String exportToken = generateOrObtainExportToken(result);

               /* use dadget parameter instances */
               if (null != parameterInstances && !parameterInstances.isEmpty())
                  result.setParameterInstances(parameterInstances);

               String type = URL.encode(config);

               RECSaikuChartDtoDec typeDef = new RECSaikuChartDtoDec();
               typeDef.setType(type);

               reportExporterDao.storeInSessionForExport(result, exportToken, "SAIKU_CHART_HTML", typeDef,
                     new RsAsyncCallback<Void>() {
                        @Override
                        public void onSuccess(Void result) {
                           panel.setUrl(reportExportService.getExportServletPath() + "&tid=" + exportToken
                                 + "&download=false");
                        }
                     });
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
               panel.unmask();
            }
         });
      }
   }

   @Override
   public String getPropertyName() {
      return PROPERTY_NAME;
   }

   @Override
   protected boolean isSupportedReport(ReportDto report) {
      return null != report && (report instanceof SaikuReportDto
            || (report instanceof TableReportDto && ((TableReportDto) report).isCubeFlag()));
   }

}
