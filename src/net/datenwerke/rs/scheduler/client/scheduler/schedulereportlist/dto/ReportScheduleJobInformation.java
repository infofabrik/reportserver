package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto;

import java.util.Date;
import java.util.List;

import net.datenwerke.gf.base.client.dtogenerator.RsDto;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public class ReportScheduleJobInformation extends RsDto {

   /**
    * 
    */
   private static final long serialVersionUID = -2710512228549048347L;

   private ReportScheduleDefinition scheduleDefinition;

   private List<Date> nextPlannedEntries;
   private List<ExecutionLogEntryDto> lastExecutedEntries;

   private int nrOfSuccessfulExecutions = 0;
   private int nrOfVetoedExecutions = 0;
   private int nrOfFailedExecutions = 0;

   private JobExecutionStatusDto executionStatus;

   private Long userId;

   public void setScheduleDefinition(ReportScheduleDefinition scheduleDefinition) {
      this.scheduleDefinition = scheduleDefinition;
   }

   public ReportScheduleDefinition getScheduleDefinition() {
      return scheduleDefinition;
   }

   public void setNextPlannedEntries(List<Date> nextPlannedEntries) {
      this.nextPlannedEntries = nextPlannedEntries;
   }

   public List<Date> getNextPlannedEntries() {
      return nextPlannedEntries;
   }

   public void setLastExecutedEntries(List<ExecutionLogEntryDto> lastExecutedEntries) {
      this.lastExecutedEntries = lastExecutedEntries;
   }

   public List<ExecutionLogEntryDto> getLastExecutedEntries() {
      return lastExecutedEntries;
   }

   public int getNrOfSuccessfulExecutions() {
      return nrOfSuccessfulExecutions;
   }

   public void setNrOfSuccessfulExecutions(int nrOfSuccessfulExecutions) {
      this.nrOfSuccessfulExecutions = nrOfSuccessfulExecutions;
   }

   public int getNrOfVetoedExecutions() {
      return nrOfVetoedExecutions;
   }

   public void setNrOfVetoedExecutions(int nrOfVetoedExecutions) {
      this.nrOfVetoedExecutions = nrOfVetoedExecutions;
   }

   public int getNrOfFailedExecutions() {
      return nrOfFailedExecutions;
   }

   public void setNrOfFailedExecutions(int nrOfFailedExecutions) {
      this.nrOfFailedExecutions = nrOfFailedExecutions;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public Long getUserId() {
      return userId;
   }

   public void setExecutionStatus(JobExecutionStatusDto executionStatus) {
      this.executionStatus = executionStatus;
   }

   public JobExecutionStatusDto getExecutionStatus() {
      return executionStatus;
   }

   public boolean isOwner(UserDto user) {
      if (null != userId && userId.equals(user.getId()))
         return true;

      if (null == scheduleDefinition)
         return false;

      for (StrippedDownUser owner : scheduleDefinition.getOwners())
         if (owner.getId().equals(user.getId()))
            return true;

      return false;
   }

}
