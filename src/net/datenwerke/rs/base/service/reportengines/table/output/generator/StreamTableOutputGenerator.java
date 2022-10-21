package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.IOException;
import java.io.OutputStream;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSSimpleTableBean;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledStreamTableReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * Generates an RSTableModel from a given report.
 * 
 * 
 *
 */
public class StreamTableOutputGenerator extends TableOutputGeneratorImpl {

   private TableReport originalReport;

   @Override
   public String[] getFormats() {
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_STREAM_TABLE };
   }

   @Override
   public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report,
         TableReport orgReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user,
         ReportExecutionConfig... configs) throws IOException {
      super.initialize(os, td, withSubtotals, report, orgReport, cellFormatters, parameters, user, configs);

      this.originalReport = orgReport;
   }

   @Override
   public void nextRow() {
   }

   @Override
   public void addField(Object field, CellFormatter formatter) {
   }

   @Override
   public CompiledReport getTableObject() {
      return new CompiledStreamTableReport(originalReport);
   }

   @Override
   public CompiledReport getFormatInfo() {
      return new RSSimpleTableBean();
   }

   @Override
   public boolean isCatchAll() {
      return false;
   }

   @Override
   public void close() {
   }

}
