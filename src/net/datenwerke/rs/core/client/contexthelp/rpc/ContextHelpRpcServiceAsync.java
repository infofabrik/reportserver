package net.datenwerke.rs.core.client.contexthelp.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;



public interface ContextHelpRpcServiceAsync {

	public void getContextHelp(ContextHelpInfo info,
			AsyncCallback<String> callback);


}
