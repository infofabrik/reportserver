package net.datenwerke.rs.terminal.service.terminal.objresolver.baseresolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.terminal.service.terminal.objresolver.hooks.ObjectResolverHook;
import net.datenwerke.rs.utils.jpa.EntityUtils;

public class HqlResolver implements ObjectResolverHook {

	private final Provider<EntityManager> entityManagerProvider;
	private final EntityUtils entityUtils;
	
	@Inject
	public HqlResolver(
		Provider<EntityManager> entityManagerProvider,
		EntityUtils entityUtils
		){
		
		/* store objects */
		this.entityManagerProvider = entityManagerProvider;
		this.entityUtils = entityUtils;
	}
	
	
	@Override
	public boolean consumes(String locationStr, TerminalSession terminalSession) {
		return null != locationStr && locationStr.startsWith("hql:");
	}

	@Override
	public Collection<Object> getObjects(String locationStr,
			TerminalSession terminalSession) throws ObjectResolverException {
		Collection<Object> result = new ArrayList<Object>();
		
		String query = locationStr.substring(4);
		
		try{
			/* execute query */
			List resultList = entityManagerProvider.get().createQuery(query).getResultList();

			/* simple result */
			if(! resultList.isEmpty()){
				Object first = resultList.get(0);
				if(first instanceof HibernateProxy)
					first = ((HibernateProxy)first).getHibernateLazyInitializer().getImplementation();
				if(entityUtils.isEntity(first))
					for(Object obj : resultList)
						result.add(obj);
			}
			
		} catch(Exception e){
			String msg =  null != e.getMessage() ? e.getMessage() : "";
			if(null != e.getCause()){
				msg += null != e.getCause().getMessage() ? e.getCause().getMessage() : "";
				if(null != e.getCause().getCause())
					msg += null != e.getCause().getCause().getMessage() ? e.getCause().getCause().getMessage() : "";
			}
			
			throw new ObjectResolverException(msg, e);
		}
		
		return result;
	}


}
