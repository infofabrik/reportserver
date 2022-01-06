package net.datenwerke.rs.core.service.reportmanager.hookers;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.genrights.reportmanager.ReportManagerAdminViewSecurityTarget;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.search.service.search.hooks.SearchResultAllowerHook;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class ReportManagerNodeSearchResultCheckHooker implements SearchResultAllowerHook {

   private final SecurityService securityService;

   @Inject
   public ReportManagerNodeSearchResultCheckHooker(
         SecurityService securityService
         ) {
      this.securityService = securityService;
   }

   @Override
   public boolean allow(AbstractNode<? extends AbstractNode<?>> node) {
      if (node instanceof AbstractReportManagerNode)
         return securityService.checkRights(ReportManagerAdminViewSecurityTarget.class, Read.class);
      
      return true;
   }

}
