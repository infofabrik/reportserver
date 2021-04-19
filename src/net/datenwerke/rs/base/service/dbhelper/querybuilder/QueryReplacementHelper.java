package net.datenwerke.rs.base.service.dbhelper.querybuilder;

import java.util.Map;

import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValueImpl;

public class QueryReplacementHelper {

	private int nrRep = 0;
	private int nrAlias = 0;
	
	public String nextReplacement(Map<String, ParameterValue> replacementMap, Object data){
		String rep = "_xx_query_replacement_prefix_" + nrRep++; //$NON-NLS-1$
		if(null == data)
			replacementMap.put(rep, new ParameterValueImpl(rep, null, Object.class));
		else
			replacementMap.put(rep, new ParameterValueImpl(rep, data, data.getClass()));
		
		return "$P{" + rep + "}"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	public String nextAlias(){
		return "_xx_rs_" + nrAlias++; //$NON-NLS-1$
	}
}
