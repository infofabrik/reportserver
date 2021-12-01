package net.datenwerke.eximport.obj;

import java.util.Collection;
import java.util.HashSet;

import net.datenwerke.eximport.im.objectimporters.ImportableElement;
import nu.xom.Element;


/**
 * 
 *
 */
public class EnclosedItemProperty extends ItemProperty implements ImportableElement {

	protected final String id;
	protected final Class<?> exporterType;
	protected final Collection<ItemProperty> itemProperties;
	
	
	public EnclosedItemProperty(String name, Class<?> type, String id, Class<?> exporterType, Collection<ItemProperty> itemProperties, Element el) {
		super(name, type, el);

		/* store objects */
		this.id = id;
		this.exporterType = exporterType;
		this.itemProperties = itemProperties;
	}
	
	public String getId(){
		return id;
	}

	public Class<?> getExporterType() {
		return exporterType;
	}

	public Collection<ItemProperty> getProperties() {
		return itemProperties;
	}
	
	public Collection<String> getReferencedIds() {
		Collection<String> references =  new HashSet<String>();
		
		for(ItemProperty property : getProperties())
			references.addAll(property.getReferencedIds());
		
		return references;
	}
	
	public Collection<String> getEnclosedObjectIds() {
		Collection<String> ids =  new HashSet<String>();
		ids.add(getId());
		
		for(ItemProperty property : getProperties())
			ids.addAll(property.getEnclosedObjectIds());
		
		return ids;
	}

}
