package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.hooks;

import java.util.Collection;

import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.core.service.reportmanager.output.ReportOutputGeneratorProvider;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator.JxlsOutputGenerator;

@HookConfig
public interface JxlsOutputGeneratorProviderHook extends ReportOutputGeneratorProvider<JxlsOutputGenerator> {
	
	public Collection<JxlsOutputGenerator> provideGenerators();
}
