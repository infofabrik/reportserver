package net.datenwerke.rs.base.service.reportengines.jasper.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface JasperReportPostCompileHook extends Hook {

   public void postprocessReport(net.sf.jasperreports.engine.JasperReport jasperReport);

}
