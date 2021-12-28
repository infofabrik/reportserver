package net.datenwerke.rs.core.client.datasinkmanager.hooks;

import java.util.Collection;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;

public interface DatasinkDefinitionConfigProviderHook extends Hook {

   boolean consumes(DatasinkDefinitionDto datasinkDefinition);

   Collection<? extends MainPanelView> getAdminViews(DatasinkDefinitionDto datasinkDefinition);

   Class<? extends AbstractDatasinkManagerNodeDto> getDatasinkClass();

   String getDatasinkName();

   AbstractDatasinkManagerNodeDto instantiateDatasink();

   ImageResource getDatasinkIcon();

   boolean isAvailable();

}
