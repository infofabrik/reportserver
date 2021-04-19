package net.datenwerke.rs.core.client.contexthelp.rpc;

import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;



public interface ContextHelpRpcServiceAsync {

	public void getContextHelp(ContextHelpInfo info,
			AsyncCallback<String> callback);


}
