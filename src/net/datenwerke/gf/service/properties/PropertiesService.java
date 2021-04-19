package net.datenwerke.gf.service.properties;

import java.util.List;

import net.datenwerke.gf.service.properties.entities.Property;

/**
 * A service that provides a way to store simple properties (key/value pairs)
 * persistently in the database.
 * 
 *
 */
public interface PropertiesService {

	/**
	 * Retrieves a property by ID (note that the id is not the property's key)
	 * 
	 * @see #getPropertyByKey(String)
	 * @param id
	 */
	Property getPropertyById(Long id);
	
	/**
	 * Retrieves a property by key.
	 * 
	 * @param name
	 */
	Property getPropertyByKey(String name);
	
	/**
	 * Returns true, if a property with the given key exists.
	 * 
	 * @param key
	 */
	boolean containsKey(String key);
	
	/**
	 * Removes a property by key.
	 * 
	 * @param key
	 */
	void removeByKey(String key);
	
	/**
	 * Updates the property with the given key, or creates a new one if none exists.
	 * 
	 * @param key
	 * @param value
	 */
	Property setProperty(String key, String value);
	
	/**
	 * Returns the property value or null.
	 * 
	 * @param key
	 */
	public String get(String key);
	
	/**
	 * Persists a property
	 * 
	 * @param property
	 */
	void persist(Property property);
	
	/**
	 * Merges a property
	 * 
	 * @param property
	 */
	Property merge(Property property);
	
	/**
	 * Removes a property
	 * 
	 * @param property
	 */
	void remove(Property property);

	/**
	 * Returns all properties
	 * 
	 */
	List<Property> getAllProperties();
}
