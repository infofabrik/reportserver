package net.datenwerke.dbpool;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.AbstractInvocationHandler;
import com.google.inject.Inject;
import com.mchange.v2.beans.BeansUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.cfg.C3P0Config;
import com.mchange.v2.log.MLevel;

import net.datenwerke.dbpool.config.ConnectionConfig;
import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.dbpool.config.predefined.StandardConnectionConfig;
import net.datenwerke.dbpool.exceptions.DriverNotFoundException;
import net.datenwerke.dbpool.hooks.C3p0ConnectionHook;
import net.datenwerke.dbpool.proxy.ConnectionPoolAware;
import net.datenwerke.dbpool.proxy.ProxiedConnectionHandler;
import net.datenwerke.rs.utils.config.ConfigService;
import net.datenwerke.rs.utils.entitydiff.EntityDiffService;

/**
 * Implementation of {@link DbPoolService} using C3P0 as connection pool.
 * 
 *
 */
public class DbC3p0PoolServiceImpl extends DbPoolServiceImpl<ComboPooledDataSource> implements DbPoolService<ComboPooledDataSource> {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private Map<ConnectionPoolConfig, ComboPooledDataSource> poolMap = new ConcurrentHashMap<ConnectionPoolConfig, ComboPooledDataSource>();
	private Map<ConnectionPoolConfig, ConnectionPoolConfig> configMap = new ConcurrentHashMap<ConnectionPoolConfig, ConnectionPoolConfig>();
	
	private Map<ConnectionPoolConfig, ReentrantLock> lockMap = new ConcurrentHashMap<ConnectionPoolConfig, ReentrantLock>();
	
	private final EntityDiffService entityDiffService;
	private final ConfigService configService;
	private final JdbcService jdbcService;
	
	@Inject
	public DbC3p0PoolServiceImpl(
		EntityDiffService entityDiffService,
		ConfigService configService,
		JdbcService jdbcService
		){
	
		/* store objects */
		this.entityDiffService = entityDiffService;
		this.configService = configService;
		this.jdbcService = jdbcService;
	}

	@Override
	public Future<Connection> getConnection(ConnectionPoolConfig poolConfig) throws SQLException {
		return getConnection(poolConfig, new StandardConnectionConfig());
	}
	
	@Override
	public Future<Connection> getConnection(ConnectionPoolConfig poolConfig, ConnectionConfig connConfig) throws SQLException {
		return getConnectionFromPool(poolConfig, connConfig);
	}

	@Override
	public Map<ConnectionPoolConfig, ComboPooledDataSource> getPoolMap(){
		return new HashMap<ConnectionPoolConfig, ComboPooledDataSource>(poolMap);
	}
	
	@Override
	public void shutdownAll(){
		for(ConnectionPoolConfig conf : new ArrayList<ConnectionPoolConfig>(poolMap.keySet()))
			shutdownPool(conf);
	}
	
	@Override
	public void shutdownPool(ConnectionPoolConfig poolConfig){
		ReentrantLock lock;
		synchronized (DbC3p0PoolServiceImpl.class) {
			if(! lockMap.containsKey(poolConfig))
				lockMap.put(poolConfig, new ReentrantLock());

			lock = lockMap.get(poolConfig);
		}
		
		lock.lock();
		
		try{
			if(poolMap.containsKey(poolConfig)){
				poolMap.get(poolConfig).close();
				
				poolMap.remove(poolConfig);
			}
		}finally {
			lock.unlock();
		}
	}
	
	protected Future<Connection> getConnectionFromPool(final ConnectionPoolConfig poolConfig, final ConnectionConfig connConfig) throws SQLException {
		ReentrantLock lock;
		synchronized (DbC3p0PoolServiceImpl.class) {
			if(! lockMap.containsKey(poolConfig))
				lockMap.put(poolConfig, new ReentrantLock());

			lock = lockMap.get(poolConfig);
		}
		
		lock.lock();
		
		try{
			/* close old pool ? */
			if(poolMap.containsKey(poolConfig) && isUpdateConfig(poolConfig)){
				poolMap.get(poolConfig).close();
				poolMap.remove(poolConfig);
			}
	
			/* create new pool ? */
			if(! poolMap.containsKey(poolConfig)){
				ComboPooledDataSource pool = createPool(poolConfig);
				poolMap.put(poolConfig, pool);
				configMap.put(poolConfig, poolConfig);
			}
			
			/* get connection */
			final ComboPooledDataSource pool = poolMap.get(poolConfig);
			
			return new Future<Connection>() {
	
				private boolean cancel;
				private boolean done;
				@Override
				public boolean cancel(boolean mayInterruptIfRunning) {
					cancel = true;
					
					return true;
				}
	
				@Override
				public Connection get() throws InterruptedException,
						ExecutionException {
					if(done || cancel)
						return null;
					
					try {
						final Connection connection = pool.getConnection();
						configureConnection(connection, connConfig);
						
						done = true;
						
						boolean useProxy = true;
						try{
							/* for test purposes .. remove after next release */
							useProxy = configService.getConfigFailsafe(DbPoolModule.CONFIG_FILE).getBoolean("pool.connection.proxy.enable", true);
						} catch(Exception e){
							logger.warn("Could not read pool config", e);
						}
						
						if(! useProxy)
							return connection;
						
						return (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(), 
								new Class[]{Connection.class, Wrapper.class, ConnectionPoolAware.class}, 
								new ProxiedConnectionHandler(connection, poolConfig)); 
					} catch (SQLException e) {
						Throwable lastFailure;
						try {
							lastFailure = pool.getLastAcquisitionFailureDefaultUser();
							if(null != lastFailure)
								throw new RuntimeException(lastFailure);
						} catch (SQLException e1) {}
							
						throw new RuntimeException(e);
					}
				}
	
				@Override
				public Connection get(long timeout, TimeUnit unit)
						throws InterruptedException, ExecutionException,
						TimeoutException {
					return get();
				}
	
				@Override
				public boolean isCancelled() {
					return cancel;
				}
	
				@Override
				public boolean isDone() {
					return done;
				}
				
			};
		} finally {
			lock.unlock();
		}
	}

	private ComboPooledDataSource createPool(ConnectionPoolConfig config) throws SQLException {
		try {
			Class.forName(config.getDriver());
		} catch (ClassNotFoundException e) {
			throw new DriverNotFoundException(config.getDriver(), e);
		}
		
		ComboPooledDataSource pool = new ComboPooledDataSource();
		
		Map defaultUserProps = C3P0Config.getUnspecifiedUserProperties(null);
		Properties cfgMap = config.getProperties();
		for(Object key : cfgMap.keySet()){
			defaultUserProps.put(key, cfgMap.get(key));
		}
		try {
			BeansUtils.overwriteAccessiblePropertiesFromMap( defaultUserProps, 
									 pool, 
									 false, 
									 Arrays.asList( new String[] {"loginTimeout", "properties"}),
									 true,
									 MLevel.FINEST,
									 MLevel.WARNING,
									 false);
		} catch (IntrospectionException e1) {
			e1.printStackTrace();
		}
		
		/* first set jdbc properties */
		if(null != config.getJdbcProperties())
			pool.setProperties(config.getJdbcProperties());
		
		try {
			pool.setDriverClass(config.getDriver());
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
		pool.setUser(config.getUsername());
		pool.setPassword(config.getPassword());
		pool.setJdbcUrl(jdbcService.adaptJdbcUrl(config.getJdbcUrl()));

		pool.setConnectionCustomizerClassName(C3p0ConnectionHook.class.getName());
		
		return pool;
	}

	protected boolean isUpdateConfig(ConnectionPoolConfig config) {
		if(! poolMap.containsKey(config))
			return false;
		
		if(! config.isMightChange())
			return false;
		
		ConnectionPoolConfig oldConfig = configMap.get(config);
		
		Date lastUpdated = config.getLastUpdated();
		Date oldLastUpdated = oldConfig.getLastUpdated();
		if(null == lastUpdated || null == oldLastUpdated)
			return ! entityDiffService.diff(oldConfig, config).isEqual();
		
		boolean update = lastUpdated.after(oldLastUpdated);
		return update;
	}

	protected void configureConnection(Connection conn, ConnectionConfig connConfig) throws SQLException {
		if(null != connConfig && connConfig.isReadOnly())
			conn.setReadOnly(true);
		
		DatabaseMetaData metaData = conn.getMetaData();
		
		if(null != connConfig && null != connConfig.getIsolationLevel() && metaData.supportsTransactionIsolationLevel(connConfig.getIsolationLevel()))
			conn.setTransactionIsolation(connConfig.getIsolationLevel());
	}

	@Override
	public boolean isActive() {
		return ! poolMap.isEmpty();
	}


}
