package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledDataCountTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
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
public class DataCountOutputGenerator extends TableOutputGeneratorImpl {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private int count = 0;
   private long start;

   @Override
   public String[] getFormats() {
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_DATACOUNT };
   }

   @Override
   public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report,
         TableReport originalReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user,
         ReportExecutionConfig... configs) throws IOException {
      super.initialize(os, td, withSubtotals, report, originalReport, cellFormatters, parameters, user, configs);
      this.start = System.currentTimeMillis();
   }

   @Override
   public void nextRow() {
   }

   @Override
   public void addField(Object field, CellFormatter formatter) {
      try {
         count = Integer.parseInt(String.valueOf(field));
      } catch (Exception e) {
         logger.warn("unexpected data", e);
      }
   }

   @Override
   public void close() {
   }

   @Override
   public CompiledDataCountTableReport getTableObject() {
      return new CompiledDataCountTableReport(count, System.currentTimeMillis() - start);
   }

   @Override
   public CompiledReport getFormatInfo() {
      return new CompiledDataCountTableReport();
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
