package net.datenwerke.rs.transport.server.transport;

import static java.util.stream.Collectors.toList;
import static net.datenwerke.rs.utils.exception.shared.LambdaExceptionUtil.rethrowConsumer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.eximport.ExportDataAnalyzerService;
import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.eximport.exceptions.InvalidImportDocumentException;
import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.eximport.obj.ExportedItem;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.transport.TransportApplyFailedException;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkFolder;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.rs.core.service.genrights.transport.TransportManagerAdminViewSecurityTarget;
import net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.eximport.service.eximport.im.http.HttpImportConfiguration;
import net.datenwerke.rs.eximport.service.eximport.im.http.HttpImportService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.dto.TransportCheckEntryDto;
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.client.transport.dto.TransportElementDto;
import net.datenwerke.rs.transport.client.transport.rpc.TransportRpcService;
import net.datenwerke.rs.transport.service.transport.PreconditionResult;
import net.datenwerke.rs.transport.service.transport.TransportService;
import net.datenwerke.rs.transport.service.transport.TransportTreeService;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.utils.instancedescription.annotations.InstanceDescription;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.service.treedb.AbstractNode;
import nu.xom.Element;
import nu.xom.Elements;

@Singleton
public class TransportRpcServiceImpl extends SecuredRemoteServiceServlet
	implements TransportRpcService {

    /**
    * 
    */
    private static final long serialVersionUID = 38635573532715367L;
    private final Provider<TransportService> transportServiceProvider;
    private final Provider<TransportTreeService> transportTreeServiceProvider;
    private final Provider<DtoService> dtoServiceProvider;
    private final Provider<SecurityService> securityServiceProvider;
    private final Provider<ExportDataAnalyzerService> analizerServiceProvider;
    private final Provider<HttpImportService> httpImportServiceProvider;

    @Inject
    public TransportRpcServiceImpl(
	    Provider<TransportService> transportServiceProvider, 
	    Provider<DtoService> dtoServiceProvider,
	    Provider<TransportTreeService> transportTreeServiceProvider,
	    Provider<SecurityService> securityServiceProvider,
	    Provider<ExportDataAnalyzerService> analizerServiceProvider,
	    Provider<HttpImportService> httpImportServiceProvider
	    ) {

	/* store objects */
	this.transportServiceProvider = transportServiceProvider;
	this.dtoServiceProvider = dtoServiceProvider;
	this.transportTreeServiceProvider = transportTreeServiceProvider;
	this.securityServiceProvider = securityServiceProvider;
	this.analizerServiceProvider = analizerServiceProvider;
	this.httpImportServiceProvider = httpImportServiceProvider;
    }

    @Override
    @Transactional(rollbackOn = { Exception.class })
    public void addAllDescendants(TransportDto transportDto, AbstractNodeDto elementDto, boolean includeVariants) 
          throws ServerCallFailedException {
       Transport transport = (Transport) dtoServiceProvider.get().loadPoso(transportDto);
       
       securityServiceProvider.get().assertRights(transport, Write.class);
       
       AbstractNode<?> element = (AbstractNode<?>) dtoServiceProvider.get().loadPoso(elementDto);
       List<AbstractNode> descendants = (List<AbstractNode>) element.getDescendants();
       for (AbstractNode descendant : descendants) {
         if (!(descendant instanceof ReportFolder)
               && !(descendant instanceof ReportVariant)
               && !(descendant instanceof DatasinkFolder)
               && !(descendant instanceof FileServerFolder)
               && !(descendant instanceof DatasourceFolder)) {
            try {
               transportServiceProvider.get().addElement(transport, descendant, includeVariants);
            } catch (Exception e) {
               throw new ServerCallFailedException(e);
            }
         }
       }
       
    }
    
    @Override
    @Transactional(rollbackOn = { Exception.class })
    public void addElement(TransportDto transportDto, AbstractNodeDto elementDto, boolean includeVariants) 
          throws ServerCallFailedException {
       Transport transport = (Transport) dtoServiceProvider.get().loadPoso(transportDto);
       
       securityServiceProvider.get().assertRights(transport, Write.class);
       
       AbstractNode<?> element = (AbstractNode<?>) dtoServiceProvider.get().loadPoso(elementDto);
       
       try {
          transportServiceProvider.get().addElement(transport, element, includeVariants);
       } catch (Exception e) {
          throw new ServerCallFailedException(e);
       }
    }

    @Override
    @SecurityChecked(
          genericTargetVerification = {
                @GenericTargetVerification(
                      target = TransportManagerAdminViewSecurityTarget.class, 
                      verify = @RightsVerification(
                            rights = Read.class
                      )
                )
          }
    )
    @Transactional(rollbackOn = { Exception.class })
    public List<TransportDto> loadImportedTransports() throws ServerCallFailedException {
       return transportTreeServiceProvider.get().getTransportsByStatus(TransportService.Status.IMPORTED.name())
             .stream()
             .filter(transport -> securityServiceProvider.get().checkRights(transport, Read.class))
             .map(transport -> (TransportDto) dtoServiceProvider.get().createDto(transport))
             .collect(toList());
    }
    
    @Override
    @SecurityChecked(
          genericTargetVerification = {
                @GenericTargetVerification(
                      target = TransportManagerAdminViewSecurityTarget.class, 
                      verify = @RightsVerification(
                            rights = Read.class
                      )
                )
          }
    )
    @Transactional(rollbackOn = { Exception.class })
    public void rpull() throws ServerCallFailedException {
       try {
          transportServiceProvider.get().rpull();
       } catch (Exception e) {
          throw new ServerCallFailedException(e);
       }
    }

   @Override
   @SecurityChecked(
         genericTargetVerification = {
               @GenericTargetVerification(
                     target = TransportManagerAdminViewSecurityTarget.class, 
                     verify = @RightsVerification(
                           rights = Read.class
                     )
               )
         }
   )
   public void apply(TransportDto transportDto) throws ServerCallFailedException {
      Transport transport = (Transport) dtoServiceProvider.get().loadPoso(transportDto);
            
      Optional<ImmutablePair<ImportResult, Exception>> result = transportServiceProvider.get().applyTransport(transport);
      
      if (!result.isPresent()) 
         throw new TransportApplyFailedException("Preconditions are not met. Details can be found in the apply log.");
      
      if (result.get().getRight() != null) {
         throw new TransportApplyFailedException(ExceptionUtils.getRootCauseMessage(result.get().getRight()), result.get().getRight());
      }
   }

   @Override
   @SecurityChecked(
         genericTargetVerification = {
               @GenericTargetVerification(
                     target = TransportManagerAdminViewSecurityTarget.class, 
                     verify = @RightsVerification(
                           rights = Read.class
                     )
               )
         }
   )
   @Transactional(rollbackOn = { Exception.class })
   public void removeElements(TransportDto transportDto, List<ImportTreeModel> elementsToRemove) throws ServerCallFailedException {
      Transport transport = (Transport) dtoServiceProvider.get().loadPoso(transportDto);
      
      securityServiceProvider.get().assertRights(transport, Write.class);
      
      List<TransportElementDto> toRemove = new ArrayList<>();
      elementsToRemove.stream().forEach(e -> {
         String[] splitArray = e.getName().split("\\(|\\)");
         String key = splitArray[splitArray.length - 1];
         TransportElementDto elementToRemove = new TransportElementDto(e.getType(), key);
         toRemove.add(elementToRemove);
      });
      transportServiceProvider.get().removeElements(transport, toRemove);
   }
   
   @Override
   @SecurityChecked(
         genericTargetVerification = {
               @GenericTargetVerification(
                     target = TransportManagerAdminViewSecurityTarget.class, 
                     verify = @RightsVerification(
                           rights = Read.class
                     )
               )
         }
   )
   public List<TransportCheckEntryDto> checkPreconditions(TransportDto transportDto) throws ServerCallFailedException {
      Transport transport = (Transport) dtoServiceProvider.get().loadPoso(transportDto);
      
      securityServiceProvider.get().assertRights(transport, Execute.class);

      Map<String, PreconditionResult> analysisResults = transportServiceProvider.get()
            .analyzeApplyPreconditions(transport);
      
      return analysisResults.keySet()
         .stream()
         .sorted()
         .map(key -> { 
               PreconditionResult result = analysisResults.get(key);
               return new TransportCheckEntryDto(key, result.getResult().name(),
                     result.getErrorMsg().isPresent() ? result.getErrorMsg().get() : "");
            })
         .collect(toList());
      
   }
   
   @Override
   @SecurityChecked(
         genericTargetVerification = {
               @GenericTargetVerification(
                     target = TransportManagerAdminViewSecurityTarget.class, 
                     verify = @RightsVerification(
                           rights = Read.class
                     )
               )
         }
   )
   @Transactional(rollbackOn = { Exception.class })
   public List<ImportTreeModel> extractTreeModel(TransportDto transportDto) throws ServerCallFailedException {
      Transport transport = (Transport) dtoServiceProvider.get().loadPoso(transportDto);
      
      securityServiceProvider.get().assertRights(transport, Read.class);
      
      HttpImportService httpImportService = httpImportServiceProvider.get();
      HttpImportConfiguration currentConfiguration = httpImportService.createNewConfig();
      List<ImportTreeModel> importTreeParentList = new ArrayList<>();

      try {
         httpImportService.setData(transport.getXml());
         Collection<Class<? extends Exporter>> exporters = analizerServiceProvider.get()
               .getExporterClasses(currentConfiguration.getImportData());
         exporters.stream().forEach(rethrowConsumer(exporter -> {
            ImportTreeModel parentImportTreeModel = new ImportTreeModel();
            parentImportTreeModel.setId(java.util.UUID.randomUUID().toString());
            parentImportTreeModel.setName(exporter.getName());
            List<ImportTreeModel> importTreeChildrenList = new ArrayList<>();
            try {
               Class cls = Class.forName(exporter.getName());
               List<ExportedItem> children = httpImportService.getExportedItemsFor(cls);
               buildChildTreeModel(importTreeChildrenList, children);
            } catch (ClassNotFoundException e) {
               throw new ServerCallFailedException(e);
            }

            findAndAddChildren(importTreeChildrenList, parentImportTreeModel);
            importTreeParentList.add(parentImportTreeModel);
         }));
      } catch (InvalidImportDocumentException | ClassNotFoundException e) {
         throw new ServerCallFailedException(e.getMessage(), e);
      }

      return importTreeParentList;
   }

   private void buildChildTreeModel(List<ImportTreeModel> importTreeChildrenList, List<ExportedItem> children)
         throws ServerCallFailedException {
      children.stream().forEach(rethrowConsumer(child -> {
         ImportTreeModel childImportTreeModel = new ImportTreeModel();
         childImportTreeModel.setId(child.getId());
         String name = child.getElement().getAttributeValue("type");
         Elements childElements = child.getElement().getChildElements();
         String description = null;
         String referenceId = null;
         String username = null;

         for (Element childElement : childElements) {
            if ("name".equals(childElement.getAttributeValue("name"))) {
               description = childElement.getValue();
            }
         }

         for (Element childElement : childElements) {
            if ("key".equals(childElement.getAttributeValue("name"))) {
               description = description + " (" + childElement.getValue() + ")";
            }
            // for users
            if (childElement.getAttributeValue("name").equals("username")) {
               username = childElement.getValue();
            }
            if (null != childElement.getAttributeValue("referenceId")) {
               referenceId = childElement.getAttributeValue("referenceId");
            }
         }

         Class<?> clazz = null;
         try {
            clazz = Class.forName(child.getElement().getAttributeValue("type"));
            name = description != null ? description : username;
         } catch (ClassNotFoundException e) {
            throw new ServerCallFailedException(e);
         }

         InstanceDescription instanceDescription = clazz.getAnnotation(InstanceDescription.class);

         childImportTreeModel.setName(name);
         if (null != instanceDescription) {
            childImportTreeModel.setType(instanceDescription.icon());
            childImportTreeModel.setType(instanceDescription.icon());
         } else {
            // default icon, if not exist
            childImportTreeModel.setType(BaseIcon.FILE.toString());
         }

         if (null != child.getId()) {
            childImportTreeModel.setId(child.getId());
         }

         childImportTreeModel.setParentId(referenceId);
         importTreeChildrenList.add(childImportTreeModel);
      }));
   }

   private void findAndAddChildren(List<ImportTreeModel> importTreeChildrenList,
         ImportTreeModel parentImportTreeModel) {
      importTreeChildrenList.stream().forEach(child -> {
         Optional<ImportTreeModel> value = importTreeChildrenList.stream()
               .filter(it -> it.getId().equals(child.getParentId())).findFirst();
         if (null == child.getParentId()) {
            child.setParentId(parentImportTreeModel.getId());
            parentImportTreeModel.addChild(child);
         } else if (value.isPresent()) {
            importTreeChildrenList.stream().filter(it -> it.getId().equals(child.getParentId())).findFirst().get()
                  .addChild(child);
         }
      });
   }
}
