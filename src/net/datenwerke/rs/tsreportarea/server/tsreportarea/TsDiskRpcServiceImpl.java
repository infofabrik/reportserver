package net.datenwerke.rs.tsreportarea.server.tsreportarea;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.NoResultException;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.service.teamspace.TeamSpaceService;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpaceApp;
import net.datenwerke.rs.teamspace.service.teamspace.genrights.TeamSpaceSecurityTarget;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsReferenceInfo;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.container.TsDiskItemList;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.rpc.TsDiskRpcService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskModule;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskService;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.TsDiskTeamSpaceAppDefinition;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskFolder;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskRoot;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.locale.TsDiskMessages;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.UserPropertiesService;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.EntireTreeDTO;
import net.datenwerke.treedb.service.treedb.AbstractNode;

/**
 * 
 *
 */
@Singleton
public class TsDiskRpcServiceImpl extends SecuredRemoteServiceServlet implements TsDiskRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 6148231216021358758L;

   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final DtoService dtoService;
   private final TsDiskService diskService;
   private final ReportService reportService;
   private final SecurityService securityService;
   private final TeamSpaceService teamSpaceService;
   private final UserManagerService userManagerService;

   private final UserPropertiesService userPropertiesService;

   @Inject
   public TsDiskRpcServiceImpl(Provider<AuthenticatorService> authenticatorServiceProvider, DtoService dtoService,
         TsDiskService diskService, ReportService reportService, SecurityService securityService,
         TeamSpaceService teamSpaceService, UserManagerService userManagerService,
         UserPropertiesService userPropertiesService) {

      /* store objects */
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.dtoService = dtoService;
      this.diskService = diskService;
      this.reportService = reportService;
      this.securityService = securityService;
      this.teamSpaceService = teamSpaceService;
      this.userManagerService = userManagerService;
      this.userPropertiesService = userPropertiesService;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public List<AbstractNodeDto> getRoot(Dto state) throws ServerCallFailedException {

      TsDiskRoot root = getRootNode(state);

      List<AbstractNodeDto> list = new ArrayList<AbstractNodeDto>();
      list.add((AbstractNodeDto) dtoService.createDto(root));

      return list;
   }

   private TsDiskRoot getRootNode(Dto state) throws ServerCallFailedException {
      /* load teamspace */
      TeamSpaceDto teamSpaceDto = getTeamSpace(state);
      TeamSpace teamSpace = (TeamSpace) dtoService.loadPoso(teamSpaceDto);

      /* check rights */
      if (!teamSpaceService.mayAccess(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      try {
         return diskService.getRoot(teamSpace);
      } catch (NoResultException e) {
      }

      return null;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto loadNodeById(Long id, Dto state) throws ServerCallFailedException {
      AbstractTsDiskNode realNode = diskService.getNodeById(id);
      if (realNode == null)
         return null;

      TeamSpace teamSpace = diskService.getTeamSpaceFor(realNode);

      /* check rights */
      if (!teamSpaceService.mayAccess(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      return (AbstractNodeDto) dtoService.createDto(realNode);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public List<AbstractNodeDto> getChildren(AbstractNodeDto node, Dto state, final Collection<Dto2PosoMapper> wlFilters,
         final Collection<Dto2PosoMapper> blFilters) throws ServerCallFailedException {
      /* load teamspace */
      TeamSpaceDto teamSpaceDto = getTeamSpace(state);
      TeamSpace teamSpace = (TeamSpace) dtoService.loadPoso(teamSpaceDto);

      /* check rights */
      if (!teamSpaceService.mayAccess(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      // TODO we actually need to check whether node is below the teamspace root

      List<AbstractNodeDto> list = new ArrayList<AbstractNodeDto>();

      for (AbstractTsDiskNode child : (Collection<AbstractTsDiskNode>) diskService.getNodeById(node.getId())
            .getChildrenSorted()) {
         if (child instanceof TsDiskFolder) {
            AbstractNodeDto dto = (AbstractNodeDto) dtoService.createListDto(child);
            list.add(dto);
         }
      }

      return list;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public TsDiskItemList getItemsIn(TeamSpaceDto teamSpaceDto, TsDiskFolderDto folderDto)
         throws ServerCallFailedException {
      /* test teamSpace */
      TeamSpace teamSpace = (TeamSpace) dtoService.loadPoso(teamSpaceDto);

      /* check rights */
      if (!teamSpaceService.mayAccess(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      /* get folder */
      AbstractTsDiskNode folder = getFolder(teamSpace, folderDto);
      if (null == folder)
         return new TsDiskItemList();

      /* create result list */
      List<AbstractTsDiskNodeDto> items = new ArrayList<AbstractTsDiskNodeDto>();

      for (AbstractTsDiskNode child : folder.getChildrenSorted()) {
         AbstractTsDiskNodeDto dto = (AbstractTsDiskNodeDto) dtoService.createListDto(child);
         items.add(dto);
      }

      /* create path */
      List<AbstractTsDiskNodeDto> path = new ArrayList<AbstractTsDiskNodeDto>();
      AbstractTsDiskNode parent = folder.getParent();
      while (null != parent) {
         path.add((AbstractTsDiskNodeDto) dtoService.createDto(parent));
         parent = parent.getParent();
      }
      Collections.reverse(path);

      /* create container */
      TsDiskItemList container = new TsDiskItemList();
      container.setItems(items);
      container.setPath(path);

      return container;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto moveNodeAppend(AbstractNodeDto node, AbstractNodeDto reference, Dto state)
         throws ServerCallFailedException {
      /* get teamspace */
      TeamSpaceDto teamSpaceDto = getTeamSpace(state);
      TeamSpace teamSpace = (TeamSpace) dtoService.loadPoso(teamSpaceDto);

      /* check rights */
      if (!teamSpaceService.isUser(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      /* get objects */
      AbstractTsDiskNode realNode = diskService.getNodeById(node.getId());
      AbstractTsDiskNode parent = diskService.getNodeById(reference.getId());

      /* check rights */
      if (!teamSpace.equals(diskService.getTeamSpaceFor(realNode))
            || !teamSpace.equals(diskService.getTeamSpaceFor(parent)))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      /* move node */
      diskService.move(realNode, parent);

      /* merge parent */
      diskService.merge(parent);

      return (AbstractTsDiskNodeDto) dtoService.createDto(realNode);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public List<AbstractNodeDto> moveNodesAppend(List<AbstractNodeDto> nodes,
         @Named("reference") AbstractNodeDto reference, Dto state) throws ServerCallFailedException {
      /* get teamspace */
      TeamSpaceDto teamSpaceDto = getTeamSpace(state);
      TeamSpace teamSpace = (TeamSpace) dtoService.loadPoso(teamSpaceDto);

      /* check rights */
      if (!teamSpaceService.isUser(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      /* get real nodes */
      List<AbstractTsDiskNode> realNodes = new ArrayList();
      for (AbstractNodeDto nodeDto : nodes) {
         AbstractTsDiskNode node = diskService.getNodeById(nodeDto.getId());

         /* check rights */
         if (!teamSpace.equals(diskService.getTeamSpaceFor(node)))
            throw new ViolatedSecurityExceptionDto("insufficient rights");

         realNodes.add(node);
      }

      /* move */
      AbstractTsDiskNode parent = diskService.getNodeById(reference.getId());
      List<AbstractNodeDto> resultList = new ArrayList<AbstractNodeDto>();

      /* check rights */
      if (!teamSpace.equals(diskService.getTeamSpaceFor(parent)))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      for (AbstractTsDiskNode node : realNodes) {
         /* move node */
         AbstractTsDiskNode oldParent = (AbstractTsDiskNode) node.getParent();
         diskService.move(node, parent);

         resultList.add((AbstractNodeDto) dtoService.createDto(node));
      }

      /* merge parent */
      diskService.merge(parent);

      return resultList;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void deleteNodes(List<AbstractNodeDto> nodes, Dto state) throws ServerCallFailedException {
      deleteNodes(nodes, state, false);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void deleteNodesWithForce(List<AbstractNodeDto> nodes, Dto state) throws ServerCallFailedException {
      deleteNodes(nodes, state, true);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void deleteNode(AbstractNodeDto node, Dto state) throws ServerCallFailedException {
      deleteNode(node, state, false);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void deleteNodeWithForce(AbstractNodeDto node, Dto state) throws ServerCallFailedException {
      deleteNode(node, state, true);
   }

   protected Boolean deleteNodes(List<AbstractNodeDto> nodes, Dto state, boolean force)
         throws ServerCallFailedException {
      if (null == nodes || nodes.isEmpty())
         return true;

      for (AbstractNodeDto node : nodes)
         deleteNode(node, state, force);

      return true;
   }

   protected Boolean deleteNode(AbstractNodeDto node, Dto state, boolean force) throws ServerCallFailedException {
      TeamSpaceDto teamSpaceDto = getTeamSpace(state);
      TeamSpace teamSpace = (TeamSpace) dtoService.loadPoso(teamSpaceDto);

      /* check rights */
      if (!teamSpaceService.isUser(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      AbstractTsDiskNode uNode = diskService.getNodeById(node.getId());

      /* do not allow the deletion of root nodes */
      if (uNode.isRoot())
         throw new ExpectedException("Root cannot be deleted.");

      if (force)
         diskService.forceRemove(uNode);
      else
         diskService.remove(uNode);

      return true;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public TsDiskFolderDto createFolder(TeamSpaceDto teamSpaceDto, TsDiskFolderDto parentDto, TsDiskFolderDto dummy)
         throws ServerCallFailedException {
      /* test teamSpace */
      TeamSpace teamSpace = (TeamSpace) dtoService.loadPoso(teamSpaceDto);

      /* check rights */
      if (!teamSpaceService.isUser(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      /* get folder */
      AbstractTsDiskNode parent = getFolder(teamSpace, parentDto);

      /* create Folder */
      TsDiskFolder folder = new TsDiskFolder();
      folder.setName(dummy.getName());
      folder.setDescription(dummy.getDescription());
      parent.addChild(folder);

      diskService.persist(folder);
      diskService.merge(parent);

      return (TsDiskFolderDto) dtoService.createDto(folder);
   }

   private TeamSpaceDto getTeamSpace(Dto state) {
      if (null == state || !(state instanceof TeamSpaceDto))
         throw new IllegalArgumentException("Illegal state");

      return (TeamSpaceDto) state;
   }

   private AbstractTsDiskNode getFolder(TeamSpace teamSpace, TsDiskFolderDto folderDto) {
      if (null == folderDto) {
         try {
            return diskService.getRoot(teamSpace);
         } catch (NoResultException e) {
            return null;
         }
      } else
         return (TsDiskFolder) dtoService.loadPoso(folderDto);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public TsDiskReportReferenceDto importReport(TeamSpaceDto teamSpaceDto, TsDiskFolderDto folderDto,
         ReportDto reportDto, boolean copyReport, boolean isReference)
         throws ServerCallFailedException, ExpectedException {

      return doImportReport(teamSpaceDto, folderDto, reportDto, copyReport, null, null, isReference);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public TsDiskReportReferenceDto importReport(TeamSpaceDto teamSpaceDto, TsDiskFolderDto folderDto,
         ReportDto reportDto, boolean copyReport, String name, String description, boolean isReference)
         throws ServerCallFailedException, ExpectedException {
      return doImportReport(teamSpaceDto, folderDto, reportDto, copyReport, name, description, isReference);
   }

   @Transactional(rollbackOn = { Exception.class })
   private TsDiskReportReferenceDto doImportReport(TeamSpaceDto teamSpaceDto, TsDiskFolderDto folderDto,
         ReportDto reportDto, boolean copyReport, String name, String description, boolean isReference)
         throws ServerCallFailedException, ExpectedException {
      /* load team space */
      TeamSpace teamSpace = (TeamSpace) dtoService.loadPoso(teamSpaceDto);

      /* check rights */
      if (!teamSpaceService.isUser(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      /* get parent */
      AbstractTsDiskNode parent = getFolder(teamSpace, folderDto);

      /* import reports */

      Report report = (Report) dtoService.loadPoso(reportDto);

      if (copyReport && report instanceof ReportVariant && !teamSpaceService.isManager(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");
      if (!(report instanceof ReportVariant) && !teamSpaceService.isManager(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      if (!copyReport && !diskService.getTeamSpacesThatLinkTo(report).isEmpty() && !isReference)
         throw new ExpectedException(TsDiskMessages.INSTANCE.errorVariantNeedsToBeDuplicated());

      TsDiskReportReference reference = null;

      /* Actual import. We leave both methods for backward-compatibility reasons. */
      if (null == name && null == description)
         reference = diskService.importReport(report, parent, copyReport, isReference);
      else
         reference = diskService.importReport(report, parent, copyReport, name, description, isReference);

      /* create dto */
      TsDiskReportReferenceDto referenceDto = (TsDiskReportReferenceDto) dtoService.createDto(reference);

      /* merge */
      diskService.merge(parent);

      return referenceDto;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public List<TeamSpaceDto> getTeamSpacesWithTsDiskApp() throws ServerCallFailedException {
      List<TeamSpaceDto> teamSpaces = new ArrayList<TeamSpaceDto>();

      /* get team spaces for current user and test if favorite app is installed */
      for (TeamSpace ts : teamSpaceService.getTeamSpaces()) {
         TeamSpaceApp app = ts.getAppByType(TsDiskTeamSpaceAppDefinition.APP_ID);
         if (null != app && app.isInstalled())
            teamSpaces.add((TeamSpaceDto) dtoService.createDto(ts));
      }

      return teamSpaces;
   }

   /**
    * Returns a list of all reports the current user is allowed to see
    */
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public List<ReportDto> getReportsInCatalog() throws ServerCallFailedException {
      List<ReportDto> reports = new ArrayList<ReportDto>();

      for (Report report : reportService.getAllReports()) {
         /* we don't care about variants */
         if (report instanceof ReportVariant)
            continue;

         /* check rights */
         if (!securityService.checkRights(report, SecurityServiceSecuree.class, Read.class))
            continue;

         reports.add((ReportDto) dtoService.createListDto(report));
      }

      return reports;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public List<TsDiskReportReferenceDto> getReferencesInApp(TeamSpaceDto teamSpaceDto, TsDiskFolderDto folderDto)
         throws ServerCallFailedException {
      TeamSpace teamSpace = (TeamSpace) dtoService.loadPoso(teamSpaceDto);

      /* check rights */
      if (!teamSpaceService.mayAccess(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      List<TsDiskReportReference> references;

      if (null != folderDto) {
         AbstractTsDiskNode folder = (AbstractTsDiskNode) dtoService.loadPoso(folderDto);

         if (!teamSpace.equals(diskService.getTeamSpaceFor(folder)))
            throw new ViolatedSecurityExceptionDto("insufficient rights");
         if (!(folder instanceof TsDiskFolder))
            throw new ServerCallFailedException("expected folder");

         references = diskService.getReferencesIn(folder);
      } else
         references = diskService.getReferencesFor(teamSpace);

      List<TsDiskReportReferenceDto> dtos = new ArrayList<TsDiskReportReferenceDto>();
      for (TsDiskReportReference reference : references)
         dtos.add((TsDiskReportReferenceDto) dtoService.createListDto(reference));

      return dtos;
   }

   @Transactional(rollbackOn = { Exception.class })
   protected AbstractNodeDto updateNode(AbstractNodeDto nodeDto) throws ServerCallFailedException {
      return updateNode(nodeDto, null);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto updateNode(AbstractNodeDto nodeDto, Dto state) throws ServerCallFailedException {
      AbstractTsDiskNode node = (AbstractTsDiskNode) dtoService.loadPoso(nodeDto);
      TeamSpace teamSpace = diskService.getTeamSpaceFor(node);

      teamSpaceService.assertIsUser(teamSpace);

      /* merge node */
      dtoService.mergePoso(nodeDto, node);
      diskService.merge(node);

      return (AbstractNodeDto) dtoService.createDtoFullAccess(node);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto updateNode(AbstractNodeDto nodeDto, boolean changeUnderlyingReport, String name,
         String description, Dto state) throws ServerCallFailedException {
      AbstractTsDiskNode node = (AbstractTsDiskNode) dtoService.loadPoso(nodeDto);
      TeamSpace teamSpace = diskService.getTeamSpaceFor(node);

      teamSpaceService.assertIsUser(teamSpace);

      /* merge node */
      node.setName(name);
      node.setDescription(description);
      diskService.merge(node);

      if (changeUnderlyingReport && node instanceof TsDiskReportReference) {
         TsDiskReportReference reference = (TsDiskReportReference) node;
         if (reference.isHardlink() && null != reference.getReport() && !reference.getReport().isWriteProtected()
               && !reference.getReport().isConfigurationProtected()) {
            Report report = reference.getReport();
            if (null != report) {
               report.setName(reference.getName());
               report.setDescription(reference.getDescription());
               reportService.merge(report);
            }
         }
      }

      return (AbstractNodeDto) dtoService.createDtoFullAccess(node);
   }

   @Override
   public AbstractNodeDto insertNode(AbstractNodeDto objectTypeToInsert, AbstractNodeDto node, Dto state)
         throws ServerCallFailedException {
      throw new ServerCallFailedException("not implemented"); //$NON-NLS-1$
   }

   @Override
   public AbstractNodeDto refreshNode(AbstractNodeDto node, Dto state) throws ServerCallFailedException {
      throw new ServerCallFailedException("not implemented"); //$NON-NLS-1$
   }

   @Override
   public EntireTreeDTO loadAll(Dto state) throws ServerCallFailedException {
      EntireTreeDTO treeDto = new EntireTreeDTO();
      TsDiskRoot root = getRootNode(state);

      List<AbstractNodeDto> list = new ArrayList<AbstractNodeDto>();
      for (AbstractNodeDto rootDto : getRoot(state)) {
         treeDto.addRoot(rootDto);
         addChildren(treeDto, rootDto, root);
      }

      return treeDto;
   }

   private void addChildren(EntireTreeDTO treeDto, AbstractNodeDto parentDto, AbstractNode parent) {
      for (AbstractNode child : (List<AbstractTsDiskNode>) parent.getChildrenSorted()) {
         AbstractNodeDto childDto = (AbstractNodeDto) dtoService.createListDto(child);
         treeDto.addChild(parentDto, childDto);
         addChildren(treeDto, childDto, child);
      }
   }

   private void addChildren(EntireTreeDTO treeDto, AbstractNodeDto parentDto, AbstractNode parent,
         Set<Class<?>> wlFilterList, Set<Class<?>> blFilterList) {
      for (AbstractNode child : (List<AbstractTsDiskNode>) parent.getChildrenSorted()) {
         boolean found = true;
         if (null != wlFilterList) {
            found = false;
            for (Class<?> filter : wlFilterList) {
               if (filter.isAssignableFrom(child.getClass())) {
                  found = true;
                  break;
               }
            }
         }
         if (!found)
            continue;
         if (null != blFilterList) {
            for (Class<?> filter : blFilterList) {
               if (filter.isAssignableFrom(child.getClass())) {
                  found = false;
                  break;
               }
            }
            if (!found)
               continue;
         }

         AbstractNodeDto childDto = (AbstractNodeDto) dtoService.createListDto(child);
         treeDto.addChild(parentDto, childDto);
         addChildren(treeDto, childDto, child, wlFilterList, blFilterList);
      }
   }

   @Override
   public AbstractNodeDto loadFullViewNode(AbstractNodeDto nodeDto, Dto state) throws ServerCallFailedException {
      AbstractTsDiskNode node = (AbstractTsDiskNode) dtoService.loadPoso(nodeDto);

      TeamSpace teamSpace = diskService.getTeamSpaceFor(node);
      if (!teamSpaceService.mayAccess(teamSpace))
         throw new ViolatedSecurityExceptionDto();

      return (AbstractNodeDto) dtoService.createDto(node);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto moveNodeInsert(AbstractNodeDto node, AbstractNodeDto reference, int index, Dto state)
         throws ServerCallFailedException {
      throw new ServerCallFailedException("not implemented"); //$NON-NLS-1$
   }

   @Override
   public AbstractNodeDto duplicateNode(AbstractNodeDto toDuplicate, Dto state) throws ServerCallFailedException {
      throw new ServerCallFailedException("not implemented"); //$NON-NLS-1$
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public void sendUserViewChangedNotice(String viewId) throws ServerCallFailedException {
      User currentUser = authenticatorServiceProvider.get().getCurrentUser();

      if (null != viewId && (viewId.length() > 64 || !viewId.matches("^[a-zA-Z0-9]*$")))
         throw new ServerCallFailedException("unsuppoted id");

      if (null != viewId)
         userPropertiesService.setPropertyValue(currentUser, TsDiskModule.USER_PROPERTY_VIEW_VIEW_ID, viewId);

      userManagerService.merge(currentUser);
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "report", isDto = true, verify = @RightsVerification(rights = Read.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public List<TeamSpaceDto> getTeamSpacesWithReferenceTo(@Named("report") ReportDto reportDto)
         throws ServerCallFailedException {
      Report report = (Report) dtoService.loadPoso(reportDto);

      Set<TeamSpace> teamSpaces = diskService.getTeamSpacesThatLinkTo(report);

      List<TeamSpaceDto> resultList = new ArrayList<TeamSpaceDto>();
      for (TeamSpace ts : teamSpaces)
         resultList.add((TeamSpaceDto) dtoService.createListDto(ts));

      return resultList;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public Map<TeamSpaceDto, List<List<AbstractTsDiskNodeDto>>> getTeamSpacesWithPathsThatLinkTo(
         @Named("report") ReportDto reportDto) throws ServerCallFailedException {
      securityService.assertRights(TeamSpaceSecurityTarget.class, Read.class);

      Report report = (Report) dtoService.loadPoso(reportDto);

      Map<TeamSpace, List<List<AbstractTsDiskNode>>> pathsMap = diskService.getTeamSpacesWithPathsThatLinkTo(report);

      Map<TeamSpaceDto, List<List<AbstractTsDiskNodeDto>>> resultMap = new HashMap<>();
      Iterator<TeamSpace> it = pathsMap.keySet().iterator();

      while (it.hasNext()) {
         TeamSpace ts = it.next();
         if (!teamSpaceService.mayAccess(ts))
            continue;
         TeamSpaceDto tsDto = (TeamSpaceDto) dtoService.createListDto(ts);
         List<List<AbstractTsDiskNode>> paths = pathsMap.get(ts);
         List<List<AbstractTsDiskNodeDto>> pathsDto = new ArrayList<>();
         for (List<AbstractTsDiskNode> path : paths) {
            List<AbstractTsDiskNodeDto> pathDto = new ArrayList<>();
            for (AbstractTsDiskNode node : path) {
               AbstractTsDiskNodeDto nodeDto = (AbstractTsDiskNodeDto) dtoService.createListDto(node);
               pathDto.add(nodeDto);
            }
            pathsDto.add(pathDto);
         }
         resultMap.put(tsDto, pathsDto);
      }

      return resultMap;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public SafeHtml getTeamSpacesWithPathsThatLinkToAsHtml(@Named("report") ReportDto reportDto)
         throws ServerCallFailedException {
      if (!securityService.checkRights(TeamSpaceSecurityTarget.class, Read.class))
         return null;

      Map<TeamSpaceDto, List<List<AbstractTsDiskNodeDto>>> teamspaces = getTeamSpacesWithPathsThatLinkTo(reportDto);

      SafeHtml asHtml = printTeamSpacesWithPathsThatLinkTo(teamspaces);

      return asHtml;

   }

   private SafeHtml printTeamSpacesWithPathsThatLinkTo(
         Map<TeamSpaceDto, List<List<AbstractTsDiskNodeDto>>> teamspaces) {
      SafeHtmlBuilder builder = new SafeHtmlBuilder();

      /* Hard links */
      Iterator<TeamSpaceDto> it = teamspaces.keySet().iterator();
      while (it.hasNext()) {
         TeamSpaceDto ts = it.next();

         List<List<AbstractTsDiskNodeDto>> hardLink = getLinks(teamspaces.get(ts), true);
         if (!hardLink.isEmpty()) {
            builder.appendHtmlConstant("<ol>");
            builder.appendHtmlConstant("<li>").appendEscaped(ts.getName() + " (" + ts.getId() + ")");
            builder.appendHtmlConstant("<ul>");
            printLinks(hardLink, builder);
            builder.appendHtmlConstant("</ul>");
            builder.appendHtmlConstant("</li>");
            builder.appendHtmlConstant("</ol>");
         }
      }

      /* Soft links */
      boolean hasSoftLinks = hasSoftLinks(teamspaces);
      if (hasSoftLinks) {
         builder.appendHtmlConstant("<span style=\"font-style:italic;\">")
               .appendEscaped(TsDiskMessages.INSTANCE.referencedBy() + ":").appendHtmlConstant("</span>");
         builder.appendHtmlConstant("<ol>");

         it = teamspaces.keySet().iterator();
         while (it.hasNext()) {
            TeamSpaceDto ts = it.next();

            List<List<AbstractTsDiskNodeDto>> softLinks = getLinks(teamspaces.get(ts), false);
            if (!softLinks.isEmpty()) {

               builder.appendHtmlConstant("<li>").appendEscaped(ts.getName() + " (" + ts.getId() + ")");
               builder.appendHtmlConstant("<ul>");
               printLinks(softLinks, builder);
               builder.appendHtmlConstant("</ul>");
               builder.appendHtmlConstant("</li>");
            }
         }
         builder.appendHtmlConstant("</ol>");
      }

      return (!teamspaces.isEmpty() ? builder.toSafeHtml() : null);
   }

   /*
    * If hardLinks == true, get all hard links in the list. If not, get all soft
    * links in the list.
    */
   private List<List<AbstractTsDiskNodeDto>> getLinks(List<List<AbstractTsDiskNodeDto>> paths, boolean hardLinks) {
      List<List<AbstractTsDiskNodeDto>> links = new ArrayList<>();
      for (List<AbstractTsDiskNodeDto> path : paths) {
         if (hardLinks) {
            if (isHardLink(path))
               links.add(path);
         } else {
            if (!isHardLink(path))
               links.add(path);
         }
      }

      return links;
   }

   private boolean hasSoftLinks(Map<TeamSpaceDto, List<List<AbstractTsDiskNodeDto>>> teamspaces) {

      Iterator<TeamSpaceDto> it = teamspaces.keySet().iterator();
      while (it.hasNext()) {
         TeamSpaceDto ts = it.next();

         List<List<AbstractTsDiskNodeDto>> softLinks = getLinks(teamspaces.get(ts), false);
         if (!softLinks.isEmpty()) {
            return true;
         }
      }
      return false;
   }

   private void printLinks(List<List<AbstractTsDiskNodeDto>> links, SafeHtmlBuilder builder) {

      for (List<AbstractTsDiskNodeDto> path : links) {
         StringBuilder pathStr = new StringBuilder();
         AbstractTsDiskNodeDto lastNode = path.get(path.size() - 1);
         for (AbstractTsDiskNodeDto node : path) {
            if (node instanceof TsDiskFolderDto) {
               pathStr.append(((TsDiskFolderDto) node).getName());
            } else if (node instanceof TsDiskRootDto) {
               pathStr.append(((TsDiskRootDto) node).getName());
            } else if (node instanceof TsDiskReportReferenceDto) {
               pathStr.append(((TsDiskReportReferenceDto) node).getName());
               pathStr.append(" (" + ((TsDiskReportReferenceDto) node).getId());
               Long reportId = ((TsDiskReportReferenceDto) node).getReport().getId();
               if (null != reportId)
                  pathStr.append(" -> " + ((TsDiskReportReferenceDto) node).getReport().getId());

               pathStr.append(")");
            } else {
               pathStr.append(node.getId());
            }
            if (node != lastNode) {
               pathStr.append("/");
            }
         }
         builder.appendHtmlConstant("<li>").appendEscaped(pathStr.toString()).appendHtmlConstant("</li>");

      }
   }

   private boolean isHardLink(List<AbstractTsDiskNodeDto> path) {
      for (AbstractTsDiskNodeDto node : path) {
         if (node instanceof TsDiskReportReferenceDto) {
            TsDiskReportReferenceDto nodeDto = (TsDiskReportReferenceDto) node;
            return nodeDto.isHardlink();
         }
      }

      return false;
   }

   @Override
   public EntireTreeDTO loadAll(Dto state, Collection<Dto2PosoMapper> wlFilters, Collection<Dto2PosoMapper> blFilters)
         throws ServerCallFailedException {
      boolean filter = (null != wlFilters && !wlFilters.isEmpty()) || (null != blFilters && !blFilters.isEmpty());
      if (!filter)
         return loadAll(state);

      Set<Class<?>> wlFilterList = new HashSet<Class<?>>();
      Set<Class<?>> blFilterList = new HashSet<Class<?>>();
      if (null == wlFilters)
         wlFilters = new HashSet<Dto2PosoMapper>();
      if (null == blFilters)
         blFilters = new HashSet<Dto2PosoMapper>();

      for (Dto2PosoMapper filterDtoMapper : wlFilters)
         wlFilterList.add(dtoService.getPosoFromDtoMapper(filterDtoMapper));
      for (Dto2PosoMapper filterDtoMapper : blFilters)
         blFilterList.add(dtoService.getPosoFromDtoMapper(filterDtoMapper));

      /* load tree */
      EntireTreeDTO treeDto = new EntireTreeDTO();
      TsDiskRoot root = getRootNode(state);

      List<AbstractNodeDto> list = new ArrayList<AbstractNodeDto>();
      for (AbstractNodeDto rootDto : getRoot(state)) {
         treeDto.addRoot(rootDto);
         addChildren(treeDto, rootDto, root, wlFilterList, blFilterList);
      }

      return treeDto;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public TsDiskReportReferenceDto createAndImportVariant(TeamSpaceDto teamSpaceDto, TsDiskFolderDto folderDto,
         @Named("report") ReportDto reportDto, String executeToken, String name, String desc)
         throws ServerCallFailedException {
      /* create report first */
      Report referenceReport = (Report) dtoService.loadPoso(reportDto);

      /* has permissions */
      securityService.assertRights(referenceReport, Execute.class);

      /* create variant */
      Report adjustedReport = (Report) dtoService.createUnmanagedPoso(reportDto);
      Report variant = referenceReport.createNewVariant(adjustedReport);

      reportService.prepareVariantForStorage((ReportVariant) variant, executeToken);
      variant.setName(name);
      variant.setDescription(desc);

      /* persist variant */
      reportService.persist(variant);

      /* import into teamspace */

      /* load team space */
      TeamSpace teamSpace = (TeamSpace) dtoService.loadPoso(teamSpaceDto);

      /* check rights */
      if (!teamSpaceService.isUser(teamSpace))
         throw new ViolatedSecurityExceptionDto("insufficient rights");

      /* get parent */
      AbstractTsDiskNode parent = getFolder(teamSpace, folderDto);

      /* do import */
      TsDiskReportReference reference = diskService.importReport(variant, parent, false, false);

      /* create and return it */
      return (TsDiskReportReferenceDto) dtoService.createDto(reference);
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public TsDiskReportReferenceDto updateReferenceAndReport(TsDiskReportReferenceDto referenceDto,
         @Named("report") ReportDto reportDto, String executeToken, String name, String description)
         throws ServerCallFailedException {
      /* load reference and check teamspace */
      AbstractTsDiskNode reference = (AbstractTsDiskNode) dtoService.loadPoso(referenceDto);
      TeamSpace teamSpace = diskService.getTeamSpaceFor(reference);

      /* check rights */
      teamSpaceService.assertIsUser(teamSpace);

      /* load report */
      Report report = (Report) dtoService.loadPoso(reportDto);
      if (!(report instanceof ReportVariant))
         throw new IllegalArgumentException();

      /* has permissions */
      securityService.assertRights(report.getParent(), Execute.class);

      /* update report */
      dtoService.mergePoso(reportDto, report);
      reportService.prepareVariantForStorage((ReportVariant) report, executeToken);
      report.setName(name);
      report.setDescription(description);

      /* merge variant */
      reportService.merge(report);

      /* merge reference */
      return (TsDiskReportReferenceDto) updateNode(referenceDto);
   }

   @Override
   public AbstractNodeDto setFlag(AbstractNodeDto node, long flagToSet, long FlagToUnset, boolean updateNode, Dto state)
         throws ServerCallFailedException {
      return null;
   }

   @Override
   public String[][] loadAllAsFto(Dto state) throws ServerCallFailedException {
      EntireTreeDTO d = loadAll(state, null, null);

      Collection<List<AbstractNodeDto>> values = d.getChildrenMap().values();
      ArrayList<String[]> res = new ArrayList<String[]>();

      for (List<AbstractNodeDto> nodes : values) {
         for (AbstractNodeDto node : nodes) {
            res.add(dtoService.dto2Fto(node));
         }
      }

      for (AbstractNodeDto node : d.getRoots()) {
         res.add(dtoService.dto2Fto(node));
      }

      return res.toArray(new String[][] {});
   }

   private boolean isFilter(Collection<Dto2PosoMapper> wlFilters, Collection<Dto2PosoMapper> blFilters) {
      return (null != wlFilters && !wlFilters.isEmpty()) || (null != blFilters && !blFilters.isEmpty());
   }

   private Set<Class<?>> convertFilters(boolean filter, Collection<Dto2PosoMapper> filters) {
      Collection<Dto2PosoMapper> cFilters = null;
      if (filter && null == filters)
         cFilters = new HashSet<Dto2PosoMapper>();
      else
         cFilters = filters;

      Set<Class<?>> filterList = new HashSet<Class<?>>();
      if (filter) {
         for (Dto2PosoMapper filterDtoMapper : cFilters)
            filterList.add(dtoService.getPosoFromDtoMapper(filterDtoMapper));
      }

      return filterList;
   }

   private boolean passesFilters(AbstractTsDiskNode node, boolean filter, Set<Class<?>> wlFilterList,
         Set<Class<?>> blFilterList) {
      if (!filter)
         return true;

      boolean passes = true;
      if (null != wlFilterList) {
         passes = false;
         for (Class<?> wlFilter : wlFilterList) {
            if (wlFilter.isAssignableFrom(node.getClass())) {
               passes = true;
               break;
            }
         }
      }
      if (!passes)
         return false;
      if (null != blFilterList) {
         for (Class<?> blFilter : blFilterList) {
            if (blFilter.isAssignableFrom(node.getClass())) {
               passes = false;
               break;
            }
         }
         if (!passes)
            return false;
      }

      return true;
   }

   @Override
   public String[][] getChildrenAsFto(AbstractNodeDto node, Dto state, final Collection<Dto2PosoMapper> wlFilters,
         final Collection<Dto2PosoMapper> blFilters) throws ServerCallFailedException {
      ArrayList<String[]> list = new ArrayList<String[]>();

      boolean filter = isFilter(wlFilters, blFilters);

      Set<Class<?>> wlFilterList = convertFilters(filter, wlFilters);
      Set<Class<?>> blFilterList = convertFilters(filter, blFilters);

      List<AbstractTsDiskNode> children = diskService.getNodeById(node.getId()).getChildrenSorted();

      for (AbstractTsDiskNode child : children) {

         TeamSpace teamSpace = diskService.getTeamSpaceFor(child);
         if (!teamSpaceService.mayAccess(teamSpace))
            continue;

         if (!passesFilters(child, filter, wlFilterList, blFilterList))
            continue;

         AbstractNodeDto dto = (AbstractNodeDto) dtoService.createDto(child, DtoView.LIST_FTO, DtoView.MINIMAL);
         list.add(dtoService.dto2Fto(dto));
      }

      return list.toArray(new String[][] {});
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Read.class)) })
   @Override
   public List<TsReferenceInfo> getReferenceInfosFor(@Named("node") ReportDto reportDto)
         throws ServerCallFailedException {
      Report report = (Report) dtoService.loadPoso(reportDto);

      List<TsDiskReportReference> referencesTo = diskService.getReferencesTo(report);

      List<TsReferenceInfo> referenceInfos = new ArrayList<>();
      for (TsDiskReportReference ref : referencesTo) {
         TsReferenceInfo refDto = new TsReferenceInfo();
         refDto.setHardlink(ref.isHardlink());

         TeamSpace ts = diskService.getTeamSpaceFor(ref);
         refDto.setTeamSpace((TeamSpaceDto) dtoService.createListDto(ts));

         referenceInfos.add(refDto);
      }

      return referenceInfos;
   }

}
