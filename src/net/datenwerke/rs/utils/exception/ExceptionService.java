package net.datenwerke.rs.utils.exception;

import com.google.inject.ImplementedBy;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.security.service.usermanager.entities.User;

@ImplementedBy(ExceptionServiceImpl.class)
public interface ExceptionService {

   String exceptionToString(Throwable e);

   String reportExecutionExceptionDetailsMessage(Throwable e, Report report, User user, String outputFormat, String uuid);

}