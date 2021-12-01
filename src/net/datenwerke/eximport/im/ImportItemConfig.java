package net.datenwerke.eximport.im;

import org.hibernate.proxy.HibernateProxy;


/**
 * 
 *
 */
public abstract class ImportItemConfig {

	protected final String id;
	protected final ImportMode importMode;
	protected final Object referenceObject;
	
	/**
	 * 
	 * @param id configured by this config
	 */
	public ImportItemConfig(String id){
		this.id = id;
		this.importMode = ImportMode.CREATE;
		this.referenceObject = null;
	}
	
	public ImportItemConfig(String id, ImportMode importMode){
		this.id = id;
		this.importMode = importMode;
		this.referenceObject = null;
		if(importMode != ImportMode.CREATE && importMode != ImportMode.IGNORE)
			throw new IllegalArgumentException("Import mode has to be create or ignore if no reference object is submitted.");
	}
	
	public ImportItemConfig(String id, ImportMode importMode, Object referenceObject){
		this.id = id;
		this.importMode = importMode;
		
		if(referenceObject instanceof HibernateProxy)
			referenceObject = ((HibernateProxy)referenceObject).getHibernateLazyInitializer().getImplementation();
		this.referenceObject = referenceObject;
	}
	
	public String getId(){
		return id;
	}
	
	public Object getReferenceObject(){
		return referenceObject;
	}
	
	public ImportMode getImportMode(){
		return importMode;
	}
	
	@Override
	public int hashCode() {
		if(null == getId())
			return super.hashCode();
		return getId().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof ImportItemConfig))
			return false;
		
		if(null == getId())
			return super.equals(obj);
		
		return getId().equals(((ImportItemConfig)obj).getId());
	}
	
}
