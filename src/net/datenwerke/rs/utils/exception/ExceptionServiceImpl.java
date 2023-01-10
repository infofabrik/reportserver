package net.datenwerke.rs.utils.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.lang3.exception.ExceptionUtils;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.security.service.usermanager.entities.User;

public class ExceptionServiceImpl implements ExceptionService {

   /*
    * (non-Javadoc)
    * 
    * @see
    * net.datenwerke.rs.services.utils.ExceptionService#exceptionToString(java.
    * lang.Throwable)
    */
   public String exceptionToString(Throwable e) {
      Writer result = new StringWriter();
      PrintWriter printWriter = new PrintWriter(result);
      e.printStackTrace(printWriter);
      return result.toString();
   }

   @Override
   public String reportExecutionExceptionDetailsMessage(Throwable e, Report report, User user, String outputFormat, String uuid) {
      StringBuilder sb = new StringBuilder();
      sb.append("Report could not be executed.")
         .append(" error: '")
         .append(ExceptionUtils.getRootCauseMessage(e))
         .append("', report_id: '")
         .append(null == report.getId() ? report.getOldTransientId() : report.getId())
         .append("', report_name: '")
         .append(report.getName());
      if (report instanceof ReportVariant) {
         Report baseReport = ((ReportVariant)report).getBaseReport();
         sb.append("', base_report_id: '")
            .append(null == baseReport.getId() ? baseReport.getOldTransientId() : baseReport.getId())
            .append("', base_report_name: '")
            .append(baseReport.getName());
      }
      sb.append("', executing_user: '")
         .append(user.getId())
         .append("', output_format: '")
         .append(outputFormat)
         .append("', uuid: '")
         .append(uuid)
         .append("'");
      return sb.toString();
   }
}
