package net.datenwerke.rs.base.service.reportengines.table;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;

import org.apache.commons.configuration2.HierarchicalConfiguration;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.NonFatalException;
import net.datenwerke.rs.base.client.reportengines.table.dto.PageSizeConfig;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportInformation;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference;
import net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference__;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport__;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryColumnFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryColumnFilter__;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.ColumnFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.ColumnFilter__;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock__;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableReportInformationReport;
import net.datenwerke.rs.base.service.reportengines.table.utils.ColumnMetadata;
import net.datenwerke.rs.base.service.reportengines.table.utils.TableReportColumnMetadataService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer__;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECMetadata;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportInformation;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.utils.reflection.ProxyUtils;
import net.datenwerke.rs.utils.simplequery.annotations.Join;
import net.datenwerke.rs.utils.simplequery.annotations.Predicate;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.eventlogger.annotations.FireMergeEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FirePersistEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;
import net.datenwerke.security.service.usermanager.entities.User;

public class TableReportUtilsImpl implements TableReportUtils {

   private final TableReportColumnMetadataService tableReportMetadataService;
   private final ReportExecutorService reportExecutor;
   private final Provider<EntityManager> entityManagerProvider;
   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final ProxyUtils proxyUtils;
   private final Provider<ConfigService> configServiceProvider;
   
   private final static String PAGE_CONFIG_CONFIG_LIST = "dynamicList.pageSize.configs.config";

   @Inject
   public TableReportUtilsImpl(
         TableReportColumnMetadataService tableReportMetadataService,
         ReportExecutorService reportExecutor, 
         Provider<EntityManager> entityManagerProvider,
         Provider<AuthenticatorService> authenticatorServiceProvider, 
         ProxyUtils proxyUtils,
         Provider<ConfigService> configServiceProvider
         ) {

      /* store objects */
      this.tableReportMetadataService = tableReportMetadataService;
      this.reportExecutor = reportExecutor;
      this.entityManagerProvider = entityManagerProvider;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.proxyUtils = proxyUtils;
      this.configServiceProvider = configServiceProvider;
   }

   @Override
   public TableReportInformation getReportInformation(final TableReport report, final String executeToken)
         throws ReportExecutorException {
      TableReportInformation information = new TableReportInformation();

      /* visible count */
      long visCols = report.getColumns().stream().filter(col -> null == col.isHidden() || !col.isHidden()).count();

      information.setVisibleCount((int) visCols);

      /* column count & meta data */
      TableReportInformationReport res = (TableReportInformationReport) reportExecutor.execute(report,
            ReportExecutorService.OUTPUT_FORMAT_REPORTINFORMATION, new RECReportInformation(),
            new RECReportExecutorToken(executeToken));
      TableDefinition tableDefinition = res.getTableModel().getTableDefinition();
      information.setColumnCount(tableDefinition.getColumnNames().size());
      information.setDataCount(res.getDataCount());
      information.setExecuteDuration(res.getExecuteDuration());

      return information;
   }

   @Override
   @SimpleQuery(from = TableReport.class, join = @Join(joinAttribute = TableReport__.metadataDatasourceContainer, where = @Predicate(attribute = DatasourceContainer__.datasource, value = "ds")))
   public List<TableReport> getReportsWithMetadataDatasource(@Named("ds") DatasourceDefinition ds) {
      return null; // by magic
   }

   @Override
   @QueryByAttribute(where = ColumnFilter__.column)
   public List<ColumnFilter> getColumnFiltersWithColumn(@Named("column") Column column) {
      return null; // by magic
   }

   @Override
   public Collection<BinaryColumnFilter> getBinaryColumnFiltersWithColumn(@Named("column") Column column) {
      Set<BinaryColumnFilter> filters = new HashSet<BinaryColumnFilter>();
      filters.addAll(getBinaryColumnFiltersWithColumnA(column));
      filters.addAll(getBinaryColumnFiltersWithColumnB(column));

      return filters;
   }

   @QueryByAttribute(where = BinaryColumnFilter__.columnA)
   protected List<BinaryColumnFilter> getBinaryColumnFiltersWithColumnA(@Named("column") Column column) {
      return null; // by magic
   }

   @QueryByAttribute(where = BinaryColumnFilter__.columnB)
   protected List<BinaryColumnFilter> getBinaryColumnFiltersWithColumnB(@Named("column") Column column) {
      return null; // by magic
   }

   @Override
   @QueryByAttribute(where = ColumnReference__.reference)
   public List<ColumnReference> getColumnReferencesFor(@Named("column") AdditionalColumnSpec reference) {
      return null; // by magic
   }

   @Override
   @SimpleQuery(join = @Join(joinAttribute = FilterBlock__.childBlocks, where = @Predicate(attribute = "", value = "childBlock")))
   public FilterBlock getParentFilterBlock(@Named("childBlock") FilterBlock filterBlock) {
      return null; // by magic
   }

   @Override
   public List<Column> getReturnedPlainColumns(TableReport report, String executeToken)
         throws ReportExecutorException, NonFatalException {
      User user = authenticatorServiceProvider.get().getCurrentUser();
      return getReturnedColumns(report, user, executeToken, true);
   }

   @Override
   public TableDefinition getReturnedPlainTableDefinition(TableReport report, String executeToken)
         throws ReportExecutorException, NonFatalException {
      User user = authenticatorServiceProvider.get().getCurrentUser();
      return getReturnedTableDefinition(report, user, executeToken, true);
   }

   @Override
   public List<Column> getReturnedColumns(TableReport report, String executeToken)
         throws ReportExecutorException, NonFatalException {
      User user = authenticatorServiceProvider.get().getCurrentUser();
      return getReturnedColumns(report, user, executeToken, false);
   }

   @Override
   public List<Column> getReturnedPlainColumns(TableReport report, User user, String executeToken)
         throws ReportExecutorException, NonFatalException {
      return getReturnedColumns(report, user, executeToken, true);
   }

   @Override
   public List<Column> getReturnedColumns(TableReport report, User user, String executeToken)
         throws ReportExecutorException, NonFatalException {
      return getReturnedColumns(report, user, executeToken, false);
   }

   protected List<Column> getReturnedColumns(TableReport report, User user, String executeToken,
         boolean ignoreAnyColumnConfiguration) throws ReportExecutorException, NonFatalException {

      ArrayList<Column> columns = new ArrayList<>();

      /* all columns please */
      report.setColumns(null);
      report.setSelectAllColumns(true);
      if (ignoreAnyColumnConfiguration)
         report.setIgnoreAnyColumnConfiguration(true);

      Map<String, ColumnMetadata> columnMetadataMap = tableReportMetadataService.createColumnMetadataMap(report, user);

      /* disable subtotal */
      report.setEnableSubtotals(false);

      /* execute report */
      RSTableModel res = (RSTableModel) reportExecutor.execute(report, user,
            ReportExecutorService.OUTPUT_FORMAT_METADATA, new RECMetadata(), new RECReportExecutorToken(executeToken));

      TableDefinition tableDefinition = res.getTableDefinition();

      for (Object[] column : tableDefinition.getColumns()) {
         Column col = new Column();
         col.setName((String) column[0]);
         col.setType(((Integer) column[3]));

         if (columnMetadataMap.containsKey(col.getName())) {
            ColumnMetadata metadata = columnMetadataMap.get(col.getName());
            col.setDefaultAlias(metadata.getDefaultAlias());
            col.setDefaultPreviewWidth(metadata.getDefaultPreviewWidth());
            col.setDescription(metadata.getDescription());
            col.setSemanticType(metadata.getSemanticType());
            col.setIndexColumn(metadata.isIndexColumn());
         }

         columns.add(col);
      }

      return columns;
   }

   protected TableDefinition getReturnedTableDefinition(TableReport report, User user, String executeToken,
         boolean ignoreAnyColumnConfiguration) throws ReportExecutorException, NonFatalException {

      /* all columns please */
      report.setColumns(null);
      report.setSelectAllColumns(true);
      if (ignoreAnyColumnConfiguration)
         report.setIgnoreAnyColumnConfiguration(true);

      /* disable subtotal */
      report.setEnableSubtotals(false);

      /* execute report */
      RSTableModel res = (RSTableModel) reportExecutor.execute(report, user,
            ReportExecutorService.OUTPUT_FORMAT_METADATA, new RECMetadata(), new RECReportExecutorToken(executeToken));

      TableDefinition tableDefinition = res.getTableDefinition();

      return tableDefinition;
   }

   @FireRemoveEntityEvents
   @Override
   public void remove(Column column) {
      EntityManager em = entityManagerProvider.get();
      column = em.find(column.getClass(), column.getId());
      if (null != column)
         em.remove(column);
   }

   @FireRemoveEntityEvents
   @Override
   public void remove(FilterBlock filterBlock) {
      EntityManager em = entityManagerProvider.get();
      filterBlock = em.find(filterBlock.getClass(), filterBlock.getId());
      if (null != filterBlock) {
         filterBlock.getFilters().clear();
         filterBlock.getChildBlocks().clear();

         /* get parent filter block */
         FilterBlock parent = getParentFilterBlock(filterBlock);
         if (null != parent)
            parent.getChildBlocks().remove(filterBlock);
         em.remove(filterBlock);
      }
   }

   @FirePersistEntityEvents
   @Override
   public void persist(AdditionalColumnSpec column) {
      EntityManager em = entityManagerProvider.get();
      em.persist(column);
   }

   @FirePersistEntityEvents
   @Override
   public void persist(Column column) {
      EntityManager em = entityManagerProvider.get();
      em.persist(column);
   }

   @FireMergeEntityEvents
   @Override
   public Column merge(Column column) {
      if (null == column)
         return null;
      EntityManager em = entityManagerProvider.get();
      column = (Column) em.find(proxyUtils.getUnproxiedClass(column.getClass()), column.getId());
      return em.merge(column);
   }

   @FireRemoveEntityEvents
   @Override
   public void remove(AdditionalColumnSpec column) {
      EntityManager em = entityManagerProvider.get();
      column = em.find(column.getClass(), column.getId());
      if (null != column)
         em.remove(column);
   }

   @FireRemoveEntityEvents
   @Override
   public void remove(PreFilter filter) {
      EntityManager em = entityManagerProvider.get();
      filter = em.find(filter.getClass(), filter.getId());
      if (null != filter)
         em.remove(filter);
   }

   @Override
   public List<PageSizeConfig> getPreviewPageSizeConfigs() {
      HierarchicalConfiguration config = (HierarchicalConfiguration) configServiceProvider.get()
            .getConfigFailsafe("ui/previews.cf");
      if (null == config)
         return getDefaultPageSizeConfigs();

      List<Object> configList = config.getList(PAGE_CONFIG_CONFIG_LIST);
      if (null == configList || configList.isEmpty())
         return getDefaultPageSizeConfigs();

      return configList
         .stream()
         .filter(o -> o instanceof String)
         .map(o -> (String) o)
         .map(s -> {
            int minCols = config.getInt(PAGE_CONFIG_CONFIG_LIST + "(" + configList.indexOf(s) + ")[@minCols]", 0);
            String maxColsStr = config.getString(PAGE_CONFIG_CONFIG_LIST + "(" + configList.indexOf(s) + ")[@maxCols]", "0");
            int maxCols = 0;
            if ("MAX".equals(maxColsStr))
               maxCols = Integer.MAX_VALUE;
            else
               maxCols = Integer.parseInt(maxColsStr);
            int numberOfRows = config.getInt(PAGE_CONFIG_CONFIG_LIST + "(" + configList.indexOf(s) + ")", 0);
            return new PageSizeConfig(minCols, maxCols, numberOfRows);
         })
         .collect(toList());
   }
   
   private List<PageSizeConfig> getDefaultPageSizeConfigs() {
      List<PageSizeConfig> configs = new ArrayList<>();
      configs.add(new PageSizeConfig(0, 99, 50));
      configs.add(new PageSizeConfig(100, 249, 25));
      configs.add(new PageSizeConfig(250, 499, 10));
      configs.add(new PageSizeConfig(500, Integer.MAX_VALUE, 5));
      return configs;
   }

}
