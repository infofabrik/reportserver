package net.datenwerke.gxtdto.client.utils;

public class StringEscapeUtils {

   /**
    * Escapes all the HTML tags in a given string
    * 
    * @param html A string containg the HTML to be encoded
    * @return A string with all HTML tags escaped (i.e. &lt;Html&gt; &lt;Html&gt;)
    */
   public static String escapeHTML(String html) {
      if (null == html)
         return null;
      return html.replace("&", "&amp;").replace(" ", "&nbsp;").replace("<", "&lt;").replace(">", "&gt;")
            .replace("ä", "&auml;").replace("Ä", "&Auml;").replace("ö", "&ouml;").replace("Ö", "&Ouml;")
            .replace("u", "&uuml;").replace("U", "&Uuml;").replace("ß", "&szlig;");
   };

   public static String escapeAngleBrackets(String html) {
      if (null == html)
         return null;
      return html.replace("<", "&lt;").replace(">", "&gt;");
   };

   public static String unescapeAngleBrackets(String text) {
      if (null == text)
         return null;
      return text.replace("&lt;", "<").replace("&gt;", ">");
   };

   /**
    * Unescapes any HTML within the given string, i.e &lt;Html&gt; = &lt;Html&gt;
    * 
    * @param text The text containing the escaped HTML
    * @return the text with the HTML unescaped
    */
   public static String unescapeHTML(String text) {
      if (null == text)
         return null;
      return text.replace("&nbsp;", " ").replace("&lt;", "<").replace("&gt;", ">").replace("&auml;", "ä")
            .replace("&Auml;", "Ä").replace("&ouml;", "ö").replace("&Ouml;", "Ö").replace("&uuml;", "ü")
            .replace("&Uuml;", "Ü").replace("&szlig;", "ß").replace("&amp;", "&");
   };

   public static String escapeXml(String text) {
      if (null == text)
         return null;
      return text.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
   };

   public static String unescapeXml(String text) {
      if (null == text)
         return null;
      return text.replace("&lt;", "<").replace("&gt;", ">").replace("&amp;", "&");
   };
}
