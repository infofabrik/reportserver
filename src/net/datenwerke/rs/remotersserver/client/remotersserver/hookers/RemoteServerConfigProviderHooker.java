package net.datenwerke.rs.remotersserver.client.remotersserver.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.remotersserver.client.remotersserver.dto.RemoteRsServerDto;
import net.datenwerke.rs.remotersserver.client.remotersserver.ui.RemoteRsServerForm;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerUIModule;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.hooks.RemoteServerDefinitionConfigProviderHook;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerMessages;

public class RemoteServerConfigProviderHooker implements RemoteServerDefinitionConfigProviderHook {

   private final Provider<RemoteRsServerForm> formProvider;

   @Inject
   public RemoteServerConfigProviderHooker(Provider<RemoteRsServerForm> formProvider) {

      /* store objects */
      this.formProvider = formProvider;
   }

   @Override
   public boolean consumes(RemoteServerDefinitionDto remoteServerDefinition) {
      return getRemoteServerClass().equals(remoteServerDefinition.getClass());
   }

   @Override
   public Collection<? extends MainPanelView> getAdminViews(RemoteServerDefinitionDto remoteServerDefinition) {
      return Collections.singleton(formProvider.get());
   }

   @Override
   public Class<? extends AbstractRemoteServerManagerNodeDto> getRemoteServerClass() {
      return RemoteServerUIModule.TYPE;
   }

   @Override
   public String getRemoteServerName() {
      return RemoteServerMessages.INSTANCE.newRemoteRsServer();
   }

   @Override
   public AbstractRemoteServerManagerNodeDto instantiateRemoteServer() {
      return new RemoteRsServerDto();
   }

   @Override
   public ImageResource getRemoteServerIcon() {
      return RemoteServerUIModule.ICON.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return true;
   }
}
