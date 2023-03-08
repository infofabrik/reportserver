package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSSimpleTableBean;
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
public class RSTableSimpleBeanOutputGenerator extends TableOutputGeneratorImpl {

   private RSSimpleTableBean bean;
   private int fieldInRow = 0;
   
   private String[] nullReplacements;
   private Boolean[] exportNullAsString;
   
   private final Provider<ExporterHelper> exporterHelperProvider;
   
   @Inject
   public RSTableSimpleBeanOutputGenerator(
         Provider<ExporterHelper> exporterHelperProvider
         ) {
      this.exporterHelperProvider = exporterHelperProvider;
   }

   @Override
   public String[] getFormats() {
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_SIMPLE_BEAN };
   }

   @Override
   public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report,
         TableReport orgReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user,
         ReportExecutionConfig... configs) throws IOException {
      super.initialize(os, td, withSubtotals, report, orgReport, cellFormatters, parameters, user, configs);

      final ExporterHelper exporterHelper = exporterHelperProvider.get();
      /* load columns */
      final List<Column> columns = exporterHelper.getExportedColumns(report, td);
      final ImmutablePair<String[], Boolean[]> nullFormats = exporterHelper.getNullFormats(columns, cellFormatters, td);
      nullReplacements = nullFormats.getLeft();
      exportNullAsString = nullFormats.getRight();
      
      bean = new RSSimpleTableBean();
      bean.setDefinition(td, report);
   }

   @Override
   public void nextRow() {
      bean.nextRow();
      fieldInRow = 0;
   }

   @Override
   public void addField(Object field, CellFormatter formatter) {
      String val = null;
      if (field == null) {
         if (!exportNullAsString[fieldInRow])
            val = "";
         else {
            val = nullReplacements[fieldInRow];
         }
      } else {
         val = formatter.format(getValueOf(field));
      }
      
      bean.addField(val);
      fieldInRow++;
   }

   @Override
   public CompiledReport getTableObject() {
      return bean;
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
      bean.close();
   }

}
