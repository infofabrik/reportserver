package net.datenwerke.rs.remoteaccess.service.sftp;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.remoteaccess.service.RemoteAccessStartup;
import net.datenwerke.rs.remoteaccess.service.sftp.annotations.KeyLocation;
import net.datenwerke.rs.remoteaccess.service.sftp.annotations.SftpEnabled;
import net.datenwerke.rs.remoteaccess.service.sftp.annotations.SftpPort;

public class SftpModule extends AbstractModule {

   private static final String CONFIG_FILE = "misc/misc.cf";
   private static final String KEY_LOCATION_KEY = "remoteaccess.sftp.keylocation";
   private static final String KEY_PORT = "remoteaccess.sftp.port";
   private static final String PROPERTY_SFTP_DISABLED = "remoteaccess.sftp[@disabled]";

   @Override
   protected void configure() {
      bind(SftpService.class).to(SftpServiceImpl.class).in(Singleton.class);

      bind(RemoteAccessStartup.class).asEagerSingleton();
   }

   @Inject
   @Provides
   @KeyLocation
   public String provideKeyLocation(ConfigService configService) {
      return configService.getConfigFailsafe(CONFIG_FILE).getString(KEY_LOCATION_KEY, null);
   }

   @Inject
   @Provides
   @SftpPort
   public int providePort(ConfigService configService) {
      return configService.getConfigFailsafe(CONFIG_FILE).getInt(KEY_PORT, 8022);
   }

   @Inject
   @Provides
   @SftpEnabled
   public boolean provideSftpEnabled(ConfigService configService) {
      return !configService.getConfigFailsafe(CONFIG_FILE).getBoolean(PROPERTY_SFTP_DISABLED, false);
   }

}
