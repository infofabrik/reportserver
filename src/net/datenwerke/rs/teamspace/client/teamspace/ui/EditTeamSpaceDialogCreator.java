package net.datenwerke.rs.teamspace.client.teamspace.ui;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.dialog.properties.RpcPropertiesDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitCompleteCallback;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceDao;
import net.datenwerke.rs.teamspace.client.teamspace.TeamSpaceUIService.TeamSpaceOperationSuccessHandler;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.client.teamspace.hooks.TeamSpaceEditDialogHook;
import net.datenwerke.rs.teamspace.client.teamspace.locale.TeamSpaceMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class EditTeamSpaceDialogCreator {

   private final HookHandlerService hookHandler;
   private final Provider<RpcPropertiesDialog> propertiesDialogProvider;
   private final TeamSpaceDao tsDao;

   @Inject
   public EditTeamSpaceDialogCreator(HookHandlerService hookHandler,
         Provider<RpcPropertiesDialog> propertiesDialogProvider, TeamSpaceDao tsDao) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.propertiesDialogProvider = propertiesDialogProvider;
      this.tsDao = tsDao;
   }

   public void displayDialog(final TeamSpaceDto currentSpace, final TeamSpaceOperationSuccessHandler successHandler) {
      tsDao.reloadTeamSpaceForEdit(currentSpace, new RsAsyncCallback<TeamSpaceDto>() {
         @Override
         public void onSuccess(TeamSpaceDto result) {
            final RpcPropertiesDialog dialog = propertiesDialogProvider.get();

            dialog.setPerformSubmitsConsecutively(true);
            dialog.continueOnFailure(true);
            dialog.setSize(640, 480);
            dialog.setHeading(TeamSpaceMessages.INSTANCE
                  .editTeamSpaceHeading(currentSpace.toDisplayTitle() + " (" + currentSpace.getId() + ") "));
            dialog.setHeaderIcon(BaseIcon.GROUP_PROPERTIES);
            dialog.setMaskOnSubmit(TeamSpaceMessages.INSTANCE.editTeamSpaceWindowServerRequestMask());
            dialog.setModal(true);
            dialog.setSubmitCompleteCallback(new SubmitCompleteCallback() {

               @Override
               public void onSuccess() {
                  dialog.mask(BaseMessages.INSTANCE.storingMsg());
                  tsDao.reloadTeamSpace(currentSpace, new RsAsyncCallback<TeamSpaceDto>() {
                     @Override
                     public void onSuccess(TeamSpaceDto result) {
                        dialog.hide();
                        successHandler.onSuccess(result);
                     }
                  });
               }

               @Override
               public void onFailure(Throwable t) {
               }
            });

            /* load cards */
            final List<TeamSpaceEditDialogHook> cardProviders = hookHandler.getHookers(TeamSpaceEditDialogHook.class);
            for (TeamSpaceEditDialogHook cardProvider : cardProviders) {
               if (!cardProvider.applies(result))
                  continue;

               cardProvider.setCurrentSpace(result);

               /* get height */
               dialog.addCard(cardProvider);
            }

            dialog.show();
         }
      });

   }
}
