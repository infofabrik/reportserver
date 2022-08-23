package net.datenwerke.rs.core.client.datasourcemanager.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface DatasourcesMessages extends Messages {

   public final static DatasourcesMessages INSTANCE = GWT.create(DatasourcesMessages.class);

   String dataSourcePermissionModuleDescription();

   String datasources();

   String query();

   String url();

   String username();

   String password();

   String database();

   String folder();

   String insert();

   String adminModuleName();

   String editDataSource();

   String dataSource();

   String editFolder();

   String useDefault();

   String useDefaultFailureTitle();

   String useDefaultFailureMessage();

   String useDefaultSuccessMessage();

   String loading();

   String submit();

   String setDefaultDatasource();

   String urlContainsWhitespaceWarning();

   String clearPassword();

   String importWhereTo();

   String importConfigFailureNoParent();

   String importMainPropertiesDescription();

   String importMainPropertiesHeadline();

   String importerName();

   String exportGroup();

   String quickExportText();

   String execute();

   String dataSourceConfigTitle();

   String defaultDatasource();

   String mondrian8();
   
   String objectInfoGeneralInfo();
   
   String objectInfoUrlInfo();
   
   String objectInfoFuncstionsSection();
   
   String objectInfoSupportsSection();
   
}