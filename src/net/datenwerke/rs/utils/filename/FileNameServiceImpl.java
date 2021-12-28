package net.datenwerke.rs.utils.filename;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.filename.hooks.FileNameSanitizerHook;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;

@Singleton
public class FileNameServiceImpl implements FileNameService {

   private final HookHandlerService hookHandlerService;

   @Inject
   public FileNameServiceImpl(HookHandlerService hookHandlerService) {
      this.hookHandlerService = hookHandlerService;
   }

   @Override
   public String sanitizeFileName(String name) {
      if (null == name)
         return "";

      for (FileNameSanitizerHook hooker : hookHandlerService.getHookers(FileNameSanitizerHook.class))
         return hooker.sanitizeFileName(name);

      String sanitized = name.replaceAll("[/\\\\:\\*<>|]+", " ").trim();
      if (".".equals(sanitized) || "..".equals(sanitized)) {
         sanitized = "file-" + sanitized;
      }
      return sanitized;
   }

   @Override
   public String sanitizeFileNameStrict(String name) {
      if (null == name)
         return "";

      for (FileNameSanitizerHook hooker : hookHandlerService.getHookers(FileNameSanitizerHook.class))
         return hooker.sanitizeFileName(name);

      if (Locale.GERMAN.equals(LocalizationServiceImpl.getLocale())
            || Locale.GERMANY.equals(LocalizationServiceImpl.getLocale())) {
         return name.trim().replaceAll("[^a-zA-ZüÜöÖäÄß\\(\\)\\[\\] \\-\\.0-9]+", "_");
      }

      /* default: strip accents */
      String sanitized = stripAccents(name);
      sanitized = sanitized.trim().replaceAll("[^a-zA-Z\\(\\)\\[\\] \\-\\.0-9]+", "_");
      return sanitized;
   }

   protected String stripAccents(final String input) {
      /* from commons lang3 */
      if (input == null) {
         return null;
      }
      final Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");//$NON-NLS-1$
      final String decomposed = Normalizer.normalize(input, Normalizer.Form.NFD);
      // Note that this doesn't correctly remove ligatures...
      return pattern.matcher(decomposed).replaceAll("");//$NON-NLS-1$
   }

}
