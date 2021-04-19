package net.datenwerke.rs.core.service.reportmanager.hooks;

import java.util.Collection;

import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.security.service.usermanager.entities.User;

public interface ReportExecutorExecuteAsHooker extends Hook {

	public interface ExecuteConfig{
		public String getFormat();
		public Collection<ReportExecutionConfig> getConfig();
	};
	
	boolean consumes(ReportDto report, String format);
	
	ExecuteConfig getConfig(ReportDto report, User currentUser, String format, DwModel config);

	DwModel adjustResult(ReportDto reportDto, User currentUser, Collection<ReportExecutionConfig> config, Object res);

	ReportDto adjustReport(ReportDto report, User currentUser, String format, Collection<ReportExecutionConfig> config);
}
