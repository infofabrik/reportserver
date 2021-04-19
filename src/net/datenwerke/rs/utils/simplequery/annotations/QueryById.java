package net.datenwerke.rs.utils.simplequery.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Allows to perform lookups by the entities id.
 * 
 * <pre>
 * 	QueryById
 *	public AbstractReportManagerNode getNodeById(long id) {
 *		return null; // by magic
 *	}
 * </pre>
 * This code snipped will perform a query 
 * <pre>
 *   FROM AbstractReportManagerNode where idField = :id
 * </pre>
 * 
 * <p>
 *  Note that if from is not specified it, the return type is examined and
 *  taken as from.
 * </p>
 * 
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface QueryById {

	Class<?> from() default Void.class;
	
}
