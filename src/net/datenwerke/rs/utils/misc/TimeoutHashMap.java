package net.datenwerke.rs.utils.misc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 * A Hashmap keeping entries no longer than a certain time
 * 
 *
 */
public class TimeoutHashMap<K, V> extends HashMap<K, V>{
	
	private class Entry<K>{
		K key;
		long created;
		
		public Entry(long created, K key) {
			this.key = key;
			this.created = created;
		}
	}
	
	private LinkedList<Entry<K>> entries = new LinkedList<Entry<K>>();

	private long timeout;
	
	
	public TimeoutHashMap(long timeout) {
		super();
		this.setTimeout(timeout);
	}
	
	public void beforeRemove(K key, V value){
		
	}
	
	private void doMaintainance(){
		long now = System.currentTimeMillis();
		
		Iterator<Entry<K>> it = entries.iterator();
		while(it.hasNext()){
			Entry<K> entry = it.next();
			if(entry.created < now - getTimeout()){
				beforeRemove(entry.key, super.get(entry.key));
				super.remove(entry.key);
				it.remove();
			}else{
				return;
			}
		}
	}
	
	
	@Override
	public boolean containsKey(Object key) {
		doMaintainance();
		return super.containsKey(key);
	}
	
	@Override
	public boolean containsValue(Object value) {
		doMaintainance();
		return super.containsValue(value);
	}
	
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		doMaintainance();
		return super.entrySet();
	}
	
	@Override
	public V get(Object key) {
		doMaintainance();
		return super.get(key);
	}
	
	@Override
	public boolean isEmpty() {
		doMaintainance();
		return super.isEmpty();
	}
	
	@Override
	public Set<K> keySet() {
		doMaintainance();
		return super.keySet();
	}
	
	public V put(K key, V value) {
		doMaintainance();
		this.entries.add(new Entry<K>(System.currentTimeMillis(), key));
		return super.put(key, value);
	};
	
	@Override
	public int size() {
		doMaintainance();
		return super.size();
	}
	
	@Override
	public Collection<V> values() {
		doMaintainance();
		return super.values();
	}
	
	@Override
	public V remove(Object key) {
		beforeRemove((K) key, super.get(key));
		doMaintainance();
		return super.remove(key);
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
}
