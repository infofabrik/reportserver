package net.datenwerke.gf.client.juel.rpc;

import net.datenwerke.gf.client.juel.dto.JuelResultDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface JuelRpcServiceAsync {

	void evaluateExpression(String expression,
			AsyncCallback<JuelResultDto> callback);

}
