package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.configuration2.BaseConfiguration;
import org.apache.commons.configuration2.Configuration;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.theme.ThemeService;
import net.datenwerke.rs.base.client.AvailableReportProperties;
import net.datenwerke.rs.base.service.parameterreplacements.provider.ReportForJuel;
import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.base.service.reportengines.table.columnfilter.FilterService;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.ColumnFormatCellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BlockType;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatNumber;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledHTMLTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.client.helper.ObjectHolder;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportStringProperty;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import net.datenwerke.security.service.usermanager.entities.User;

public class HTMLOutputGenerator extends TableOutputGeneratorImpl {

   protected static final String CONFIG_FILE = "dynamiclists/htmlexport.cf";

   private final ReportEnginesMessages messages = LocalizationServiceImpl.getMessages(ReportEnginesMessages.class);

   @Inject
   protected Provider<ThemeService> themeServiceProvider;

   @Inject
   protected Provider<ConfigService> configServiceProvider;

   @Inject
   protected Provider<LocalizationServiceImpl> localizationServiceProvider;
   
   @Inject
   protected Provider<FilterService> filterServiceProvider;

   @Inject
   protected Provider<LicenseService> licenseServiceProvider;

   @Inject
   protected Provider<SimpleJuel> juelProvider;

   protected final static String DATA_SUBTOTAL_ROW_TAG_OPEN = "<tr class=\"subtotal-row\">"; //$NON-NLS-1$
   protected final static String DATA_ROW_TAG_OPEN_ODD = "<tr class='odd'>"; //$NON-NLS-1$
   protected final static String DATA_ROW_TAG_OPEN_EVEN = "<tr class='even'>"; //$NON-NLS-1$
   protected final static String DATA_ROW_TAG_CLOSE = "</tr>"; //$NON-NLS-1$

   protected final static String DATA_FIELD_OPEN = "<td>"; //$NON-NLS-1$
   protected final static String DATA_FIELD_CLOSE = "</td>"; //$NON-NLS-1$

   protected final static String TABLE_OPEN = "<table class=\"report\">"; //$NON-NLS-1$
   protected final static String TABLE_CLOSE = "</table>"; //$NON-NLS-1$

   protected final static String PARAMETERS_TABLE_OPEN = "<table class=\"parameters\">"; //$NON-NLS-1$
   protected final static String PARAMETERS_TABLE_CLOSE = "</table>"; //$NON-NLS-1$

   protected final static String PARAMETERS_ROW_TAG_OPEN_ODD = "<tr class='parameters-odd'>"; //$NON-NLS-1$
   protected final static String PARAMETERS_ROW_TAG_OPEN_EVEN = "<tr class='parameters-even'>"; //$NON-NLS-1$
   protected final static String PARAMETERS_ROW_TAG_CLOSE = "</tr>"; //$NON-NLS-1$

   protected final static String PARAMETERS_FIELD_OPEN = "<td>"; //$NON-NLS-1$
   protected final static String PARAMETERS_FIELD_CLOSE = "</td>"; //$NON-NLS-1$

   protected static final String STYLE_PROPERTY = "htmlexport.style";
   protected static final String PRE_PROPERTY = "htmlexport.pre";
   protected static final String POST_PROPERTY = "htmlexport.post";
   protected static final String SCRIPT_PROPERTY = "htmlexport.script";
   protected static final String HEAD_PROPERTY = "htmlexport.head";
   protected static final String TITLE_PROPERTY = "htmlexport.title";

   protected final static String FILTERS_TABLE_OPEN = "<table class=\"filters\">"; //$NON-NLS-1$
   protected final static String FILTERS_TABLE_CLOSE = "</table>"; //$NON-NLS-1$

   protected final static String FILTERS_ROW_TAG_OPEN_ODD = "<tr class='filters-odd'>"; //$NON-NLS-1$
   protected final static String FILTERS_ROW_TAG_OPEN_EVEN = "<tr class='filters-even'>"; //$NON-NLS-1$
   protected final static String FILTERS_ROW_TAG_CLOSE = "</tr>";

   protected final static String FILTERS_FIELD_OPEN = "<td>";
   protected final static String FILTERS_FIELD_CLOSE = "</td>";

   protected Appendable writer;

   protected boolean odd = false;

   protected Configuration configFile;

   @Override
   public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report,
         TableReport orgReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user,
         ReportExecutionConfig... configs) throws IOException {
      super.initialize(os, td, withSubtotals, report, orgReport, cellFormatters, parameters, user, configs);

      initConfigFile();
      initWriter(os);

      initDocument();
   }

   protected void initWriter(OutputStream os) throws UnsupportedEncodingException, IOException {
      if (null == os)
         writer = new StringBuilder();
      else
         writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(os, charset)));
   }

   protected void initConfigFile() {
      if (licenseServiceProvider.get().isEnterprise())
         configFile = configServiceProvider.get().getConfigFailsafe(getConfigFileLocation());
      else
         configFile = new BaseConfiguration();
   }

   protected String getConfigFileLocation() {
      return CONFIG_FILE;
   }

   protected void initDocument() throws IOException {

      /* only avoid caption if only data is being exported. */
      ReportProperty outputParamsProperty = report
            .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_PARAMETERS.getValue());
      boolean outputParameters = false;
      if (outputParamsProperty instanceof ReportStringProperty)
         outputParameters = ((ReportStringProperty) outputParamsProperty).toBoolean();

      ReportProperty outputFiltersProperty = report
            .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_FILTERS.getValue());
      boolean outputFilters = false;
      if (outputFiltersProperty instanceof ReportStringProperty)
         outputFilters = ((ReportStringProperty) outputFiltersProperty).toBoolean();

      ReportProperty outputCompleteConfigurationProperty = report
            .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_COMPLETE_CONFIGURATION.getValue());

      boolean outputCompleteConfiguration = false;
      if (outputCompleteConfigurationProperty instanceof ReportStringProperty)
         outputCompleteConfiguration = ((ReportStringProperty) outputCompleteConfigurationProperty).toBoolean();

      writer.append("<?xml version=\"1.0\" encoding=\"").append(charset).append("\" ?>\n");
      writer.append("<html xmlns=\"http://www.w3.org/1999/xhtml\"><head>");
      doAddHtmlHeader();
      writer.append("</head><body class=\"" + getBodyClass() + "\">\n");
      doAddPreTable();
      writer.append(TABLE_OPEN);
      if (licenseServiceProvider.get().isEnterprise()
            && (outputParameters || outputCompleteConfiguration || outputFilters)) {
         writer.append("<caption>");
         writer.append(messages.outputNameDynamicList());
         writer.append("</caption>");
      }

      writer.append("<thead>");
      writer.append("<tr>"); //$NON-NLS-1$
      for (String colName : td.getColumnNames()) {
         writer.append("<th>"); //$NON-NLS-1$
         writer.append(StringEscapeUtils.escapeXml(colName));
         writer.append("</th>"); //$NON-NLS-1$
      }
      writer.append("</tr>"); //$NON-NLS-1$
      writer.append("</thead>");
      writer.append("<tbody>");
      writer.append(DATA_ROW_TAG_OPEN_ODD);
   }

   protected String getBodyClass() {
      return "rs-reportexport-dl-html";
   }

   protected void doAddPreTable() throws IOException {
      String pre = configFile.getString(PRE_PROPERTY, "");
      if (null != pre && !"".equals(pre.trim())) {
         String html = parseTemplate(pre);
         writer.append(html);
      } else {
         writer.append("<div class='wrap'>");
         writer.append("<div class='header'>");
         writer.append(
               "<span class='logo'><img src=\"data:image/png;base64,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\" alt=\"logo\"/></span>");
         writer.append("<div class='reportdata'>");
         writer.append("<span class='name'>" + org.apache.commons.text.StringEscapeUtils.escapeHtml4(report.getName())
               + "</span>");
         writer.append("<span class='date'>" + getNowString() + "</span>");
         writer.append("</div>");
         writer.append("<div class='clear'></div>");
         writer.append("</div>");
      }
   }

   protected String parseTemplate(String template) {
      SimpleJuel juel = juelProvider.get();

      juel.addReplacement("columncount", td.getColumnCount(), Integer.class);
      juel.addReplacement("report", new ReportForJuel(report));
      juel.addReplacement("now", getNowString());

      juel.addReplacement("page", ReportEnginesMessages.INSTANCE.page());
      juel.addReplacement("of", ReportEnginesMessages.INSTANCE.of());

      juel.addReplacement("user", user);

      juel.addReplacement("parameterSet", parameters);

      ReportProperty outputParamsProperty = report
            .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_PARAMETERS.getValue());

      boolean outputParameters = false;
      if (outputParamsProperty instanceof ReportStringProperty)
         outputParameters = ((ReportStringProperty) outputParamsProperty).toBoolean();

      ReportProperty outputFiltersProperty = report
            .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_FILTERS.getValue());

      boolean outputFilters = false;
      if (outputFiltersProperty instanceof ReportStringProperty)
         outputFilters = ((ReportStringProperty) outputFiltersProperty).toBoolean();

      ReportProperty outputCompleteConfigurationProperty = report
            .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_COMPLETE_CONFIGURATION.getValue());

      boolean outputCompleteConfiguration = false;
      if (outputCompleteConfigurationProperty instanceof ReportStringProperty)
         outputCompleteConfiguration = ((ReportStringProperty) outputCompleteConfigurationProperty).toBoolean();

      if (outputParameters || outputCompleteConfiguration) {
         ReportProperty includeHiddenProperty = report
               .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_INCLUDE_PARAMETERS.getValue());
         boolean includeHidden = false;
         if (includeHiddenProperty instanceof ReportStringProperty)
            includeHidden = ((ReportStringProperty) includeHiddenProperty).toBoolean();

         Map<String, Object> outputConfiguration = parameters.getParameterMapSimple();
         if (outputCompleteConfiguration)
            outputConfiguration = parameters.getCompleteConfiguration(true, includeHidden);
         else
            outputConfiguration = parameters.getParameterMapSimpleFiltered(true, includeHidden);

         outputConfiguration = new TreeMap<>(outputConfiguration);

         juel.addReplacement("parameterMap", parameters.getParameterMapSimple());

         if (licenseServiceProvider.get().isEnterprise())
            juel.addReplacement("parameterMapSimple",
                  parametersPrettyPrint(outputConfiguration, outputCompleteConfiguration));
         else
            juel.addReplacement("parameterMapSimple", "");

      } else {
         juel.addReplacement("parameterMap", "");
         juel.addReplacement("parameterMapSimple", "");
      }
      if (outputFilters || outputCompleteConfiguration) {
         if (licenseServiceProvider.get().isEnterprise())
            juel.addReplacement("filterMapSimple", filtersPrettyPrint());
         else
            juel.addReplacement("filterMapSimple", "");
      } else
         juel.addReplacement("filterMapSimple", "");

      try {
         return juel.parse(template);
      } catch (Exception e) {
         return "ERROR";
      }
   }

   protected String parametersPrettyPrint(Map<String, Object> parameters, boolean outputCompleteConfiguration) {
      StringBuilder builder = new StringBuilder();

      Iterator<String> it = parameters.keySet().iterator();
      builder.append(PARAMETERS_TABLE_OPEN);
      builder.append("<caption>");
      builder.append(outputCompleteConfiguration ? messages.outputNameDynamicListConfiguration()
            : messages.outputNameDynamicListParameter());
      builder.append("</caption>");
      if (parameters.isEmpty()) {
         builder.append(PARAMETERS_TABLE_CLOSE);
         builder.append("<div style=\"font-size:10pt;\">" + messages.noparameters() + "</div>");
      } else {
         builder.append("<thead>");
         builder.append("<tr>");
         builder.append("<th>");
         builder.append(outputCompleteConfiguration ? messages.configurationParameter() : messages.parameter());
         builder.append("</th>");
         builder.append("<th>");
         builder.append(messages.value());
         builder.append("</th>");
         builder.append("</tr>");
         builder.append("</thead>");
         builder.append("<tbody>");

         boolean odd = true;
         while (it.hasNext()) {
            String parameter = it.next();
            Object value = parameters.get(parameter);
            if (odd)
               builder.append(PARAMETERS_ROW_TAG_OPEN_ODD);
            else
               builder.append(PARAMETERS_ROW_TAG_OPEN_EVEN);

            builder.append(PARAMETERS_FIELD_OPEN);
            builder.append(parameter);
            builder.append(PARAMETERS_FIELD_CLOSE);
            builder.append(PARAMETERS_FIELD_OPEN);
            builder.append(null != value
                  ? org.apache.commons.text.StringEscapeUtils.escapeHtml4(value.toString()).replace("-", "&#45;")
                  : null);

            builder.append(PARAMETERS_FIELD_CLOSE);
            builder.append(PARAMETERS_ROW_TAG_CLOSE);

            odd = !odd;
         }

         builder.append("</tbody>");
         builder.append(PARAMETERS_TABLE_CLOSE);
      }

      return builder.toString();
   }

   protected String filtersPrettyPrint() {
      StringBuilder builder = new StringBuilder();

      builder.append(FILTERS_TABLE_OPEN);
      builder.append("<caption>");
      builder.append(messages.filters());
      builder.append("</caption>");

      final Map<String, Map<String, Object>> filterMap = filterServiceProvider.get().getFilterMap(report);
      
      if (filterMap.isEmpty()) {
         builder.append(FILTERS_TABLE_CLOSE);
         builder.append("<div style=\"font-size:10pt;\">" + messages.nofilters() + "</div>");
      } else {
         builder.append("<thead>");
         builder.append("<tr>");
         builder.append("<th>");
         builder.append(messages.columns());
         builder.append("</th>");
         builder.append("<th>");
         builder.append(messages.type());
         builder.append("</th>");
         builder.append("<th>");
         builder.append(messages.value());
         builder.append("</th>");
         builder.append("</tr>");
         builder.append("</thead>");
         builder.append("<tbody>");
         
         ObjectHolder<Boolean> odd = new ObjectHolder<>();// odd/even color
         odd.set(true);
         
         filterMap.forEach((column, filters) -> {
            final int rowSpan = filters.size();
            
            if (odd.get())
               builder.append(FILTERS_ROW_TAG_OPEN_ODD);
            else
               builder.append(FILTERS_ROW_TAG_OPEN_EVEN);
            builder.append("<td rowspan=\"" + rowSpan + "\">");
            builder.append(column);
            builder.append(FILTERS_FIELD_CLOSE);
            
            ObjectHolder<Boolean> first = new ObjectHolder<>();
            first.set(true);
            
            filters.forEach((type, value) -> {
               
               if (!first.get()) {
                  if (odd.get())
                     builder.append(FILTERS_ROW_TAG_OPEN_ODD);
                  else
                     builder.append(FILTERS_ROW_TAG_OPEN_EVEN);
               }
               
               builder.append(FILTERS_FIELD_OPEN);
               builder.append(type);
               builder.append(FILTERS_FIELD_CLOSE);
               
               builder.append(FILTERS_FIELD_OPEN);
               builder.append(value.toString());
               builder.append(FILTERS_FIELD_CLOSE);
               builder.append(FILTERS_ROW_TAG_CLOSE);
               
               first.set(false);
               
            });
            odd.set(!odd.get());
         });
         
         builder.append("</tbody>");
         builder.append(FILTERS_TABLE_CLOSE);
      }

      addPrefilterOutput(builder);

      return builder.toString();
   }
   
   private void addPrefilterOutput(StringBuilder builder) {
      Map<BlockType, Object> prefilterMap = filterServiceProvider.get().getPrefilterMap(report);
      
      StringBuilder prefilterBuilder = new StringBuilder();
      prefilterBuilder.append("<div class=\"headlineSeparator\">").append(messages.prefilter()).append("</div>\r\n");
      if (!prefilterMap.isEmpty()) {
         prefilterBuilder.append("\t<div class=\"container prefilterList\">\r\n");
         prefilterMap.forEach((blockType, val) -> {
            printPrefilterBlock(blockType, (Set<?>) val, 1, prefilterBuilder);
         });
         prefilterBuilder.append("\t</div>\r\n\r\n");
      } else
         prefilterBuilder.append("<div style=\"font-size:10pt;\">").append(messages.noprefilter()).append("</div>");

      builder.append(prefilterBuilder);
   }

   protected void printPrefilterBlock(final BlockType blockType, Set<?> childElements, int currentDepth, StringBuilder builder) {

      builder.append("<div class=\"prefilterBlock " + (currentDepth % 2 == 0 ? "even" : "odd") + "\">\r\n");
      
      builder.append("<span class=\"prefilterOp\">").append(blockType.name()).append("</span>");
      
      childElements.forEach(child -> {
         if (child instanceof Map) {
            Map<?, ?> childMap = (Map<?, ?>) child;
            childMap.forEach((childKey, childVal) -> {
               if (childKey instanceof BlockType && childVal instanceof Set) {
                  // FilterBlock
                  printPrefilterBlock((BlockType) childKey, (Set<?>) childVal, currentDepth + 1, builder);
               } else if (childKey instanceof String && childVal instanceof List) {
                  // BinaryColumnFilter
                  printBinaryColumnFilter((String) childKey, (List<?>) childVal, currentDepth + 1, builder);
               } else if (childKey instanceof String && childVal instanceof Map) {
                  // ColumnFilter
                  printColumnFilter((String) childKey, (Map<?, ?>) childVal, currentDepth + 1, builder);
               }
            });
         }
      });
      
      builder.append("</div>");
   }
   
   private void printBinaryColumnFilter(String operator, List<?> values, int currentDepth, StringBuilder builder) {
      if (2 != values.size())
         throw new IllegalArgumentException("Values of binary column filter must have size 2");
      
      builder.append("<div class=\"prefilterChildFilter " + (currentDepth % 2 == 0 ? "even" : "odd") + "\">");
      builder.append("<div class=\"prefilterCompare\">");
      builder.append(htmlspecialchars(values.get(0).toString()));
      builder.append(" ");
      builder.append(htmlspecialchars(operator));
      builder.append(" ");
      builder.append(htmlspecialchars(values.get(1).toString()));
      builder.append("</div>");
      builder.append("</div>");
   }
   
   private void printColumnFilter(String columnName, Map<?, ?> columnFilters, int currentDepth, StringBuilder builder) {
      builder.append("<div class=\"prefilterChildFilter " + (currentDepth % 2 == 0 ? "even" : "odd") + "\">");
      builder.append("<div class=\"prefilterValue\">");
      builder.append(htmlspecialchars(columnName));
      
      builder.append("<div class=\"\">");
      columnFilters.forEach((filterKey, filterVal) -> {
         builder.append("<div class=\"filtertype\">");
         builder.append(htmlspecialchars(filterKey.toString()));
         builder.append("</div>");
         
         builder.append("<div class=\"filtervalue\">");
         builder.append(htmlspecialchars(filterVal.toString()));
         builder.append("</div>");
         
         builder.append("<div class=\"break\"></div>");
      });
      builder.append("</div>");
      builder.append("</div>");
      builder.append("</div>");
   }

   protected String htmlspecialchars(String str) {
      return org.apache.commons.text.StringEscapeUtils.escapeHtml4(str).replace("\r\n", "\n").replace("\n", "<br />");
   }

   protected void doAddPostTable() throws IOException {
      String post = configFile.getString(POST_PROPERTY, "");
      if (null != post && !"".equals(post.trim())) {
         String html = parseTemplate(post);
         writer.append(html);
      } else
         writer.append("</div>"); // wrap
   }

   protected void doAddHtmlHeader() throws IOException {
      writer.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>");
      writer.append("<style type=\"text/css\"><!--");
      doAddCss();
      writer.append("\n-->\n</style>\n");
      writer.append("<script>/* <![CDATA[ */");
      doAddScript();
      writer.append("\n/* ]]> */</script>\n");

      String head = configFile.getString(HEAD_PROPERTY, "");
      if (null != head && !"".equals(head.trim())) {
         String html = parseTemplate(head);
         writer.append(html);
      }

      String title = configFile.getString(TITLE_PROPERTY, "");
      if (null != title && !"".equals(title.trim())) {
         String html = parseTemplate(title);
         writer.append("<title>").append(html).append("</title>\n");
      } else
         writer.append("<title>").append(report.getName()).append("</title>\n");
   }

   private void doAddScript() throws IOException {
      String script = configFile.getString(SCRIPT_PROPERTY, "");
      if (null != script && !"".equals(script.trim())) {
         String html = parseTemplate(script);
         writer.append(html);
      }
   }

   protected void doAddCss() throws IOException {
      writer.append(themeServiceProvider.get().getTheme());

      if (null != cellFormatters) {
         for (int i = 0; i < cellFormatters.length; i++) {
            if (cellFormatters[i] instanceof ColumnFormatCellFormatter
                  && ((ColumnFormatCellFormatter) cellFormatters[i]).getColumnFormat() instanceof ColumnFormatNumber) {
               writer.append("tr td:nth-child(");
               writer.append("" + (i + 1));
               writer.append(") { text-align: right; }");
            }
         }
      }

      String style = configFile.getString(STYLE_PROPERTY, "");
      if (null != style && !"".equals(style.trim())) {
         String html = parseTemplate(style);
         writer.append(html);
      } else {
         writer.append("@page {size: A4 portrait;margin-top:1.5cm;");
         writer.append(
               "@top-left { content: \"" + org.apache.commons.text.StringEscapeUtils.unescapeHtml4(report.getName())
                     + "\"; font-family: DejaVu Sans, Sans-Serif; font-size: 8pt; }");
         writer.append("@top-right {content: \"" + getNowString()
               + "\"; font-family: DejaVu Sans, Sans-Serif; font-size: 8pt; }");
         writer.append("@bottom-right { content: \"" + ReportEnginesMessages.INSTANCE.page() + " \" counter(page) \" "
               + ReportEnginesMessages.INSTANCE.of()
               + " \" counter(pages); font-family: DejaVu Sans, Sans-Serif; font-size: 8pt; }");
         writer.append("}");
         writer.append("* { font-family: DejaVu Sans, Sans-Serif; }");
      }
   }

   @Override
   public void addField(Object field, CellFormatter formatter) throws IOException {
      writer.append(DATA_FIELD_OPEN);
      writer.append(StringEscapeUtils.escapeXml(formatter.format(getValueOf(field))));
      writer.append(DATA_FIELD_CLOSE);
   }

   protected void addField(Object field) throws IOException {
      writer.append(DATA_FIELD_OPEN);
      writer.append(StringEscapeUtils.escapeXml(String.valueOf(getValueOf(field))));
      writer.append(DATA_FIELD_CLOSE);
   }

   @Override
   public void close() throws IOException {
      writer.append(DATA_ROW_TAG_CLOSE);
      writer.append("</tbody>");
      writer.append(TABLE_CLOSE);
      doAddPostTable();
      writer.append("</body></html>");

      if (writer instanceof Closeable)
         ((Closeable) writer).close();
   }

   @Override
   public void nextRow() throws IOException {
      writer.append(DATA_ROW_TAG_CLOSE);
      if (odd)
         writer.append(DATA_ROW_TAG_OPEN_ODD);
      else
         writer.append(DATA_ROW_TAG_OPEN_EVEN);

      odd = !odd;
   }

   @Override
   public void addGroupRow(int[] subtotalIndices, Object[] subtotals, int[] subtotalGroupFieldIndices,
         Object[] subtotalGroupFieldValues, int rowSize, CellFormatter[] cellFormatters) throws IOException {
      writer.append(DATA_ROW_TAG_CLOSE);
      writer.append(DATA_SUBTOTAL_ROW_TAG_OPEN);
      odd = false;

      int aggIndex = 0;
      int groupIndex = 0;
      for (int i = 0; i < rowSize; i++) {
         if (aggIndex < subtotalIndices.length && i == subtotalIndices[aggIndex]) {
            addField(subtotals[aggIndex], cellFormatters.length > i ? cellFormatters[i] : Column.DUMMY_FORMATTER);
            aggIndex++;
         } else if (groupIndex < subtotalGroupFieldIndices.length && i == subtotalGroupFieldIndices[groupIndex]) {
            addField(subtotalGroupFieldValues[groupIndex],
                  cellFormatters.length > i ? cellFormatters[i] : Column.DUMMY_FORMATTER);
            groupIndex++;
         } else
            addField("", Column.DUMMY_FORMATTER);
      }

      writer.append(DATA_ROW_TAG_CLOSE);
      writer.append(DATA_ROW_TAG_OPEN_ODD);
   }

   protected String getNowString() {
      return DateFormat
            .getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, localizationServiceProvider.get().getLocale())
            .format(new Date());
   }

   @Override
   public String[] getFormats() {
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_HTML };
   }

   @Override
   public CompiledReport getTableObject() {
      return new CompiledHTMLTableReport(writer.toString());
   }

   @Override
   public CompiledReport getFormatInfo() {
      return new CompiledHTMLTableReport(null);
   }

   @Override
   public boolean isCatchAll() {
      return false;
   }
}
