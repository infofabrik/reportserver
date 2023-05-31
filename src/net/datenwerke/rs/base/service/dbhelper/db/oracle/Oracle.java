package net.datenwerke.rs.base.service.dbhelper.db.oracle;

import static java.util.stream.Collectors.toList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.IntStream;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.utils.SqlTypes;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OrderOffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil;
import net.datenwerke.rs.utils.oracle.StupidOracleService;
import oracle.xdb.XMLType;

/**
 * 
 *
 */
public class Oracle extends DatabaseHelper {

   public static final String CONVERT_BLOBS_FOR_FILTERING_PROPERTY = "database.oracle.filter.convertclobs";

   public static final String DB_NAME = "Oracle";
   public static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
   public static final String DB_DESCRIPTOR = "DBHelper_Oracle";
   public static final String DB_DESCRIPTION = "Oracle JDBC driver";

   private final StupidOracleService sos;

   private ConfigService configService;

   @Inject
   public Oracle(StupidOracleService sos, ConfigService configService) {

      this.sos = sos;
      this.configService = configService;
   }

   private boolean isConvertClob() {
      return configService.getConfigFailsafe("misc/misc.cf").getBoolean(CONVERT_BLOBS_FOR_FILTERING_PROPERTY, true);
   }

   @Override
   public String getDescriptor() {
      return DB_DESCRIPTOR;
   }

   @Override
   public String getDriver() {
      return DB_DRIVER;
   }

   @Override
   public String getName() {
      return DB_NAME;
   }

   @Override
   public ResultSetObjectHandler createResultSetHandler(final ResultSet resultSet, final Connection con)
         throws SQLException {
      final ResultSetMetaData md = resultSet.getMetaData();
      final int cnt = md.getColumnCount();
      final List<Integer> dateIndices = IntStream
            .range(1, cnt)
            .filter(LambdaExceptionUtil.rethrowIntPredicate( i -> SqlTypes.isDateLikeType(md.getColumnType(i)) ))
            .boxed()
            .collect(toList());
      
      if (dateIndices.isEmpty())
         return pos -> getOracleObject(resultSet, pos);

      return pos -> {
         if (dateIndices.contains(pos)) {
            Object obj = resultSet.getObject(pos);

            if (sos.isOracleTimestamp(obj)) {
               return sos.getTimeStampFromOracleTimestamp(obj, con);
            } else if (sos.isOracleDatum(obj))
               return sos.getDateFromOracleDatum(obj);
         }
         return getOracleObject(resultSet, pos);
      };
   }

   private Object getOracleObject(ResultSet resultSet, int pos) throws SQLException {
      Object o = resultSet.getObject(pos);
      // we just display the xml text
      if (o instanceof XMLType) {
         XMLType xml = (XMLType) o;
         String xmlString = xml.getString();
         return xmlString;
      }
      return o;
   }

   @Override
   public String prepareColumnForSorting(String name, Column column) {
      if (isConvertClob() && Integer.valueOf(SqlTypes.CLOB).equals(column.getType()))
         return "dbms_lob.substr(" + name + ", 4000, 1)";
      return super.prepareColumnForComparison(name, column);
   }

   @Override
   public String prepareColumnForComparison(String name, Column column) {
      if (isConvertClob() && Integer.valueOf(SqlTypes.CLOB).equals(column.getType()))
         return "dbms_lob.substr(" + name + ", 4000, 1)";
      return super.prepareColumnForComparison(name, column);
   }

   @Override
   public String prepareColumnForDistinctQuery(String name, Column column) {
      if (isConvertClob() && Integer.valueOf(SqlTypes.CLOB).equals(column.getType()))
         return "dbms_lob.substr(" + name + ", 4000, 1) " + name;
      return super.prepareColumnForDistinctQuery(name, column);
   }

   @Override
   public LimitQuery getNewLimitQuery(Query nestedQuery, QueryBuilder queryBuilder) {
      return new OracleLimitQuery(nestedQuery, queryBuilder);
   }

   @Override
   public OffsetQuery getNewOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      return new OracleOffsetQuery(nestedQuery, queryBuilder, columnNamingService);
   }

   @Override
   public OrderOffsetQuery getNewOrderOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      return new OracleOrderOffsetQuery(nestedQuery, queryBuilder, columnNamingService);
   }
   
   @Override
   public String getDescription() {
      return DB_DESCRIPTION;
   }
   
   @Override
   public String createDummyQuery() {
      return "SELECT * FROM DUAL";
   }
}
