package net.datenwerke.rs.core.client.parameters.rpc;

import java.util.Collection;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

@RemoteServiceRelativePath("reportmanager_parameter")
public interface ParameterRpcService extends RemoteService {

	public ReportDto addParameter(ParameterDefinitionDto parameter, AbstractNodeDto correspondingNode) throws ServerCallFailedException;
	
	public ReportDto updateParameter(ParameterDefinitionDto parameter) throws ServerCallFailedException, ExpectedException;
	
	public ReportDto removeParameters(Collection<ParameterDefinitionDto> parameters) throws ServerCallFailedException;
	
	public ReportDto updateParameterInstances(Collection<ParameterDefinitionDto> parameters) throws ServerCallFailedException;
	
	public ReportDto movedParameter(ParameterDefinitionDto parameter, int to) throws ServerCallFailedException;
	
	public ReportDto duplicateParameters(List<ParameterDefinitionDto> params, AbstractNodeDto correspondingNode) throws ServerCallFailedException, ExpectedException;
}
