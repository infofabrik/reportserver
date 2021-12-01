package net.datenwerke.rs.utils.simplequery;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;

import net.datenwerke.rs.utils.simplequery.annotations.Join;
import net.datenwerke.rs.utils.simplequery.annotations.OrderBy;

import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.annotations.common.reflection.MetadataProvider;

import com.google.inject.name.Named;

public class SimpleQueryHelper {

	public Predicate getPredicate(
			MethodInvocation invocation,
			Class<? extends MetadataProvider> fromMetadata,
			CriteriaBuilder qb,
			From from,
			net.datenwerke.rs.utils.simplequery.annotations.Predicate predData) {
		String attribute = predData.attribute();
		Object value = null;
		if(invocation.getArguments().length > 0 && ! "".equals(predData.value()))
			value = getValue(invocation, predData);
		
		return getPredicate(invocation, fromMetadata, qb, from, predData.type(), attribute, value);
	}
	
	@SuppressWarnings("unchecked")
	public <A> Predicate getPredicate(
			MethodInvocation invocation,
			Class<? extends MetadataProvider> fromMetadata,
			CriteriaBuilder qb,
			From from,
			PredicateType type,
			String attribute,
			A value) {

		Expression expression = from;
		if(null != attribute && ! "".equals(attribute)){
			Bindable<?> attributeValue = getBindable(attribute,fromMetadata);
			if(attributeValue instanceof SingularAttribute)
				expression = from.get((SingularAttribute<?,?>) attributeValue);
			else
				expression = from.get((PluralAttribute<?,?,?>) attributeValue);
		} 
		
		switch(type){
		case EQUAL:
			return qb.equal(expression, value);
		case NOT_EQUAL:
			return qb.notEqual(expression, value);
		case LIKE:
			return qb.like(expression, (String) value);
		case NOT_LIKE:
			return qb.notLike(expression, (String) value);
		case GREATER:
			return qb.greaterThan(expression, (Comparable)value);
		case GREATER_OR_EQUAL:
			return qb.greaterThanOrEqualTo(expression, (Comparable)value);
		case LESS:
			return qb.lessThan(expression, (Comparable)value);
		case LESS_OR_EQUAL:
			return qb.lessThanOrEqualTo(expression,(Comparable)value);
		case IS_NULL:
			return qb.isNull(expression);
		case NOT_IS_NULL:
			return qb.isNotNull(expression);
		case IN:
			if(value instanceof Collection)
				return expression.in(((Collection)value).toArray());
			return expression.in(value);
		case NOT_IN:
			if(value instanceof Collection)
				return qb.not(expression.in(((Collection)value).toArray()));
			return qb.not(expression.in(value));
		case IS_MEMBER:
			if(value instanceof Collection)
				return qb.isMember(((Collection)value).toArray(), expression);
			return qb.isMember(value, expression);
		case NOT_IS_MEMBER:
			if(value instanceof Collection)
				return qb.isNotMember(((Collection)value).toArray(), expression);
			return qb.isNotMember(value, expression);
		}
		throw new IllegalStateException("Could not parse predicate");
	}
	
	private Bindable<?> getBindable(String attribute,
			Class<? extends MetadataProvider> fromMetadata) {
		try {
			String attributeName = (String) fromMetadata.getField(attribute).get(null);
			
			/* get metadata */
			Class<?> metadata = Class.forName(fromMetadata.getName().substring(0, fromMetadata.getName().length()-1));
			return (Bindable<?>) metadata.getField(attributeName).get(null);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
	}

	private Object getValue(
			MethodInvocation invocation,
			net.datenwerke.rs.utils.simplequery.annotations.Predicate predData) {
		return getArgumentByName(invocation, predData.value());
	}
	
	private Object getArgumentByName(MethodInvocation invocation, String parameterName) {
		Method method = invocation.getMethod();
		
		Annotation[][] allParamAnnotations = method.getParameterAnnotations();
		for(int i = 0; i < allParamAnnotations.length; i++){
			List<Annotation> paramAnnotations = Arrays.asList(method.getParameterAnnotations()[i]);
			for(Annotation paramAnno : paramAnnotations){
				if(paramAnno.annotationType().equals(Named.class)){
					Named namedAnnotation = (Named) paramAnno;
					
					if(parameterName.equals(namedAnnotation.value()))
						return invocation.getArguments()[i];
				}
			}
		}
		throw new IllegalArgumentException("Could not find named Parameter: " + parameterName + " on method " + method.getName() + " in class " + invocation.getMethod().getDeclaringClass() + ". Are you missing an @Named annotation?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public Class<? extends MetadataProvider> getMetadataProviderFor(Class<?> type) throws ClassNotFoundException {
		return (Class<? extends MetadataProvider>) Class.forName(type.getName() + "__");
	}

	public void amendOrderBy(CriteriaBuilder qb, Root r, CriteriaQuery c, Class<? extends MetadataProvider> fromMetadata, OrderBy[] orderBys) {
		if(orderBys.length == 0)
			return;
		
		List<Order> orderList = new ArrayList<Order>();
		
		for(OrderBy order : orderBys){
			String attributeName = order.attribute();
			Bindable<?> attribute = getBindable(attributeName, fromMetadata);
			
			if(OrderBy.Direction.ASC.equals(order.dir()))
				orderList.add(qb.asc(r.get((SingularAttribute) attribute)));
			else
				orderList.add(qb.desc(r.get((SingularAttribute) attribute)));
		}
		
		c.orderBy(orderList);
	}

	public void amendSingleProjection(CriteriaBuilder qb, Root r,
			CriteriaQuery c, Class<? extends MetadataProvider> fromMetadata,
			String select) {
		if(null == select || "".equals(select))
			return;
		
		Bindable<?> attribute = getBindable(select, fromMetadata);
		
		c.select(r.get((SingularAttribute) attribute));
	}

	public CriteriaQuery createQuery(CriteriaBuilder qb, Class<?> from,
			Class<? extends MetadataProvider> fromMetadata, String select) {
		if(null == select || "".equals(select))
			return qb.createQuery(from);
		
		Class<?> type = getTypeOfAttribute(select, fromMetadata);
		
		return qb.createQuery(type);
	}

	private Class<?> getTypeOfAttribute(String select,
			Class<? extends MetadataProvider> fromMetadata) {
		Bindable<?> bindable = getBindable(select, fromMetadata);
		return bindable.getBindableJavaType();
	}

	public void amendJoinPaths(MethodInvocation invocation, CriteriaBuilder qb, Root r, CriteriaQuery c,
			Class<? extends MetadataProvider> fromMetadata, Join[] joinPath) throws ClassNotFoundException {
		
		From currentFrom = r;
		Class<? extends MetadataProvider> currentMetadata = fromMetadata;
		for(Join join : joinPath){
			Bindable<?> joinBind = getBindable(join.joinAttribute(), currentMetadata);
			currentMetadata = getMetadataProviderFor(joinBind.getBindableJavaType());
			if(joinBind instanceof SingularAttribute)
				currentFrom = currentFrom.join((SingularAttribute) joinBind);
			else if(joinBind instanceof ListAttribute)
				currentFrom = currentFrom.join((ListAttribute) joinBind);
			else if(joinBind instanceof SetAttribute)
				currentFrom = currentFrom.join((SetAttribute) joinBind);
			
			/* handle predicates */
			List<Predicate> predicates = new ArrayList<Predicate>();
			for(net.datenwerke.rs.utils.simplequery.annotations.Predicate predData : join.where()){
				Predicate predicate = getPredicate(invocation, currentMetadata, qb, currentFrom, predData);
				predicates.add(predicate);
			}
			
			if(! predicates.isEmpty())
				c.where(qb.and(predicates.toArray(new Predicate[]{})));
		}
	}

}
