package net.datenwerke.dbpool;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.inject.Inject;
import javax.sql.DataSource;

import com.google.inject.Provider;

import net.datenwerke.dbpool.annotations.PoolC3P0;
import net.datenwerke.dbpool.annotations.UseConnectionPool;
import net.datenwerke.dbpool.config.ConnectionConfig;
import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.dbpool.config.predefined.StandardConnectionConfig;
import net.datenwerke.dbpool.exceptions.DriverNotFoundException;

/**
 * A pool service that can either use {@link DbC3p0PoolServiceImpl} as backend.
 * 
 *
 */
public class MetaPoolService implements DbPoolService {

	private final DbPoolService c3p0;
	private final Provider<Boolean> useConnectionPool;
	private final JdbcService jdbcService;
	
	@Inject
	public MetaPoolService(
		@PoolC3P0 DbPoolService c3p0,
		@UseConnectionPool Provider<Boolean> useConnectionPool,
		JdbcService jdbcService
		) {
		this.c3p0 = c3p0;
		this.useConnectionPool = useConnectionPool;
		this.jdbcService = jdbcService;
	}
	
	@Override
	public Future getConnection(ConnectionPoolConfig config)
			throws SQLException {
		if(! useConnectionPool.get() || ! config.isPoolable())
			return getConnectionDirectly(config, new StandardConnectionConfig());
		
		return c3p0.getConnection(config);
	}
	
	public DbPoolService getActiveService() {
		return c3p0;
	}
	
	public DbPoolService getC3P0(){
		return c3p0;
	}
	
	@Override
	public boolean isActive() {
		return c3p0.isActive();
	}

	@Override
	public Future getConnection(ConnectionPoolConfig poolConfig,
			ConnectionConfig connConfig) throws SQLException {
		if(! useConnectionPool.get() || ! poolConfig.isPoolable())
			return getConnectionDirectly(poolConfig, connConfig);
		
		return c3p0.getConnection(poolConfig, connConfig);
	}
	
	protected Future<Connection> getConnectionDirectly(final ConnectionPoolConfig config, final ConnectionConfig connConfig) throws SQLException {
		try {
			Class.forName(config.getDriver());
		} catch (ClassNotFoundException e) {
			throw new DriverNotFoundException(config.getDriver(), e);
		}
		
		return new Future<Connection>(){

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				return false;
			}

			@Override
			public Connection get() throws InterruptedException,
					ExecutionException {
				/* open connection */
				Connection conn = null;
				
				/* set timeout */
				DriverManager.setLoginTimeout(10);
				
				/* create connection */
				try {
					conn = DriverManager.getConnection(
							 jdbcService.adaptJdbcUrl(config.getJdbcUrl()),
					         config.getUsername(),
					         config.getPassword() );
					
					configureConnection(conn, connConfig);
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
				
				return conn;
			}

			@Override
			public Connection get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException,
					TimeoutException {
				return get();
			}

			@Override
			public boolean isCancelled() {
				return false;
			}

			@Override
			public boolean isDone() {
				return true;
			}
			
		};
	}

	protected void configureConnection(Connection conn, ConnectionConfig connConfig) throws SQLException {
		if(connConfig.isReadOnly())
			conn.setReadOnly(true);
		
		DatabaseMetaData metaData = conn.getMetaData();
		
		if(null != connConfig.getIsolationLevel() && metaData.supportsTransactionIsolationLevel(connConfig.getIsolationLevel()))
			conn.setTransactionIsolation(connConfig.getIsolationLevel());
	}

	@Override
	public Map getPoolMap() {
		return c3p0.getPoolMap();
	}

	@Override
	public void shutdownPool(ConnectionPoolConfig poolConfig) {
		c3p0.shutdownPool(poolConfig);
	}

	@Override
	public void shutdownAll() {
		c3p0.shutdownAll();
	}
	
	@Override
	public DataSource getDataSource(ConnectionPoolConfig config) {
		return c3p0.getDataSource(config);
	}
	
	@Override
	public DataSource getDataSource(ConnectionPoolConfig poolConfig,
			ConnectionConfig connConfig) {
		return c3p0.getDataSource(poolConfig, connConfig);
	}

}
