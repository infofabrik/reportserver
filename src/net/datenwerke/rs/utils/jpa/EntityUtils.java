package net.datenwerke.rs.utils.jpa;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.persistence.metamodel.Metamodel;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

public class EntityUtils {

   private final Provider<EntityManagerFactory> entityManagerFactoryProvider;
   private final Provider<EntityManager> entityManagerProvider;
   private final ReflectionService reflectionService;

   private static final int DEEP_UNPROXY_DEPTH = 10;

   @Inject
   public EntityUtils(Provider<EntityManagerFactory> entityManagerFactoryProvider,
         Provider<EntityManager> entityManagerProvider, ReflectionService reflectionService) {

      /* store objects */
      this.entityManagerFactoryProvider = entityManagerFactoryProvider;
      this.entityManagerProvider = entityManagerProvider;
      this.reflectionService = reflectionService;
   }

   public Object simpleHibernateUnproxy(Object entity) {
      if (null == entity)
         return entity;

      Hibernate.initialize(entity);
      if (entity instanceof HibernateProxy)
         entity = ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();

      if (entity instanceof Collection)
         ((Collection) entity).size();

      return entity;
   }

   public Object deepHibernateUnproxy(Object entity) throws IllegalArgumentException, IllegalAccessException {
      IdentityHashMap<Object, Integer> map = new IdentityHashMap<Object, Integer>();
      return deepHibernateUnproxy(entity, map, 0);
   }

   public Object deepHibernateUnproxy(Object entity, IdentityHashMap<Object, Integer> map, int depth)
         throws IllegalArgumentException, IllegalAccessException {
      if (null == entity)
         return entity;

      if (map.containsKey(entity))
         return entity;

      if (depth >= DEEP_UNPROXY_DEPTH - 1)
         return entity;

      map.put(entity, 1);

      Hibernate.initialize(entity);
      if (entity instanceof HibernateProxy)
         entity = ((HibernateProxy) entity).getHibernateLazyInitializer().getImplementation();
      if (entity instanceof Collection)
         ((Collection) entity).size();

      for (Field f : reflectionService.getAllFields(entity.getClass())) {
         int m = f.getModifiers();

         if (!Modifier.isFinal(m) && !Modifier.isStatic(m) && !reflectionService.isSimpleField(f)) {
            f.setAccessible(true);
            Object value = f.get(entity);
            if (null != value) {
               value = deepHibernateUnproxy(value, map, depth + 1);
               f.set(entity, value);
            }
         }
      }

      return entity;
   }

   public Object getId(Object object) {
      if (!isEntity(object))
         return null;
      return getId(object.getClass(), object);
   }

   public Object getId(Class<?> clazz, Object object) {
      Field f = getIdField(clazz);
      if (null == f)
         return null;

      f.setAccessible(true);
      try {
         return f.get(object);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public Object getTransientId(Object object) {
      if (!isEntity(object))
         return null;
      return getTransientId(object.getClass(), object);
   }

   public Object getTransientId(Class<?> clazz, Object object) {
      Field f = getTransientIdField(clazz);

      if (null == f)
         return null;

      f.setAccessible(true);
      try {
         return f.get(object);
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   public Field getTransientIdField(Class<?> clazz) {
      return reflectionService.getFieldByAnnotation(clazz, TransientID.class);
   }

   public Field getIdField(Class<?> clazz) {
      return reflectionService.getFieldByAnnotation(clazz, Id.class);
   }

   public boolean isEntity(Class<?> type) {
      return type.isAnnotationPresent(Entity.class);
   }

   public boolean isEntity(Object object) {
      if (null == object)
         return false;
      return isEntity(object.getClass());
   }

   public boolean isEntity(Field field) {
      return isEntity(field.getType());
   }

   public boolean isEntityCollection(Field field) {
      if (reflectionService.isCollection(field))
         return isEntity(reflectionService.getGenericType(field));
      return false;
   }

   public Class<?> getEntityBySimpleName(final String name) {
      return getAllEntityClasses()
         .stream()
         .filter(c -> c.getSimpleName().equals(name))
         .findAny()
         .orElse(null);
   }

   public Metamodel getMetaModel() {
      return entityManagerFactoryProvider.get().getMetamodel();
   }

   public Set<Field> getPersistantFields(Class<?> targetClass) {
      Set<Field> fields = new HashSet<Field>();

      while (null != targetClass) {
         for (Field field : targetClass.getDeclaredFields()) {
            if (!Modifier.isTransient(field.getModifiers()) && !Modifier.isStatic(field.getModifiers())
                  && null == field.getAnnotation(Transient.class))
               fields.add(field);
         }
         targetClass = targetClass.getSuperclass();
      }

      return fields;
   }

   public Set<Field> getEnclosedOrSimpleFields(final Class<?> clazz) {
      return getPersistantFields(clazz)
         .stream()
         .filter(field -> (!isEntity(field) && !isEntityCollection(field)) || isEnclosed(field))
         .collect(toSet());
   }

   public Set<Field> getEnclosedFields(final Class<?> clazz) {
      return getPersistantFields(clazz)
         .stream()
         .filter(this::isEnclosed)
         .collect(toSet());
   }

   public boolean isEnclosed(Field field) {
      return field.isAnnotationPresent(EnclosedEntity.class);
   }

   public Object unproxy(Object a) {
      if (a instanceof HibernateProxy)
         return ((HibernateProxy) a).getHibernateLazyInitializer().getImplementation();
      return a;
   }

   public Collection<Class<?>> getAllEntityClasses(final Class<?> clazz) {
      return getAllEntityClasses()
         .stream()
         .filter(entity -> clazz.isAssignableFrom(entity))
         .collect(toSet());
   }

   public List<Class<?>> getAllEntityClasses() {
      return getMetaModel().getEntities()
         .stream()
         .filter(entity -> Objects.nonNull(entity.getJavaType()))
         .map(entity -> entity.getJavaType())
         .collect(toList());
   }

   public Object deepDetach(Object object) throws IllegalArgumentException, IllegalAccessException {
      EntityManager entityManager = entityManagerProvider.get();
      return deepDetach(object, new IdentityHashMap<Object, Integer>(), entityManager);
   }

   public Object deepDetach(Object entity, IdentityHashMap<Object, Integer> map, EntityManager entityManager)
         throws IllegalArgumentException, IllegalAccessException {
      if (null == entity)
         return null;

      if (map.containsKey(entity))
         return entity;

      map.put(entity, 1);

      if (isEntity(entity)) {
         entityManager.detach(entity);
         return entity;
      }

      if (entity.getClass().isArray()) {
         int length = Array.getLength(entity);
         for (int i = 0; i < length; i++) {
            Object arrayElement = Array.get(entity, i);
            deepDetach(arrayElement, map, entityManager);
         }
      } else {
         for (Field f : reflectionService.getAllFields(entity.getClass())) {
            int m = f.getModifiers();

            if (!Modifier.isFinal(m) && !Modifier.isStatic(m) && !reflectionService.isSimpleField(f)) {
               f.setAccessible(true);
               Object value = f.get(entity);
               if (null != value) {
                  value = deepDetach(value, map, entityManager);
                  f.set(entity, value);
               }
            }
         }
      }

      return entity;
   }
}
