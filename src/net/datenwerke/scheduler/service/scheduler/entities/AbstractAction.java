package net.datenwerke.scheduler.service.scheduler.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionExecutionException;
import net.datenwerke.scheduler.service.scheduler.hooks.MonitorActionExecution;

import com.google.inject.Inject;

@Entity
@Table(name="SCHED_ACTION")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class AbstractAction {

	@Transient @Inject protected HookHandlerService hookHandler;
	
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
	
	public void execute(AbstractJob abstractJob) throws ActionExecutionException {
		List<MonitorActionExecution> monitors = hookHandler.getHookers(MonitorActionExecution.class);
		try{
			for(MonitorActionExecution monitor : monitors)
				monitor.notifyOfExecution(this);

			doExecute(abstractJob);
			
			for(MonitorActionExecution monitor : monitors)
				monitor.actionExecutedSuccessfully(this);

		} catch(RuntimeException e){
			for(MonitorActionExecution monitor : monitors)
				monitor.actionExecutionFailed(this, e);

			throw e;
		} catch(ActionExecutionException e){
			for(MonitorActionExecution monitor : monitors)
				monitor.actionExecutionFailed(this, e);

			throw e;
		}
	}
	
	protected void doExecute(AbstractJob abstractJob) throws ActionExecutionException {
	}

	public boolean consumes(AbstractJob job){
		return true;
	}
	
	@Override
	public int hashCode() {
		if(null == id)
			return super.hashCode();
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
    	if(! (obj instanceof AbstractAction))
    		return false;
    	
    	/* cast object */
    	AbstractAction job = null;
    	try {
    		job = (AbstractAction) obj;
    	} catch(ClassCastException e){
    		return false;
    	}
    	
    	/* test id */
    	if(null == getId() && null != job.getId())
    		return false;
    	if(null != getId() && ! getId().equals(job.getId()))
    		return false;
    	
    	return true;
	}

	public void adjustActionEntryForFailure(ActionEntry actionEntry) {
		actionEntry.setActionName(getClass().getSimpleName());
	}

	public void adjustActionEntryForSuccess(ActionEntry actionEntry) {
		actionEntry.setActionName(getClass().getSimpleName());
	}
}
