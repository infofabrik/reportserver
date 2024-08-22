package net.datenwerke.rs.utils.oracle;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;

public class StupidOracleServiceImpl implements StupidOracleService {

   @Override
   public boolean isOracleTimestamp(Object obj) {
      if (null == obj)
         return false;
      try {
         Class<?> cls = Class.forName("oracle.sql.TIMESTAMP");
         if (null != cls && cls.isAssignableFrom(obj.getClass()))
            return true;
         cls = Class.forName("oracle.sql.TIMESTAMPLTZ");
         if (null != cls && cls.isAssignableFrom(obj.getClass()))
            return true;
         cls = Class.forName("oracle.sql.TIMESTAMPTZ");
         if (null != cls && cls.isAssignableFrom(obj.getClass()))
            return true;

      } catch (Exception e) {
      }
      return false;
   }

   @Override
   public Timestamp getTimeStampFromOracleTimestamp(Object obj, Connection con) {
      if (!isOracleTimestamp(obj))
         throw new IllegalArgumentException();
      try {
         Class<?> oraconClass = Class.forName("oracle.jdbc.OracleConnection");
         Class<?> tsClass = Class.forName("oracle.sql.TIMESTAMP");
         Class<?> ltzClass = Class.forName("oracle.sql.TIMESTAMPLTZ");
         Class<?> tzClass =  Class.forName("oracle.sql.TIMESTAMPTZ");

         Object tsValue = null;
         if (ltzClass.isAssignableFrom(obj.getClass())) {
            Method tsValueMethod = obj.getClass().getMethod("timestampValue", Connection.class);
            tsValue = tsValueMethod.invoke(obj, con.unwrap(oraconClass));
         }

         if (tzClass.isAssignableFrom(obj.getClass())) {
            Method tsValueMethod = obj.getClass().getMethod("timestampValue", Connection.class);
            tsValue = tsValueMethod.invoke(obj, con.unwrap(oraconClass));
         }

         if (tsClass.isAssignableFrom(obj.getClass())) {
            Method tsValueMethod = obj.getClass().getMethod("timestampValue");
            tsValue = tsValueMethod.invoke(obj);
         }

         return (Timestamp) tsValue;
      } catch (Exception e) {
         throw new IllegalArgumentException(e);
      }
   }

   @Override
   public Date getDateFromOracleDatum(Object obj) {
      if (!isOracleDatum(obj))
         throw new IllegalArgumentException();
      try {
         return (Date) obj.getClass().getMethod("dateValue").invoke(obj);
      } catch (Exception e) {
         throw new IllegalArgumentException(e);
      }
   }

   @Override
   public boolean isOracleDatum(Object obj) {
      if (null == obj)
         return false;
      try {
         Class<?> cls = Class.forName("oracle.sql.Datum");
         if (null != cls && cls.isAssignableFrom(obj.getClass()))
            return true;
      } catch (Exception e) {
      }
      return false;
   }

}
