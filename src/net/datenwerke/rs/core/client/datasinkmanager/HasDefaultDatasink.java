package net.datenwerke.rs.core.client.datasinkmanager;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;

public interface HasDefaultDatasink {

   void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback);

}
