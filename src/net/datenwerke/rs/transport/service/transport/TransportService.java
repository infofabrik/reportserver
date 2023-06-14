package net.datenwerke.rs.transport.service.transport;

import java.util.Map;

import net.datenwerke.rs.transport.service.transport.entities.Transport;

public interface TransportService {

   String createTransport(String description);
   
   void setInitialProperties(Transport transport, Map<String,String> initialProperties);
   
   Map<String,String> createInitialProperties();
}
