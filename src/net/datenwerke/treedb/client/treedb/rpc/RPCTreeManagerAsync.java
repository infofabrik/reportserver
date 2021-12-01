package net.datenwerke.treedb.client.treedb.rpc;


import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public interface RPCTreeManagerAsync {

	public void deleteNode(AbstractNodeDto node, Dto state, AsyncCallback<Void> callback);
	
	public void deleteNodeWithForce(AbstractNodeDto node, Dto state, AsyncCallback<Void> callback);
	
	public void insertNode(AbstractNodeDto dummyNode, AbstractNodeDto node, Dto state, AsyncCallback<AbstractNodeDto> callback);
	
	public void updateNode(AbstractNodeDto node, Dto state, AsyncCallback<AbstractNodeDto> callback);
	
	public void moveNodeInsert(AbstractNodeDto node, AbstractNodeDto reference, int position, Dto state, AsyncCallback<AbstractNodeDto> asyncCallback);
	
	public void moveNodeAppend(AbstractNodeDto node, AbstractNodeDto reference, Dto state, AsyncCallback<AbstractNodeDto> asyncCallback);
	
	public void moveNodesAppend(List<AbstractNodeDto> nodes,
			AbstractNodeDto reference, Dto state,
			AsyncCallback<List<AbstractNodeDto>> transformDtoCallback);
	
	public void loadFullViewNode(AbstractNodeDto node, Dto state, AsyncCallback<AbstractNodeDto> callback);
	
	public void refreshNode(AbstractNodeDto node, Dto state, AsyncCallback<AbstractNodeDto> callback);

	public void duplicateNode(AbstractNodeDto toDuplicate, Dto state, AsyncCallback<AbstractNodeDto> callback);
	
	public void setFlag(AbstractNodeDto node, long flagToSet, long flagToUnset, boolean updateNode, Dto state, AsyncCallback<AbstractNodeDto> callback);
	
}
