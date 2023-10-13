package net.datenwerke.rs.core.service.internaldb.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface InternalDbMessages extends Messages {

   public final static InternalDbMessages INSTANCE = LocalizationServiceImpl.getMessages(InternalDbMessages.class);

   String internalDbName();

   String internalDbVersion();

   String internalDbDriverName();

   String internalDbDriverVersion();

   String internalDbJdbcMajorVersion();

   String internalDbJdbcMinorVersion();

   String internalDbJdbcUrl();

   String internalDbUsername();

   String internalDatasourceName();

   String internalDatasourceId();

   String internalDatasourcePath();

   String internalDbJdbcProperties();

   String dbConfiguration();

   String internalDatasource();

   String noInternalDbFound();

   String schemaVersion();
   
   String key();

}
