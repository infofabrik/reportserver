package net.datenwerke.eximport.obj;

import java.util.Collection;
import java.util.HashSet;

import net.datenwerke.eximport.im.objectimporters.ImportableElement;
import nu.xom.Element;


/**
 * 
 *
 */
public class ExportedItem implements ImportableElement {

	private final String id;
	private final Class<?> type;
	private final Collection<ItemProperty> properties;
	private final Class<?> exporterType;
	private Element element;

	public ExportedItem(String id, Class<?> type, Collection<ItemProperty> properties, Class<?> exporterType, Element el) {
		super();
		this.id = id;
		this.type = type;
		this.properties = properties;
		this.exporterType = exporterType;
		this.element = el;
	}

	public String getId() {
		return id;
	}

	public Class<?> getType() {
		return type;
	}

	public Collection<ItemProperty> getProperties() {
		return properties;
	}

	public Class<?> getExporterType() {
		return exporterType;
	}

	public Collection<String> getReferencedIDs() {
		Collection<String> referencedIds = new HashSet<String>();
		
		for(ItemProperty property : getProperties())
			referencedIds.addAll(property.getReferencedIds());
		
		return referencedIds;
	}

	public Collection<String> getEnclosedObjectIds() {
		Collection<String> ids = new HashSet<String>();
		
		for(ItemProperty property : getProperties())
			ids.addAll(property.getEnclosedObjectIds());
		
		return ids;
	}
	
	public ItemProperty getPropertyByName(String name){
		for(ItemProperty property : properties)
			if(name.equals(property.getName()))
				return property;
		return null;
	}
	
	public Element getElement() {
		return element;
	}
	
}
