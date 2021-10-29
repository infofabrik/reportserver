package net.datenwerke.rs.base.service.parameters.helpers;

import java.util.ArrayList;
import java.util.Iterator;

import net.datenwerke.rs.utils.misc.StringEscapeUtils;

public class ParameterValueArrayList<E> extends ArrayList<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5028584023075590226L;

	@Override
	public String toString() {
		return toString("\'");
	}
	
	
	public String toString(String quote) {
		if(isEmpty())
			return "";
		
		StringBuilder builder = new StringBuilder();
		
		Iterator<E> it = iterator();
		
		E first = it.next();
		if(first instanceof String)
			builder.append(quote).append(StringEscapeUtils.escapeSql(first.toString())).append(quote);
		else
			builder.append(first.toString());
		
		while(it.hasNext()){
			E val = it.next();
			
			builder.append(", ");
			if(val instanceof String)
				builder.append(quote).append(StringEscapeUtils.escapeSql(val.toString())).append(quote);
			else
				builder.append(StringEscapeUtils.escapeSql(val.toString()));
		}
		
		return builder.toString();
	}
	
}
