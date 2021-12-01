package net.datenwerke.rs.utils.simplequery.byid;

import java.lang.reflect.Field;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.datenwerke.rs.utils.jpa.EntityUtils;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;

import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 *
 */
public class QueryByIdProcessor {

	private final Provider<EntityManager> entityManagerProvider;
	private final EntityUtils entityUtils;
	
	@Inject
	public QueryByIdProcessor(
		Provider<EntityManager> entityManagerProvider,
		EntityUtils entityUtils
		){
		
		/* store objects */
		this.entityManagerProvider = entityManagerProvider;
		this.entityUtils = entityUtils;
	}
	
	public Object process(QueryByIdHandler handler, MethodInvocation invocation) {
		QueryById metadata = handler.getMetadata();
		Class<?> from = metadata.from();
		if(Void.class.equals(from))
			from = invocation.getMethod().getReturnType();
		
		if(! from.isAnnotationPresent(Entity.class))
			throw new IllegalArgumentException("Can only perform queries for entities");
		
		EntityManager em = entityManagerProvider.get();
		CriteriaBuilder qb = em.getCriteriaBuilder();
		
		CriteriaQuery c = qb.createQuery(from);
		Root r = c.from(from);
		
		Field idField;
		try {
			idField = entityUtils.getIdField(from);
			if(null == idField)
				throw new IllegalArgumentException("Could not find ID field on " + from);
			Object providedId = invocation.getArguments()[0];
			Predicate condition = qb.equal(r.get(idField.getName()), providedId);
			c.where(condition);
			
			TypedQuery<?> q = em.createQuery(c);
//			q.setFlushMode(FlushModeType.COMMIT);
			
			Object result = q.getSingleResult();
			return result;
		} catch(NoResultException e){
			return null;
		}
	}

}
