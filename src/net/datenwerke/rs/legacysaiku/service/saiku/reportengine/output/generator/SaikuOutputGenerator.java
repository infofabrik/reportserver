package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.generator;

import java.util.List;

import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.output.ReportOutputGenerator;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object.CompiledRSSaikuReport;

import org.olap4j.CellSet;
import org.legacysaiku.olap.dto.SaikuDimensionSelection;
import org.legacysaiku.olap.dto.resultset.CellDataSet;
import org.legacysaiku.olap.util.formatter.ICellSetFormatter;


public interface SaikuOutputGenerator extends ReportOutputGenerator {
	
	public CompiledRSSaikuReport exportReport(CellDataSet cellDataSet, CellSet cellset, List<SaikuDimensionSelection> filters, String outputFormat, ReportExecutionConfig... configs) throws ReportExecutorException;
	public ICellSetFormatter getCellSetFormatter();

	public void initialize(SaikuReport report);
}
