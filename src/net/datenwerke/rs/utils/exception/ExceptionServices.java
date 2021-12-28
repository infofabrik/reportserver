package net.datenwerke.rs.utils.exception;

import com.google.inject.ImplementedBy;

@ImplementedBy(ExceptionServicesImpl.class)
public interface ExceptionServices {

   public abstract String exceptionToString(Throwable e);

}