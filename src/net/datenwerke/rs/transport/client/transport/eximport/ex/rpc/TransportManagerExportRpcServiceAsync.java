package net.datenwerke.rs.transport.client.transport.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.transport.client.transport.dto.AbstractTransportManagerNodeDto;

public interface TransportManagerExportRpcServiceAsync {
   void quickExport(AbstractTransportManagerNodeDto dto, AsyncCallback<Void> callback);

   void loadResult(AsyncCallback<String> callback);
}