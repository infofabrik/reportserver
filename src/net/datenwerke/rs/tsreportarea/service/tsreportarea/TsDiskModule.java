package net.datenwerke.rs.tsreportarea.service.tsreportarea;

import com.google.inject.Singleton;

import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.vfs.TsFavoritesVFSModule;

public class TsDiskModule extends AbstractReportServerModule {

	public static final String USER_PROPERTY_VIEW_VERTICAL_SPLIT = "favoriteService:view:split";
	public static final String USER_PROPERTY_VIEW_VIEW_ID = "favoriteService:view:id";

	@Override
	protected void configure() {
		/* bind service */
		bind(TsDiskService.class).to(TsDiskServiceImpl.class).in(Singleton.class);
		
		/* startup */
		bind(TsDiskStartup.class).asEagerSingleton();
		
		/* sub modules */
		install(new TsFavoritesVFSModule());
	}

}
