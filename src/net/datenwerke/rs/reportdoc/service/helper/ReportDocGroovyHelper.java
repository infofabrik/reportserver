package net.datenwerke.rs.reportdoc.service.helper;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Injector;

import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.security.service.security.rights.Right;

public class ReportDocGroovyHelper {

	private final Injector injector;
	private TerminalSession session;

	@Inject
	public ReportDocGroovyHelper(Injector injector) {
		super();
		this.injector = injector;
	}
	
	public Object getInstance(Class<?> type){
		return injector.getInstance(type);
	}
	
	public Object findObject(String objectLocation, Class<? extends Right> ... rights) throws ObjectResolverException{
		TerminalSession session = this.session;
		if(null == session)
			session = (TerminalSession) injector.getInstance(TerminalService.class).getUnscopedTerminalSession();
		return session.getObjectResolver().getObject(objectLocation, rights);
	}
	
	public Collection<Object> findObjects(String objectLocation, Class<? extends Right> ... rights) throws ObjectResolverException{
		TerminalSession session = this.session;
		if(null == session)
			session = (TerminalSession) injector.getInstance(TerminalService.class).getUnscopedTerminalSession();
		return session.getObjectResolver().getObjects(objectLocation, rights);
	}
	
	public <T> List<T> getEntitiesByType(Class<T> type){
		return injector.getInstance(EntityManager.class).createQuery("FROM " + type.getSimpleName()).getResultList();
	}

	public void setSession(TerminalSession session) {
		this.session = session;
	}
}
