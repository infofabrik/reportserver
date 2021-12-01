package net.datenwerke.rs.core.service.reportmanager.hooks;

import java.util.Collections;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public abstract class ConfigureReportViaRequestAndLocationImpl implements
		ConfigureReportViaHistoryLocationHook,
		ConfigureReportViaHttpRequestHook {

	protected interface ParameterProvider{
		Enumeration<String> getParameterNames();
		String getParameter(String key);
	}
	
	@Override
	public final void adjustReport(Report report, final HttpServletRequest req) {
		adjustReport(report, new ParameterProvider() {
			
			@Override
			public Enumeration<String> getParameterNames() {
				return req.getParameterNames();
			}
			
			@Override
			public String getParameter(String key) {
				return req.getParameter(key);
			}
		});
	}

	@Override
	public void adjustReport(Report report, final HistoryLocation location) {
		adjustReport(report, new ParameterProvider() {
			
			@Override
			public Enumeration<String> getParameterNames() {
				return Collections.enumeration(location.getParameterNames());
			}
			
			@Override
			public String getParameter(String key) {
				return location.getParameterValue(key);
			}
		});
	}
	
	protected abstract void adjustReport(Report report, ParameterProvider parameterProvider);

}
