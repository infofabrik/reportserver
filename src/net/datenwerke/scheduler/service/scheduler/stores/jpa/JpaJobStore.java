package net.datenwerke.scheduler.service.scheduler.stores.jpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob__;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractTrigger__;
import net.datenwerke.scheduler.service.scheduler.entities.JobExecutionStatus;
import net.datenwerke.scheduler.service.scheduler.stores.JobExecutionCompanion;
import net.datenwerke.scheduler.service.scheduler.stores.JobStoreImpl;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterCriteria;

public class JpaJobStore extends JobStoreImpl {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final Provider<EntityManager> entityManagerProvider;
   private final Provider<JpaExecutionCompanion> executionCompanionProvider;

   @Inject
   public JpaJobStore(Provider<EntityManager> entityManagerProvider,
         Provider<JpaExecutionCompanion> executionCompanionProvider) {

      /* store objects */
      this.entityManagerProvider = entityManagerProvider;
      this.executionCompanionProvider = executionCompanionProvider;
   }

   @Override
   public void scheduleJob(AbstractJob job, AbstractTrigger trigger) {
      super.scheduleJob(job, trigger);

      entityManagerProvider.get().persist(job);
   }

   @Override
   public Collection<AbstractJob> getActiveJobs() {
      EntityManager entityManager = entityManagerProvider.get();

      Query query = entityManager.createQuery("from " + AbstractJob.class.getSimpleName() + " where "
            + AbstractJob__.trigger + "." + AbstractTrigger__.nextScheduledFireTime + " is not null and "
            + AbstractJob__.executionStatus + " = :status");

      query.setParameter("status", JobExecutionStatus.INACTIVE);

      return query.getResultList();
   }

   @Override
   public Collection<AbstractJob> getExecutingJobs() {
      EntityManager entityManager = entityManagerProvider.get();

      Query query = entityManager.createQuery("from " + AbstractJob.class.getSimpleName() + " where " + "("
            + AbstractJob__.executionStatus + " = :status1" + " or " + AbstractJob__.executionStatus + " = :status2 )");

      query.setParameter("status1", JobExecutionStatus.EXECUTING);
      query.setParameter("status2", JobExecutionStatus.EXECUTING_ACTIONS);

      return query.getResultList();
   }

   @Override
   public Collection<AbstractJob> getWaitingJobs() {
      EntityManager entityManager = entityManagerProvider.get();

      Query query = entityManager.createQuery(
            "from " + AbstractJob.class.getSimpleName() + " where " + AbstractJob__.executionStatus + " = :status");

      query.setParameter("status", JobExecutionStatus.WAITING);

      return query.getResultList();
   }

   @Override
   @SimpleQuery
   public Collection<AbstractJob> getAllJobs() {
      return null; // magic
   }

   @Override
   public Collection<AbstractJob> getMisfiredJobs() {
      try {
         return getMisfiredJobs(Calendar.getInstance().getTime());
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
      }
      return new HashSet<AbstractJob>();
   }

   @Override
   public Collection<AbstractJob> getMisfiredJobs(Date before) {
      EntityManager entityManager = entityManagerProvider.get();

      Query query = entityManager.createQuery("from " + AbstractJob.class.getSimpleName() + " where " + "( "
            + AbstractJob__.trigger + "." + AbstractTrigger__.nextScheduledFireTime + " <= :date or "
            + AbstractJob__.trigger + "." + AbstractTrigger__.executeOnce + " = true" + ") and "
            + AbstractJob__.executionStatus + " = :status");

      query.setParameter("status", JobExecutionStatus.INACTIVE);
      query.setParameter("date", before);

      Collection<AbstractJob> result = query.getResultList();
      return result;
   }

   public AbstractJob reloadJob(AbstractJob job) {
      return entityManagerProvider.get().find(AbstractJob.class, job.getId());
   }

   @Override
   public void unschedule(AbstractJob job) {
      EntityManager em = entityManagerProvider.get();
      job.getTrigger().setDone();
      em.merge(job);
   }

   @Override
   public void remove(AbstractJob job) {
      doRemove(job, new HashSet<Long>());
   }

   private void doRemove(AbstractJob job, HashSet<Long> set) {
      if (set.contains(job.getId()))
         throw new IllegalStateException("Detected circle in job dependencies: linkToPrevious");
      set.add(job.getId());

      EntityManager em = entityManagerProvider.get();
      job = em.find(AbstractJob.class, job.getId());

      /* we need to take care of links to previous version, as they are cascaded */
      if (null != job.getLinkToPrevious())
         doRemove(job.getLinkToPrevious(), set);

      /* remove all that have previous id */
      List<AbstractJob> linkTos = getWithLinkToPrevious(job);
      if (null != linkTos && !linkTos.isEmpty()) {
         for (AbstractJob linkTo : linkTos) {
            linkTo.setLinkToPrevious(null);
            merge(linkTo);
         }
      }

      if (null != job)
         em.remove(job);
   }

   @QueryByAttribute(where = AbstractJob__.linkToPrevious)
   public List<AbstractJob> getWithLinkToPrevious(AbstractJob job) {
      return null; // magic
   }

   @Override
   public JobExecutionCompanion getExecutionCompanion() {
      return executionCompanionProvider.get();
   }

   @Override
   public List<AbstractJob> getJobsBy(JobFilterConfiguration mainFilterConfig, JobFilterCriteria... addConfigs) {
      EntityManager em = entityManagerProvider.get();

      CriteriaBuilder builder = em.getCriteriaBuilder();
      CriteriaQuery<AbstractJob> cQuery = builder.createQuery(AbstractJob.class);
      Root<? extends AbstractJob> root = cQuery.from(mainFilterConfig.getJobType());

      /* select */
      cQuery.select(root);

      /* distinct */
      /* Won't work on oracle see rs-1330 */
      // cQuery.distinct(true);

      List<Predicate> predicates = mainFilterConfig.prepareCriteriaQuery(builder, cQuery, root);
      for (JobFilterCriteria add : addConfigs)
         predicates.addAll(add.prepareCriteriaQuery(builder, cQuery, root));

      cQuery.where(builder.and(predicates.toArray(new Predicate[] {})));

      /* order by due date and creation date */
      if (null != mainFilterConfig.getOrder() && mainFilterConfig.validateSortField(mainFilterConfig.getSortField())) {
         List<Order> orderList = new ArrayList<Order>();

         if (net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.Order.ASC.equals(mainFilterConfig.getOrder()))
            orderList.add(builder
                  .asc(mainFilterConfig.transformSortField(mainFilterConfig.getSortField(), builder, cQuery, root)));
         else
            orderList.add(builder
                  .desc(mainFilterConfig.transformSortField(mainFilterConfig.getSortField(), builder, cQuery, root)));

         cQuery.orderBy(orderList);
      }

      /* execute query and return result */
      Query query = em.createQuery(cQuery);

      /* set limit and offset */
      if (mainFilterConfig.getLimit() != -1) {
         query.setFirstResult(mainFilterConfig.getOffset());
         query.setMaxResults(mainFilterConfig.getLimit());
      }

      @SuppressWarnings("unchecked")
      List<AbstractJob> result = query.getResultList();

      /* rs-1330 uargs. */
      HashSet<Long> ids = new HashSet<Long>();
      Iterator<AbstractJob> it = result.iterator();
      while (it.hasNext()) {
         AbstractJob job = it.next();
         if (ids.contains(job.getId())) {
            it.remove();
         } else {
            ids.add(job.getId());
         }
      }

      initJobs(result);

      return result;
   }

   public long countJobs(JobFilterConfiguration mainFilterConfig, JobFilterCriteria... addConfigs) {
      EntityManager em = entityManagerProvider.get();

      CriteriaBuilder builder = em.getCriteriaBuilder();
      CriteriaQuery<Long> cQuery = builder.createQuery(Long.class);
      Root<? extends AbstractJob> root = cQuery.from(mainFilterConfig.getJobType());

      cQuery.select(builder.count(root));

      /* prepare query */
      List<Predicate> predicates = mainFilterConfig.prepareCriteriaQuery(builder, cQuery, root);
      for (JobFilterCriteria add : addConfigs)
         predicates.addAll(add.prepareCriteriaQuery(builder, cQuery, root));
      cQuery.where(builder.and(predicates.toArray(new Predicate[] {})));

      /* execute query and return result */
      Query query = em.createQuery(cQuery);

      Long result = (Long) query.getSingleResult();

      return result;
   }

   public AbstractJob getJobById(long id) {
      AbstractJob job = _getJobById(id);
      initJob(job);

      return job;
   }

   @QueryById
   protected AbstractJob _getJobById(long id) {
      return null; // magic
   }

   @Override
   public void clearErrorState(AbstractJob job) {
      super.clearErrorState(job);
      merge(job);
   }

   @Override
   public void merge(AbstractJob job) {
      entityManagerProvider.get().merge(job);
   }
}
