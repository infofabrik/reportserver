package net.datenwerke.rs.transport.service.transport;

import java.util.List;

import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface TransportTreeService extends TreeDBManager<AbstractTransportManagerNode> {

   public static final String MODULE_NAME = "TransportManager"; //$NON-NLS-1$

   public static final String SECUREE_ID = "TransportManagerService_Default"; //$NON-NLS-1$

   long getTransportIdFromKey(String key);
   
   Transport getTransportByKey(String key);
   
   List<Transport> getTransportsByStatus(String status);

}