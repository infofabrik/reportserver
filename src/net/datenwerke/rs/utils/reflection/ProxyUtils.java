package net.datenwerke.rs.utils.reflection;

import java.lang.reflect.Proxy;

public class ProxyUtils {

	public static final String ENHANCED_BY_CGLIB_CLASSNAME_SNIPPET = "$$EnhancerByCGLIB$$"; //$NON-NLS-1$
	public static final String ENHANCED_BY_GUICE_CLASSNAME_SNIPPET = "$$EnhancerByGuice$$"; //$NON-NLS-1$
	public static final String ENHANCED_BY_HIBERNATE_CLASSNAME_SNIPPET = "_$$_javassist_"; //$NON-NLS-1$
	
	public boolean compareClasses(Class<?> typeA, Class<?> typeB){
		if(null == typeA && null == typeB)
			return true;
		if(null != typeA && typeA.equals(typeB))
			return true;
		if(! isProxy(typeA) && ! isProxy(typeB))
			return false;
		
		return getUnproxiedClass(typeA).equals(getUnproxiedClass(typeB));
	}
	
	public boolean isAssignableFrom(Class<?> typeA, Class<?> typeB){
		if(compareClasses(typeA, typeB))
			return true;
		if(! isProxy(typeA))
			return typeA.isAssignableFrom(typeB);
		
		Class<?> unproxiedA = getUnproxiedClass(typeA); 
		return unproxiedA.isAssignableFrom(typeB);
	}
	
	public boolean isInInheritanceLine(Class<?> typeA, Class<?> typeB){
		return isAssignableFrom(typeA, typeB) || isAssignableFrom(typeB, typeA);
	}
	
	public boolean isProxy(Class<?> type){
		if (null == type || type.isInterface() || type.equals(Object.class) )
	       return false;
	    
		if (Proxy.isProxyClass(type) || net.sf.cglib.proxy.Proxy.isProxyClass(type))
	       return true;

	    return type.getName().contains(ENHANCED_BY_CGLIB_CLASSNAME_SNIPPET) ||
	    		type.getName().contains(ENHANCED_BY_GUICE_CLASSNAME_SNIPPET) ||
	    		type.getName().contains(ENHANCED_BY_HIBERNATE_CLASSNAME_SNIPPET);
    }
	
	public Class<?> getUnproxiedClass(Class<?> type){
		if(isProxy(type))
			return getUnproxiedClass(type.getSuperclass());
		return type;
	}
	 
}
