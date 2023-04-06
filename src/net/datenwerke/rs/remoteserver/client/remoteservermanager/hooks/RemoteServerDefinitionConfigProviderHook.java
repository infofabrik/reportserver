package net.datenwerke.rs.remoteserver.client.remoteservermanager.hooks;

import java.util.Collection;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;

public interface RemoteServerDefinitionConfigProviderHook extends Hook {

   boolean consumes(RemoteServerDefinitionDto remoteServerDefinition);

   Collection<? extends MainPanelView> getAdminViews(RemoteServerDefinitionDto remoteServerDefinition);

   Class<? extends AbstractRemoteServerManagerNodeDto> getRemoteServerClass();

   String getRemoteServerName();

   AbstractRemoteServerManagerNodeDto instantiateRemoteServer();

   ImageResource getRemoteServerIcon();

   boolean isAvailable();

}
