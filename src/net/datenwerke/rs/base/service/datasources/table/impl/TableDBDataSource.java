package net.datenwerke.rs.base.service.datasources.table.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
import net.datenwerke.rs.base.service.datasources.statementmanager.StatementManagerService;
import net.datenwerke.rs.base.service.datasources.table.impl.config.TableDatasourceConfig;
import net.datenwerke.rs.base.service.datasources.table.impl.events.CloseTableDatasourceEvent;
import net.datenwerke.rs.base.service.datasources.table.impl.events.OpenTableDatasourceEvent;
import net.datenwerke.rs.base.service.datasources.table.impl.hooks.TableDbDatasourceOpenedHook;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper.ResultSetObjectHandler;
import net.datenwerke.rs.base.service.dbhelper.ManagedQueryFactory;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.exceptions.DatabaseConnectionException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.utils.eventbus.EventBus;

/**
 * 
 *
 */
public class TableDBDataSource implements TableDataSource {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   @Inject
   private static ManagedQueryFactory queryFactory;

   @Inject
   private static EventBus eventBus;

   @Inject
   private static StatementManagerService statementManagerService;

   @Inject
   private static HookHandlerService hookHandler;

   private final Connection connection;
   private final ManagedQuery mQuery;
   private final DatasourceContainerProvider datasourceContainerProvider;
   private final DatabaseHelper dbHelper;
   private final String uuid = UUID.randomUUID().toString();

   private PreparedStatement stmt;

   private ResultSet resultSet;

   private boolean open = false;

   private TableDefinition plainTableDefinition;

   private ManagedQuery plainMQuery;

   private ParameterSet parameters;

   private TableDatasourceConfig config;

   private ResultSetObjectHandler resultSetHandler;

   private Map<Integer, Integer> indexMap;

   public TableDBDataSource(Connection connection, String query,
         DatasourceContainerProvider datasourceContainerProvider, DatabaseHelper dbHelper) {

      if (null == query || "".equals(query.trim()))
         throw new IllegalArgumentException("Query may not be empty");

      // this is necessary if the sql has a comment at the end in order to separate
      // the comment from the next sql
      query += "\n";

      this.connection = connection;
      this.datasourceContainerProvider = datasourceContainerProvider;
      this.dbHelper = dbHelper;
      this.mQuery = queryFactory.create(query, dbHelper, this);
      this.plainMQuery = queryFactory.create(query, dbHelper, this);
   }

   @Override
   public void applyConfig(TableDatasourceConfig config) {
      this.config = config;
   }

   @Override
   public DatasourceContainerProvider getDatasourceContainerProvider() {
      return datasourceContainerProvider;
   }

   @Override
   public TableDefinition getPlainTableDefinition() {
      return plainTableDefinition;
   }

   public Connection getConnection() {
      return connection;
   }

   public ManagedQuery getManagedQuery() {
      return mQuery;
   }

   @Override
   public void addQueryComment(String comment) {
      plainMQuery.addQueryComment(comment);
      mQuery.addQueryComment(comment);
   }

   @Override
   public Object getFieldValue(int pos) throws ReportExecutorException {
      if (!open) {
         /* next opens and selects the first row as we are currently not open */
         if (!next())
            throw new IndexOutOfBoundsException();
      }

      try {
         return resultSetHandler.getObject(indexMap.get(pos));
      } catch (SQLException e) {
         DatabaseConnectionException dce = new DatabaseConnectionException(
               "Could not ascertain object at position: " + pos); //$NON-NLS-1$
         dce.initCause(e);
         throw dce;
      }
   }

   @Override
   public TableDefinition getTableDefinition() throws ReportExecutorException {
      if (!open)
         open();

      /* get metadata and build table definition */
      TableDefinition td = null;
      try {
         ResultSetMetaData metaData = resultSet.getMetaData();
         if (null == mQuery.getColumns() || mQuery.isCountRows())
            td = TableDefinition.fromResultSetMetaData(metaData);
         else
            td = TableDefinition.fromResultSetMetaData(metaData, mQuery.getColumns());

         /*
          * Incorporates aliases into the tableDefinition. We can't just use the column
          * list from the managed query, as this is incorrect in some cases -> select
          * count(*) from (...)
          */
         if (null != mQuery.getColumns() && !mQuery.getColumns().isEmpty()) {

            ArrayList<String> originalColumnNames = new ArrayList<String>();

            /* build a map, mapping unique column names to aliases */
            HashMap<String, String> uniqueNameToAliasMap = new HashMap<String, String>();
            for (int i = 0; i < mQuery.getColumns().size(); i++) {
               // see also uniqeColumnNamem in QueryBuilder
               String uniqueName = QueryBuilder.uniqueColumnPrefix + i;
               String aliasName = uniqueName;

               Column col = mQuery.getColumns().get(i);

               /* filter hidden columns */
               if (col.isHidden())
                  continue;

               if (null != col.getAlias() && !col.getAlias().isEmpty())
                  aliasName = col.getAlias();
               else if (null != col.getDefaultAlias() && !col.getDefaultAlias().isEmpty())
                  aliasName = col.getDefaultAlias();
               else
                  aliasName = col.getName();

               originalColumnNames.add(col.getName());

               uniqueNameToAliasMap.put(uniqueName.toLowerCase(), aliasName);
            }

            /*
             * replace those column Names present in the map with their respective aliases
             */
            ArrayList<String> columnNames = new ArrayList<String>();
            for (String colName : td.getColumnNames()) {
               if (!uniqueNameToAliasMap.containsKey(colName.toLowerCase()))
                  columnNames.add(colName);
               else
                  columnNames.add(uniqueNameToAliasMap.get(colName.toLowerCase()));
            }

            td.setColumnNames(columnNames);
            td.setOriginalColumnNames(originalColumnNames);
         }

      } catch (SQLException e) {
         DatabaseConnectionException dce = new DatabaseConnectionException("Could not obtain metadata."); //$NON-NLS-1$
         dce.initCause(e);
         throw dce;
      }

      return td;
   }

   @Override
   public boolean next() throws ReportExecutorException {
      if (!open)
         open();
      try {
         /* isclosed does not work for all drivers (in c3p0) */
//			if(resultSet.isClosed())
//				return false;

         return resultSet.next();
      } catch (SQLException e) {
         DatabaseConnectionException dce = new DatabaseConnectionException("Could not increment cursor on resultset."); //$NON-NLS-1$
         dce.initCause(e);
         throw dce;
      }
   }

   @Override
   public void open() throws ReportExecutorException {
      open(null);
   }

   @Override
   public void open(String executorToken) throws ReportExecutorException {
      if (open)
         throw new IllegalStateException("DataSource already open");

      /* set flag */
      open = true;

      if (null == executorToken)
         executorToken = UUID.randomUUID().toString();

      for (TableDbDatasourceOpenedHook hookers : hookHandler.getHookers(TableDbDatasourceOpenedHook.class)) {
         hookers.datasourceOpenend(this, executorToken);
      }

      /* handle plain */
      try {
         plainMQuery.setLimit(0);
         plainMQuery.setIgnoreAnyColumnConfiguration(true);
         PreparedStatement getColumnsStmt = plainMQuery.prepareStatement(connection);
         getColumnsStmt.setMaxRows(0);

         statementManagerService.registerStatement(executorToken, getColumnsStmt, connection);
         ResultSetMetaData metaData = getColumnsStmt.executeQuery().getMetaData();

         this.plainTableDefinition = TableDefinition.fromResultSetMetaData(metaData);
         mQuery.setPlainColumnNames(plainTableDefinition.getColumnNames());
      } catch (SQLException e) {
         throw new ReportExecutorException(
               DatasourcesMessages.INSTANCE.exceptionCouldNotExecuteStmt(e.getLocalizedMessage()), e);
      } finally {
         statementManagerService.unregisterStatement(executorToken);
      }

      /* open connection and get resultset */
      if (null == stmt) {
         try {
            stmt = mQuery.prepareStatement(connection);

            /* configure statement */
            if (null != config) {
               if (null != config.getQueryTimeout()) {
                  try {
                     stmt.setQueryTimeout(config.getQueryTimeout());
                  } catch (Exception e) {
                     logger.info("Could not set query timeout", e);
                  }
               }
            }
         } catch (SQLException e) {
            throw new ReportExecutorException(
                  DatasourcesMessages.INSTANCE.exceptionCouldNotOpenDatasource(e.getLocalizedMessage()), e);
         }
      }
      try {
         eventBus.fireEvent(new OpenTableDatasourceEvent(stmt, uuid));
         statementManagerService.registerStatement(executorToken, stmt, connection);

         resultSet = stmt.executeQuery();
         resultSetHandler = dbHelper.createResultSetHandler(resultSet, connection);

         /* prepare indexMap */
         int hiddenOffset = 0;
         int index = 1;
         if (null == mQuery.getColumns() || mQuery.isCountRows() || mQuery.isDistinct()
               || mQuery.hasAggregateColumns()) {
            indexMap = new IdentityMap();
         } else {
            indexMap = new HashMap<Integer, Integer>();

            for (int i = 0; i < mQuery.getColumns().size(); i++) {
               if (mQuery.getColumns().get(i).isHidden()) {
                  hiddenOffset++;
               } else {
                  indexMap.put(index, index + hiddenOffset);
                  index++;
               }
            }
         }
      } catch (SQLException e) {
         throw new ReportExecutorException(
               DatasourcesMessages.INSTANCE.exceptionCouldNotExecuteStmt(e.getLocalizedMessage()), e);
      } finally {
         statementManagerService.unregisterStatement(executorToken);
      }
   }

   @Override
   public void close() {
      try {
         if (open && null != stmt) {
            try {
               /* isclosed does not work for all drivers (in c3p0) */
//					if(! stmt.isClosed())
               stmt.close();
            } catch (SQLException e) {
               DatabaseConnectionException dce = new DatabaseConnectionException("Could not close statement."); //$NON-NLS-1$
               dce.initCause(e);
               throw dce;
            }

            try {
               if (!connection.isClosed())
                  connection.close();
            } catch (SQLException e) {
               DatabaseConnectionException dce = new DatabaseConnectionException("Could not close connection."); //$NON-NLS-1$
               dce.initCause(e);
               throw dce;
            }
         } else if (!open) {
            try {
               if (!connection.isClosed())
                  connection.close();
            } catch (SQLException e) {
               DatabaseConnectionException dce = new DatabaseConnectionException("Could not close connection."); //$NON-NLS-1$
               dce.initCause(e);
               throw dce;
            }
         }
      } finally {
         /* remove flag */
         open = false;

         eventBus.fireEvent(new CloseTableDatasourceEvent(uuid));
      }
   }

   @Override
   public boolean isOpen() {
      return open;
   }

   @Override
   public void applyParameters(ParameterSet parameters) {
      if (isOpen())
         throw new IllegalStateException("We are already open. Cannot change the query now"); //$NON-NLS-1$

      /* execute juel and jasper parameters */
      if (null != parameters) {
         mQuery.applyParameterSet(parameters);
         plainMQuery.applyParameterSet(parameters);
      }

      this.parameters = parameters;
   }

   @Override
   public ParameterSet getParameters() {
      return parameters;
   }

   @Override
   public void applyColumnConfiguration(List<Column> columnList) {
      if (null != columnList) {
         if (columnList.isEmpty()) {
            columnList = null;
         } else {
            int visibleColumns = 0;
            for (Column c : columnList) {
               if (null == c.isHidden() || !c.isHidden())
                  visibleColumns++;
            }

            if (0 == visibleColumns)
               throw new ReportExecutorRuntimeException("No visible columns");
         }
      }

      mQuery.applyColumnConfiguration(columnList);
   }

   @Override
   public void addAdditionalColumnSpecs(List<AdditionalColumnSpec> additionalColumns) {

      mQuery.setAdditionalColumnSpecs(additionalColumns);
   }

   @Override
   public void limit(int limit) {
      if (isOpen())
         throw new IllegalStateException("We are already open. Cannot change the query now"); //$NON-NLS-1$
      mQuery.setLimit(limit);
   }

   @Override
   public void countRows() {
      mQuery.setCountRows(true);
   }

   @Override
   public void distinct(boolean enableDistinct) {
      mQuery.distinct(true);

   }

   @Override
   public void paged(int offset, int length) {
      mQuery.paged(offset, length);
   }

   @Override
   public void setPreFilter(FilterBlock rootBlock) {
      mQuery.preFilter(rootBlock);
   }

   @Override
   public void setIgnoreAnyColumnConfiguration(boolean ignore) {
      mQuery.setIgnoreAnyColumnConfiguration(ignore);
   }

   class IdentityMap implements Map<Integer, Integer> {

      @Override
      public int size() {
         return Integer.MAX_VALUE;
      }

      @Override
      public boolean isEmpty() {
         return false;
      }

      @Override
      public boolean containsKey(Object key) {
         if (key instanceof Integer)
            return true;
         return false;
      }

      @Override
      public boolean containsValue(Object value) {
         if (value instanceof Integer)
            return true;
         return false;
      }

      @Override
      public Integer get(Object key) {
         if (key instanceof Integer)
            return (Integer) key;
         return null;
      }

      @Override
      public Integer put(Integer key, Integer value) {
         return key;
      }

      @Override
      public Integer remove(Object key) {
         return null;
      }

      @Override
      public void putAll(Map<? extends Integer, ? extends Integer> m) {

      }

      @Override
      public void clear() {

      }

      @Override
      public Set<Integer> keySet() {
         return null;
      }

      @Override
      public Collection<Integer> values() {
         return null;
      }

      @Override
      public Set<java.util.Map.Entry<Integer, Integer>> entrySet() {
         return null;
      }

   }

   @Override
   public void cancelStatement() {
      // TODO Auto-generated method stub

   }

}
