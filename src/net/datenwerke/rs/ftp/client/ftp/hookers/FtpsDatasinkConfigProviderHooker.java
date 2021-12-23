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
import net.datenwerke.rs.ftp.client.ftp.dto.FtpsDatasinkDto;
import net.datenwerke.rs.ftp.client.ftp.ui.FtpsDatasinkForm;

public class FtpsDatasinkConfigProviderHooker implements
DatasinkDefinitionConfigProviderHook {
    
    private final Provider<FtpsDatasinkForm> formProvider;
    
    public final static String ACTIVE_MODE = "active-ftps";
    public final static String PASSIVE_MODE = "passive-ftps";
    
    @Inject
    public FtpsDatasinkConfigProviderHooker(
        Provider<FtpsDatasinkForm> formProvider
        ){
        
        /* store objects */
        this.formProvider = formProvider;
    }

    @Override
    public boolean consumes(DatasinkDefinitionDto datasinkDefinition) {
        return getDatasinkClass().equals(datasinkDefinition.getClass());
    }

    @Override
    public Collection<? extends MainPanelView> getAdminViews(DatasinkDefinitionDto datasinkDefinition) {
        return Collections.singleton(formProvider.get());
    }

    @Override
    public Class<? extends AbstractDatasinkManagerNodeDto> getDatasinkClass() {
        return FtpUiModule.FTPS_TYPE;
    }

    @Override
    public String getDatasinkName() {
        return FtpUiModule.FTPS_NAME;
    }

    @Override
    public AbstractDatasinkManagerNodeDto instantiateDatasink() {
        return new FtpsDatasinkDto();
    }

    @Override
    public ImageResource getDatasinkIcon() {
        return FtpUiModule.FTPS_ICON.toImageResource();
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

}