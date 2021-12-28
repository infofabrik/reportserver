package net.datenwerke.gxtdto.client.dtomanager.dtomod.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ChangeMonitoredList<E> extends MonitoredCollection<E, List<E>> implements List<E> {

   /**
    * 
    */
   private static final long serialVersionUID = 4127764949591740255L;

   public ChangeMonitoredList() {
      super(new ArrayList<E>());
   }

   public ChangeMonitoredList(List<E> underlyingList) {
      super(null == underlyingList ? new ArrayList<E>() : underlyingList);
   }

   @Override
   public boolean add(E e) {
      boolean returnValue = underlyingCollection.add(e);
      markModified();
      return returnValue;
   }

   @Override
   public void add(int i, E e) {
      underlyingCollection.add(i, e);
      markModified();
   }

   @Override
   public boolean addAll(Collection<? extends E> c) {
      boolean returnValue = underlyingCollection.addAll(c);
      markModified();
      return returnValue;
   }

   @Override
   public boolean addAll(int i, Collection<? extends E> c) {
      boolean returnValue = underlyingCollection.addAll(i, c);
      markModified();
      return returnValue;
   }

   @Override
   public void clear() {
      underlyingCollection.clear();
      markModified();
   }

   @Override
   public boolean contains(Object o) {
      return underlyingCollection.contains(o);
   }

   @Override
   public boolean containsAll(Collection<?> c) {
      return underlyingCollection.contains(c);
   }

   @Override
   public E get(int i) {
      return underlyingCollection.get(i);
   }

   @Override
   public int indexOf(Object o) {
      return underlyingCollection.indexOf(o);
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
   public int lastIndexOf(Object o) {
      return underlyingCollection.lastIndexOf(o);
   }

   @Override
   public ListIterator<E> listIterator() {
      return underlyingCollection.listIterator();
   }

   @Override
   public ListIterator<E> listIterator(int i) {
      return underlyingCollection.listIterator(i);
   }

   @Override
   public boolean remove(Object o) {
      boolean retValue = underlyingCollection.remove(o);
      if (retValue)
         markModified();
      return retValue;
   }

   @Override
   public E remove(int o) {
      E retValue = underlyingCollection.remove(o);
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
   public E set(int i, E e) {
      E retValue = underlyingCollection.set(i, e);
      markModified();
      return retValue;
   }

   @Override
   public int size() {
      return underlyingCollection.size();
   }

   @Override
   public List<E> subList(int i1, int i2) {
      return underlyingCollection.subList(i1, i2);
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
