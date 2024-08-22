package net.datenwerke.rs.core.client.reportexecutor.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ReportexecutorMessages extends Messages {

   public static ReportexecutorMessages INSTANCE = GWT.create(ReportexecutorMessages.class);

   String changeVariant(String reportName);

   String columns();

   String confirmRemoveMessage(String reportName);

   String confirmRemoveTitle();

   String createNewVariant(String reportName);

   String createVariant();

   String csvConfigPrintHeader();

   String csvConfigQuote();

   String csvConfigSeparator();

   String csvConfigLineSeparator();

   String editVariant();

   String executeDuration(double executeDuration, double serverCall);

   String executing();

   String export2CSV();

   String failed();

   String frontendHeadline();

   String geolocationLookupText();

   String loading();

   String loadingExecuteDuration();

   String loadingNumColumns();

   String loadingNumPages();

   String loadingNumRecords();

   String noColumnsSelected();

   String noAggregationSelected();

   String pages();

   String parameterWidgetHeader();

   String preview();

   String records();

   String removeDescription();

   String store();

   String storedSuccessfully();

   String storeNew();

   String success();

   String successText(String variantName);

   String variantDeleted();

   String executeAreaModule();

   String editVariantConfirmTitle();

   String editVariantConfirmMsg();

   String closeChangedReportTitle();

   String closeChangedReportMessage();

   String forceLegacyPdfPreview();

   String decimalSeparator();

   String linktomsg();

   String countRecords();

   String loadingDataTitle();

   String loadingDataMsg();

   String pleaseProvideName();

   String csvConfigCharset();

   String jxlsVariantTemplate();

   String jxlsReportTemplate();

   String jxlsNumberColumnWidth();

   String jxlsTextColumnWidth();

   String jxlsDateColumnWidth();

   String jxlsCurrencyColumnWidth();

   String previewDisabledLabel();
}
