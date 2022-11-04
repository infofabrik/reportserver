package net.datenwerke.rs.legacysaiku.server.rest.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.core.JsonParseException;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.rs.saiku.client.saiku.locale.SaikuNativeMessages;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;

@Path("/legacysaiku/i18n")
@XmlAccessorType(XmlAccessType.NONE)
public class SaikuI18nResource {

   private Provider<ServletContext> servletContext;
   private RemoteMessageService remoteMessageService;

   @Inject
   public SaikuI18nResource(Provider<ServletContext> servletContext, RemoteMessageService remoteMessageService) {
      this.servletContext = servletContext;
      this.remoteMessageService = remoteMessageService;
   }

   @GET
   @Path("/{lang}")
   @Produces({ "application/json" })
   public Map<String, String> getMapping() throws JsonParseException, IOException {
      Map<String, String> mapping = new HashMap<String, String>();

      Locale locale = LocalizationServiceImpl.getLocale();
      String country = locale.getLanguage();

      /* try loading original json */
      InputStream originalMapping = servletContext.get()
            .getResourceAsStream("/resources/legacysaiku/js/legacysaiku/plugins/I18n/po/" + country + ".json");
      if (null == originalMapping)
         originalMapping = servletContext.get()
               .getResourceAsStream("/resources/legacysaiku/js/legacysaiku/plugins/I18n/po/en.json");

      /* overlay rs messages */
      HashMap<String, HashMap<String, String>> messages = remoteMessageService.getMessages(country);
      if (null != messages) {
         HashMap<String, String> rsmap = messages.get(SaikuNativeMessages.class.getName());
         mapping.putAll(rsmap);
      }

      return mapping;
   }
}
