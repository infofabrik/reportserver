package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto.GeneralInfoDto;

public interface GeneralInfoRpcServiceAsync {

	void loadGeneralInfo(AsyncCallback<GeneralInfoDto> transformAndKeepCallback);

}
