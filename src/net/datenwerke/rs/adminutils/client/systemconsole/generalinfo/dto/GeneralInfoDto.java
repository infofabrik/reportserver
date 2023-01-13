package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto;

import java.io.Serializable;
import java.util.List;

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
   private String groovyVersion;
   private String osVersion;
   private String userAgent;
   private String locale;
   private String jvmLocale;
   
   private String hibernateDialect;
   private String hibernateDriverClass;
   private String hibernateConnectionUrl;
   private String hibernateConnectionUsername;
   private String hibernateDefaultSchema;
   private String schemaVersion;
   
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
   private List<String> supportedSslProtocols;
   private List<String> defaultSslProtocols;
   private List<String> enabledSslProtocols;
   private List<String> staticPams;

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

   public void setSupportedSslProtocols(List<String> supportedSslProtocols) {
      this.supportedSslProtocols = supportedSslProtocols;
   }

   public void setDefaultSslProtocols(List<String> defaultSslProtocols) {
      this.defaultSslProtocols = defaultSslProtocols;      
   }

   public void setEnabledSslProtocols(List<String> enabledSslProtocols) {
      this.enabledSslProtocols = enabledSslProtocols;
   }

   public List<String> getSupportedSslProtocols() {
      return this.supportedSslProtocols;
   }
   
   public List<String> getDefaultSslProtocols() {
      return this.defaultSslProtocols;      
   }
   
   public List<String> getEnabledSslProtocols() {
      return this.enabledSslProtocols;
   }

   public List<String> getStaticPams() {
      return this.staticPams;
   }

   public void setStaticPams(List<String> staticPams) {
      this.staticPams = staticPams;
   }

   public String getGroovyVersion() {
      return groovyVersion;
   }

   public void setGroovyVersion(String groovyVersion) {
      this.groovyVersion = groovyVersion;
   }

   public String getHibernateDialect() {
      return hibernateDialect;
   }

   public void setHibernateDialect(String hibernateDialect) {
      this.hibernateDialect = hibernateDialect;
   }

   public String getHibernateConnectionUsername() {
      return hibernateConnectionUsername;
   }

   public void setHibernateConnectionUsername(String hibernateConnectionUsername) {
      this.hibernateConnectionUsername = hibernateConnectionUsername;
   }

   public String getHibernateDefaultSchema() {
      return hibernateDefaultSchema;
   }

   public void setHibernateDefaultSchema(String hibernateDefaultSchema) {
      this.hibernateDefaultSchema = hibernateDefaultSchema;
   }

   public String getHibernateDriverClass() {
      return hibernateDriverClass;
   }

   public void setHibernateDriverClass(String hibernateDriverClass) {
      this.hibernateDriverClass = hibernateDriverClass;
   }

   public String getHibernateConnectionUrl() {
      return hibernateConnectionUrl;
   }

   public void setHibernateConnectionUrl(String hibernateConnectionUrl) {
      this.hibernateConnectionUrl = hibernateConnectionUrl;
   }

   public String getSchemaVersion() {
      return schemaVersion;
   }

   public void setSchemaVersion(String hibernateSchemaVersion) {
      this.schemaVersion = hibernateSchemaVersion;
   }

}
