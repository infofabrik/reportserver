package net.datenwerke.rs.base.service.parameterreplacements.hookers;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import net.datenwerke.rs.base.service.parameterreplacements.provider.LocaleInfoParameterReplacement;
import net.datenwerke.rs.base.service.parameterreplacements.provider.ReportParameterReplacement;
import net.datenwerke.rs.base.service.parameterreplacements.provider.UserParameterReplacement;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterSetReplacementProviderHook;
import net.datenwerke.rs.core.service.reportmanager.metadata.ReportMetadataParameterReplacement;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetReplacementProvider;


public class ParameterSetReplacementProviderHooker implements
		ParameterSetReplacementProviderHook {

	private final UserParameterReplacement userParameterProvider;
	private final ReportParameterReplacement reportParameterProvider;
	private final ReportMetadataParameterReplacement reportMetadataParameterReplacement;
	private final LocaleInfoParameterReplacement localeInfoParameterReplacement;
	
	@Inject
	public ParameterSetReplacementProviderHooker(
		UserParameterReplacement userParameterProvider,
		ReportParameterReplacement reportParameterProvider,
		ReportMetadataParameterReplacement reportMetadataParameterReplacement,
		LocaleInfoParameterReplacement localeInfoParameterReplacement
		){
		
		this.userParameterProvider = userParameterProvider;
		this.reportParameterProvider = reportParameterProvider;
		this.reportMetadataParameterReplacement = reportMetadataParameterReplacement;
		this.localeInfoParameterReplacement = localeInfoParameterReplacement;
	}
	
	@Override
	public Collection<? extends ParameterSetReplacementProvider> getProviders() {
		Set<ParameterSetReplacementProvider> providers = new HashSet<ParameterSetReplacementProvider>();
		
		providers.add(userParameterProvider);
		providers.add(reportParameterProvider);
		providers.add(reportMetadataParameterReplacement);
		providers.add(localeInfoParameterReplacement);
		
		return providers;
	}

}
