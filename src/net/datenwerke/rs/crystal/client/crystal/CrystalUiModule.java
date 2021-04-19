package net.datenwerke.rs.crystal.client.crystal;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class CrystalUiModule extends AbstractGinModule {

	public static final String UPLOAD_REPORT_ID_FIELD = "reportID";
	public static final String UPLOAD_HANDLER_ID = "crystalreport_upload_handler";

	
	@Override
	protected void configure() {
		bind(CrystalUiService.class).to(CrystalUiServiceImpl.class).in(Singleton.class);

		bind(CrystalUiStartup.class).asEagerSingleton();
	}


}
