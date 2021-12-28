package net.datenwerke.rs.utils.interfaces;

public interface Callback<T> {

   public void execute(T object) throws Exception;

}
