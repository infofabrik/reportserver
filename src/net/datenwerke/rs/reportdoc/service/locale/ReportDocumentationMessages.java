package net.datenwerke.rs.reportdoc.service.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface ReportDocumentationMessages extends Messages{

	public final static ReportDocumentationMessages INSTANCE = LocalizationServiceImpl.getMessages(ReportDocumentationMessages.class);
	
	String creatingPdf();

	String commandDeployReport_sub_analyze_description();
	String commandDeployReport_sub_analyze_newReport();
	String commandDeployReport_sub_analyze_destinationReport();
	String commandDeployReport_description();
	
	String commandDeployReport_sub_analyze_leftReport();
	String commandDeployReport_sub_analyze_rightReport();
	String commandDeployReport_sub_analyze_ignorecase();
	
	String commandVariantTest_description();
	String commandVariantTest_description_arg_report();
	String commandVariantTest_description_arg_datasource();

}
