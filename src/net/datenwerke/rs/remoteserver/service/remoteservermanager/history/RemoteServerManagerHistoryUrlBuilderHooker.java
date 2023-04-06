package net.datenwerke.rs.remoteserver.service.remoteservermanager.history;

import com.google.inject.Inject;

import net.datenwerke.rs.remoteserver.client.remoteservermanager.RemoteServerUIModule;
import net.datenwerke.rs.core.service.genrights.remoteservers.RemoteServerManagerAdminViewSecurityTarget;
import net.datenwerke.rs.core.service.history.helper.TreePanelHistoryUrlBuilderHooker;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.locale.RemoteServerManagerMessages;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.rights.Read;

public class RemoteServerManagerHistoryUrlBuilderHooker extends TreePanelHistoryUrlBuilderHooker {

   private final RemoteServerManagerMessages messages = LocalizationServiceImpl.getMessages(RemoteServerManagerMessages.class);

   private final static String HISTORY_BUILDER_NAME = "RemoteServerManager";
   
   private final SecurityService securityService;

   @Inject
   public RemoteServerManagerHistoryUrlBuilderHooker(SecurityService securityService) {
      this.securityService = securityService;
   }

   @Override
   public boolean consumes(Object o) {
      if (!(o instanceof AbstractRemoteServerManagerNode))
         return false;
      if (securityService.checkRights(RemoteServerManagerAdminViewSecurityTarget.class, Read.class))
         return true;
      else {
         if (!(o instanceof SecurityTarget))
            return false;
         else
            return securityService.checkRights((SecurityTarget) o, Read.class);
      }
   }

   @Override
   protected String getTokenName() {
      return RemoteServerUIModule.REMOTE_SERVER_FAV_HISTORY_TOKEN;
   }

   @Override
   protected String getBuilderId() {
      return HISTORY_BUILDER_NAME;
   }

   @Override
   protected String getNameFor(Object o) {
      return messages.historyUrlBuilderName();
   }

   @Override
   protected String getIconFor(Object o) {
      return messages.historyUrlBuilderIcon();
   }

}
