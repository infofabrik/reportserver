package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;

public interface DatasinkManagerExportRpcServiceAsync {
   void quickExport(AbstractDatasinkManagerNodeDto dto, AsyncCallback<Void> callback);

   void loadResult(AsyncCallback<String> callback);
}