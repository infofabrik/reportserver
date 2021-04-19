package net.datenwerke.rs.samba.client.samba.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.samba.client.samba.dto.SambaDatasinkDto;
import net.datenwerke.rs.samba.client.samba.ui.SambaDatasinkForm;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class SambaDatasinkConfigProviderHooker implements
        DatasinkDefinitionConfigProviderHook {

    private final Provider<SambaDatasinkForm> formProvider;
    
    @Inject
    public SambaDatasinkConfigProviderHooker(
        Provider<SambaDatasinkForm> formProvider
        ){
        
        /* store objects */
        this.formProvider = formProvider;
    }
    
    @Override
    public boolean consumes(DatasinkDefinitionDto datasinkDefinition) {
        return SambaDatasinkDto.class.equals(datasinkDefinition.getClass());
    }

    @Override
    public Collection<? extends MainPanelView> getAdminViews(
            DatasinkDefinitionDto datasinkDefinition) {
        return Collections.singleton(formProvider.get());
    }

    @Override
    public Class<? extends AbstractDatasinkManagerNodeDto> getDatasinkClass() {
        return SambaDatasinkDto.class;
    }

    @Override
    public String getDatasinkName() {
        return "Samba";
    }

    @Override
    public AbstractDatasinkManagerNodeDto instantiateDatasink() {
        return new SambaDatasinkDto();
    }
    
    @Override
    public ImageResource getDatasinkIcon() {
        return BaseIcon.ANGLE_DOUBLE_UP.toImageResource();
    }
    
    @Override
    public boolean isAvailable() {
        return true;
    }
}