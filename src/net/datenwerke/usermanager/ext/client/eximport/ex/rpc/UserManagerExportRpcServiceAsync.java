package net.datenwerke.usermanager.ext.client.eximport.ex.rpc;

import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserManagerExportRpcServiceAsync {

	void quickExport(AbstractUserManagerNodeDto dto,
			AsyncCallback<Void> callback);

	void loadResult(AsyncCallback<String> callback);

}
