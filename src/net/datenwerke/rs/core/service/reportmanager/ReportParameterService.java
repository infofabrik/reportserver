package net.datenwerke.rs.core.service.reportmanager;

import java.util.List;
import java.util.Set;

import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

public interface ReportParameterService {

   /**
    * Returns all installed parameter definitions.
    * 
    */
   public Set<Class<? extends ParameterDefinition>> getInstalledParameters();

   /**
    * Persists the parameter
    * 
    * @param pd
    */
   public void persist(ParameterDefinition pd);

   public void persist(ParameterInstance pi);

   public ParameterDefinition getParameterById(long id);

   public ParameterDefinition getParameterByKey(long report, String key);

   public List<ParameterDefinition> getParameterDependees(ParameterDefinition definition);

   public List<ParameterInstance> getInstancesForParameterDefinition(ParameterDefinition pd);

   public ParameterDefinition merge(ParameterDefinition parameter);

   public AbstractReportManagerNode remove(ParameterDefinition parameter);

   /**
    * Creates an instance for the report and all possible variants
    * 
    * @param report
    * @param definition
    */
   public void addParameterDefinition(Report report, ParameterDefinition definition);

   public ParameterDefinition getUnmanagedParameterById(long id);

   public Report getReportWithParameter(ParameterDefinition pd);

   /**
    * Moves a parameter to the specified position
    * 
    * @param parameter
    * @param to
    */
   public AbstractReportManagerNode moveParameter(ParameterDefinition parameter, int to);

   public void remove(ParameterInstance instance);

   public ParameterDefinition getUnmanagedParameter(ParameterDefinition<?> definition);

   void updateParameterInstances(Report report, ParameterDefinition definition);

   Report getReportWithInstance(ParameterInstance instance);
}
