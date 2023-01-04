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
   private String maxMemory;
   private String osVersion;
   private String userAgent;
   private String locale;
   private String jvmLocale;
   
   private String internalDbId;
   private String internalDbDatasourceName;
   private String internalDbDatabaseName;
   private String internalDbVersion;
   private String internalDbDriverName;
   private String internalDbDriverVersion;
   private String internalDbJdbcMajorVersion;
   private String internalDbJdbcMinorVersion;
   private String internalDbJdbcUrl;
   private String internalDbUsername;
   private String supportedSslProtocols;
   private String defaultSslProtocols;
   private String enabledSslProtocols;

   public GeneralInfoDto() {
   }
   
   public String getLocale() {
      return locale;
   }

   public void setLocale(String locale) {
      this.locale = locale;
   }
   
   public String getJvmLocale() {
      return jvmLocale;
   }

   public void setJvmLocale(String jvmLocale) {
      this.jvmLocale = jvmLocale;
   }
   
   public String getUserAgent() {
      return userAgent;
   }

   public void setUserAgent(String userAgent) {
      this.userAgent = userAgent;
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
   
   public String getMaxMemory() {
      return maxMemory;
   }
   
   public void setMaxMemory(String maxMemory) {
      this.maxMemory = maxMemory;
   }

   public String getRsVersion() {
      return rsVersion;
   }

   public void setRsVersion(String rsVersion) {
      this.rsVersion = rsVersion;
   }

   public String getInternalDbId() {
      return internalDbId;
   }

   public void setInternalDbId(String internalDbId) {
      this.internalDbId = internalDbId;
   }

   public String getInternalDbDatasourceName() {
      return internalDbDatasourceName;
   }

   public void setInternalDbDatasourceName(String internalDbDatasourceName) {
      this.internalDbDatasourceName = internalDbDatasourceName;
   }

   public String getInternalDbDatabaseName() {
      return internalDbDatabaseName;
   }

   public void setInternalDbDatabaseName(String internalDbDatabaseName) {
      this.internalDbDatabaseName = internalDbDatabaseName;
   }

   public String getInternalDbVersion() {
      return internalDbVersion;
   }

   public void setInternalDbVersion(String internalDbVersion) {
      this.internalDbVersion = internalDbVersion;
   }

   public String getInternalDbDriverName() {
      return internalDbDriverName;
   }

   public void setInternalDbDriverName(String internalDbDriverName) {
      this.internalDbDriverName = internalDbDriverName;
   }

   public String getInternalDbDriverVersion() {
      return internalDbDriverVersion;
   }
   
   public void setInternalDbDriverVersion(String internalDbDriverVersion) {
      this.internalDbDriverVersion = internalDbDriverVersion;
   }
   
   public String getInternalDbJdbcMajorVersion() {
      return internalDbJdbcMajorVersion;
   }
   
   public void setInternalDbJdbcMajorVersion(String internalDbJdbcMajorVersion) {
      this.internalDbJdbcMajorVersion = internalDbJdbcMajorVersion;
   }
   
   public String getInternalDbJdbcMinorVersion() {
      return internalDbJdbcMinorVersion;
   }
   
   public void setInternalDbJdbcMinorVersion(String internalDbJdbcMinorVersion) {
      this.internalDbJdbcMinorVersion = internalDbJdbcMinorVersion;
   }
   
   public String getInternalDbJdbcUrl() {
      return internalDbJdbcUrl;
   }
   
   public void setInternalDbJdbcUrl(String internalDbJdbcUrl) {
      this.internalDbJdbcUrl = internalDbJdbcUrl;
   }
   
   public String getInternalDbUsername() {
      return internalDbUsername;
   }
   
   public void setInternalDbUsername(String internalDbUsername) {
      this.internalDbUsername = internalDbUsername;
   }

   public void setSupportedSslProtocols(String supportedSslProtocols) {
      this.supportedSslProtocols = supportedSslProtocols;
   }

   public void setDefaultSslProtocols(String defaultSslProtocols) {
      this.defaultSslProtocols = defaultSslProtocols;      
   }

   public void setEnabledSslProtocols(String enabledSslProtocols) {
      this.enabledSslProtocols = enabledSslProtocols;
   }

   public String getSupportedSslProtocols() {
      return this.supportedSslProtocols;
   }
   
   public String getDefaultSslProtocols() {
      return this.defaultSslProtocols;      
   }
   
   public String getEnabledSslProtocols() {
      return this.enabledSslProtocols;
   }
}
