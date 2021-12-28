package net.datenwerke.gf.client.theme.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gf.client.theme.dto.ThemeUiConfig;


public interface ThemeRpcServiceAsync {

	void loadUiConfig(AsyncCallback<ThemeUiConfig> callback);


}
