package net.datenwerke.rs.core.service.reportmanager.hooks;

import java.util.Collection;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetReplacementProvider;

public interface ParameterSetReplacementProviderHook extends Hook {

	Collection<? extends ParameterSetReplacementProvider> getProviders();

}
