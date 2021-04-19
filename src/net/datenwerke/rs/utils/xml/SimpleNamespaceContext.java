package net.datenwerke.rs.utils.xml;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;

public class SimpleNamespaceContext implements NamespaceContext {

	private Map<String, String> namespaces;

	public SimpleNamespaceContext() {
		this.namespaces = new HashMap<String, String>();
	}
	
	public SimpleNamespaceContext(String prefix, String uri){
		this.namespaces = new HashMap<String, String>();
		this.namespaces.put(prefix, uri);
	}

	public SimpleNamespaceContext(Map<String, String> namespaces) {
		this.namespaces = new HashMap<String, String>(namespaces);
	}

	@Override
	public String getNamespaceURI(String prefix) {
		return namespaces.get(prefix);
	}

	@Override
	public String getPrefix(String namespaceURI) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator getPrefixes(String namespaceURI) {
		throw new UnsupportedOperationException();
	}
}