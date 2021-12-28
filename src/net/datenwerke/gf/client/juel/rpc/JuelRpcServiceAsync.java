package net.datenwerke.gf.client.juel.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gf.client.juel.dto.JuelResultDto;

public interface JuelRpcServiceAsync {

   void evaluateExpression(String expression, AsyncCallback<JuelResultDto> callback);

}
