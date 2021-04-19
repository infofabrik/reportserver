package net.datenwerke.rs.core.service.internaldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.inject.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.utils.juel.SimpleJuel;

public class TempTableHelper {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	@Inject
	private static Provider<SimpleJuel> simpleJuel;

	@Inject
	private static DbPoolService dbPoolService;

	private final static String PRAEFIX = "RS_TMPTBL_";
	public final static String PARAMETER_TMP_TABLE = "_RS_TMP_TABLENAME";
	public final static String PARAMETER_QUERY = "_RS_QUERY";

	public static interface CleanupCallback {
		public void doCleanup(TempTableHelper helper, String table, int uptoRev);
	}

	private int id;
	private int rev = 0;

	private AtomicInteger write_rev = new AtomicInteger();
	private ThreadLocal<Integer> tl_write_rev = new ThreadLocal<>();

	private int aliasnum = 0;
	private HashMap<String, Integer> aliasMap = new HashMap<String,Integer>();
	private HashMap<String, ConnectionPoolConfig> configMap = new HashMap<String, ConnectionPoolConfig>();
	private HashMap<String, CleanupCallback> cleanupCallbacks = new HashMap<String, TempTableHelper.CleanupCallback>();


	public TempTableHelper(int id) {
		this.id = id;
	}

	/**
	 * Returns the table for an existing table alias. To generate a fresh table on a new alias use {@link #getTableName(ConnectionPoolConfig, String)}
	 * @param tableAlias
	 */
	public String getTableName(String tableAlias) {
		Integer writeRev = tl_write_rev.get();
		if(null != writeRev)
			return getTableName(tableAlias, writeRev);
		
		return getTableName(tableAlias, rev);
	}
	
	public String getTableName(String tableAlias, int forRev) {
		Integer alid = aliasMap.get(tableAlias);
		return PRAEFIX + id + "_" + alid + "_" + forRev;
	}

	public synchronized String getTableName(ConnectionPoolConfig cpc, String tableAlias){
		if(!aliasMap.containsKey(tableAlias)){
			aliasnum++;
			aliasMap.put(tableAlias, aliasnum);
			configMap.put(tableAlias, cpc);
		}

		return getTableName(tableAlias);
	}

	public Collection<String> getTableAliases(){
		return new HashSet<String>(aliasMap.keySet());
	}

	public void addCustomCleanup(String tableAlias, CleanupCallback callback){
		cleanupCallbacks.put(tableAlias, callback);
	}

	@Deprecated
	public void cleanup(){
		cleanup(rev-1);
	}
	
	public synchronized void cleanup(int uptoRev){
		for(String tableAlias : aliasMap.keySet()){
			if(cleanupCallbacks.containsKey(tableAlias)){
				cleanupCallbacks.get(tableAlias).doCleanup(this, getTableName(configMap.get(tableAlias), tableAlias), uptoRev);
			}else{
				try {
					cleanup(tableAlias, uptoRev);
				} catch (SQLException e) {
				} catch (InterruptedException e) {
				} catch (ExecutionException e) {
				}
			}
		}

	}

	private void cleanup(String alias, int uptoRev) throws SQLException, InterruptedException, ExecutionException {
		if(aliasMap.containsKey(alias)){
			ConnectionPoolConfig cpc = configMap.get(alias);
			try(Connection connection = (Connection) dbPoolService.getConnection(cpc).get()){
				for(String tablename : getMatchingTablesForCleanup(connection,uptoRev)){
					String qry = "DROP TABLE " + tablename ;
					logger.info("drop table " + tablename);
				
					try{
						PreparedStatement stmt = connection.prepareStatement(qry);
						stmt.executeUpdate();
					} catch(Exception e){
						logger.warn("Could not drop table " + tablename);
					}
				}
			} catch (SQLException e) {
				logger.warn("Could not obtain temp tables for cleanup");
			}
		}
	}

	private List<String> getMatchingTablesForCleanup(Connection connection, int uptoRev) throws SQLException {
		String tablePattern = "(?i:rs_tmptbl_\\d+_\\d+_\\d+)";
		List<String> tables = new ArrayList<>();

		ResultSet res = connection.getMetaData().getTables(connection.getCatalog(), null, null, Arrays.asList("TABLE").toArray(new String[0]));
		while(res.next()){
			String tablename = res.getString("TABLE_NAME");
			if(tablename.matches(tablePattern)){
				int tableRev = Integer.parseInt(tablename.substring(tablename.lastIndexOf("_")+1));
				
				if(tableRev <= uptoRev)
					tables.add(tablename);
			}
		}
		
		return tables;
	}

	public synchronized String processQuery(String query) {
		SimpleJuel queryParser = simpleJuel.get();

		for(String alias : aliasMap.keySet())
			queryParser.addReplacement(alias, getTableName(alias));

		return queryParser.parse(query);
	}
	
	public void setParameterValues(Report report, ParameterSet parameters, TempTableResult tempTableResult) {
		for(String alias : tempTableResult.getTableHelper().getTableAliases()) {
			String tableName = tempTableResult.getTableHelper().getTableName(alias);
			parameters.addVariable(PARAMETER_TMP_TABLE, tableName);
		}
		
		String query = tempTableResult.getFinalQuery();
		parameters.addVariable(PARAMETER_QUERY, query);
	}
	
	public String processQuery(String query, int forRev) {
		SimpleJuel queryParser = simpleJuel.get();

		for(String alias : aliasMap.keySet())
			queryParser.addReplacement(alias, getTableName(alias, forRev));

		return queryParser.parse(query);
	}

	synchronized void incrementWriteRevision() {
		int wr = write_rev.incrementAndGet();
		tl_write_rev.set(wr);
	}

	public synchronized int writeOperationCompleted() {
		Integer wr = tl_write_rev.get();
		if(null == wr)
			throw new IllegalStateException("no write revision set");
		if(wr > rev)
			rev = wr;
		
		tl_write_rev.set(null);
		
		return wr;
	}

	public int getWriteRev() {
		Integer wr = tl_write_rev.get();
		if(null == wr)
			return rev;
		return wr;
	}

	


}
