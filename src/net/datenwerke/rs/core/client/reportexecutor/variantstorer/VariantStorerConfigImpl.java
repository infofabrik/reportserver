package net.datenwerke.rs.core.client.reportexecutor.variantstorer;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;

public class VariantStorerConfigImpl implements VariantStorerConfig {

   private final ReportExecutorDao executerDao;
   private final ReportExecutorUIService executorService;

   private boolean display = true;

   @Inject
   public VariantStorerConfigImpl(ReportExecutorDao executerDao, ReportExecutorUIService executorService) {
      this.executerDao = executerDao;
      this.executorService = executorService;
   }

   @Override
   public boolean displayVariantStorer() {
      return display;
   }

   public void setDisplay(boolean display) {
      this.display = display;
   }

   @Override
   public boolean allowEditVariant() {
      return true;
   }

   @Override
   public boolean displayEditVariantOnStore() {
      return true;
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
      return true;
   }

   @Override
   public VariantStorerHandleServerCalls getServerCallHandler() {
      return new VariantStorerHandleServerCalls() {

         @Override
         public void editVariant(ReportDto report, String executeToken, String name, String description,
               AsyncCallback<ReportDto> callback) {
            executerDao.editVariant(report, executeToken, name, description, callback);
         }

         @Override
         public void deleteVariant(ReportDto report, AsyncCallback<Void> callback) {
            executerDao.deleteVariant(report, callback);
         }

         @Override
         public void createNewVariant(ReportDto report, TeamSpaceDto teamSpace, TsDiskFolderDto folder,
               String executeToken, String name, String description, AsyncCallback<ReportDto> callback) {
            executorService.createNewVariant(report, teamSpace, folder, executeToken, name, description, callback);
         }
      };
   }

}
