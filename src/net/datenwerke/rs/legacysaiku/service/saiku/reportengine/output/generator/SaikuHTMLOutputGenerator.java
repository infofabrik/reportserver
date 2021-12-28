package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.generator;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import javax.inject.Inject;

import org.legacysaiku.olap.dto.SaikuDimensionSelection;
import org.legacysaiku.olap.dto.resultset.AbstractBaseCell;
import org.legacysaiku.olap.dto.resultset.CellDataSet;
import org.legacysaiku.olap.dto.resultset.DataCell;
import org.legacysaiku.olap.dto.resultset.MemberCell;
import org.olap4j.CellSet;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object.CompiledHTMLSaikuReport;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object.CompiledRSSaikuReport;

public class SaikuHTMLOutputGenerator extends SaikuOutputGeneratorImpl {
	
	
	@Inject
	public SaikuHTMLOutputGenerator(HookHandlerService hookHandler) {
		super(hookHandler);
	}

	@Override
	public CompiledRSSaikuReport exportReport(CellDataSet cellDataSet,
			CellSet cellset, List<SaikuDimensionSelection> filters,
			String outputFormat, ReportExecutionConfig... configs)
			throws ReportExecutorException {
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		pw.println("<table style=\"border: 1px solid grey\">");
		for(AbstractBaseCell[] r : cellDataSet.getCellSetHeaders()){
			pw.print("<tr>");
			for(AbstractBaseCell c : r){
				pw.print("<th>" + c + "</th>");
			}
			pw.print("</tr>");
		}

		for(AbstractBaseCell[] r : cellDataSet.getCellSetBody()){
			pw.println("<tr>");
			for(AbstractBaseCell c : r){
				if(c instanceof MemberCell){
					MemberCell m = (MemberCell) c;
					pw.print("<td>" +m.getFormattedValue() + "</td>");

					
				}else if(c instanceof DataCell){
					DataCell d = (DataCell) c;
					
					pw.print("<td>" +c.getFormattedValue() + " " + "</td>");
				}
				
			}
			pw.println("</tr>");
		}
		
		
		pw.println("</table>");

		return new CompiledHTMLSaikuReport(sw.getBuffer().toString() );
	}

	@Override
	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_HTML};
	}

	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledHTMLSaikuReport();
	}

}
