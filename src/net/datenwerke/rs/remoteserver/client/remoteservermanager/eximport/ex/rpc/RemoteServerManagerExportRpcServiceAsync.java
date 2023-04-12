package net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;

public interface RemoteServerManagerExportRpcServiceAsync {
   void quickExport(AbstractRemoteServerManagerNodeDto dto, AsyncCallback<Void> callback);

   void loadResult(AsyncCallback<String> callback);
}