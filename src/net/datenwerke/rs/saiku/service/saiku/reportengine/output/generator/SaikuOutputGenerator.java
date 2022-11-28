package net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator;

import java.util.List;

import org.olap4j.CellSet;
import org.saiku.olap.dto.resultset.CellDataSet;
import org.saiku.olap.query2.ThinHierarchy;
import org.saiku.olap.util.formatter.ICellSetFormatter;

import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.output.ReportOutputGenerator;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.object.CompiledRSSaikuReport;
import net.datenwerke.security.service.usermanager.entities.User;

public interface SaikuOutputGenerator extends ReportOutputGenerator {

   public CompiledRSSaikuReport exportReport(CellDataSet cellDataSet, CellSet cellset, List<ThinHierarchy> filters,
         ICellSetFormatter formatter, String outputFormat, ReportExecutionConfig... configs)
         throws ReportExecutorException;
   
   public ICellSetFormatter getCellSetFormatter();

   public void initialize(SaikuReport report, User user);
}
