package net.datenwerke.scheduler.service.scheduler.entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Version;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;

@Entity
@Table(name = "SCHED_TRIGGER")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractTrigger {

   protected int nrOfSuccessfulExecutions = 0;
   protected int nrOfVetoedExecutions = 0;
   protected int nrOfFailedExecutions = 0;

   protected Date firstFireTime;

   protected Date nextScheduledFireTime;

   protected boolean executeOnce;

   @Version
   protected Long version;

   @ExposeToClient(id = true)
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   protected Long id;

   private MisfireInstruction misfireInstruction = MisfireInstruction.EXECUTE_ONCE;

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

   public int getNrOfSuccessfulExecutions() {
      return nrOfSuccessfulExecutions;
   }

   public void setNrOfSuccessfulExecutions(int nrOfSuccessfulExecutions) {
      this.nrOfSuccessfulExecutions = nrOfSuccessfulExecutions;
   }

   public int getNrOfFailedExecutions() {
      return nrOfFailedExecutions;
   }

   public void setNrOfFailedExecutions(int nrOfFailedExecutions) {
      this.nrOfFailedExecutions = nrOfFailedExecutions;
   }

   public void setFirstFireTime(Date firstFireTime) {
      this.firstFireTime = firstFireTime;
   }

   public Date getFirstFireTime() {
      return firstFireTime;
   }

   public boolean isDone() {
      return null == nextScheduledFireTime;
   }

   public void setDone() {
      nextScheduledFireTime = null;
   }

   public boolean confirmExecution(AbstractJob job) {
      return true;
   }

   public void setNextScheduledFireTime(Date nextScheduledFireTime) {
      this.nextScheduledFireTime = nextScheduledFireTime;
   }

   public Date getNextScheduledFireTime() {
      return nextScheduledFireTime;
   }

   public List<ExecutionLogEntry> updateStateAfterSuccessfulExecution(AbstractJob job) {
      setNrOfSuccessfulExecutions(getNrOfSuccessfulExecutions() + 1);

      if (executeOnce)
         executeOnce = false;
      else
         return updateFireTime(job, nextScheduledFireTime);

      return Collections.emptyList();
   }

   public List<ExecutionLogEntry> updateStateAfterFailedExecution(AbstractJob job) {
      setNrOfFailedExecutions(getNrOfFailedExecutions() + 1);

      if (executeOnce)
         executeOnce = false;
      else
         return updateFireTime(job, nextScheduledFireTime);

      return Collections.emptyList();
   }

   public void updateStateAfterVetoedExecution(AbstractJob job, Date nextFireTime) {
      setNrOfVetoedExecutions(getNrOfVetoedExecutions() + 1);

      if (executeOnce)
         executeOnce = false;
      else
         setNextScheduledFireTime(nextFireTime);
   }

   protected List<ExecutionLogEntry> updateFireTime(AbstractJob job, Date lastFireTime) {
      if (null == lastFireTime)
         return Collections.emptyList();

      Date nextFireTime = computeNextFireTime(lastFireTime);
      if (null == nextFireTime) {
         setNextScheduledFireTime(nextFireTime);
         return Collections.emptyList();
      }

      switch (misfireInstruction) {
      case EXECUTE_ONCE:
         Date now = new GregorianCalendar().getTime();
         if (nextFireTime.after(now))
            setNextScheduledFireTime(nextFireTime);
         else {
            List<ExecutionLogEntry> entryList = new ArrayList<ExecutionLogEntry>();

            while (null != nextFireTime && !nextFireTime.after(now)) {
               setNrOfFailedExecutions(getNrOfFailedExecutions() + 1);
               entryList.add(job.createMissedTriggerHistoryEntry(nextFireTime));

               Date newFireTime = computeNextFireTime(nextFireTime);
               if (null != newFireTime && (newFireTime.equals(nextFireTime) || newFireTime.before(nextFireTime)))
                  throw new IllegalStateException("The trigger does not work correctly");

               nextFireTime = newFireTime;
            }
            setNextScheduledFireTime(nextFireTime);

            return entryList;
         }
         break;
      case EXECUTE_ALL:
         if (nextFireTime.equals(lastFireTime))
            throw new IllegalStateException("The trigger does not work correctly");
         setNextScheduledFireTime(nextFireTime);
         break;
      }

      return Collections.emptyList();
   }

   public boolean isInitialized() {
      return null != firstFireTime;
   }

   public void initialize() {
      if (isInitialized())
         throw new IllegalStateException("Trigger has already been initialized");

      setFirstFireTime(computeFirstFireTime());

      Calendar next = new GregorianCalendar();
      next.setTime(firstFireTime);

      Calendar now = new GregorianCalendar();
      while (next.before(now)) {
         Date nextTime = computeNextFireTime(next.getTime());
         if (nextTime != null)
            next.setTime(nextTime);
         else
            return;
      }

      setNextScheduledFireTime(next.getTime());
   }

   public abstract Date computeFirstFireTime();

   public abstract Date computeNextFireTime(Date lastFireTime);

   @Override
   public int hashCode() {
      if (null == id)
         return super.hashCode();
      return id.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof AbstractTrigger))
         return false;

      /* cast object */
      AbstractTrigger job = null;
      try {
         job = (AbstractTrigger) obj;
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

   public void setNrOfVetoedExecutions(int nrOfVetoedExecutions) {
      this.nrOfVetoedExecutions = nrOfVetoedExecutions;
   }

   public int getNrOfVetoedExecutions() {
      return nrOfVetoedExecutions;
   }

   public void setExecuteOnce(boolean executeOnce) {
      this.executeOnce = executeOnce;
   }

   public boolean isExecuteOnce() {
      return executeOnce;
   }

   public void setMisfireInstruction(MisfireInstruction misfireInstruction) {
      this.misfireInstruction = misfireInstruction;
   }

   public MisfireInstruction getMisfireInstruction() {
      return misfireInstruction;
   }

   public List<Date> getNextScheduleTimes(int times) {
      List<Date> dates = new ArrayList<Date>();

      Date current = getNextScheduledFireTime();
      while (null != current && dates.size() < times) {
         dates.add(current);
         current = computeNextFireTime(current);
      }

      return dates;
   }

}
