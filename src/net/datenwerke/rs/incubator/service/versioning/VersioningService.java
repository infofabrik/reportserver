package net.datenwerke.rs.incubator.service.versioning;

import java.util.Set;

import org.hibernate.envers.AuditReader;

import net.datenwerke.rs.incubator.service.versioning.entities.Revision;

public interface VersioningService {

	/**
	 * Returns a {@link Set} of all {@link Revision}s of the given {@link Object}
	 * 
	 * @param object The {@link Object}
	 * @return A {@link Set} of {@link Revision}s
	 */
	public Set<Revision> getRevisions(Object object);
	public Set<Revision> getRevisions(Set<Number> revisionNumbers);
	
	/**
	 * Returns an instance of the given {@link Object} at the given <i>revision</i>. The
	 * result gets casted to the {@link Class} <i>T</i>
	 * 
	 * @param <T> The class to cast to
	 * @param clazz The {@link Class} object
	 * @param object The {@link Object}
	 * @param revision The <i>revision</i> as a {@link Number}
	 * @return An instance of the given {@link Object} with the data of the given <i>revision</i>
	 */
	public <T> T getAtRevision(Class<T> clazz, Object object, Number revision);
	
	public <T> T getAtRevision(Class<T> clazz, long objectId, Number revision);
	
	/**
	 * Returns a {@link Set} of {@link Number}s identified all revisions of the given {@link Object}
	 * 
	 * @param object The {@link Object} to get the revisions from
	 * @return A {@link Set} holding the revision {@link Number}s
	 */
	public Set<Number> getRevisionNumbers(Object object);
	public Set<Number> getRevisionNumbers(Class<?> clazz, long objectId);
	
	/**
	 * Returns the used {@link AuditReader}
	 * @return The used {@link AuditReader}
	 */
	public AuditReader getAuditReader();


	

}
