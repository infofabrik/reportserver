package net.datenwerke.gf.client.theme.rpc;

import net.datenwerke.gf.client.theme.dto.ThemeUiConfig;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface ThemeRpcServiceAsync {

	void loadUiConfig(AsyncCallback<ThemeUiConfig> callback);


}
