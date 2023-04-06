package net.datenwerke.rs.remoteserver.client.remoteservermanager;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.rpc.RemoteServerTreeManagerAsync;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;

public class RemoteServerTreeManagerDao extends TreeDbManagerDao {

   @Inject
   public RemoteServerTreeManagerDao(RemoteServerTreeManagerAsync treeManager) {
      super(treeManager);
   }

   @Override
   public Dto2PosoMapper getBaseNodeMapper() {
      return AbstractRemoteServerManagerNodeDto.newPosoMapper();
   }

}
