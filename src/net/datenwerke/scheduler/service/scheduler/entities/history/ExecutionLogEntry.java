package net.datenwerke.scheduler.service.scheduler.entities.history;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;
import net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecutionMode;

import org.hibernate.annotations.Type;

@GenerateDto(
	dtoPackage="net.datenwerke.scheduler.client.scheduler.dto.history"
)
@Entity
@Table(name="SCHED_HIST_EXEC_ENTRY")
public class ExecutionLogEntry implements Comparable<ExecutionLogEntry> {

	@ExposeToClient(view=DtoView.LIST)
	private Date scheduledStart;
	
	@ExposeToClient(view=DtoView.LIST)
	private Date start = Calendar.getInstance().getTime();
	
	@ExposeToClient(view=DtoView.LIST)
	private Date end;
	
	@ExposeToClient(view=DtoView.LIST)
	private Outcome outcome = Outcome.EXECUTING;
	
	@ExposeToClient
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String badErrorDescription;
	
	@ExposeToClient(view=DtoView.LIST)
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	private String vetoExplanation;
	
	@ExposeToClient(view=DtoView.LIST)
	private VetoJobExecutionMode vetoMode;
	
	@ExposeToClient
	@EnclosedEntity
	@OneToOne(cascade=CascadeType.ALL)
	private JobEntry jobEntry;
	
	@ExposeToClient
	@EnclosedEntity
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="EXEC_ENTRY")
	private List<ActionEntry> actionEntries = new ArrayList<ActionEntry>();
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getEnd() {
		return end;
	}

	public void setStart(Date start) {
		if(null == start)
			start = Calendar.getInstance().getTime();
		this.start = start;
	}

	public Date getStart() {
		return start;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	public Outcome getOutcome() {
		return outcome;
	}

	public JobEntry getJobEntry() {
		return jobEntry;
	}

	public void setJobEntry(JobEntry jobEntry) {
		this.jobEntry = jobEntry;
	}

	public List<ActionEntry> getActionEntries() {
		return actionEntries;
	}

	public void setActionEntries(List<ActionEntry> actionEntries) {
		this.actionEntries = actionEntries;
	}

	public void addActionEntry(ActionEntry actionEntry) {
		actionEntries.add(actionEntry);
	}

	public String getVetoExplanation() {
		return vetoExplanation;
	}

	public void setVetoExplanation(String vetoExplanation) {
		this.vetoExplanation = vetoExplanation;
	}

	public VetoJobExecutionMode getVetoMode() {
		return vetoMode;
	}

	public void setVetoMode(VetoJobExecutionMode vetoMode) {
		this.vetoMode = vetoMode;
	}

	public void setBadErrorDescription(String badErrorDescription) {
		this.badErrorDescription = badErrorDescription;
	}

	public String getBadErrorDescription() {
		return badErrorDescription;
	}

	public void setScheduledStart(Date scheduledStart) {
		this.scheduledStart = scheduledStart;
	}

	public Date getScheduledStart() {
		return scheduledStart;
	}

	@Override
	public int compareTo(ExecutionLogEntry b) {
		if(b == null)
			return -1;
		
		Date adate = getScheduledStart() == null ? getStart() : getScheduledStart();
		Date bdate = b.getScheduledStart() == null ? b.getStart() : b.getScheduledStart();
		
		if(adate == null && bdate == null)
			return 0;
		if(adate != null)
			if(bdate == null)
				return -1;
			else
				return adate.compareTo(bdate);
		
		return 1;
	}
	
	
	
}
