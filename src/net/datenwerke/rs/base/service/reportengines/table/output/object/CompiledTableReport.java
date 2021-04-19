package net.datenwerke.rs.base.service.reportengines.table.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

public abstract class CompiledTableReport implements CompiledReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3942656458077955178L;
	
	private boolean hasData;

	@Override
	public boolean hasData() {
		return hasData;
	}
	
	public void setHasData(boolean hasData) {
		this.hasData = hasData;
	}
	
	@Override
	public boolean isStringReport() {
		return true;
	}
}
