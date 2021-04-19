package net.datenwerke.rs.saiku.client.saiku;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.config.ClientConfigDao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;

public class SaikuUiServiceImpl implements SaikuUiService {

	private final ClientConfigDao configDao;
	
	private HashMap<String,String> settings;
	
	@Inject
	public SaikuUiServiceImpl(ClientConfigDao configDao) {
		super();
		this.configDao = configDao;
	}

	@Override
	public void getSettings(final AsyncCallback<HashMap<String, String>> callback) {
		if(null != settings){
			callback.onSuccess(settings);
			return;
		}
		
		configDao.getConfigProperties("saiku.properties", new RsAsyncCallback<HashMap<String,String>>(){
			@Override
			public void onSuccess(HashMap<String, String> result) {
				settings = result;
				if(null == settings)
					settings = new HashMap<>();
				callback.onSuccess(settings);
			}
			@Override
			public void onFailure(Throwable caught) {
				settings = new HashMap<>();
				callback.onSuccess(settings);
			}
		});
	}
}
