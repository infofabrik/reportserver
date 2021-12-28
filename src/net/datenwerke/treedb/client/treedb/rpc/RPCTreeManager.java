package net.datenwerke.treedb.client.treedb.rpc;

import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.NeedForcefulDeleteClientException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public interface RPCTreeManager {

   public AbstractNodeDto moveNodeInsert(AbstractNodeDto node, AbstractNodeDto reference, int index, Dto state)
         throws ServerCallFailedException;

   public AbstractNodeDto moveNodeAppend(AbstractNodeDto node, AbstractNodeDto reference, Dto state)
         throws ServerCallFailedException;

   public List<AbstractNodeDto> moveNodesAppend(List<AbstractNodeDto> node, AbstractNodeDto reference, Dto state)
         throws ServerCallFailedException;

   public AbstractNodeDto insertNode(AbstractNodeDto objectTypeToInsert, AbstractNodeDto node, Dto state)
         throws ServerCallFailedException;

   public AbstractNodeDto updateNode(AbstractNodeDto node, Dto state) throws ServerCallFailedException;

   public void deleteNode(AbstractNodeDto node, Dto state)
         throws ServerCallFailedException, NeedForcefulDeleteClientException;

   public void deleteNodeWithForce(AbstractNodeDto node, Dto state) throws ServerCallFailedException;

   public AbstractNodeDto loadFullViewNode(AbstractNodeDto node, Dto state) throws ServerCallFailedException;

   public AbstractNodeDto refreshNode(AbstractNodeDto node, Dto state) throws ServerCallFailedException;

   public AbstractNodeDto duplicateNode(AbstractNodeDto toDuplicate, Dto state) throws ServerCallFailedException;

   AbstractNodeDto setFlag(AbstractNodeDto node, long flagToSet, long flagToUnset, boolean updateNode, Dto state)
         throws ServerCallFailedException;
}
