package net.datenwerke.security.service.authenticator.hooks;

import java.util.LinkedHashSet;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.security.service.authenticator.ReportServerPAM;

@HookConfig
public interface PAMHook extends Hook {
	
	public void beforeStaticPamConfig(LinkedHashSet<ReportServerPAM> pams);
	public void afterStaticPamConfig(LinkedHashSet<ReportServerPAM> pams);
	
}
