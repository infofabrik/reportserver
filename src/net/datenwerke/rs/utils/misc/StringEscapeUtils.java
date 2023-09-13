package net.datenwerke.rs.utils.misc;

import org.apache.commons.lang3.StringUtils;

public class StringEscapeUtils {

   public static String escapeXml(String text) {
      if (null == text)
         return null;
      return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
   };
   
   public static String escapeAngleBrackets(String text) {
      if (null == text)
         return null;
      return text.replace("<", "&lt;").replace(">", "&gt;");
   };

   public static String unescapeAngleBrackets(String text) {
      if (null == text)
         return null;
      return text.replace("&lt;", "<").replace("&gt;", ">");
   };

   public static String unescapeXml(String text) {
      if (null == text)
         return null;
      return text.replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&");
   };

   // adapted from commons-lang2
   public static String escapeSql(String str) {
      if (str == null) {
         return null;
      }
      return StringUtils.replace(str, "'", "''");
   }
   
   // https://learn.microsoft.com/en-us/windows/win32/fileio/naming-a-file
   public static String removeInvalidFilenameChars(String name) {
      String replacement = "_";
      return name
            .replace("<", replacement)
            .replace(">", replacement)
            .replace(":", replacement)
            .replace("\"", replacement)
            .replace("/", replacement)
            .replace("\\", replacement)
            .replace("|", replacement)
            .replace("?", replacement)
            .replace("*", replacement);
   }
}
