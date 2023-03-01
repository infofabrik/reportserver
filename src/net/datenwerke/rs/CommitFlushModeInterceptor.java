package net.datenwerke.rs;

import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;

public class CommitFlushModeInterceptor implements MethodInterceptor {

   @Inject
   private Provider<EntityManager> entityManagerProvider;

   public Object invoke(MethodInvocation invocation) throws Throwable {
      final EntityManager entityManager = entityManagerProvider.get();
      final FlushModeType currentFlushMode = entityManager.getFlushMode();
      try {
         entityManager.setFlushMode(FlushModeType.COMMIT);
         return invocation.proceed();
      } finally {
         entityManager.setFlushMode(currentFlushMode);
      }
   }

}
