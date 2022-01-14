package net.datenwerke.security.service.eventlogger.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;

import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.AfterMergeEntityEvent;
import net.datenwerke.security.service.eventlogger.jpa.MergeEntityEvent;

public class MergeEntityEventInterceptor implements MethodInterceptor {

   @Inject
   private EventBus eventBus;

   @Override
   public Object invoke(MethodInvocation method) throws Throwable {
      if (1 != method.getArguments().length)
         throw new IllegalArgumentException("Excepted exactly one argument");

      Object entity = method.getArguments()[0];

      eventBus.fireEvent(new MergeEntityEvent(entity));

      Object returnValue = method.proceed();

      eventBus.fireEvent(new AfterMergeEntityEvent(returnValue));

      return returnValue;
   }
}
