package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jxls.builder.xls.XlsCommentAreaBuilder;

import net.datenwerke.rs.base.service.datasources.table.impl.utils.JasperStyleParameterParser;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;
import net.sf.jasperreports.engine.JRException;

public class JxlsSqlExecutor {
	protected static final Log log = LogFactory.getLog(JxlsSqlExecutor.class);
	private Connection connection;
	private ParameterSet parameters;

	public JxlsSqlExecutor(Connection connection, ParameterSet parameters) {
		this.connection = connection;
		this.parameters = parameters;
	}

	public JxlsSqlExecutor(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	public List query(String sql) throws SQLException {
		return execSqlWithParameters(sql, true);
	}

	public List exec(String sql) throws SQLException {
		return execSqlWithParameters(sql, true);
	}
	
	public List execSqlWithParameters(String sql, boolean lowercase) throws SQLException {
		Map<String, ParameterValue> pMap = null;
		if(null != parameters)
			pMap = parameters.getParameterMap();

		/* create real stmt */
		sql = sql.replace(XlsCommentAreaBuilder.LINE_SEPARATOR, System.lineSeparator());
		JasperStyleParameterParser parser = new JasperStyleParameterParser(connection, sql, pMap, parameters);

		try {
			ResultSet rs = null;
			PreparedStatement stmt = parser.getStatement();
			try{
				rs = stmt.executeQuery();

				RowSetDynaClass rsdc = new RowSetDynaClass(rs, lowercase, true);
				return rsdc.getRows();
			} finally {
				try{
					if(null != stmt)
						stmt.close();
				} finally {
					if(null != rs)
						rs.close();
				}
			}
		} catch (JRException e) {
			throw new RuntimeException(e);
		}
	}

	@Deprecated
	public List exec(String sql, Object... params) throws SQLException {
		return execSql(sql, true, params);
	}
	
	public List queryCS(String sql) throws SQLException {
		return execSqlWithParameters(sql, false); 
	}

	public List execCS(String sql) throws SQLException {
		return execSqlWithParameters(sql, false); 
	}
	
	@Deprecated
	public List execCS(String sql, Object... params) throws SQLException {
		return execSql(sql, false, params);
	}

	protected List execSql(String sql, boolean lowercase, Object... params) throws SQLException {
		sql = sql.replaceAll("&apos;", "'");

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(sql);
			if(null != params && params.length > 0){
				int index = 1;
				for(Object p : params){
					if(p instanceof String)
						stmt.setString(index, p.toString());
					else if(p instanceof Timestamp)
						stmt.setTimestamp(index, (Timestamp) p);
					else if(p instanceof Time)
						stmt.setTime(index, (Time) p);
					else if(p instanceof Date)
						stmt.setDate(index, (Date) p);
					else if(p instanceof java.util.Date)
						stmt.setDate(index, new Date(((java.util.Date) p).getTime()));
					else if(p instanceof BigDecimal)
						stmt.setBigDecimal(index, (BigDecimal) p);
					else if(p instanceof Double)
						stmt.setDouble(index, (Double) p);
					else if(p instanceof Float)
						stmt.setFloat(index, (Float) p);
					else if(p instanceof Long)
						stmt.setLong(index, (Long) p);
					else if(p instanceof Integer)
						stmt.setInt(index, (Integer) p);
					else if(p instanceof Short)
						stmt.setShort(index, (Short) p);
					else if(p instanceof Boolean)
						stmt.setBoolean(index, (boolean) p);
					else if(p instanceof Byte)
						stmt.setByte(index, (byte) p);
					else if(p instanceof byte[])
						stmt.setBytes(index, (byte[]) p);
					else
						throw new IllegalArgumentException("Unsupported parameter of type " + p.getClass());

					index++;
				}
			}

			rs = stmt.executeQuery();

			RowSetDynaClass rsdc = new RowSetDynaClass(rs, lowercase, true);
			return rsdc.getRows();
		} finally {
			try{
				if(null != stmt)
					stmt.close();
			} finally {
				if(null != rs)
					rs.close();
			}
		}
	}


}