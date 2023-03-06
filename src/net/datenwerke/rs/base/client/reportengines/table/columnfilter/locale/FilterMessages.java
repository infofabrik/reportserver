package net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface FilterMessages extends Messages {

   public static final FilterMessages INSTANCE = GWT.create(FilterMessages.class);

   String excludeRangeTitle();

   String excludeTitle();

   String includeRangesTitle();

   String includeTitle();

   String grid();

   String value();

   String textViewTitle();

   String textViewInvalidMessage();

   String rangeFrom();

   String rangeTo();

   String columnSelectionTitle();

   String editFilter();

   String selection();

   String linkedDescription();

   String column();

   String highlightColor();

   String alias();

   String removeAllFilters();

   String removeColumn();

   String selectColumns();

   String removeAllColumns();

   String filterHeading();

   String generalColumnProperties();

   String columnProperties();

   String aggregateHeading();

   String aggregate();

   String properties();

   String aggregateFunction();

   String defaultAlias();

   String description();

   String filter();

   String type();

   String distinctFilter();

   String errorRangeParseTextLine(String line);

   String errorRangeNoHyphen(String line);

   String validationErrorNoNumberFormatInvalidCharacter(String value);

   String validationErrorNoIntegerFormatInvalidCharacter(String value);

   String loadColumnsMessage();

   String orderHeading();

   String visible();

   String hidden();

   String nullHandling();

   String orderAsc();

   String orderDesc();

   String orderNone();

   String excludeValue();

   String includeValue();

   String nullHandlingLabel();

   String filterDialogHeading(String name, String name2);

   String previewEnhancerMenuFilterHeading();

   String caseSensitiveLabel();

   String editColumnFilter();

   String editColumnFormat();

   String format();

   String formatTitle();

   String removeFormat();

   String decimalPlacesLabel();

   String thousandSeparatorLabel();

   String currencySymbolLabel();

   String formatDateBaseFormat();

   String formatDateTargetFormat();

   String formatDateRollover();

   String formatDateReplaceError();

   String formatDateReplaceErrorWith();

   String formatDialogHeading(String name, String type);

   String formatTypeDefault();

   String formatTypeNumber();

   String formatTypeCurrency();

   String formatTypeDate();

   String formatTypePercent();

   String formatTypeScientific();

   String formatTypeText();

   String formatTypeTemplate();

   String formateTemplateLabel();

   String editSubtotals();

   String subtotalGroupColumnsLabel();

   String selectedGroupingColumns();

   String moveColumnsToTop();

   String moveColumnsToBottom();

   String nullReplacementLabel();

   String formatTypeLabel();

   String nullHandlingDefaultLabel();

   String nullHandlingIncludeLabel();

   String nullHandlingExcludeLabel();

   String optionsLabel();

   String noSearchPossible();

   String validationErrorTimeStamp(String value);

   String validationErrorDate(String value);

   String noFloatEqualFilterWarning();

   String blobAggregateCancelTitle();

   String blobAggregateCancelMsg();

   String columnCannotBeImportedTitle();

   String columnNotAllowedInReport(String column);

   String convertToPrefilterTitle();

   String convertToPrefilterText();

   String dimension();

   String deleteColumnOnKeyPressTitle();

   String deleteColumnOnKeyPressText();

   String subTotalFilterWarningMsg();

   String defaultWidth();

   String width();

   String failed();

   String previewWidthWarningMsg();

   String exportEmptyValuesAsString();

}