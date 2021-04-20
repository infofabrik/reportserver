package net.datenwerke.rs.scp.client.scp.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.scp.client.scp.dto.ScpDatasinkDto;
import net.datenwerke.rs.scp.client.scp.ui.ScpDatasinkForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ScpDatasinkConfigProviderHooker implements DatasinkDefinitionConfigProviderHook {

	private final Provider<ScpDatasinkForm> formProvider;

	@Inject
	public ScpDatasinkConfigProviderHooker(Provider<ScpDatasinkForm> formProvider) {

		/* store objects */
		this.formProvider = formProvider;
	}

	@Override
	public boolean consumes(DatasinkDefinitionDto datasinkDefinition) {
		return ScpDatasinkDto.class.equals(datasinkDefinition.getClass());
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(DatasinkDefinitionDto datasinkDefinition) {
		return Collections.singleton(formProvider.get());
	}

	@Override
	public Class<? extends AbstractDatasinkManagerNodeDto> getDatasinkClass() {
		return ScpDatasinkDto.class;
	}

	@Override
	public String getDatasinkName() {
		return "SCP";
	}

	@Override
	public AbstractDatasinkManagerNodeDto instantiateDatasink() {
		return new ScpDatasinkDto();
	}

	@Override
	public ImageResource getDatasinkIcon() {
		return BaseIcon.ARROW_UP.toImageResource();
	}

	@Override
	public boolean isAvailable() {
		return true;
	}

}
