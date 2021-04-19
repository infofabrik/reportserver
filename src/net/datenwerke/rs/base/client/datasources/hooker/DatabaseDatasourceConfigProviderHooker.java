package net.datenwerke.rs.base.client.datasources.hooker;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.base.client.datasources.config.DatabaseDatasourceConfigConfigurator;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.base.client.datasources.locale.BaseDatasourceMessages;
import net.datenwerke.rs.base.client.datasources.ui.DatabaseDatasourceForm;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.hooks.DatasourceDefinitionConfigProviderHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class DatabaseDatasourceConfigProviderHooker implements
		DatasourceDefinitionConfigProviderHook {

	private final Provider<DatabaseDatasourceConfigConfigurator> dsdDatabaseConfigurator;
	private final Provider<DatabaseDatasourceForm> formProvider;
	
	@Inject
	public DatabaseDatasourceConfigProviderHooker(
		Provider<DatabaseDatasourceConfigConfigurator> dsdDatabaseConfigurator,
		Provider<DatabaseDatasourceForm> formProvider
		){
		
		/* store objects */
		this.dsdDatabaseConfigurator = dsdDatabaseConfigurator;
		this.formProvider = formProvider;
	}
	
	@Override
	public Map<? extends Class<? extends DatasourceDefinitionDto>, ? extends Provider<? extends DatasourceDefinitionConfigConfigurator>> getConfigs() {
		Map<Class<? extends DatasourceDefinitionDto>, Provider<? extends DatasourceDefinitionConfigConfigurator>> map =
			new HashMap<Class<? extends DatasourceDefinitionDto>, Provider<? extends DatasourceDefinitionConfigConfigurator>>();
				
		map.put(DatabaseDatasourceDto.class, dsdDatabaseConfigurator);
		
		return map;
	}

	@Override
	public boolean consumes(DatasourceDefinitionDto dsd) {
		return DatabaseDatasourceDto.class.equals(dsd.getClass());
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(
			DatasourceDefinitionDto dsd) {
		return Collections.singleton(formProvider.get());
	}

	@Override
	public Class<? extends AbstractDatasourceManagerNodeDto> getDatasourceClass() {
		return DatabaseDatasourceDto.class;
	}

	@Override
	public String getDatasourceName() {
		return BaseDatasourceMessages.INSTANCE.databaseTypeName();
	}

	@Override
	public AbstractDatasourceManagerNodeDto instantiateDatasource() {
		return new DatabaseDatasourceDto();
	}
	
	@Override
	public ImageResource getDatasourceIcon() {
		return BaseIcon.DATABASE.toImageResource();
	}
	
	@Override
	public boolean isAvailable() {
		return true;
	}
}
