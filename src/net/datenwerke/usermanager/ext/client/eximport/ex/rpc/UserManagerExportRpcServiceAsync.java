package net.datenwerke.usermanager.ext.client.eximport.ex.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;

public interface UserManagerExportRpcServiceAsync {

	void quickExport(AbstractUserManagerNodeDto dto,
			AsyncCallback<Void> callback);

	void loadResult(AsyncCallback<String> callback);

}
