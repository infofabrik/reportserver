package net.datenwerke.rs.birt.service.reportengine.output.object;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

public abstract class CompiledRSBirtReport implements CompiledReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8189077879349881158L;

	
	@Override
	public boolean hasData() {
		return true;
	}
	
	@Override
	public boolean isStringReport() {
		return false;
	}
}
