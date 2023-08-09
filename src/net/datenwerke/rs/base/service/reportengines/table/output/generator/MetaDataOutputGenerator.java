package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.OutputStream;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
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
public class MetaDataOutputGenerator implements TableOutputGenerator {

   private RSTableModel tableModel;

   @Override
   public String[] getFormats() {
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_METADATA };
   }
   
   @Override
   public void preInitialize() {
   }

   @Override
   public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report,
         TableReport orgReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user,
         ReportExecutionConfig... configs) {
      /* create empty table model */
      tableModel = new RSTableModel(td);
   }

   @Override
   public void nextRow() {
   }

   @Override
   public void addField(Object field, CellFormatter formatter) {
   }

   @Override
   public void close() {
   }

   @Override
   public CompiledReport getTableObject() {
      return tableModel;
   }

   @Override
   public CompiledReport getFormatInfo() {
      return new RSTableModel();
   }

   @Override
   public boolean isCatchAll() {
      return false;
   }

   @Override
   public void addGroupRow(int[] subtotalIndices, Object[] subtotals, int[] subtotalGroupFieldIndices,
         Object[] subtotalGroupFieldValues, int rowSize, CellFormatter[] cellFormatters) {

   }

   @Override
   public boolean supportsStreaming() {
      return true;
   }

}
