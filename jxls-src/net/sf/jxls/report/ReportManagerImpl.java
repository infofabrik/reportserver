package net.sf.jxls.report;

import org.apache.commons.beanutils.RowSetDynaClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

/**
 * @author Leonid Vysochyn
 */
public class ReportManagerImpl implements ReportManager {
    protected static final Log log = LogFactory.getLog(ReportManagerImpl.class);
    Connection connection;

    public ReportManagerImpl(Connection connection, Map beans) {
        this.connection = connection;
    }

    public ReportManagerImpl(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public List exec(String sql) throws SQLException {
        Statement stmt = connection.createStatement();
        sql = sql.replaceAll("&apos;", "'");
        ResultSet rs = stmt.executeQuery(sql);
        // second parameter to RowSetDynaClass constructor indicates that properties should not be lowercased
        RowSetDynaClass rsdc = new RowSetDynaClass(rs, true);
        stmt.close();
        rs.close();
        return rsdc.getRows();
    }


}
