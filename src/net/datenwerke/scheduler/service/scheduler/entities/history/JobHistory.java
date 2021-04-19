package net.datenwerke.scheduler.service.scheduler.entities.history;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;

@GenerateDto(
	dtoPackage="net.datenwerke.scheduler.client.scheduler.dto.history"
)
@Entity
@Table(name="SCHEDULER_JOB_HISTORY")
public class JobHistory {

	@ExposeToClient(view=DtoView.LIST)
	@JoinColumn(name="JOB_HISTORY")
	@EnclosedEntity
	@OneToMany(cascade=CascadeType.ALL)
	@OrderBy("start")
	private List<ExecutionLogEntry> executionLogEntries = new ArrayList<ExecutionLogEntry>();
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	public List<ExecutionLogEntry> getExecutionLogEntries() {
		return executionLogEntries;
	}

	public void setExecutionLogEntries(List<ExecutionLogEntry> executionLogEntries) {
		this.executionLogEntries = executionLogEntries;
	}

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

	public void addExecutionLogEntry(ExecutionLogEntry entry){
		this.executionLogEntries.add(entry);
	}

	public Collection<? extends ExecutionLogEntry> getExecutionLogEntriesInScheduledStartOrder() {
		List<ExecutionLogEntry> entries = new ArrayList<ExecutionLogEntry>(executionLogEntries);
		
		Collections.sort(entries);
		
		return entries;
	}
	
	
}
