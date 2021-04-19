package net.datenwerke.gf.client.localization.rpc;

import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LocalizationRpcServiceAsync {

	void getLanguageSelectorConfiguration(
			AsyncCallback<Map<String, String>> callback);

	void setUserLocale(String locale, AsyncCallback<Void> callback);

	void setUserTimezone(String timezone, AsyncCallback<Void> callback);

}