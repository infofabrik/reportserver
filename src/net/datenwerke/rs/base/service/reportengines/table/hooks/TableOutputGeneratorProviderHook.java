package net.datenwerke.rs.base.service.reportengines.table.hooks;

import java.util.Collection;

import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.TableOutputGenerator;
import net.datenwerke.rs.core.service.reportmanager.output.ReportOutputGeneratorProvider;

@HookConfig
public interface TableOutputGeneratorProviderHook extends ReportOutputGeneratorProvider<TableOutputGenerator> {

   public Collection<TableOutputGenerator> provideGenerators();
}
