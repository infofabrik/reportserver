package net.datenwerke.rs.base.service.reportengines.table.output.object;

/**
 * 
 *
 */
public class CompiledPDFTableReport extends CompiledTableReport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7175341527659298964L;
	private byte[] report;

	public CompiledPDFTableReport() {
	}

	public CompiledPDFTableReport(byte[] cReport) {
		setReport(cReport);
	}

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

	@Override
	public boolean isStringReport() {
		return false;
	}
	
}
