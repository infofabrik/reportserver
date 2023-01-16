package net.datenwerke.gf.client.history;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gf.client.history.rpc.HistoryRpcServiceAsync;
import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.Dto;

public class HistoryDao extends Dao {

   private final HistoryRpcServiceAsync rpcService;

   @Inject
   public HistoryDao(HistoryRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void getLinksFor(Dto dto, AsyncCallback<List<HistoryLinkDto>> callback) {
      rpcService.getLinksFor(dto, transformAndKeepCallback(callback));
   }

   public void getFormattedObjectPaths(Dto dto, AsyncCallback<List<String>> callback) {
      rpcService.getFormattedObjectPaths(dto, transformAndKeepCallback(callback));
   }

}
