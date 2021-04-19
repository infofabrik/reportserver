package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.object;


/**
 * 
 *
 */
public class CompiledJxlsXlsxReport extends CompiledJxlsReport {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6866651621046451997L;

	public CompiledJxlsXlsxReport(byte[] report) {
		super(report);
	}

	@Override
	public String getFileExtension() {
		return "xlsx"; //$NON-NLS-1$
	}

	@Override
	public String getMimeType() {
		return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"; //$NON-NLS-1$
	}
	

}
