package net.datenwerke.rs.utils.oracle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;

import com.google.inject.ImplementedBy;

@ImplementedBy(StupidOracleServiceImpl.class)
public interface StupidOracleService {

	boolean isOracleTimestamp(Object obj);

	boolean isOracleDatum(Object obj);

	Timestamp getTimeStampFromOracleTimestamp(Object obj, Connection con);

	Date getDateFromOracleDatum(Object obj);

}
