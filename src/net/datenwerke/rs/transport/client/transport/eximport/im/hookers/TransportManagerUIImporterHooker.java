package net.datenwerke.rs.transport.client.transport.eximport.im.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterConfiguratorHook;
import net.datenwerke.rs.eximport.client.eximport.im.ui.ImportMainPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.transport.client.transport.eximport.im.ui.TransportImporterConfigPanel;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;

public class TransportManagerUIImporterHooker implements ImporterConfiguratorHook {

   private static final String SUPPORTED_EXPORTER_ID = "TransportManagerExporter";
   private static final String IMPORTER_ID = "TransportManagerImporter";

   private final Provider<TransportImporterConfigPanel> configPanelProvider;

   private TransportImporterConfigPanel configPanel;

   @Inject
   public TransportManagerUIImporterHooker(Provider<TransportImporterConfigPanel> configPanelProvider) {

      /* store objects */
      this.configPanelProvider = configPanelProvider;
   }

   @Override
   public ImageResource getImporterIcon() {
      return BaseIcon.ARCHIVE.toImageResource();
   }

   @Override
   public String getImporterName() {
      return TransportMessages.INSTANCE.transports();
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