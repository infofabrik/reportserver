package net.datenwerke.rs.core.client.reportproperties;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto;
import net.datenwerke.rs.core.client.reportproperties.rpc.ReportPropertiesRpcServiceAsync;

public class ReportPropertiesDao extends Dao {

   private final ReportPropertiesRpcServiceAsync rpcService;

   @Inject
   public ReportPropertiesDao(ReportPropertiesRpcServiceAsync rpcService) {

      /* store objects */
      this.rpcService = rpcService;
   }

   public void getPropertyKeys(ReportDto reportDto, AsyncCallback<List<String>> callback) {
      rpcService.getPropertyKeys(reportDto, transformAndKeepCallback(callback));
   }

   public void getSupportedPropertyKeys(ReportDto reportDto, AsyncCallback<List<String>> callback) {
      rpcService.getSupportedPropertyKeys(reportDto, transformAndKeepCallback(callback));
   }

   public void updateProperties(ReportDto report, List<ReportStringPropertyDto> addedProperties,
         List<ReportStringPropertyDto> modifiedProperties, List<ReportStringPropertyDto> removedProperties,
         AsyncCallback<ReportDto> callback) {
      rpcService.updateProperties(report, addedProperties, modifiedProperties, removedProperties,
            transformAndKeepCallback(callback));
   }

   public void getInheritedProperties(ReportDto report, AsyncCallback<List<ReportStringPropertyDto>> callback) {
      rpcService.getInheritedProperties(report, transformAndKeepCallback(callback));
   }
}
