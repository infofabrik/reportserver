package net.datenwerke.security.service.security.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.annotation.UpdateOwner;
import net.datenwerke.security.service.security.interfaces.Owneable;
import net.datenwerke.security.service.usermanager.entities.User;

public class UpdateOwnerInformationInterceptor implements MethodInterceptor {

	@Inject
	private Provider<AuthenticatorService> authenticatorProvider;
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		/* get annotation */
		UpdateOwner updateAnnotation = invocation.getMethod().getAnnotation(UpdateOwner.class);
		if(null == updateAnnotation)
			throw new IllegalStateException("This should actually never happen."); //$NON-NLS-1$
		
		/* get correct argument */
		Object argument = getArgumentByName(invocation, updateAnnotation.name());

		if(null == argument)
			throw new IllegalArgumentException("argument should not be null."); //$NON-NLS-1$
		
		if(! (argument instanceof Owneable))
			throw new IllegalArgumentException(argument.getClass().getName() + " does not implement Owneable."); //$NON-NLS-1$
		
		/* get current user */
		try{
			User currentUser = authenticatorProvider.get().getCurrentUser();
			((Owneable)argument).setOwner(currentUser);
		} catch(Exception e){
			// TODO clever error handling
		}
		
		return invocation.proceed();
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
		throw new IllegalArgumentException("Could not find named Parameter: " + parameterName + " on method " + method.getName() + ". Are you missing an @Named annotation?"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	
}
