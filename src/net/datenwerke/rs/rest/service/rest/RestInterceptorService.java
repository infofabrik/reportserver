package net.datenwerke.rs.rest.service.rest;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import org.aopalliance.intercept.ConstructorInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.glassfish.hk2.api.Filter;
import org.glassfish.hk2.api.InterceptionService;
import org.glassfish.hk2.utilities.BuilderHelper;

import com.google.common.collect.Lists;

import net.datenwerke.rs.rest.service.rest.annotations.RestAuthentication;

public class RestInterceptorService implements InterceptionService {

   @Inject
   private Provider<RestAuthenticationCheckInterceptor> interceptorProvider;
   
   @Override
   public List<ConstructorInterceptor> getConstructorInterceptors(Constructor<?> constructor) {
      return Lists.newArrayList();
   }
   
   @Override
   public Filter getDescriptorFilter() {
      return BuilderHelper.allFilter();
   }

   // based on https://github.com/mycom-int/jersey-guice-aop
   @Override
   public List<MethodInterceptor> getMethodInterceptors(Method method)
   {
      List<MethodInterceptor> ret = new ArrayList<>();
      if (method.isAnnotationPresent(RestAuthentication.class))
         ret.add(interceptorProvider.get());
      return ret;
   }
}
