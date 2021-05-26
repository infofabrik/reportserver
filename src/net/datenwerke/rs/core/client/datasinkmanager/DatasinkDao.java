package net.datenwerke.rs.core.client.datasinkmanager;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;

public interface DatasinkDao {

   void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback);

}
