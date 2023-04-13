package net.datenwerke.rs.remoteaccess.service.sftp.hookers;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.GeneralInfoUiModule;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.remoteaccess.service.sftp.annotations.KeyLocation;
import net.datenwerke.rs.remoteaccess.service.sftp.annotations.SftpEnabled;
import net.datenwerke.rs.remoteaccess.service.sftp.annotations.SftpPort;
import net.datenwerke.rs.remoteaccess.service.sftp.locale.SftpMessages;
import net.datenwerke.rs.utils.file.RsFileUtils;
import net.datenwerke.rs.utils.misc.Nullable;

public class SftpServerCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final Provider<String> sftpKeyLocation;
   private final Provider<Integer> sftpPort;
   private final Provider<Boolean> sftpEnabled;
   
   private static final String SFTP_SERVER_ENABLED = "SFTP_SERVER_ENABLED";
   private static final String SFTP_SERVER_DISABLED_MSG = "SFTP_SERVER_DISABLED_MSG";
   private static final String SFTP_SERVER_KEY = "SFTP_SERVER_KEY";
   private static final String SFTP_SERVER_PORT = "SFTP_SERVER_PORT";
   private static final String SFTP_SERVER = "SFTP_SERVER";
   
   @Inject
   public SftpServerCategoryProviderHooker(
         @Nullable @KeyLocation Provider<String> sftpKeyLocation,
         @SftpPort Provider<Integer> sftpPort, 
         @SftpEnabled Provider<Boolean> sftpEnabled
         ) {
      this.sftpKeyLocation = sftpKeyLocation;
      this.sftpPort = sftpPort;
      this.sftpEnabled = sftpEnabled;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      final Map<ImmutablePair<String, String>, Object> props = new LinkedHashMap<>();
      
      props.put(ImmutablePair.of(SFTP_SERVER_ENABLED, GeneralInfoUiModule.ENABLED), sftpEnabled.get());
      props.put(ImmutablePair.of(SFTP_SERVER_DISABLED_MSG, GeneralInfoUiModule.DISABLED_MESSAGE),
            SftpMessages.INSTANCE.disabled());
      String keylo = sftpKeyLocation.get();
      
      if (keylo.contains(":")) {
         props.put(ImmutablePair.of(SFTP_SERVER_KEY, SftpMessages.INSTANCE.sftpKey()), "URL: " + keylo);
      } else if (keylo.equals("$generated")) {
         props.put(ImmutablePair.of(SFTP_SERVER_KEY, SftpMessages.INSTANCE.sftpKey()), "Generated");
      } else {
         props.put(ImmutablePair.of(SFTP_SERVER_KEY, SftpMessages.INSTANCE.sftpKey()),
               "File: " + RsFileUtils.appendFileCheck(Paths.get(keylo)));
      }
      props.put(ImmutablePair.of(SFTP_SERVER_PORT, SftpMessages.INSTANCE.sftpPort()), sftpPort.get());
      
      return Collections.singletonMap(ImmutablePair.of(SFTP_SERVER, SftpMessages.INSTANCE.sftpServer()), props);
   }
   

}
