package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.generator;

import org.legacysaiku.olap.util.formatter.CellSetFormatter;
import org.legacysaiku.olap.util.formatter.FlattenedCellSetFormatter;
import org.legacysaiku.olap.util.formatter.ICellSetFormatter;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;

public abstract class SaikuOutputGeneratorImpl implements SaikuOutputGenerator {

   protected final HookHandlerService hookHandler;
   protected SaikuReport report;

   @Override
   public void initialize(SaikuReport report) {
      this.report = report;

   }

   public SaikuOutputGeneratorImpl(HookHandlerService hookHandler) {
      this.hookHandler = hookHandler;
   }

   @Override
   public boolean isCatchAll() {
      return false;
   }

   @Override
   public ICellSetFormatter getCellSetFormatter() {
      if (report.isHideParents())
         return new FlattenedCellSetFormatter();
      return new CellSetFormatter();
   }

   protected <R extends ReportExecutionConfig> R getConfig(Class<? extends R> type, ReportExecutionConfig... configs) {
      for (ReportExecutionConfig config : configs)
         if (type.isAssignableFrom(config.getClass()))
            return (R) config;
      return null;
   }
}
