package net.datenwerke.rs.tsreportarea.client.tsreportarea;

import java.util.List;
import java.util.Map;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.hooks.PrepareReportModelForStorageOrExecutionHook;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsReferenceInfo;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.container.TsDiskItemList;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.rpc.TsDiskRpcServiceAsync;

/**
 * 
 *
 */
public class TsDiskDao extends Dao {

   private final TsDiskRpcServiceAsync rpcService;
   private final HookHandlerService hookHandler;

   @Inject
   public TsDiskDao(TsDiskRpcServiceAsync rpcservice, HookHandlerService hookHandler) {
      /* store objects */
      this.rpcService = rpcservice;
      this.hookHandler = hookHandler;
   }

   public void getItemsIn(TeamSpaceDto teamSpace, TsDiskFolderDto folder, AsyncCallback<TsDiskItemList> callback) {
      rpcService.getItemsIn(teamSpace, folder, transformContainerCallback(callback));
   }

   public void createFolder(TeamSpaceDto teamSpaceDto, TsDiskFolderDto parent, TsDiskFolderDto dummy,
         AsyncCallback<TsDiskFolderDto> callback) {
      rpcService.createFolder(teamSpaceDto, parent, dummy, transformDtoCallback(callback));
   }

   public void importReport(TeamSpaceDto teamSpaceDto, TsDiskFolderDto parent, ReportDto report, boolean copy,
         boolean reference, AsyncCallback<TsDiskReportReferenceDto> callback) {
      rpcService.importReport(teamSpaceDto, parent, report, copy, reference, transformAndKeepCallback(callback));
   }

   public void importReport(TeamSpaceDto teamSpaceDto, TsDiskFolderDto parent, ReportDto report, boolean copy,
         String name, String description, boolean reference, AsyncCallback<TsDiskReportReferenceDto> callback) {
      rpcService.importReport(teamSpaceDto, parent, report, copy, name, description, reference,
            transformAndKeepCallback(callback));
   }

   public void getTeamSpacesWithFavoriteApp(AsyncCallback<List<TeamSpaceDto>> callback) {
      rpcService.getTeamSpacesWithTsDiskApp(transformListCallback(callback));
   }

   public void getReportsInCatalog(AsyncCallback<List<ReportDto>> callback) {
      rpcService.getReportsInCatalog(transformListCallback(callback));
   }

   public void getReferencesInApp(TeamSpaceDto teamSpace, AsyncCallback<List<TsDiskReportReferenceDto>> callback) {
      getReferencesInApp(teamSpace, null, callback);
   }

   public void getReferencesInApp(TeamSpaceDto teamSpace, TsDiskFolderDto folder,
         AsyncCallback<List<TsDiskReportReferenceDto>> callback) {
      rpcService.getReferencesInApp(teamSpace, folder, transformListCallback(callback));
   }

   public void sendUserViewChangedNotice(String viewId, AsyncCallback<Void> callback) {
      rpcService.sendUserViewChangedNotice(viewId, transformAndKeepCallback(callback));
   }

   public void getTeamSpacesWithReferenceTo(ReportDto report, AsyncCallback<List<TeamSpaceDto>> callback) {
      rpcService.getTeamSpacesWithReferenceTo(report, transformListCallback(callback));
   }

   public void getTeamSpacesWithPathsThatLinkTo(ReportDto report,
         AsyncCallback<Map<TeamSpaceDto, List<List<AbstractTsDiskNodeDto>>>> callback) {
      rpcService.getTeamSpacesWithPathsThatLinkTo(report, transformMapOfListListCallback(callback));
   }

   public void getTeamSpacesWithPathsThatLinkToAsHtml(ReportDto report, AsyncCallback<SafeHtml> callback) {
      rpcService.getTeamSpacesWithPathsThatLinkToAsHtml(report, transformAndKeepCallback(callback));
   }

   public void createAndImportVariant(TeamSpaceDto currentSpace, TsDiskFolderDto currentFolder, ReportDto report,
         String executeToken, String name, String desc, RsAsyncCallback<TsDiskReportReferenceDto> callback) {
      ReportDto reportVariantDto = unproxy(report);

      prepareForStorage(reportVariantDto, executeToken);

      rpcService.createAndImportVariant(currentSpace, currentFolder, reportVariantDto, executeToken, name, desc,
            transformDtoCallback(callback));
   }

   public void updateReferenceAndReport(TsDiskReportReferenceDto reference, ReportDto report, String executeToken,
         String name, String description, RsAsyncCallback<TsDiskReportReferenceDto> callback) {

      prepareForStorage(report, executeToken);

      rpcService.updateReferenceAndReport(reference, report, executeToken, name, description,
            transformDtoCallback(callback));
   }

   private void prepareForStorage(ReportDto reportDto, String executeToken) {
      for (PrepareReportModelForStorageOrExecutionHook hooker : hookHandler
            .getHookers(PrepareReportModelForStorageOrExecutionHook.class)) {
         if (hooker.consumes(reportDto)) {
            hooker.prepareForExecutionOrStorage(reportDto, executeToken);
         }
      }
   }

   public void getReferenceInfosFor(ReportDto report, AsyncCallback<List<TsReferenceInfo>> callback) {
      rpcService.getReferenceInfosFor(report, transformAndKeepCallback(callback));
   }
   
   public void isFileUploadEnabled(AsyncCallback<Boolean> callback) {
      rpcService.isFileUploadEnabled(transformAndKeepCallback(callback));
   }
   
   public void getMaxUploadFileSizeBytes(AsyncCallback<Long> callback) {
      rpcService.getMaxUploadFileSizeBytes(transformAndKeepCallback(callback));
   }
}
