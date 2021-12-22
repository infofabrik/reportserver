package net.datenwerke.gf.client.config;

import com.google.inject.Inject;
import com.google.inject.Singleton;


@Singleton
public class ClientConfigServiceImpl implements ClientConfigService {

	private final ClientConfigXmlService jsonService;

	@Inject
	public ClientConfigServiceImpl(ClientConfigXmlService jsonService) {
		this.jsonService = jsonService;
	}
	@Override
	public void setMainConfig(String json) {
		jsonService.setXmlConfig(json);
	}
	
	@Override
	public boolean getBoolean(String property, boolean defaultValue){
		return jsonService.getBoolean(property, defaultValue);
	}

	@Override
	public String getString(String property, String defaultValue) {
		return jsonService.getString(property, defaultValue);
	}
	
}
