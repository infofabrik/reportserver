package net.datenwerke.rs.ftp.service.ftp.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.dto.ScheduleAsFtpsFileInformation;
import net.datenwerke.rs.ftp.service.ftp.action.ScheduleAsFtpsFileAction;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpsDatasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsFtpsFileHooker implements ScheduleConfigProviderHook{
    
    private final Provider<ScheduleAsFtpsFileAction> actionProvider;
    private final Provider<DtoService> dtoServiceProvider;
    
    @Inject
    public ScheduleConfigAsFtpsFileHooker(
        DtoService dtoService,
        Provider<ScheduleAsFtpsFileAction> actionProvider,
        Provider<DtoService> dtoServiceProvider
        ) {
        
        /* store objects */
        this.actionProvider = actionProvider;
        this.dtoServiceProvider = dtoServiceProvider;
    }


    @Override
    public void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO)
            throws InvalidConfigurationException {
        ScheduleAsFtpsFileInformation info = scheduleDTO.getAdditionalInfo(ScheduleAsFtpsFileInformation.class);
        if(null == info)
            return;
        
        if (null == info.getFtpsDatasinkDto())
            throw new InvalidConfigurationException("No FTPS datasink specified");
        if(null == info.getName() || info.getName().trim().isEmpty())
            throw new InvalidConfigurationException("No name specified");
        if(null == info.getFolder() || info.getFolder().trim().isEmpty())
            throw new InvalidConfigurationException("No folder specified");
        
        ScheduleAsFtpsFileAction action = actionProvider.get();
        
        action.setName(info.getName());
        action.setFolder(info.getFolder());
        action.setFtpsDatasink((FtpsDatasink)dtoServiceProvider.get().loadPoso(info.getFtpsDatasinkDto()));
        
        try {
            job.addAction(action);
        } catch (ActionNotSupportedException e) {
            throw new InvalidConfigurationException(e);
        }
        
    }

    @Override
    public void adaptScheduleDefinition(ReportScheduleDefinition rsd, ReportExecuteJob job) throws ExpectedException {
        ScheduleAsFtpsFileAction action = job.getAction(ScheduleAsFtpsFileAction.class);
        if(null == action)
            return;
        
        ScheduleAsFtpsFileInformation info = new ScheduleAsFtpsFileInformation();
        
        info.setName(action.getName());
        info.setFolder(action.getFolder());
        info.setFtpsDatasinkDto((FtpsDatasinkDto)dtoServiceProvider.get().createDto(action.getFtpsDatasink()));
        
        if (null == info.getFtpsDatasinkDto())
            throw new IllegalArgumentException("No FTPS datasink specified");
        if(null == info.getName() || info.getName().trim().isEmpty())
            throw new IllegalArgumentException("No name specified");
        if(null == info.getFolder() || info.getFolder().trim().isEmpty())
            throw new IllegalArgumentException("No folder specified");
        
        rsd.addAdditionalInfo(info);
        
    }

}