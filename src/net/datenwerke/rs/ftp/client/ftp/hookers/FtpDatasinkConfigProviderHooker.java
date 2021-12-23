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
import net.datenwerke.rs.ftp.client.ftp.FtpUiModule;
import net.datenwerke.rs.ftp.client.ftp.dto.FtpDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.ui.FtpDatasinkForm;

public class FtpDatasinkConfigProviderHooker implements
		DatasinkDefinitionConfigProviderHook {

	private final Provider<FtpDatasinkForm> formProvider;
	
	public final static String ACTIVE_MODE = "active-ftp";
	public final static String PASSIVE_MODE = "passive-ftp";
	
	@Inject
	public FtpDatasinkConfigProviderHooker(
		Provider<FtpDatasinkForm> formProvider
		){
		
		/* store objects */
		this.formProvider = formProvider;
	}
	
	@Override
	public boolean consumes(DatasinkDefinitionDto datasinkDefinition) {
		return getDatasinkClass().equals(datasinkDefinition.getClass());
	}

	@Override
	public Collection<? extends MainPanelView> getAdminViews(
			DatasinkDefinitionDto datasinkDefinition) {
		return Collections.singleton(formProvider.get());
	}

	@Override
	public Class<? extends AbstractDatasinkManagerNodeDto> getDatasinkClass() {
		return FtpUiModule.FTP_TYPE;
	}

	@Override
	public String getDatasinkName() {
		return FtpUiModule.FTP_NAME;
	}

	@Override
	public AbstractDatasinkManagerNodeDto instantiateDatasink() {
		return new FtpDatasinkDto();
	}
	
	@Override
	public ImageResource getDatasinkIcon() {
		return FtpUiModule.FTP_ICON.toImageResource();
	}
	
	@Override
	public boolean isAvailable() {
		return true;
	}
}
