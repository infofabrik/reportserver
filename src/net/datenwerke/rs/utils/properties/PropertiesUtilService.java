package net.datenwerke.rs.utils.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertiesUtilService {

   public static Map<String, String> convert(Properties prop) {
      return prop.entrySet().stream().collect(
        Collectors.toMap(
          e -> String.valueOf(e.getKey()),
          e -> String.valueOf(e.getValue()),
          (prev, next) -> next, HashMap::new
      ));
  }
}
