package net.datenwerke.gxtdto.client.utilityservices;

import net.datenwerke.gxtdto.client.utilityservices.grid.GridHelperService;
import net.datenwerke.gxtdto.client.utilityservices.grid.GridHelperServiceImpl;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerService;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerServiceImpl;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarServiceImpl;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

public class UtilsUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* bind service */
		bind(UtilsUIService.class).to(UtilsUIServiceImpl.class).in(Singleton.class);

		bind(GridHelperService.class).to(GridHelperServiceImpl.class).in(Singleton.class);
		bind(ToolbarService.class).to(ToolbarServiceImpl.class).in(Singleton.class);
		bind(SubmitTrackerService.class).to(SubmitTrackerServiceImpl.class).in(Singleton.class);
	}

}
