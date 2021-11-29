package net.datenwerke.rs.ftp.service.ftp.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.datasinkmanager.DatasinkService;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkFilenameFolderConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.ftp.service.ftp.SftpService;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;

@Entity
@Table(name="SCHED_ACTION_AS_SFTP_FILE")
@Inheritance(strategy=InheritanceType.JOINED)
public class ScheduleAsSftpFileAction extends AbstractAction {

	@Transient @Inject private Provider<SimpleJuel> simpleJuelProvider;
	@Transient @Inject private SftpService sftpService;
	@Transient @Inject private DatasinkService datasinkService;
	
	@EnclosedEntity
	@OneToOne
	private SftpDatasink sftpDatasink;
	
	@Transient private Report report;
	
	@Transient private String filename;
	
	private String name;
	private String folder;
	
	private Boolean compressed = false;
	   
	public Boolean isCompressed() {
	   return compressed;
	}
	   
	public void setCompressed(Boolean compressed) {
	   this.compressed = compressed;
	}
	   
	@Transient
	@Inject
	private ZipUtilsService zipUtilsService;
	
	@Override
	public void execute(AbstractJob job) throws ActionExecutionException {
		if(! (job instanceof ReportExecuteJob))
			throw new ActionExecutionException("No idea what job that is");
		
		ReportExecuteJob rJob = (ReportExecuteJob) job;
		
		/* did everything go as planned ?*/
		if(null == rJob.getExecutedReport())
			return;
		
		if (! datasinkService.isEnabled(sftpService) || ! datasinkService.isSchedulingEnabled(sftpService))
			throw new ActionExecutionException("sftp scheduling is disabled");
		
		report = rJob.getReport();
		
		SimpleJuel juel = simpleJuelProvider.get();
		juel.addReplacement("now", new SimpleDateFormat("yyyyMMddhhmm").format(Calendar.getInstance().getTime()));
		filename = null == name ? "" : juel.parse(name);
		
		sendViaSFTPDatasink(rJob, filename);
		
		if(null == name || name.trim().isEmpty())
			throw new ActionExecutionException("name is empty");
		
		if(null == sftpDatasink)
			throw new ActionExecutionException("sftp datasink is empty");
		
		if(null == folder || folder.trim().isEmpty())
			throw new ActionExecutionException("folder is empty");
		
	}
	
	private void sendViaSFTPDatasink(ReportExecuteJob rJob, String filename) throws ActionExecutionException {
       if (compressed) {
          String filenameScheduling = filename += ".zip";
          ByteArrayOutputStream os = new ByteArrayOutputStream();
          Object reportObj = rJob.getExecutedReport().getReport();
          String reportFileExtension = rJob.getExecutedReport().getFileExtension();
          try {
             zipUtilsService.createZip(Collections
                   .singletonMap(rJob.getReport().getName().replace(":", "_").replace("/", "_").replace("\\", "_").replace(" ", "_")
                         + "." + reportFileExtension, reportObj),
                   os);
          } catch (IOException e) {
             throw new ActionExecutionException(e);
          }
          try {
             datasinkService.exportIntoDatasink(os.toByteArray(), sftpDatasink, sftpService, 
                   new DatasinkFilenameFolderConfig() {

                @Override
                public String getFolder() {
                   return folder;
                }

                @Override
                public String getFilename() {
                   return filenameScheduling;
                }
             });
          } catch (Exception e) {
             throw new ActionExecutionException("report could not be sent to SFTP", e);
          }
       } else {
          String filenameScheduling = filename += "." + rJob.getExecutedReport().getFileExtension();
          try {
             datasinkService.exportIntoDatasink(rJob.getExecutedReport().getReport(), sftpDatasink, sftpService,
                   new DatasinkFilenameFolderConfig() {

                      @Override
                      public String getFolder() {
                         return folder;
                      }

                      @Override
                      public String getFilename() {
                         return filenameScheduling;
                      }
                   });
         } catch (Exception e) {
             throw new ActionExecutionException("report could not be sent to SFTP", e);
         }
       }
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public Report getReport() {
		return report;
	}
	
	public SftpDatasink getSftpDatasink() {
		return sftpDatasink;
	}

	public void setSftpDatasink(SftpDatasink sftpDatasink) {
		this.sftpDatasink = sftpDatasink;
	}
}
