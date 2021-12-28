package net.datenwerke.rs.core.client.reportmanager.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ReportmanagerMessages extends Messages {

   public final ReportmanagerMessages INSTANCE = GWT.create(ReportmanagerMessages.class);

   String addSubreport();

   String createPreview();

   String datasource();

   String date();

   String dateAndTime();

   String dateParameterName();

   String defaultValue();

   String editFolder();

   String editJasperReport();

   String editReportVariant();

   String editTableReport();

   String folder();

   String ID();

   String importConfigFailureFolderBeneathReport();

   String importConfigFailureNoParent();

   String importConfigFailureVariantParent();

   String importerName();

   String importMainPropertiesDescription();

   String importMainPropertiesHeadline();

   String importRemoveKeyFieldLabel();

   String importWhereTo();

   String insert();

   String jrxmlFile();

   String JRXMLMaster();

   String key();

   String lastModified();

   String metadataDataSource();

   String nameMasterJRXML();

   String owner();

   String parameterManagement();

   String preview();

   String previewCreated();

   String quickExportIncludeVariantsText();

   String quickExportIncludeVariantsTitle();

   String refreshed();

   String removedSuccess();

   String reportManagement();

   String reportmanager();

   String reportSecurityDescription();

   String stringParameterName();

   String subreports();

   String time();

   String type();

   String upload();

   String uploadError();

   String selectParamBtnLabel();

   String execute();

   String switchToFormulaMode();

   String switchToDateMode();

   String writeProtect();

   String configurationProtect();

   String mdxEditor();

   String variant();

   String reportLabel();

   String selectReportHeader();

   String catalog();

   String isVariant();

   String parentReportName();

   String propertyAllowCubify();

   String fieldLabelCubify();

   String fileSelectionParameterName();

   String deleteVariantConfirmMessage(String string);

   String currentUser();

   String currentReport();

   String expressions();
}
