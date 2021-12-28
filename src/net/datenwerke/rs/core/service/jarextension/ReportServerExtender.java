package net.datenwerke.rs.core.service.jarextension;

/**
 * Implement this interface to extend ReportServer via jars.
 * 
 * For this use the Java Services API:
 * <a>http://docs.oracle.com/javase/6/docs/api/java/util/ServiceLoader.html</a>
 * 
 *
 */
public interface ReportServerExtender {

   public void extend();
}
