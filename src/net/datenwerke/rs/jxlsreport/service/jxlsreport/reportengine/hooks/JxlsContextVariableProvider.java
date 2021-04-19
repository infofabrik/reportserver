package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.hooks;

import java.util.Map;

import org.jxls.common.Context;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;

@HookConfig
public class JxlsContextVariableProvider implements Hook {

	public void adaptContext(Context context) {
		
	}

	public void adaptLegacyContext(Map<String, Object> beans) {
		
	}

	public void finishedExport() {
		
	}

}