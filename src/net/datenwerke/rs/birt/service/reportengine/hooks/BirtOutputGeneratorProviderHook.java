package net.datenwerke.rs.birt.service.reportengine.hooks;

import java.util.Collection;

import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.birt.service.reportengine.output.generator.BirtOutputGenerator;
import net.datenwerke.rs.core.service.reportmanager.output.ReportOutputGeneratorProvider;

@HookConfig
public interface BirtOutputGeneratorProviderHook extends ReportOutputGeneratorProvider<BirtOutputGenerator> {

	public Collection<BirtOutputGenerator> provideGenerators();
}
