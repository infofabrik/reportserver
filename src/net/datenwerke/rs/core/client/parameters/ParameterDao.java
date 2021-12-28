package net.datenwerke.rs.core.client.parameters;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.rpc.ParameterRpcServiceAsync;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 *
 * 
 * 
 *
 */
public class ParameterDao extends Dao {

   private final ParameterRpcServiceAsync rpcService;

   @Inject
   public ParameterDao(ParameterRpcServiceAsync rpcService) {
      super();
      this.rpcService = rpcService;
   }

   public void addParameter(ReportDto report, ParameterDefinitionDto parameter, AbstractNodeDto correspondingNode,
         AsyncCallback<ReportDto> callback) {
      rpcService.addParameter(parameter, correspondingNode, transformDtoCallback(callback));
   }

   public void updateParameter(ReportDto report, ParameterDefinitionDto parameter, AsyncCallback<ReportDto> callback) {
      rpcService.updateParameter(parameter, transformDtoCallback(callback));
   }

   public void removeParameters(ReportDto report, Collection<ParameterDefinitionDto> parameters,
         AsyncCallback<ReportDto> callback) {
      rpcService.removeParameters(parameters, transformDtoCallback(callback));
   }

   public void movedParameter(ParameterDefinitionDto parameter, int to, AsyncCallback<ReportDto> callback) {
      rpcService.movedParameter(parameter, to, transformDtoCallback(callback));
   }

   public void updateParameterInstances(ReportDto report, Collection<ParameterDefinitionDto> parameters,
         AsyncCallback<ReportDto> callback) {
      rpcService.updateParameterInstances(parameters, transformDtoCallback(callback));
   }

   public void duplicateParameters(List<ParameterDefinitionDto> params, AbstractNodeDto correspondingNode,
         AsyncCallback<ReportDto> callback) {
      rpcService.duplicateParameters(params, correspondingNode, transformDtoCallback(callback));
   }
}
