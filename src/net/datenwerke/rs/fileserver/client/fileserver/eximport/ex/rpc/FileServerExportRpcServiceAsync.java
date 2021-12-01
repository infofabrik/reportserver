package net.datenwerke.rs.fileserver.client.fileserver.eximport.ex.rpc;

import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface FileServerExportRpcServiceAsync {

	void quickExport(AbstractFileServerNodeDto dto, AsyncCallback<Void> callback);

	void loadResult(AsyncCallback<String> callback);

}
