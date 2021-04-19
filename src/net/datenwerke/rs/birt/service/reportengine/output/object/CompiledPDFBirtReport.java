package net.datenwerke.rs.birt.service.reportengine.output.object;

public class CompiledPDFBirtReport extends CompiledRSBirtReport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
		return "pdf"; //$NON-NLS-1$
	}

	public String getMimeType() {
		return "application/pdf"; //$NON-NLS-1$
	}

	
}
