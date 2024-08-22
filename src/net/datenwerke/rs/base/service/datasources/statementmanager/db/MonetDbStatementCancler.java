package net.datenwerke.rs.base.service.datasources.statementmanager.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.mchange.v2.c3p0.impl.NewProxyPreparedStatement;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.dbpool.proxy.ConnectionPoolAware;
import net.datenwerke.rs.base.service.datasources.statementmanager.hooks.adapter.StatementCancellationHookAdapter;

public class MonetDbStatementCancler extends StatementCancellationHookAdapter {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final String STATEMENT_TYPE = "nl.cwi.monetdb.jdbc.MonetPreparedStatement";

   private final DbPoolService<?> poolService;

   @Inject
   public MonetDbStatementCancler(DbPoolService poolService) {
      this.poolService = poolService;
   }

   @Override
   public boolean consumes(Statement statement, Connection connection) {
      if (STATEMENT_TYPE.equals(statement.getClass()))
         return true;
      if (statement instanceof NewProxyPreparedStatement) {
         try {
            Class<?> stmtType = Class.forName(STATEMENT_TYPE);
            return ((NewProxyPreparedStatement) statement).isWrapperFor(stmtType);
         } catch (Exception e) {
         }
      }

      return false;
   }

   @Override
   public boolean cancelStatement(Statement statement, Connection connection, String statementId) {
      try {
         ConnectionPoolConfig poolConfig = null;
         try {
            if (connection instanceof ConnectionPoolAware)
               poolConfig = ((ConnectionPoolAware) connection).__getReportServerConnectionPoolConfig();
         } catch (Exception e) {
            logger.warn("Could not extract pool from connection", e);
         }

         if (null == poolConfig)
            return false;

         try (Connection con = poolService.getConnection(poolConfig).get()) {
            try (PreparedStatement newStmt = con
                  .prepareStatement("SELECT qtag FROM sys.queue() WHERE query LIKE ? LIMIT 1")) {

               newStmt.setString(1, "%" + statementId + "%");

               try (ResultSet resultSet = newStmt.executeQuery()) {
                  if (resultSet.next()) {
                     int id = resultSet.getInt(1);
                     con.createStatement().execute("CALL sys.stop(" + id + ")");
                  }
               }
            }
         }

      } catch (Exception e) {
      }
      return true;
   }
}
