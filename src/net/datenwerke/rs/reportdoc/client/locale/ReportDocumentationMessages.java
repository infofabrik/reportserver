package net.datenwerke.rs.reportdoc.client.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ReportDocumentationMessages extends Messages {

	public final ReportDocumentationMessages INSTANCE = GWT.create(ReportDocumentationMessages.class);
	
	String name();

	String berichtsdokuHelpText();

	String berichtsdokuGroupHeading();

	String noBerichtsdokuAssignedText();

	String noBerichtsdokuAssignedHeader();

	String couldNotLoadDoku();

	String reportChangedInfoHeader();
	String reportChangedInfoMessage();
	
	String loadError();
	String general();
	String baseReport();
	String distinct();
	String yes();
	String no();
	String groupedBy();
	String subtotals();
	String computedColumns();
	String columns();
	String hidden();
	String order();
	String emptyCells();
	String aggregation();
	String formattedAs();
	String formatCurrency();
	String formatNumber();
	String formatDate();
	String formatText();
	String formatTemplate();
	String ascending();
	String descending();
	String excluded();
	String included();
	String parameter();
	String defaultOrFirst();
	String all();
	String prefilter();
	String reportDoku();
	String page();
	String of();
	String created();
	String lastChanged();
	String creator();
	String caseSensitiveFilter();
	String teamspaces();
	String scheduler();
	String variants();

	String date();
	String user();
	String change();
	String history();
	String revision();
	String deleted();
	String noHistory();
	
	String column();
	
	String deploymentAnalysisDesc();
	String deploymentAnalysisDesc2();
	String deploymentAnalysis();
	String deploymentAnalysisRightMsg();
	String deploymentAnalysisBothMsg();
	String deploymentAnalysisJavaType();
	String deploymentAnalysisSize();
	String deploymentAnalysisSqlType();
	String deploymentAnalysisReport();
	String deploymentAnalysisLeft();
	String deploymentAnalysisRight();
	String deploymentAnalysisConflicts();
	String deploymentAnalysisVariantsMissingColumns();
	String deploymentAnalysisVariantsConflictingColumns();
	String deploymentAnalysisNoConflicts();
	
	String variantTest();
	
}