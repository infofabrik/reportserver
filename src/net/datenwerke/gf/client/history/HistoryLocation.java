package net.datenwerke.gf.client.history;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HistoryLocation implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4157763331057600063L;
	
	private String location;
	private Map<String, String> parameters;
	
	public final static String SEP_LOC_PARAM = "/";
	public final static String SEP_PARAM_KEY_VALUE = ":";
	public final static String SEP_PARAMS = "&";
	
	
	public HistoryLocation() {
		//default constructor
	}
	
	public HistoryLocation(String location, Map.Entry<String, String>... parameters) {
		this.location = location;
		this.parameters = new HashMap<String, String>();
		
		for(Map.Entry<String, String> param : parameters)
			this.parameters.put(param.getKey(), param.getValue());
	}
	
	public void addParameter(String key, String value){
		parameters.put(key, value);
	}
	
	public static Map.Entry<String, String> makeParameter(final String pKey, final String pValue){
		return new Map.Entry<String, String>() {
			private String value = pValue;
			private final String key = pKey; 
			@Override
			public String setValue(String value) {
				String oldval = this.value;
				this.value = value;
				return oldval;
			}
			
			@Override
			public String getValue() {
				return value;
			}
			
			@Override
			public String getKey() {
				return key;
			}
		};
	}
	
	
	public String asString(){
		StringBuffer stb = new StringBuffer();
		stb.append(location);
		
		if(parameters.size() > 0){
			stb.append(SEP_LOC_PARAM);
		}
		
			
		Iterator<Entry<String, String>> it = parameters.entrySet().iterator();
		while(it.hasNext()){
			Entry<String, String> param = it.next();
			stb.append(param.getKey());
			stb.append(SEP_PARAM_KEY_VALUE);
			stb.append(param.getValue());
			
			if(it.hasNext())
				stb.append(SEP_PARAMS);
		}
		
		return stb.toString();
	}
	
	public static HistoryLocation fromString(String stringLocation){
		if(! stringLocation.contains(SEP_LOC_PARAM)){
			return new HistoryLocation(stringLocation);
		}else{
			String[] split = stringLocation.split(SEP_LOC_PARAM);

			if(split.length != 2)
				return new HistoryLocation(stringLocation);
			
			String location = split[0];
			String[] params = split[1].split(SEP_PARAMS);
			
			ArrayList<Entry<String, String>> entries = new ArrayList<Map.Entry<String,String>>();
			
			for(String param : params){
				String[] keyval = param.split(SEP_PARAM_KEY_VALUE);
				if(keyval.length == 2)
					entries.add(makeParameter(keyval[0], keyval[1]));
			}
			
			return new HistoryLocation(location, entries.toArray(new Entry[0]));
		}
	}
	
	
	public String getLocation() {
		return location;
	}
	
	
	@Override
	public String toString() {
		return asString();
	}

	public boolean hasParameter(String parameterName){
		return parameters.containsKey(parameterName);
		
	}
	
	public String getParameterValue(String parameterName){
		return parameters.get(parameterName);
	}

	public Collection<String> getParameterNames() {
		return parameters.keySet();
	}
}
