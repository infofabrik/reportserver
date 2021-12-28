package net.datenwerke.treedb.service.treedb.exceptions;

public class UnknownBaseClassException extends TreeDBRuntimeException {

   public UnknownBaseClassException(Class<?> clazz) {
      super("Unknown treedb base class for: " + clazz.getCanonicalName()); //$NON-NLS-1$
   }
}
