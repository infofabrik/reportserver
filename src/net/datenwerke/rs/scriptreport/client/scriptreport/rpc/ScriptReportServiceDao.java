package net.datenwerke.rs.scriptreport.client.scriptreport.rpc;

import java.util.Collection;

import javax.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto;

public class ScriptReportServiceDao extends Dao {

	private final ScriptReportRpcServiceAsync rpcService;
	
	@Inject
	public ScriptReportServiceDao(ScriptReportRpcServiceAsync scriptReportRpcServiceAsync) {
		this.rpcService =  scriptReportRpcServiceAsync;
	}
	
	
	public void getScriptParameterContents(ScriptParameterDefinitionDto definition, Collection<ParameterInstanceDto> relevantInstances, RsAsyncCallback<String> callback) {
		rpcService.getScriptParameterContents(definition, unproxy(relevantInstances), callback);
	}
}
