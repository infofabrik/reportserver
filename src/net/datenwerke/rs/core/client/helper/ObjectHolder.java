package net.datenwerke.rs.core.client.helper;

public class ObjectHolder<T> {
   
   private T object;
   
   public void set(T object) {
      this.object = object;
   }

   public T get() {
      return object;
   }
}
