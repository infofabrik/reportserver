package net.datenwerke.rs.tsreportarea.client.tsreportarea.hookers;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dialog.properties.PropertiesDialogCard;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCTeamSpace;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportPostProcessConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterPostProcessorConfiguratorHook;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskPostProcessorConfigDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;

public class ImportPostProcessorHooker implements ImporterPostProcessorConfiguratorHook {

   private static final String POST_PROCESSOR_ID = "FavoritesPostProcessor";

   private TsDiskPostProcessorConfigDto config;
   private PropertiesDialogCard card;
   private TeamSpaceDto teamspaceValue;

   @Override
   public ImportPostProcessConfigDto getConfiguration() throws NotProperlyConfiguredException {
      return config;
   }

   @Override
   public String getPostProcessorId() {
      return POST_PROCESSOR_ID;
   }

   @Override
   public PropertiesDialogCard getCard() {
      if (null == card)
         card = createCard();

      return card;
   }

   private PropertiesDialogCard createCard() {
      return new PropertiesDialogCard() {

         private SimpleForm form;
         private String teamspaceKey;

         @Override
         public ImageResource getIcon() {
            return BaseIcon.NEWSPAPER_O.toImageResource(1);
         }

         @Override
         public Widget getCard() {
            form = SimpleForm.getInlineInstance();

            teamspaceKey = form.addField(TeamSpaceDto.class,
                  TsFavoriteMessages.INSTANCE.importPostProcessorIntoTeamspace(), new SFFCTeamSpace() {
                     @Override
                     public boolean isMulti() {
                        return false;
                     }

                     @Override
                     public boolean isLoadAll() {
                        return true;
                     }
                  });

            form.setValue(teamspaceKey, teamspaceValue);
            
            form.loadFields();

            DwContentPanel wrapper = DwContentPanel
                  .newLightHeaderPanel(TsFavoriteMessages.INSTANCE.importPostProcessorHeadline(), form);
            wrapper.setInfoText(TsFavoriteMessages.INSTANCE.importPostProcessorDescription());
            return wrapper;
         }

         @Override
         public String getName() {
            return TsFavoriteMessages.INSTANCE.importPostProcessorName();
         }

         @Override
         public void cancelPressed() {

         }

         @Override
         public String isValid() {
            return null;
         }

         @Override
         public void submitPressed() {
            TeamSpaceDto teamSpace = (TeamSpaceDto) form.getValue(teamspaceKey);
            teamspaceValue = teamSpace;
            
            if (null == teamSpace)
               config = null;
            else {
               config = new TsDiskPostProcessorConfigDto();
               config.setTeamSpace(teamSpace);
            }
         }

         @Override
         public int getHeight() {
            return 280;
         }

      };
   }

   @Override
   public void reset() {
      card = null;
      teamspaceValue = null;
   }

}
