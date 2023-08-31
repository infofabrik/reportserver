package net.datenwerke.rs.tsreportarea.client.tsreportarea.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsReferenceInfo;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.container.TsDiskItemList;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.rpc.RPCTreeLoaderAsync;
import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManagerAsync;

public interface TsDiskRpcServiceAsync extends RPCTreeLoaderAsync, RPCTreeManagerAsync {

   void getItemsIn(TeamSpaceDto teamSpace, TsDiskFolderDto folder, AsyncCallback<TsDiskItemList> callback);

   void createFolder(TeamSpaceDto teamSpaceDto, TsDiskFolderDto parent, TsDiskFolderDto dummy,
         AsyncCallback<TsDiskFolderDto> callback);

   void importReport(TeamSpaceDto teamSpaceDto, TsDiskFolderDto parent, ReportDto reportDto, boolean copyReport,
         boolean reference, AsyncCallback<TsDiskReportReferenceDto> callback);

   void importReport(TeamSpaceDto teamSpaceDto, TsDiskFolderDto parent, ReportDto reportDto, boolean copyReport,
         String name, String description, boolean reference, AsyncCallback<TsDiskReportReferenceDto> callback);

   void getTeamSpacesWithTsDiskApp(AsyncCallback<List<TeamSpaceDto>> callback);

   void getReportsInCatalog(AsyncCallback<List<ReportDto>> callback);

   void getReferencesInApp(TeamSpaceDto teamSpace, TsDiskFolderDto folder,
         AsyncCallback<List<TsDiskReportReferenceDto>> callback);

   void sendUserViewChangedNotice(String viewId, AsyncCallback<Void> callback);

   void getTeamSpacesWithReferenceTo(ReportDto report, AsyncCallback<List<TeamSpaceDto>> callback);

   void getTeamSpacesWithPathsThatLinkTo(ReportDto report,
         AsyncCallback<Map<TeamSpaceDto, List<List<AbstractTsDiskNodeDto>>>> callback);

   void getTeamSpacesWithPathsThatLinkToAsHtml(ReportDto report, AsyncCallback<SafeHtml> callback);

   void updateNode(AbstractNodeDto nodeDto, boolean changeUnderlyingReport, String name, String description, Dto state,
         AsyncCallback<AbstractNodeDto> callback);

   void createAndImportVariant(TeamSpaceDto currentSpace, TsDiskFolderDto currentFolder, ReportDto reportVariantDto,
         String executeToken, String name, String desc, AsyncCallback<TsDiskReportReferenceDto> callback);

   void updateReferenceAndReport(TsDiskReportReferenceDto reference, ReportDto report, String executeToken, String name,
         String description, AsyncCallback<TsDiskReportReferenceDto> callback);

   void deleteNodes(List<AbstractNodeDto> nodes, Dto state, AsyncCallback<Void> callback);

   void deleteNodesWithForce(List<AbstractNodeDto> nodes, Dto state, AsyncCallback<Void> callback);

   void getReferenceInfosFor(ReportDto report, AsyncCallback<List<TsReferenceInfo>> callback);
   
   void isFileUploadEnabled(AsyncCallback<Boolean> callback);
}
