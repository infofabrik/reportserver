package net.datenwerke.treedb.client.treedb;

import java.util.List;

import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.dtomanager.callback.DaoAsyncCallback;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.NeedForcefulDeleteClientException;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManagerAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;

abstract public class TreeDbManagerDao<M extends RPCTreeManagerAsync> extends Dao {

	protected final M treeManager;

	protected Dto state;
	
	public TreeDbManagerDao(M treeManager) {
		this.treeManager = treeManager;
	}
	
	public void deleteNodeAndAskForMoreForce(final AbstractNodeDto node, final AsyncCallback<Boolean> callback){
		final DaoAsyncCallback<Boolean> daoCallback = transformAndKeepCallback(callback);
		daoCallback.ignoreExpectedExceptions(true);
		treeManager.deleteNode(node, state, new RsAsyncCallback<Void>(){
			@Override
			public void onSuccess(Void result) {
				daoCallback.addDtoToDetach(node);
				callback.onSuccess(true);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				if(caught instanceof NeedForcefulDeleteClientException){
					ConfirmMessageBox cmb = new DwConfirmMessageBox(BaseMessages.INSTANCE.needForcefulDeleteTitle(), BaseMessages.INSTANCE.needForcefulDeleteMsg(caught.getMessage()));
					cmb.addDialogHideHandler(new DialogHideHandler() {
						@Override
						public void onDialogHide(DialogHideEvent event) {
							if (event.getHideButton() == PredefinedButton.YES) {
								deleteNodeWithForce(node, new AsyncCallback<Void>() {
									@Override
									public void onFailure(Throwable caught) {
										callback.onFailure(caught);
									}

									@Override
									public void onSuccess(Void result) {
										daoCallback.addDtoToDetach(node);
										callback.onSuccess(true);
									}
								});
							} else
								daoCallback.onSuccess(false);		
						}
					});
					
					cmb.show();
				} else  {
					daoCallback.ignoreExpectedExceptions(false);
					daoCallback.onFailure(caught);
				}
			}
		});
	}
	
	public void deleteNode(AbstractNodeDto node, AsyncCallback<Void> callback){
		DaoAsyncCallback<Void> daoCallback = transformAndKeepCallback(callback);
		daoCallback.addDtoToDetach(node);
		treeManager.deleteNode(node, state, daoCallback);
	}
	
	public void deleteNodeWithForce(AbstractNodeDto node, AsyncCallback<Void> callback){
		DaoAsyncCallback<Void> daoCallback = transformAndKeepCallback(callback);
		daoCallback.addDtoToDetach(node);
		treeManager.deleteNodeWithForce(node, state, daoCallback);
	}
	
	public void insertNode(AbstractNodeDto dummyNode, AbstractNodeDto node, AsyncCallback<AbstractNodeDto> callback){
		treeManager.insertNode(dummyNode, node, state, transformDtoCallback(callback));
	}
	
	public void updateNode(AbstractNodeDto node, AsyncCallback<AbstractNodeDto> callback){
		treeManager.updateNode(node, state, transformDtoCallback(callback));
		
		node.clearModified();
	}
	
	public void loadFullViewNode(AbstractNodeDto node, AsyncCallback<AbstractNodeDto> callback) {
		treeManager.loadFullViewNode(node, state, transformAndKeepCallback(callback));
	}
	
	public void moveNodeInsert(AbstractNodeDto node, AbstractNodeDto reference, int position,AsyncCallback<AbstractNodeDto> callback){
		treeManager.moveNodeInsert(node, reference, position, state, transformDtoCallback(callback));
	}
	
	public void moveNodeAppend(AbstractNodeDto node, AbstractNodeDto reference, AsyncCallback<AbstractNodeDto> callback){
		treeManager.moveNodeAppend(node, reference, state, transformDtoCallback(callback));
	}
	
	public void moveNodesAppend(List<AbstractNodeDto> nodes, AbstractNodeDto reference, AsyncCallback<List<AbstractNodeDto>> callback){
		treeManager.moveNodesAppend(nodes, reference, state, transformListCallback(callback));
	}
	
	public void refreshNode(AbstractNodeDto node, AsyncCallback<AbstractNodeDto> callback){
		treeManager.refreshNode(node, state, transformDtoCallback(callback));
		
		node.clearModified();
	}
	
	public void duplicateNode(AbstractNodeDto toDuplicate, AsyncCallback<AbstractNodeDto> callback){
		treeManager.duplicateNode(toDuplicate, state, transformDtoCallback(callback));
	}

	public void setFlag(AbstractNodeDto node, long flagToSet, long flagToUnset, boolean updateNode, AsyncCallback<AbstractNodeDto> callback){
		treeManager.setFlag(node, flagToSet, flagToUnset, updateNode, state, transformDtoCallback(callback));
	}
	
	public Dto getState() {
		return state;
	}

	public abstract Dto2PosoMapper getBaseNodeMapper();
	
	public void setState(Dto state) {
		this.state = state;
	}
	
	

}
