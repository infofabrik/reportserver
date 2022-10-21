package net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers;

import java.util.Objects;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ViolatedSecurityExceptionDto;
import net.datenwerke.rs.core.client.reportexecutor.ExecuteReportConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexecutor.variantstorer.VariantStorerConfig;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskTreeManagerDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.config.TeamSpaceViewConfig;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.GeneralReferenceHandlerHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

public class TsDiskReportHandler implements GeneralReferenceHandlerHook {

   private final ReportExecutorUIService reportExecutorService;
   private final TeamSpaceUIService teamSpaceService;
   private final TsDiskDao favoriteDao;
   private final TsDiskTreeManagerDao treeManagerDao;

   @Inject
   public TsDiskReportHandler(
         ReportExecutorUIService reportExecutorService, 
         TeamSpaceUIService teamSpaceService,
         TsDiskDao favoriteDao, 
         TsDiskTreeManagerDao treeManagerDao
         ) {
      this.reportExecutorService = reportExecutorService;
      this.teamSpaceService = teamSpaceService;
      this.favoriteDao = favoriteDao;
      this.treeManagerDao = treeManagerDao;
   }

   @Override
   public boolean consumes(TsDiskGeneralReferenceDto item) {
      return item instanceof TsDiskReportReferenceDto;
   }

   @Override
   public void handle(TsDiskGeneralReferenceDto genReference, TsDiskMainComponent mainComponent) {
      TsDiskReportReferenceDto reference = (TsDiskReportReferenceDto) genReference;

      if (null == reference.getReport())
         new DwAlertMessageBox(BaseMessages.INSTANCE.error(), TsFavoriteMessages.INSTANCE.reportCouldNotBeLoaded())
               .show();
      else
         displayReport(reference.getReport(), reference, mainComponent);
   }

   private void displayReport(ReportDto report, final TsDiskReportReferenceDto reference,
         final TsDiskMainComponent mainComponent) {
      reportExecutorService.executeReport(report, null, new ExecuteReportConfiguration() {

         private TsDiskReportReferenceDto currentReference = reference;

         @Override
         public boolean handleError(Throwable t) {
            if (t instanceof ViolatedSecurityExceptionDto) {
               new DwAlertMessageBox(TsFavoriteMessages.INSTANCE.violatedSecurityTitle(),
                     TsFavoriteMessages.INSTANCE.violatedSecurityMessage()).show();
               return true;
            }
            return false;
         }

         @Override
         public boolean acceptView(String viewId) {
            return true;
         }

         @Override
         public String getUrlParameters() {
            String params = "ts:" + mainComponent.getCurrentSpace().getId();
            if (null != mainComponent.getCurrentFolder())
               params += "&tsf:" + mainComponent.getCurrentFolder().getId();
            return params;
         }

         @Override
         public VariantStorerConfig getVariantStorerConfig() {
            return new VariantStorerConfig() {
               @Override
               public boolean displayVariantStorer() {
                  return teamSpaceService.isUser(mainComponent.getCurrentSpace());
               }

               @Override
               public boolean allowEditVariant() {
                  return currentReference.isHardlink();
               }

               @Override
               public boolean displayEditVariantOnStore() {
                  return false;
               }

               @Override
               public TeamSpaceDto getTeamSpace() {
                  return mainComponent.getCurrentSpace();
               }

               @Override
               public TsDiskFolderDto getTeamSpaceFolder() {
                  return mainComponent.getCurrentFolder();
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
                           final AsyncCallback<ReportDto> callback) {
                        mainComponent.mask(BaseMessages.INSTANCE.storingMsg());
                        reference.setName(name);
                        reference.setDescription(description);
                        favoriteDao.updateReferenceAndReport(currentReference, report, executeToken, name, description,
                              new RsAsyncCallback<TsDiskReportReferenceDto>() {
                                 @Override
                                 public void onSuccess(TsDiskReportReferenceDto result) {
                                    mainComponent.unmask();
                                    currentReference = result;
                                    callback.onSuccess(result.getReport());
                                 }

                                 @Override
                                 public void onFailure(Throwable caught) {
                                    mainComponent.unmask();
                                 }
                              });
                     }

                     @Override
                     public void deleteVariant(ReportDto report, final AsyncCallback<Void> callback) {
                        mainComponent.mask(BaseMessages.INSTANCE.storingMsg());
                        treeManagerDao.deleteNode(currentReference, new RsAsyncCallback<Void>() {
                           @Override
                           public void onSuccess(Void result) {
                              mainComponent.unmask();
                              callback.onSuccess(null);
                           }

                           @Override
                           public void onFailure(Throwable caught) {
                              mainComponent.unmask();
                           }
                        });
                     }

                     @Override
                     public void createNewVariant(ReportDto report, final TeamSpaceDto teamSpace,
                           final TsDiskFolderDto folder, String executeToken, String name, String desc,
                           final AsyncCallback<ReportDto> callback) {
                        /* import report */
                        mainComponent.mask(BaseMessages.INSTANCE.storingMsg());
                        favoriteDao.createAndImportVariant(teamSpace, folder, report, executeToken, name, desc,
                              new RsAsyncCallback<TsDiskReportReferenceDto>() {
                                 @Override
                                 public void onSuccess(TsDiskReportReferenceDto result) {
                                    if (null == result)
                                       return;

                                    mainComponent.unmask();

                                    if (Objects.equals(teamSpace, mainComponent.getCurrentSpace())
                                          && Objects.equals(folder, mainComponent.getCurrentFolder())) {
                                       mainComponent.addToStore(result);
                                       currentReference = result;
                                    }

                                    callback.onSuccess(result.getReport());
                                 }

                                 @Override
                                 public void onFailure(Throwable caught) {
                                    mainComponent.unmask();
                                 }
                              });
                     }
                  };
               }
            };
         }

         @Override
         public String getViewId() {
            return null;
         };

      }, new TeamSpaceViewConfig(mainComponent.getCurrentSpace()));

   }
}
