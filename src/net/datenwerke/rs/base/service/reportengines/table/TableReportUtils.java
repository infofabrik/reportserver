package net.datenwerke.rs.base.service.reportengines.table;

import java.util.Collection;
import java.util.List;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.NonFatalException;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportInformation;
import net.datenwerke.rs.base.service.reportengines.table.entities.AdditionalColumnSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryColumnFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.ColumnFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.security.service.usermanager.entities.User;

public interface TableReportUtils {

   List<Column> getReturnedColumns(TableReport report, String executeToken)
         throws ReportExecutorException, NonFatalException;

   List<Column> getReturnedPlainColumns(TableReport report, String executeToken)
         throws ReportExecutorException, NonFatalException;

   TableDefinition getReturnedPlainTableDefinition(TableReport report, String executeToken)
         throws ReportExecutorException, NonFatalException;

   List<Column> getReturnedColumns(TableReport report, User user, String executeToken)
         throws ReportExecutorException, NonFatalException;

   List<Column> getReturnedPlainColumns(TableReport report, User user, String executeToken)
         throws ReportExecutorException, NonFatalException;

   TableReportInformation getReportInformation(TableReport report, String executeToken) throws ReportExecutorException;

   FilterBlock getParentFilterBlock(FilterBlock filterBlock);

   List<TableReport> getReportsWithMetadataDatasource(DatasourceDefinition ds);

   void remove(Column column);

   Column merge(Column column);

   void persist(AdditionalColumnSpec column);

   void persist(Column column);

   void remove(AdditionalColumnSpec column);

   void remove(PreFilter filter);

   void remove(FilterBlock filterBlock);

   List<ColumnFilter> getColumnFiltersWithColumn(Column column);

   Collection<BinaryColumnFilter> getBinaryColumnFiltersWithColumn(Column column);

   List<ColumnReference> getColumnReferencesFor(AdditionalColumnSpec reference);

}
