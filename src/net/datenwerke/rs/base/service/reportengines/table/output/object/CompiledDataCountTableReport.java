package net.datenwerke.rs.base.service.reportengines.table.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

import org.apache.commons.lang3.NotImplementedException;

/**
 * 
 *
 */
public class CompiledDataCountTableReport extends CompiledTableReport implements CompiledReport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8668286824874533411L;
	
	final private int report;
	private long executeDuration;
	
	public CompiledDataCountTableReport(){
		report = -1;
	}
	
	public CompiledDataCountTableReport(int report, long executeDuration) {
		this.report = report;
		this.executeDuration = executeDuration;
	}

	public Integer getReport() {
		return report;
	}
	
	public int getDataCount(){
		return report;
	}
	
	public long getExecuteDuration(){
		return executeDuration;
	}

	public String getFileExtension() {
		throw new NotImplementedException("not implemented");
	}

	public String getMimeType() {
		throw new NotImplementedException("not implemented");
	}
	
	@Override
	public boolean isStringReport() {
		return false;
	}
}
