package net.datenwerke.rs.utils.entitycloner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.utils.entitycloner.annotation.CloneGuide;
import net.datenwerke.rs.utils.entitycloner.annotation.ClonePostProcessor;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitycloner.annotation.EntityClonerIgnore;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.entitycloner.config.ClonerConfig;
import net.datenwerke.rs.utils.entitycloner.config.DefaultClonerConfig;
import net.datenwerke.rs.utils.jpa.EntityUtils;

public class EntityClonerServiceImpl implements EntityClonerService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	interface CallbackOnInnerEntityCreation{
		void created(Object innerEntity) throws IllegalArgumentException, IllegalAccessException;
	}
	
	private final Provider<EntityManager> entityManagerProvider;
	private final EntityUtils entityUtils;
	
	@Inject
	public EntityClonerServiceImpl(
		Provider<EntityManager> entityManagerProvider,
		EntityUtils entityUtils
		){
		
		/* store objects */
		this.entityManagerProvider = entityManagerProvider;
		this.entityUtils = entityUtils;
	}
	
	public <T> T cloneEntity(T entity) {
		if(null == entity)
			return null;
		
		Class<?> entityClass = entity.getClass();
		return (T) cloneEntity(entity, entityClass, new DefaultClonerConfig());
	}
	
	public <T> T cloneEntity(T entity, ClonerConfig config) {
		if(null == entity)
			return null;
		
		Class<?> entityClass = entity.getClass();
		return (T) cloneEntity(entity, entityClass, config);
	}

	public Object cloneEntity(Object entity, Class<?> entityClass){
		if(null == entity)
			return null;
		
		return cloneEntity(entity, entityClass, new DefaultClonerConfig());
	}
	
	public Object cloneEntity(Object entity, Class<?> entityClass, ClonerConfig config) {
		if(null == entity)
			return null;
		
		Map<Object, Object> clonedInnerEntityCache = new IdentityHashMap<Object, Object>();
		Map<Object, List<CallbackOnInnerEntityCreation>> loadedReferencedEntityCache = new IdentityHashMap<Object, List<EntityClonerServiceImpl.CallbackOnInnerEntityCreation>>();
		Map<Object, List<CallbackOnInnerEntityCreation>> inProcess = new IdentityHashMap<Object, List<CallbackOnInnerEntityCreation>>();
		
		try {
			Object clone = entityClass.newInstance();
			copyFields(entity, clone, config, clonedInnerEntityCache, loadedReferencedEntityCache, inProcess);
			
			clearId(entityClass, clone, config);
			clearVersion(entityClass, clone, config);
			
			postProcess(entity, clone, config);
			
			return clone;
		} catch (InstantiationException e) {
			logger.warn( "error cloning entity", e);
		} catch (IllegalAccessException e) {
			logger.warn( "error cloning entity", e);
		} finally {
			clonedInnerEntityCache.clear();
			loadedReferencedEntityCache.clear();
		}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	private <T> T cloneInnerEntity(T entity, ClonerConfig config,
			Map<Object, Object> clonedInnerEntityCache, Map<Object, List<CallbackOnInnerEntityCreation>> loadedReferencedEntityCache, Map<Object, List<CallbackOnInnerEntityCreation>> inProcess) {
		Class<?> entityClass = entity.getClass();
		
		try {
			T clone = (T) entityClass.newInstance();
			copyFields(entity, clone, config, clonedInnerEntityCache, loadedReferencedEntityCache, inProcess);
			
			clearId(entityClass, clone, config);
			clearVersion(entityClass, clone, config);
			
			postProcess(entity, clone, config);
			
			return (T)clone;
		} catch (InstantiationException e) {
			logger.warn( "error cloning entity", e);
		} catch (IllegalAccessException e) {
			logger.warn( "error cloning entity", e);
		}
		
		return null;
	}
	
	private void postProcess(Object entity, Object clone, ClonerConfig config) {
		for(Method m : clone.getClass().getMethods()){
			if(m.isAnnotationPresent(ClonePostProcessor.class)){
				try {
					m.invoke(clone, entity);
				} catch (IllegalArgumentException e) {
					logger.warn( "error cloning entity", e);
				} catch (IllegalAccessException e) {
					logger.warn( "error cloning entity", e);
				} catch (InvocationTargetException e) {
					logger.warn( "error cloning entity", e);
				}
				break;
			}
		}
	}
	
	private void clearId(Class<?> clazz, Object object, ClonerConfig config) throws IllegalArgumentException, IllegalAccessException {
		for(Field f : clazz.getDeclaredFields()){
			if(f.isAnnotationPresent(Id.class)){
				f.setAccessible(true);
			
				/* obtain old id */
				Object oldId = f.get(object);
				if(null == oldId)
					oldId = entityUtils.getTransientId(object);
				
				/* set id to null */
				f.set(object, null);
				
				/* store id as transient id */
				storeIdAsTransient(clazz, object, oldId);
				
				return;
			}
		}
		
		if(null != clazz.getSuperclass())
			clearId(clazz.getSuperclass(), object, config);
	}
	
	private void storeIdAsTransient(Class<?> clazz, Object object, Object oldId) throws IllegalArgumentException, IllegalAccessException {
		for(Field f : clazz.getDeclaredFields()){
			if(f.isAnnotationPresent(TransientID.class)){
				f.setAccessible(true);
				f.set(object, oldId);
				return;
			}
		}
	}

	private void clearVersion(Class<?> clazz, Object object, ClonerConfig config) throws IllegalArgumentException, IllegalAccessException {
		for(Field f : clazz.getDeclaredFields()){
			if(f.isAnnotationPresent(Version.class)){
				f.setAccessible(true);
				f.set(object, null);
				return;
			}
		}
		
		if(null != clazz.getSuperclass())
			clearVersion(clazz.getSuperclass(), object, config);
	}

	public void copyFields(Object source, Object target, ClonerConfig config, 
			Map<Object, Object> clonedInnerEntityCache, Map<Object, List<CallbackOnInnerEntityCreation>> loadedReferencedEntityCache, Map<Object, List<CallbackOnInnerEntityCreation>> inProcess){
		Class<?> targetClass = target.getClass();
		
		Method cloneGuide = findCloneGuide(targetClass);
		Set<Field> fieldsToCopy = Collections.emptySet();
		try {
			fieldsToCopy = gatherCopyableFields(target, targetClass, cloneGuide);
		} catch (IllegalArgumentException e1) {
			logger.warn( "error cloning entity", e1);
		} catch (IllegalAccessException e1) {
			logger.warn( "error cloning entity", e1);
		} catch (InvocationTargetException e1) {
			logger.warn( "error cloning entity", e1);
		}
		
		/* copy objects */
		copyFields(source, target, fieldsToCopy, cloneGuide, config, true, clonedInnerEntityCache, loadedReferencedEntityCache, inProcess);
		copyFields(source, target, fieldsToCopy, cloneGuide, config, false, clonedInnerEntityCache, loadedReferencedEntityCache, inProcess);
	}
	

	private void copyFields(Object source, final Object target,
			Set<Field> fieldsToCopy, Method cloneGuide, ClonerConfig config, boolean enclosed, 
			Map<Object, Object> clonedInnerEntityCache, Map<Object, List<CallbackOnInnerEntityCreation>> loadedReferencedEntityCache, Map<Object, List<CallbackOnInnerEntityCreation>> inProcess) {
		for(final Field field : fieldsToCopy){
			/* make field accessible */
			field.setAccessible(true);
			
			try {
				if(enclosed && ! field.isAnnotationPresent(EnclosedEntity.class))
					continue;
				
				Object content = field.get(source);

				if(null == content)
					continue;
				
				if(content instanceof HibernateProxy)
					content = ((HibernateProxy)content).getHibernateLazyInitializer().getImplementation();
				
				if(content instanceof Collection){
					content = copyCollectionParameters(content, field, target, config, clonedInnerEntityCache, loadedReferencedEntityCache, inProcess);
					field.set(target, content);
				} else if(entityUtils.isEntity(content)){
					copyEntity(content, field, target, config, new CallbackOnInnerEntityCreation() {
						
						@Override
						public void created(Object innerEntity) throws IllegalArgumentException, IllegalAccessException {
							field.set(target, innerEntity);
						}
					}, clonedInnerEntityCache, loadedReferencedEntityCache, inProcess);
				} else
					field.set(target, content);
			} catch (IllegalArgumentException e) {
				logger.warn( "error cloning entity", e);
			} catch (IllegalAccessException e) {
				logger.warn( "error cloning entity", e);
			} catch (InvocationTargetException e) {
				logger.warn( "error cloning entity", e);
			}
		}
	}

	private Method findCloneGuide(Class<?> targetClass) {
		for(Method m : targetClass.getMethods())
			if(m.isAnnotationPresent(CloneGuide.class))
				return m;
		return null;
	}

	private Set<Field> gatherCopyableFields(Object target, Class<?> targetClass, Method cloneGuide) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Set<Field> fields = new HashSet<Field>();
		
		while(null != targetClass){
			for(Field field : targetClass.getDeclaredFields()){
				if(! field.isAnnotationPresent(EntityClonerIgnore.class) && ! Modifier.isTransient(field.getModifiers()) && ! Modifier.isStatic(field.getModifiers()))
					if( (null == cloneGuide) || (true == (Boolean) cloneGuide.invoke(target, field)))
						fields.add(field);
			}
			targetClass = targetClass.getSuperclass();
		}
		
		return fields;
	}

	
	private void copyEntity(Object content, Field field, Object target, ClonerConfig config, CallbackOnInnerEntityCreation callback,
			Map<Object, Object> clonedInnerEntityCache, Map<Object, List<CallbackOnInnerEntityCreation>> loadedReferencedEntityCache, Map<Object, List<CallbackOnInnerEntityCreation>> inProcess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		/* if reference */
		Object entityId = entityUtils.getId(content.getClass(), content);
		
		if(clonedInnerEntityCache.containsKey(content)){
			callback.created(clonedInnerEntityCache.get(content));
		} else if(inProcess.containsKey(content)){
			inProcess.get(content).add(callback);
		} else if(field.isAnnotationPresent(EnclosedEntity.class) || null == entityId || config.cloneReferencedEntities()){
			inProcess.put(content, new ArrayList<EntityClonerServiceImpl.CallbackOnInnerEntityCreation>());
			
			Object clonedContent = cloneInnerEntity(content, config, clonedInnerEntityCache, loadedReferencedEntityCache, inProcess);
			
			clonedInnerEntityCache.put(content, clonedContent);
			if(inProcess.containsKey(content)){
				List<EntityClonerServiceImpl.CallbackOnInnerEntityCreation> list = inProcess.remove(content);
				if(null != list)
					for(CallbackOnInnerEntityCreation waitingCallback : list)
						waitingCallback.created(clonedContent);
			}
			
			callback.created(clonedContent);
			
			/* test if we have accidentally already loaded the entity as referenced */
			if(loadedReferencedEntityCache.containsKey(content)){
				for(CallbackOnInnerEntityCreation oldCallback : loadedReferencedEntityCache.get(content))
					oldCallback.created(clonedContent);
			}
		} else if(config.nullReferencedEntities()) { 
			callback.created(null);
		} else {
			Object managedEntity = entityManagerProvider.get().find(content.getClass(), entityId);
			callback.created(managedEntity);
			
			/* store in map in order to possibly remedy a falsely loaded reference */
			if(! loadedReferencedEntityCache.containsKey(content))
				loadedReferencedEntityCache.put(content, new ArrayList<EntityClonerServiceImpl.CallbackOnInnerEntityCreation>());
			loadedReferencedEntityCache.get(content).add(callback);
		}
	}

	@SuppressWarnings("unchecked")
	private Object copyCollectionParameters(Object content, Field field, Object target, ClonerConfig config,
			Map<Object, Object> clonedInnerEntityCache, Map<Object, List<CallbackOnInnerEntityCreation>> loadedReferencedEntityCache, Map<Object, List<CallbackOnInnerEntityCreation>> inProcess) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if(content instanceof HibernateProxy)
			content = ((HibernateProxy)content).getHibernateLazyInitializer().getImplementation();
		
		final Collection newCollection = createCollectionInstance((Collection)content);
		
		for(Object contentItem : (Collection) content){
			if(contentItem instanceof HibernateProxy)
				contentItem = ((HibernateProxy)contentItem).getHibernateLazyInitializer().getImplementation();
			
			if(entityUtils.isEntity(contentItem)) {
				final int n = newCollection.size();
				copyEntity(contentItem, field, target, config, new CallbackOnInnerEntityCreation() {
					
					@Override
					public void created(Object innerEntity) throws IllegalArgumentException,
							IllegalAccessException {
						if(newCollection instanceof List)
							((List)newCollection).add(n, innerEntity);
						else
							newCollection.add(innerEntity);
					}
				}, clonedInnerEntityCache,loadedReferencedEntityCache, inProcess);
			} else 
				newCollection.add(contentItem);
		}
		
		return newCollection;
	}


	/**
	 * Chooses a standard implementation for a collection
	 * @param content
	 */
	@SuppressWarnings("unchecked")
	private Collection createCollectionInstance(Collection content) {
		if(content instanceof Set)
			return new HashSet();
		if(content instanceof List)
			return new ArrayList();
		throw new IllegalArgumentException("Unsupported Collection type; " + content.getClass().getName()); //$NON-NLS-1$
	}

}
