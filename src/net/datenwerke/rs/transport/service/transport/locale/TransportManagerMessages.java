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
   
   String transportTypeName();

   String historyUrlBuilderName();

   String historyUrlBuilderIcon();
   
   String commandTransportAdd_desc();
   
   String commandTransportAdd_target();
   
   String commandTransportAdd_element();
   
   String commandTransportAdd_sub_flagV();
   
   String commandTransportDescribe_desc();
   
   String commandTransportDescribe_transport();
}