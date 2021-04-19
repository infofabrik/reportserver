package net.datenwerke.rs.search.client.search.rpc;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.search.client.search.dto.SearchFilterDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("search")
public interface SearchRpcService extends RemoteService{
	
	public SearchResultListDto find(String searchStr) throws ServerCallFailedException;
	
	public SearchResultListDto find(Dto2PosoMapper type, String searchStr) throws ServerCallFailedException;

	SearchResultListDto find(String searchStr, SearchFilterDto filter) throws ServerCallFailedException;

}
