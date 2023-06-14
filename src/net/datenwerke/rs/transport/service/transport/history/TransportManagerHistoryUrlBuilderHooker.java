package net.datenwerke.rs.transport.service.transport.history;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.genrights.transport.TransportManagerAdminViewSecurityTarget;
import net.datenwerke.rs.core.service.history.helper.TreePanelHistoryUrlBuilderHooker;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.locale.RemoteServerManagerMessages;
import net.datenwerke.rs.transport.client.transport.TransportUIModule;
import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.rights.Read;

public class TransportManagerHistoryUrlBuilderHooker extends TreePanelHistoryUrlBuilderHooker {

   private final RemoteServerManagerMessages messages = LocalizationServiceImpl.getMessages(RemoteServerManagerMessages.class);

   private final static String HISTORY_BUILDER_NAME = "TransportManager";
   
   private final SecurityService securityService;

   @Inject
   public TransportManagerHistoryUrlBuilderHooker(SecurityService securityService) {
      this.securityService = securityService;
   }

   @Override
   public boolean consumes(Object o) {
      if (!(o instanceof AbstractTransportManagerNode))
         return false;
      if (securityService.checkRights(TransportManagerAdminViewSecurityTarget.class, Read.class))
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
      return TransportUIModule.TRANSPORT_FAV_HISTORY_TOKEN;
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
