package net.datenwerke.rs.eximport.client.eximport.im.hookers;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dialog.properties.PropertiesDialogCard;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCBoolean;
import net.datenwerke.rs.eximport.client.eximport.im.ImporterDao;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportPostProcessConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterPostProcessorConfiguratorHook;
import net.datenwerke.rs.eximport.client.eximport.locale.ExImportMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ImportMergeOptionsHooker implements ImporterPostProcessorConfiguratorHook {

   private static final String POST_PROCESSOR_ID = "ImportMergeOptions";
   private final ImporterDao importerDao;
   private PropertiesDialogCard card;
   private SimpleForm form;
   private String useMergeImporterKey;
   private Boolean useMergeImporterValue = true;
   
   
   @Inject
   public ImportMergeOptionsHooker(ImporterDao importerDao) {
      /* store objects */
      this.importerDao = importerDao;
   }
   

   @Override
   public ImportPostProcessConfigDto getConfiguration() throws NotProperlyConfiguredException {
      return null;
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

         @Override
         public ImageResource getIcon() {
            return BaseIcon.NEWSPAPER_O.toImageResource(1);
         }

         @Override
         public Widget getCard() {
            form = SimpleForm.getInlineInstance();
            useMergeImporterKey = form.addField(Boolean.class, "", new SFFCBoolean() {
               @Override
               public String getBoxLabel() {
                  return ExImportMessages.INSTANCE.merge();
               }
            });
            form.setValue(useMergeImporterKey, useMergeImporterValue);
            form.loadFields();
            DwContentPanel wrapper = DwContentPanel.newLightHeaderPanel(ExImportMessages.INSTANCE.mergeOptions(), form);
            return wrapper;
         }

         @Override
         public String getName() {
            return ExImportMessages.INSTANCE.mergeOptions();
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
            Boolean useMergeImporter = (Boolean) form.getValue(useMergeImporterKey);
            useMergeImporterValue = useMergeImporter;
            importerDao.setUseMergeImporter(useMergeImporter, new RsAsyncCallback<Void>());
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
      useMergeImporterValue = true;
   }

}
