package net.datenwerke.rs.base.service.datasources.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface DatasourcesMessages extends Messages {

   public final static DatasourcesMessages INSTANCE = LocalizationServiceImpl.getMessages(DatasourcesMessages.class);

   String databaseDatasourceTypeName();

   String commandSql_description();

   String commandSql_datasource();

   String exceptionCouldNotExecuteStmt(String message);

   String exceptionCouldNotOpenDatasource(String message);

   String exceptionCouldNotPrepareStmt(String message);

   String commandConnPoolStats_description();
}
