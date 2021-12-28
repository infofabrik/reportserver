package net.datenwerke.rs.incubator.client.scriptdatasource.hooker;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.hooks.DatasourceDefinitionConfigProviderHook;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.incubator.client.scriptdatasource.config.ScriptDatasourceConfigConfigurator;
import net.datenwerke.rs.incubator.client.scriptdatasource.dto.ScriptDatasourceDto;
import net.datenwerke.rs.incubator.client.scriptdatasource.locale.ScriptDatasourceMessages;
import net.datenwerke.rs.incubator.client.scriptdatasource.ui.ScriptDatasourceForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ScriptDatasourceConfigProviderHooker implements
		DatasourceDefinitionConfigProviderHook {

	private final Provider<ScriptDatasourceConfigConfigurator> configurator;
	private final Provider<ScriptDatasourceForm> formProvider;
	private final EnterpriseUiService enterpriseService;
	
	@Inject
	public ScriptDatasourceConfigProviderHooker(
		Provider<ScriptDatasourceConfigConfigurator> configurator,
		Provider<ScriptDatasourceForm> formProvider,
		EnterpriseUiService enterpriseService
		){
		
		/* store objects */
		this.configurator = configurator;
		this.formProvider = formProvider;
		this.enterpriseService = enterpriseService;
	}
	
	@Override
	public Map<? extends Class<? extends DatasourceDefinitionDto>, ? extends Provider<? extends DatasourceDefinitionConfigConfigurator>> getConfigs() {
		Map<Class<? extends DatasourceDefinitionDto>, Provider<? extends DatasourceDefinitionConfigConfigurator>> map =
			new HashMap<Class<? extends DatasourceDefinitionDto>, Provider<? extends DatasourceDefinitionConfigConfigurator>>();
				
		map.put(ScriptDatasourceDto.class, configurator);
		
		return map;
	}

	@Override
	public boolean consumes(DatasourceDefinitionDto dsd) {
		return dsd instanceof ScriptDatasourceDto;
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(
			DatasourceDefinitionDto dsd) {
		return Collections.singleton(formProvider.get());
	}

	@Override
	public Class<? extends AbstractDatasourceManagerNodeDto> getDatasourceClass() {
		return ScriptDatasourceDto.class;
	}

	@Override
	public String getDatasourceName() {
		return ScriptDatasourceMessages.INSTANCE.scriptDatasourceTypeName();
	}

	@Override
	public AbstractDatasourceManagerNodeDto instantiateDatasource() {
		return new ScriptDatasourceDto();
	}

	@Override
	public ImageResource getDatasourceIcon() {
		return BaseIcon.SCRIPT.toImageResource();
	}

	@Override
	public boolean isAvailable() {
		return enterpriseService.isEnterprise();
	}
}
