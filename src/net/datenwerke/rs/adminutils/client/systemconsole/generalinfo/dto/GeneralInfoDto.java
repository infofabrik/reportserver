package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class GeneralInfoDto implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private String rsVersion;
   private String javaVersion;
   private String javaHome;
   private String vmArguments;
   private String applicationServer;
   private String catalinaHome;
   private String catalinaBase;
   private String logFilesDirectory;
   private String restURL;
   private String requestURL;
   private String serverName;
   private int serverPort;
   private String scheme;
   private String contextPath;
   private String protocol;
   private String maxMemory;
   private String groovyVersion;
   private String now;
   private String osVersion;
   private String userAgent;

   private String locale;
   private String jvmLocale;
   private String jvmUserLanguage;
   private String jvmUserCountry;
   private String jvmUserTimezone;
   private String jvmFileEncoding;

   private String hibernateDialect;
   private String hibernateDefaultSchema;
   private String hibernateDbDatabaseName;
   private String hibernateDbVersion;
   private String hibernateDbDriverName;
   private String hibernateDbDriverVersion;
   private String hibernateDbJdbcMajorVersion;
   private String hibernateDbJdbcMinorVersion;
   private String hibernateDbJdbcUrl;
   private String hibernateDbJdbcUsername;

   private String configDir;

   private String schemaVersion;

   private boolean internalDbConfigured;
   private String internalDbId;
   private String internalDbDatasourceName;
   private String internalDbPath;
   private String internalDbDatabaseName;
   private String internalDbVersion;
   private String internalDbDriverName;
   private String internalDbDriverVersion;
   private String internalDbJdbcMajorVersion;
   private String internalDbJdbcMinorVersion;
   private String internalDbJdbcUrl;
   private String internalDbUsername;
   private Map<String, String> internalDbJdbcProperties;

   private String sslKnownHosts;
   private List<String> supportedSslProtocols;
   private List<String> defaultSslProtocols;
   private List<String> enabledSslProtocols;

   private List<String> staticPams;
   
   private boolean sftpEnabled;
   private String sftpKey;
   private int sftpPort;
   
   private long baseReportCount;
   private long variantReportCount;
   private long baseJasperCount;
   private long variantJasperCount;
   private long baseBirtCount;
   private long variantBirtCount;
   private long baseCrystalCount;
   private long variantCrystalCount;
   private long baseDynamicListCount;
   private long variantDynamicListCount;
   private long baseScriptReportCount;
   private long variantScriptReportCount;
   private long baseGridReportCount;
   private long variantGridReportCount;
   private long baseJxlsReportCount;
   private long variantJxlsReportCount;
   private long baseSaikuReportCount;
   private long variantSaikuReportCount;

   public GeneralInfoDto() {
   }
   
   public String getJavaHome() {
      return javaHome;
   }

   public void setJavaHome(String javaHome) {
      this.javaHome = javaHome;
   }

   public String getCatalinaHome() {
      return catalinaHome;
   }

   public void setCatalinaHome(String catalinaHome) {
      this.catalinaHome = catalinaHome;
   }

   public String getCatalinaBase() {
      return catalinaBase;
   }

   public void setCatalinaBase(String catalinaBase) {
      this.catalinaBase = catalinaBase;
   }

   public String getJvmUserLanguage() {
      return jvmUserLanguage;
   }

   public void setJvmUserLanguage(String jvmUserLanguage) {
      this.jvmUserLanguage = jvmUserLanguage;
   }

   public String getJvmUserCountry() {
      return jvmUserCountry;
   }

   public void setJvmUserCountry(String jvmUserCountry) {
      this.jvmUserCountry = jvmUserCountry;
   }

   public String getJvmUserTimezone() {
      return jvmUserTimezone;
   }

   public void setJvmUserTimezone(String jvmUserTimezone) {
      this.jvmUserTimezone = jvmUserTimezone;
   }

   public String getJvmFileEncoding() {
      return jvmFileEncoding;
   }

   public void setJvmFileEncoding(String jvmFileEncoding) {
      this.jvmFileEncoding = jvmFileEncoding;
   }

   public boolean isInternalDbConfigured() {
      return internalDbConfigured;
   }

   public void setInternalDbConfigured(boolean internalDbConfigured) {
      this.internalDbConfigured = internalDbConfigured;
   }

   public Map<String, String> getInternalDbJdbcProperties() {
      return internalDbJdbcProperties;
   }

   public void setInternalDbJdbcProperties(Map<String, String> internalDbJdbcProperties) {
      this.internalDbJdbcProperties = internalDbJdbcProperties;
   }

   public String getConfigDir() {
      return configDir;
   }

   public void setConfigDir(String configDir) {
      this.configDir = configDir;
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
   
   public String getNow() {
      return now;
   }

   public void setNow(String now) {
      this.now = now;
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
   
   public String getServerName() {
      return serverName;
   }

   public void setServerName(String serverName) {
      this.serverName = serverName;
   }

   public int getServerPort() {
      return serverPort;
   }

   public void setServerPort(int serverPort) {
      this.serverPort = serverPort;
   }

   public String getRequestURL() {
      return requestURL;
   }

   public void setRequestURL(String requestURL) {
      this.requestURL = requestURL;
   }

   public String getScheme() {
      return scheme;
   }

   public void setScheme(String scheme) {
      this.scheme = scheme;
   }

   public String getContextPath() {
      return contextPath;
   }

   public void setContextPath(String contextPath) {
      this.contextPath = contextPath;
   }
   
   public String getProtocol() {
      return protocol;
   }

   public void setProtocol(String protocol) {
      this.protocol = protocol;
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

   public String getInternalDbPath() {
      return internalDbPath;
   }

   public void setInternalDbPath(String internalDbPath) {
      this.internalDbPath = internalDbPath;
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
   
   public String getSslKnownHosts() {
      return sslKnownHosts;
   }

   public void setSslKnownHosts(String sslKnownHosts) {
      this.sslKnownHosts = sslKnownHosts;
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

   public String getHibernateDefaultSchema() {
      return hibernateDefaultSchema;
   }

   public void setHibernateDefaultSchema(String hibernateDefaultSchema) {
      this.hibernateDefaultSchema = hibernateDefaultSchema;
   }
   
   public String getHibernateDbDatabaseName() {
      return hibernateDbDatabaseName;
   }

   public void setHibernateDbDatabaseName(String hibernateDbDatabaseName) {
      this.hibernateDbDatabaseName = hibernateDbDatabaseName;
   }

   public String getHibernateDbVersion() {
      return hibernateDbVersion;
   }

   public void setHibernateDbVersion(String hibernateDbVersion) {
      this.hibernateDbVersion = hibernateDbVersion;
   }

   public String getHibernateDbDriverName() {
      return hibernateDbDriverName;
   }

   public void setHibernateDbDriverName(String hibernateDbDriverName) {
      this.hibernateDbDriverName = hibernateDbDriverName;
   }
   
   public String getHibernateDbDriverVersion() {
      return hibernateDbDriverVersion;
   }

   public void setHibernateDbDriverVersion(String hibernateDbDriverVersion) {
      this.hibernateDbDriverVersion = hibernateDbDriverVersion;
   }

   public String getHibernateDbJdbcMajorVersion() {
      return hibernateDbJdbcMajorVersion;
   }

   public void setHibernateDbJdbcMajorVersion(String hibernateDbJdbcMajorVersion) {
      this.hibernateDbJdbcMajorVersion = hibernateDbJdbcMajorVersion;
   }

   public String getHibernateDbJdbcMinorVersion() {
      return hibernateDbJdbcMinorVersion;
   }

   public void setHibernateDbJdbcMinorVersion(String hibernateDbJdbcMinorVersion) {
      this.hibernateDbJdbcMinorVersion = hibernateDbJdbcMinorVersion;
   }

   public String getHibernateDbJdbcUrl() {
      return hibernateDbJdbcUrl;
   }

   public void setHibernateDbJdbcUrl(String hibernateDbJdbcUrl) {
      this.hibernateDbJdbcUrl = hibernateDbJdbcUrl;
   }

   public String getHibernateDbJdbcUsername() {
      return hibernateDbJdbcUsername;
   }

   public void setHibernateDbJdbcUsername(String hibernateDbJdbcUsername) {
      this.hibernateDbJdbcUsername = hibernateDbJdbcUsername;
   }

   public String getSchemaVersion() {
      return schemaVersion;
   }

   public void setSchemaVersion(String hibernateSchemaVersion) {
      this.schemaVersion = hibernateSchemaVersion;
   }

   public String getLogFilesDirectory() {
      return logFilesDirectory;
   }

   public void setLogFilesDirectory(String logFilesDirectory) {
      this.logFilesDirectory = logFilesDirectory;
   }

   public boolean isSftpEnabled() {
      return sftpEnabled;
   }

   public void setSftpEnabled(boolean sftpEnabled) {
      this.sftpEnabled = sftpEnabled;
   }
   
   public String getSftpKey() {
      return sftpKey;
   }

   public void setSftpKey(String sftpKey) {
      this.sftpKey = sftpKey;
   }

   public int getSftpPort() {
      return sftpPort;
   }

   public void setSftpPort(int sftpPort) {
      this.sftpPort = sftpPort;
   }

   public void setRestURL(String restURL) {
      this.restURL = restURL;
   }
   
   public String getRestURL() {
      return restURL;
   }

   public long getBaseReportCount() {
      return baseReportCount;
   }

   public void setBaseReportCount(long baseReportCount) {
      this.baseReportCount = baseReportCount;
   }

   public long getVariantReportCount() {
      return variantReportCount;
   }

   public void setVariantReportCount(long variantReportCount) {
      this.variantReportCount = variantReportCount;
   }

   public long getBaseJasperCount() {
      return baseJasperCount;
   }

   public void setBaseJasperCount(long baseJasperCount) {
      this.baseJasperCount = baseJasperCount;
   }

   public long getVariantJasperCount() {
      return variantJasperCount;
   }

   public void setVariantJasperCount(long variantJasperCount) {
      this.variantJasperCount = variantJasperCount;
   }

   public long getBaseBirtCount() {
      return baseBirtCount;
   }

   public void setBaseBirtCount(long baseBirtCount) {
      this.baseBirtCount = baseBirtCount;
   }

   public long getVariantBirtCount() {
      return variantBirtCount;
   }

   public void setVariantBirtCount(long variantBirtCount) {
      this.variantBirtCount = variantBirtCount;
   }

   public long getBaseCrystalCount() {
      return baseCrystalCount;
   }

   public void setBaseCrystalCount(long baseCrystalCount) {
      this.baseCrystalCount = baseCrystalCount;
   }

   public long getVariantCrystalCount() {
      return variantCrystalCount;
   }

   public void setVariantCrystalCount(long variantCrystalCount) {
      this.variantCrystalCount = variantCrystalCount;
   }

   public long getBaseDynamicListCount() {
      return baseDynamicListCount;
   }

   public void setBaseDynamicListCount(long baseDynamicListCount) {
      this.baseDynamicListCount = baseDynamicListCount;
   }

   public long getVariantDynamicListCount() {
      return variantDynamicListCount;
   }

   public void setVariantDynamicListCount(long variantDynamicListCount) {
      this.variantDynamicListCount = variantDynamicListCount;
   }

   public long getBaseScriptReportCount() {
      return baseScriptReportCount;
   }

   public void setBaseScriptReportCount(long baseScriptReportCount) {
      this.baseScriptReportCount = baseScriptReportCount;
   }

   public long getVariantScriptReportCount() {
      return variantScriptReportCount;
   }

   public void setVariantScriptReportCount(long variantScriptReportCount) {
      this.variantScriptReportCount = variantScriptReportCount;
   }

   public long getBaseGridReportCount() {
      return baseGridReportCount;
   }

   public void setBaseGridReportCount(long baseGridReportCount) {
      this.baseGridReportCount = baseGridReportCount;
   }

   public long getVariantGridReportCount() {
      return variantGridReportCount;
   }

   public void setVariantGridReportCount(long variantGridReportCount) {
      this.variantGridReportCount = variantGridReportCount;
   }

   public long getBaseJxlsReportCount() {
      return baseJxlsReportCount;
   }

   public void setBaseJxlsReportCount(long baseJxlsReportCount) {
      this.baseJxlsReportCount = baseJxlsReportCount;
   }

   public long getVariantJxlsReportCount() {
      return variantJxlsReportCount;
   }

   public void setVariantJxlsReportCount(long variantJxlsReportCount) {
      this.variantJxlsReportCount = variantJxlsReportCount;
   }

   public long getBaseSaikuReportCount() {
      return baseSaikuReportCount;
   }

   public void setBaseSaikuReportCount(long baseSaikuReportCount) {
      this.baseSaikuReportCount = baseSaikuReportCount;
   }

   public long getVariantSaikuReportCount() {
      return variantSaikuReportCount;
   }

   public void setVariantSaikuReportCount(long variantSaikuReportCount) {
      this.variantSaikuReportCount = variantSaikuReportCount;
   }
   
}
