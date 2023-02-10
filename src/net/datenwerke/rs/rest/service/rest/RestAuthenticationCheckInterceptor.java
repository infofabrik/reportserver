package net.datenwerke.rs.rest.service.rest;

import java.lang.reflect.Method;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.rs.core.server.reportexport.helper.ApiKeyHelper;
import net.datenwerke.rs.rest.service.rest.annotations.RestAuthenticationBypass;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.usermanager.entities.User;

public class RestAuthenticationCheckInterceptor implements MethodInterceptor {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());
   
   @Inject
   private Provider<AuthenticatorService> authenticatorServiceProvider;
   
   @Inject
   private Provider<ApiKeyHelper> apiKeyHelperProvider;
   
   public Object invoke(MethodInvocation invocation) throws Throwable {
      try {
         if (invocation.getMethod().isAnnotationPresent(RestAuthenticationBypass.class))
            return invocation.proceed();
         
         Method getRequest = invocation.getThis().getClass().getDeclaredMethod("getRequest");
         HttpServletRequest request = (HttpServletRequest) getRequest.invoke(invocation.getThis());
         
         final ApiKeyHelper apiKeyHelper = apiKeyHelperProvider.get();
         String apikey = apiKeyHelper.readApiKeyFromBearer(request);
         if (null == apikey)
            apikey = request.getParameter("apikey");
         final User user = apiKeyHelper.getUser(request, apikey);
         if (null != user) {
            try {
               authenticatorServiceProvider.get().setAuthenticatedInThread(user.getId());
               return invocation.proceed();
            } finally {
               authenticatorServiceProvider.get().logoffUserInThread();
            }
         } else {
            return Response.status(Status.UNAUTHORIZED).build();
         }
         
      } catch (ViolatedSecurityException e) {
         logger.error(ExceptionUtils.getRootCauseMessage(e), e);
         return Response.status(Status.UNAUTHORIZED).build();
      } catch (Exception e) {
         if (ExceptionUtils.getRootCause(e) instanceof ViolatedSecurityException) {
            logger.error(ExceptionUtils.getRootCauseMessage(e), e);
            return Response.status(Status.UNAUTHORIZED).build();
         }
         logger.error(ExceptionUtils.getRootCauseMessage(e), e);
         return Response.status(Status.INTERNAL_SERVER_ERROR).build();
      }
   }
   
}
