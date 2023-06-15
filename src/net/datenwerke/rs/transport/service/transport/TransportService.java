package net.datenwerke.rs.transport.service.transport;

import java.util.Map;

import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder;

public interface TransportService {

   String createTransport(String description, TransportFolder parent);
   
   void setInitialProperties(Transport transport, Map<String,String> initialProperties);
   
   Map<String,String> createInitialProperties();
}
