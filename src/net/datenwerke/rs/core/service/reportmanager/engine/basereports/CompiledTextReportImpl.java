package net.datenwerke.rs.core.service.reportmanager.engine.basereports;

public class CompiledTextReportImpl implements CompiledTextReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8228843755262198294L;
	
	private final String report;
	
	public CompiledTextReportImpl(String report) {
		this.report = report;
	}

	@Override
	public String getMimeType() {
		return "text/plain";
	}

	@Override
	public String getFileExtension() {
		return "txt";
	}

	@Override
	public String getReport() {
		return report;
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
		if (!(obj instanceof CompiledTextReportImpl)) {
			return false;
		}
		CompiledTextReportImpl other = (CompiledTextReportImpl) obj;
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
		return true;
	}
}
