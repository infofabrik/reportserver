package net.datenwerke.rs.scheduler.service.scheduler.hookers;

import static java.util.stream.Collectors.toMap;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.rs.scheduler.service.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scripting.service.jobs.ScriptExecuteJob;
import net.datenwerke.rs.uservariables.service.uservariables.hooks.UserVariableEntryProviderHook;
import net.datenwerke.scheduler.service.scheduler.SchedulerService;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration;

public class UsageStatisticsSchedulerProviderHooker implements UserVariableEntryProviderHook {

   private final SchedulerService schedulerService;
   
   @Inject
   public UsageStatisticsSchedulerProviderHooker(
         SchedulerService schedulerService
         ) {
      this.schedulerService = schedulerService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Object> provideEntry() {
      // active report jobs
      JobFilterConfiguration activeReportJobsFilter = new JobFilterConfiguration();
      activeReportJobsFilter.setActive(true);
      activeReportJobsFilter.setInActive(false);
      activeReportJobsFilter.setJobType(ReportExecuteJob.class);
      int activeReportJobs = schedulerService.getJobsBy(activeReportJobsFilter).size();
      
      // inactive report jobs
      JobFilterConfiguration inactiveReportJobsFilter = new JobFilterConfiguration();
      inactiveReportJobsFilter.setActive(false);
      inactiveReportJobsFilter.setInActive(true);
      inactiveReportJobsFilter.setJobType(ReportExecuteJob.class);
      int inactiveReportJobs = schedulerService.getJobsBy(inactiveReportJobsFilter).size();
      
      // active script jobs
      JobFilterConfiguration activeScriptJobsFilter = new JobFilterConfiguration();
      activeScriptJobsFilter.setActive(true);
      activeScriptJobsFilter.setInActive(false);
      activeScriptJobsFilter.setJobType(ScriptExecuteJob.class);
      int activeScriptJobs = schedulerService.getJobsBy(activeScriptJobsFilter).size();
      
      // inactive script jobs
      JobFilterConfiguration inactiveScriptJobsFilter = new JobFilterConfiguration();
      inactiveScriptJobsFilter.setActive(false);
      inactiveScriptJobsFilter.setInActive(true);
      inactiveScriptJobsFilter.setJobType(ScriptExecuteJob.class);
      int inactiveScriptJobs = schedulerService.getJobsBy(inactiveScriptJobsFilter).size();
      
      return Stream.of(
            new SimpleEntry<>(ImmutablePair.of("ACTIVE_REPORT_JOBS", SchedulerMessages.INSTANCE.activeReportJobs()),
                  activeReportJobs),
            new SimpleEntry<>(ImmutablePair.of("INACTIVE_REPORT_JOBS", SchedulerMessages.INSTANCE.inactiveReportJobs()),
                  inactiveReportJobs),
            new SimpleEntry<>(ImmutablePair.of("ACTIVE_SCRIPT_JOBS", SchedulerMessages.INSTANCE.activeScriptJobs()),
                  activeScriptJobs),
            new SimpleEntry<>(ImmutablePair.of("INACTIVE_SCRIPT_JOBS", SchedulerMessages.INSTANCE.inactiveScriptJobs()),
                  inactiveScriptJobs))
            .collect(toMap(Entry::getKey, Entry::getValue, (val1, val2) -> val2, LinkedHashMap::new));
   }

}
