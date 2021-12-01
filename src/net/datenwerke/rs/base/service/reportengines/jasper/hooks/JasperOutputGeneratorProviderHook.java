package net.datenwerke.rs.base.service.reportengines.jasper.hooks;

import java.util.Collection;

import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.base.service.reportengines.jasper.output.generator.JasperOutputGenerator;
import net.datenwerke.rs.core.service.reportmanager.output.ReportOutputGeneratorProvider;

@HookConfig
public interface JasperOutputGeneratorProviderHook extends ReportOutputGeneratorProvider<JasperOutputGenerator> {

	public Collection<JasperOutputGenerator> provideGenerators();
}
