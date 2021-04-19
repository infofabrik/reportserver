/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2007-2011 Pentaho
// All Rights Reserved.
*/
package mondrian3.olap4j;

import mondrian3.rolap.RolapConnection;

import org.olap4j.OlapException;

import java.io.InputStream;
import java.io.Reader;
import java.sql.*;
import java.util.*;
import java.util.concurrent.Executor;

/**
 * Implementation of {@link mondrian3.olap4j.Factory} for JDBC 3.0.
 *
 * @author jhyde
 * @since Jun 14, 2007
 */
class FactoryJdbc3Impl implements Factory {
    private CatalogFinder catalogFinder;

    public Connection newConnection(
        MondrianOlap4jDriver driver,
        String url,
        Properties info)
        throws SQLException
    {
        return new MondrianOlap4jConnectionJdbc3(driver, url, info);
    }

    public EmptyResultSet newEmptyResultSet(
        MondrianOlap4jConnection olap4jConnection)
    {
        List<String> headerList = Collections.emptyList();
        List<List<Object>> rowList = Collections.emptyList();
        return new EmptyResultSetJdbc3(
            olap4jConnection, headerList, rowList);
    }

    public ResultSet newFixedResultSet(
        MondrianOlap4jConnection olap4jConnection,
        List<String> headerList,
        List<List<Object>> rowList)
    {
        return new EmptyResultSetJdbc3(
            olap4jConnection, headerList, rowList);
    }

    public MondrianOlap4jCellSet newCellSet(
        MondrianOlap4jStatement olap4jStatement)
    {
        return new MondrianOlap4jCellSetJdbc3(olap4jStatement);
    }

    public MondrianOlap4jStatement newStatement(
        MondrianOlap4jConnection olap4jConnection)
    {
        return new MondrianOlap4jStatementJdbc3(olap4jConnection);
    }

    public MondrianOlap4jPreparedStatement newPreparedStatement(
        String mdx,
        MondrianOlap4jConnection olap4jConnection)
        throws OlapException
    {
        return new MondrianOlap4jPreparedStatementJdbc3(olap4jConnection, mdx);
    }

    public MondrianOlap4jDatabaseMetaData newDatabaseMetaData(
        MondrianOlap4jConnection olap4jConnection,
        RolapConnection mondrianConnection)
    {
        return new MondrianOlap4jDatabaseMetaDataJdbc3(
            olap4jConnection, mondrianConnection);
    }

    // Inner classes

    private static class MondrianOlap4jStatementJdbc3
        extends MondrianOlap4jStatement
    {
        public MondrianOlap4jStatementJdbc3(
            MondrianOlap4jConnection olap4jConnection)
        {
            super(olap4jConnection);
        }

		@Override
		public void closeOnCompletion() throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isCloseOnCompletion() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}
    }

    private static class MondrianOlap4jPreparedStatementJdbc3
        extends MondrianOlap4jPreparedStatement
    {
        public MondrianOlap4jPreparedStatementJdbc3(
            MondrianOlap4jConnection olap4jConnection,
            String mdx)
            throws OlapException
        {
            super(olap4jConnection, mdx);
        }

		@Override
		public void setAsciiStream(int arg0, InputStream arg1) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setAsciiStream(int arg0, InputStream arg1, long arg2) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setBinaryStream(int arg0, InputStream arg1) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setBinaryStream(int arg0, InputStream arg1, long arg2) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setBlob(int arg0, InputStream arg1) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setBlob(int arg0, InputStream arg1, long arg2) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setCharacterStream(int arg0, Reader arg1) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setClob(int arg0, Reader arg1) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setClob(int arg0, Reader arg1, long arg2) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setNCharacterStream(int arg0, Reader arg1) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setNCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setNClob(int arg0, NClob arg1) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setNClob(int arg0, Reader arg1) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setNClob(int arg0, Reader arg1, long arg2) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setNString(int arg0, String arg1) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setRowId(int parameterIndex, RowId x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void closeOnCompletion() throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isCloseOnCompletion() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}
    }

    private static class MondrianOlap4jCellSetJdbc3
        extends MondrianOlap4jCellSet
    {
        public MondrianOlap4jCellSetJdbc3(
            MondrianOlap4jStatement olap4jStatement)
        {
            super(olap4jStatement);
        }

		@Override
		public int getHoldability() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Reader getNCharacterStream(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Reader getNCharacterStream(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public NClob getNClob(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public NClob getNClob(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getNString(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getNString(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RowId getRowId(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RowId getRowId(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SQLXML getSQLXML(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SQLXML getSQLXML(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isClosed() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateClob(int columnIndex, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateClob(String columnLabel, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNClob(int columnIndex, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNClob(String columnLabel, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNString(int columnIndex, String nString) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNString(String columnLabel, String nString) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateRowId(int columnIndex, RowId x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateRowId(String columnLabel, RowId x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
			// TODO Auto-generated method stub
			
		}
    }

    private static class EmptyResultSetJdbc3 extends EmptyResultSet {
        public EmptyResultSetJdbc3(
            MondrianOlap4jConnection olap4jConnection,
            List<String> headerList,
            List<List<Object>> rowList)
        {
            super(olap4jConnection, headerList, rowList);
        }

		@Override
		public int getHoldability() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Reader getNCharacterStream(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Reader getNCharacterStream(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public NClob getNClob(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public NClob getNClob(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getNString(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getNString(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RowId getRowId(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RowId getRowId(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SQLXML getSQLXML(int columnIndex) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SQLXML getSQLXML(String columnLabel) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isClosed() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateClob(int columnIndex, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateClob(String columnLabel, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNClob(int columnIndex, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNClob(String columnLabel, Reader reader) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNString(int columnIndex, String nString) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateNString(String columnLabel, String nString) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateRowId(int columnIndex, RowId x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateRowId(String columnLabel, RowId x) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
			// TODO Auto-generated method stub
			
		}
    }

    private class MondrianOlap4jConnectionJdbc3
        extends MondrianOlap4jConnection
    {
        public MondrianOlap4jConnectionJdbc3(
            MondrianOlap4jDriver driver,
            String url,
            Properties info) throws SQLException
        {
            super(FactoryJdbc3Impl.this, driver, url, info);
        }

		@Override
		public void abort(Executor executor) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Blob createBlob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Clob createClob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public NClob createNClob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getClientInfo(String name) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			// TODO Auto-generated method stub
			
		}
    }

    private static class MondrianOlap4jDatabaseMetaDataJdbc3
        extends MondrianOlap4jDatabaseMetaData
    {
        public MondrianOlap4jDatabaseMetaDataJdbc3(
            MondrianOlap4jConnection olap4jConnection,
            RolapConnection mondrianConnection)
        {
            super(olap4jConnection, mondrianConnection);
        }

		@Override
		public boolean autoCommitFailureClosesAllResultSets() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean generatedKeyAlwaysReturned() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public ResultSet getClientInfoProperties() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ResultSet getFunctionColumns(String catalog, String schemaPattern, String functionNamePattern,
				String columnNamePattern) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ResultSet getFunctions(String catalog, String schemaPattern, String functionNamePattern)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ResultSet getPseudoColumns(String catalog, String schemaPattern, String tableNamePattern,
				String columnNamePattern) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RowIdLifetime getRowIdLifetime() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ResultSet getSchemas(String catalog, String schemaPattern) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean supportsStoredFunctionsUsingCallSyntax() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}
    }
}

// End FactoryJdbc3Impl.java
