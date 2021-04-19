package net.datenwerke.gf.client.history.rpc;

import java.util.List;

import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto;

import com.google.gwt.user.client.rpc.AsyncCallback;



public interface HistoryRpcServiceAsync {

	void getLinksFor(Dto dto, AsyncCallback<List<HistoryLinkDto>> callback);


}
