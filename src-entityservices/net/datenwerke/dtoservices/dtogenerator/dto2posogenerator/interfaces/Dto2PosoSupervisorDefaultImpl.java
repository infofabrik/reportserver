package net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces;

import java.util.Collection;

public class Dto2PosoSupervisorDefaultImpl implements Dto2PosoSupervisor<Object, Object> {

	@Override
	public void enclosedObjectsRemovedFromCollection(Object dto, Object poso,
			Collection<?> object, String fieldname) {
		
	}

	@Override
	public void enclosedObjectRemoved(Object dto, Object poso, Object removedProperty, Object replacement,
			String fieldname) {
		
	}
	
	@Override
	public void referencedObjectRemoved(Object dto, Object poso, Object removedProperty, Object replacement,
			String fieldname) {
		
	}

}
