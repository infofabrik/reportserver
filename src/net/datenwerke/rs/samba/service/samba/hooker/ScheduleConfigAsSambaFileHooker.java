package net.datenwerke.rs.samba.service.samba.hooker;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto;
import net.datenwerke.rs.samba.client.samba.dto.ScheduleAsSambaFileInformation;
import net.datenwerke.rs.samba.service.samba.action.ScheduleAsSambaFileAction;
import net.datenwerke.rs.samba.service.samba.definitions.SambaDatasink;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;

public class ScheduleConfigAsSambaFileHooker implements ScheduleConfigProviderHook {

    private final Provider<ScheduleAsSambaFileAction> actionProvider;
    private final Provider<DtoService> dtoServiceProvider;
    
    @Inject
    public ScheduleConfigAsSambaFileHooker(
        DtoService dtoService,
        Provider<ScheduleAsSambaFileAction> actionProvider,
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
        ScheduleAsSambaFileInformation info = scheduleDTO.getAdditionalInfo(ScheduleAsSambaFileInformation.class);
        if(null == info)
            return;
        
        if (null == info.getSambaDatasinkDto())
            throw new InvalidConfigurationException("No Samba datasink specified");
        if(null == info.getName() || info.getName().trim().isEmpty())
            throw new InvalidConfigurationException("No name specified");
        if(null == info.getFolder() || info.getFolder().trim().isEmpty())
            throw new InvalidConfigurationException("No folder specified");
        
        ScheduleAsSambaFileAction action = actionProvider.get();
        
        action.setName(info.getName());
        action.setFolder(info.getFolder());
        action.setSambaDatasink((SambaDatasink)dtoServiceProvider.get().loadPoso(info.getSambaDatasinkDto()));
        
        try {
            job.addAction(action);
        } catch (ActionNotSupportedException e) {
            throw new InvalidConfigurationException(e);
        }
    }

    @Override
    public void adaptScheduleDefinition(ReportScheduleDefinition rsd,
            ReportExecuteJob job) throws ExpectedException {
        ScheduleAsSambaFileAction action = job.getAction(ScheduleAsSambaFileAction.class);
        if(null == action)
            return;
        
        ScheduleAsSambaFileInformation info = new ScheduleAsSambaFileInformation();
        
        info.setName(action.getName());
        info.setFolder(action.getFolder());
        info.setSambaDatasinkDto((SambaDatasinkDto)dtoServiceProvider.get().createDto(action.getSambaDatasink()));
        
        if (null == info.getSambaDatasinkDto())
            throw new IllegalArgumentException("No Samba datasink specified");
        if(null == info.getName() || info.getName().trim().isEmpty())
            throw new IllegalArgumentException("No name specified");
        if(null == info.getFolder() || info.getFolder().trim().isEmpty())
            throw new IllegalArgumentException("No folder specified");
        
        rsd.addAdditionalInfo(info);
    }

}