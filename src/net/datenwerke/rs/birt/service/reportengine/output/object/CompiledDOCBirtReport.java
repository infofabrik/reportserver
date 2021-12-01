package net.datenwerke.rs.birt.service.reportengine.output.object;

public class CompiledDOCBirtReport extends CompiledRSBirtReport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7901050004687112644L;
	
	private byte[] report;

	public byte[] getReport() {
		return report;
	}

	public void setReport(Object report) {
		try{
			this.report = (byte[]) report;
		} catch(ClassCastException e){
			IllegalArgumentException iae = new IllegalArgumentException("Expected byte array"); //$NON-NLS-1$
			iae.initCause(e);
			throw iae;
		}
	}
	
	public String getFileExtension() {
		return "doc"; //$NON-NLS-1$
	}

	public String getMimeType() {
		return "application/msword"; //$NON-NLS-1$
	}

}
