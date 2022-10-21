package net.datenwerke.rs.tsreportarea.client.tsreportarea;

import java.util.Collection;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.history.HistoryCallback;
import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gf.client.history.HistoryUiService;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.eventbus.events.SubmoduleDisplayRequest;
import net.datenwerke.gxtdto.client.eventbus.events.SubmoduleDisplayRequest.ParentDisplayedCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoAdditionalInfoProvider;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectPreviewTabProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportSelectionRepositoryProviderHook;
import net.datenwerke.rs.core.client.urlview.hooks.UrlViewObjectInfoPostProcessorHook;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterPostProcessorConfiguratorHook;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceApp;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIModule;
import net.datenwerke.rs.teamspace.client.teamspace.hooks.TeamSpaceAppProviderHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskFolderDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskRootDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.simpleform.TsDiskProvider;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.simpleform.TsFolderProvider;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.simpleform.TsReportReferenceProvider;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers.ImportPostProcessorHooker;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers.MenuAddFolderHooker;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers.MenuDeleteHooker;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers.MenuExportHooker;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers.MenuOpenHooker;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers.MenuRenameHooker;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers.MenuTestHooker;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers.ReferenceCallHistoryCallback;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers.ReportSelectionTeamspaceRepositoryProvider;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers.TsDiskReportHandler;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers.TsUrlViewObjectInfoPostProcessor;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.GeneralReferenceHandlerHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteListViewHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.objectinfo.ReportInTeamSpaceObjectInfo;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.objectinfo.TsFavoriteReferenceObjectInfo;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.listviews.TsDiskGridListView;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.listviews.TsDiskIconListView;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class TsDiskUIStartup {

   @Inject
   public TsDiskUIStartup(final HookHandlerService hookHandler, final Provider<TsDiskTeamSpaceApp> teamSpaceAppProvider,
         final Provider<TsDiskGridListView> gridListViewProvider,
         final Provider<TsDiskIconListView> iconListViewProvider,

         final Provider<TsFavoriteReferenceObjectInfo> referenceObjectInfo,

         final Provider<MenuAddFolderHooker> addFolderHooker, final Provider<MenuRenameHooker> menuRenameHooker,
         final Provider<MenuTestHooker> menuTestHooker, final Provider<MenuDeleteHooker> menuDeleteHooker,
         final Provider<MenuExportHooker> exportBtnHooker, final Provider<MenuOpenHooker> openBtnHooker,

         final Provider<ImportPostProcessorHooker> importPostProcessorProvider,

         final Provider<TsFolderProvider> simpleFormFolderProvider,
         final Provider<TsReportReferenceProvider> simpleFormReportProvider,
         final Provider<TsDiskProvider> simpleFormDiskNodeProvider,

         final Provider<TsUrlViewObjectInfoPostProcessor> urlViewPostProcessor,

         final Provider<TsDiskReportHandler> reportReferenceHandler,

         final ReportSelectionTeamspaceRepositoryProvider reportTeamspaceRepositoryProvider,

         EventBus eventBus,

         final ReportInTeamSpaceObjectInfo reportInTeamspaceInfo,

         HistoryUiService historyService, final EventBus eventbus, final TsDiskTreeLoaderDao favoriteDao,
         final ReferenceCallHistoryCallback referenceCallCallbackProvider) {

      /* simpleform */
      hookHandler.attachHooker(FormFieldProviderHook.class, simpleFormFolderProvider, HookHandlerService.PRIORITY_HIGH);
      hookHandler.attachHooker(FormFieldProviderHook.class, simpleFormReportProvider);
      hookHandler.attachHooker(FormFieldProviderHook.class, simpleFormDiskNodeProvider);

      /* teamspace app */
      hookHandler.attachHooker(TeamSpaceAppProviderHook.class, new TeamSpaceAppProviderHook(teamSpaceAppProvider));

      /* generalref handler */
      hookHandler.attachHooker(GeneralReferenceHandlerHook.class, reportReferenceHandler);

      /* report selection provider */
      hookHandler.attachHooker(ReportSelectionRepositoryProviderHook.class, reportTeamspaceRepositoryProvider);

      /* list views */
      hookHandler.attachHooker(TsFavoriteListViewHook.class, gridListViewProvider, HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(TsFavoriteListViewHook.class, iconListViewProvider, HookHandlerService.PRIORITY_LOWER);

      /* object infos */
      hookHandler.attachHooker(ObjectPreviewTabProviderHook.class, referenceObjectInfo);
      hookHandler.attachHooker(ObjectInfoAdditionalInfoProvider.class, reportInTeamspaceInfo);

      /* urlview */
      hookHandler.attachHooker(UrlViewObjectInfoPostProcessorHook.class, urlViewPostProcessor);

      /* menu */
      hookHandler.attachHooker(TsFavoriteMenuHook.class, openBtnHooker, HookHandlerService.PRIORITY_HIGH);
      hookHandler.attachHooker(TsFavoriteMenuHook.class, menuTestHooker, HookHandlerService.PRIORITY_HIGH + 10);
      hookHandler.attachHooker(TsFavoriteMenuHook.class, exportBtnHooker, HookHandlerService.PRIORITY_HIGH + 20);
      hookHandler.attachHooker(TsFavoriteMenuHook.class, menuRenameHooker, HookHandlerService.PRIORITY_MEDIUM);
      hookHandler.attachHooker(TsFavoriteMenuHook.class, addFolderHooker, HookHandlerService.PRIORITY_MEDIUM + 10);
      hookHandler.attachHooker(TsFavoriteMenuHook.class, menuDeleteHooker, HookHandlerService.PRIORITY_LOW);

      /* import post processor */
      hookHandler.attachHooker(ImporterPostProcessorConfiguratorHook.class, importPostProcessorProvider);

      /* history */
      historyService.addHistoryCallback(TsDiskUIModule.TEAMSPACE_SELECT_ITEM_HISTORY_TOKEN, new HistoryCallback() {

         @Override
         public void locationChanged(final HistoryLocation location) {
            Long id = Long.valueOf(location.getParameterValue("ts"));
            SubmoduleDisplayRequest req = new SubmoduleDisplayRequest(null, TeamSpaceUIModule.TEAMSPACE_PANEL_ID);
            req.addParameter(TeamSpaceUIModule.TEAMSPACE_ID_KEY, id);
            req.setCallback(new ParentDisplayedCallback() {
               @Override
               public void notify(Object obj) {
                  Collection<TeamSpaceApp> apps = (Collection<TeamSpaceApp>) obj;
                  for (final TeamSpaceApp app : apps) {
                     if (app instanceof TsDiskTeamSpaceApp) {
                        String idStr = location.getParameterValue("id");
                        String selStr = location.getParameterValue("sel");

                        Long selId = null;
                        try {
                           if (null != selStr)
                              selId = Long.valueOf(selStr);
                        } catch (NumberFormatException e) {
                        }
                        final Long selIdFinal = selId;

                        if (null == idStr)
                           ((TsDiskMainComponent) app.getAppComponent()).folderOpened(null, selIdFinal);
                        else {
                           try {
                              long id = Long.valueOf(idStr);

                              favoriteDao.loadNodeById(id, new RsAsyncCallback<AbstractNodeDto>() {
                                 public void onSuccess(AbstractNodeDto result) {
                                    if (result instanceof TsDiskRootDto)
                                       ((TsDiskMainComponent) app.getAppComponent()).folderOpened(null, selIdFinal,
                                             true);
                                    else
                                       ((TsDiskMainComponent) app.getAppComponent())
                                             .folderOpened((TsDiskFolderDto) result, selIdFinal, true);
                                 };
                              });
                           } catch (NumberFormatException e) {
                           }
                        }

                        break;
                     }
                  }
               }
            });

            eventbus.fireEvent(req);
         }
      });

      /* configureHistory for execution */
      historyService.addHistoryCallback(TsDiskUIModule.TEAMSPACE_OPEN_ITEM_HISTORY_TOKEN,
            referenceCallCallbackProvider);

   }
}
