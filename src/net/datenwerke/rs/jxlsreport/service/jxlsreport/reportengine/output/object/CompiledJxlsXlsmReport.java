package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.object;


/**
 * 
 *
 */
public class CompiledJxlsXlsmReport extends CompiledJxlsReport {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3201695582498172613L;
	
	public CompiledJxlsXlsmReport(byte[] report) {
		super(report);
	}
	
	@Override
	public String getFileExtension() {
		return "xlsm"; //$NON-NLS-1$
	}

	@Override
	public String getMimeType() {
		return "application/vnd.ms-excel.sheet.macroEnabled.12"; //$NON-NLS-1$
	}

}
