package net.datenwerke.rs.core.server.datasources;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.base.service.datasources.DatasourceHelperService;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceRpcService;
import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceTreeLoader;
import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceTreeManager;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.AbstractDatasourceManagerNode;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.server.TreeDBManagerTreeHandler;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;

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
   public DatasourceManagerTreeHandlerRpcServiceImpl(DatasourceService datasourceService, DtoService dtoGenerator,
         SecurityService securityService, EntityClonerService entityClonerService, DatasourceHelperService datasourceHelperService) {

      super(datasourceService, dtoGenerator, securityService, entityClonerService);

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
   protected void nodeCloned(AbstractDatasourceManagerNode clonedNode) {
      if (!(clonedNode instanceof DatasourceDefinition))
         throw new IllegalArgumentException();
      DatasourceDefinition datasource = (DatasourceDefinition) clonedNode;

      datasource.setName(datasource.getName() == null ? "copy" : datasource.getName() + " (copy)");
   }

   @Override
   public Map<String, SafeHtml> getDatasourceInfoDetailsAsHtml(DatabaseDatasourceDto datasourceDto) {
      DatabaseDatasource src = (DatabaseDatasource) dtoService.loadPoso(datasourceDto);
      Map<String, Object> datasourceInfo = datasourceHelperService.fetchInfoDatasourceMetadata(src);
      Map<String, SafeHtml> result = new HashMap<>();
      datasourceHelperService.getDatasourceInfoDefinition().forEach((key, mapSpecficInfoDef) -> {
         Map<String, String> specificInfoDef = (Map<String, String>) mapSpecficInfoDef;
         result.put(key, buildTableInfo(datasourceInfo, specificInfoDef));
      });
   return result;
   }
   
   private SafeHtml buildTableInfo(Map<String, Object> datasourceInfo, Map<String, String> info) {
      SafeHtmlBuilder builder = new SafeHtmlBuilder();
      builder = builder
            .appendHtmlConstant("<table style=\"width:100%\">")
            .appendHtmlConstant("<tr>")
            .appendHtmlConstant("<th>").appendEscaped("Description").appendHtmlConstant("</th>")
            .appendHtmlConstant("<th>")
            .appendHtmlConstant("</th>")
            .appendHtmlConstant("</tr>");

      for(String key : info.keySet()) {
         
         String result = datasourceInfo.get(info.get(key)).toString();
         builder = builder
               .appendHtmlConstant("<tr>")
               .appendHtmlConstant("<td>").appendEscaped(key + ":").appendHtmlConstant("</td>")
               .appendHtmlConstant("<td>").appendEscaped(result).appendHtmlConstant("</td>")
               .appendHtmlConstant("</tr>");       
      }
      builder = builder.appendHtmlConstant("</table>");
   return builder.toSafeHtml();
   }

}
