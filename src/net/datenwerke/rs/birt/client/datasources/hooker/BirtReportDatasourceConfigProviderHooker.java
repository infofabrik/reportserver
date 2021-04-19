package net.datenwerke.rs.birt.client.datasources.hooker;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.birt.client.datasources.config.BirtReportDatasourceConfigConfigurator;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto;
import net.datenwerke.rs.birt.client.datasources.ui.BirtReportDatasourceForm;
import net.datenwerke.rs.birt.client.reportengines.locale.BirtMessages;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.hooks.DatasourceDefinitionConfigProviderHook;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class BirtReportDatasourceConfigProviderHooker implements DatasourceDefinitionConfigProviderHook {
	
	private Provider<BirtReportDatasourceForm> formProvider;
	private Provider<BirtReportDatasourceConfigConfigurator> configurator;

	@Inject
	public BirtReportDatasourceConfigProviderHooker(
			Provider<BirtReportDatasourceForm> formProvider, 
			Provider<BirtReportDatasourceConfigConfigurator> configurator
			) {
				this.formProvider = formProvider;
				this.configurator = configurator;

	}

	@Override
	public boolean consumes(DatasourceDefinitionDto dsd) {
		return (dsd instanceof BirtReportDatasourceDefinitionDto);
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(DatasourceDefinitionDto dsd) {
		return Collections.singleton(formProvider.get());
	}

	@Override
	public Map<? extends Class<? extends DatasourceDefinitionDto>, ? extends Provider<? extends DatasourceDefinitionConfigConfigurator>> getConfigs() {
		
		Map<Class<? extends DatasourceDefinitionDto>, Provider<? extends DatasourceDefinitionConfigConfigurator>> map =
				new HashMap<Class<? extends DatasourceDefinitionDto>, Provider<? extends DatasourceDefinitionConfigConfigurator>>();

		map.put(BirtReportDatasourceDefinitionDto.class, configurator);

		return map;
	}

	@Override
	public Class<? extends AbstractDatasourceManagerNodeDto> getDatasourceClass() {
		return BirtReportDatasourceDefinitionDto.class;
	}

	@Override
	public String getDatasourceName() {
		return BirtMessages.INSTANCE.birtReportDatasourceTypeName();
	}

	@Override
	public AbstractDatasourceManagerNodeDto instantiateDatasource() {
		return new BirtReportDatasourceDefinitionDto();
	}

	@Override
	public ImageResource getDatasourceIcon() {
		return BaseIcon.REPORT.toImageResource();
	}
	
	@Override
	public boolean isAvailable() {
		return true;
	}

}
