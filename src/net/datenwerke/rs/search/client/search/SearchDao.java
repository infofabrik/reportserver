package net.datenwerke.rs.search.client.search;

import java.util.ArrayList;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.search.client.search.dto.SearchFilterDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultEntryDto;
import net.datenwerke.rs.search.client.search.dto.SearchResultListDto;
import net.datenwerke.rs.search.client.search.dto.decorator.SearchResultListDtoDec;
import net.datenwerke.rs.search.client.search.rpc.SearchRpcServiceAsync;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

public class SearchDao extends Dao {

   private final SearchRpcServiceAsync rpcService;

   private static SearchResultListDto emptyResult;
   static {
      emptyResult = new SearchResultListDtoDec(new ArrayList<SearchResultEntryDto>());
   }

   @Inject
   public SearchDao(SearchRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void find(Dto2PosoMapper type, String searchStr, AsyncCallback<SearchResultListDto> callback) {
      if (null == searchStr || "".equals(searchStr.trim()))
         callback.onSuccess(emptyResult);
      else
         rpcService.find(type, searchStr, transformAndKeepCallback(callback));
   }

   public void find(String searchStr, AsyncCallback<SearchResultListDto> callback) {
      if (null == searchStr || "".equals(searchStr.trim()))
         callback.onSuccess(emptyResult);
      else
         rpcService.find(searchStr, transformAndKeepCallback(callback));
   }

   public void find(String searchStr, SearchFilterDto filter, AsyncCallback<SearchResultListDto> callback) {
      if (null == searchStr || "".equals(searchStr.trim()))
         callback.onSuccess(emptyResult);
      else
         rpcService.find(searchStr, filter, transformAndKeepCallback(callback));
   }

}
