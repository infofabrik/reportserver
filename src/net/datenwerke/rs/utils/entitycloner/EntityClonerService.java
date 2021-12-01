package net.datenwerke.rs.utils.entitycloner;

import net.datenwerke.rs.utils.entitycloner.config.ClonerConfig;


public interface EntityClonerService {

	public <T> T cloneEntity(T entity);
	
	public <T> T cloneEntity(T entity, ClonerConfig config);

	public Object cloneEntity(Object entity, Class<?> entityClass);
	
	public Object cloneEntity(Object entity, Class<?> entityClass, ClonerConfig config);

}
