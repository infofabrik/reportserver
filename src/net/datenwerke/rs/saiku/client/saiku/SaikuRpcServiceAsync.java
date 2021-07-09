package net.datenwerke.rs.saiku.client.saiku;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.ListLoadResult;

import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.saiku.dto.SaikuReportDto;

public interface SaikuRpcServiceAsync {

   public Request stashReport(String token, SaikuReportDto report, AsyncCallback<Void> callback);

   public void loadCubesFor(MondrianDatasourceDto datasourceDefinitionDto, SaikuReportDto saikuReportDto,
         AsyncCallback<ListLoadResult<String>> transformAndKeepCallback);

   public void clearCache(MondrianDatasourceDto datasourceDefinitionDto, AsyncCallback<Void> callback);

}
