package net.datenwerke.gf.client.history.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto;

public interface HistoryRpcServiceAsync {

   void getLinksFor(Dto dto, AsyncCallback<List<HistoryLinkDto>> callback);

   void getFormattedObjectPaths(Dto dto, AsyncCallback<List<String>> callback);

   void getRealObjectPaths(Dto dto, AsyncCallback<List<String>> callback);

}
