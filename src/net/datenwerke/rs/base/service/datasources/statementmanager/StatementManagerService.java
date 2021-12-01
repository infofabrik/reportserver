package net.datenwerke.rs.base.service.datasources.statementmanager;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public interface StatementManagerService {

	public static class StatementContainer{
		private final Statement statement;
		private final Connection connection;
		public StatementContainer(Statement statement, Connection connection) {
			super();
			this.statement = statement;
			this.connection = connection;
		}
		public Statement getStatement() {
			return statement;
		}
		public Connection getConnection() {
			return connection;
		}
	}
	
	void registerStatement(String statementId, Statement statement, Connection connection);

	public Collection<StatementContainer> getStatements(String statementId);

	public void unregisterStatement(String statementId);
	
	public void cancelStatement(String statementId);

	Map<String, Map<String, StatementContainer>> getUserStatementMap();

	Map<String, Map<String, StatementContainer>> getUserStatementMap(Long currentUserId);

	Map<Long, Map<String, Map<String, StatementContainer>>> getAllStatements();

	Date getRegisterDate(String id);

}
