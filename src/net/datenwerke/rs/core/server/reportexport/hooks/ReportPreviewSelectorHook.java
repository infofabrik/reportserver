package net.datenwerke.rs.core.server.reportexport.hooks;

import javax.servlet.http.HttpServletRequest;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface ReportPreviewSelectorHook extends Hook {

	public String postprocessPreviewSelection(String usrVal, String cfgVal, String autoVal, HttpServletRequest request, String res);
}
