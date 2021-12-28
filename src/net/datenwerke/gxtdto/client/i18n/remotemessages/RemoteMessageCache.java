package net.datenwerke.gxtdto.client.i18n.remotemessages;

import java.util.HashMap;

import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gxtdto.client.i18n.remotemessages.rpc.RemoteMessageRpcServiceAsync;


public class RemoteMessageCache {
	
	private static RemoteMessageCache instance;
	private HashMap<String, HashMap<String, String>> cache;
	
	
	public static RemoteMessageCache getInstance(){
		if(null == instance)
			instance = new RemoteMessageCache();
		
		return instance;
	}
	
	private RemoteMessageCache() {
		
	}
	
	public void init(RemoteMessageRpcServiceAsync service, final AsyncCallback<Void> initCompleteCallback){
		 service.getMessages(getCurrentLocale(), new AsyncCallback<HashMap<String,HashMap<String,String>>>() {
			
			@Override
			public void onSuccess(HashMap<String, HashMap<String, String>> result) {
				cache = result;
				initCompleteCallback.onSuccess(null);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				initCompleteCallback.onFailure(caught);
			}
		});
	}
	
	private String getCurrentLocale() {
		String locale = Window.Location.getParameter("locale");
		if(null == locale){
			locale = Cookies.getCookie("locale");
		}
		 return locale;
	}
	
	public String getMessage(String msgClass, String key){
		if(null == cache)
			return "null";
		
		if(! cache.containsKey(msgClass)) {
			System.out.println("No such message file "+msgClass+". While locating key " + key);
//			System.out.println("Known files are " + cache.keySet());
			return "NULL";
//			throw new RuntimeException("No such message file "+msgClass+". While locating key " + key);
		}

		HashMap<String, String> keys = cache.get(msgClass);
		
		if(! keys.containsKey(key)){
			System.out.println("No such key " + key + " in message file " + msgClass);
			System.out.println("Known keys are " + keys.keySet());
			return "NULL";
//			throw new RuntimeException("No such key " + key + " in message file " + msgClass);
		}
			
		return keys.get(key);
	}
	
	

}
