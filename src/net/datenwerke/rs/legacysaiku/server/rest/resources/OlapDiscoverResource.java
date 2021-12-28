package net.datenwerke.rs.legacysaiku.server.rest.resources;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.legacysaiku.olap.dto.SaikuCatalog;
import org.legacysaiku.olap.dto.SaikuConnection;
import org.legacysaiku.olap.dto.SaikuCube;
import org.legacysaiku.olap.dto.SaikuCubeMetadata;
import org.legacysaiku.olap.dto.SaikuDimension;
import org.legacysaiku.olap.dto.SaikuHierarchy;
import org.legacysaiku.olap.dto.SaikuLevel;
import org.legacysaiku.olap.dto.SaikuMember;
import org.legacysaiku.olap.dto.SaikuSchema;
import org.legacysaiku.olap.util.ObjectUtil;
import org.olap4j.mdx.IdentifierNode;
import org.olap4j.mdx.IdentifierSegment;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.rs.legacysaiku.service.saiku.OlapUtilService;
import net.datenwerke.rs.legacysaiku.service.saiku.SaikuSessionContainer;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;

@Path("/legacysaiku/{username}/discover")
public class OlapDiscoverResource implements Serializable {

   private static final long serialVersionUID = -5106440271813074141L;
   private static final Logger log = LoggerFactory.getLogger(OlapDiscoverResource.class);

   private Provider<SaikuSessionContainer> saikuSessionContainer;
   private OlapUtilService olapUtilService;

   @Inject
   public OlapDiscoverResource(Provider<SaikuSessionContainer> saikuSessionContainer, OlapUtilService olapUtilService

   ) {

      this.saikuSessionContainer = saikuSessionContainer;
      this.olapUtilService = olapUtilService;
   }

   /**
    * Returns the datasources available.
    */
   @GET
   @Produces({ "application/json" })
   public List<SaikuConnection> getConnections(@PathParam("username") String username) {

      try {
         SaikuReport report = getReport(username);
         String connectionName = report.getUuid();

         Cube cube = olapUtilService.getCube(report);

         SaikuCube scube = new SaikuCube(connectionName, cube.getUniqueName(), cube.getName(), cube.getCaption(),
               cube.getSchema().getCatalog().getName(), cube.getSchema().getName());
         SaikuSchema sschema = new SaikuSchema(cube.getSchema().getName(), Arrays.asList(scube));
         SaikuCatalog scatalog = new SaikuCatalog(cube.getSchema().getCatalog().getName(), Arrays.asList(sschema));

         return Arrays.asList(new SaikuConnection(connectionName, Arrays.asList(scatalog)));
      } catch (Exception e) {
         log.error(this.getClass().getName(), e);
         // return new ArrayList<SaikuConnection>();
         throw new RuntimeException(e);
      }
   }

   /**
    * Returns the datasources available.
    */
   @GET
   @Produces({ "application/json" })
   @Path("/{connection}")
   public List<SaikuConnection> getConnection(@PathParam("username") String username,
         @PathParam("connection") String connectionName) {
      return getConnections(username);
   }

   @GET
   @Produces({ "application/json" })
   @Path("/refresh")
   public List<SaikuConnection> refreshConnections(@PathParam("username") String username) {
      return getConnections(username);
   }

   @GET
   @Produces({ "application/json" })
   @Path("/{connection}/refresh")
   public List<SaikuConnection> refreshConnection(@PathParam("username") String username,
         @PathParam("connection") String connectionName) {
      return getConnections(username);
   }

   @GET
   @Produces({ "application/json" })
   @Path("/{connection}/{catalog}/{schema}/{cube}/metadata")
   public SaikuCubeMetadata getMetadata(@PathParam("username") String username,
         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName) {

      try {

         Cube cube = getCube(username);
         List<SaikuDimension> dimensions = ObjectUtil.convertDimensions(olapUtilService.getAllDimensions(cube));
         List<SaikuMember> measures = ObjectUtil.convertMembers(olapUtilService.getAllMeasures(cube));

         return new SaikuCubeMetadata(dimensions, measures);
      } catch (Exception e) {
         e.printStackTrace();
      }

      return new SaikuCubeMetadata(null, null);
   }

   @GET
   @Produces({ "application/json" })
   @Path("/{connection}/{catalog}/{schema}/{cube}/dimensions")
   public List<SaikuDimension> getDimensions(@PathParam("username") String username,
         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName) {
      try {
         Cube cube = getCube(username);
         return ObjectUtil.convertDimensions(olapUtilService.getAllDimensions(cube));
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @GET
   @Produces({ "application/json" })
   @Path("/{connection}/{catalog}/{schema}/{cube}/dimensions/{dimension}")
   public SaikuDimension getDimension(@PathParam("username") String username,
         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName,
         @PathParam("dimension") String dimensionName) {
      try {
         Cube cube = getCube(username);
         return ObjectUtil.convert(cube.getDimensions().get(dimensionName));
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   @GET
   @Produces({ "application/json" })
   @Path("/{connection}/{catalog}/{schema}/{cube}/dimensions/{dimension}/hierarchies")
   public List<SaikuHierarchy> getDimensionHierarchies(@PathParam("username") String username,
         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName,
         @PathParam("dimension") String dimensionName) {

      return getDimension(username, connectionName, catalogName, schemaName, cubeName, dimensionName).getHierarchies();
   }

   @GET
   @Produces({ "application/json" })
   @Path("/{connection}/{catalog}/{schema}/{cube}/dimensions/{dimension}/hierarchies/{hierarchy}/levels")
   public List<SaikuLevel> getHierarchy(@PathParam("username") String username,
         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName,
         @PathParam("dimension") String dimensionName, @PathParam("hierarchy") String hierarchyName) {
      try {
         Cube cube = getCube(username);
         return ObjectUtil.convertLevels(olapUtilService.getAllLevels(cube, dimensionName, hierarchyName));
      } catch (Exception e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      }
   }

   /**
    * Get level information.
    */
   @GET
   @Produces({ "application/json" })
   @Path("/{connection}/{catalog}/{schema}/{cube}/dimensions/{dimension}/hierarchies/{hierarchy}/levels/{level}")
   public List<SaikuMember> getLevelMembers(@PathParam("username") String username,
         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName,
         @PathParam("dimension") String dimensionName, @PathParam("hierarchy") String hierarchyName,
         @PathParam("level") String levelName) {
      try {
         Cube cube = getCube(username);
         return ObjectUtil.convertMembers(olapUtilService.getAllMembers(cube, dimensionName, hierarchyName, levelName));
      } catch (Exception e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      }
   }

   /**
    * Get root member of that hierarchy.
    */
   @GET
   @Produces({ "application/json" })
   @Path("/{connection}/{catalog}/{schema}/{cube}/hierarchies/{hierarchy}/rootmembers")
   public List<SaikuMember> getRootMembers(@PathParam("username") String username,
         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName,
         @PathParam("hierarchy") String hierarchyName) {
      try {
         Cube cube = getCube(username);
         return ObjectUtil.convertMembers(olapUtilService.getHierarchyRootMembers(cube, hierarchyName));
      } catch (Exception e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      }
   }

   @GET
   @Path("/{connection}/{catalog}/{schema}/{cube}/hierarchies/")
   @Produces({ "application/json" })
   public List<SaikuHierarchy> getCubeHierarchies(@PathParam("username") String username,
         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName) {
      try {
         Cube cube = getCube(username);
         List<Hierarchy> h = cube.getHierarchies();
         return ObjectUtil.convertHierarchies(h);
      } catch (Exception e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      }
   }

   @GET
   @Path("/{connection}/{catalog}/{schema}/{cube}/measures/")
   @Produces({ "application/json" })
   public List<SaikuMember> getCubeMeasures(@PathParam("username") String username,
         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName) {
      try {
         Cube cube = getCube(username);
         return ObjectUtil.convertMembers(olapUtilService.getAllMeasures(cube));
      } catch (Exception e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      }
   }

   /**
    * Get all info for given member
    */
   @GET
   @Produces({ "application/json" })
   @Path("/{connection}/{catalog}/{schema}/{cube}/member/{member}")
   public SaikuMember getMember(@PathParam("username") String username, @PathParam("connection") String connectionName,
         @PathParam("catalog") String catalogName, @PathParam("schema") String schemaName,
         @PathParam("cube") String cubeName, @PathParam("member") String memberName) {
      try {
         Cube cube = getCube(username);
         return ObjectUtil.convert(cube.lookupMember(IdentifierNode.parseIdentifier(memberName).getSegmentList()));
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }

   /**
    * Get child members of given member
    */
   @GET
   @Produces({ "application/json" })
   @Path("/{connection}/{catalog}/{schema}/{cube}/member/{member}/children")
   public List<SaikuMember> getMemberChildren(@PathParam("username") String username,
         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName,
         @PathParam("member") String memberName) {
      List<SaikuMember> members = new ArrayList<SaikuMember>();

      try {
         Cube cube = getCube(username);
         List<IdentifierSegment> memberList = IdentifierNode.parseIdentifier(memberName).getSegmentList();
         Member m = cube.lookupMember(memberList);

         if (m != null) {
            for (Member c : m.getChildMembers()) {
               SaikuMember sm = ObjectUtil.convert(c);
               members.add(sm);
            }
         }

      } catch (Exception e) {
         e.printStackTrace();
         throw new RuntimeException(e);
      }
      return members;
   }

   private SaikuReport getReport(String username) {
      return saikuSessionContainer.get().getReport(username);
   }

   private Cube getCube(String username) throws ClassNotFoundException, IOException, SQLException {
      SaikuReport report = getReport(username);
      Cube cube = olapUtilService.getCube(report);
      return cube;
   }
}
