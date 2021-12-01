package net.datenwerke.rs.ftp.client.ftp.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.ftp.client.ftp.dto.SftpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.ui.SftpDatasinkForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class SftpDatasinkConfigProviderHooker implements
		DatasinkDefinitionConfigProviderHook {

	private final Provider<SftpDatasinkForm> formProvider;
	
	@Inject
	public SftpDatasinkConfigProviderHooker(
		Provider<SftpDatasinkForm> formProvider
		){
		
		/* store objects */
		this.formProvider = formProvider;
	}
	
	@Override
	public boolean consumes(DatasinkDefinitionDto datasinkDefinition) {
		return SftpDatasinkDto.class.equals(datasinkDefinition.getClass());
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(
			DatasinkDefinitionDto datasinkDefinition) {
		return Collections.singleton(formProvider.get());
	}

	@Override
	public Class<? extends AbstractDatasinkManagerNodeDto> getDatasinkClass() {
		return SftpDatasinkDto.class;
	}

	@Override
	public String getDatasinkName() {
		return "SFTP";
	}

	@Override
	public AbstractDatasinkManagerNodeDto instantiateDatasink() {
		return new SftpDatasinkDto();
	}
	
	@Override
	public ImageResource getDatasinkIcon() {
		return BaseIcon.ARROW_CIRCLE_UP.toImageResource();
	}
	
	@Override
	public boolean isAvailable() {
		return true;
	}
}
