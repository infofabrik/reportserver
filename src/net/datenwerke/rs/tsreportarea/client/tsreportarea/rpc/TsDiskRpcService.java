package net.datenwerke.rs.tsreportarea.client.tsreportarea.rpc;

import java.util.List;
import java.util.Map;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.NeedForcefulDeleteClientException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsReferenceInfo;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.container.TsDiskItemList;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.rpc.RPCTreeLoader;
import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManager;

/**
 * 
 *
 */
@RemoteServiceRelativePath("ts/favorites")
public interface TsDiskRpcService extends RemoteService, RPCTreeLoader, RPCTreeManager {

   TsDiskItemList getItemsIn(TeamSpaceDto teamSpace, TsDiskFolderDto folder) throws ServerCallFailedException;

   TsDiskFolderDto createFolder(TeamSpaceDto teamSpaceDto, TsDiskFolderDto parent, TsDiskFolderDto dummy)
         throws ServerCallFailedException;

   TsDiskReportReferenceDto importReport(TeamSpaceDto teamSpaceDto, TsDiskFolderDto parent, ReportDto report,
         boolean copyReport, boolean reference) throws ServerCallFailedException;

   TsDiskReportReferenceDto importReport(TeamSpaceDto teamSpaceDto, TsDiskFolderDto parent, ReportDto report,
         boolean copyReport, String name, String description, boolean reference) throws ServerCallFailedException;

   List<TeamSpaceDto> getTeamSpacesWithTsDiskApp() throws ServerCallFailedException;

   List<ReportDto> getReportsInCatalog() throws ServerCallFailedException;

   List<TsDiskReportReferenceDto> getReferencesInApp(TeamSpaceDto teamSpace, TsDiskFolderDto folder)
         throws ServerCallFailedException;

   void sendUserViewChangedNotice(String viewId) throws ServerCallFailedException;

   List<TeamSpaceDto> getTeamSpacesWithReferenceTo(ReportDto report) throws ServerCallFailedException;

   Map<TeamSpaceDto, List<List<AbstractTsDiskNodeDto>>> getTeamSpacesWithPathsThatLinkTo(ReportDto report)
         throws ServerCallFailedException;

   SafeHtml getTeamSpacesWithPathsThatLinkToAsHtml(ReportDto report) throws ServerCallFailedException;

   AbstractNodeDto updateNode(AbstractNodeDto nodeDto, boolean changeUnderlyingReport, String name, String description,
         Dto state) throws ServerCallFailedException;

   TsDiskReportReferenceDto createAndImportVariant(TeamSpaceDto currentSpace, TsDiskFolderDto currentFolder,
         ReportDto reportVariantDto, String executeToken, String name, String desc) throws ServerCallFailedException;

   TsDiskReportReferenceDto updateReferenceAndReport(TsDiskReportReferenceDto reference, ReportDto report,
         String executeToken, String name, String description) throws ServerCallFailedException;

   void deleteNodes(List<AbstractNodeDto> nodes, Dto state)
         throws ServerCallFailedException, NeedForcefulDeleteClientException;

   void deleteNodesWithForce(List<AbstractNodeDto> nodes, Dto state) throws ServerCallFailedException;

   List<TsReferenceInfo> getReferenceInfosFor(ReportDto report) throws ServerCallFailedException;
   
   Boolean isFileUploadEnabled() throws ServerCallFailedException;
}
