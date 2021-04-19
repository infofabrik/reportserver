package net.datenwerke.rs.core.service.reportmanager.output;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface ReportOutputGeneratorProvider<G extends ReportOutputGenerator> extends Hook {

	public Collection<G> provideGenerators();
}
