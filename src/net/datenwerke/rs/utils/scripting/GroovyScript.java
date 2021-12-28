package net.datenwerke.rs.utils.scripting;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import groovy.lang.GroovyObject;


public class GroovyScript {

	private Class<?> script;
	
	private Map<String, Object> binding = new HashMap<String, Object>();
	
	public GroovyScript(Class<?> script) {
		this.script = script;
	}
	
	Class<?> getScript() {
		return script;
	}
	
	public void newBinding(){
		binding = new HashMap<String, Object>();
	}
	
	public void setVariable(String key, Object value){
		binding.put(key, value);
	}
	
	public Map<String, Object> getBinding() {
		return binding;
	}

	public Object run() {
		try {
			GroovyObject groovy = (GroovyObject) script.newInstance();
			for(Entry<String, Object> e : binding.entrySet())
				groovy.setProperty(e.getKey(), e.getValue());
			return groovy.invokeMethod("run", null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
