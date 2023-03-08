package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.inject.Provider;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledCSVTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECCsv;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportserver.ReportServerService;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
public class CSVOutputGenerator extends TableOutputGeneratorImpl {

   private String delimiter; // $NON-NLS-1$
   private String quotationMark; // $NON-NLS-1$
   private String newline; // $NON-NLS-1$
   protected boolean haveSeenFieldInRow = false;
   protected StringBuilder builder;

   protected PrintWriter writer;
   
   int cell = 0;
   
   private String[] nullReplacements;
   private Boolean[] exportNullAsString;

   public static final String CONFIG_FILE = "exportfilemd/csvexport.cf";
   public static final String CSV_LINE_SEPARATOR_PROPERTY = "csv.lineSeparator";
   public static final String CSV_SEPARATOR_PROPERTY = "csv.separator";
   public static final String CSV_QUOTATION_PROPERTY = "csv.quote";

   private final ConfigService configService;
   private final ReportServerService reportServerService;
   
   private final Provider<ExporterHelper> exporterHelperProvider;

   @Inject
   public CSVOutputGenerator(
         ConfigService configService, 
         ReportServerService reportServerService,
         Provider<ExporterHelper> exporterHelperProvider
         ) {
      super();
      this.reportServerService = reportServerService;
      this.configService = configService;
      this.exporterHelperProvider = exporterHelperProvider;
      
      this.newline = getLineSeparator();
      this.delimiter = getDelimiter();
      this.quotationMark = getQuotationMark();
      this.charset = getCharset();
   }

   @Override
   public void addField(Object field, CellFormatter formatter) throws IOException {
      if (haveSeenFieldInRow)
         builder.append(delimiter);
      builder.append(quotationMark);
      String val = null;
      if (field == null) {
         if (!exportNullAsString[cell])
            val = "";
         else {
            val = nullReplacements[cell];
         }
      } else {
         val = formatter.format(getValueOf(field));
      }
      if (!"".equals(quotationMark))
         builder.append(
               String.valueOf(val).replaceAll(quotationMark, "\\\\" + quotationMark));
      else
         builder.append(String.valueOf(val));
      builder.append(quotationMark);

      if (null != writer) {
         writer.write(builder.toString());
         builder.delete(0, builder.length());
      }
      haveSeenFieldInRow = true;
      
      cell++;
   }

   @Override
   public void close() throws IOException {
      builder.append(newline);
      if (null != writer) {
         writer.write(builder.toString());
         builder.delete(0, builder.length());

         writer.close();
      }
   }

   private String getDelimiter() {
      return configService.getConfigFailsafe(CONFIG_FILE).getString(CSV_SEPARATOR_PROPERTY, ";");
   }

   private String getQuotationMark() {
      return configService.getConfigFailsafe(CONFIG_FILE).getString(CSV_QUOTATION_PROPERTY, "\"");
   }

   private String getLineSeparator() {
      return configService.getConfigFailsafe(CONFIG_FILE).getString(CSV_LINE_SEPARATOR_PROPERTY, "\r\n")
            .replace("\\n", "\n").replace("\\r", "\r");
   }

   private String getCharset() {
      return reportServerService.getCharset();
   }

   @Override
   public String[] getFormats() {
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_CSV };
   }

   @Override
   public CompiledReport getTableObject() {
      return new CompiledCSVTableReport(builder.toString());
   }

   @Override
   public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report,
         TableReport orgReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user,
         ReportExecutionConfig... configs) throws IOException {
      super.initialize(os, td, withSubtotals, report, orgReport, cellFormatters, parameters, user, configs);

      final ImmutablePair<String[], Boolean[]> nullFormats = exporterHelperProvider.get().getNullFormats(report, td,
            cellFormatters);
      nullReplacements = nullFormats.getLeft();
      exportNullAsString = nullFormats.getRight();
      
      RECCsv config = getConfig(RECCsv.class);
      if (null != config) {
         delimiter = null == config.getSeparator() ? "" : config.getSeparator();
         quotationMark = null == config.getQuote() ? "" : config.getQuote();
         newline = null == config.getLineSeparator() ? "" : config.getLineSeparator();
         newline = newline.replace("\\r", "\r").replace("\\n", "\n");
         charset = null == config.getCharset() ? charset : config.getCharset();
      }

      /* initialize buffer */
      builder = new StringBuilder();
      if (null != os)
         writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, charset)));

      /* add header */
      if (null == config || config.isPrintHeader()) {
         boolean first = true;
         for (String name : td.getColumnNames()) {
            if (first)
               first = false;
            else
               builder.append(delimiter);

            builder.append(quotationMark);
            builder.append(name);
            builder.append(quotationMark);
         }
         builder.append(newline);
      }

      if (null != writer) {
         writer.write(builder.toString());
         builder.delete(0, builder.length());
      }
   }

   @Override
   public void nextRow() throws IOException {
      builder.append(newline);

      if (null != writer) {
         writer.write(builder.toString());
         builder.delete(0, builder.length());
      }

      haveSeenFieldInRow = false;
      
      cell = 0;
   }

   @Override
   public CompiledReport getFormatInfo() {
      return new CompiledCSVTableReport(null);
   }

}
