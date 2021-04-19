package net.datenwerke.rs.utils.guice;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.matcher.Matcher;

public class GuiceMatchers {

	/**
	 * Matches only public methods
	 */
	public static Matcher<Object> publicMethod() {
        return PUBLIC;
    }

    private static final Matcher<Object> PUBLIC = new Public();
	
	private static class Public extends AbstractMatcher<Object> implements Serializable {
		public boolean matches(Object o) {
		    if(o instanceof Method){
		    	return Modifier.isPublic(((Method)o).getModifiers()); 
		    }
			return false;
		}
		
		@Override
		public String toString() {
		    return "publicMethod()";
		}
		
		public Object readResolve() {
		    return publicMethod();
		}
		
		private static final long serialVersionUID = 0;
	}
	
}
