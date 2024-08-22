package net.datenwerke.usermanager.ext.client.properties;

public enum DefaultUserProperties {
   APIKEY("apikey");

   private String property;

   DefaultUserProperties(String property) {
       this.property = property;
   }

   public String getProperty() {
       return property;
   }
}