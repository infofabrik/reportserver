package net.datenwerke.scheduler.service.scheduler.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

import com.google.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.scheduler.service.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobHistory;
import net.datenwerke.scheduler.service.scheduler.exceptions.ActionNotSupportedException;
import net.datenwerke.scheduler.service.scheduler.exceptions.JobExecutionException;
import net.datenwerke.scheduler.service.scheduler.hooks.MonitorJobExecutionHook;

@Entity
@Table(name = "SCHED_JOB")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractJob {

   @Transient
   @Inject
   protected HookHandlerService hookHandler;

   @EnclosedEntity
   @OneToOne(cascade = CascadeType.ALL)
   private AbstractTrigger trigger;

   @JoinTable(name = "SCHED_JOB_2_ACTIONS")
   @EnclosedEntity
   @OneToMany(cascade = CascadeType.ALL)
   private List<AbstractAction> actions = new ArrayList<AbstractAction>();

   private Date createdOn = new Date();

   @Column(length = 128)
   private String title;

   @Lob
   @Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
   private String description;

   @EnclosedEntity
   @OneToOne(cascade = CascadeType.ALL)
   private JobHistory history = new JobHistory();

   @EnclosedEntity
   private JobExecutionStatus executionStatus = JobExecutionStatus.INACTIVE;

   private Outcome lastOutcome = null;

   @OneToOne(cascade = CascadeType.ALL)
   private AbstractJob linkToPrevious;

   @Version
   private Long version;

   @ExposeToClient(id = true)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
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

   public void setTrigger(AbstractTrigger trigger) {
      this.trigger = trigger;
   }

   public AbstractTrigger getTrigger() {
      return trigger;
   }

   public void setActions(List<AbstractAction> actions) throws ActionNotSupportedException {
      if (null != actions) {
         for (AbstractAction action : actions)
            if (!action.consumes(this))
               throw new ActionNotSupportedException(this, action);
      } else
         actions = new ArrayList<AbstractAction>();
      this.actions = actions;
   }

   public void addAction(AbstractAction action) throws ActionNotSupportedException {
      if (null == action)
         return;
      if (!action.consumes(this))
         throw new ActionNotSupportedException(this, action);

      this.actions.add(action);
   }

   public List<AbstractAction> getActions() {
      return actions;
   }

   public <A extends AbstractAction> A getAction(Class<A> actionType) {
      if (null == actions || actions.isEmpty())
         return null;
      for (AbstractAction action : actions)
         if (actionType.isAssignableFrom(action.getClass()))
            return (A) action;
      return null;
   }

   public void setCreatedOn(Date createdOn) {
      this.createdOn = createdOn;
   }

   public Date getCreatedOn() {
      return createdOn;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getTitle() {
      return title;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getDescription() {
      return description;
   }

   public void setHistory(JobHistory history) {
      this.history = history;
   }

   public JobHistory getHistory() {
      return history;
   }

   public void setExecutionStatus(JobExecutionStatus executionStatus) {
      this.executionStatus = executionStatus;
   }

   public JobExecutionStatus getExecutionStatus() {
      return executionStatus;
   }

   public void execute() throws JobExecutionException {
      List<MonitorJobExecutionHook> monitors = hookHandler.getHookers(MonitorJobExecutionHook.class);
      try {
         for (MonitorJobExecutionHook monitor : monitors)
            monitor.notifyOfExecution(this);

         doExecute();

         for (MonitorJobExecutionHook monitor : monitors)
            monitor.jobExecutedSuccessfully(this);
      } catch (JobExecutionException e) {
         for (MonitorJobExecutionHook monitor : monitors)
            monitor.jobExecutionFailed(this, e);

         throw e;
      } catch (RuntimeException e) {
         for (MonitorJobExecutionHook monitor : monitors)
            monitor.jobExecutionFailed(this, e);
         throw e;
      }
   };

   protected void doExecute() throws JobExecutionException {

   }

   public void hasBeenScheduled() {

   }

   public Date getLastExecution() {
      if (null == history)
         return null;
      if (null == history.getExecutionLogEntries() || history.getExecutionLogEntries().isEmpty())
         return null;
      return history.getExecutionLogEntries().get(history.getExecutionLogEntries().size() - 1).getStart();
   }

   @Override
   public int hashCode() {
      if (null == id)
         return super.hashCode();
      return id.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof AbstractJob))
         return false;

      /* cast object */
      AbstractJob job = null;
      try {
         job = (AbstractJob) obj;
      } catch (ClassCastException e) {
         return false;
      }

      /* test id */
      if (null == getId() && null != job.getId())
         return false;
      if (null != getId() && !getId().equals(job.getId()))
         return false;

      return true;
   }

   public void adjustJobEntryForSuccess(JobEntry jobEntry) {
      lastOutcome = Outcome.SUCCESS;
   }

   public void adjustJobEntryForFailure(JobEntry jobEntry) {
      lastOutcome = Outcome.FAILURE;
   }

   public void setLinkToPrevious(AbstractJob linkToPrevious) {
      this.linkToPrevious = linkToPrevious;
   }

   public AbstractJob getLinkToPrevious() {
      return linkToPrevious;
   }

   public ExecutionLogEntry createMissedTriggerHistoryEntry(Date startTime) {
      ExecutionLogEntry entry = new ExecutionLogEntry();
      entry.setOutcome(Outcome.FAILURE);
      entry.setScheduledStart(startTime);
      entry.setBadErrorDescription(SchedulerMessages.INSTANCE.missedFireTime());

      return entry;
   }

   public boolean isActive() {
      return !getTrigger().isDone();
   }

   public Outcome getLastOutcome() {
      return lastOutcome;
   }

   public void setLastOutcome(Outcome lastOutcome) {
      this.lastOutcome = lastOutcome;
   }

   public abstract void copyTransientFieldsFrom(AbstractJob job);

}
