package net.datenwerke.gf.service.authenticator.hookers;

import static java.util.stream.Collectors.toSet;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.gf.service.authenticator.locale.AuthenticatorMessages;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.security.service.authenticator.AuthenticatorService;

public class PamCategoryProviderHooker implements GeneralInfoCategoryProviderHook {

   private final AuthenticatorService authenticatorService;
   
   private static final String STATIC_PAM_CONFIGURATION = "STATIC_PAM_CONFIGURATION";
   private static final String PAM = "PAM";
   
   @Inject
   public PamCategoryProviderHooker(
         AuthenticatorService authenticatorService
         ) {
      this.authenticatorService = authenticatorService;
   }
   
   @Override
   public Map<ImmutablePair<String, String>, Map<ImmutablePair<String, String>, Object>> provideCategory() {
      final Map<ImmutablePair<String, String>, Object> props = new LinkedHashMap<>();

      props.put(ImmutablePair.of(STATIC_PAM_CONFIGURATION, AuthenticatorMessages.INSTANCE.staticPamsLabel()), 
            authenticatorService.getStaticPams()
               .stream()
               .map(pam -> pam.getClass().getName())
               .collect(toSet()));

      return Collections.singletonMap(ImmutablePair.of(PAM, AuthenticatorMessages.INSTANCE.pamConfiguration()), props);
   }

}
