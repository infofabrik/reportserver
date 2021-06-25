package net.datenwerke.rs.base.client.datasources;

import java.util.ArrayList;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.datasources.hooker.ArgumentDatasourceConnectorConfigHooker;
import net.datenwerke.rs.base.client.datasources.hooker.CsvDatasourceConfigProviderHooker;
import net.datenwerke.rs.base.client.datasources.hooker.DatabaseDatasourceConfigProviderHooker;
import net.datenwerke.rs.base.client.datasources.hooker.TextDatasourceConnectorConfigHooker;
import net.datenwerke.rs.base.client.datasources.hooker.UrlDatasourceConnectorConfigHooker;
import net.datenwerke.rs.base.client.datasources.hooks.DatasourceConnectorConfiguratorHook;
import net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto;
import net.datenwerke.rs.core.client.datasourcemanager.hooks.DatasourceDefinitionConfigProviderHook;

public class DatasourceExtensionUiStartup {

   @Inject
   public DatasourceExtensionUiStartup(HookHandlerService hookHandler,

         Provider<DatabaseDatasourceConfigProviderHooker> databaseConfigHooker,
         Provider<CsvDatasourceConfigProviderHooker> csvDatasourceConfigHooker,

         Provider<TextDatasourceConnectorConfigHooker> textDatasourceConfigHooker,
         Provider<UrlDatasourceConnectorConfigHooker> urlDatasourceConfigHooker,
         Provider<ArgumentDatasourceConnectorConfigHooker> argumentDatasourceConfigHooker,

         BaseDatasourceDao baseDatasourceDao, final BaseDatasourceUiService datasourcService) {

      /* datasources */
      hookHandler.attachHooker(DatasourceDefinitionConfigProviderHook.class, databaseConfigHooker, 10);
      hookHandler.attachHooker(DatasourceDefinitionConfigProviderHook.class, csvDatasourceConfigHooker, 20);

      /* connectors */
      hookHandler.attachHooker(DatasourceConnectorConfiguratorHook.class, textDatasourceConfigHooker,
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(DatasourceConnectorConfiguratorHook.class, urlDatasourceConfigHooker,
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(DatasourceConnectorConfiguratorHook.class, argumentDatasourceConfigHooker,
            HookHandlerService.PRIORITY_LOW);

      /* call server to get dbhelper */
      baseDatasourceDao.getDBHelperList(new RsAsyncCallback<ArrayList<DatabaseHelperDto>>() {
         @Override
         public void onSuccess(ArrayList<DatabaseHelperDto> result) {
            datasourcService.setDatabaseHelpers(result);
         }

         @Override
         public void onFailure(Throwable caught) {
            new DetailErrorDialog(caught).show();
         }
      });
   }
}
