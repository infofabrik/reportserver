package net.datenwerke.rs.remotersrestserver.client.remotersrestserver.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.RemoteRsRestServerUIModule;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.dto.RemoteRsRestServerDto;
import net.datenwerke.rs.remotersrestserver.client.remotersrestserver.ui.RemoteRsRestServerForm;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.hooks.RemoteServerDefinitionConfigProviderHook;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerMessages;

public class RemoteServerConfigProviderHooker implements RemoteServerDefinitionConfigProviderHook {

   private final Provider<RemoteRsRestServerForm> formProvider;

   @Inject
   public RemoteServerConfigProviderHooker(Provider<RemoteRsRestServerForm> formProvider) {

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
      return RemoteRsRestServerUIModule.TYPE;
   }

   @Override
   public String getRemoteServerName() {
      return RemoteServerMessages.INSTANCE.newRemoteRsServer();
   }

   @Override
   public AbstractRemoteServerManagerNodeDto instantiateRemoteServer() {
      return new RemoteRsRestServerDto();
   }

   @Override
   public ImageResource getRemoteServerIcon() {
      return RemoteRsRestServerUIModule.ICON.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return true;
   }
}
