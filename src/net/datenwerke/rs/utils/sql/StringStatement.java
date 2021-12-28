package net.datenwerke.rs.utils.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import net.datenwerke.rs.utils.misc.StringEscapeUtils;

public class StringStatement implements PreparedStatement {

   public final char PAR_PREFIX = '!';
   public final char PAR_POSTFIX = '!';
   private Set<Integer> blockedChars;

   private String query;
   private String originalQuery;

   public StringStatement(String query) {
      this.query = query;
      this.originalQuery = query;
      this.blockedChars = new HashSet<Integer>();

      prepareQuery();
   }

   protected void prepareQuery() {
      boolean singleQuoted = false;
      boolean doubleQuoted = false;
      boolean escaped = false;
      char sQuote = '\'';
      char dQuote = '"';
      char backslash = '\\';
      char par = '?';

      int parCounter = 1;
      for (int i = 0; i < query.length(); i++) {
         char c = query.charAt(i);
         if (c == backslash)
            escaped = !escaped;
         else if (!escaped && c == sQuote)
            singleQuoted = !singleQuoted;
         else if (!escaped && c == dQuote)
            doubleQuoted = !doubleQuoted;
         else if (c == PAR_PREFIX || c == PAR_POSTFIX)
            blockedChars.add(i);
         else if (!escaped && !singleQuoted && !doubleQuoted && c == par) {
            String prefix = query.substring(0, i);
            String postfix = i + 1 < query.length() ? query.substring(i + 1, query.length()) : "";

            query = prefix + PAR_PREFIX + parCounter + PAR_POSTFIX + postfix;

            i += ("" + PAR_PREFIX + parCounter + PAR_POSTFIX).length();
            parCounter++;
         }
      }
   }

   public String getQuery() {
      return query;
   }

   public String getOriginalQuery() {
      return originalQuery;
   }

   @Override
   public ResultSet executeQuery(String sql) throws SQLException {
      return null;
   }

   @Override
   public int executeUpdate(String sql) throws SQLException {
      return 0;
   }

   @Override
   public void close() throws SQLException {
   }

   @Override
   public int getMaxFieldSize() throws SQLException {
      return 0;
   }

   @Override
   public void setMaxFieldSize(int max) throws SQLException {
   }

   @Override
   public int getMaxRows() throws SQLException {
      return 0;
   }

   @Override
   public void setMaxRows(int max) throws SQLException {
   }

   @Override
   public void setEscapeProcessing(boolean enable) throws SQLException {
   }

   @Override
   public int getQueryTimeout() throws SQLException {
      return 0;
   }

   @Override
   public void setQueryTimeout(int seconds) throws SQLException {

   }

   @Override
   public void cancel() throws SQLException {

   }

   @Override
   public SQLWarning getWarnings() throws SQLException {
      return null;
   }

   @Override
   public void clearWarnings() throws SQLException {

   }

   @Override
   public void setCursorName(String name) throws SQLException {
   }

   @Override
   public boolean execute(String sql) throws SQLException {
      return false;
   }

   @Override
   public ResultSet getResultSet() throws SQLException {
      return null;
   }

   @Override
   public int getUpdateCount() throws SQLException {
      return 0;
   }

   @Override
   public boolean getMoreResults() throws SQLException {
      return false;
   }

   @Override
   public void setFetchDirection(int direction) throws SQLException {

   }

   @Override
   public int getFetchDirection() throws SQLException {
      return 0;
   }

   @Override
   public void setFetchSize(int rows) throws SQLException {
   }

   @Override
   public int getFetchSize() throws SQLException {
      return 0;
   }

   @Override
   public int getResultSetConcurrency() throws SQLException {
      return 0;
   }

   @Override
   public int getResultSetType() throws SQLException {
      return 0;
   }

   @Override
   public void addBatch(String sql) throws SQLException {
   }

   @Override
   public void clearBatch() throws SQLException {
   }

   @Override
   public int[] executeBatch() throws SQLException {
      return null;
   }

   @Override
   public Connection getConnection() throws SQLException {
      return null;
   }

   @Override
   public boolean getMoreResults(int current) throws SQLException {
      return false;
   }

   @Override
   public ResultSet getGeneratedKeys() throws SQLException {
      return null;
   }

   @Override
   public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
      return 0;
   }

   @Override
   public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
      return 0;
   }

   @Override
   public int executeUpdate(String sql, String[] columnNames) throws SQLException {
      return 0;
   }

   @Override
   public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
      return false;
   }

   @Override
   public boolean execute(String sql, int[] columnIndexes) throws SQLException {
      return false;
   }

   @Override
   public boolean execute(String sql, String[] columnNames) throws SQLException {
      return false;
   }

   @Override
   public int getResultSetHoldability() throws SQLException {
      return 0;
   }

   @Override
   public boolean isClosed() throws SQLException {
      return false;
   }

   @Override
   public void setPoolable(boolean poolable) throws SQLException {
   }

   @Override
   public boolean isPoolable() throws SQLException {
      return false;
   }

   @Override
   public void closeOnCompletion() throws SQLException {
   }

   @Override
   public boolean isCloseOnCompletion() throws SQLException {
      return false;
   }

   @Override
   public <T> T unwrap(Class<T> iface) throws SQLException {
      return null;
   }

   @Override
   public boolean isWrapperFor(Class<?> iface) throws SQLException {
      return false;
   }

   @Override
   public ResultSet executeQuery() throws SQLException {
      return null;
   }

   @Override
   public int executeUpdate() throws SQLException {
      return 0;
   }

   protected int getParameterPos(int parameterIndex) {
      String searchToken = "" + PAR_PREFIX + parameterIndex + PAR_POSTFIX;
      int start = 0;
      int pos = query.indexOf(searchToken, start);
      while (pos != -1) {
         /* check if escaped */
         if (!blockedChars.contains(pos)) {
            return pos;
         }

         pos = query.indexOf(searchToken, pos + 1);
      }

      throw new IllegalStateException("Could not find parameter: " + parameterIndex);
   }

   protected void replace(int parameterIndex, String replacement) {
      int pos = getParameterPos(parameterIndex);
      int length = ("" + PAR_PREFIX + parameterIndex + PAR_POSTFIX).length();

      String prefix = query.substring(0, pos);
      String postfix = pos + 1 < query.length() ? query.substring(pos + length, query.length()) : "";

      query = prefix + replacement + postfix;
   }

   @Override
   public void setNull(int parameterIndex, int sqlType) throws SQLException {
      replace(parameterIndex, "NULL");
   }

   @Override
   public void setBoolean(int parameterIndex, boolean x) throws SQLException {
      replace(parameterIndex, x ? "TRUE" : "FALSE");
   }

   @Override
   public void setByte(int parameterIndex, byte x) throws SQLException {
   }

   @Override
   public void setShort(int parameterIndex, short x) throws SQLException {
      replace(parameterIndex, "" + x);
   }

   @Override
   public void setInt(int parameterIndex, int x) throws SQLException {
      replace(parameterIndex, "" + x);
   }

   @Override
   public void setLong(int parameterIndex, long x) throws SQLException {
      replace(parameterIndex, "" + x);
   }

   @Override
   public void setFloat(int parameterIndex, float x) throws SQLException {
      replace(parameterIndex, "" + x);
   }

   @Override
   public void setDouble(int parameterIndex, double x) throws SQLException {
      replace(parameterIndex, "" + x);
   }

   @Override
   public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
      replace(parameterIndex, "" + x.toString());
   }

   @Override
   public void setString(int parameterIndex, String x) throws SQLException {
      replace(parameterIndex, "'" + StringEscapeUtils.escapeSql(x) + "'");
   }

   @Override
   public void setBytes(int parameterIndex, byte[] x) throws SQLException {
   }

   @Override
   public void setDate(int parameterIndex, Date x) throws SQLException {
      replace(parameterIndex, "'" + x.toString() + "'");
   }

   @Override
   public void setTime(int parameterIndex, Time x) throws SQLException {
      replace(parameterIndex, "'" + x.toString() + "'");
   }

   @Override
   public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
      replace(parameterIndex, "'" + x.toString() + "'");
   }

   @Override
   public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
   }

   @Override
   public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void clearParameters() throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setObject(int parameterIndex, Object x) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public boolean execute() throws SQLException {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void addBatch() throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setRef(int parameterIndex, Ref x) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setBlob(int parameterIndex, Blob x) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setClob(int parameterIndex, Clob x) throws SQLException {
      replace(parameterIndex, "'" + StringEscapeUtils.escapeSql(x.toString()) + "'");
   }

   @Override
   public void setArray(int parameterIndex, Array x) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public ResultSetMetaData getMetaData() throws SQLException {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setURL(int parameterIndex, URL x) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public ParameterMetaData getParameterMetaData() throws SQLException {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void setRowId(int parameterIndex, RowId x) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setNString(int parameterIndex, String value) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setNClob(int parameterIndex, NClob value) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setClob(int parameterIndex, Reader reader) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public void setNClob(int parameterIndex, Reader reader) throws SQLException {
      // TODO Auto-generated method stub

   }

   @Override
   public String toString() {
      return query;
   }

}
