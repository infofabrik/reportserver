package net.datenwerke.rs.terminal.service.terminal.objresolver.baseresolver;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.EntityManager;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.terminal.service.terminal.objresolver.hooks.ObjectResolverHook;
import net.datenwerke.rs.utils.jpa.EntityUtils;

public class IdResolver implements ObjectResolverHook {

	private final Provider<EntityManager> entityManagerProvider;
	private final EntityUtils entityUtils;
	
	@Inject
	public IdResolver(
		Provider<EntityManager> entityManagerProvider,
		EntityUtils entityUtils
		){
		
		/* store objects */
		this.entityManagerProvider = entityManagerProvider;
		this.entityUtils = entityUtils;
	}
	
	
	@Override
	public boolean consumes(String locationStr, TerminalSession terminalSession) {
		return null != locationStr && locationStr.startsWith("id:") && locationStr.split(":").length == 3;
	}

	@Override
	public Collection<Object> getObjects(String locationStr,
			TerminalSession terminalSession) throws ObjectResolverException {
		String[] parts = locationStr.split(":");
		String entityName = parts[1];
		String id = parts[2];
		
		try{
			Class<?> type = entityUtils.getEntityBySimpleName(entityName);
			
			/* execute query */
			Field idField = entityUtils.getIdField(type);
			idField.getType();
			Object result = entityManagerProvider.get().find(type, getCasted(idField.getType(), id));

			/* simple result */
			if(null != result){
				if(result instanceof HibernateProxy)
					result = ((HibernateProxy)result).getHibernateLazyInitializer().getImplementation();
				if(entityUtils.isEntity(result))
					return Collections.singleton(result);
			}
		} catch(Exception e){
			throw new IllegalArgumentException(e);
		}
		
		return Collections.emptySet();
	}


	private Object getCasted(Class<?> type, String id) {
		if(String.class.equals(type))
			return id;
		if(Long.class.equals(type))
			return Long.valueOf(id);
		if(Integer.class.equals(type))
			return Integer.parseInt(id);
		return id;
	}
	
	

}
