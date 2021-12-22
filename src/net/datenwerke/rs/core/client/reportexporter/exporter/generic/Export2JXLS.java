package net.datenwerke.rs.core.client.reportexporter.exporter.generic;

import static java.util.stream.Collectors.toList;
import static net.datenwerke.rs.utils.stream.shared.StreamUtil.streamOfNullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.MarginData;

import net.datenwerke.gf.client.config.ClientConfigXmlService;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow.OnButtonClickHandler;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticRadioList;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.dto.RECJxlsDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECJxlsDtoDec;
import net.datenwerke.rs.core.client.reportexporter.dto.pa.RECJxlsDtoPA;
import net.datenwerke.rs.core.client.reportexporter.exporter.ReportExporterImpl;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.locale.JxlsReportMessages;
import net.datenwerke.rs.license.client.locale.LicenseMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.utils.stream.shared.StreamUtil;

public abstract class Export2JXLS extends ReportExporterImpl {

   protected RECJxlsDto config;
   private final ReportExporterDao exporterDao;
   private final ClientConfigXmlService jsonService;
   private static final String CONFIGURATION_FILE = "jxlsexport.cf";

   @Inject
   public Export2JXLS(
         ReportExporterDao exporterDao, 
         ClientConfigXmlService jsonService
         ) {
      this.exporterDao = exporterDao;
      this.jsonService = jsonService;
   }

   @Override
   public boolean consumesConfiguration(ReportDto report) {
      return true;
   }

   @Override
   public void configureFrom(List<ReportExecutionConfigDto> exportConfiguration) {
      this.config = streamOfNullable(exportConfiguration)
         .filter(config -> !exportConfiguration.isEmpty())
         .filter(config -> config instanceof RECJxlsDtoDec)
         .map(config -> (RECJxlsDto) config)
         .findAny()
         .orElse(null);
   }

   @Override
   public void displayConfiguration(final ReportDto report, final String executorToken, boolean allowAutomaticConfig,
         final ConfigurationFinishedCallback callback) {
      final DwWindow window = new DwWindow();
      window.setHeading(getExportTitle());
      window.getHeader().setIcon(getIcon());
      window.setSize(400, 466);
      window.setModal(true);

      final SimpleForm form = SimpleForm.getInlineInstance();
      window.add(form, new MarginData(10));

      form.addField(List.class, RECJxlsDtoPA.INSTANCE.jxls1(), LicenseMessages.INSTANCE.versionLabel(),
            new RECJxlsDtoDec(), new SFFCStaticRadioList<Boolean>() {
               @Override
               public Map<String, Boolean> getValues() {
                  Map<String, Boolean> map = new HashMap<String, Boolean>();
                  map.put("JXLS", false);
                  map.put(JxlsReportMessages.INSTANCE.useLegacyJxls(), true);
                  return map;
               }
            });

      form.addField(List.class, RECJxlsDtoPA.INSTANCE.jxlsReport(), ReportExporterMessages.INSTANCE.exportTypeLabel(),
            new RECJxlsDtoDec(), new SFFCStaticRadioList<Boolean>() {
               @Override
               public Map<String, Boolean> getValues() {
                  Map<String, Boolean> map = new HashMap<String, Boolean>();
                  map.put(ReportexecutorMessages.INSTANCE.jxlsVariantTemplate(), false);
                  map.put(ReportexecutorMessages.INSTANCE.jxlsReportTemplate(), true);
                  return map;
               }
            });

      form.addField(Integer.class, RECJxlsDtoPA.INSTANCE.numberColumnWidth(),
            ReportexecutorMessages.INSTANCE.jxlsNumberColumnWidth());
      form.addField(Integer.class, RECJxlsDtoPA.INSTANCE.textColumnWidth(),
            ReportexecutorMessages.INSTANCE.jxlsTextColumnWidth());
      form.addField(Integer.class, RECJxlsDtoPA.INSTANCE.dateColumnWidth(),
            ReportexecutorMessages.INSTANCE.jxlsDateColumnWidth());
      form.addField(Integer.class, RECJxlsDtoPA.INSTANCE.currencyColumnWidth(),
            ReportexecutorMessages.INSTANCE.jxlsCurrencyColumnWidth());

      if (null == config) {
         config = new RECJxlsDtoDec();
         exporterDao.getExportDefaultSettingsAsXml(CONFIGURATION_FILE, new RsAsyncCallback<String>() {
            @Override
            public void onSuccess(String result) {
               if (!"".equals(result.trim())) {
                  jsonService.setXmlConfig(result);
                  int numberColumnWidth = Integer.parseInt(jsonService.getString("jxls.numberColumnWidth", "8"));
                  int textColumnWidth = Integer.parseInt(jsonService.getString("jxls.textColumnWidth", "8"));
                  int dateColumnWidth = Integer.parseInt(jsonService.getString("jxls.dateColumnWidth", "8"));
                  int currencyColumnWidth = Integer.parseInt(jsonService.getString("jxls.currencyColumnWidth", "8"));
                  config.setNumberColumnWidth(numberColumnWidth);
                  config.setTextColumnWidth(textColumnWidth);
                  config.setDateColumnWidth(dateColumnWidth);
                  config.setCurrencyColumnWidth(currencyColumnWidth);
               }
            }
         });
      }
      form.bind(config);

      window.addCancelButton();

      DwTextButton submitBtn = window.addSubmitButton(() -> callback.success());
      submitBtn.setText(ReportExporterMessages.INSTANCE.exportReport());

      window.show();
   }

   @Override
   public boolean isConfigured() {
      return null != config;
   }

   @Override
   public List<ReportExecutionConfigDto> getConfiguration() {
      if (null == config)
         return new ArrayList<>();
      
      return Stream.of(config)
            .collect(toList());
   }

   @Override
   public String getExportDescription() {
      return "JXLS " + JxlsReportMessages.INSTANCE.templateUpload();
   }

   @Override
   public String getExportTitle() {
      return "JXLS " + JxlsReportMessages.INSTANCE.templateUpload(); //$NON-NLS-1$
   }

   @Override
   public String getOutputFormat() {
      return "JXLS_TEMPLATE"; //$NON-NLS-1$
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.fromFileExtension("xls").toImageResource();
   }

   @Override
   public boolean hasConfiguration() {
      return true;
   }

}