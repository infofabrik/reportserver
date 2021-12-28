package net.datenwerke.dbpool;

import com.google.inject.ImplementedBy;

@ImplementedBy(JdbcServiceImpl.class)
public interface JdbcService {

   String adaptJdbcUrl(String jdbcUrl);

}
