package net.datenwerke.rs.core.service.urlview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Provider;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.urlview.annotations.UrlViewConfig;
import net.datenwerke.rs.core.service.urlview.hooks.UrlViewServerHook;
import net.datenwerke.rs.utils.config.ConfigService;
import net.datenwerke.rs.utils.juel.SimpleJuel;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.SubnodeConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;

public class UrlViewModule extends AbstractModule {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	public static final String CONFIG_FILE = "ui/urlview.cf";
	public static final String VIEW_TYPES = "types";
	public static final String VIEW_URL = "url";
	public static final String VIEW_NAME = "name";
	public static final String VIEW_PRIORITY = "priority";

	
	@Override
	protected void configure() {
		
	}

	@Provides @UrlViewConfig @Inject
	public Map<String,Map<String, List<String[]>>> providerConfig(
		AuthenticatorService authenticatorService,
		ConfigService configService,
		HookHandlerService hookHandler, 
		RemoteMessageService remoteMessageService,
		LocalizationServiceImpl localizationService,
		Provider<SimpleJuel> simpleJuelProvider
		){
		
		/* result map */
		Map<String,Map<String, List<String[]>>> map = new HashMap<String,Map<String, List<String[]>>>();

		User currentUser = authenticatorService.getCurrentUser();
		UrlViewRestrictor restrictor = new UrlViewRestrictor(currentUser);
		
		/* simple juel name parser */
		SimpleJuel simpleJuelNameParser = simpleJuelProvider.get();
		String currentLanguage = localizationService.getLocale().getLanguage();
		simpleJuelNameParser.addReplacement("msgs", remoteMessageService.getMessages(currentLanguage));
		
		try{
			HierarchicalConfiguration config = (HierarchicalConfiguration) configService.getConfig(CONFIG_FILE);
			if(null == config)
				return map;
			
			for(String viewType : new String[]{"adminviews", "objectinfo"}){
				HashMap<String, List<String[]>> innerMap = new HashMap<String, List<String[]>>();
				map.put(viewType, innerMap);
				
				List views = config.configurationsAt(viewType + ".view");
				for(Iterator it = views.iterator(); it.hasNext();){
				    HierarchicalConfiguration sub = (HierarchicalConfiguration) ((HierarchicalConfiguration) it.next()).clone();
				    
				    try{
				    	SubnodeConfiguration restrictionConfig = sub.configurationAt("restrictTo");
				    	if(restrictor.restrictionApplies(restrictionConfig))
				    		continue;
				    } catch(IllegalArgumentException e){} // swallow
				    
				    try{
					    String[] list = sub.getString(VIEW_TYPES).split(",");
					    String url = sub.getString(VIEW_URL);
					    String name = sub.getString(VIEW_NAME);
					    
					    url = url == null ? "" : url;
					    name = parseName(name, simpleJuelNameParser);
					    name = name == null ? "" : name;
					    
					    for(String type : list){
					    	if(! innerMap.containsKey(type))
					    		innerMap.put(type, new ArrayList<String[]>());
					    	innerMap.get(type).add(new String[]{name, url});
					    }
				    }catch(Exception e){//swallow
				    }
				}
			}

			/* module */
			List views = config.configurationsAt("module.view");
			HashMap<String, List<String[]>> innerMap = new HashMap<String, List<String[]>>();
			map.put("module", innerMap);
			int module = 1;
			for(Iterator it = views.iterator(); it.hasNext();){
				HierarchicalConfiguration sub = (HierarchicalConfiguration) ((HierarchicalConfiguration) it.next()).clone();
			    try{
			    	SubnodeConfiguration restrictionConfig = sub.configurationAt("restrictTo");
			    	if(restrictor.restrictionApplies(restrictionConfig))
			    		continue;
			    } catch(IllegalArgumentException e){} // swallow
			    
			    try{
				    String url = sub.getString(VIEW_URL);
				    String name = sub.getString(VIEW_NAME);
				    String priority = sub.getString(VIEW_PRIORITY);
				    
				    url = url == null ? "" : url;
				    name = parseName(name, simpleJuelNameParser);
				    name = name == null ? "" : name;
				    
				    ArrayList<String[]> l = new ArrayList<String[]>();
				    if(null != priority)
				    	l.add(new String[]{name, url, priority});
				    else
				    	l.add(new String[]{name, url});
			    	innerMap.put("" + module++, l);
			    }catch(Exception e){//swallow
			    }
			}
			
			for(UrlViewServerHook hooker : hookHandler.getHookers(UrlViewServerHook.class)){
				Map<String,Map<String, List<String[]>>> hookedConfig = hooker.provideConfiguration(config);
				if(null != hookedConfig)
					map.putAll(hookedConfig);
			}
		} catch(Exception e){//swallow
			logger.warn( "Error parsing urlview config: ", e);
		}
			
		return map;
	}
	
	private String parseName(String name, SimpleJuel parser) {
		if(!name.contains("${"))
			return name;
		
		return parser.parse(name);
	}
	
}
