package net.datenwerke.rs.core.client.datasinkmanager.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;

public interface DatasinkRpcServiceAsync {

   void getDefaultFolder(DatasinkDefinitionDto datasinkDto, AsyncCallback<String> callback);
}
