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
   
   String commandTransportImport_desc();

   String commandTransportClose_desc();
   
   String commandTransportAdd_target();
   
   String commandTransportImport_target();
   
   String commandTransportAdd_element();

   String commandTransportClose();
   
   String commandTransportAdd_sub_flagV();
   
   String commandTransportDescribe_desc();
   
   String commandTransportDescribe_transport();
   
   String transportFolderTypeName();
   
   String commandTransportRpull_desc();
   
   String commandTransportRpull_remoteServer();
   
   String commandTransportRpull_target();
   
   String commandTransportRemove_desc();
   
   String commandTransportRemove_transport();
   
   String commandTransportRemove_type();
   
   String commandTransportRemove_key();
   
   String commandTransportApply_desc();
   
   String commandTransportApply_target();
   
   String commandTransportApply_flagC();
}