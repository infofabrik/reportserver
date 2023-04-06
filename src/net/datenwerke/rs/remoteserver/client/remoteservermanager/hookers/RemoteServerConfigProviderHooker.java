package net.datenwerke.rs.remoteserver.client.remoteservermanager.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerUIModule;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.helper.forms.RemoteServerForm;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.hooks.RemoteServerDefinitionConfigProviderHook;

public class RemoteServerConfigProviderHooker implements RemoteServerDefinitionConfigProviderHook {

   private final Provider<RemoteServerForm> formProvider;

   @Inject
   public RemoteServerConfigProviderHooker(Provider<RemoteServerForm> formProvider) {

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
      return "New Remote Server";
   }

   @Override
   public AbstractRemoteServerManagerNodeDto instantiateRemoteServer() {
      return new RemoteServerDto();
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
