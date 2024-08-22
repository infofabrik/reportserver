package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.ui.DatasinkImporterConfigPanel;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterConfiguratorHook;
import net.datenwerke.rs.eximport.client.eximport.im.ui.ImportMainPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class DatasinkManagerUIImporterHooker implements ImporterConfiguratorHook {

   private static final String SUPPORTED_EXPORTER_ID = "DatasinkManagerExporter";
   private static final String IMPORTER_ID = "DatasinkManagerImporter";

   private final Provider<DatasinkImporterConfigPanel> configPanelProvider;

   private DatasinkImporterConfigPanel configPanel;

   @Inject
   public DatasinkManagerUIImporterHooker(Provider<DatasinkImporterConfigPanel> configPanelProvider) {

      /* store objects */
      this.configPanelProvider = configPanelProvider;
   }

   @Override
   public ImageResource getImporterIcon() {
      return BaseIcon.SERVER.toImageResource();
   }

   @Override
   public String getImporterName() {
      return DatasinksMessages.INSTANCE.datasinks();
   }

   @Override
   public Collection<String> getSupportedExporters() {
      return Collections.singletonList(SUPPORTED_EXPORTER_ID);
   }

   @Override
   public Widget initConfigPanel(ImportMainPanel importMainPanel) {
      configPanel = configPanelProvider.get();
      return configPanel;
   }

   @Override
   public ImportConfigDto getConfiguration() throws NotProperlyConfiguredException {
      if (null == configPanel)
         return null;
      return configPanel.getConfiguration();
   }

   @Override
   public String getImporterId() {
      return IMPORTER_ID;
   }

   @Override
   public void reset() {
      if (null != configPanel)
         configPanel.resetConfig();
   }

}