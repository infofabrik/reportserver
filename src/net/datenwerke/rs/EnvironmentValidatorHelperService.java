package net.datenwerke.rs;

import java.sql.SQLException;
import java.util.Properties;

public interface EnvironmentValidatorHelperService {

   String getSchemaVersion() throws SQLException;

   Properties getJpaProperties();

}
