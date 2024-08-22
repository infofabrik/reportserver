package net.datenwerke.rs.core.server.reportmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import groovy.lang.Closure;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.rpc.ReportManagerTreeLoader;
import net.datenwerke.rs.core.client.reportmanager.rpc.ReportManagerTreeManager;
import net.datenwerke.rs.core.service.reportmanager.ReportService;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.server.TreeDBManagerTreeHandler;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityServiceSecuree;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.treedb.actions.InsertAction;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
@Singleton
public class ReportManagerTreeHandlerImpl extends TreeDBManagerTreeHandler<AbstractReportManagerNode>
      implements ReportManagerTreeLoader, ReportManagerTreeManager {

   /**
    * 
    */
   private static final long serialVersionUID = -6463726280201202702L;

   final private ReportService reportManager;
   
   @Inject
   public ReportManagerTreeHandlerImpl(
         ReportService reportManager, 
         DtoService dtoGenerator,
         SecurityService securityService, 
         EntityClonerService entityClonerService,
         KeyNameGeneratorService keyGeneratorService
         ) {

      super(reportManager, 
            dtoGenerator, 
            securityService, 
            entityClonerService,
            keyGeneratorService);

      /* store objects */
      this.reportManager = reportManager;
   }

   @Override
   protected boolean allowDuplicateNode(AbstractReportManagerNode node) {
      return node instanceof Report && !(node instanceof ReportVariant);
   }

   @Override
   protected void nodeCloned(AbstractReportManagerNode clonedNode, AbstractReportManagerNode realNode) {
      if (!(clonedNode instanceof Report))
         throw new IllegalArgumentException();
      Report clone = (Report) clonedNode;
      Report real = (Report) realNode;

      Closure getAllNodes = new Closure(null) {
         public List<AbstractReportManagerNode> doCall() {
            return realNode.getParent().getChildren();
         }
      };
      clone.setName(clone.getName() == null
            ? keyGeneratorService.getNextCopyName("", getAllNodes)
            : keyGeneratorService.getNextCopyName(clone.getName(), getAllNodes));
      clone.setKey(keyGeneratorService.getNextCopyKey(real.getKey(), reportManager));
   }
   
   private void nodeClonedWithVariants(AbstractReportManagerNode clonedNode, AbstractReportManagerNode realNode,
         List<AbstractNodeDto> variants) {
      nodeCloned(clonedNode, realNode);
      
      Report clone = (Report) clonedNode;
      Report real = (Report) realNode;
      
      List<Long> variantIds = variants.stream()
            .map(AbstractNodeDto::getId).collect(Collectors.toList()); 

      List<AbstractReportManagerNode> children = new ArrayList<AbstractReportManagerNode>();
      real.getChildren().stream().forEach(variant -> {
         if (variantIds.contains(variant.getId())) {
            Report clonedChild = (Report) entityClonerService.cloneEntity(variant);
            clonedChild.setKey(keyGeneratorService.generateDefaultKey());
            clonedChild.setParent(clone);
            children.add(clonedChild);
            treeDBManager.persist(clonedNode);
         }
      });
      clone.setChildren(children);
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(name = "node", isDto = true, verify = @RightsVerification(rights = Write.class)) })
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto updateNode(@Named("node") AbstractNodeDto node, Dto state) throws ServerCallFailedException {
      /* check if there already is a report with the same key */
      if (node instanceof ReportDto) {
         String key = ((ReportDto) node).getKey();
         if (null != key && !"".equals(key.trim())) {
            try {
               long id = reportManager.getReportIdFromKey(((ReportDto) node).getKey());

               if (id != node.getId())
                  throw new ExpectedException("There is already a report with the same key: " + id);

               /*
                * if the report id is the same as the id of the report to be changed do nothing
                * because this is ok
                */
            } catch (NoResultException e) {
               /* do nothing because this is good */
            }
         }
      }

      return super.updateNode(node, state);
   }

   @SecurityChecked(loginRequired = true)
   @Transactional(rollbackOn = { Exception.class })
   @Override
   public String[][] getReportsInCatalog(ReportFolderDto folderDto, boolean showVariants)
         throws ServerCallFailedException {
      ArrayList<String[]> list = new ArrayList<String[]>();

      List<Report> allReports;
      if (null == folderDto)
         allReports = reportManager.getAllReports();
      else {
         allReports = new ArrayList<Report>();
         ReportFolder folder = (ReportFolder) dtoService.loadPoso(folderDto);
         for (AbstractReportManagerNode node : folder.getDescendants()) {
            if (node instanceof Report)
               allReports.add((Report) node);
         }
      }

      for (Report report : allReports) {
         /* we don't care about variants */
         if (!showVariants && report instanceof ReportVariant)
            continue;

         /* check rights */
         if (!securityService.checkRights(report, SecurityServiceSecuree.class, Read.class))
            continue;

         ReportDto dto = (ReportDto) dtoService.createListDto(report);
         list.add(dtoService.dto2Fto(dto));
      }

      return list.toArray(new String[][] {});
   }
   
   @Override
   protected void doSetInitialProperties(AbstractReportManagerNode inserted) {
      if (inserted instanceof Report) {
         ((Report)inserted).setKey(keyGeneratorService.generateDefaultKey(reportManager));
      }
   }
   
   @SecurityChecked(
         argumentVerification = {
               @ArgumentVerification(
                  name = "toDuplicate", 
                  isDto = true, 
                  verify = @RightsVerification(
                        rights = Read.class
                  ), 
                  parentChecks = @RightsVerification(
                        actions = InsertAction.class
                  )
            )
         }
   )
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto duplicateReportWithVariants(@Named("toDuplicate") AbstractNodeDto toDuplicate,
         List<AbstractNodeDto> variants, Dto state) throws ServerCallFailedException {

      AbstractReportManagerNode realNode = treeDBManager.getNodeById(toDuplicate.getId());
      if (!allowDuplicateNode(realNode))
         throw new ServerCallFailedException("node is not to be duplicated");
       AbstractReportManagerNode parent = realNode.getParent();
      
      AbstractReportManagerNode clonedNode = entityClonerService.cloneEntity(realNode);      
      nodeClonedWithVariants(clonedNode, realNode, variants);
      parent.addChild(clonedNode);
      treeDBManager.persist(clonedNode);
      
      return (AbstractNodeDto) dtoService.createDtoFullAccess(clonedNode);
   }
   
}
