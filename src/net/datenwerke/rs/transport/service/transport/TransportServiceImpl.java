package net.datenwerke.rs.transport.service.transport;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.commons.lang3.exception.ExceptionUtils;

import net.datenwerke.rs.EnvironmentValidatorHelperService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.json.service.json.JsonService;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder;
import net.datenwerke.rs.utils.crypto.HashUtil;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;

public class TransportServiceImpl implements TransportService {

   private final Provider<AuthenticatorService> authenticatorServiceProvider;
   private final Provider<GeneralInfoService> generalInfoServiceProvider;
   private final Provider<LicenseService> licenseServiceProvider;
   private final Provider<EnvironmentValidatorHelperService> envServiceProvider;
   private final Provider<JsonService> jsonServiceProvider;
   private final Provider<HashUtil> hashUtilProvider;
   private final Provider<TransportTreeService> transportTreeServiceProvider;
   
   private final static String INITIAL_PROP_USER_USERNAME = "USER_USERNAME";
   private final static String INITIAL_PROP_USER_FIRSTNAME = "USER_FIRSTNAME";
   private final static String INITIAL_PROP_USER_LASTNAME = "USER_LASTNAME";
   private final static String INITIAL_PROP_USER_EMAIL = "USER_EMAIL";
   private final static String INITIAL_PROP_SERVER_ID = "SERVER_ID";
   private final static String INITIAL_PROP_RS_VERSION = "RS_VERSION";
   private final static String INITIAL_PROP_SCHEMA_VERSION = "SCHEMA_VERSION";
   private final static String INITIAL_PROP_KEY = "KEY";
   
   @Inject
   public TransportServiceImpl(
         Provider<AuthenticatorService> authenticatorServiceProvider,
         Provider<GeneralInfoService> generalInfoServiceProvider,
         Provider<LicenseService> licenseServiceProvider,
         Provider<EnvironmentValidatorHelperService> envServiceProvider,
         Provider<JsonService> jsonServiceProvider,
         Provider<HashUtil> hashUtilProvider,
         Provider<TransportTreeService> transportTreeServiceProvider
         ) {
      this.authenticatorServiceProvider = authenticatorServiceProvider;
      this.generalInfoServiceProvider = generalInfoServiceProvider;
      this.licenseServiceProvider = licenseServiceProvider;
      this.envServiceProvider = envServiceProvider;
      this.jsonServiceProvider = jsonServiceProvider;
      this.hashUtilProvider = hashUtilProvider;
      this.transportTreeServiceProvider = transportTreeServiceProvider;
   }
         
   
   @Override
   public String createTransport(String description, TransportFolder parent) {
      Map<String,String> asMap = createInitialProperties();
      
      Transport transport = new Transport();
      setInitialProperties(transport, asMap);
      transport.setDescription(description);
      transport.setParent(parent);
      
      transportTreeServiceProvider.get().persist(transport);
      return asMap.get(INITIAL_PROP_KEY);
   }


   @Override
   public void setInitialProperties(Transport transport, Map<String,String> initialProperties) {
      transport.setServerId(initialProperties.get(INITIAL_PROP_SERVER_ID));
      transport.setRsVersion(initialProperties.get(INITIAL_PROP_RS_VERSION));
      transport.setRsSchemaVersion(initialProperties.get(INITIAL_PROP_SCHEMA_VERSION));
      transport.setCreatorUsername(initialProperties.get(INITIAL_PROP_USER_USERNAME));
      transport.setCreatorFirstname(initialProperties.get(INITIAL_PROP_USER_FIRSTNAME));
      transport.setCreatorLastname(initialProperties.get(INITIAL_PROP_USER_LASTNAME));
      transport.setCreatorEmail(initialProperties.get(INITIAL_PROP_USER_EMAIL));
      transport.setKey(initialProperties.get(INITIAL_PROP_KEY));
   }
   
   @Override
   public Map<String,String> createInitialProperties() {
      User user = authenticatorServiceProvider.get().getCurrentUser();
      String serverId = licenseServiceProvider.get().getServerId();
      String rsVersion = generalInfoServiceProvider.get().getRsVersion();
      String schemaVersion = "unknown";
      try {
         schemaVersion = envServiceProvider.get().getSchemaVersion();
      } catch (SQLException e) {
         schemaVersion = ExceptionUtils.getRootCauseMessage(e);
      }
      String username = user.getUsername();
      String firstname = user.getFirstname();
      String lastname = user.getLastname();
      String email = user.getEmail();
      
      Map<String,String> asMap = new HashMap<>();
      // now is important for getting different hash values
      asMap.put("now", DateUtils.formatCurrentDate()); 
      asMap.put(INITIAL_PROP_SERVER_ID, serverId);
      asMap.put(INITIAL_PROP_RS_VERSION, rsVersion);
      asMap.put(INITIAL_PROP_SCHEMA_VERSION, schemaVersion);
      asMap.put(INITIAL_PROP_USER_USERNAME, username);
      asMap.put(INITIAL_PROP_USER_FIRSTNAME, firstname);
      asMap.put(INITIAL_PROP_USER_LASTNAME, lastname);
      asMap.put(INITIAL_PROP_USER_EMAIL, email);
      
      String asJson = jsonServiceProvider.get().map2Json(asMap);
      String hash = hashUtilProvider.get().sha1(asJson);
      asMap.put(INITIAL_PROP_KEY, hash);
      return asMap;
   }

}
