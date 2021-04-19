package net.datenwerke.eximport.obj;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import nu.xom.Element;


/**
 * 
 *
 */
public class ItemPropertyCollection extends ItemProperty {

	protected final Class<?> propertyType;
	protected final List<ItemProperty> collectionValues;
	
	public ItemPropertyCollection(String name, Class<?> type, Class<?> propertyType, List<ItemProperty> collectionValues, Element el) {
		super(name, type, el);
		
		this.propertyType = propertyType;
		this.collectionValues = collectionValues;
	}

	public Class<?> getPropertyType() {
		return propertyType;
	}

	public List<ItemProperty> getCollectionValues() {
		return collectionValues;
	}
	
	public Collection<String> getReferencedIds() {
		Collection<String> references =  new HashSet<String>();
		
		for(ItemProperty property : getCollectionValues())
			references.addAll(property.getReferencedIds());
		
		return references;
	}
	
	public Collection<String> getEnclosedObjectIds() {
		Collection<String> ids =  new HashSet<String>();
		
		for(ItemProperty property : getCollectionValues())
			ids.addAll(property.getEnclosedObjectIds());
		
		return ids;
	}


}
