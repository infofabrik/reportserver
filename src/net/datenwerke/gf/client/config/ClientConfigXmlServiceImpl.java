package net.datenwerke.gf.client.config;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.XMLParser;

public class ClientConfigXmlServiceImpl implements ClientConfigXmlService {

   private Document config;

   @Override
   public void setXmlConfig(String xml) {
      try {
         config = XMLParser.parse(xml);
      } catch (Exception e) {
         GWT.log(e.getMessage());
      }
   }

   @Override
   public boolean getBoolean(String property, boolean defaultValue) {
      if (null == config)
         return defaultValue;

      return Boolean.parseBoolean(getString(property, defaultValue + ""));
   }

   @Override
   public String getString(String property, String defaultValue) {
      if (null == config)
         return defaultValue;

      try {
         Element obj = getBaseObject(property);
         if (property.contains("."))
            property = property.substring(property.lastIndexOf(".") + 1);
         String value = obj.getElementsByTagName(property).item(0).getFirstChild().getNodeValue();
         if (null != value)
            return value;
         return defaultValue;
      } catch (Exception e) {
         return defaultValue;
      }
   }

   private Element getBaseObject(String property) {
      Element obj = (Element) config.getDocumentElement();
      while (property.contains(".")) {
         int index = property.indexOf(".");
         String key = property.substring(0, index);
         property = property.substring(index + 1);

         obj = (Element) obj.getElementsByTagName(key).item(0);
      }

      return obj;
   }
}
