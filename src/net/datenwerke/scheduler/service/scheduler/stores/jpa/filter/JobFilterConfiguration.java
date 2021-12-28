package net.datenwerke.scheduler.service.scheduler.stores.jpa.filter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob_;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob__;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger_;
import net.datenwerke.scheduler.service.scheduler.entities.JobExecutionStatus;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry_;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobHistory_;

@GenerateDto(dtoPackage = "net.datenwerke.scheduler.client.scheduler.dto.filter")
public class JobFilterConfiguration implements JobFilterCriteria {

   @ExposeToClient
   private String sortField;

   @ExposeToClient
   private Order order;

   @ExposeToClient
   private int offset = 0;

   @ExposeToClient
   private int limit = -1;

   @ExposeToClient
   private boolean active = true;

   @ExposeToClient
   private boolean inActive = false;

   @ExposeToClient
   private JobExecutionStatus executionStatus = null;

   @ExposeToClient
   private Outcome lastOutcome = null;

   @ExposeToClient
   private String jobId = null;

   @ExposeToClient
   private String jobTitle = null;

   private Class<? extends AbstractJob> jobType = AbstractJob.class;

   public String getSortField() {
      return sortField;
   }

   public void setSortField(String sortField) {
      this.sortField = sortField;
   }

   public Order getOrder() {
      return order;
   }

   public void setOrder(Order order) {
      this.order = order;
   }

   public int getOffset() {
      return offset;
   }

   public void setOffset(int offset) {
      this.offset = offset;
   }

   public int getLimit() {
      return limit;
   }

   public void setLimit(int limit) {
      this.limit = limit;
   }

   public void setInActive(boolean inActive) {
      this.inActive = inActive;
   }

   public boolean isInActive() {
      return inActive;
   }

   public void setActive(boolean active) {
      this.active = active;
   }

   public boolean isActive() {
      return active;
   }

   public Class<? extends AbstractJob> getJobType() {
      return jobType;
   }

   public void setJobType(Class<? extends AbstractJob> jobType) {
      this.jobType = jobType;
   }

   public String getJobId() {
      return jobId;
   }

   public void setJobId(String jobId) {
      this.jobId = jobId;
   }

   public String getJobTitle() {
      return jobTitle;
   }

   public void setJobTitle(String jobTitle) {
      this.jobTitle = jobTitle;
   }

   public boolean validateSortField(String sortField) {
      if (null == sortField)
         return false;

      if ("jobId".equals(sortField))
         return true;
      if ("lastScheduled".equals(sortField))
         return true;
      if ("nextScheduled".equals(sortField))
         return true;
      return false;
   }

   public Expression<?> transformSortField(String sortField, CriteriaBuilder builder, CriteriaQuery<AbstractJob> cQuery,
         Root<? extends AbstractJob> root) {
      if ("jobId".equals(sortField))
         return root.get(AbstractJob_.id);
      if ("lastScheduled".equals(sortField))
         return root.join(AbstractJob_.history).join(JobHistory_.executionLogEntries, JoinType.LEFT)
               .get(ExecutionLogEntry_.start);
      if ("nextScheduled".equals(sortField))
         return root.join(AbstractJob_.trigger, JoinType.LEFT).get(AbstractTrigger_.nextScheduledFireTime);
      return null;
   }

   @Override
   public List<Predicate> prepareCriteriaQuery(CriteriaBuilder builder, CriteriaQuery<?> cQuery,
         Root<? extends AbstractJob> root) {
      List<Predicate> predicates = new ArrayList<Predicate>();

      if (null != executionStatus)
         predicates.add(root.get(AbstractJob_.executionStatus).in(executionStatus));

      /* join trigger */
      if (!active || !inActive) {
         Join<AbstractJob, AbstractTrigger> trigger = root.join(AbstractJob__.trigger);

         if (!inActive)
            predicates.add(trigger.get(AbstractTrigger_.nextScheduledFireTime).isNotNull());
         else
            predicates.add(trigger.get(AbstractTrigger_.nextScheduledFireTime).isNull());
      }

      if (null != lastOutcome)
         predicates.add(root.get(AbstractJob_.lastOutcome).in(lastOutcome));

      if (null != jobId) {
         String query = jobId.replace("?", "_").replace("*", "%");
         if (!"".equals(query.trim()))
            predicates.add(builder.like(root.get(AbstractJob_.id).as(String.class), query));
      }

      if (null != jobTitle) {
         String query = jobTitle.replace("?", "_").replace("*", "%");
         if (!"".equals(query.trim()))
            predicates.add(builder.like(root.get(AbstractJob_.title).as(String.class), query));
      }

      return predicates;
   }

   public Outcome getLastOutcome() {
      return lastOutcome;
   }

   public void setLastOutcome(Outcome lastOutcome) {
      this.lastOutcome = lastOutcome;
   }

   public JobExecutionStatus getExecutionStatus() {
      return executionStatus;
   }

   public void setExecutionStatus(JobExecutionStatus executionStatus) {
      this.executionStatus = executionStatus;
   }

}
