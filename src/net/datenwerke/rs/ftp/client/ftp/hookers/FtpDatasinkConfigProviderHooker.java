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
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.ui.FtpDatasinkForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class FtpDatasinkConfigProviderHooker implements
		DatasinkDefinitionConfigProviderHook {

	private final Provider<FtpDatasinkForm> formProvider;
	
	@Inject
	public FtpDatasinkConfigProviderHooker(
		Provider<FtpDatasinkForm> formProvider
		){
		
		/* store objects */
		this.formProvider = formProvider;
	}
	
	@Override
	public boolean consumes(DatasinkDefinitionDto datasinkDefinition) {
		return FtpDatasinkDto.class.equals(datasinkDefinition.getClass());
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(
			DatasinkDefinitionDto datasinkDefinition) {
		return Collections.singleton(formProvider.get());
	}

	@Override
	public Class<? extends AbstractDatasinkManagerNodeDto> getDatasinkClass() {
		return FtpDatasinkDto.class;
	}

	@Override
	public String getDatasinkName() {
		return "FTP";
	}

	@Override
	public AbstractDatasinkManagerNodeDto instantiateDatasink() {
		return new FtpDatasinkDto();
	}
	
	@Override
	public ImageResource getDatasinkIcon() {
		return BaseIcon.UPLOAD.toImageResource();
	}
	
	@Override
	public boolean isAvailable() {
		return true;
	}
}
