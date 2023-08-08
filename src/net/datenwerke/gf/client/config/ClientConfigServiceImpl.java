package net.datenwerke.gf.client.config;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.rs.base.client.reportengines.table.dto.PageSizeConfig;

@Singleton
public class ClientConfigServiceImpl implements ClientConfigService {

   private final ClientConfigXmlService jsonService;
   private List<PageSizeConfig> previewPageSizeConfigs;

   @Inject
   public ClientConfigServiceImpl(ClientConfigXmlService jsonService) {
      this.jsonService = jsonService;
   }

   @Override
   public void setMainConfig(String json) {
      jsonService.setXmlConfig(json);
   }

   @Override
   public boolean getBoolean(String property, boolean defaultValue) {
      return jsonService.getBoolean(property, defaultValue);
   }

   @Override
   public String getString(String property, String defaultValue) {
      return jsonService.getString(property, defaultValue);
   }

   @Override
   public void setPreviewPageSizeConfigs(List<PageSizeConfig> previewPageSizeConfigs) {
      this.previewPageSizeConfigs = previewPageSizeConfigs;
   }

   @Override
   public List<PageSizeConfig> getPreviewPageSizeConfigs() {
      return previewPageSizeConfigs;
   }

}
