package net.datenwerke.rs.ldapserver.client.ldapserver.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.rs.ldapserver.client.ldapserver.LdapServerUIModule;
import net.datenwerke.rs.ldapserver.client.ldapserver.ui.LdapServerForm;
import net.datenwerke.rs.ldapserver.client.ldapserver.dto.LdapServerDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.hooks.RemoteServerDefinitionConfigProviderHook;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerMessages;

public class LdapConfigProviderHooker implements RemoteServerDefinitionConfigProviderHook {

   private final Provider<LdapServerForm> formProvider;

   @Inject
   public LdapConfigProviderHooker(Provider<LdapServerForm> formProvider) {

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
      return LdapServerUIModule.TYPE;
   }

   @Override
   public String getRemoteServerName() {
      return RemoteServerMessages.INSTANCE.newLdapServer();
   }

   @Override
   public AbstractRemoteServerManagerNodeDto instantiateRemoteServer() {
      return new LdapServerDto();
   }

   @Override
   public ImageResource getRemoteServerIcon() {
      return LdapServerUIModule.ICON.toImageResource();
   }

   @Override
   public boolean isAvailable() {
      return true;
   }
}
