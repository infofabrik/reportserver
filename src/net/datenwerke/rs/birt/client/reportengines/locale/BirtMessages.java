package net.datenwerke.rs.birt.client.reportengines.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface BirtMessages extends Messages{

	public final static BirtMessages INSTANCE = GWT.create(BirtMessages.class);
	
	String reportTypeName();

	String editBirtReport();

	String rptdesign();

	String fileName();

	String birtDownloadToolbarHeading();

	String birtDownloadToolbarButtonText();

	String birtReportDatasourceTypeName();

	String parameterProposalBtn();

	String noProposalsFoundTitle();

	String noProposalsFoundText();

	String datasourceTarget();

	String datasourceReport();

	String datasourceType();

	String queryWrapper();

	String fileMustBeRptDesign();

}
