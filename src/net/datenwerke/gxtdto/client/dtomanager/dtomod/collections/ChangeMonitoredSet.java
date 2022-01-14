package net.datenwerke.gxtdto.client.dtomanager.dtomod.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ChangeMonitoredSet<E> extends MonitoredCollection<E, Set<E>> implements Set<E> {

   /**
    * 
    */
   private static final long serialVersionUID = -6254780343696211158L;

   public ChangeMonitoredSet() {
      super(new HashSet<E>());
   }

   public ChangeMonitoredSet(Set<E> underlyingCollection) {
      super(null == underlyingCollection ? new HashSet<E>() : underlyingCollection);
   }

   @Override
   public boolean add(E e) {
      boolean retValue = underlyingCollection.add(e);
      if (retValue)
         markModified();
      return retValue;
   }

   @Override
   public boolean addAll(Collection<? extends E> c) {
      boolean retValue = underlyingCollection.addAll(c);
      if (retValue)
         markModified();
      return retValue;
   }

   @Override
   public void clear() {
      if (!underlyingCollection.isEmpty()) {
         underlyingCollection.clear();
         markModified();
      }
   }

   @Override
   public boolean contains(Object o) {
      return underlyingCollection.contains(o);
   }

   @Override
   public boolean containsAll(Collection<?> c) {
      return underlyingCollection.containsAll(c);
   }

   @Override
   public boolean isEmpty() {
      return underlyingCollection.isEmpty();
   }

   @Override
   public Iterator<E> iterator() {
      return underlyingCollection.iterator();
   }

   @Override
   public boolean remove(Object o) {
      boolean retValue = underlyingCollection.remove(o);
      if (retValue)
         markModified();
      return retValue;
   }

   @Override
   public boolean removeAll(Collection<?> c) {
      boolean retValue = underlyingCollection.removeAll(c);
      if (retValue)
         markModified();
      return retValue;
   }

   @Override
   public boolean retainAll(Collection<?> c) {
      boolean retValue = underlyingCollection.retainAll(c);
      if (retValue)
         markModified();
      return retValue;
   }

   @Override
   public int size() {
      return underlyingCollection.size();
   }

   @Override
   public Object[] toArray() {
      return underlyingCollection.toArray();
   }

   @Override
   public <T> T[] toArray(T[] a) {
      return underlyingCollection.toArray(a);
   }

}
