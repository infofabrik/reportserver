package net.datenwerke.rs.base.client;

public enum AvailableReportProperties {
   PROPERTY_OUTPUT_FORMAT_AUTH("output_format_auth"), 
   PROPERTY_OUTPUT_FORMAT_DEFAULT("output_format_default"),
   PROPERTY_JXLS_STREAM("jxls_stream"),
   PROPERTY_JXLS_STREAM_ROW_ACCESS_WINDOW_SIZE("jxls_stream_row_access_window_size"),
   PROPERTY_JXLS_STREAM_COMPRESS_TMP_FILES("jxls_stream_compress_tmp_files"),
   PROPERTY_JXLS_STREAM_USE_SHARED_STRINGS_TABLE("jxls_stream_use_shared_strings_table"),
   PROPERTY_OUTPUT_PARAMETERS("output_parameters", true), 
   PROPERTY_OUTPUT_FILTERS("output_filters", true),
   PROPERTY_OUTPUT_INCLUDE_PARAMETERS("output_include_hidden_parameters", true),
   PROPERTY_OUTPUT_COMPLETE_CONFIGURATION("output_complete_configuration", true),
   PROPERTY_SUPPRESS_AUTOMATIC_PREVIEW("suppress_automatic_preview", false),
   PROPERTY_DL_FILTER_SHOW_CONSISTENCY("ui:filter:consistency:show", true),
   PROPERTY_DL_FILTER_DEFAULT_CONSISTENCY("ui:filter:consistency:default", true),
   PROPERTY_DL_FILTER_COUNT_DEFAULT("ui:filter:count:default", true),
   PROPERTY_DL_PREVIEW_COUNT_DEFAULT("ui:preview:count:default", true),
   PROPERTY_VETO_LARGE_REPORT_EXPORT("export:veto_large_report");

   private String value;
   private boolean dynamicListSpecificProperty;

   AvailableReportProperties(String value) {
      this.value = value;
   }

   AvailableReportProperties(String param, boolean isOutputProperty) {
      this(param);
      this.dynamicListSpecificProperty = isOutputProperty;
   }

   public String getValue() {
      return value;
   }

   public boolean isDynamicListSpecificProperty() {
      return dynamicListSpecificProperty;
   }
};