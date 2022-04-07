package net.datenwerke.rs.core.service.datasinkmanager;

import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;

public interface HasDefaultConfiguration {
   
   public static final String DEFAULT_EXPORT_FILENAME = "export-";
   
   DatasinkConfiguration getDefaultConfiguration(String fileEnding);
}
