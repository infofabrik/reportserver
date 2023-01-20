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
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.properties.PropertiesUtilService;
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
   public DatasourceManagerTreeHandlerRpcServiceImpl(
         DatasourceService datasourceService, 
         DtoService dtoGenerator,
         SecurityService securityService, 
         EntityClonerService entityClonerService,
         DatasourceHelperService datasourceHelperService
         ) {

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

}
