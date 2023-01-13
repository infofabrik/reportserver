package net.datenwerke.rs.utils.exception

import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.FREE_FORMATTED
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.MAX_FORMATTED
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.TOTAL_FORMATTED
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.USED_FORMATTED
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.APPLICATION_SERVER
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.BASE_REPORT_ID
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.BASE_REPORT_KEY
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.BASE_REPORT_NAME
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.BASE_REPORT_TYPE
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.DATASOURCE_GENERAL_INFO
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.DATASOURCE_ID
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.DATASOURCE_NAME
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.DATASOURCE_QUERY
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.DATASOURCE_TYPE
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.DEFAULT_SSL_PROTOCOLS
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.ENABLED_SSL_PROTOCOLS
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.ERROR
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.EXECUTING_USER_FIRSTNAME
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.EXECUTING_USER_ID
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.EXECUTING_USER_LASTNAME
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.EXECUTING_USER_USERNAME
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.GROOVY_VERSION
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.JAVA_VERSION
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.JAVA_VM_ARGUMENTS
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.JVM_LOCALE
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.LOCALE
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.MEMORY_FREE
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.MEMORY_MAX
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.MEMORY_TOTAL
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.MEMORY_USED
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.OS_VERSION
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.REPORTSERVER_VERSION
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.REPORT_ID
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.REPORT_KEY
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.REPORT_NAME
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.REPORT_OUTPUT_FORMAT
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.REPORT_TYPE
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.REPORT_UUID
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.STATIC_PAMS
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperty.SUPPORTED_SSL_PROTOCOLS
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_DATASOURCE
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_ERROR
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_EXECUTING_USER
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_GENERAL
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_MEMORY
import static net.datenwerke.rs.utils.exception.ExceptionService.LogPropertyType.TYPE_REPORT

import javax.inject.Inject

import org.apache.commons.lang3.exception.ExceptionUtils

import com.google.inject.Provider

import groovy.json.JsonBuilder
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService
import net.datenwerke.rs.base.service.datasources.DatasourceHelperService
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig
import net.datenwerke.rs.configservice.service.configservice.ConfigService
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant
import net.datenwerke.scheduler.service.scheduler.exceptions.JobExecutionException
import net.datenwerke.security.service.usermanager.entities.User

public class ExceptionServiceImpl implements ExceptionService {

   private final Provider<ConfigService> configServiceProvider
   private final Provider<GeneralInfoService> generalInfoServiceProvider
   private final Provider<DatasourceHelperService> datasourceHelperServiceProvider
    
   @Inject
   public ExceptionServiceImpl(
      Provider<ConfigService> configServiceProvider,
      Provider<GeneralInfoService> generalInfoServiceProvider,
      Provider<DatasourceHelperService> datasourceHelperServiceProvider
      ) {
         this.configServiceProvider = configServiceProvider
         this.generalInfoServiceProvider = generalInfoServiceProvider
         this.datasourceHelperServiceProvider = datasourceHelperServiceProvider
      }
   
   @Override
   public String exceptionToString(Throwable e) {
      def details = null
      if ((e instanceof ReportExecutorException || e instanceof JobExecutionException) && e.details)
         details = e.details
      Writer result = new StringWriter()
      PrintWriter printWriter = new PrintWriter(result)
      e.printStackTrace(printWriter)
      return (details? details + ' ': '') + result.toString()
   
   }

   @Override
   public String reportExecutionExceptionDetailsMessage(Throwable e, Report report, User user, String outputFormat, String uuid) {
      def config = configServiceProvider.get().getConfigFailsafe(CONFIG_FILE)
      def properties = config.getList(PROPERTIES) ?: getDefaultProperties().collect { it as String }
         
      def propertiesString = new JsonBuilder(populatePropertyValues(properties, e, report, user, outputFormat, uuid)).toString()
      return "Report could not be executed. $propertiesString"
   }
   
   private def populatePropertyValues(List<String> properties,
         Throwable e, Report report, User user, String outputFormat, String uuid) {
      def propertyValues = new LinkedHashMap()
      def baseReport = report instanceof ReportVariant ? report.baseReport: null
      def datasource = report?.datasourceContainer?.datasource
      properties.each {
         setProperty(it.toUpperCase(Locale.ROOT) as LogProperty,
               e, report, baseReport, datasource,
               user, outputFormat, uuid, propertyValues)
      }
      propertyValues
   }
   
   private def getDefaultProperties() {
      return [
         ERROR,
         REPORT_ID,
         REPORT_NAME,
         BASE_REPORT_ID,
         BASE_REPORT_NAME,
         EXECUTING_USER_ID,
         REPORT_OUTPUT_FORMAT,
         REPORT_UUID
      ]
   }
   
   private def setProperty(LogProperty property,
         Throwable e, Report report, Report baseReport, datasource, 
         User user, String outputFormat, String uuid, Map propertyValues) {
      switch (property.type) {
         case TYPE_ERROR:
            propertyValues[property] = ExceptionUtils.getRootCauseMessage(e)
            break
         case TYPE_REPORT:
            setReportProperty(property, report, baseReport, outputFormat, uuid, propertyValues)
            break
         case TYPE_EXECUTING_USER:
            setUserProperty(property, user, propertyValues)
            break
         case TYPE_GENERAL:
            setGeneralProperty(property, propertyValues)
            break
         case TYPE_MEMORY:
            setMemoryProperty(property, propertyValues)
            break
         case TYPE_DATASOURCE:
            setDatasourceProperty(property, report, datasource,propertyValues)
            break
         default:
            throw new IllegalArgumentException("Property not found: '$property'")
      }
   }
   
   private def setDatasourceProperty(LogProperty property, Report report, datasource, Map propertyValues) {
      switch (property) {
         case DATASOURCE_ID:
            propertyValues[property] = datasource?.id
            break
         case DATASOURCE_NAME:
            propertyValues[property] = datasource?.name
            break
         case DATASOURCE_TYPE:
            propertyValues[property] = datasource?.getClass()?.simpleName
            break
         case DATASOURCE_QUERY:
            def datasourceConfig = report?.datasourceContainer?.datasourceConfig
               if (datasourceConfig instanceof DatabaseDatasourceConfig)
                  propertyValues[property] = datasourceHelperServiceProvider.get().getQuery(report?.datasourceContainer)
            break
         case DATASOURCE_GENERAL_INFO:
            if (datasource instanceof DatabaseDatasource)
                  propertyValues[property] = formatGetters(datasourceHelperServiceProvider.get()
                        .fetchInfoDatasourceMetadata(datasource, true , true, false, false)) 
            break
         default:
            throw new IllegalArgumentException('not a datasource property')
      }
   }
   
   private def formatGetters(Map map) {
      return map
        .collectEntries { key, val -> [(key - 'get'): val] }
        .collectEntries { key, val -> 
           (key.startsWith('URL') || key.startsWith('JDBC')) ? [(key): val] : [ "${key.uncapitalize()}": val] }
   }
   
   private def setReportProperty(LogProperty property, Report report, Report baseReport, 
      String outputFormat, String uuid, Map propertyValues) {
      switch (property) {
         case REPORT_ID:
            propertyValues[property] = report.id ?: report.oldTransientId
            break
         case REPORT_NAME:
            propertyValues[property] = report.name
            break
         case REPORT_KEY:
            propertyValues[property] = report.key
            break
         case REPORT_TYPE:
            propertyValues[property] = report.type
            break
         case BASE_REPORT_ID:
            if (baseReport)
               propertyValues[property] = baseReport.id ?: baseReport.oldTransientId
            break
         case BASE_REPORT_NAME:
            if (baseReport)
               propertyValues[property] = baseReport.name
            break
         case BASE_REPORT_KEY:
            if (baseReport)
               propertyValues[property] = baseReport.key
            break
         case BASE_REPORT_TYPE:
            if (baseReport)
               propertyValues[property] = baseReport.type
            break
         case REPORT_OUTPUT_FORMAT:
            propertyValues[property] = outputFormat
            break
         case REPORT_UUID:
            propertyValues[property] = uuid
            break
         default:
            throw new IllegalArgumentException('not a report property')
      }
   }
   
   private def setUserProperty(LogProperty property, User user, Map propertyValues) {
      switch (property) {
         case EXECUTING_USER_ID:
            propertyValues[property] = user.id
            break
         case EXECUTING_USER_USERNAME:
            propertyValues[property] = user.username
            break
         case EXECUTING_USER_FIRSTNAME:
            propertyValues[property] = user.firstname
            break
         case EXECUTING_USER_LASTNAME:
            propertyValues[property] = user.lastname
            break
         default:
            throw new IllegalArgumentException('not a user property')
      }
   }

   private def setGeneralProperty(LogProperty property, Map propertyValues) {
      def generalInfoService = generalInfoServiceProvider.get()
      switch (property) {
         case JAVA_VERSION:
            propertyValues[property] = generalInfoService.javaVersion
            break
         case JAVA_VM_ARGUMENTS:
            propertyValues[property] = generalInfoService.vmArguments
            break
         case APPLICATION_SERVER:
            propertyValues[property] = generalInfoService.applicationServer
            break
         case OS_VERSION:
            propertyValues[property] = generalInfoService.osVersion
            break
         case REPORTSERVER_VERSION:
            propertyValues[property] = generalInfoService.rsVersion
            break
         case LOCALE:
            propertyValues[property] = generalInfoService.locale
            break
         case JVM_LOCALE:
            propertyValues[property] = generalInfoService.jvmLocale
            break
         case SUPPORTED_SSL_PROTOCOLS:
            propertyValues[property] = generalInfoService.supportedSslProtocols
            break
         case DEFAULT_SSL_PROTOCOLS:
            propertyValues[property] = generalInfoService.defaultSslProtocols
            break
         case ENABLED_SSL_PROTOCOLS:
            propertyValues[property] = generalInfoService.enabledSslProtocols
            break
         case GROOVY_VERSION:
            propertyValues[property] = generalInfoService.groovyVersion
            break
         case STATIC_PAMS:
            propertyValues[property] = generalInfoService.staticPams
            break
         default:
            throw new IllegalArgumentException('not a general property')
      }
   }

   private def setMemoryProperty(LogProperty property,  Map propertyValues) {
      def memory = generalInfoServiceProvider.get().memoryValues
      switch (property) {
         case MEMORY_USED:
            propertyValues[property] = memory[USED_FORMATTED]
            break
         case MEMORY_FREE:
            propertyValues[property] = memory[FREE_FORMATTED]
            break
         case MEMORY_TOTAL:
            propertyValues[property] = memory[TOTAL_FORMATTED]
            break
         case MEMORY_MAX:
            propertyValues[property] = memory[MAX_FORMATTED]
            break
         default:
            throw new IllegalArgumentException('not a memory property')
      }
   }

   @Override
   public Optional<String> getReportExecutionExceptionDetailsMessage(Throwable e) {
      if (e instanceof ReportExecutorException || e instanceof JobExecutionException)
         return Optional.ofNullable(e.details)
         
      return Optional.empty()
   }
}
