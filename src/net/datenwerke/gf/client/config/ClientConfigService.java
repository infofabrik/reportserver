package net.datenwerke.gf.client.config;

import java.util.List;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.base.client.reportengines.table.dto.PageSizeConfig;

@ImplementedBy(ClientConfigServiceImpl.class)
public interface ClientConfigService {

   void setMainConfig(String result);

   boolean getBoolean(String property, boolean defaultValue);

   String getString(String property, String defaultValue);

   void setPreviewPageSizeConfigs(List<PageSizeConfig> configs);

   List<PageSizeConfig> getPreviewPageSizeConfigs();
}
