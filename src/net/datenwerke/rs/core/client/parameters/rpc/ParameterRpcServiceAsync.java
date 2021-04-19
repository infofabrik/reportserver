package net.datenwerke.rs.core.client.parameters.rpc;

import java.util.Collection;
import java.util.List;

import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ParameterRpcServiceAsync {

	void addParameter(ParameterDefinitionDto parameter, AbstractNodeDto correspondingNode,
			AsyncCallback<ReportDto> callback);

	void updateParameter(ParameterDefinitionDto parameter,
			AsyncCallback<ReportDto> callback);

	void removeParameters(Collection<ParameterDefinitionDto> parameters,
			AsyncCallback<ReportDto> callback);

	void movedParameter(ParameterDefinitionDto parameter, int to,
			AsyncCallback<ReportDto> callback);

	void updateParameterInstances(
			Collection<ParameterDefinitionDto> parameters,
			AsyncCallback<ReportDto> callback);

	void duplicateParameters(List<ParameterDefinitionDto> params,
			AbstractNodeDto correspondingNode, AsyncCallback<ReportDto> callback);

}
