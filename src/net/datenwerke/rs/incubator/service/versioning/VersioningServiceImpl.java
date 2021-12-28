package net.datenwerke.rs.incubator.service.versioning;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.EntityManager;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.Audited;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.incubator.service.versioning.entities.Revision;
import net.datenwerke.rs.utils.jpa.EntityUtils;
import net.datenwerke.rs.utils.reflection.ReflectionService;

public class VersioningServiceImpl implements VersioningService{
	
	private final Provider<EntityManager> entityManagerProvider;
	private final ReflectionService reflectionService;
	private final EntityUtils entityUtils;

	@Inject
	public VersioningServiceImpl(
			Provider<EntityManager> entityManagerProvider, 
			ReflectionService reflectionService,
			EntityUtils entityUtils) {

		this.entityManagerProvider = entityManagerProvider;
		this.reflectionService = reflectionService;
		this.entityUtils = entityUtils;
	}
	
	public <T> T getAtRevision(Class<T> clazz, Object object, Number revision){
		AuditReader auditReader = getAuditReader();
		Object o = auditReader.find(clazz, entityUtils.getId(object), revision);
		o = entityUtils.unproxy(o);
	
		if(null == o)
			return null;
		
		if(!clazz.isAssignableFrom(o.getClass()))
			throw new RuntimeException("Incompatible types");
		
		return (T)o;
	}
	
	public <T> T getAtRevision(Class<T> clazz, long objectId, Number revision){
		AuditReader auditReader = getAuditReader();
		Object o = auditReader.find(clazz, objectId, revision);
		o = entityUtils.unproxy(o);
	
		if(null == o)
			return null;
		
		if(!clazz.isAssignableFrom(o.getClass()))
			throw new RuntimeException("Incompatible types");
		
		return (T)o;
	}
	
	public AuditReader getAuditReader(){
		return AuditReaderFactory.get(entityManagerProvider.get());
	}
	
	@Override
	public Set<Revision> getRevisions(Object object) {
		object = entityUtils.unproxy(object);
		
		TreeSet<Revision> res = new TreeSet<Revision>();
		
		AuditReader auditReader = getAuditReader();
		
		for(Number n : getRevisionNumbers(object)){
			Revision rev = auditReader.findRevision(Revision.class, n);
			res.add(rev);
		}
		
		return res;
	}
	
	@Override
	public Set<Revision> getRevisions(Set<Number> revisionNumbers) {
		TreeSet<Revision> res = new TreeSet<Revision>();
		
		AuditReader auditReader = getAuditReader();
		
		for(Number n : revisionNumbers){
			Revision rev = auditReader.findRevision(Revision.class, n);
			res.add(rev);
		}
		
		return res;
	}
	

	@Override
	public Set<Number> getRevisionNumbers(Object object){
		TreeSet<Number> res = new TreeSet<Number>();
		if(null == object)
			return res;

		object = entityUtils.unproxy(object);
		Class<?> clazz = object.getClass();
		
		if(object instanceof Collection){
			Iterator it = ((Collection) object).iterator();
			while(it.hasNext())
				res.addAll(getRevisionNumbers(it.next()));
			
			return res;
		}
		
		if(!isAuditedEntity(clazz))
			return res;
		
		/* get revisions of enclosed entities */
		for(Field field : entityUtils.getEnclosedFields(clazz))
			if(isAuditedEntity(field.getType()) || Collection.class.isAssignableFrom(field.getType()))
				res.addAll(getRevisionNumbers(reflectionService.getFieldValueNoSecurity(field, object)));
		
		/* get revisions of current object */
		AuditReader auditReader = getAuditReader();
		Object eid = entityUtils.getId(object);
		res.addAll(auditReader.getRevisions(getAuditedSuperclass(clazz), eid));
		
		return res;
	}
	
	public Set<Number> getRevisionNumbers(Class<?> clazz, long objectId){
		TreeSet<Number> res = new TreeSet<Number>();
		
		if(!isAuditedEntity(clazz))
			return res;
		
		AuditReader auditReader = getAuditReader();
		
		res.addAll(auditReader.getRevisions(getAuditedSuperclass(clazz), objectId));
		
		return res;
	}
	
	private boolean isAuditedEntity(Class<?> clazz){
		if(clazz.isAnnotationPresent(Audited.class))
			return true;
		
		if(null != clazz.getSuperclass())
			return isAuditedEntity(clazz.getSuperclass());
			
		return false;
	}
	
	private Class<?> getAuditedSuperclass(Class<?> clazz){
		if(clazz.isAnnotationPresent(Audited.class))
			return clazz;
		
		if(null != clazz.getSuperclass())
			return getAuditedSuperclass(clazz.getSuperclass());
			
		return null;
	}
	
	
}
