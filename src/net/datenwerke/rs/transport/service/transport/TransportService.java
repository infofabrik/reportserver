package net.datenwerke.rs.transport.service.transport;

import java.util.List;
import java.util.Map;

import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public interface TransportService {

   String createTransport(String description, TransportFolder parent);
   
   void setInitialProperties(Transport transport, Map<String,String> initialProperties, boolean setXml);
   
   Map<String,String> createInitialProperties();
   
   void addElement(Transport transport, AbstractNode<?> element, boolean includeVariants);
   
   boolean isTransportable(AbstractNode<?> node);
   
   Map<String, Object> getMetadata(Transport transport);
   
   Map<String, List<Map<String,String>>> getElements(Transport transport);
   
}
