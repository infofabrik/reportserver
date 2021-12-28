package net.datenwerke.rs.utils.scripting;

import com.google.inject.ImplementedBy;

@ImplementedBy(GroovyScriptingServiceDummyImpl.class)
public interface GroovyScriptingService {

   public abstract GroovyScript compile(String script);

   GroovyScript compile(String script, boolean useSandbox);

   public abstract Object evaluate(GroovyScript script);

}