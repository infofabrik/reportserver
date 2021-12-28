package net.datenwerke.rs.core.client.i18tools;

import javax.inject.Inject;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.i18n.I18nToolsUIService;
import net.datenwerke.rs.core.client.i18tools.dto.FormatPatternsDto;
import net.datenwerke.rs.core.client.i18tools.rpc.I18nToolsRpcServiceAsync;

public class I18nToolsDao extends Dao {

   private final I18nToolsRpcServiceAsync rpcService;
   private final I18nToolsUIService i18nToolsService;

   @Inject
   public I18nToolsDao(I18nToolsRpcServiceAsync rpcService, I18nToolsUIService i18nToolsService) {
      this.rpcService = rpcService;
      this.i18nToolsService = i18nToolsService;
   }

   public void getDecimalSeparator(AsyncCallback<String> callback) {
      rpcService.getDecimalSeparator(callback);
   }

   public void setDecimalSeparator(String separator, AsyncCallback<Void> callback) {
      if (i18nToolsService instanceof I18nToolsUiServiceImpl)
         ((I18nToolsUiServiceImpl) i18nToolsService).setUserDecimalSeparator(separator);
      rpcService.setDecimalSeparator(separator, callback);
   }

   public void getFormatPatterns(AsyncCallback<FormatPatternsDto> callback) {
      rpcService.getFormatPatterns(callback);
   }
}
