package net.datenwerke.rs.saiku.client.saiku;

import javax.inject.Inject;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.servercommunication.callback.TimeoutCallback;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;

public class SaikuDao extends Dao {

   private final SaikuRpcServiceAsync rpcService;

   @Inject
   public SaikuDao(SaikuRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public Request stashReport(String token, SaikuReportDto report, AsyncCallback<Void> callback) {
      return rpcService.stashReport(token, report, transformAndKeepCallback(callback));
   }

   public void loadCubesFor(MondrianDatasourceDto datasourceDefinitionDto, final SaikuReportDto saikuReportDto,
         AsyncCallback<ListLoadResult<String>> callback) {
      rpcService.loadCubesFor(datasourceDefinitionDto, saikuReportDto, transformAndKeepCallback(callback));
   }

   public void clearCache(MondrianDatasourceDto datasourceDefinitionDto, AsyncCallback<Void> callback) {
      rpcService.clearCache(datasourceDefinitionDto, callback);
   }

   public Request testConnection(MondrianDatasourceDto datasourceDto, TimeoutCallback<Boolean> callback) {
      return rpcService.testConnection(datasourceDto, transformAndKeepCallback(callback));
   }

}
