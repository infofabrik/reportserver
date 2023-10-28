package net.datenwerke.rs.scheduler.client.scheduler.schedulereportlist.dto;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public interface ReportScheduleJobListInformationPA extends PropertyAccess<ReportScheduleJobListInformation> {

   @Path("jobId")
   public ModelKeyProvider<ReportScheduleJobListInformation> dtoId();

   public ValueProvider<ReportScheduleJobListInformation, Long> reportId();

   public ValueProvider<ReportScheduleJobListInformation, String> reportName();

   public ValueProvider<ReportScheduleJobListInformation, String> reportDescription();

   public ValueProvider<ReportScheduleJobListInformation, Date> lastScheduled();

   public ValueProvider<ReportScheduleJobListInformation, Date> nextScheduled();

   public ValueProvider<ReportScheduleJobListInformation, Long> jobId();

   public ValueProvider<ReportScheduleJobListInformation, String> jobTitle();

   public ValueProvider<ReportScheduleJobListInformation, String> jobDescription();

   public ValueProvider<ReportScheduleJobListInformation, Boolean> reportDeleted();

   public ValueProvider<ReportScheduleJobListInformation, Boolean> executorDeleted();
   
   public ValueProvider<ReportScheduleJobListInformation, UserDto> executor();
   
   public ValueProvider<ReportScheduleJobListInformation, StrippedDownUser> scheduledBy();
}
