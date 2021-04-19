package net.datenwerke.gf.client.history.rpc;

import java.util.List;

import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("history")
public interface HistoryRpcService extends RemoteService {

	public List<HistoryLinkDto> getLinksFor(Dto dto);
}
