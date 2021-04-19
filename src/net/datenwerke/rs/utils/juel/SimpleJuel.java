package net.datenwerke.rs.utils.juel;

import java.util.HashMap;
import java.util.Map;

import com.google.inject.Inject;

/**
 * A simple template parser.
 * 
 *
 */
public class SimpleJuel {

	private final JuelService juelService;
	private final Map<String, VariableAssignment> replacementMap;
	
	@Inject
	public SimpleJuel(
		JuelService juelService
		){
		
		this.juelService = juelService;
		replacementMap = new HashMap<String, VariableAssignment>();
	}
	
	public String parse(String template){
		Object result = parseAsObject(template);
		return null == result ? null : result.toString();
	}
	
	public Object parseAsObject(String template){
		if(null == template)
			return null;
		
	    return juelService.evaluate(replacementMap, template);
	}
	
	public void addReplacement(String key, Object value) {
		replacementMap.put(key, new VariableAssignment(value));
	}
	
	public void addReplacement(String key, Object value, Class<?> type) {
		replacementMap.put(key, new VariableAssignment(value, type));
	}
	
}
