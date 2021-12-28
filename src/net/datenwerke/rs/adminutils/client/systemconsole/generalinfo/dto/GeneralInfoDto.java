package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto;

import java.io.Serializable;

public class GeneralInfoDto implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private String rsVersion;
   private String javaVersion;
   private String vmArguments;
   private String applicationServer;
   private String osVersion;
   private String browserName;
   private String browserVersion;

   public String getBrowserName() {
      return browserName;
   }

   public void setBrowserName(String name) {
      this.browserName = name;
   }

   public String getOsVersion() {
      return osVersion;
   }

   public void setOsVersion(String osVersion) {
      this.osVersion = osVersion;
   }

   public String getJavaVersion() {
      return javaVersion;
   }

   public void setJavaVersion(String javaVersion) {
      this.javaVersion = javaVersion;
   }

   public String getVmArguments() {
      return vmArguments;
   }

   public void setVmArguments(String vmArguments) {
      this.vmArguments = vmArguments;
   }

   public String getApplicationServer() {
      return applicationServer;
   }

   public void setApplicationServer(String applicationServer) {
      this.applicationServer = applicationServer;
   }

   public GeneralInfoDto() {
   }

   public String getRsVersion() {
      return rsVersion;
   }

   public void setRsVersion(String rsVersion) {
      this.rsVersion = rsVersion;
   }

   public String getBrowserVersion() {
      return browserVersion;
   }

   public void setBrowserVersion(String browserVersion) {
      this.browserVersion = browserVersion;
   }
}
