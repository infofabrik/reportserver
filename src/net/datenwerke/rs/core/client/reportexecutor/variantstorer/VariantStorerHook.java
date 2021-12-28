package net.datenwerke.rs.core.client.reportexecutor.variantstorer;

import net.datenwerke.rs.core.client.reportexecutor.events.ExecutorEventHandler;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportExecutorViewToolbarHook;

/**
 * 
 *
 */
public interface VariantStorerHook extends ReportExecutorViewToolbarHook {

   void setEventHandler(ExecutorEventHandler eventHandler);

   void setConfig(VariantStorerConfig variantStorerConfig);

}
