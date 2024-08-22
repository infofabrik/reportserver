package net.datenwerke.rs.core.server.datasources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NoResultException;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import groovy.lang.Closure;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.datasources.DatasourceInfoType;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.base.service.datasources.DatasourceHelperService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.locale.DatasourcesMessages;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceRpcService;
import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceTreeLoader;
import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceTreeManager;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.properties.PropertiesUtilService;
import net.datenwerke.security.server.TreeDBManagerTreeHandler;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
@Singleton
public class DatasourceManagerTreeHandlerRpcServiceImpl extends TreeDBManagerTreeHandler<AbstractDatasourceManagerNode>
      implements DatasourceTreeLoader, DatasourceTreeManager, DatasourceRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -455777535667237770L;

   private final DatasourceService datasourceService;
   private final DatasourceHelperService datasourceHelperService;

   @Inject
   public DatasourceManagerTreeHandlerRpcServiceImpl(
         DatasourceService datasourceService, 
         DtoService dtoGenerator,
         SecurityService securityService, 
         EntityClonerService entityClonerService,
         DatasourceHelperService datasourceHelperService,
         KeyNameGeneratorService keyGeneratorService
         ) {

      super(datasourceService, 
            dtoGenerator, 
            securityService, 
            entityClonerService,
            keyGeneratorService);

      /* store objects */
      this.datasourceService = datasourceService;
      this.datasourceHelperService = datasourceHelperService;
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public DatasourceDefinitionDto getDefaultDatasource() throws ServerCallFailedException {
      DatasourceDefinition ds = datasourceService.getDefaultDatasource();

      if (null == ds)
         return null;

      if (!securityService.checkRights(ds, Read.class))
         return null;

      return (DatasourceDefinitionDto) dtoService.createDto(ds);
   }

   @Override
   protected boolean allowDuplicateNode(AbstractDatasourceManagerNode node) {
      return node instanceof DatasourceDefinition;
   }

   @Override
   protected void nodeCloned(AbstractDatasourceManagerNode clonedNode, AbstractDatasourceManagerNode realNode) {
      if (!(clonedNode instanceof DatasourceDefinition))
         throw new IllegalArgumentException();
      DatasourceDefinition clone = (DatasourceDefinition) clonedNode;
      DatasourceDefinition real = (DatasourceDefinition) realNode;
      Closure getAllNodes = new Closure(null) {
         public List<AbstractDatasourceManagerNode> doCall() {
            return realNode.getParent().getChildren();
         }
      };
      clone.setName(clone.getName() == null
            ? keyGeneratorService.getNextCopyName("", getAllNodes)
            : keyGeneratorService.getNextCopyName(clone.getName(), getAllNodes));
      clone.setKey(keyGeneratorService.getNextCopyKey(real.getKey(), datasourceService));
   }

   @Override
   public Map<DatasourceInfoType, SafeHtml> getDatasourceInfoDetailsAsHtml(DatabaseDatasourceDto datasourceDto)
         throws ServerCallFailedException {
      DatabaseDatasource datasource = (DatabaseDatasource) dtoService.loadPoso(datasourceDto);
      Map<String, Object> datasourceInfo;
      try {
         datasourceInfo = datasourceHelperService.fetchInfoDatasourceMetadata(datasource, true, true, true, true);
      } catch (Exception e) {
         throw new ServerCallFailedException("Could not retrieve datasource metadata", e);
      }
      Map<DatasourceInfoType, SafeHtml> result = new HashMap<>();
      datasourceHelperService.getDatasourceInfoDefinition().forEach((key, mapSpecificInfoDef) -> {
         Map<String, String> specificInfoDef = (Map<String, String>) mapSpecificInfoDef;
         result.put(key, buildTableInfo(datasource, key, datasourceInfo, specificInfoDef));
      });
      return result;
   }

   private SafeHtml buildTableInfo(DatabaseDatasource datasource, DatasourceInfoType type, Map<String, Object> datasourceInfo,
         Map<String, String> info) {
      String tdOpenTag = "<td class=\"rs-text-wrapping\">";

      SafeHtmlBuilder builder = new SafeHtmlBuilder();
      builder = builder.appendHtmlConstant("<table style=\"width:90%; table-layout:fixed\">");

      for (String key : info.keySet()) {
         Object res = datasourceInfo.get(info.get(key));
         String result = null == res ? "null" : res.toString();
         builder = builder
               .appendHtmlConstant("<tr>")
               .appendHtmlConstant(tdOpenTag).appendEscaped(key + ":").appendHtmlConstant("</td>")
               .appendHtmlConstant(tdOpenTag).appendEscaped(result).appendHtmlConstant("</td>")
               .appendHtmlConstant("</tr>");  
      }
      if (type == DatasourceInfoType.DATABASE) {
         String jdbcProperties = (null != datasource.parseJdbcProperties()
               ? PropertiesUtilService.convert(datasource.parseJdbcProperties()).toString()
               : "");
         builder = builder
               .appendHtmlConstant("<tr>")
               .appendHtmlConstant(tdOpenTag)
               .appendEscaped(DatasourcesMessages.INSTANCE.jdbcProperties() + ":").appendHtmlConstant("</td>")
               .appendHtmlConstant(tdOpenTag).appendEscaped(jdbcProperties).appendHtmlConstant("</td>")
               .appendHtmlConstant("</tr>");  
      }
      builder = builder.appendHtmlConstant("</table>");
      return builder.toSafeHtml();
   }
   
   @Override
   protected void doSetInitialProperties(AbstractDatasourceManagerNode inserted) {
      if (inserted instanceof DatasourceDefinition) {
         ((DatasourceDefinition)inserted).setKey(keyGeneratorService.generateDefaultKey(datasourceService));
      }
   }
   
   @SecurityChecked(
         argumentVerification = {
               @ArgumentVerification(
                     name = "node", 
                     isDto = true, 
                     verify = @RightsVerification(
                           rights = Write.class
                     )
               ) 
         }
   )
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto updateNode(@Named("node") AbstractNodeDto node, Dto state) throws ServerCallFailedException {
      /* check if there already is a datasource with the same key */
      if (node instanceof DatasourceDefinitionDto) {
         String key = ((DatasourceDefinitionDto) node).getKey();
         if (null != key && !"".equals(key.trim())) {
            try {
               long id = datasourceService.getDatasourceIdFromKey(((DatasourceDefinitionDto) node).getKey());

               if (id != node.getId())
                  throw new ExpectedException("There is already a datasource with the same key: " + id);

               /*
                * if the datasource id is the same as the id of the datasource to be changed do nothing
                * because this is ok
                */
            } catch (NoResultException e) {
               /* do nothing because this is good */
            }
         }
      }
      return super.updateNode(node, state);
   }
}
