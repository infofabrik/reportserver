package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GeneralInfoRpcServiceAsync {

   void loadGeneralInfo(AsyncCallback<String> transformAndKeepCallback);

}
