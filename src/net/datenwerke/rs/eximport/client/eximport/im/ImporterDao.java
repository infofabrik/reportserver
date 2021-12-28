package net.datenwerke.rs.eximport.client.eximport.im;

import java.util.Collection;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportPostProcessConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.rpc.ImportRpcServiceAsync;

public class ImporterDao extends Dao {

   private final ImportRpcServiceAsync rpcService;

   @Inject
   public ImporterDao(ImportRpcServiceAsync rpcService) {

      /* store objects */
      this.rpcService = rpcService;
   }

   public void uploadXML(String xmldata, AsyncCallback<Collection<String>> callback) {
      rpcService.uploadXML(xmldata, transformAndKeepCallback(callback));
   }

   public void performImport(Map<String, ImportConfigDto> configMap,
         Map<String, ImportPostProcessConfigDto> postProcessMap, AsyncCallback<Void> callback) {
      rpcService.performImport(configMap, postProcessMap, transformAndKeepCallback(callback));
   }

   public void reset(AsyncCallback<Void> callback) {
      rpcService.reset(transformAndKeepCallback(callback));
   }

   public void initViaFile(AsyncCallback<Collection<String>> callback) {
      rpcService.initViaFile(transformAndKeepCallback(callback));
   }

   public void invalidateConfig(AsyncCallback<Void> callback) {
      rpcService.invalidateConfig(transformAndKeepCallback(callback));
   }

}
