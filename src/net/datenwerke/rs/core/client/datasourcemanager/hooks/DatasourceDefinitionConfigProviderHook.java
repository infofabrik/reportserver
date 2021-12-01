package net.datenwerke.rs.core.client.datasourcemanager.hooks;

import java.util.Collection;
import java.util.Map;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Provider;

public interface DatasourceDefinitionConfigProviderHook extends Hook {

	boolean consumes(DatasourceDefinitionDto dsd);
	
	Collection<? extends MainPanelView> getAdminViews(
			DatasourceDefinitionDto dsd);

	Map<? extends Class<? extends DatasourceDefinitionDto>, ? extends Provider<? extends DatasourceDefinitionConfigConfigurator>> getConfigs();

	Class<? extends AbstractDatasourceManagerNodeDto> getDatasourceClass();

	String getDatasourceName();

	AbstractDatasourceManagerNodeDto instantiateDatasource();

	ImageResource getDatasourceIcon();

	boolean isAvailable();

}
