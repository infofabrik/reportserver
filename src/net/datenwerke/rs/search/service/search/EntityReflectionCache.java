package net.datenwerke.rs.search.service.search;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import net.datenwerke.rs.utils.reflection.ReflectionService;

public class EntityReflectionCache {

	private ReflectionService reflectionService;
	
	private HashMap<Class, List<Field>> fieldmap = new HashMap<Class, List<Field>>();
	
	@Inject
	public EntityReflectionCache(ReflectionService reflectionService) {
		this.reflectionService = reflectionService;
	}
		
	public List<Field> getFields(Class type){
		if(!fieldmap.containsKey(type))
			scanType(type);
		
		return fieldmap.get(type);
	}

	private void scanType(Class type) {
		List<Field> res = reflectionService.getFieldsByAnnotation(type, net.datenwerke.gf.base.service.annotations.Field.class);
		for(Field f : res)
			f.setAccessible(true);
		fieldmap.put(type, res);
	}
}
