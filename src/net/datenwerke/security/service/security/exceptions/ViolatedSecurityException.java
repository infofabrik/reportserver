package net.datenwerke.security.service.security.exceptions;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.tuple.ImmutablePair;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.utils.instancedescription.InstanceDescription;
import net.datenwerke.rs.utils.instancedescription.InstanceDescriptionService;
import net.datenwerke.security.service.security.SecurityModule;
import net.datenwerke.security.service.security.action.SecurityAction;
import net.datenwerke.security.service.security.entities.GenericSecurityTargetEntity;
import net.datenwerke.security.service.security.rights.Right;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class ViolatedSecurityException extends RuntimeException {

   /**
    * 
    */
   private static final long serialVersionUID = 6708769716826141524L;

   @Inject
   private static InstanceDescriptionService instanceDescriptionService;

   @Inject
   private static ConfigService configService;

   public ViolatedSecurityException() {
      super();
   }

   public ViolatedSecurityException(String message) {
      super(message);
   }

   public ViolatedSecurityException(Exception e) {
      super(e);
   }

   public ViolatedSecurityException(String message, Exception e) {
      super(message, e);
   }

   public ViolatedSecurityException(MethodInvocation invocation) {
      this(invocation, ""); //$NON-NLS-1$
   }

   public ViolatedSecurityException(Object target, Class<? extends Right>... rights) {
      super(getMessageFor(target, rights));
   }

   public ViolatedSecurityException(MethodInvocation invocation, String reason) {
      super("Violated security. Execution of method " + //$NON-NLS-1$
            invocation.getMethod().getName() + " in class " + //$NON-NLS-1$
            invocation.getMethod().getDeclaringClass().getName() + "(target: " //$NON-NLS-1$
            + (null != invocation.getThis() ? invocation.getThis().getClass().getName() : "null") + //$NON-NLS-1$
            ") was prohibited. " + reason); //$NON-NLS-1$
   }

   public ViolatedSecurityException(MethodInvocation invocation, Object target, Class<? extends Right>[] rights,
         Class<? extends SecurityAction>[] actions) {
      super(getMessageFor(invocation, target, rights, actions));
   }

   private static String getMessageFor(MethodInvocation invocation, Object target, Class<? extends Right>[] rights,
         Class<? extends SecurityAction>[] actions) {

      StringBuilder sb = new StringBuilder();
      sb.append("Violated security. ");

      if (isHideViolatedSecurityExceptionDetails())
         return sb.toString();

      Long id = null;
      String type = null;

      if (target instanceof Class) {
         type = ((Class<?>) target).getSimpleName();
      } else {
         ImmutablePair<Long, String> targetInfo = getInfoForTarget(target);
         id = targetInfo.getLeft();
         type = targetInfo.getRight();
      }

      if (rights.length != 0) {
         sb.append("Missing permissions (");
         sb.append(getMessageFor(rights));
         sb.append(") ");
      }

      if (actions.length != 0) {
         sb.append("Missing actions (");
         sb.append(getMessageFor(actions));
         sb.append(") ");
      }

      sb.append("for object type ").append(type);

      if (null != id)
         sb.append(" with id ").append(id);

      sb.append(".\n\n");

      sb.append(getMessageFor(invocation));

      return sb.toString();
   }

   private static boolean isHideViolatedSecurityExceptionDetails() {
      return configService.getConfigFailsafe(SecurityModule.CONFIG_FILE)
            .getBoolean(SecurityModule.ERROR_MESSAGE_LEVEL_PROPERTY, false);
   }

   private static String getMessageFor(Object target, Class<? extends Right>[] rights) {

      StringBuilder sb = new StringBuilder();
      sb.append("Violated security. ");

      if (isHideViolatedSecurityExceptionDetails())
         return sb.toString();

      Long id = null;
      String type = null;

      if (target instanceof Class) {
         type = ((Class<?>) target).getSimpleName();
      } else {
         ImmutablePair<Long, String> targetInfo = getInfoForTarget(target);
         id = targetInfo.getLeft();
         type = targetInfo.getRight();
      }

      if (rights.length != 0) {
         sb.append("Missing permissions (");
         sb.append(getMessageFor(rights));
         sb.append(") ");
      }

      sb.append(" for object type ").append(type);

      if (null != id)
         sb.append(" with id ").append(id);

      sb.append(".");

      return sb.toString();
   }

   private static ImmutablePair<Long, String> getInfoForTarget(Object target) {
      String type = null != target ? target.getClass().getSimpleName() : "null";
      Long id = null;

      try {
         InstanceDescription description = instanceDescriptionService.getDescriptionFor(target);
         if (null != description) {
            type = description.getObjectType();
         }
      } catch (Exception e) {
      }

      if (target instanceof AbstractNode)
         id = ((AbstractNode) target).getId();

      if (target instanceof GenericSecurityTargetEntity) {
         type = ((GenericSecurityTargetEntity) target).getTargetIdentifier();
      }

      return new ImmutablePair<Long, String>(id, type);
   }

   private static StringBuilder getMessageFor(MethodInvocation invocation) {

      StringBuilder sb = new StringBuilder();
      sb.append("Method ");
      sb.append(invocation.getMethod().getName());
      sb.append(" in class ");
      sb.append(invocation.getMethod().getDeclaringClass().getSimpleName());
      sb.append("(target: ");
      sb.append((null != invocation.getThis() ? invocation.getThis().getClass().getSimpleName() : "null"));
      sb.append(") was prohibited. ");

      return sb;
   }

   private static StringBuilder getMessageFor(Class<?>[] objs) {
      StringBuilder sb = new StringBuilder();
      boolean first = true;
      for (Class<?> o : objs) {
         if (first)
            first = false;
         else
            sb.append(", ");
         sb.append(o.getSimpleName());
      }

      return sb;
   }
}
