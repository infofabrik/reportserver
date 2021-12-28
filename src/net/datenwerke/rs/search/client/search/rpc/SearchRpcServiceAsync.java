package net.datenwerke.rs.search.client.search.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.search.client.search.dto.SearchFilterDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;


public interface SearchRpcServiceAsync  {

	void find(Dto2PosoMapper type, String searchStr,AsyncCallback<SearchResultListDto> callback);

	void find(String searchStr, AsyncCallback<SearchResultListDto> callback);

	void find(
			String searchStr, SearchFilterDto filter,
			AsyncCallback<SearchResultListDto> transformAndKeepCallback);

}
