package net.datenwerke.gf.client.localization;

import java.util.Map;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.localization.rpc.LocalizationRpcServiceAsync;
import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;

public class LocalizationDao extends Dao {
	
	private final LocalizationRpcServiceAsync rpcService;

	@Inject
	public LocalizationDao(LocalizationRpcServiceAsync rpcService) {
		this.rpcService = rpcService;
	}
	
	
	public void getLanguageSelectorConfiguration(AsyncCallback<Map<String, String>> callback){
		rpcService.getLanguageSelectorConfiguration(callback);
	}

	public void setUserLocale(String locale, AsyncCallback<Void> callback){
		rpcService.setUserLocale(locale, callback);
	}
	
	public String getCurrentClientLocale() {
		String locale = Window.Location.getParameter("locale");
		if(null == locale){
			locale = Cookies.getCookie("locale");
		}
		
		return locale;
	}


	public void setUserTimezone(String timezone, RsAsyncCallback<Void> callback) {
		rpcService.setUserTimezone(timezone, callback);
	}
	
}
