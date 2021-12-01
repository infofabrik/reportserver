package net.datenwerke.rs.core.service.reportmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition__;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance__;
import net.datenwerke.rs.core.service.reportmanager.annotations.ReportServerParameter;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report__;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterContainerNode;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.simplequery.annotations.Join;
import net.datenwerke.rs.utils.simplequery.annotations.Predicate;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.eventlogger.annotations.FirePersistEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

/**
 * 
 *
 */
public class ReportParameterServiceImpl implements ReportParameterService {

	private final ReportService reportService;
	private final Provider<EntityManager> entityManagerProvider;
	private final Set<Class<? extends ParameterDefinition>> installedParameters;
	private final EntityClonerService entityCloner;
	
	@Inject
	public ReportParameterServiceImpl(
		ReportService reportService,
		Provider<EntityManager> entityManagerProvider,
		EntityClonerService entityCloner,
		
		@ReportServerParameter Set<Class<? extends ParameterDefinition>> installedParameters
		){
		
		/* store objects */
		this.reportService = reportService;
		this.entityManagerProvider = entityManagerProvider;
		this.installedParameters = installedParameters;
		this.entityCloner = entityCloner;
	}
	
	@Override
	public Set<Class<? extends ParameterDefinition>> getInstalledParameters(){
		return installedParameters;
	}
	
	@Override
	@FirePersistEntityEvents
	public void persist(ParameterDefinition pd) {
		EntityManager em = entityManagerProvider.get();
		em.persist(pd);
	}

	@Override
	@FirePersistEntityEvents
	public void persist(ParameterInstance pi) {
		EntityManager em = entityManagerProvider.get();
		em.persist(pi);
	}
	
	@Override
	@QueryById
	public ParameterDefinition getParameterById(long id) {
		return null; // by magic
	}
	
	@Override
	public ParameterDefinition getParameterByKey(@Named("rid") long report, @Named("key") String key) {
		EntityManager em = entityManagerProvider.get();
		Query q = em.createQuery("from " + Report.class.getSimpleName() + " as r, " + ParameterDefinition.class.getSimpleName() + " as pd WHERE r." + Report__.id + " = :report AND pd." + ParameterDefinition__.key + " = :key AND pd IN elements(r." + Report__.parameterDefinitions + ")"); //$NON-NLS-1$
		q.setParameter("report", report); //$NON-NLS-1$
		q.setParameter("key", key);
		List rs = q.getResultList();
		if(rs.size() > 0)
			return (ParameterDefinition) ((Object[])rs.get(0))[1];
		
		return null;
	}
	
	@Override
	@SimpleQuery(join=@Join(joinAttribute=ParameterDefinition__.dependsOn,where=@Predicate(attribute="", value="definition")))
	public List<ParameterDefinition> getParameterDependees(@Named("definition") ParameterDefinition definition) {
		return null; // by magic
	}
	
	@Override
	public ParameterDefinition getUnmanagedParameterById(long id) {
		ParameterDefinition managedParameter = getParameterById(id);
		return getUnmanagedParameter(managedParameter);
	}
	
	@Override
	public ParameterDefinition getUnmanagedParameter(ParameterDefinition<?> managedParameter) {
		ParameterDefinition unmanagedParameter = entityCloner.cloneEntity(managedParameter);
		return unmanagedParameter;
	}
	
	@Override
	@SimpleQuery(from=Report.class,join=@Join(joinAttribute=Report__.parameterDefinitions, where=@Predicate(attribute="",value="definition")))
	public Report getReportWithParameter(@Named("definition") ParameterDefinition pd) {
		return null; // magic
	}
	
	@Override
	@SimpleQuery(from=Report.class,join=@Join(joinAttribute=Report__.parameterInstances, where=@Predicate(attribute="",value="instance")))
	public Report getReportWithInstance(@Named("instance")ParameterInstance instance) {
		return null; // magic
	}

	@Override
	@QueryByAttribute(where=ParameterInstance__.definition)
	public List<ParameterInstance> getInstancesForParameterDefinition(ParameterDefinition definition) {
		return null; // magic
	}
	
	@Override
	public ParameterDefinition merge(ParameterDefinition definition) {
		/* load report and tell report that its parameter was changed */
		Report report = getReportWithParameter(definition);
		if(null == report)
			throw new IllegalArgumentException("Could not find corresponding node to parameter defintion " + definition.getId()); //$NON-NLS-1$
		
		if(checkCycle(definition, new HashSet<ParameterDefinition>()))
			throw new IllegalArgumentException("Parameters are not allowed to have cycles");
		
		reportService.merge(report);
		
		return entityManagerProvider.get().merge(definition);
	}

	protected boolean checkCycle(ParameterDefinition def, Set<ParameterDefinition> checkList) {
		if(checkList.contains(def))
			return true;
		
		checkList.add(def);
		for (Iterator<ParameterDefinition> iterator = def.getDependsOn().iterator(); iterator.hasNext();) {
			ParameterDefinition subDep = iterator.next();
			if(checkCycle(subDep, new HashSet<ParameterDefinition>(checkList)))
				return true;
		}
		return false;
	}

	/**
	 * Updates the instances in all reports (+variants)
	 * @param report
	 * @param definition
	 */
	@Override
	public void updateParameterInstances(Report report,
		ParameterDefinition definition) {

		/* get old instance and remove it */
		ParameterInstance oldInstance = report.getParameterInstanceFor(definition);
		remove(oldInstance);
		
		ParameterInstance newInstance = definition.createParameterInstance();
		persist(newInstance);
		report.addParameterInstance(newInstance);
		
		for(AbstractReportManagerNode child : report.getChildren()){
			if(! (child instanceof Report))
				throw new IllegalStateException("Expected children of reports to be of type Report."); //$NON-NLS-1$
			updateParameterInstances((Report)child, definition);
		}
	}
	
	public void addParameterDefinition(Report report, ParameterDefinition definition){
		if(report instanceof ReportVariant)
			throw new IllegalArgumentException("Variants do not have definitions: " + report.getId());
		
		report.addParameterDefinition(definition);
		
		/* add instance to me and all children */
		ParameterInstance instance = definition.createParameterInstance();
		report.addParameterInstance(instance);
		
		/* create instance for all variants */
		for(AbstractReportManagerNode reportChild : report.getChildren()){
			if(! (reportChild instanceof Report))
				throw new IllegalStateException("Expected children of reports to be reports."); //$NON-NLS-1$
			ParameterInstance myInstance = definition.createParameterInstance();
			((Report)reportChild).addParameterInstance(myInstance);
		}
	}
	

	@FireRemoveEntityEvents
	public AbstractReportManagerNode remove(ParameterDefinition definition) {
		Report report = getReportWithParameter(definition);
		if(null == report)
			throw new IllegalArgumentException("Could not find corresponding node to parameter defintion " + definition.getId()); //$NON-NLS-1$
		
		/* remove all instances */
		for(ParameterInstance instance : getInstancesForParameterDefinition(definition))
			remove(instance);	
		
		/* cleare dependees */
		List<ParameterDefinition> dependees = getParameterDependees(definition);
		if(null != dependees){
			for(ParameterDefinition dependee : dependees){
				List<ParameterDefinition> dependsOn = dependee.getDependsOn();
				dependsOn.remove(definition);
				dependee.setDependsOn(new ArrayList<ParameterDefinition>(dependsOn));
				merge(dependee);
			}
		}
		
		/* remove parameter from node */
		((ParameterContainerNode)report).removeParameterDefinition(definition);
		
		/* remove parameter */
		EntityManager em = entityManagerProvider.get();
		em.remove(em.find(definition.getClass(), definition.getId()));

		return report;
	}


	public AbstractReportManagerNode moveParameter(ParameterDefinition parameter, int to) {
		/* get corresponding node */
		Report node = getReportWithParameter(parameter);
		
		/* get current position */
		int currentPosition = parameter.getN();
		
		/* get parameterList  */
		List<ParameterDefinition> parameterList = ((ParameterContainerNode)node).getParameterDefinitions();

		/* move paramater */
		if(currentPosition < to)
			for(int i = currentPosition + 1; i <= to; i++)
				parameterList.get(i).setN(i-1);
		else if(to < currentPosition)
			for(int i = to; i < currentPosition; i++)
				parameterList.get(i).setN(i+1);

		parameter.setN(to);
		
		/* sort list by n */
		Collections.sort (parameterList, new Comparator<ParameterDefinition>() {
			public int compare(ParameterDefinition o1, ParameterDefinition o2) {
				if(o1.getN() < o2.getN())
					return -1;
				if(o1.getN() > o2.getN())
					return 1;
				return 0;
			}
		});
		
		/* merge node */
		reportService.merge(node);
		
		return node;
	}

	@FireRemoveEntityEvents
	public void remove(ParameterInstance instance) {
		Report report = getReportWithInstance(instance);
		
		/* remove instance */
		instance.setDefinition(null);
		if(null == report)
			throw new RuntimeException("We have encountered a lost ParameterInstance: " + instance.getId() + ". Please try storing a report as this triggers a cleanup process. If this does not resolve the problem, please contact an administrator.");
		report.removeParameterInstance(instance);
		
		EntityManager em = entityManagerProvider.get();
		em.remove(em.find(instance.getClass(), instance.getId()));
	}
}
