package net.datenwerke.rs.transport.service.transport.locale;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.localization.Messages;

public interface TransportManagerMessages extends Messages {
   
   public final static TransportManagerMessages INSTANCE = LocalizationServiceImpl
         .getMessages(TransportManagerMessages.class);

   String commandTransportCreate_desc();
   
   String commandTransportCreate_target();
   
   String commandTransportCreate_description();
   
   String transportDesc();
   
   String transports();
   
   String transportUsageStatistics();
}