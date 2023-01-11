package net.datenwerke.rs.utils.exception

import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.USED_FORMATTED
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.FREE_FORMATTED
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.TOTAL_FORMATTED
import static net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory.MAX_FORMATTED
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.APPLICATION_SERVER
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.BASE_REPORT_ID
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.BASE_REPORT_KEY
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.BASE_REPORT_NAME
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.BASE_REPORT_TYPE
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.DEFAULT_SSL_PROTOCOLS
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.ENABLED_SSL_PROTOCOLS
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.ERROR
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.EXECUTING_USER_FIRSTNAME
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.EXECUTING_USER_ID
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.EXECUTING_USER_LASTNAME
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.EXECUTING_USER_USERNAME
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.GROOVY_VERSION
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.JAVA_VERSION
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.JAVA_VM_ARGUMENTS
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.JVM_LOCALE
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.LOCALE
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.MEMORY_FREE
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.MEMORY_MAX
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.MEMORY_TOTAL
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.MEMORY_USED
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.OS_VERSION
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.REPORTSERVER_VERSION
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.REPORT_ID
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.REPORT_KEY
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.REPORT_NAME
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.REPORT_OUTPUT_FORMAT
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.REPORT_TYPE
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.REPORT_UUID
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.STATIC_PAMS
import static net.datenwerke.rs.utils.exception.ExceptionService.LogProperties.SUPPORTED_SSL_PROTOCOLS

import javax.inject.Inject

import org.apache.commons.lang3.exception.ExceptionUtils

import com.google.inject.Provider

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.Memory
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService
import net.datenwerke.rs.configservice.service.configservice.ConfigService
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant
import net.datenwerke.security.service.usermanager.entities.User

public class ExceptionServiceImpl implements ExceptionService {

   private final Provider<ConfigService> configServiceProvider
   private final Provider<GeneralInfoService> generalInfoServiceProvider
    
   @Inject
   public ExceptionServiceImpl(
      Provider<ConfigService> configServiceProvider,
      Provider<GeneralInfoService> generalInfoServiceProvider
      ) {
         this.configServiceProvider = configServiceProvider
         this.generalInfoServiceProvider = generalInfoServiceProvider
      }
   
   @Override
   public String exceptionToString(Throwable e) {
      Writer result = new StringWriter()
      PrintWriter printWriter = new PrintWriter(result)
      e.printStackTrace(printWriter)
      return result.toString()
   }

   @Override
   public String reportExecutionExceptionDetailsMessage(Throwable e, Report report, User user, String outputFormat, String uuid) {
      def config = configServiceProvider.get().getConfigFailsafe(CONFIG_FILE)
      def properties = config.getList(PROPERTIES) ?: getDefaultProperties().collect { it as String }
         
      def propertiesString = populatePropertyValues(properties, e, report, user, outputFormat, uuid)
         .inject('') { s, key, val -> s += "${s?', ':''}" + "$key: '$val'" }
      return "Report could not be executed. $propertiesString"
   }
   
   private def populatePropertyValues(List<String> properties,
         Throwable e, Report report, User user, String outputFormat, String uuid) {
      Report baseReport = report instanceof ReportVariant ? report.baseReport: null
      def generalInfoService = generalInfoServiceProvider.get()
      def memory = generalInfoService.memoryValues
      def propertyValues = new LinkedHashMap()
      properties.each {
         switch (it.toUpperCase(Locale.ROOT) as LogProperties) {
            case ERROR:
               propertyValues[it] = ExceptionUtils.getRootCauseMessage(e)
               break
            case REPORT_ID:
               propertyValues[it] = report.id ?: report.oldTransientId
               break
            case REPORT_NAME:
               propertyValues[it] = report.name
               break
            case REPORT_KEY:
               propertyValues[it] = report.key
               break
            case REPORT_TYPE:
               propertyValues[it] = report.type
               break
            case BASE_REPORT_ID:
               if (baseReport)
                  propertyValues[it] = baseReport.id ?: baseReport.oldTransientId
               break
            case BASE_REPORT_NAME:
               if (baseReport)
                  propertyValues[it] = baseReport.name
               break
            case BASE_REPORT_KEY:
               if (baseReport)
                  propertyValues[it] = baseReport.key
               break
            case BASE_REPORT_TYPE:
               if (baseReport)
                  propertyValues[it] = baseReport.type
               break
            case REPORT_OUTPUT_FORMAT:
               propertyValues[it] = outputFormat
               break
            case REPORT_UUID:
               propertyValues[it] = uuid
               break
            case EXECUTING_USER_ID:
               propertyValues[it] = user.id
               break
            case EXECUTING_USER_USERNAME:
               propertyValues[it] = user.username
               break
            case EXECUTING_USER_FIRSTNAME:
               propertyValues[it] = user.firstname
               break
            case EXECUTING_USER_LASTNAME:
               propertyValues[it] = user.lastname
               break
            case JAVA_VERSION:
               propertyValues[it] = generalInfoService.javaVersion
               break
            case JAVA_VM_ARGUMENTS:
               propertyValues[it] = generalInfoService.vmArguments
               break
            case APPLICATION_SERVER:
               propertyValues[it] = generalInfoService.applicationServer
               break
            case OS_VERSION:
               propertyValues[it] = generalInfoService.osVersion
               break
            case REPORTSERVER_VERSION:
               propertyValues[it] = generalInfoService.rsVersion
               break
            case LOCALE:
               propertyValues[it] = generalInfoService.locale
               break
            case JVM_LOCALE:
               propertyValues[it] = generalInfoService.jvmLocale
               break
            case SUPPORTED_SSL_PROTOCOLS:
               propertyValues[it] = generalInfoService.supportedSslProtocols.join(', ')
               break
            case DEFAULT_SSL_PROTOCOLS:
               propertyValues[it] = generalInfoService.defaultSslProtocols.join(', ')
               break
            case ENABLED_SSL_PROTOCOLS:
               propertyValues[it] = generalInfoService.enabledSslProtocols.join(', ')
               break
            case GROOVY_VERSION:
               propertyValues[it] = generalInfoService.groovyVersion
               break
            case STATIC_PAMS:
               propertyValues[it] = generalInfoService.staticPams
               break
            case MEMORY_USED:
               propertyValues[it] = memory[USED_FORMATTED]
               break
            case MEMORY_FREE:
               propertyValues[it] = memory[FREE_FORMATTED]
               break
            case MEMORY_TOTAL:
               propertyValues[it] = memory[TOTAL_FORMATTED]
               break
            case MEMORY_MAX:
               propertyValues[it] = memory[MAX_FORMATTED]
               break
         }
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
}
