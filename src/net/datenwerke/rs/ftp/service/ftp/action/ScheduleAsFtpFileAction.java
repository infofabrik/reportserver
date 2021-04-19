package net.datenwerke.rs.ftp.service.ftp.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.ftp.service.ftp.FtpService;
import net.datenwerke.rs.ftp.service.ftp.definitions.FtpDatasink;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractAction;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;

@Entity
@Table(name="SCHED_ACTION_AS_FTP_FILE")
@Inheritance(strategy=InheritanceType.JOINED)
public class ScheduleAsFtpFileAction extends AbstractAction {

	@Transient @Inject private Provider<SimpleJuel> simpleJuelProvider;
	@Transient @Inject private FtpService ftpService;
	
	@EnclosedEntity
	@OneToOne
	private FtpDatasink ftpDatasink;
	
	@Transient private Report report;
	
	@Transient private String filename;
	
	private String name;
	private String folder;
	
	@Override
	public void execute(AbstractJob job) throws ActionExecutionException {
		if(! (job instanceof ReportExecuteJob))
			throw new ActionExecutionException("No idea what job that is");
		
		ReportExecuteJob rJob = (ReportExecuteJob) job;
		
		/* did everything go as planned ?*/
		if(null == rJob.getExecutedReport())
			return;
		
		if (! ftpService.isFtpEnabled() || ! ftpService.isFtpSchedulingEnabled())
			throw new ActionExecutionException("ftp scheduling is disabled");
		
		CompiledReport compiledReport = rJob.getExecutedReport();
		report = rJob.getReport();
		
		SimpleJuel juel = simpleJuelProvider.get();
		juel.addReplacement("now", new SimpleDateFormat("yyyyMMddhhmm").format(Calendar.getInstance().getTime()));
		filename = null == name ? "" : juel.parse(name);
		
		filename += "." + compiledReport.getFileExtension();
		
		if(null == name || name.trim().isEmpty())
			throw new ActionExecutionException("name is empty");
		
		if(null == ftpDatasink)
			throw new ActionExecutionException("ftp datasink is empty");
		
		if(null == folder || folder.trim().isEmpty())
			throw new ActionExecutionException("folder is empty");
		
		try {
			ftpService.sendToFtpServer(compiledReport.getReport(), ftpDatasink, filename, folder);
		} catch (Exception e) {
			throw new ActionExecutionException("report could not be sent to FTP", e);
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
	
	public FtpDatasink getFtpDatasink() {
		return ftpDatasink;
	}

	public void setFtpDatasink(FtpDatasink ftpDatasink) {
		this.ftpDatasink = ftpDatasink;
	}
}
