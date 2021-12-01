package net.datenwerke.rs.scriptreport.client.scriptreport.rpc;

import java.util.Collection;

import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ScriptReportRpcServiceAsync {

	void getScriptParameterContents(ScriptParameterDefinitionDto definition, Collection<ParameterInstanceDto> relevantInstances, AsyncCallback<String> callback);

}
