package net.datenwerke.rs.incubator.client.reportmetadata;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto;
import net.datenwerke.rs.incubator.client.reportmetadata.rpc.ReportMetadataRpcServiceAsync;

public class ReportMetadataDao extends Dao {

   private final ReportMetadataRpcServiceAsync rpcService;

   @Inject
   public ReportMetadataDao(ReportMetadataRpcServiceAsync rpcService) {

      /* store objects */
      this.rpcService = rpcService;
   }

   public void getPropertyKeys(AsyncCallback<List<String>> callback) {
      rpcService.getMetadataKeys(transformAndKeepCallback(callback));
   }

   public void updateProperties(ReportDto report, List<ReportMetadataDto> addedProperties,
         List<ReportMetadataDto> modifiedProperties, List<ReportMetadataDto> removedProperties,
         AsyncCallback<ReportDto> callback) {
      rpcService.updateMetadata(report, addedProperties, modifiedProperties, removedProperties,
            transformDtoCallback(callback));
   }
}
