package net.datenwerke.rs.utils.localization;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Map;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.This;

public class LocalizationInterceptor {

   private final Map<String, ? extends Map> xmappings;
   
   LocalizationInterceptor(
         final Map<String, ? extends Map> xmappings
         ) {
      this.xmappings = xmappings;
   }
   
   public String intercept(
         @This Object self, 
         @Origin String method, 
         @AllArguments Object[] args
         ) {
      Map<String, ? extends Map> mappings = xmappings;
      
      /* we search for interfaces of type Messages */
      Class<?> msgInterface = Arrays
            .stream(self.getClass().getInterfaces())
            .filter(currentInterface -> {
               return Arrays.stream(currentInterface.getInterfaces())
                  .filter(parentInterface -> parentInterface.getName().equals(Messages.class.getName()))
                  .findAny()
                  .isPresent();
            }).findAny()
                .orElseThrow(() -> new IllegalArgumentException("Not an instance of " + Messages.class.getName()));
            
      /* load mapping */
      if (null == mappings)
         mappings = LocalizationServiceImpl.getMappings(msgInterface);
      
      String withoutArgs = method.substring(0, method.indexOf("("));
      String key = withoutArgs.substring(withoutArgs.lastIndexOf(".")+1);
      String locale = LocalizationServiceImpl.getLocale().getLanguage();
      
      if (args.length == 0) {
         return LocalizationServiceImpl.getMapping(mappings, locale, key, self);
      } else {
         String template = LocalizationServiceImpl.getMapping(mappings, locale, key, self);
         return MessageFormat.format(template, args);
      }
   }
}
