package net.datenwerke.rs.ftp.service.ftp.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.ScheduleAsFtpFileInformation;
import net.datenwerke.rs.ftp.service.ftp.action.ScheduleAsFtpFileAction;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsFtpFileHooker implements ScheduleConfigProviderHook {

	private final Provider<ScheduleAsFtpFileAction> actionProvider;
	private final Provider<DtoService> dtoServiceProvider;
	
	@Inject
	public ScheduleConfigAsFtpFileHooker(
		DtoService dtoService,
		Provider<ScheduleAsFtpFileAction> actionProvider,
		Provider<DtoService> dtoServiceProvider
		) {
		
		/* store objects */
		this.actionProvider = actionProvider;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	@Override
	public void adaptJob(ReportExecuteJob job,
			ReportScheduleDefinition scheduleDTO)
			throws InvalidConfigurationException {
		ScheduleAsFtpFileInformation info = scheduleDTO.getAdditionalInfo(ScheduleAsFtpFileInformation.class);
		if(null == info)
			return;
		
		if (null == info.getFtpDatasinkDto())
			throw new InvalidConfigurationException("No FTP datasink specified");
		if(null == info.getName() || info.getName().trim().isEmpty())
			throw new InvalidConfigurationException("No name specified");
		if(null == info.getFolder() || info.getFolder().trim().isEmpty())
			throw new InvalidConfigurationException("No folder specified");
		
		ScheduleAsFtpFileAction action = actionProvider.get();
		
		action.setName(info.getName());
		action.setFolder(info.getFolder());
		action.setFtpDatasink((FtpDatasink)dtoServiceProvider.get().loadPoso(info.getFtpDatasinkDto()));
		
		try {
			job.addAction(action);
		} catch (ActionNotSupportedException e) {
			throw new InvalidConfigurationException(e);
		}
	}

	@Override
	public void adaptScheduleDefinition(ReportScheduleDefinition rsd,
			ReportExecuteJob job) throws ExpectedException {
		ScheduleAsFtpFileAction action = job.getAction(ScheduleAsFtpFileAction.class);
		if(null == action)
			return;
		
		ScheduleAsFtpFileInformation info = new ScheduleAsFtpFileInformation();
		
		info.setName(action.getName());
		info.setFolder(action.getFolder());
		info.setFtpDatasinkDto((FtpDatasinkDto)dtoServiceProvider.get().createDto(action.getFtpDatasink()));
		
		if (null == info.getFtpDatasinkDto())
			throw new IllegalArgumentException("No FTP datasink specified");
		if(null == info.getName() || info.getName().trim().isEmpty())
			throw new IllegalArgumentException("No name specified");
		if(null == info.getFolder() || info.getFolder().trim().isEmpty())
			throw new IllegalArgumentException("No folder specified");
		
		rsd.addAdditionalInfo(info);
	}

}
