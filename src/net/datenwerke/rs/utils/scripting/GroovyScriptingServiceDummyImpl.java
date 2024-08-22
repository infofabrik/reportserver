package net.datenwerke.rs.utils.scripting;

public class GroovyScriptingServiceDummyImpl implements GroovyScriptingService {

   @Override
   public GroovyScript compile(String script) {
      return null;
   }

   @Override
   public GroovyScript compile(String script, boolean useSandbox) {
      return null;
   }

   @Override
   public Object evaluate(GroovyScript script) {
      return null;
   }

}
