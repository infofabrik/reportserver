package net.datenwerke.rs.keyutils.client.keyutils;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.keyutils.client.keyutils.rpc.KeyUtilsRpcServiceAsync;

public class KeyUtilsDao extends Dao{
   
   private final KeyUtilsRpcServiceAsync keyUtilsRpcService;

   @Inject
   public KeyUtilsDao(KeyUtilsRpcServiceAsync keyUtilsRpcService) {
      this.keyUtilsRpcService = keyUtilsRpcService;
   }
   public void generateDefaultKey(AsyncCallback<String> callback) {
      keyUtilsRpcService.generateDefaultKey(transformAndKeepCallback(callback));
   }
}
