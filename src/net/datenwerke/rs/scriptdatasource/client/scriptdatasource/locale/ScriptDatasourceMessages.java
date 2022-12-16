package net.datenwerke.rs.scriptdatasource.client.scriptdatasource.locale;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;

public interface ScriptDatasourceMessages extends Messages {

   public static final ScriptDatasourceMessages INSTANCE = GWT.create(ScriptDatasourceMessages.class);

   String scriptDatasourceTypeName();

   String scriptLabel();

   String editDataSource();

   String argumentsLabel();

   String databaseCacheLabel();

   String queryWrapperLabel();

   String defineAtTargetLabel();

}