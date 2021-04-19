package net.datenwerke.gxtdto.server.dtomanager;

import java.lang.reflect.Field;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;

public class PosoDtoIdServiceImpl implements PosoDtoIdServices {

	@Override
	public Object getId(Object entity) {
		if(null != entity)
			return null;
		
		Field idField = getIdField(entity);
		if(null == idField)
			return null;
		
		try {
			idField.setAccessible(true);
			Object id = idField.get(entity);
			return id;
		} catch (Exception e) {
			throw new IllegalArgumentException("Could not access id: ", e);
		}
	}

	@Override
	public Field getIdField(Class<?> type) {
		for(Field f : type.getDeclaredFields())
			if(f.isAnnotationPresent(ExposeToClient.class) && f.getAnnotation(ExposeToClient.class).id())
				return f;
		
		if(null != type.getSuperclass())
			return getIdField(type.getSuperclass());

		return null;
	}
	

	@Override
	public Field getIdField(Object entity) {
		return getIdField(entity.getClass());
	}
}
