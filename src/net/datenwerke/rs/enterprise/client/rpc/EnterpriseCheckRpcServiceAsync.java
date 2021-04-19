package net.datenwerke.rs.enterprise.client.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.enterprise.client.EnterpriseInformationDto;

public interface EnterpriseCheckRpcServiceAsync {

	void getEnterpriseInfos(AsyncCallback<EnterpriseInformationDto> transformAndKeepCallback);


}
