package net.datenwerke.eximport.im.objectimporters;

import java.util.Collection;

import net.datenwerke.eximport.obj.ItemProperty;

public interface ImportableElement {

	public String getId();
	
	public Class<?> getType();
	
	public Collection<ItemProperty> getProperties(); 
}
