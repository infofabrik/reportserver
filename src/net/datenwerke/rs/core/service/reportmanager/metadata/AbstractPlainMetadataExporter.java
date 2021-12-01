package net.datenwerke.rs.core.service.reportmanager.metadata;

import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.service.usermanager.entities.User;

abstract public class AbstractPlainMetadataExporter implements ReportMetadataExporter {

	protected static final String SEPARATOR = "-----------------------------------\n";
	
	protected final StringBuilder dataBuilder;
	
	public AbstractPlainMetadataExporter(){
		dataBuilder = new StringBuilder();
		init();
	}
	
	protected void init() {
		dataBuilder.append(SEPARATOR);
	}

	@Override
	public String[] getFormats() {
		return new String[]{ReportExecutorService.METADATA_FORMAT_PLAIN};
	}

	@Override
	public CompiledReportMetadata getMetadata() {
		return new PlainMetadata(dataBuilder.toString());
	}

	protected void beginSection(String header){
		dataBuilder.append('\n')
				.append(SEPARATOR)
				.append("++ ").append(header.toUpperCase()).append(" ++\n");
	
	}
	
	@Override
	public void beginParameterSection() {
		beginSection("Parameter");
	}
	
	@Override
	public void visitParameter(ParameterInstance instance, ParameterDefinition definition, User user) {
		if(definition.isHidden())
			return;
		
		dataBuilder.append(definition.getName())
					.append(instance.toInformationString(user))
				   .append('\n');
	}

	@Override
	public void visitUser(User user) {
	}
	
	@Override
	public void visitReport(Report report) {
		dataBuilder.append("Berichtsname: " + report.getName() + "\n");
		dataBuilder.append("Beschreibung: " + report.getDescription() + "\n");
	}
	
	@Override
	public void cleanUp(){}

}
