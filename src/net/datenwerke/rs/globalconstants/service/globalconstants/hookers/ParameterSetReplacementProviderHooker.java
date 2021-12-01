package net.datenwerke.rs.globalconstants.service.globalconstants.hookers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterSetReplacementProviderHook;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetReplacementProvider;
import net.datenwerke.rs.globalconstants.service.globalconstants.GlobalConstantParameterReplacementProvider;

public class ParameterSetReplacementProviderHooker implements
		ParameterSetReplacementProviderHook {

	private final GlobalConstantParameterReplacementProvider globalConstantProvider;
	
	@Inject
	public ParameterSetReplacementProviderHooker(
		GlobalConstantParameterReplacementProvider globalConstantProvider
		){
		
		this.globalConstantProvider = globalConstantProvider;
	}
	
	@Override
	public Collection<? extends ParameterSetReplacementProvider> getProviders() {
		Set<ParameterSetReplacementProvider> providers = new HashSet<ParameterSetReplacementProvider>();
		
		providers.add(globalConstantProvider);
		
		return providers;
	}

}
