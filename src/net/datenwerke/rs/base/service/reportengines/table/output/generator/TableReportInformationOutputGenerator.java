package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableReportInformationReport;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * Generates an {@link RSTableModel} from an report with only the
 * metaInformation set.
 * 
 *
 */
public class TableReportInformationOutputGenerator extends TableOutputGeneratorImpl {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());
   
   private RSTableModel tableModel;
   private int count = 0;
   private Instant start;

   @Override
   public String[] getFormats() {
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_REPORTINFORMATION };
   }
   
   @Override
   public void preInitialize() {
      this.start = Instant.now();
   }

   @Override
   public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report,
         TableReport originalReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user,
         ReportExecutionConfig... configs) throws IOException {
      /* create empty table model */
      tableModel = new RSTableModel(td);
      super.initialize(os, td, withSubtotals, report, originalReport, cellFormatters, parameters, user, configs);
   }

   @Override
   public void nextRow() {
      count++;
   }

   @Override
   public void addField(Object field, CellFormatter formatter) {
   }

   @Override
   public void close() {
   }

   @Override
   public CompiledReport getTableObject() {
      int actualCount = count > 0 ? count + 1 : 0;
      Duration time = Duration.between(start, Instant.now());
      return new TableReportInformationReport(actualCount, time.toMillis(), tableModel);
   }

   @Override
   public CompiledReport getFormatInfo() {
      return new TableReportInformationReport();
   }

   @Override
   public boolean isCatchAll() {
      return false;
   }

   @Override
   public void addGroupRow(int[] subtotalIndices, Object[] subtotals, int[] subtotalGroupFieldIndices,
         Object[] subtotalGroupFieldValues, int rowSize, CellFormatter[] cellFormatters) {

   }

}
