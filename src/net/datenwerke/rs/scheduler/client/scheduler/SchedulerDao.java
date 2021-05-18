package net.datenwerke.rs.scheduler.client.scheduler;

import java.util.List;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.client.scheduler.rpc.SchedulerRpcServiceAsync;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobInformation;
import net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto.ReportScheduleJobListInformation;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterCriteriaDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.security.client.security.dto.RightDto;

public class SchedulerDao extends Dao {

   private final SchedulerRpcServiceAsync rpcService;

   @Inject
   public SchedulerDao(SchedulerRpcServiceAsync rpcService) {
      super();
      this.rpcService = rpcService;
   }

   public void schedule(ReportScheduleDefinition scheduleDTO, AsyncCallback<Void> callback) {
      rpcService.schedule(scheduleDTO, transformAndKeepCallback(callback));
   }

   public void reschedule(Long jobId, ReportScheduleDefinition scheduleDTO, AsyncCallback<Void> callback) {
      rpcService.reschedule(jobId, scheduleDTO, transformAndKeepCallback(callback));
   }

   public void unschedule(Long jobId, AsyncCallback<Boolean> callback) {
      rpcService.unschedule(jobId, transformAndKeepCallback(callback));
   }

   public void loadFullScheduleInformation(ReportScheduleJobListInformation selected,
         AsyncCallback<ReportScheduleJobInformation> callback) {
      rpcService.loadFullScheduleInformation(selected, transformAndKeepCallback(callback));
   }

   public void getReportJobList(JobFilterConfigurationDto jobFilterConfig, List<JobFilterCriteriaDto> addCriterions,
         AsyncCallback<PagingLoadResult<ReportScheduleJobListInformation>> callback) {
      rpcService.getReportJobList(jobFilterConfig, addCriterions, transformAndKeepCallback(callback));
   }

   public void getReportJobList(ReportDto reportDto, AsyncCallback<List<ReportScheduleJobListInformation>> callback) {
      rpcService.getReportJobList(reportDto, transformAndKeepCallback(callback));
   }

   public void getReportJobListAsHtml(ReportDto reportDto, AsyncCallback<SafeHtml> callback) {
      rpcService.getReportJobListAsHtml(reportDto, transformAndKeepCallback(callback));
   }

   public void getReportFor(Long jobId, AsyncCallback<ReportDto> callback) {
      rpcService.getReportFor(jobId, transformAndKeepCallback(callback));
   }

   public void loadDetailsFor(ReportScheduleJobListInformation selected,
         AsyncCallback<ReportScheduleJobInformation> callback) {
      rpcService.loadDetailsFor(selected, transformAndKeepCallback(callback));
   }

   public void loadFullDetailsFor(Long jobId, ExecutionLogEntryDto entry,
         AsyncCallback<ExecutionLogEntryDto> callback) {
      rpcService.loadFullDetailsFor(jobId, entry, transformAndKeepCallback(callback));
   }

   public void scheduleOnce(Long jobId, AsyncCallback<Void> callback) {
      rpcService.scheduleOnce(jobId, transformAndKeepCallback(callback));
   }

   public void remove(Long jobId, RsAsyncCallback<Boolean> callback) {
      rpcService.remove(jobId, transformAndKeepCallback(callback));
   }

   public void clearErrorStatus(Long jobId, RsAsyncCallback<Void> callback) {
      rpcService.clearErrorStatus(jobId, transformAndKeepCallback(callback));
   }

   public void isDefaultEmailCompression(RsAsyncCallback<Boolean> callback) {
      rpcService.isDefaultEmailCompression(transformAndKeepCallback(callback));
   }

   public void assertOwnersHaveReportRights(List<Long> ownerIds, ReportDto report, List<RightDto> rights,
         RsAsyncCallback<Void> callback) {
      rpcService.assertOwnersHaveReportRights(ownerIds, report, rights, transformAndKeepCallback(callback));
   }
}
