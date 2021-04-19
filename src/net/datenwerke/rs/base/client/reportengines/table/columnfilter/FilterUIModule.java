package net.datenwerke.rs.base.client.reportengines.table.columnfilter;

import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.AbstractFilterAspect;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.DistinctSelectorPanel;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.GridView;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.LikeFilterComponent;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.SelectionPanel;
import net.datenwerke.rs.base.client.reportengines.table.helpers.filter.TextView;

import com.google.gwt.inject.client.AbstractGinModule;

/**
 * 
 *
 */
public class FilterUIModule extends AbstractGinModule {

	@Override
	protected void configure() {
		/* bind hook handler */
		bind(FilterUIStartup.class).asEagerSingleton();
		
		bind(FilterService.class).to(FilterServiceImpl.class);
		
		requestStaticInjection(DistinctSelectorPanel.class);
		requestStaticInjection(LikeFilterComponent.class);
		requestStaticInjection(SelectionPanel.class);
		requestStaticInjection(TextView.class);
		requestStaticInjection(GridView.class);
		requestStaticInjection(AbstractFilterAspect.class);
	}

}
