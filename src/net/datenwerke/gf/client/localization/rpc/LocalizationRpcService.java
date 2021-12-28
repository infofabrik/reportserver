package net.datenwerke.gf.client.localization.rpc;

import java.util.Map;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;

@RemoteServiceRelativePath("localization")
public interface LocalizationRpcService extends RemoteService {
	
	public Map<String, String> getLanguageSelectorConfiguration() throws ServerCallFailedException;

	public void setUserLocale(String locale) throws ServerCallFailedException;

	void setUserTimezone(String timezone);
}
