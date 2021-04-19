package net.datenwerke.rs.core.client.reportexecutor;

import net.datenwerke.rs.core.client.reportexecutor.variantstorer.VariantStorerConfig;

public interface ExecuteReportConfiguration {

	public VariantStorerConfig getVariantStorerConfig();
	
	public String getViewId();
	
	public boolean handleError(Throwable t);

	public boolean acceptView(String viewId);
	
	String getUrlParameters();
}
