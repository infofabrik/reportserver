package net.datenwerke.rs.scheduler.client.scheduler.rpc;

import java.util.List;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.security.client.security.dto.RightDto;

public interface SchedulerRpcServiceAsync {

   void schedule(ReportScheduleDefinition scheduleDTO, AsyncCallback<Void> callback);

   void loadFullScheduleInformation(ReportScheduleJobListInformation selected,
         AsyncCallback<ReportScheduleJobInformation> callback);

   void getReportJobList(JobFilterConfigurationDto jobFilterConfig, List<JobFilterCriteriaDto> addCriterions,
         AsyncCallback<PagingLoadResult<ReportScheduleJobListInformation>> callback);

   void unschedule(Long jobId, AsyncCallback<Boolean> callback);

   void remove(Long jobId, AsyncCallback<Boolean> callback);

   void clearErrorStatus(Long jobId, AsyncCallback<Void> callback);

   void getReportFor(Long jobId, AsyncCallback<ReportDto> callback);

   void reschedule(Long jobId, ReportScheduleDefinition scheduleDTO, AsyncCallback<Void> transformAndKeepCallback);

   void loadDetailsFor(ReportScheduleJobListInformation selected, AsyncCallback<ReportScheduleJobInformation> callback);

   void loadFullDetailsFor(Long jobId, ExecutionLogEntryDto entry, AsyncCallback<ExecutionLogEntryDto> callback);

   void scheduleOnce(Long jobId, AsyncCallback<Void> callback);

   void getReportJobList(ReportDto reportDto, AsyncCallback<List<ReportScheduleJobListInformation>> callback);

   void getReportJobListAsHtml(ReportDto reportDto, AsyncCallback<SafeHtml> callback);

   void isDefaultEmailCompression(AsyncCallback<Boolean> callback);

   void assertOwnersHaveReportRights(List<Long> ownerIds, ReportDto report, List<RightDto> rights,
         AsyncCallback<Void> callback);
}
