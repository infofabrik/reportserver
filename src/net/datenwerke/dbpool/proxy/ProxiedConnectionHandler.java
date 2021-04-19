package net.datenwerke.dbpool.proxy;

import java.lang.reflect.Method;
import java.sql.Connection;

import com.google.common.reflect.AbstractInvocationHandler;

import net.datenwerke.dbpool.config.ConnectionPoolConfig;

public class ProxiedConnectionHandler extends AbstractInvocationHandler {

	private final Connection connection;
	private final ConnectionPoolConfig config;
	
	public ProxiedConnectionHandler(Connection connection, ConnectionPoolConfig config) {
		super();
		this.connection = connection;
		this.config = config;
	}

	@Override
	protected Object handleInvocation(Object proxy, Method method, Object[] args) throws Throwable {
		if("__getReportServerConnectionPoolConfig".equals(method.getName()))
			return config;
		return method.invoke(connection, args);
	}


}
