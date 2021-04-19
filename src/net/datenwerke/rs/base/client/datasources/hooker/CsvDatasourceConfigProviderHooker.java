package net.datenwerke.rs.base.client.datasources.hooker;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.base.client.datasources.config.CsvDatasourceConfigConfigurator;
import net.datenwerke.rs.base.client.datasources.dto.CsvDatasourceDto;
import net.datenwerke.rs.base.client.datasources.locale.BaseDatasourceMessages;
import net.datenwerke.rs.base.client.datasources.ui.CsvDatasourceForm;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.hooks.DatasourceDefinitionConfigProviderHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class CsvDatasourceConfigProviderHooker implements
		DatasourceDefinitionConfigProviderHook {

	private final Provider<CsvDatasourceConfigConfigurator> configurator;
	private final Provider<CsvDatasourceForm> formProvider;
	
	@Inject
	public CsvDatasourceConfigProviderHooker(
		Provider<CsvDatasourceConfigConfigurator> configurator,
		Provider<CsvDatasourceForm> formProvider
		){
		
		/* store objects */
		this.configurator = configurator;
		this.formProvider = formProvider;
	}
	
	@Override
	public Map<? extends Class<? extends DatasourceDefinitionDto>, ? extends Provider<? extends DatasourceDefinitionConfigConfigurator>> getConfigs() {
		Map<Class<? extends DatasourceDefinitionDto>, Provider<? extends DatasourceDefinitionConfigConfigurator>> map =
			new HashMap<Class<? extends DatasourceDefinitionDto>, Provider<? extends DatasourceDefinitionConfigConfigurator>>();
				
		map.put(CsvDatasourceDto.class, configurator);
		
		return map;
	}

	@Override
	public boolean consumes(DatasourceDefinitionDto dsd) {
		return dsd instanceof CsvDatasourceDto;
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(
			DatasourceDefinitionDto dsd) {
		return Collections.singleton(formProvider.get());
	}

	@Override
	public Class<? extends AbstractDatasourceManagerNodeDto> getDatasourceClass() {
		return CsvDatasourceDto.class;
	}

	@Override
	public String getDatasourceName() {
		return BaseDatasourceMessages.INSTANCE.csvDatasourceTypeName();
	}

	@Override
	public AbstractDatasourceManagerNodeDto instantiateDatasource() {
		return new CsvDatasourceDto();
	}

	@Override
	public ImageResource getDatasourceIcon() {
		return BaseIcon.fromFileExtension("csv").toImageResource();
	}
	
	@Override
	public boolean isAvailable() {
		return true;
	}

}
