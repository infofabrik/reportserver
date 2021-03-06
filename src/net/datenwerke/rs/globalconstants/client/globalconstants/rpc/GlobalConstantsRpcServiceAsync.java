package net.datenwerke.rs.globalconstants.client.globalconstants.rpc;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto;

public interface GlobalConstantsRpcServiceAsync {

   void loadGlobalConstants(AsyncCallback<List<GlobalConstantDto>> callback);

   void addNewConstant(AsyncCallback<GlobalConstantDto> callback);

   void updateConstant(GlobalConstantDto constantDto, AsyncCallback<GlobalConstantDto> callback);

   void removeAllConstants(AsyncCallback<Void> callback);

   void removeConstants(Collection<GlobalConstantDto> constant, AsyncCallback<Void> callback);

}
