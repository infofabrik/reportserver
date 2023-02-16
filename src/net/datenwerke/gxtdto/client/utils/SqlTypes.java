package net.datenwerke.gxtdto.client.utils;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Singleton;

@Singleton
public class SqlTypes {

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>BIT</code>.
    */
   public final static int BIT = -7;
   public final static String BIT_NAME = "BIT";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>TINYINT</code>.
    */
   public final static int TINYINT = -6;
   public final static String TINYINT_NAME = "TINYINT";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>SMALLINT</code>.
    */
   public final static int SMALLINT = 5;
   public final static String SMALLINT_NAME = "SMALLINT";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>INTEGER</code>.
    */
   public final static int INTEGER = 4;
   public final static String INTEGER_NAME = "INTEGER";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>BIGINT</code>.
    */
   public final static int BIGINT = -5;
   public final static String BIGINT_NAME = "BIGINT";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>FLOAT</code>.
    */
   public final static int FLOAT = 6;
   public final static String FLOAT_NAME = "FLOAT";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>REAL</code>.
    */
   public final static int REAL = 7;
   public final static String REAL_NAME = "REAL";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>DOUBLE</code>.
    */
   public final static int DOUBLE = 8;
   public final static String DOUBLE_NAME = "DOUBLE";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>NUMERIC</code>.
    */
   public final static int NUMERIC = 2;
   public final static String NUMERIC_NAME = "NUMERIC";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>DECIMAL</code>.
    */
   public final static int DECIMAL = 3;
   public final static String DECIMAL_NAME = "DECIMAL";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>CHAR</code>.
    */
   public final static int CHAR = 1;
   public final static String CHAR_NAME = "CHAR";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>VARCHAR</code>.
    */
   public final static int VARCHAR = 12;
   public final static String VARCHAR_NAME = "VARCHAR";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>LONGVARCHAR</code>.
    */
   public final static int LONGVARCHAR = -1;
   public final static String LONGVARCHAR_NAME = "LONGVARCHAR";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>DATE</code>.
    */
   public final static int DATE = 91;
   public final static String DATE_NAME = "DATE";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>TIME</code>.
    */
   public final static int TIME = 92;
   public final static String TIME_NAME = "TIME";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>TIMESTAMP</code>.
    */
   public final static int TIMESTAMP = 93;
   public final static String TIMESTAMP_NAME = "TIMESTAMP";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>BINARY</code>.
    */
   public final static int BINARY = -2;
   public final static String BINARY_NAME = "BINARY";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>VARBINARY</code>.
    */
   public final static int VARBINARY = -3;
   public final static String VARBINARY_NAME = "VARBINARY";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>LONGVARBINARY</code>.
    */
   public final static int LONGVARBINARY = -4;
   public final static String LONGVARBINARY_NAME = "LONGVARBINARY";

   /**
    * <P>
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>NULL</code>.
    */
   public final static int NULL = 0;
   public final static String NULL_NAME = "NULL";

   /**
    * The constant in the Java programming language that indicates that the SQL
    * type is database-specific and gets mapped to a Java object that can be
    * accessed via the methods <code>getObject</code> and <code>setObject</code>.
    */
   public final static int OTHER = 1111;
   public final static String OTHER_NAME = "OTHER";

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>JAVA_OBJECT</code>.
    * 
    * @since 1.2
    */
   public final static int JAVA_OBJECT = 2000;
   public final static String JAVA_OBJECT_NAME = "JAVA OBJECT";

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>DISTINCT</code>.
    * 
    * @since 1.2
    */
   public final static int DISTINCT = 2001;
   public final static String DISTINCT_NAME = "DISTINCT";

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>STRUCT</code>.
    * 
    * @since 1.2
    */
   public final static int STRUCT = 2002;
   public final static String STRUCT_NAME = "STRUCT";

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>ARRAY</code>.
    * 
    * @since 1.2
    */
   public final static int ARRAY = 2003;
   public final static String ARRAY_NAME = "ARRAY";

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>BLOB</code>.
    * 
    * @since 1.2
    */
   public final static int BLOB = 2004;
   public final static String BLOB_NAME = "BLOB";

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>CLOB</code>.
    * 
    * @since 1.2
    */
   public final static int CLOB = 2005;
   public final static String CLOB_NAME = "CLOB";

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>REF</code>.
    * 
    * @since 1.2
    */
   public final static int REF = 2006;
   public final static String REF_NAME = "REF";

   /**
    * The constant in the Java programming language, somtimes referred to as a type
    * code, that identifies the generic SQL type <code>DATALINK</code>.
    *
    * @since 1.4
    */
   public final static int DATALINK = 70;
   public final static String DATALINK_NAME = "DATALINK";

   /**
    * The constant in the Java programming language, somtimes referred to as a type
    * code, that identifies the generic SQL type <code>BOOLEAN</code>.
    *
    * @since 1.4
    */
   public final static int BOOLEAN = 16;
   public final static String BOOLEAN_NAME = "BOOLEAN";

   // ------------------------- JDBC 4.0 -----------------------------------

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>ROWID</code>
    *
    * @since 1.6
    *
    */
   public final static int ROWID = -8;
   public final static String ROWID_NAME = "ROWID";

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>NCHAR</code>
    *
    * @since 1.6
    */
   public static final int NCHAR = -15;
   public final static String NCHAR_NAME = "NCHAR";

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>NVARCHAR</code>.
    *
    * @since 1.6
    */
   public static final int NVARCHAR = -9;
   public final static String NVARCHAR_NAME = "NVARCHAR";

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>LONGNVARCHAR</code>.
    *
    * @since 1.6
    */
   public static final int LONGNVARCHAR = -16;
   public final static String LONGNVARCHAR_NAME = "LONGNVARCHAR";

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>NCLOB</code>.
    *
    * @since 1.6
    */
   public static final int NCLOB = 2011;
   public final static String NCLOB_NAME = "NCLOB";

   /**
    * The constant in the Java programming language, sometimes referred to as a
    * type code, that identifies the generic SQL type <code>XML</code>.
    *
    * @since 1.6
    */
   public static final int SQLXML = 2009;
   public final static String SQLXML_NAME = "SQLXML";

   /**
    * Oracle Timestamp with Local Timezone
    */
   public static final int TIMESTAMPLTZ = -102;
   public static final String TIMESTAMPLTZ_NAME = "TIMESTAMPLTZ";

   private final static Map<Integer, String> typeToName;
   static {
      typeToName = new HashMap<Integer, String>();

      typeToName.put(ARRAY, ARRAY_NAME);
      typeToName.put(BIGINT, BIGINT_NAME);
      typeToName.put(BINARY, BINARY_NAME);
      typeToName.put(BIT, BIT_NAME);
      typeToName.put(BLOB, BLOB_NAME);
      typeToName.put(BOOLEAN, BOOLEAN_NAME);
      typeToName.put(CHAR, CHAR_NAME);
      typeToName.put(CLOB, CLOB_NAME);
      typeToName.put(DATALINK, DATALINK_NAME);
      typeToName.put(DATE, DATE_NAME);
      typeToName.put(DECIMAL, DECIMAL_NAME);
      typeToName.put(DOUBLE, DOUBLE_NAME);
      typeToName.put(FLOAT, FLOAT_NAME);
      typeToName.put(INTEGER, INTEGER_NAME);
      typeToName.put(JAVA_OBJECT, JAVA_OBJECT_NAME);
      typeToName.put(LONGVARBINARY, LONGVARBINARY_NAME);
      typeToName.put(LONGVARCHAR, LONGVARCHAR_NAME);
      typeToName.put(NULL, NULL_NAME);
      typeToName.put(NUMERIC, NUMERIC_NAME);
      typeToName.put(OTHER, OTHER_NAME);
      typeToName.put(REAL, REAL_NAME);
      typeToName.put(REF, REF_NAME);
      typeToName.put(SMALLINT, SMALLINT_NAME);
      typeToName.put(STRUCT, STRUCT_NAME);
      typeToName.put(TIME, TIME_NAME);
      typeToName.put(TIMESTAMP, TIMESTAMP_NAME);
      typeToName.put(TIMESTAMPLTZ, TIMESTAMPLTZ_NAME);
      typeToName.put(TINYINT, TINYINT_NAME);
      typeToName.put(VARBINARY, VARBINARY_NAME);
      typeToName.put(VARCHAR, VARCHAR_NAME);
      typeToName.put(ROWID, ROWID_NAME);
      typeToName.put(NCHAR, NCHAR_NAME);
      typeToName.put(NVARCHAR, NVARCHAR_NAME);
      typeToName.put(LONGNVARCHAR, LONGNVARCHAR_NAME);
      typeToName.put(NCLOB, NCLOB_NAME);
      typeToName.put(SQLXML, SQLXML_NAME);
   }

   public static String getName(Integer type) {
      if (null == type)
         return "";
      return typeToName.get(type);
   }

   public static boolean isString(Integer type) {
      if (null == type)
         return false;

      switch (type) {
      case CHAR:
      case CLOB:
      case LONGVARCHAR:
      case VARCHAR:
      case NCHAR:
      case NCLOB:
      case NVARCHAR:
      case LONGNVARCHAR:
         return true;
      }

      return false;
   }

   public static boolean isNumerical(Integer type) {
      if (null == type)
         return false;

      switch (type) {
      case BIGINT:
      case BIT:
      case DECIMAL:
      case DOUBLE:
      case FLOAT:
      case INTEGER:
      case NUMERIC:
      case REAL:
      case SMALLINT:
      case TINYINT:
         return true;
      }
      return false;
   }

   public static boolean isInteger(Integer type) {
      if (null == type)
         return false;
      switch (type) {
      case BIGINT:
      case INTEGER:
      case SMALLINT:
      case TINYINT:
         return true;
      }
      return false;
   }

   public static boolean isDateLikeType(Integer type) {
      if (null == type)
         return false;
      switch (type) {
      case DATE:
      case TIME:
      case TIMESTAMP:
      case TIMESTAMPLTZ:
         return true;
      }
      return false;
   }

   public static boolean isTimeStamp(Integer type) {
      if (null == type)
         return false;
      switch (type) {
      case TIMESTAMP:
      case TIMESTAMPLTZ:
         return true;
      }
      return false;
   }

   public static boolean isTime(Integer type) {
      if (null == type)
         return false;
      switch (type) {
      case TIME:
         return true;
      }
      return false;
   }

   public static boolean isDate(Integer type) {
      if (null == type)
         return false;
      switch (type) {
      case DATE:
         return true;
      }
      return false;
   }

   public boolean isFloatingPoint(Integer type) {
      if (null == type)
         return false;

      switch (type) {
      case DOUBLE:
      case FLOAT:
         return true;
      }
      return false;
   }

   public boolean isLob(Integer type) {
      if (null == type)
         return false;

      switch (type) {
      case BLOB:
      case CLOB:
      case NCLOB:
      case LONGVARBINARY:
      case LONGVARCHAR:
      case LONGNVARCHAR:
         return true;
      }
      return false;
   }

   public boolean isBinary(Integer type) {
      if (null == type)
         return false;

      switch (type) {
      case BINARY:
      case LONGVARBINARY:
         return true;
      }
      return false;
   }
}
