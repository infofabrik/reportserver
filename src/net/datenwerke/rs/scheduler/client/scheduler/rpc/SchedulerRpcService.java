package net.datenwerke.rs.scheduler.client.scheduler.rpc;

import java.util.List;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.security.client.security.dto.RightDto;

@RemoteServiceRelativePath("scheduler")
public interface SchedulerRpcService extends RemoteService {

   public void schedule(ReportScheduleDefinition scheduleDTO) throws ServerCallFailedException;

   public boolean unschedule(Long jobId) throws ServerCallFailedException;

   public ReportDto getReportFor(Long jobId) throws ServerCallFailedException;

   PagingLoadResult<ReportScheduleJobListInformation> getReportJobList(JobFilterConfigurationDto jobFilterConfig,
         List<JobFilterCriteriaDto> addCriterions) throws ServerCallFailedException;

   ReportScheduleJobInformation loadFullScheduleInformation(ReportScheduleJobListInformation selected)
         throws ServerCallFailedException;

   void reschedule(Long jobId, ReportScheduleDefinition scheduleDTO) throws ServerCallFailedException;

   ReportScheduleJobInformation loadDetailsFor(ReportScheduleJobListInformation selected)
         throws ServerCallFailedException;

   ExecutionLogEntryDto loadFullDetailsFor(Long jobId, ExecutionLogEntryDto entry) throws ServerCallFailedException;

   void scheduleOnce(Long jobId) throws ServerCallFailedException;

   boolean remove(Long jobId) throws ServerCallFailedException;

   void clearErrorStatus(Long jobId) throws ServerCallFailedException;

   List<ReportScheduleJobListInformation> getReportJobList(ReportDto report) throws ServerCallFailedException;

   SafeHtml getReportJobListAsHtml(ReportDto report) throws ServerCallFailedException;

   /**
    * @return true if email attachments should be compressed by default
    */
   boolean isDefaultEmailCompression() throws ServerCallFailedException;

   void assertOwnersHaveReportRights(List<Long> ownerIds, ReportDto report, List<RightDto> rights)
         throws ServerCallFailedException;
}