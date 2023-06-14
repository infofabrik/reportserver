package net.datenwerke.rs.transport.client.transport;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.transport.client.transport.dto.AbstractTransportManagerNodeDto;
import net.datenwerke.rs.transport.client.transport.rpc.TransportTreeManagerAsync;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;

public class TransportTreeManagerDao extends TreeDbManagerDao {

   @Inject
   public TransportTreeManagerDao(TransportTreeManagerAsync treeManager) {
      super(treeManager);
   }

   @Override
   public Dto2PosoMapper getBaseNodeMapper() {
      return AbstractTransportManagerNodeDto.newPosoMapper();
   }

}
