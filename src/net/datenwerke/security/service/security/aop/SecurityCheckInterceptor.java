package net.datenwerke.security.service.security.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.interfaces.NullIndicatorInterface;
import net.datenwerke.rs.utils.reflection.ProxyUtils;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.Securee;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.action.SecurityAction;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.BypassMethod;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.ReturnObjectValidation;
import net.datenwerke.security.service.security.annotation.ReturnObjectValidation.Mode;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.security.rights.Right;
import net.datenwerke.security.service.usermanager.entities.User;
import net.datenwerke.treedb.service.treedb.AbstractNode;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

/**
 * 
 *
 */
public class SecurityCheckInterceptor implements MethodInterceptor {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	@Inject
	private SecurityService securityService;
	
	@Inject
	private DtoService dtoGenerator;
	
	@Inject
	private ProxyUtils cgLibUtils;
	
	@Inject
	private Injector injector;
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		SecurityChecked secAnnotation = getSecurityAnnotation(invocation);
		if(null == secAnnotation)
			throw new ViolatedSecurityException("Could not find SecurityChecked annotation"); //$NON-NLS-1$
		
		try{
			if(! isBypass(secAnnotation, invocation)){
				/* certificate */
				if(secAnnotation.certificateRequired())
					checkCertificate(invocation);
				
				/* login */
				if(secAnnotation.loginRequired())
					checkLogin(invocation);
				
				if(secAnnotation.superUserRequired())
					checkSuperuser(invocation);
				
				/* test generic targets */
				checkGenericTargets(secAnnotation,invocation);
				
				/* test parameters */
				checkArguments(secAnnotation, invocation);
			}
		} catch(ViolatedSecurityException e){
			return handleSecurityViolation(e, secAnnotation, invocation);
		} catch(Exception e){
			logger.warn( e.getMessage(), e);
			// TODO should be logged instead
			
			ViolatedSecurityException vse = new ViolatedSecurityException();
			vse.initCause(e);
			return handleSecurityViolation(vse, secAnnotation, invocation);
		}
		
		/* should we ignore the method and simply return a boolean? */
		if(secAnnotation.returnBooleanOnCheck()){
			/* test that method returns boolean */
			if(! methodReturnsBoolean(invocation.getMethod()))
				throw new IllegalArgumentException(invocation.getMethod().getName() + " does not return boolean"); //$NON-NLS-1$
			
			return true;
		}
		
		/* so far we have not detected any security violation, so let's proceed with the method call */
		Object returnObject = invocation.proceed();
		
		/* validate returned object */
		returnObject = returnObjectValidation(invocation, secAnnotation, returnObject); 
		
		/* return the filtered object */
		return returnObject;
	}


	/**
	 * Handles a security breach.
	 * 
	 * <p>
	 * Tests whether or not to swallow the exception and return a
	 * boolean or some object or whether to throw exception</p>
	 * 
	 *  
	 * @param e
	 * @param secAnnotation
	 * @param invocation
	 * @throws Throwable
	 */
	private Object handleSecurityViolation(Throwable e, SecurityChecked secAnnotation, MethodInvocation invocation) throws Throwable {
		/* gather flags */
		boolean returnBoolean = secAnnotation.returnBooleanOnCheck();
		boolean returnFalseOnFailure = secAnnotation.returnFalseOnFailure();
		boolean returnObjectOnFailure = secAnnotation.returnObjectInstanceOnFailure();
		boolean returnNullOnFailure = secAnnotation.returnNullOnFailure();
		
		if(returnBoolean || returnFalseOnFailure){
			/* test that method returns boolean */
			if(! methodReturnsBoolean(invocation.getMethod()))
				throw new IllegalArgumentException(invocation.getMethod().getName() + " does not return boolean"); //$NON-NLS-1$
			
			return false;
		} else if(returnNullOnFailure){
			return null;
		} else if(returnObjectOnFailure){
			/* get return type */
			Class<?> returnType = getReturnTypeOnFailure(secAnnotation, invocation);
			
			/* create object instance and return that */
			Object instance = returnType.newInstance();

			return instance;
		} else {
			throw e;
		}
	}


	@SuppressWarnings("unchecked")
	private Object returnObjectValidation(MethodInvocation invocation, SecurityChecked secAnnotation, Object returnObject) throws ViolatedSecurityException {
		for(ReturnObjectValidation validationAnno : secAnnotation.returnObjectValidation()){
			/* differentiate between collections and normal objects */
			if(returnObject instanceof Collection){
				returnObject = returnObjectValidationWithCollection(invocation, secAnnotation, validationAnno, (Collection)returnObject);
				
				if(((Collection)returnObject).isEmpty())
					return returnObject;
			} else {
				returnObject = returnObjectValidationWithObject(invocation, secAnnotation, validationAnno, returnObject);
			
				if(null == returnObject)
					return null;
			}
		}
		return returnObject;
	}


	private Object returnObjectValidationWithObject(MethodInvocation invocation, 
			SecurityChecked secAnnotation, ReturnObjectValidation validationAnno, Object returnObject) throws ViolatedSecurityException {
		/* gather flags */
		Mode mode = validationAnno.mode();
		
		/* load security target */
		SecurityTarget securityTarget = null;
		if(validationAnno.isDto())
			securityTarget = (SecurityTarget) dtoGenerator.loadPoso(returnObject);
		else if(returnObject instanceof SecurityTarget)
			securityTarget = (SecurityTarget) returnObject;
		else
			throw new IllegalArgumentException("Return Object of type " + returnObject.getClass().getName() + " is no SecurityTarget. Did you forget to specify isDto?"); //$NON-NLS-1$ //$NON-NLS-2$
		
		/* run checks on security target */
		for(RightsVerification rightsVerification : validationAnno.verify()){
			if(! checkRights((SecurityTarget) securityTarget, rightsVerification.securee(), rightsVerification.rights(), rightsVerification.actions())){
				if(mode.equals(Mode.FILTER))
					returnObject = null;
				else
					throw new ViolatedSecurityException(invocation, securityTarget, rightsVerification.rights(), rightsVerification.actions());
				
				break;
			}
		}
		
		return returnObject;
	}


	@SuppressWarnings("unchecked")
	private Object returnObjectValidationWithCollection(MethodInvocation invocation, 
			SecurityChecked secAnnotation,
			ReturnObjectValidation validationAnno, Collection returnObject) throws ViolatedSecurityException {
		/* gather flags */
		Mode mode = validationAnno.mode();
		
		/* loop over collection */
		Iterator iterator = returnObject.iterator();
		while(iterator.hasNext()){
			Object nextTarget = iterator.next();
			
			/* load security target */
			SecurityTarget securityTarget = null;
			if(validationAnno.isDto())
				securityTarget = (SecurityTarget) dtoGenerator.loadPoso(nextTarget);
			else if(returnObject instanceof SecurityTarget)
				securityTarget = (SecurityTarget) nextTarget;
			else
				throw new IllegalArgumentException("Return Object of type " + nextTarget.getClass().getName() + " is no SecurityTarget. Did you forget to specify isDto?"); //$NON-NLS-1$ //$NON-NLS-2$
			
			/* run checks on security target */
			try{
				for(RightsVerification rightsVerification : validationAnno.verify())
					if(! checkRights((SecurityTarget) securityTarget, rightsVerification.securee(), rightsVerification.rights(), rightsVerification.actions()))
						throw new ViolatedSecurityException(invocation);
				
				/* should we run checks on parent object */
				if(validationAnno.parentChecks().length > 0 && securityTarget instanceof AbstractNode){
					AbstractNode parentTarget = ((AbstractNode)securityTarget).getParent();
					if(null == parentTarget)
						throw new ViolatedSecurityException(invocation, "The parent is null"); //$NON-NLS-1$
					if(!(parentTarget instanceof SecurityTarget))
						throw new ViolatedSecurityException(invocation, "The parent: " + (parentTarget.getClass().getName()) + " is no SecurityTarget"); //$NON-NLS-1$ //$NON-NLS-2$
					
					for(RightsVerification rightsVerification : validationAnno.parentChecks())
						if(! checkRights((SecurityTarget) parentTarget, rightsVerification.securee(), rightsVerification.rights(), rightsVerification.actions()))
							throw new ViolatedSecurityException(invocation);
				}
			} catch (ViolatedSecurityException e){
				if(mode.equals(Mode.FILTER))
					iterator.remove();
				else
					throw e;
			}
		}
		
		return returnObject;
	}


	private Class<?> getReturnTypeOnFailure(SecurityChecked secAnnotation,
			MethodInvocation invocation) {
		if(! secAnnotation.returnTypeOnFailue().equals(NullIndicatorInterface.class))
			return secAnnotation.returnTypeOnFailue();
		
		/* get method's return type */
		Method method = invocation.getMethod();
		Class<?> returnType = method.getReturnType();
		
		/* map certain interfaces */
		if(returnType.equals(Collection.class))
			return ArrayList.class;
		if(returnType.equals(List.class))
			return ArrayList.class;
		if(returnType.equals(Set.class))
			return HashSet.class;
		if(returnType.equals(Map.class))
			return HashMap.class;
		
		/* use methods return type */
		return returnType;
	}


	/**
	 * Loops over ParameterVerification annotations.
	 *  
	 * @param secAnnotation
	 * @param invocation
	 * @throws ViolatedSecurityException 
	 */
	@SuppressWarnings("unchecked")
	private void checkArguments(SecurityChecked secAnnotation, MethodInvocation invocation) throws ViolatedSecurityException {
		for(ArgumentVerification verificationAnno : secAnnotation.argumentVerification()){
			String argumentName = verificationAnno.name();
			Object securityTarget = getArgumentByName(invocation, argumentName);

			/* do we have to convert a dto */
			if(securityTarget instanceof Collection){
				for(Object realTarget : (Collection)securityTarget){
					checkRightsOnArgumentObject(verificationAnno, realTarget, invocation);
				}
			} else {
				checkRightsOnArgumentObject(verificationAnno, securityTarget, invocation);
			}
			
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private void checkRightsOnArgumentObject(ArgumentVerification verificationAnno, Object securityTarget, MethodInvocation invocation) throws ViolatedSecurityException{
		if(null == securityTarget)
			throw new ViolatedSecurityException(invocation, "Security target may not be null"); //$NON-NLS-1$
		
		if(verificationAnno.isDto())
			securityTarget = dtoGenerator.loadPoso(securityTarget);
		
		if(null == securityTarget)
			throw new ViolatedSecurityException(invocation, "Security target may not be null"); //$NON-NLS-1$

		if(! (securityTarget instanceof SecurityTarget))
			throw new ViolatedSecurityException(invocation, securityTarget.getClass().getName() + " is no valid security target."); //$NON-NLS-1$
		
		/* run checks on security target */
		for(RightsVerification rightsVerification : verificationAnno.verify()){
			if(! checkRights((SecurityTarget) securityTarget, rightsVerification.securee(), rightsVerification.rights(), rightsVerification.actions())){
				throw new ViolatedSecurityException(invocation, (SecurityTarget)securityTarget, 
						rightsVerification.rights(), rightsVerification.actions());
			}
		}
		
		/* should we run checks on parent object */
		if(verificationAnno.parentChecks().length > 0 && securityTarget instanceof AbstractNode){
			AbstractNode parentTarget = ((AbstractNode)securityTarget).getParent();
			if(null == parentTarget)
				throw new ViolatedSecurityException(invocation, "The parent is null"); //$NON-NLS-1$
			if(!(parentTarget instanceof SecurityTarget))
				throw new ViolatedSecurityException(invocation, "The parent: " + (parentTarget.getClass().getName()) + " is no SecurityTarget"); //$NON-NLS-1$ //$NON-NLS-2$
			
			for(RightsVerification rightsVerification : verificationAnno.parentChecks()){
				if(! checkRights((SecurityTarget) parentTarget, rightsVerification.securee(), rightsVerification.rights(), rightsVerification.actions())){
					throw new ViolatedSecurityException(invocation);
				}
			}
		}
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

	private void checkGenericTargets(SecurityChecked secAnnotation,
			MethodInvocation invocation) throws ViolatedSecurityException {
		for(GenericTargetVerification genercicTargetAnno : secAnnotation.genericTargetVerification()){
			Class<?> target = genercicTargetAnno.target();
			
			for(RightsVerification verificationAnno : genercicTargetAnno.verify()){
				if(! checkRights(target, verificationAnno.securee(), verificationAnno.rights(), verificationAnno.actions()))
					throw new ViolatedSecurityException(invocation, target, verificationAnno.rights(), verificationAnno.actions());
			}
		}
	}

	private boolean checkRights(SecurityTarget target, Class<? extends Securee> securee, Class<? extends Right>[] rights, Class<? extends SecurityAction>[] actions) {
		boolean rightsCheck = securityService.checkRights(target, securee, rights);
		boolean actionsCheck = securityService.checkActions(target, actions);
		return rightsCheck && actionsCheck;
	}
	
	private boolean checkRights(Class<?> target, Class<? extends Securee> securee, Class<? extends Right>[] rights, Class<? extends SecurityAction>[] actions) {
		boolean rightsCheck = securityService.checkRights(target, securee, rights);
		boolean actionsCheck = securityService.checkActions(target, actions);
		return rightsCheck && actionsCheck;
	}


	private boolean methodReturnsBoolean(Method method) {
		Class<?> returnType = method.getReturnType();
		return returnType.equals(Boolean.class) || returnType.equals(boolean.class);
	}

	private boolean isBypass(SecurityChecked secAnnotation,
			MethodInvocation invocation) {
		if(secAnnotation.bypass())
			return true;
		String methodName = invocation.getMethod().getName();
		for(BypassMethod bypassMethod : secAnnotation.bypassMethods()){
			if(methodName.equals(bypassMethod.value()))
				return true;
		}
		
		/* bypass inherited ? */
		Object target = invocation.getThis();
		if(secAnnotation.bypassInheritedMethods() && null != target){
			Class<?> targetClass = invocation.getThis().getClass();
			if(isInheritedMethod(invocation.getMethod().getDeclaringClass(), targetClass))
				return true;
		}
		
		return false;
	}

	private boolean isInheritedMethod(Class<?> declaringClass, Class<?> targetClass) {
		if( cgLibUtils.compareClasses(declaringClass, targetClass) )
			return false;
		return declaringClass.isAssignableFrom(targetClass);
	}
	
	private void checkSuperuser(MethodInvocation invocation) throws ViolatedSecurityException {
		AuthenticatorService authenticatorService = null;
		
		/* assert the user is authenticated */
		try{
			authenticatorService = injector.getInstance(AuthenticatorService.class);
		} catch(Exception e){
			ViolatedSecurityException vse = new ViolatedSecurityException(invocation);
			vse.initCause(e);
			throw vse;
		} 
		
		User user = authenticatorService.getCurrentUser();
		if(user == null || ! user.isSuperUser())
			throw new ViolatedSecurityException(invocation);
	}

	private void checkLogin(MethodInvocation invocation) throws ViolatedSecurityException {
		AuthenticatorService authenticatorService = null;
		
		/* assert the user is authenticated */
		try{
			authenticatorService = injector.getInstance(AuthenticatorService.class);
		} catch(Exception e){
			ViolatedSecurityException vse = new ViolatedSecurityException(invocation);
			vse.initCause(e);
			throw vse;
		} 
		
		if(! authenticatorService.isAuthenticated())
			throw new ViolatedSecurityException(invocation);
		
	}

	private void checkCertificate(MethodInvocation invocation) {
		
	}

	private SecurityChecked getSecurityAnnotation(MethodInvocation invocation){
		Method method = invocation.getMethod();
		if(method.isAnnotationPresent(SecurityChecked.class)) {
			SecurityChecked anno = invocation.getMethod().getAnnotation(SecurityChecked.class);
			if(NullIndicatorInterface.class.equals(anno.location()))
				return anno;
			else if(anno.location().isAnnotationPresent(SecurityChecked.class))
				return anno.location().getAnnotation(SecurityChecked.class);
		}

		if(null != invocation.getThis()){
			Class<?> clazz = invocation.getThis().getClass(); 
			SecurityChecked anno = clazz.getAnnotation(SecurityChecked.class);
			if(NullIndicatorInterface.class.equals(anno.location()))
				return anno;
			else if(anno.location().isAnnotationPresent(SecurityChecked.class))
				return anno.location().getAnnotation(SecurityChecked.class);
		}
		return null;
	}
}
