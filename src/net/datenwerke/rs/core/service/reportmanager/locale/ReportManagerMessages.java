package net.datenwerke.rs.core.service.reportmanager.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface ReportManagerMessages extends Messages {

   public final static ReportManagerMessages INSTANCE = LocalizationServiceImpl
         .getMessages(ReportManagerMessages.class);

   String parameterDefinitionDefaultDescription();

   String parameterDefinitionDefaultName();

   String reportManagerSecureeName();

   String rightCreateVariantAbbreviation();

   String rightCreateVariantDescription();

   String historyUrlBuilderName();

   String historyUrlBuilderIcon();

   String exceptionInvalidParameter();

   String exceptionReportCouldNotBeLoaded();

   String exceptionReportCouldNotBeExecuted(String localizedMessage);

   String exceptionWhileReportExecution();

   String folderTypeName();

   String exceptionCannotMoveVariantSinceMismatchedParams();

   String exceptionReportDoesNotExist();

}
