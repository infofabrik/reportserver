package net.datenwerke.rs.scheduler.client.scheduler.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DateTriggerConfigDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

/**
 * Simple transfer object to transfer the schedule information to the server.
 * 
 *
 */
public class ReportScheduleDefinition implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 529346772433967794L;

   private String title;
   private String description;

   /**
    * make sure to install all types
    */
   private ReportDto report;
   private String outputFormat;

   private List<ReportExecutionConfigDto> exportConfiguration;

   private UserDto executor;
   private StrippedDownUser scheduledBy;

   private List<StrippedDownUser> owners = new ArrayList<>();
   private List<Long> recipients;

   private List<AdditionalScheduleInformation> additionalInfos = new ArrayList<AdditionalScheduleInformation>();

   private DateTriggerConfigDto schedulerConfig;

   private Long jobId;

   private ArrayList<ReportScheduleDefinitionSendToConfig> sendToConfigs = new ArrayList<ReportScheduleDefinitionSendToConfig>();

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public ReportDto getReport() {
      return report;
   }

   public void setReport(ReportDto report) {
      this.report = report;
   }

   public void setExportConfiguration(List<ReportExecutionConfigDto> exportConfiguration) {
      this.exportConfiguration = exportConfiguration;
   }

   public List<ReportExecutionConfigDto> getExportConfiguration() {
      return exportConfiguration;
   }

   public void setSchedulerConfig(DateTriggerConfigDto config) {
      this.schedulerConfig = config;
   }

   public DateTriggerConfigDto getSchedulerConfig() {
      return schedulerConfig;
   }

   public void setAdditionalInfos(List<AdditionalScheduleInformation> additionalInfos) {
      this.additionalInfos = additionalInfos;
   }

   public void addAdditionalInfo(AdditionalScheduleInformation info) {
      this.additionalInfos.add(info);
   }

   public List<AdditionalScheduleInformation> getAdditionalInfos() {
      return additionalInfos;
   }

   public String getOutputFormat() {
      return outputFormat;
   }

   public void setOutputFormat(String outputFormat) {
      this.outputFormat = outputFormat;
   }

   public void setRecipients(List<Long> recipients) {
      this.recipients = recipients;
   }

   public List<Long> getRecipients() {
      return recipients;
   }

   public ArrayList<ReportScheduleDefinitionSendToConfig> getSendToConfigs() {
      return sendToConfigs;
   }

   public void setSendToConfigs(ArrayList<ReportScheduleDefinitionSendToConfig> sendToConfigs) {
      this.sendToConfigs = sendToConfigs;
   }

   public void addSendToConfigs(ReportScheduleDefinitionSendToConfig sendToConfig) {
      if (null == sendToConfigs)
         this.sendToConfigs = new ArrayList<ReportScheduleDefinitionSendToConfig>();
      sendToConfigs.add(sendToConfig);
   }

   public ReportScheduleDefinitionSendToConfig getSendToConfig(final String id) {
      if (null == sendToConfigs)
         return null;

      return sendToConfigs.stream().filter(config -> id.equals(config.getId())).findAny().orElse(null);
   }

   public void setOwners(List<StrippedDownUser> owners) {
      this.owners = owners;
   }

   public List<StrippedDownUser> getOwners() {
      return owners;
   }

   public <A> A getAdditionalInfo(Class<A> type) {
      if (null == type || null == additionalInfos)
         return null;

      for (AdditionalScheduleInformation info : getAdditionalInfos())
         if (type.equals(info.getClass()))
            return (A) info;

      return null;
   }

   public void setJobId(Long jobId) {
      this.jobId = jobId;
   }

   public Long getJobId() {
      return jobId;
   }

   RsDto whitelist_1;

   public UserDto getExecutor() {
      return executor;
   }

   public void setExecutor(UserDto executor) {
      this.executor = executor;
   }

   public StrippedDownUser getScheduledBy() {
      return scheduledBy;
   }

   public void setScheduledBy(StrippedDownUser scheduledBy) {
      this.scheduledBy = scheduledBy;
   }
}
