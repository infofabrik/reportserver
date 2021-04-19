package net.datenwerke.gxtdto.client.servercommunication.callback;

import net.datenwerke.gxtdto.client.servercommunication.callback.locale.CallbackHandlerMessages;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.util.DelayedTask;

public class TimeoutCallback <T> implements AsyncCallback<T>{

	private final NotamCallback<T> callback;
	private final DelayedTask  dt;
	
	public TimeoutCallback(NotamCallback<T> callback){
		this(15000, callback);
	}
	
	public TimeoutCallback(int timeout, NotamCallback<T> callback) {
		this.callback = callback;
		
		dt = new DelayedTask() {
			@Override
			public void onExecute() {
				onTimeout();
			}
		};
		
		dt.delay(timeout);
	}
	
	private void onTimeout(){
		if(null != callback.getRequest()){
			if(!callback.getRequest().isPending()){
				return;
			}
			callback.getRequest().cancel();
		}
		doOnTimeout();
		callback.onFailure(new ExpectedException(CallbackHandlerMessages.INSTANCE.timeoutMessage()));
	}
	
	public void doOnTimeout(){};
	
	public void onFailure(Throwable caught) {
		dt.cancel();
		callback.onFailure(caught);
		
	}
	
	public void onSuccess(T result) {
		dt.cancel();
		callback.onSuccess(result);
	};
}
