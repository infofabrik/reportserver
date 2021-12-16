package net.datenwerke.rs.box.client.box;

import java.util.List;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.datasinkmanager.HasDefaultDatasink;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto;
import net.datenwerke.rs.box.client.box.rpc.BoxRpcServiceAsync;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;

public class BoxDao extends Dao implements HasDefaultDatasink {

   private final BoxRpcServiceAsync rpcService;

   @Inject
   public BoxDao(BoxRpcServiceAsync rpcService) {
      this.rpcService = rpcService;
   }

   public void exportIntoBox(ReportDto reportDto, String executorToken, BoxDatasinkDto boxDatasinkDto, String format,
         List<ReportExecutionConfigDto> configs, String name, String folder, boolean compressed, AsyncCallback<Void> callback) {
      rpcService.exportIntoBox(reportDto, executorToken, boxDatasinkDto, format, configs, name, folder, compressed,
            transformAndKeepCallback(callback));
   }
   
   public void exportFileIntoDatasink(AbstractFileServerNodeDto  file, DatasinkDefinitionDto datasinkDto, String name,
         String folder,  boolean compressed,AsyncCallback<Void> callback) {
      rpcService.exportFileIntoDatasink(file, datasinkDto, name, folder, compressed,transformAndKeepCallback(callback));
   }

   public void getStorageEnabledConfigs(AsyncCallback<Map<StorageType, Boolean>> callback) {
      rpcService.getStorageEnabledConfigs(transformAndKeepCallback(callback));
   }

   public Request testBoxDatasink(BoxDatasinkDto boxDatasinkDto, AsyncCallback<Boolean> callback) {
      return rpcService.testBoxDatasink(boxDatasinkDto, transformAndKeepCallback(callback));
   }

   @Override
   public void getDefaultDatasink(AsyncCallback<DatasinkDefinitionDto> callback) {
      rpcService.getDefaultDatasink(transformAndKeepCallback(callback));
   }

}
