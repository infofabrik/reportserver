package net.datenwerke.rs.legacysaiku.client.saiku;

import javax.inject.Inject;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
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

   public void loadCubesFor(MondrianDatasourceDto datasourceDefinitionDto, SaikuReportDto saikuReportDto,
         AsyncCallback<ListLoadResult<String>> callback) {
      rpcService.loadCubesFor(datasourceDefinitionDto, saikuReportDto, transformAndKeepCallback(callback));
   }

}
