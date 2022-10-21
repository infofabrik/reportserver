package net.datenwerke.rs.legacysaiku.service.saiku;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.olap4j.OlapConnection;
import org.olap4j.OlapException;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Dimension;
import org.olap4j.metadata.Level;
import org.olap4j.metadata.Member;

import net.datenwerke.rs.adminutils.client.datasourcetester.ConnectionTestFailedException;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;

public interface OlapUtilService {

   OlapConnection getOlapConnection(SaikuReport report) throws ClassNotFoundException, IOException, SQLException;

   OlapConnection getOlapConnection(MondrianDatasource mondrianDatasource, SaikuReport report, boolean resetParameters)
         throws IOException, ClassNotFoundException, SQLException;

   Cube getCube(SaikuReport report) throws ClassNotFoundException, IOException, SQLException;

   String replaceParametersInQuery(ParameterSet parameterSet, Map<String, ParameterValue> pMap, String mondrianSchema,
         boolean resetParameters);

   List<Member> getAllMeasures(Cube cube) throws OlapException;

   List<Dimension> getAllDimensions(Cube cube);

   List<Member> getAllMembers(Cube cube, String dimension, String hierarchy, String level) throws OlapException;

   List<Level> getAllLevels(Cube cube, String dimension, String hierarchy) throws OlapException;

   List<Member> getHierarchyRootMembers(Cube cube, String hierarchyName) throws OlapException;

   List<String> getCubes(MondrianDatasource datasource, SaikuReport saikuReport)
         throws ClassNotFoundException, IOException, SQLException;

   /**
    * Clears the Mondrian Cache of the given report.
    * 
    * @param report the report of which the cache should be cleared
    */
   void flushCache(Report report);

   void flushCache(MondrianDatasource datasource);

   boolean testConnection(MondrianDatasource datasource) throws ConnectionTestFailedException;

}
