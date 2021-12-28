package net.datenwerke.security.service.crypto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.utils.config.ConfigFileNotFoundException;
import net.datenwerke.security.service.crypto.credentialproviders.CryptoCredentialProvider;
import net.datenwerke.security.service.crypto.hooks.UserCryptoCredentialHook;

public class CryptoServiceImpl implements CryptoService {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private ConfigService configService;

   private HookHandlerService hookHandlerService;

   @Inject
   public CryptoServiceImpl(ConfigService configService, HookHandlerService hookHandlerService) {
      this.configService = configService;
      this.hookHandlerService = hookHandlerService;
   }

   @Override
   public boolean hasCryptoCredentials(String keyIdentifier) {
      return null != getCryptoCredentials(keyIdentifier);
   }

   @Override
   public CryptoCredentials getCryptoCredentials(String keyIdentifier) {
      List<CryptoCredentials> res = new ArrayList<>();
      for (CryptoCredentialProvider prov : getCredentialProviders(keyIdentifier)) {
         CryptoCredentials cred = prov.getCredentials();
         if (null != cred)
            res.add(cred);
      }
      if (res.size() > 0)
         return res.get(0);
      return null;
   }

   @Override
   public CryptoCredentials getUserCryptoCredentials(String email) {
      List<CryptoCredentials> res = new ArrayList<CryptoCredentials>();
      for (UserCryptoCredentialHook hooker : hookHandlerService.getHookers(UserCryptoCredentialHook.class)) {
         CryptoCredentials creds = hooker.getUserCryptoCredentials(email);
         if (null != creds)
            res.add(creds);
      }

      for (CryptoCredentialProvider prov : getCredentialProviders(KEY_USER)) {
         CryptoCredentials cred = prov.getCredentials(email);
         if (null != cred)
            res.add(cred);
      }

      if (res.size() > 0)
         return res.get(0);

      return null;
   }

   public void createEmptyConfiguration() {
      HierarchicalConfiguration newConfig = (HierarchicalConfiguration) configService.newConfig();

      String[] keyIds = { KEY_SIGN, KEY_SFTP };
      int n = 0;
      for (String keyId : keyIds) {
         newConfig.addProperty("cryptocredentials.provider(-1)[@type]", keyId);
         newConfig.setProperty("cryptocredentials.provider(" + n + ").class",
               "net.datenwerke.rs.incubator.service.crypto.FileServerKeyStoreKryptoCredentialProvider");
         newConfig.setProperty("cryptocredentials.provider(" + n + ").alias", "");
         newConfig.setProperty("cryptocredentials.provider(" + n + ").secret", "");
         newConfig.setProperty("cryptocredentials.provider(" + n + ").type", "");
         newConfig.setProperty("cryptocredentials.provider(" + n + ").location", "");
         n++;
      }
   }

   private Collection<CryptoCredentialProvider> getCredentialProviders(String keyId) {
      List<CryptoCredentialProvider> res = new ArrayList<CryptoCredentialProvider>();

      try {
         HierarchicalConfiguration config = (HierarchicalConfiguration) configService.getConfig(CONFIG_FILE);
         List<HierarchicalConfiguration> subnodes = config.configurationsAt("cryptocredentials.provider");
         for (HierarchicalConfiguration subnode : subnodes) {
            String type = subnode.getString("[@type]");
            if (!(null == keyId || keyId.equals(type)))
               continue;
            String providerClass = subnode.getString("class");
            try {
               Class<?> provClass = Class.forName(providerClass);
               CryptoCredentialProvider credentialProvider = (CryptoCredentialProvider) provClass
                     .getConstructor(HierarchicalConfiguration.class).newInstance(subnode);
               res.add(credentialProvider);
            } catch (Exception e) {
               logger.info("Failed to instantiate CryptoCredentailProvider " + providerClass, e);
            }
         }
      } catch (ConfigFileNotFoundException e) {
      }

      return res;
   }

}
