package net.datenwerke.rs.tsreportarea.client.tsreportarea;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

/**
 * 
 *
 */
public class TsDiskUIModule extends AbstractGinModule {

	public static final String TEAMSPACE_OPEN_ITEM_HISTORY_TOKEN = "tsexec";
	public static final String TEAMSPACE_SELECT_ITEM_HISTORY_TOKEN = "tsselect";
	
	public static final String USER_PROPERTY_VIEW_VERTICAL_SPLIT = "favoriteService:view:split";
	public static final String USER_PROPERTY_VIEW_VIEW_ID = "favoriteService:view:id";
	
	@Override
	protected void configure() {
		/* service */
		bind(TsDiskUIService.class).to(TsDiskUIServiceImpl.class).in(Singleton.class);
		
		/* startup */
		bind(TsDiskUIStartup.class).asEagerSingleton();
	}

}
