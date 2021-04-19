package net.datenwerke.gxtdto.client.i18n.remotemessages;

import com.sencha.gxt.core.client.util.Format;


public abstract class DwRemoteMessage {
	
	protected RemoteMessageCache msgcache = RemoteMessageCache.getInstance();

	public String getMessage(String msgClass, String key, Object... args) {
		String msgtpl = msgcache.getMessage(msgClass, key);
		if(null == args){
			return msgtpl;
		}else{
			return Format.substitute(msgtpl, args);
		}
	}

}
