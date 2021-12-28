package net.datenwerke.rs.base.service.reportengines.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface ReportEnginesMessages extends Messages {

   public final static ReportEnginesMessages INSTANCE = LocalizationServiceImpl
         .getMessages(ReportEnginesMessages.class);

   String xlsOutputGeneratorSheetName();

   String rsTableToXLSNullValue();

   String rsTableToXLSBinaryData();

   String dateFormat();

   String timeFormat();

   String exceptionNoColumnsSelected();

   String exceptionOutputFormatNotSupportsDuplicateNames(String columnName);

   String jasperReportTypeName();

   String jasperReportVariantTypeName();

   String tableReportTypeName();

   String tableReportVariantTypeName();

   String detailRowsInGroup(int rowsInGroup);

   String exceptionNeedAggregateAndGroupForSubtotals();

   String page();

   String of();

   String exceptionNoFilterOnNonAggregateNonGroupingForSubtotals();

   String parameter();

   String value();

   String configurationParameter();

   String outputNameDynamicList();

   String outputNameDynamicListParameter();

   String outputNameDynamicListConfiguration();

   String filters();

   String columns();

   String type();

   String excludeTitle();

   String includeTitle();

   String includeRangesTitle();

   String excludeRangeTitle();

   String nullHandlingLabel();

   String nullInclude();

   String nullExclude();

   String caseSensitiveLabel();

   String yes();

   String no();

   String prefilter();

   String caseSensitiveFilter();

   String emptyCells();

   String excluded();

   String included();

   String configuration();

   String parameters();

   String noparameters();

   String nofilters();

   String noprefilter();
}
