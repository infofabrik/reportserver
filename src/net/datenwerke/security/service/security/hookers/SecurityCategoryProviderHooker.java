package net.datenwerke.security.service.security.hookers;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.locale.SecurityMessages;

public class SecurityCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final Provider<SecurityService> securityServiceProvider;
   
   private static final String KNOWN_HOSTS_FILE = "KNOWN_HOSTS_FILE";
   private static final String SUPPORTED_SSL_PROTOCOLS = "SUPPORTED_SSL_PROTOCOLS";
   private static final String DEFAULT_SSL_PROTOCOLS = "DEFAULT_SSL_PROTOCOLS";
   private static final String ENABLED_SSL_PROTOCOLS = "ENABLED_SSL_PROTOCOLS";
   private static final String SECURITY_INFO = "SECURITY_INFO";
   
   @Inject
   public SecurityCategoryProviderHooker(
         Provider<SecurityService> securityServiceProvider
         ) {
      this.securityServiceProvider = securityServiceProvider;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      final Map<ImmutablePair<String, String>, Object> props = new LinkedHashMap<>();
      final SecurityService securityService = securityServiceProvider.get();
      props.put(ImmutablePair.of(KNOWN_HOSTS_FILE, SecurityMessages.INSTANCE.knownHostsFile()),
            securityService.getKnownHostsFile(true));
      props.put(ImmutablePair.of(SUPPORTED_SSL_PROTOCOLS,
            SecurityMessages.INSTANCE.supportedSslProtocols()), securityService.getSupportedSslProtocols());
      props.put(ImmutablePair.of(DEFAULT_SSL_PROTOCOLS,
            SecurityMessages.INSTANCE.defaultSslProtocols()), securityService.getDefaultSslProtocols());
      props.put(ImmutablePair.of(ENABLED_SSL_PROTOCOLS,
            SecurityMessages.INSTANCE.enabledSslProtocols()), securityService.getEnabledSslProtocols());
      
      return Collections.singletonMap(ImmutablePair.of(SECURITY_INFO, SecurityMessages.INSTANCE.security()), props);
   }
   

}
