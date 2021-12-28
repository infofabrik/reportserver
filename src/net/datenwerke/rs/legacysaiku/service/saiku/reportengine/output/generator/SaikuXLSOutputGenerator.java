package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.generator;

import java.util.List;

import javax.inject.Inject;

import org.legacysaiku.olap.dto.SaikuDimensionSelection;
import org.legacysaiku.olap.dto.resultset.CellDataSet;
import org.legacysaiku.service.util.export.ExcelExporter;
import org.olap4j.CellSet;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object.CompiledRSSaikuReport;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object.CompiledXLSSaikuReport;

public class SaikuXLSOutputGenerator extends SaikuOutputGeneratorImpl {

   @Inject
   public SaikuXLSOutputGenerator(HookHandlerService hookHandler) {
      super(hookHandler);
      // TODO Auto-generated constructor stub
   }

   @Override
   public String[] getFormats() {
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_EXCEL };
   }

   @Override
   public CompiledReport getFormatInfo() {
      return new CompiledXLSSaikuReport();
   }

   @Override
   public CompiledRSSaikuReport exportReport(CellDataSet cellDataSet, CellSet cellset,
         List<SaikuDimensionSelection> filters, String outputFormat, ReportExecutionConfig... configs)
         throws ReportExecutorException {

      byte[] excel = ExcelExporter.exportExcel(cellset, getCellSetFormatter(), filters);
      return new CompiledXLSSaikuReport(excel);
   }

}
