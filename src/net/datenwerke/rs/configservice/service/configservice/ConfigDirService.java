package net.datenwerke.rs.configservice.service.configservice;

import java.io.File;

public interface ConfigDirService {

   public boolean isEnabled();

   public File getConfigDir();

}
