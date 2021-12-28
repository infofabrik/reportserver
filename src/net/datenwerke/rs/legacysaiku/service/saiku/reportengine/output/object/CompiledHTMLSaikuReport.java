package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledHtmlReportImpl;

public class CompiledHTMLSaikuReport extends CompiledHtmlReportImpl implements CompiledRSSaikuReport {

   private static final long serialVersionUID = -5303584974753303200L;

   public CompiledHTMLSaikuReport(String report) {
      super(report);
   }

   public CompiledHTMLSaikuReport() {
      super("");
   }

}
