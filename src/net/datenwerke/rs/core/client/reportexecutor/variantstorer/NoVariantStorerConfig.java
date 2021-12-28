package net.datenwerke.rs.core.client.reportexecutor.variantstorer;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;

public class NoVariantStorerConfig implements VariantStorerConfig {

   @Override
   public boolean displayVariantStorer() {
      return false;
   }

   @Override
   public boolean displayEditVariantOnStore() {
      return false;
   }

   @Override
   public boolean allowEditVariant() {
      return false;
   }

   @Override
   public TeamSpaceDto getTeamSpace() {
      return null;
   }

   @Override
   public TsDiskFolderDto getTeamSpaceFolder() {
      return null;
   }

   @Override
   public boolean allowNullTeamSpace() {
      return false;
   }

   @Override
   public VariantStorerHandleServerCalls getServerCallHandler() {
      return new VariantStorerHandleServerCalls() {
         @Override
         public void editVariant(ReportDto report, String executeToken, String name, String description,
               AsyncCallback<ReportDto> callback) {
         }

         @Override
         public void deleteVariant(ReportDto report, AsyncCallback<Void> callback) {
         }

         @Override
         public void createNewVariant(ReportDto report, TeamSpaceDto teamSpace, TsDiskFolderDto folder,
               String executeToken, String name, String desc, AsyncCallback<ReportDto> callback) {
         }
      };
   }

}
