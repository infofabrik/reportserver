package net.datenwerke.security.service.security;

import java.util.Collection;

import net.datenwerke.security.service.security.action.SecurityAction;
import net.datenwerke.security.service.security.annotation.GenericSecurityTarget;
import net.datenwerke.security.service.security.entities.Ace;
import net.datenwerke.security.service.security.entities.Acl;
import net.datenwerke.security.service.security.entities.GenericSecurityTargetEntity;
import net.datenwerke.security.service.security.entities.HierarchicalAce;
import net.datenwerke.security.service.security.rights.Right;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * Brings security to ReportServer.
 */
public interface SecurityService {

   /**
    * Registers the given {@link Securee} with the system
    * 
    * @param securee The {@link Securee}
    */
   void registerSecuree(Securee securee);

   /**
    * Returns a {@link Collection} of all registered {@link Securee}s
    * 
    * @return A {@link Collection} of {@link Securee}s
    */
   Collection<Securee> getAllRegisteredSecurees();

   /**
    * Allows a module to register one or more security targets.
    * 
    * @param configs One or more instances of {@link SecurityTargetConfiguration}
    */
   void registerSecurityTarget(SecurityTargetConfiguration... configs);

   /**
    * Registers a security target identified by the given {@link Class} and
    * {@link Securee}
    * 
    * @param type    The {@link Class}
    * @param securee The {@link Securee}
    */
   void registerSecurityTarget(Class<?> type, Securee securee);

   /**
    * Registers a new type as a security target adding only the default rights.
    * 
    * @param types One or more {@link Class}es to be registered
    */
   void registerSecurityTarget(Class<?>... types);

   /**
    * Returns a {@link Collection} of {@link Securee}s matching the given
    * <i>target</i>
    * 
    * @param target A {@link Class} defining the type of the target
    * @return A {@link Collection} of {@link Securee}s
    */
   Collection<Securee> getRegisteredSecureesForTarget(Class<?> target);

   /**
    * Returns a {@link Collection} of {@link Class}es of registered generic
    * security targets
    * 
    * @return A {@link Collection} of {@link Class}es
    */
   Collection<Class<?>> getRegisteredGenericSecurityTargets();

   /**
    * Returs a {@link Collection} of all registered {@link SecurityTarget}s
    * 
    * @return A {@link Collection} of {@link SecurityTarget}s
    */
   Collection<Class<? extends SecurityTarget>> getRegisteredSecurityTargets();

   /**
    * Tests whether the {@link SecurityTarget} has the given {@link Right}s or not.
    * 
    * @param target The {@link SecurityTarget}
    * @param rights A list of {@link Right}s to test
    * @return true if the tests passed; false otherwise
    */
   boolean checkRights(SecurityTarget target, Class<? extends Right>... rights);

   /**
    * Tests whether the target has the specified rights.
    * 
    * @param target  The {@link SecurityTarget}
    * @param rights  A list of {@link Right}s to test
    * @param securee The {@link Securee}
    * @return true if the tests passed; false otherwise
    */
   boolean checkRights(SecurityTarget target, Class<? extends Securee> securee, Class<? extends Right>... rights);

   /**
    * Tests whether the given {@link SecurityAction}s are allowed to be performed
    * by the given {@link SecurityTarget} or not.
    * 
    * @param target  The {@link SecurityTarget}
    * @param actions A list of {@link SecurityAction}s
    * @return true if the tests passed; false otherwise
    */
   boolean checkActions(SecurityTarget target, Class<? extends SecurityAction>... actions);

   /**
    * Tests whether the target has the specified rights.
    * 
    * @param user    The {@link User}
    * @param target
    * @param securee
    * @param rights
    */
   boolean checkRights(User user, SecurityTarget target, Class<? extends Securee> securee,
         Class<? extends Right>... rights);

   boolean checkRights(User user, SecurityTarget target, boolean requireInheritance, Class<? extends Securee> securee,
         Class<? extends Right>... rights);

   boolean checkRights(SecurityTarget target, boolean requireInheritance, Class<? extends Securee> securee,
         Class<? extends Right>... rights);

   boolean checkActions(User user, SecurityTarget target, Class<? extends SecurityAction>... actions);

   /**
    * Tests whether the target has the specified rights.
    * 
    * @param target
    * @param rights
    */
   boolean checkRights(Class<?> target, Class<? extends Securee> securee, Class<? extends Right>... rights);

   boolean checkRights(Class<?> target, Class<? extends Right>... rights);

   boolean checkActions(Class<?> target, Class<? extends SecurityAction>... actions);

   boolean checkRights(User user, Class<?> target, Class<? extends Securee> securee, Class<? extends Right>... rights);

   boolean checkActions(User user, Class<?> target, Class<? extends SecurityAction>... actions);

   /**
    * Tests whether the {@link Class} has been registered.
    * 
    * @param type The {@link Class} to test
    * @return true if the {@link Class} has been registered; false otherwise
    */
   boolean isTypeSecured(Class<?> type);

   /**
    * Tests if the given {@link Class} is a {@link GenericSecurityTarget}
    * 
    * @param type The {@link Class} to test
    * @return true if the given {@link Class} is a {@link GenericSecurityTarget};
    *         false otherwise
    */
   boolean isGenericTarget(Class<?> type);

   /**
    * Tests if the given {@link Class} is a {@link SecurityTarget}
    * 
    * @param type The {@link Class} to test
    * @return true if the given {@link Class} is a {@link SecurityTarget}; false
    *         otherwise
    */
   boolean isEntitySecurityTarget(Class<?> type);

   /**
    * Creates and returns a new instance of {@link GenericSecurityTargetEntity}
    * with the generic target identifier of the given {@link Class}.
    * 
    * @param type The {@link Class} to use the generic target identifier from
    * @return A new instance of {@link GenericSecurityTargetEntity}
    */
   GenericSecurityTargetEntity createGenericSecurityTargetEntity(Class<?> type);

   /**
    * Merges the given {@link GenericSecurityTargetEntity} with the representation
    * in the database and returns the result.
    * 
    * @param targetEntity The {@link GenericSecurityTargetEntity} with the new data
    * @return The merged {@link GenericSecurityTargetEntity}
    */
   GenericSecurityTargetEntity merge(GenericSecurityTargetEntity targetEntity);

   /**
    * Loads a {@link GenericSecurityTargetEntity} by the generic target identifier
    * of the given {@link Class}
    * 
    * @param type The {@link Class} to get the generic target identifier
    * @return The loaded {@link GenericSecurityTargetEntity}
    */
   GenericSecurityTargetEntity loadGenericTarget(Class<?> type);

   /**
    * Loads a {@link GenericSecurityTargetEntity} by the given generic target
    * identifier
    * 
    * @param id The generic target identifier
    * @return The loaded {@link GenericSecurityTargetEntity}
    */
   GenericSecurityTargetEntity loadGenericTargetByIdentifier(String id);

   /**
    * Returns the corresponding marker class.
    * 
    * @param id The id to identify the marker class
    * @return The marker class
    * @see GenericSecurityTargetMarker
    * @see GenericSecurityTarget
    */
   Class<?> getGenericTargetMarkerById(String id);

   /**
    * Persists the given {@link Acl}.
    * 
    * @param acl The {@link Acl} to persist
    */
   void persist(Acl acl);

   /**
    * Merges the given {@link Acl} with the representation in the database and
    * returns the result.
    * 
    * @param acl The {@link Acl} with the new data
    * @return The merged {@link Acl}
    */
   Acl merge(Acl acl);

   /**
    * Removes the given {@link Acl} from the database.
    * 
    * @param acl The {@link Acl} to remove
    */
   void remove(Acl acl);

   /**
    * Persists the given {@link Ace}
    * 
    * @param ace The {@link Ace} to persist
    */
   void persist(Ace ace);

   /**
    * Merges the given {@link Ace} with the representation in the database and
    * returns the result.
    * 
    * @param ace The {@link Ace} with the new data
    * @return The merged {@link Ace}
    */
   Ace merge(Ace ace);

   /**
    * Removes the given {@link Ace} from the database
    * 
    * @param ace The {@link Ace} to remove from the database
    */
   void remove(Ace ace);

   /**
    * Creates a new {@link Ace} and configures it with the given target
    * 
    * @param target The target {@link Class}
    * @return The newly created {@link Ace}
    */
   Ace createACE(Class<?> target);

   /**
    * Creates a new {@link Ace} and configures it with the given target
    * 
    * @param entity A {@link GenericSecurityTargetEntity} holding the target marker
    *               id
    * @return The newly created {@link Ace}
    */
   Ace createACE(GenericSecurityTargetEntity entity);

   /**
    * Creates a new {@link HierarchicalAce} and configures it with the given target
    * 
    * @param target The target {@link Class}
    * @return The newly created {@link HierarchicalAce}
    */
   HierarchicalAce createHierarchicalACE(Class<?> target);

   void assertRights(Object target, Class<? extends Right>... rights);

   void assertActions(Object target, Class<? extends SecurityAction>... actions);

   void assertRights(Collection<User> users, Object target, Class<? extends Right>... rights);

   void assertUserLoggedIn();

}
