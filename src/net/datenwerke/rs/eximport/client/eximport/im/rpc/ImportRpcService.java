package net.datenwerke.rs.eximport.client.eximport.im.rpc;

import java.util.Collection;
import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportPostProcessConfigDto;

@RemoteServiceRelativePath("importrpc")
public interface ImportRpcService extends RemoteService {

   public Collection<String> initViaFile() throws ServerCallFailedException;

   public Collection<String> uploadXML(String xmldata) throws ServerCallFailedException;

   public void setUseMergeImporter(boolean value) throws ServerCallFailedException;

   public void reset() throws ServerCallFailedException;

   public void invalidateConfig() throws ServerCallFailedException;

   public void performImport(Map<String, ImportConfigDto> configMap,
         Map<String, ImportPostProcessConfigDto> postProcessMap) throws ServerCallFailedException;

}
