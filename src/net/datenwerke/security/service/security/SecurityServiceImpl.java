package net.datenwerke.security.service.security;

import static java.util.stream.Collectors.toSet;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.net.ssl.SSLContext;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.GeneralInfoService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.utils.file.RsFileUtils;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.authenticator.exceptions.AuthenticatorRuntimeException;
import net.datenwerke.security.service.security.action.SecurityAction;
import net.datenwerke.security.service.security.annotation.GenericSecurityTarget;
import net.datenwerke.security.service.security.entities.AccessType;
import net.datenwerke.security.service.security.entities.Ace;
import net.datenwerke.security.service.security.entities.AceAccessMap;
import net.datenwerke.security.service.security.entities.Acl;
import net.datenwerke.security.service.security.entities.GenericSecurityTargetEntity;
import net.datenwerke.security.service.security.entities.GenericSecurityTargetEntity__;
import net.datenwerke.security.service.security.entities.HierarchicalAce;
import net.datenwerke.security.service.security.exceptions.IllegalGenericTargetException;
import net.datenwerke.security.service.security.exceptions.IllegalSecurityTargetConfigurationException;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Right;
import net.datenwerke.security.service.usermanager.UserManagerService;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 *
 */
@Singleton
public class SecurityServiceImpl implements SecurityService {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private Map<Class<?>, List<SecurityTargetConfiguration>> genericTargetConfigurations = new HashMap<>();
   private Map<Class<? extends SecurityTarget>, List<SecurityTargetConfiguration>> targetConfigurations = new HashMap<>();
   private Set<Securee> registeredSecurees = new HashSet<Securee>();

   private final Provider<EntityManager> entityManagerProvider;
   private final Provider<AuthenticatorService> authenticatorProvider;
   private final Provider<UserManagerService> userManagerProvider;
   private final Provider<ConfigService> configServiceProvider;
   private final Provider<GeneralInfoService> generalInfoServiceProvider;

   @Inject
   public SecurityServiceImpl(
         Provider<EntityManager> entityManagerProvider,
         Provider<AuthenticatorService> authenticatorProvider, 
         Provider<UserManagerService> userManagerProvider,
         Provider<ConfigService> configServiceProvider,
         Provider<GeneralInfoService> generalInfoServiceProvider
         ) {

      this.entityManagerProvider = entityManagerProvider;
      this.authenticatorProvider = authenticatorProvider;
      this.userManagerProvider = userManagerProvider;
      this.configServiceProvider = configServiceProvider;
      this.generalInfoServiceProvider = generalInfoServiceProvider;
   }

   @SuppressWarnings("unchecked")
   @Override
   public void registerSecurityTarget(Class<?>... types) {
      for (Class<?> type : types) {
         if (isGenericTarget(type)) {
            if (!genericTargetConfigurations.containsKey(type)) {
               genericTargetConfigurations.put(type, new ArrayList<SecurityTargetConfiguration>());
               addGenericSecurityTarget(getDefaultConfiguration(type));
            } else {
               throw new RuntimeException("Duplicate configuration for security target: " + type);
            }
         } else {
            targetConfigurations.put((Class<? extends SecurityTarget>) type,
                  new ArrayList<SecurityTargetConfiguration>());
            addSecurityTarget(getDefaultConfiguration(type));
         }
      }
   }

   @Override
   public void registerSecurityTarget(Class<?> target, Securee securee) {
      SecurityTargetConfiguration config = getSecurityTarget(target, securee);
      registerSecurityTarget(config);
   }

   private SecurityTargetConfiguration getSecurityTarget(Class<?> target, Securee securee) {
      if (isGenericTarget(target)) {
         return new SecurityTargetConfiguration(securee, null, (Class<? extends SecurityTarget>) target);
      } else if (isEntitySecurityTarget(target)) {
         return new SecurityTargetConfiguration(securee, (Class<? extends SecurityTarget>) target, null);
      } else
         throw new IllegalArgumentException("Passed argument is no valid security target: " + target.getName()); //$NON-NLS-1$
   }

   @Override
   public void registerSecurityTarget(SecurityTargetConfiguration... configs) {
      for (SecurityTargetConfiguration config : configs) {
         /* validate configuration */
         validateConfiguration(config);

         /* add configuration */
         if (config.isGenericTarget())
            addGenericSecurityTarget(config);
         else
            addSecurityTarget(config);
      }
   }

   /**
    * Validates the configuration.
    * 
    * @param config
    */
   private void validateConfiguration(SecurityTargetConfiguration config) {
      Set<Long> bits = new HashSet<Long>();
      for (Right r : config.getSecuree().getRights()) {
         double log2 = Math.log(r.getBitField()) / Math.log(2);
         if (Math.floor(log2) != log2)
            throw new IllegalSecurityTargetConfigurationException("A bitfield has to be a power of 2.");
         bits.add(r.getBitField());
      }
      if (bits.size() != config.getSecuree().getRights().size())
         throw new IllegalSecurityTargetConfigurationException("Each right has to have a unique bitfield.");
   }

   @Override
   public Class<?> getGenericTargetMarkerById(String id) {
      for (Class<?> type : genericTargetConfigurations.keySet()) {
         String targetId = getGenericTargetIdentifier(type);
         if (id.equals(targetId))
            return type;
      }
      throw new IllegalArgumentException("Could not find any target with id: " + id);
   }

   private Right instantiateRight(Class<? extends Right> rightClass) {
      try {
         return rightClass.newInstance();
      } catch (Exception e) {
         RuntimeException re = new RuntimeException("Could not instantiate right: " + rightClass.getName());
         re.initCause(e);
         throw re;
      }
   }

   /**
    * adds the configuration and adds the default configuration if the target is
    * unknown.
    * 
    * @param config
    */
   private void addSecurityTarget(SecurityTargetConfiguration config) {
      if (!targetConfigurations.containsKey(config.getTarget())) {
         targetConfigurations.put(config.getTarget(), new ArrayList<SecurityTargetConfiguration>());
         addSecurityTarget(getDefaultConfiguration(config.getTarget()));
      }
      targetConfigurations.get(config.getTarget()).add(config);
   }

   /**
    * adds the configuration and adds the default configuration if the target is
    * unknown.
    * 
    * @param config
    */
   private void addGenericSecurityTarget(SecurityTargetConfiguration config) {
      if (!genericTargetConfigurations.containsKey(config.getGenericTarget())) {
         genericTargetConfigurations.put(config.getGenericTarget(), new ArrayList<SecurityTargetConfiguration>());
         addGenericSecurityTarget(getDefaultConfiguration(config.getGenericTarget()));
      }
      genericTargetConfigurations.get(config.getGenericTarget()).add(config);
   }

   /**
    * Creates the default configuration.
    * 
    * @param target
    */
   private SecurityTargetConfiguration getDefaultConfiguration(Class<?> target) {
      return getSecurityTarget(target, new SecurityServiceSecuree());
   }

   @Override
   public boolean isTypeSecured(Class<?> type) {
      return targetConfigurations.containsKey(type) || genericTargetConfigurations.containsKey(type);
   }

   @Override
   public void assertUserLoggedIn() {
      if (null == authenticatorProvider.get().getCurrentUser())
         throw new ViolatedSecurityException("Expected that a user is logged in.");
   }

   @Override
   public void assertRights(Object target, Class<? extends Right>... rights) {
      if (target instanceof Class<?>) {
         /* get targetEntity */
         target = loadGenericTarget((Class<?>) target);
      }

      assertUserLoggedIn();

      if (!(target instanceof SecurityTarget))
         return;

      if (!checkRights((SecurityTarget) target, rights))
         throw new ViolatedSecurityException(target, rights);
   }
   
   @Override
   public void assertActions(Object target, Class<? extends SecurityAction>... actions) {
      if (target instanceof Class<?>) {
         /* get targetEntity */
         target = loadGenericTarget((Class<?>) target);
      }

      assertUserLoggedIn();

      if (!(target instanceof SecurityTarget))
         return;

      if (!checkActions((SecurityTarget) target, actions)) {
         Set<Class<? extends Right>> rights = new HashSet<>();
         for (Class<? extends SecurityAction> a : actions) {
            Collection<Right> ar = instanceRightsFromAction(a);
            for (Right r : ar)
               rights.add(r.getClass());
         }
         throw new ViolatedSecurityException(target, rights.toArray(new Class[] {}));
      }
   }

   @Override
   public boolean checkRights(SecurityTarget target, Class<? extends Right>... rights) {
      return checkRights(target, SecurityServiceSecuree.class, rights);
   }
   
   @Override
   public boolean checkRights(Object target, Class<? extends Right>... rights) {
      if (target instanceof Class<?>) {
         /* get targetEntity */
         target = loadGenericTarget((Class<?>) target);
      }

      assertUserLoggedIn();

      if (!(target instanceof SecurityTarget))
         return false;

      return checkRights((SecurityTarget)target, rights);
   }

   @Override
   public boolean checkRights(SecurityTarget target, Class<? extends Securee> securee,
         Class<? extends Right>... rights) {
      try {
         AuthenticatorService authenticator = authenticatorProvider.get();
         return checkRights(authenticator.getCurrentUser(), target, securee, rights);
      } catch (AuthenticatorRuntimeException e) {
         return false;
      }
   }

   @Override
   public boolean checkRights(User user, Class<?> target, Class<? extends Securee> securee,
         Class<? extends Right>... rights) {
      if (!isGenericTarget(target))
         throw new IllegalArgumentException(target.getName() + " is no valid GenericSecurityTarget");

      /* get targetEntity */
      GenericSecurityTargetEntity securityTargetEntity = loadGenericTarget(target);

      return checkRights(user, securityTargetEntity, securee, rights);
   }

   @Override
   public boolean checkRights(SecurityTarget target, boolean requireInheritance, Class<? extends Securee> securee,
         Class<? extends Right>... rights) {
      try {
         AuthenticatorService authenticator = authenticatorProvider.get();
         return checkRights(authenticator.getCurrentUser(), target, requireInheritance, securee, rights);
      } catch (AuthenticatorRuntimeException e) {
         return false;
      }
   }

   @Override
   public boolean checkRights(User user, SecurityTarget target, Class<? extends Securee> securee,
         Class<? extends Right>... rights) {
      return checkRights(user, target, false, securee, rights);
   }

   @Override
   public boolean checkRights(User user, SecurityTarget target, boolean requireInheritance,
         Class<? extends Securee> securee, Class<? extends Right>... rights) {
      /* load rights */
      Collection<Right> rightsCollection = instanceRights(rights);

      /* return if nothing to check */
      if (rightsCollection.isEmpty())
         return true;

      return testRights(target, user, requireInheritance, securee, rightsCollection);
   }

   @Override
   public boolean checkRights(Class<?> target, Class<? extends Right>... rights) {
      return checkRights(target, SecurityServiceSecuree.class, rights);
   }

   @Override
   public boolean checkRights(Class<?> target, Class<? extends Securee> securee, Class<? extends Right>... rights) {
      try {
         AuthenticatorService authenticator = authenticatorProvider.get();
         return checkRights(authenticator.getCurrentUser(), target, securee, rights);
      } catch (AuthenticatorRuntimeException e) {
         return false;
      }
   }

   @Override
   public boolean checkActions(Class<?> target, Class<? extends SecurityAction>... actions) {
      try {
         AuthenticatorService authenticator = authenticatorProvider.get();
         return checkActions(authenticator.getCurrentUser(), target, actions);
      } catch (AuthenticatorRuntimeException e) {
         return false;
      }
   }

   @Override
   public boolean checkActions(User user, Class<?> target, Class<? extends SecurityAction>... actions) {
      if (!isGenericTarget(target))
         throw new IllegalArgumentException(target.getName() + " is no valid GenericSecurityTarget");

      /* get targetEntity */
      GenericSecurityTargetEntity securityTargetEntity = loadGenericTarget(target);

      return checkActions(user, securityTargetEntity, actions);
   }

   @Override
   public boolean checkActions(SecurityTarget target, Class<? extends SecurityAction>... actions) {
      try {
         AuthenticatorService authenticator = authenticatorProvider.get();
         return checkActions(authenticator.getCurrentUser(), target, actions);
      } catch (AuthenticatorRuntimeException e) {
         return false;
      }
   }

   @Override
   public boolean checkActions(User user, SecurityTarget target, Class<? extends SecurityAction>... actions) {
      for (Class<? extends SecurityAction> action : actions) {
         /* load rights */
         Collection<Right> rightsCollection = instanceRightsFromAction(action);

         /* load securee */
         Class<? extends Securee> securee = getSecureeFromAction(action);

         /* inheritance */
         boolean requireInheritance = getRequireInheritanceFromAction(action);

         /* return if nothing to check */
         if (rightsCollection.isEmpty())
            return true;

         boolean result = testRights(target, user, requireInheritance, securee, rightsCollection);
         if (!result)
            return false;
      }

      return true;
   }

   public boolean testRights(SecurityTarget target, User user, boolean requireInheritance,
         Class<? extends Securee> securee, Collection<Right> rightsCollection) {
      if (null == user)
         return false;

      /* get bitfield */
      long bitField = 0;
      for (Right right : rightsCollection)
         bitField |= right.getBitField();

      /* get securee */
      Securee secureeInstance = instantiateSecuree(securee);

      if (target instanceof HierarchicalSecurityTarget)
         return testRights_hierarchicalTarget((HierarchicalSecurityTarget) target, user, requireInheritance,
               secureeInstance.getSecureeId(), bitField, false);
      return testRights_simpleTarget(target, user, secureeInstance.getSecureeId(), bitField);
   }

   public boolean testRights_hierarchicalTarget(HierarchicalSecurityTarget target, User user,
         boolean requireInheritance, String secureeId, long bitField, boolean inherited) {
      if (user.isSuperUser())
         return true;

      /* obtain acl */
      Acl acl = target.getAcl();

      if (null != acl) {
         /* get user manager */
         UserManagerService userManager = userManagerProvider.get();

         /* loop over ACEs */
         if (null != acl.getAces()) {
            for (Ace ace : acl.getAces()) {
               /*
                * if we are only looking for inherited ace's then discard all others
                */
               if (!(ace instanceof HierarchicalAce))
                  throw new IllegalStateException(
                        "A hierarchical target should only get hierarchical ACEs. " + target.getClass().getName());
               if (inherited && !((HierarchicalAce) ace).isInheritedACE())
                  continue;

               AbstractUserManagerNode folk = ace.getFolk();
               if (null == folk)
                  continue;

               /*
                * as we are using lazy loading as part of tree nodes, hibernate may feel like
                * returning a proxy
                */
               if (folk instanceof HibernateProxy)
                  folk = (AbstractUserManagerNode) ((HibernateProxy) folk).getHibernateLazyInitializer()
                        .getImplementation();

               if (userManager.userInFolk(user, folk)) {
                  /* ace matches, so let's check the rights */
                  AceAccessMap map = ace.getAccessMap(secureeId);
                  if (null == map)
                     continue;

                  /* if deny mapfail if one right is not granted */
                  if (AccessType.DENY.equals(ace.getAccesstype())) {
                     long testField = bitField & map.getAccess();
                     if (0 != testField)
                        return false;
                  } else {
                     /* if require inherited check ace */
                     if (requireInheritance && !((HierarchicalAce) ace).isInheritedACE())
                        continue;

                     /* it is an allow ace .. so lets reduce the rights that are to be checked */
                     bitField ^= (bitField & map.getAccess());
                  }
               }
               if (0 == bitField)
                  return true;
            }
         }
      }

      /* go to parent */
      if (null != target.getParentTarget())
         return testRights_hierarchicalTarget(target.getParentTarget(), user, requireInheritance, secureeId, bitField,
               true);

      return 0 == bitField;
   }

   private Securee instantiateSecuree(Class<? extends Securee> securee) {
      try {
         return securee.newInstance();
      } catch (Exception e) {
         IllegalArgumentException iae = new IllegalArgumentException(
               "Could not instantiate securee: " + securee.getName());
         iae.initCause(e);
         throw iae;
      }
   }

   public boolean testRights_simpleTarget(SecurityTarget target, User user, String secureeId, long bitField) {
      if (user.isSuperUser())
         return true;

      /* obtain acl */
      Acl acl = target.getAcl();

      /* get user manager */
      UserManagerService userManager = userManagerProvider.get();

      /* loop over ACEs */
      if (null != acl && null != acl.getAces()) {
         for (Ace ace : acl.getAces()) {
            AbstractUserManagerNode folk = ace.getFolk();
            if (null == folk)
               continue;

            /*
             * as we are using lazy loading as part of tree nodes, hibernate may feel like
             * returning a proxy
             */
            if (folk instanceof HibernateProxy)
               folk = (AbstractUserManagerNode) ((HibernateProxy) folk).getHibernateLazyInitializer()
                     .getImplementation();

            if (userManager.userInFolk(user, folk)) {
               /* ace matches, so let's check the rights */
               AceAccessMap map = ace.getAccessMap(secureeId);
               if (null == map)
                  continue;

               /* if deny mapfail if one right is not granted */
               if (AccessType.DENY.equals(ace.getAccesstype())) {
                  long testField = bitField & map.getAccess();
                  if (0 != testField)
                     return false;
               } else {
                  /* it is an allow ace .. so lets reduce the rights that are to be checked */
                  bitField ^= (bitField & map.getAccess());
               }
            }
            if (0 == bitField)
               return true;
         }
      }

      return 0 == bitField;
   }

   private Collection<Right> instanceRights(Class<? extends Right>[] rights) {
      Collection<Right> col = new HashSet<Right>();
      try {
         for (Class<? extends Right> right : rights) {
            col.add(right.newInstance());
         }
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
         RuntimeException re = new RuntimeException();
         re.initCause(e);
         throw re;
      }
      return col;
   }

   private Collection<Right> instanceRightsFromAction(Class<? extends SecurityAction> action) {
      Collection<Right> col = new HashSet<Right>();
      try {
         SecurityAction actionInstance = action.newInstance();
         col.addAll(actionInstance.getRights());
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
         RuntimeException re = new RuntimeException();
         re.initCause(e);
         throw re;
      }
      return col;
   }

   private Class<? extends Securee> getSecureeFromAction(Class<? extends SecurityAction> action) {
      try {
         SecurityAction actionInstance = action.newInstance();
         return actionInstance.getSecuree();
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
         RuntimeException re = new RuntimeException();
         re.initCause(e);
         throw re;
      }
   }

   private boolean getRequireInheritanceFromAction(Class<? extends SecurityAction> action) {
      try {
         SecurityAction actionInstance = action.newInstance();
         return actionInstance.requireInheritance();
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
         RuntimeException re = new RuntimeException();
         re.initCause(e);
         throw re;
      }
   }

   @Override
   public GenericSecurityTargetEntity loadGenericTarget(Class<?> type) {
      EntityManager entityManager = entityManagerProvider.get();

      String targetIdentifier = getGenericTargetIdentifier(type);
      GenericSecurityTargetEntity target = loadGenericTargetByIdentifier(targetIdentifier);
      if (null == target)
         throw new IllegalGenericTargetException("Could not find a generic target with target ID: " + targetIdentifier);

      return target;
   }

   @Override
   @QueryByAttribute(where = GenericSecurityTargetEntity__.targetIdentifier)
   public GenericSecurityTargetEntity loadGenericTargetByIdentifier(String targetIdentifier) {
      return null; // warp dynamic finders will do the magic here...
   }

   private String getGenericTargetIdentifier(Class<?> type) {
      if (!isGenericTarget(type))
         throw new IllegalArgumentException();

      GenericSecurityTarget anno = type.getAnnotation(GenericSecurityTarget.class);
      if (!"".equals(anno.alias()))
         return anno.alias();

      return type.getSimpleName();
   }

   @Override
   public boolean isEntitySecurityTarget(Class<?> type) {
      // are we inspecting the searched for interface
      if (type.equals(SecurityTarget.class))
         return true;

      // inspect implemented interfaces
      Class<?>[] implementedInterfaces = type.getInterfaces();

      // inspect interfaces
      for (Class<?> impIFace : implementedInterfaces)
         for (Class<?> iFace : impIFace.getInterfaces())
            return isEntitySecurityTarget(iFace);

      // inspect super classes/interfaces
      if (null != type.getSuperclass())
         return isEntitySecurityTarget(type.getSuperclass());

      return false;
   }

   @Override
   public boolean isGenericTarget(Class<?> type) {
      return type.isAnnotationPresent(GenericSecurityTarget.class);
   }

   @Override
   public GenericSecurityTargetEntity createGenericSecurityTargetEntity(Class<?> type) {
      if (!isGenericTarget(type))
         throw new IllegalArgumentException();

      EntityManager entityManager = entityManagerProvider.get();
      String id = getGenericTargetIdentifier(type);

      GenericSecurityTargetEntity entity = new GenericSecurityTargetEntity();
      entity.setTargetIdentifier(id);

      entityManager.persist(entity);

      return entity;
   }

   @Override
   public GenericSecurityTargetEntity merge(GenericSecurityTargetEntity targetEntity) {
      return entityManagerProvider.get().merge(targetEntity);
   }

   @Override
   public Acl merge(Acl acl) {
      return entityManagerProvider.get().merge(acl);
   }

   @Override
   public Ace merge(Ace ace) {
      return entityManagerProvider.get().merge(ace);
   }

   @Override
   public void persist(Acl acl) {
      entityManagerProvider.get().persist(acl);
   }

   @Override
   public void persist(Ace ace) {
      entityManagerProvider.get().persist(ace);
   }

   @Override
   public void remove(Acl acl) {
      EntityManager em = entityManagerProvider.get();
      em.remove(em.find(acl.getClass(), acl.getId()));
   }

   @Override
   public void remove(Ace ace) {
      /* remove ace from acl and update positions */
      Acl acl = ace.getAcl();
      acl.removeACE(ace);

      /* remove ace */
      EntityManager em = entityManagerProvider.get();
      em.remove(em.find(ace.getClass(), ace.getId()));
   }

   @Override
   public void registerSecuree(Securee securee) {
      for (Securee registeredSecuree : registeredSecurees)
         if (securee.getSecureeId().equals(registeredSecuree.getSecureeId()))
            throw new IllegalArgumentException(
                  "A securee with the " + securee.getSecureeId() + " id has already been registered.");
      registeredSecurees.add(securee);
   }

   @Override
   public Collection<Securee> getAllRegisteredSecurees() {
      return registeredSecurees;
   }

   @Override
   public Collection<Securee> getRegisteredSecureesForTarget(Class<?> target) {
      if (isEntitySecurityTarget(target))
         return getRegisteredSecureesForEntityTarget(target);
      else if (isGenericTarget(target))
         return getRegisteredSecureesForGenericTarget(target);

      throw new IllegalArgumentException("Target is not a security target: " + target.getName());
   }

   private Collection<Securee> getRegisteredSecureesForGenericTarget(Class<?> target) {
      Set<Securee> securees = new HashSet<Securee>();

      if (!genericTargetConfigurations.containsKey(target))
         throw new IllegalArgumentException(target.getName() + " is not a registered generic security target.");

      for (SecurityTargetConfiguration config : genericTargetConfigurations.get(target))
         securees.add(config.getSecuree());

      return securees;
   }

   private Collection<Securee> getRegisteredSecureesForEntityTarget(Class<?> target) {
      Set<Securee> securees = new HashSet<Securee>();

      if (!targetConfigurations.containsKey(target))
         throw new IllegalArgumentException(target.getName() + " is not a registered security target.");

      for (SecurityTargetConfiguration config : targetConfigurations.get(target))
         securees.add(config.getSecuree());

      return securees;
   }

   @Override
   public Ace createACE(Class<?> target) {
      Ace ace = new Ace();
      configureNewACE(ace, target);

      return ace;
   }

   @Override
   public Ace createACE(GenericSecurityTargetEntity entity) {
      Class<?> marker = getGenericTargetMarkerById(entity.getTargetIdentifier());
      Ace ace = new Ace();
      configureNewACE(ace, marker);

      return ace;
   }

   @Override
   public HierarchicalAce createHierarchicalACE(Class<?> target) {
      HierarchicalAce ace = new HierarchicalAce();
      configureNewACE(ace, target);

      return ace;
   }

   private void configureNewACE(Ace ace, Class<?> target) {
      Set<Securee> securees = new HashSet<Securee>();

      /* locate securees */
      if (isGenericTarget(target)) {
         if (!genericTargetConfigurations.containsKey(target))
            throw new IllegalArgumentException(target.getName() + " is not a registered generic security target.");

         for (SecurityTargetConfiguration config : genericTargetConfigurations.get(target))
            securees.add(config.getSecuree());
      } else if (isEntitySecurityTarget(target)) {
         if (!targetConfigurations.containsKey(target))
            throw new IllegalArgumentException(target.getName() + " is not a registered security target.");

         for (SecurityTargetConfiguration config : targetConfigurations.get(target))
            securees.add(config.getSecuree());
      } else
         throw new IllegalArgumentException(target.getName() + " is not a valid security target.");

      /* create access maps */
      for (Securee securee : securees) {
         AceAccessMap accessMap = new AceAccessMap();
         accessMap.setSecuree(securee.getSecureeId());
         ace.addAccessMap(accessMap);
      }
   }

   @Override
   public Collection<Class<?>> getRegisteredGenericSecurityTargets() {
      return genericTargetConfigurations.keySet();
   }

   @Override
   public Collection<Class<? extends SecurityTarget>> getRegisteredSecurityTargets() {
      return targetConfigurations.keySet();
   }

   @Override
   public void assertRights(Collection<User> users, Object target, Class<? extends Right>... rights) {
      if (target instanceof Class<?>) {
         /* get targetEntity */
         target = loadGenericTarget((Class<?>) target);
      }

      final SecurityTarget securityTarget = (SecurityTarget) target;
      Set<User> usersMissingRights = users.stream()
            .filter(owner -> !checkRights(owner, (SecurityTarget) securityTarget, SecurityServiceSecuree.class, rights))
            .collect(toSet());

      if (!usersMissingRights.isEmpty()) {
         throw new ViolatedSecurityException("Users missing rights ("
               + Arrays.stream(rights).collect(Collectors.toSet()) + "): " + usersMissingRights);
      }
   }
   
   @Override
   public List<String> getSupportedSslProtocols() {
      try {
         String[] protocols = SSLContext.getDefault().getSupportedSSLParameters().getProtocols();
         if (null == protocols)
            return Collections.emptyList();
         return Arrays.asList(protocols);
      } catch (Exception e) {
         logger.warn("cannot read supported SSL protocols", e); 
         return Arrays.asList("Unknown (" + ExceptionUtils.getRootCauseMessage(e) + ")");
      }
   }

   @Override
   public List<String> getDefaultSslProtocols() {
      try {
         String[] protocols = SSLContext.getDefault().getDefaultSSLParameters().getProtocols();
         if (null == protocols)
            return Collections.emptyList();
         return Arrays.asList(protocols);
      } catch (Exception e) {
         logger.warn("cannot read default SSL protocols", e); 
         return Arrays.asList("Unknown (" + ExceptionUtils.getRootCauseMessage(e) + ")");
      }
   }

   @Override
   public List<String> getEnabledSslProtocols() {
      try {
         String[] protocols = SSLContext.getDefault().createSSLEngine().getEnabledProtocols();
         if (null == protocols)
            return Collections.emptyList();
         return Arrays.asList(protocols);
      } catch (Exception e) {
         logger.warn("cannot read enabled SSL protocols", e); 
         return Arrays.asList("Unknown (" + ExceptionUtils.getRootCauseMessage(e) + ")");
      }
   }
   
   @Override
   public String getKnownHostsFile(boolean appendFileCheck) {
      Path knownHostsFile = Paths.get(configServiceProvider.get().getConfigFailsafe("security/misc.cf")
            .getString("knownHosts", generalInfoServiceProvider.get().getUserHome(false) + "/.ssh/known_hosts"));
      if (appendFileCheck)
         return RsFileUtils.appendFileCheck(knownHostsFile);

      return knownHostsFile.toAbsolutePath().toString();
   }

}
