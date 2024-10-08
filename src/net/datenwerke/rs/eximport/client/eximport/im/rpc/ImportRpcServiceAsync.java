package net.datenwerke.rs.eximport.client.eximport.im.rpc;

import java.util.Collection;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportPostProcessConfigDto;

public interface ImportRpcServiceAsync {

   void performImport(Map<String, ImportConfigDto> configMap, Map<String, ImportPostProcessConfigDto> postProcessMap,
         AsyncCallback<Void> callback);

   void reset(AsyncCallback<Void> callback);

   void invalidateConfig(AsyncCallback<Void> callback);

   void initViaFile(AsyncCallback<Collection<String>> callback);

   void uploadXML(String xmldata, AsyncCallback<Collection<String>> callback);

   void setUseMergeImporter(boolean value, AsyncCallback<Void> callback);

}
