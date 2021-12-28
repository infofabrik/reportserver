package net.datenwerke.rs.core.service.reportmanager.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

@HookConfig
public interface VariantToBeStoredHook extends Hook {

   public void variantToBeStored(Report report, String executerToken);

}
