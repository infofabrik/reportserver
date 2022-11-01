package net.datenwerke.rs.configservice.service.manservice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.annotations.DefaultLocale;
import net.datenwerke.rs.utils.man.ManPageNotFoundException;
import net.datenwerke.rs.utils.man.ManPageService;

@Singleton
public class ManPageServiceImpl implements ManPageService {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final Map<String, String> cache = new ConcurrentHashMap<String, String>();

   private final TerminalService terminalService;

   private final LocalizationServiceImpl localizationService;

   private final Provider<String> defaultLocale;

   @Inject
   public ManPageServiceImpl(TerminalService terminalService, LocalizationServiceImpl localizationService,
         @DefaultLocale Provider<String> defaultLocale) {

      /* store objects */
      this.terminalService = terminalService;
      this.localizationService = localizationService;
      this.defaultLocale = defaultLocale;
   }

   @Override
   public String getManPageFailsafe(String identifier) {
      try {
         return getManPage(identifier);
      } catch (Exception e) {
         logger.warn("Manpage " + identifier + " could not be loaded.");
         return "";
      }
   }

   @Override
   public String getManPage(String identifier) {
      return getManPage(identifier, true);
   }

   @Override
   public String getManPage(String identifier, boolean useCache) {
      identifier = identifier.trim();
      if (identifier.startsWith("/"))
         throw new ManPageNotFoundException(
               "Could not find manpage for " + identifier + ". Identifier may not start with /.");

      if (identifier.contains(".."))
         throw new ManPageNotFoundException(
               "Could not find manpage for " + identifier + ". Identifier may not contain '..'.");

      try {
         if (useCache && cache.containsKey(identifier))
            return cache.get(identifier);

         String currentUserLocale = localizationService.getLocale().getLanguage();
         String[] locales = new String[] { currentUserLocale, defaultLocale.get(), "" };

         Object object = null;
         for (String loc : locales) {
            object = terminalService.getObjectByQuery(
                  "/fileserver/doc/" + identifier + (loc.isEmpty() ? "" : "." + loc) + ".man", false);
            if (null != object) {
               break;
            }
         }

         if (null == object || !(object instanceof FileServerFile))
            throw new ManPageNotFoundException("Could not find manpage for " + identifier);

         FileServerFile file = (FileServerFile) object;

         byte[] data = file.getData();
         if (null == data)
            throw new ManPageNotFoundException("manpage file is empty " + identifier);
         String strData = new String(data);

         cache.put(identifier, strData);

         return strData;
      } catch (ObjectResolverException e) {
         throw new ManPageNotFoundException("Could not find manpage for " + identifier, e);
      }
   }

   @Override
   public void clearCache(String identifier) {
      cache.remove(identifier);
   }

   @Override
   public void clearCache() {
      cache.clear();
   }
}
