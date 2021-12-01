package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;

public class CompiledPdfReport implements CompiledReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2495473276012232477L;

	private final Object report;

	public CompiledPdfReport(Object report) {
		this.report = report;
	}

	public Object getReport(){
		return report;
	}
	
	public String getMimeType(){
		return "application/pdf";
	}
	
	public String getFileExtension(){
		return "pdf";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((report == null) ? 0 : report.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CompiledPdfReport)) {
			return false;
		}
		CompiledPdfReport other = (CompiledPdfReport) obj;
		if (report == null) {
			if (other.report != null) {
				return false;
			}
		} else if (!report.equals(other.report)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean hasData() {
		return report != null;
	}
	
	@Override
	public boolean isStringReport() {
		return false;
	}
}
