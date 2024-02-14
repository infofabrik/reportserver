package net.datenwerke.rs.transport.service.transport;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.RemoteRsRestServer;
import net.datenwerke.rs.transport.client.transport.dto.TransportElementDto;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public interface TransportService {
    
   public static final String CONFIG_FILE = "main/transport.cf";
   public static final String APPLY_CONFIG_USER = "apply.user";
   
   public static enum Status {
      CREATED, CLOSED, IMPORTED, APPLIED, ERROR, INVALID
   }
   
   String createTransport(String description, TransportFolder parent);
   
   void setInitialProperties(Transport transport, Map<String,String> initialProperties, boolean setXml);
   
   Map<String,String> createInitialProperties();
   
   void addElement(Transport transport, AbstractNode<?> element, boolean includeVariants);
   
   void removeElements(Transport transport, List<TransportElementDto> toRemove);
   
   Optional<ImmutablePair<ImportResult, Exception>> applyTransport(Transport transport);
   
   boolean isTransportable(AbstractNode<?> node);
   
   Map<String, Object> getMetadata(Transport transport);
   
   Map<String, List<Map<String,String>>> getElements(Transport transport);

   void close(Transport transport);
   

   /**
    * This modifies an xml String so that
    * whenever there is a an 'itemProperty' element 
    * that has an attribute 'referenceId' we look up 
    * the corresponding object and add the object's
    * key in the new attribute 'referenceKey'. The
    * new attribute is added to the 'itemproperty'
    * element
    * @param transportXml the xml to be modified
    * @return transportXml with additional referenceKey attributes
    */
   String addReferenceKeys(String transportXml);

   ImportResult rpull(RemoteRsRestServer remoteServer, String destinationPath);
   
   ImportResult rpull();
   
   Map<String, PreconditionResult> analyzeApplyPreconditions(Transport transport);
   
   boolean isAppliable(Map<String, PreconditionResult> analysisResults);
   
   boolean isAppliable(Transport transport);
   
   List<String> getRemoteReferenceKeys(String transportXml);
   
}
