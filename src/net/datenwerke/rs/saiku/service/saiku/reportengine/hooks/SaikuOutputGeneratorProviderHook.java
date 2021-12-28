package net.datenwerke.rs.saiku.service.saiku.reportengine.hooks;

import java.util.Collection;

import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.core.service.reportmanager.output.ReportOutputGeneratorProvider;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator.SaikuOutputGenerator;

@HookConfig
public interface SaikuOutputGeneratorProviderHook extends ReportOutputGeneratorProvider<SaikuOutputGenerator> {

   public Collection<SaikuOutputGenerator> provideGenerators();
}
