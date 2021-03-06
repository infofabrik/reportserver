package net.datenwerke.gf.client.theme;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.theme.dto.ThemeUiConfig;
import net.datenwerke.gf.client.theme.rpc.ThemeRpcServiceAsync;
import net.datenwerke.gxtdto.client.dtomanager.Dao;

public class ThemeDao extends Dao {

   private final ThemeRpcServiceAsync rpcService;

   @Inject
   public ThemeDao(ThemeRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void loadUiTheme(AsyncCallback<ThemeUiConfig> callback) {
      rpcService.loadUiConfig(callback);
   }

}
