package net.sf.jxls.report;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractCollection;
import java.util.Iterator;

import org.apache.commons.beanutils.ResultSetDynaClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Allows JDBC ResultSet to be used with XLSTransformer
 * Note! This is very basic implementation just to give an example of how this can be done.
 * You may want to create your own implementation to use advanced features of some specific jdbc driver for example.
 * @author Leonid Vysochyn
 */
public class ResultSetCollection extends AbstractCollection {
    protected static final Log log = LogFactory.getLog(ResultSetCollection.class);

    ResultSet resultSet;
    ResultSetDynaClass rsDynaClass;
    private boolean lowerCase = true;

    private int numberOfRows;

    /**
     * Construct a new ResultSetCollection for the specified ResultSet.
     * The property names corresponding to column names in the result set will be lower cased.
     *
     * @param resultSet - The result set to be wrapped
     * @throws SQLException         - if the metadata for this result set cannot be introspected
     * @throws NullPointerException - if resultSet  is null
     */
    public ResultSetCollection(ResultSet resultSet) throws SQLException, NullPointerException {
        this.resultSet = resultSet;
        rsDynaClass = new ResultSetDynaClass(resultSet);
        try {
            setNumberOfRows();
        } catch (SQLException e) {
            log.error("Can't get number of ResultSet records. Try to pass it to a corresponding constructor", e);
            throw e;
        }
    }

    /**
     * Construct a new ResultSetCollection for the specified ResultSet.
     *
     * @param resultSet - The result set to be wrapped
     * @param lowerCase - Should property names be lower cased?
     * @throws SQLException         - if the metadata for this result set cannot be introspected
     * @throws NullPointerException - if resultSet  is null
     */
    public ResultSetCollection(ResultSet resultSet, boolean lowerCase) throws SQLException, NullPointerException {
        this.resultSet = resultSet;
        this.lowerCase = lowerCase;
        rsDynaClass = new ResultSetDynaClass(resultSet, lowerCase);
        try {
            setNumberOfRows();
        } catch (SQLException e) {
            log.error("Can't get number of ResultSet records. Try to pass it to a corresponding constructor");
            throw e;
        }
    }

    /**
     * Construct a new ResultSetCollection for the specified ResultSet.
     * The property names corresponding to column names in the result set will be lower cased.
     *
     * @param resultSet    - The result set to be wrapped
     * @param numberOfRows - The number of rows in result set
     * @throws SQLException         - if the metadata for this result set cannot be introspected
     * @throws NullPointerException - if resultSet  is null
     */
    public ResultSetCollection(ResultSet resultSet, int numberOfRows) throws SQLException, NullPointerException {
        this.resultSet = resultSet;
        this.numberOfRows = numberOfRows;
        rsDynaClass = new ResultSetDynaClass(resultSet, lowerCase);
    }

    /**
     * Construct a new ResultSetCollection for the specified ResultSet.
     *
     * @param resultSet    - The result set to be wrapped
     * @param numberOfRows - The number of rows in result set
     * @param lowerCase    - Should property names be lower cased?
     * @throws SQLException         - if the metadata for this result set cannot be introspected
     * @throws NullPointerException - if resultSet  is null
     */
    public ResultSetCollection(ResultSet resultSet, int numberOfRows, boolean lowerCase) throws SQLException, NullPointerException {
        this.lowerCase = lowerCase;
        this.resultSet = resultSet;
        this.numberOfRows = numberOfRows;
        rsDynaClass = new ResultSetDynaClass(resultSet, lowerCase);
    }

    protected void setNumberOfRows() throws SQLException {
        if (resultSet != null) {
            resultSet.last();
            numberOfRows = resultSet.getRow();
            resultSet.beforeFirst();
        }
    }

    public int size() {
        return numberOfRows;
    }

    public Iterator iterator() {
        return rsDynaClass.iterator();
    }

    public boolean isLowerCase() {
        return lowerCase;
    }

}
