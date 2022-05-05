package net.datenwerke.rs.printer.service.printer.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface PrinterDatasinkMessages extends Messages {

   public final static PrinterDatasinkMessages INSTANCE = LocalizationServiceImpl
         .getMessages(PrinterDatasinkMessages.class);

   String printerDatasinkTypeName();
}
