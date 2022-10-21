package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Clob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * Generates an RSTableModel from a given report.
 * 
 * 
 *
 */
public class RSTableOutputGenerator extends TableOutputGeneratorImpl {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   protected RSTableModel tableModel;

   protected Object[] currentRow;
   protected int rowLength;
   protected int fieldIndex = 0;

   private boolean currentRowIsBlank = true;

   @Override
   public String[] getFormats() {
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_TABLE };
   }

   @Override
   public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report,
         TableReport originalReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user,
         ReportExecutionConfig... configs) throws IOException {
      super.initialize(os, td, withSubtotals, report, originalReport, cellFormatters, parameters, user, configs);

      /* create empty table model */
      tableModel = new RSTableModel(td);
      rowLength = tableModel.getColumnCount();
      currentRow = new Object[rowLength];
      fieldIndex = 0;
   }

   @Override
   public void nextRow() {
      currentRowIsBlank = true;
      if (null != currentRow) {
         if (fieldIndex != rowLength)
            throw new IllegalStateException("Row was not completed"); //$NON-NLS-1$

         tableModel.addDataRow(currentRow);
      }
      currentRow = new Object[rowLength];
      fieldIndex = 0;
   }

   @Override
   public void addField(Object field, CellFormatter formatter) {
      currentRowIsBlank = false;
      if (!(field instanceof Clob))
         currentRow[fieldIndex++] = field;
      else {
         Clob clob = (Clob) field;

         try {
            Reader is = clob.getCharacterStream();
            StringBuffer sb = new StringBuffer();
            int length = (int) clob.length();
            if (length > 0) {
               char[] buffer = new char[length];

               int count = 0;
               while ((count = is.read(buffer)) != -1)
                  sb.append(buffer);

               currentRow[fieldIndex++] = sb.toString();
            }
         } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            currentRow[fieldIndex++] = field;
         }
      }
   }

   @Override
   public void close() {
      if (null != currentRow && !currentRowIsBlank) {
         if (fieldIndex != rowLength)
            throw new IllegalStateException("Row was not completed"); //$NON-NLS-1$

         tableModel.addDataRow(currentRow);
      }
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
      for (int i = 0; i < subtotalIndices.length; i++) {
         currentRow[subtotalIndices[i]] = subtotals[i];
      }

      for (int i = 0; i < subtotalGroupFieldIndices.length; i++) {
         currentRow[subtotalGroupFieldIndices[i]] = subtotalGroupFieldValues[i];
      }

      tableModel.addDataRow(currentRow).setSubtotalRow(true);
      currentRow = null;
      nextRow();
   }

}
