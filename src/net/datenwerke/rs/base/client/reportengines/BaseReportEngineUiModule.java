package net.datenwerke.rs.base.client.reportengines;

import net.datenwerke.rs.base.client.reportengines.table.columnfilter.FilterUIModule;
import net.datenwerke.rs.base.client.reportengines.table.helpers.ColumnFilterWindow;
import net.datenwerke.rs.base.client.reportengines.table.helpers.ColumnFormatWindow;
import net.datenwerke.rs.base.client.reportengines.table.helpers.ColumnSelector;
import net.datenwerke.rs.base.client.reportengines.table.helpers.validator.NumericalFieldValidator;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.PreFilterUiModule;

import com.google.gwt.inject.client.AbstractGinModule;

public class BaseReportEngineUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(BaseReportEngineUiStartup.class).asEagerSingleton();
		
		/* submodules */
		install(new FilterUIModule());
		install(new PreFilterUiModule());
		
		/* static injection */
		requestStaticInjection(
			ColumnFilterWindow.class,
			ColumnFormatWindow.class,
			ColumnSelector.class,
			NumericalFieldValidator.class
		);
	}

}
