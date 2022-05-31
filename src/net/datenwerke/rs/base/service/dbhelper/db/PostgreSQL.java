package net.datenwerke.rs.base.service.dbhelper.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.jdbc.PgSQLXML;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

/**
 * 
 *
 */
public class PostgreSQL extends DatabaseHelper {

   public static final String DB_NAME = "PostgreSQL";
   public static final String DB_DRIVER = "org.postgresql.Driver";
   public static final String DB_DESCRIPTOR = "DBHelper_PostgreSQL";

   @Override
   public String getDescriptor() {
      return DB_DESCRIPTOR;
   }

   @Override
   public String getDriver() {
      return DB_DRIVER;
   }

   @Override
   public String createDummyQuery() {
      return "SELECT 1+1";
   }

   @Override
   public String getName() {
      return DB_NAME;
   }

   @Override
   public ResultSetObjectHandler createResultSetHandler(final ResultSet resultSet, final Connection con)
         throws SQLException {
      return new ResultSetObjectHandler() {

         @Override
         public Object getObject(int pos) throws SQLException {
            Object o = resultSet.getObject(pos);
            // we just display the xml text
            if (o instanceof PgSQLXML) {
               PgSQLXML xml = (PgSQLXML) o;
               String xmlString = xml.getString();
               return xmlString;
            }
            return o;
         }
      };
   }
}
