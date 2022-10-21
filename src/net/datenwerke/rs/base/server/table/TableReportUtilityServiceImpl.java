
package net.datenwerke.rs.base.server.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;
import com.sencha.gxt.data.shared.SortDir;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoadResultBean;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

import net.datenwerke.gxtdto.client.model.ListStringBaseModel;
import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.NonFatalException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportInformation;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.FilterType;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.SelectorPanelLoadConfig;
import net.datenwerke.rs.base.client.reportengines.table.rpc.TableReportUtilityService;
import net.datenwerke.rs.base.client.reportengines.table.ui.model.Expressions;
import net.datenwerke.rs.base.client.reportengines.table.ui.model.SpecialParameters;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig;
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier;
import net.datenwerke.rs.base.service.reportengines.table.TableReportUtils;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.Order;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableRow;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;
import net.datenwerke.rs.core.service.reportmanager.ReportDtoService;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@Singleton
public class TableReportUtilityServiceImpl extends SecuredRemoteServiceServlet implements TableReportUtilityService {

   /**
    * 
    */
   private static final long serialVersionUID = 7567323018629663576L;

   private final SimpleDataSupplier simpleDataSupplyer;
   private final DtoService dtoService;
   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final TableReportUtils tableReportUtils;
   private final ParameterSetFactory parameterSetFactory;
   private final SecurityService securityService;
   private final ReportDtoService reportDtoService;
   private final I18nToolsService i18nToolsService;
   private final ReportService reportService;

   @Inject
   public TableReportUtilityServiceImpl(SimpleDataSupplier simpleDataSupplyer, DtoService dtoService,
         ReportDtoService reportDtoService, Provider<AuthenticatorService> authenticatorServiceProvider,
         TableReportUtils tableReportUtils, ParameterSetFactory parameterSetFactory, SecurityService securityService,
         I18nToolsService i18nToolsService, ReportService reportService) {

      this.simpleDataSupplyer = simpleDataSupplyer;
      this.dtoService = dtoService;
      this.reportDtoService = reportDtoService;
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.tableReportUtils = tableReportUtils;
      this.parameterSetFactory = parameterSetFactory;
      this.securityService = securityService;
      this.i18nToolsService = i18nToolsService;
      this.reportService = reportService;
   }

   private ParameterSet getParameterSet(Report report) {
      AuthenticatorService authenticatorService = authenticatorServiceProvider.get();
      User user = authenticatorService.getCurrentUser();

      ParameterSet ps = parameterSetFactory.create(user, report);
      ps.addAll(report.getParameterInstances());

      return ps;
   }

   @Transactional(rollbackOn = { Exception.class })
   @Override
   public Map<String, List<String>> getSpecialParameter(@Named("report") TableReportDto tableReportDto,
         String executeToken) throws ExpectedException {
      Map<String, List<String>> result = new HashMap<>();
      Set<String> keySet = parameterSetFactory.create().getParameterMap().keySet();

      for (SpecialParameters paramKey : SpecialParameters.values()) {
         Set<String> paramSet = new HashSet<>();
         Iterator<String> it = keySet.iterator();
         while (it.hasNext()) {
            String key = it.next();
            if (key.contains(paramKey.name() + "_")) {
               paramSet.add(key);
               it.remove();
            }
         }
         if (!paramSet.isEmpty()) {
            List<String> keyList = new ArrayList<>(paramSet);
            Collections.sort(keyList);
            result.put(paramKey.name(), keyList);
         }
      }

      // all that are left are global constants
      List<String> globalConstantsParamSet = new ArrayList<>();
      for (String key : keySet) {
         // _RS_LOCALE is filtered out (internal parameter).
         if (!"_RS_LOCALE".equals(key)) {
            globalConstantsParamSet.add(key);
         }
      }

      if (!globalConstantsParamSet.isEmpty()) {
         Collections.sort(globalConstantsParamSet);
         result.put(SpecialParameters._RS_GLOBAL_CONSTANTS.name(), globalConstantsParamSet);
      }

      /* get reference report */
      TableReport referenceReport = (TableReport) reportDtoService.getReferenceReport(tableReportDto);

      /* check rights */
      securityService.assertRights(referenceReport, Read.class);

      Set<ReportMetadata> metadataSet = referenceReport.getReportMetadata();

      List<String> reportMetadataParamSet = new ArrayList<>();
      for (ReportMetadata entry : metadataSet) {
         reportMetadataParamSet.add(SpecialParameters._RS_METADATA.name() + "_" + entry.getName());
      }

      if (!reportMetadataParamSet.isEmpty()) {
         Collections.sort(globalConstantsParamSet);
         result.put(SpecialParameters._RS_METADATA.name(), reportMetadataParamSet);
      }

      List<String> expressionParamSet = new ArrayList<>();
      for (Expressions expression : Expressions.values()) {
         expressionParamSet.add(expression.getValue());
      }

      if (!expressionParamSet.isEmpty()) {
         result.put(SpecialParameters._RS_EXPRESSION.name(), expressionParamSet);
      }

      return result;
   }

   @Transactional(rollbackOn = { Exception.class })
   @Override
   public PagingLoadResult<StringBaseModel> getDistinctValuesPaged(SelectorPanelLoadConfig pagingLoadConfig,
         @Named("report") TableReportDto reportDto, ColumnDto columnDto, FilterType type, boolean useFilters,
         boolean countResults, String executeToken) throws ServerCallFailedException {
      /* get reference report */
      TableReport referenceReport = (TableReport) reportDtoService.getReferenceReport(reportDto);

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);

      /* create variant */
      TableReport adjustedReport = (TableReport) dtoService.createUnmanagedPoso(reportDto);
      referenceReport = (TableReport) referenceReport.createTemporaryVariant(adjustedReport);

      Column column = (Column) dtoService.createUnmanagedPoso(columnDto);

      int i = reportDto.getColumns().indexOf(columnDto);
      if (i == -1)
         reportDto.getColumns().add(columnDto);
      else
         referenceReport.getColumns().set(i, column);

      /* set (override) column properties */
      if (!pagingLoadConfig.getSortInfo().isEmpty())
         column.setOrder(
               pagingLoadConfig.getSortInfo().get(0).getSortDir().equals(SortDir.ASC) ? Order.ASC : Order.DESC);
      else
         column.setOrder(Order.ASC);
      column.setHidden(false);

      /* apply filters */
      column.setLikeFilter(pagingLoadConfig.getFilter());
      column.setNullHandling(null);

      if (null != column.getFilter()) {
         if (FilterType.Include.equals(type)) {
            (column.getFilter()).setIncludeValues(null);
            (column.getFilter()).setIncludeRanges(null);
         } else {
            (column.getFilter()).setExcludeValues(null);
            (column.getFilter()).setExcludeRanges(null);
         }
      }

      /* case sensitive */
      if (null != column.getFilter())
         column.getFilter().setCaseSensitive(pagingLoadConfig.isCaseSensitive());

      try {
         ParameterSet parameters = getParameterSet(referenceReport);
         RSTableModel tableModel = simpleDataSupplyer.getDistinctColumnValuesPaged(referenceReport, parameters, column,
               referenceReport.getAdditionalColumns(), pagingLoadConfig.getOffset(), pagingLoadConfig.getLimit(),
               useFilters, executeToken);

         List<StringBaseModel> list = new ArrayList<StringBaseModel>();
         for (RSTableRow row : tableModel.getData()) {
            Object data = row.getAt(0);

            if (null == data)
               continue;

            StringBaseModel v = new StringBaseModel();
            if (data instanceof Number) {
               // v.setValue(numberServices.format((Number)data));
               String formatted = i18nToolsService.getUserNumberFormatter().format((Number) data);
               v.setValue(formatted);
            } else {
               v.setValue(data.toString());
            }
            list.add(v);
         }

         /* if we should cound */
         int count = Integer.MAX_VALUE;
         if (countResults)
            count = simpleDataSupplyer.getDistinctColumnValuesCount(referenceReport, parameters, column,
                  referenceReport.getAdditionalColumns(), useFilters);
         return new PagingLoadResultBean<StringBaseModel>(list, count, pagingLoadConfig.getOffset());

      } catch (ReportExecutorException e) {
         throw new NonFatalException(e);
      }
   }

   @Transactional(rollbackOn = { Exception.class })
   @Override
   public ListLoadResult<ColumnDto> getReturnedColumns(@Named("report") TableReportDto tableReportDto,
         String executeToken) throws NonFatalException, ExpectedException {
      ArrayList<ColumnDto> columns = new ArrayList<ColumnDto>();

      /* get reference report */
      TableReport referenceReport = (TableReport) reportDtoService.getReferenceReport(tableReportDto);

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);

      /* copy data */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(tableReportDto);
      referenceReport = (TableReport) referenceReport.createTemporaryVariant(adjustedReport);

      try {
         for (Column column : tableReportUtils.getReturnedPlainColumns((TableReport) referenceReport, executeToken)) {
            columns.add((ColumnDto) dtoService.createDto(column));
         }
      } catch (ReportExecutorException e) {
         throw new NonFatalException(e);
      }

      return new ListLoadResultBean<ColumnDto>(columns);
   }

   @Transactional(rollbackOn = { Exception.class })
   @Override
   public TableReportInformation getReportInformation(@Named("report") TableReportDto tableReportDto,
         String executeToken) throws ExpectedException {
      /* get reference report */
      TableReport referenceReport = (TableReport) reportDtoService.getReferenceReport(tableReportDto);

      /* check rights */
      securityService.assertRights(referenceReport, Execute.class);

      /* create variant */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(tableReportDto);
      referenceReport = (TableReport) referenceReport.createTemporaryVariant(adjustedReport);

      try {
         return tableReportUtils.getReportInformation(referenceReport, executeToken);
      } catch (ReportExecutorException e) {
         throw new ExpectedException(e);
      }
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "report", isDto = true, verify = @RightsVerification(rights = { Read.class,
               Execute.class, Write.class })) })
   @Override
   public List<ColumnDto> loadColumnDefinition(@Named("report") ReportDto referenceReportDto,
         DatasourceContainerDto containerDto, String query, String executeToken) throws ServerCallFailedException {
      ArrayList<ColumnDto> columns = new ArrayList<ColumnDto>();

      /* get real report and validate it */
      Report realReport = (Report) reportService.getReportById((Long) referenceReportDto.getDtoId());
      DatasourceContainer container = (DatasourceContainer) dtoService.createUnmanagedPoso(containerDto);

      DatasourceDefinitionConfig dsConfig = container.getDatasourceConfig();
      DatasourceDefinition ds = container.getDatasource();

      if (!(ds instanceof DatabaseDatasource))
         throw new ServerCallFailedException("Expected Database");
      securityService.assertRights(ds, Read.class, Execute.class);
      if (!(dsConfig instanceof DatabaseDatasourceConfig))
         throw new ServerCallFailedException("Expected Database");

      ((DatabaseDatasourceConfig) dsConfig).setQuery(query);

      if (!(realReport instanceof TableReport)) {
         /*
          * We create a fake table report. The permissions are the same as for the real
          * report. These were already checked in the current method.
          */
         realReport = new TableReport();
      }

      realReport.setDatasourceContainer(container);

      try {
         for (Column column : tableReportUtils.getReturnedPlainColumns((TableReport) realReport, executeToken)) {
            columns.add((ColumnDto) dtoService.createDto(column));
         }
      } catch (ReportExecutorException e) {
         throw new NonFatalException(e);
      }

      return columns;
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "report", isDto = true, verify = @RightsVerification(rights = { Read.class,
               Execute.class, Write.class })) })
   @Override
   public PagingLoadResult<ListStringBaseModel> loadData(@Named("report") ReportDto referenceReportDto,
         DatasourceContainerDto containerDto, PagingLoadConfig loadConfig, String query)
         throws ServerCallFailedException {
      Report referenceReport = (Report) reportService.getReportById((Long) referenceReportDto.getDtoId());
      DatasourceContainer container = (DatasourceContainer) dtoService.createUnmanagedPoso(containerDto);

      /* update query */
      DatasourceDefinitionConfig dsConfig = container.getDatasourceConfig();
      DatasourceDefinition ds = container.getDatasource();

      if (!(ds instanceof DatabaseDatasource))
         throw new ServerCallFailedException("Expected Database");
      securityService.assertRights(ds, Read.class, Execute.class);
      if (!(dsConfig instanceof DatabaseDatasourceConfig))
         throw new ServerCallFailedException("Expected Database");

      ((DatabaseDatasourceConfig) dsConfig).setQuery(query);
      referenceReport.setDatasourceContainer(container);

      ParameterSet parameters = getParameterSet(referenceReport);
      try {
         RSTableModel tableModel = simpleDataSupplyer.getData(referenceReport, parameters, loadConfig.getOffset(),
               loadConfig.getLimit());
         int count = simpleDataSupplyer.getDataCount(referenceReport, parameters);

         List<ListStringBaseModel> list = new ArrayList<ListStringBaseModel>();
         for (RSTableRow row : tableModel.getData())
            list.add(new ListStringBaseModel(row.getRow()));

         return new PagingLoadResultBean<ListStringBaseModel>(list, count, loadConfig.getOffset());
      } catch (ReportExecutorException e) {
         throw new ServerCallFailedException(e);
      }
   }

}
