/*  
 *   Copyright 2012 OSBI Ltd
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package net.datenwerke.rs.saiku.server.rest.resources;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.olap4j.mdx.IdentifierNode;
import org.olap4j.mdx.IdentifierSegment;
import org.olap4j.metadata.Cube;
import org.olap4j.metadata.Hierarchy;
import org.olap4j.metadata.Member;
import org.olap4j.metadata.NamedList;
import org.saiku.olap.dto.SaikuCatalog;
import org.saiku.olap.dto.SaikuConnection;
import org.saiku.olap.dto.SaikuCube;
import org.saiku.olap.dto.SaikuCubeMetadata;
import org.saiku.olap.dto.SaikuDimension;
import org.saiku.olap.dto.SaikuHierarchy;
import org.saiku.olap.dto.SaikuLevel;
import org.saiku.olap.dto.SaikuMeasure;
import org.saiku.olap.dto.SaikuMember;
import org.saiku.olap.dto.SaikuSchema;
import org.saiku.olap.dto.SimpleCubeElement;
import org.saiku.olap.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.saiku.service.hooks.SaikuCubeMetadataHook;
import net.datenwerke.rs.saiku.service.locale.SaikuMessages;
import net.datenwerke.rs.saiku.service.saiku.OlapUtilService;
import net.datenwerke.rs.saiku.service.saiku.SaikuSessionContainer;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;

//@Component
@Path("/saiku/{username}/discover")
public class OlapDiscoverResource implements Serializable {

//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	private OlapDiscoverService olapDiscoverService;
//    
//    private static final Logger log = LoggerFactory.getLogger(OlapDiscoverResource.class);
//    
//    public void setOlapDiscoverService(OlapDiscoverService olapds) {
//        olapDiscoverService = olapds;
//    }
   
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private final OlapUtilService olapUtilService;
   private final HookHandlerService hookHandler;
   private final Provider<SaikuSessionContainer> saikuSessionContainer;

   private final Provider<AuthenticatorService> authenticatorService;
   private final SecurityService securityService;

   private static final Logger log = LoggerFactory.getLogger(OlapDiscoverResource.class);

   @Inject
   public OlapDiscoverResource(OlapUtilService olapUtils, Provider<SaikuSessionContainer> saikuSessionContainer,
         HookHandlerService hookHandler, SecurityService securityService,
         Provider<AuthenticatorService> authenticatorService) {
      this.olapUtilService = olapUtils;
      this.saikuSessionContainer = saikuSessionContainer;
      this.hookHandler = hookHandler;
      this.securityService = securityService;
      this.authenticatorService = authenticatorService;
   }
    
    /**
     * Returns the org.saiku.datasources available.
     * @summary Get datasources.
     */
    @GET
    @Produces({"application/json" })
     public List<SaikuConnection> getConnections(@PathParam("username") String username) {
//    	try {
//			return olapDiscoverService.getAllConnections();
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//			return new ArrayList<>();
//		}
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
     * Returns the org.saiku.datasources available.
     * @summary Get connections by connectionName.
     * @param connectionName The connection name
     */
    @GET
    @Produces({"application/json" })
    @Path("/{connection}")
    public List<SaikuConnection> getConnections(@PathParam("username") String username,
          @PathParam("connection") String connectionName) {
//    	try {
//			return olapDiscoverService.getConnection(connectionName);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//			return new ArrayList<>();
//		}
       return getConnections(username);
    }


  /**
   * Refresh the Saiku connections.
   * @Summary Refresh connections.
   * @return The existing connections.
   */
    @GET
    @Produces({"application/json" })
  	@Path("/refresh")
     public List<SaikuConnection> refreshConnections(@PathParam("username") String username) {
//    	try {
//    		olapDiscoverService.refreshAllConnections();
//			return olapDiscoverService.getAllConnections();
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//			return new ArrayList<>();
//		}
       return getConnections(username);
    }

  /**
   * Refresh a specific connection by connection name.
   * @summary Refresh connection.
   * @param connectionName The connection name.
   * @return A List of available connections.
   */
    @GET
    @Produces({"application/json" })
    @Path("/{connection}/refresh")
     public List<SaikuConnection> refreshConnection( @PathParam("username") String username,
           @PathParam("connection") String connectionName) {
//    	try {
//			olapDiscoverService.refreshConnection(connectionName);
//			return olapDiscoverService.getConnection(connectionName);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//			return new ArrayList<>();
//		}
       return getConnections(username);
    }

  /**
   * Get Cube Metadata
   * @param connectionName The connection name
   * @param catalogName The catalog name
   * @param schemaName The schema name
   * @param cubeName The cube name
   * @return A metadata object.
   */
	@GET
    @Produces({"application/json" })
	@Path("/{connection}/{catalog}/{schema}/{cube}/metadata")
     public SaikuCubeMetadata getMetadata(
           @PathParam("username") String username,
           @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
           @PathParam("schema") String schemaName, @PathParam("cube") String cubeName)
    {
//		if ("null".equals(schemaName)) {
//			schemaName = "";
//		}
//		SaikuCube cube = new SaikuCube(connectionName, cubeName,cubeName,cubeName, catalogName, schemaName);
//		try {
//			List<SaikuDimension> dimensions = olapDiscoverService.getAllDimensions(cube);
//			List<SaikuMember> measures = olapDiscoverService.getMeasures(cube);
//			Map<String, Object> properties = olapDiscoverService.getProperties(cube);
//			return new SaikuCubeMetadata(dimensions, measures, properties);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//		}
//		return new SaikuCubeMetadata(null, null, null);
              try {
                 Cube cube = getCube(username);
                 List<SaikuDimension> dimensions = ObjectUtil.convertDimensions(olapUtilService.getAllDimensions(cube));
                 List<SaikuMember> measures = ObjectUtil.convertMembers(olapUtilService.getAllMeasures(cube)); // ensure to fix
                                                                                                               // bug in convert
                                                                                                               // members
                 Map<String, Object> properties = olapUtilService.getProperties(cube);

                 for (SaikuMember m : measures) {
                    if (m instanceof SaikuMeasure) {
                       if (null == ((SaikuMeasure) m).getMeasureGroup()
                             || "".equals(((SaikuMeasure) m).getMeasureGroup().trim())) {
                          Field f = SaikuMeasure.class.getDeclaredField("measureGroup");
                          f.setAccessible(true);
                          f.set(m, SaikuMessages.INSTANCE.undefinedMeasureGroup());
                       }
                    }
                 }

                 SaikuCubeMetadata metadata = new SaikuCubeMetadata(dimensions, measures, properties);

                 for (SaikuCubeMetadataHook hooker : hookHandler.getHookers(SaikuCubeMetadataHook.class))
                    hooker.adapt(metadata, cube);

                 return metadata;
              } catch (Exception e) {
                 e.printStackTrace();
              }

              return new SaikuCubeMetadata(null, null, null);
	}

  /**
   * Get the dimensions from a cube.
   * @Summary Get Dimensions
   * @param connectionName The connection name.
   * @param catalogName The catalog name.
   * @param schemaName The schema name.
   * @param cubeName The cube name.
   * @return A list of Dimensions.
   */
	@GET
    @Produces({"application/json" })
	@Path("/{connection}/{catalog}/{schema}/{cube}/dimensions")
     public List<SaikuDimension> getDimensions(
           @PathParam("username") String username,
           @PathParam("connection") String connectionName, 
           @PathParam("catalog") String catalogName,
           @PathParam("schema") String schemaName, 
           @PathParam("cube") String cubeName
           ) 
    {
//		if ("null".equals(schemaName)) {
//			schemaName = "";
//		}
//		SaikuCube cube = new SaikuCube(connectionName, cubeName,cubeName,cubeName, catalogName, schemaName);
//		try {
//			return olapDiscoverService.getAllDimensions(cube);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//		}
//		return new ArrayList<>();
	   try {
	         Cube cube = getCube(username);
	         return ObjectUtil.convertDimensions(olapUtilService.getAllDimensions(cube));
	      } catch (Exception e) {
	         throw new RuntimeException(e);
	      }
	}

  /**
   * Get a dimension from cube
   * @summary Get dimension
   * @param connectionName The connection name
   * @param catalogName The catalog name
   * @param schemaName The schema name
   * @param cubeName The cube name
   * @param dimensionName The dimension name
   * @return
   */
	@GET
    @Produces({"application/json" })
	@Path("/{connection}/{catalog}/{schema}/{cube}/dimensions/{dimension}")
     public SaikuDimension getDimension(
           @PathParam("username") String username,
           @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
           @PathParam("schema") String schemaName, @PathParam("cube") String cubeName,
           @PathParam("dimension") String dimensionName) 
    {
//		if ("null".equals(schemaName)) {
//			schemaName = "";
//		}
//		SaikuCube cube = new SaikuCube(connectionName, cubeName,cubeName,cubeName, catalogName, schemaName);
//		try {
//			return olapDiscoverService.getDimension(cube, dimensionName);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//		}
//		return null;
	   try {
	         Cube cube = getCube(username);
	         return ObjectUtil.convert(cube.getDimensions().get(dimensionName));
	      } catch (Exception e) {
	         throw new RuntimeException(e);
	      }
	}

  /**
   * Get hierarchies from a dimension.
   * @summary Get Hierarchies
   * @param connectionName The connection name
   * @param catalogName The catalog name
   * @param schemaName The schema name
   * @param cubeName The cube name
   * @param dimensionName The dimension name
   * @return A list of hierarchies
   */
	@GET
    @Produces({"application/json" })
	@Path("/{connection}/{catalog}/{schema}/{cube}/dimensions/{dimension}/hierarchies")
     public List<SaikuHierarchy> getDimensionHierarchies(@PathParam("username") String username,
           @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
           @PathParam("schema") String schemaName, @PathParam("cube") String cubeName,
           @PathParam("dimension") String dimensionName) {
//		if ("null".equals(schemaName)) {
//			schemaName = "";
//		}
//		SaikuCube cube = new SaikuCube(connectionName, cubeName,cubeName,cubeName, catalogName, schemaName);
//		try {
//			return olapDiscoverService.getAllDimensionHierarchies(cube, dimensionName);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//		}
//		return new ArrayList<>();
	   try {
	         Cube cube = getCube(username);
	         return olapUtilService.getAllDimensionHierarchies(cube, dimensionName);
	      } catch (Exception e) {
	         throw new RuntimeException(e);
	      }
	}

  /**
   * Get a hierarchy
   * @summary Get a hierarchy.
   * @param connectionName The connection name
   * @param catalogName The catalog name
   * @param schemaName The schema name
   * @param cubeName The cube name
   * @param dimensionName The dimension name
   * @param hierarchyName The hierarchy name
   * @return A list of Saiku Levels
   */
	@GET
	@Produces({"application/json" })
	@Path("/{connection}/{catalog}/{schema}/{cube}/dimensions/{dimension}/hierarchies/{hierarchy}/levels")
	public List<SaikuLevel> getHierarchy(@PathParam("username") String username,
	         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
	         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName,
	         @PathParam("dimension") String dimensionName, @PathParam("hierarchy") String hierarchyName)
	{
//		if ("null".equals(schemaName)) {
//			schemaName = "";
//		}
//		SaikuCube cube = new SaikuCube(connectionName, cubeName,cubeName,cubeName, catalogName, schemaName);
//		try {
//			return olapDiscoverService.getAllHierarchyLevels(cube, dimensionName, hierarchyName);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//		}
//		return new ArrayList<>();
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
     * @summary Level information
     * @param connectionName The connection name
     * @param catalogName The catalog name
     * @param schemaName The schema name
     * @param cubeName The cube name
     * @param dimensionName The dimension name
     * @param hierarchyName The hierarchy name
     * @param levelName The level name
	 * @return A list of level information.
	 */
	@GET
	@Produces({"application/json" })
	@Path("/{connection}/{catalog}/{schema}/{cube}/dimensions/{dimension}/hierarchies/{hierarchy}/levels/{level}")
	public List<SimpleCubeElement> getLevelMembers(
	      @PathParam("username") String username,
	         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
	         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName,
	         @PathParam("dimension") String dimensionName, @PathParam("hierarchy") String hierarchyName,
	         @PathParam("level") String levelName)
	{
//		if ("null".equals(schemaName)) {
//			schemaName = "";
//		}
//
//		SaikuCube cube = new SaikuCube(connectionName, cubeName,cubeName,cubeName, catalogName, schemaName);
//
//	  
//		try {
//			return olapDiscoverService.getLevelMembers(cube, hierarchyName, levelName);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//		}
//		return new ArrayList<>();
	   try {
	         Cube cube = getCube(username);
	         return olapUtilService.getAllMembers(cube, hierarchyName, levelName);
	      } catch (Exception e) {
	         e.printStackTrace();
	         throw new RuntimeException(e);
	      }
	}

  /**
   * Get root member of that hierarchy.
   * @param connectionName The connection name
   * @param catalogName The catalog name
   * @param schemaName The schema name
   * @param cubeName The cube name
   * @param hierarchyName The hierarchy name
   * @return A list of Saiku members
   */
	@GET
	@Produces({"application/json" })
	@Path("/{connection}/{catalog}/{schema}/{cube}/hierarchies/{hierarchy}/rootmembers")
	public List<SaikuMember> getRootMembers(
	      @PathParam("username") String username,
	         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
	         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName,
	         @PathParam("hierarchy") String hierarchyName)
		{
//		if ("null".equals(schemaName)) {
//			schemaName = "";
//		}
//		SaikuCube cube = new SaikuCube(connectionName, cubeName,cubeName,cubeName, catalogName, schemaName);
//		try {
//			return olapDiscoverService.getHierarchyRootMembers(cube, hierarchyName);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//		}
//		return null;
	   try {
	         Cube cube = getCube(username);
	         return ObjectUtil.convertMembers(olapUtilService.getHierarchyRootMembers(cube, hierarchyName));
	      } catch (Exception e) {
	         e.printStackTrace();
	         throw new RuntimeException(e);
	      }
	}


  /**
   * Get Cube Hierachy Information
   * @summary Get Cube Hierarchies
   * @param connectionName The connection name
   * @param catalogName The catalog name
   * @param schemaName The schema name
   * @param cubeName The cube name
   * @return A list of Saiku Hierarchies
   */
	@GET
	@Path("/{connection}/{catalog}/{schema}/{cube}/hierarchies/")
    @Produces({"application/json" })
     public List<SaikuHierarchy> getCubeHierarchies(@PathParam("username") String username,
           @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
           @PathParam("schema") String schemaName, @PathParam("cube") String cubeName) {
//		if ("null".equals(schemaName)) {
//			schemaName = "";
//		}
//		SaikuCube cube = new SaikuCube(connectionName, cubeName,cubeName,cubeName, catalogName, schemaName);
//		try {
//			return olapDiscoverService.getAllHierarchies(cube);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//		}
//		return new ArrayList<>();
	   try {
	         Cube cube = getCube(username);
	         NamedList<Hierarchy> h = cube.getHierarchies();
	         return ObjectUtil.convertHierarchies(h);
	      } catch (Exception e) {
	         e.printStackTrace();
	         throw new RuntimeException(e);
	      }
	}

  /**
   * Get Cube Measure Information
   * @summary Get Cube Measures
   * @param connectionName The connection name
   * @param catalogName The catalog name
   * @param schemaName The schema name
   * @param cubeName The cube name
   * @return A list of Saiku Members
   */
	@GET
	@Path("/{connection}/{catalog}/{schema}/{cube}/measures/")
    @Produces({"application/json" })
     public List<SaikuMember> getCubeMeasures(@PathParam("username") String username,
           @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
           @PathParam("schema") String schemaName, @PathParam("cube") String cubeName) {
//		if ("null".equals(schemaName)) {
//			schemaName = "";
//		}
//		SaikuCube cube = new SaikuCube(connectionName, cubeName,cubeName,cubeName, catalogName, schemaName);
//		try {
//			return olapDiscoverService.getMeasures(cube);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//		}
//		return new ArrayList<>();
	   try {
	         Cube cube = getCube(username);
	         List<SaikuMember> members = ObjectUtil.convertMembers(olapUtilService.getAllMeasures(cube));

	         return members;
	      } catch (Exception e) {
	         e.printStackTrace();
	         throw new RuntimeException(e);
	      }
	}
	
	/**
	 * Get all info for given member
     * @summary Get Member Information
     * @param catalogName The catalog name
     * @param connectionName The connection name
     * @param cubeName The cube name
     * @param memberName The member name
     * @param schemaName The schema name
	 * @return  A Saiku Member
	 */
	@GET
	@Produces({"application/json" })
	@Path("/{connection}/{catalog}/{schema}/{cube}/member/{member}")
	public SaikuMember getMember(
	      @PathParam("username") String username, @PathParam("connection") String connectionName,
	         @PathParam("catalog") String catalogName, @PathParam("schema") String schemaName,
	         @PathParam("cube") String cubeName, @PathParam("member") String memberName)
	{
//		if ("null".equals(schemaName)) {
//			schemaName = "";
//		}
//		SaikuCube cube = new SaikuCube(connectionName, cubeName,cubeName,cubeName, catalogName, schemaName);
//		try {
//			return olapDiscoverService.getMember(cube, memberName);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//		}
//		return null;
	   try {
	         Cube cube = getCube(username);
	         return olapUtilService.getMember(cube, memberName);
	      } catch (Exception e) {
	         throw new RuntimeException(e);
	      }
	}
	
	/**
	 * Get child members of given member.
     * @summary Get child members
     * @param connectionName The connection name
     * @param schemaName The schema name
     * @param catalogName The catalog name
     * @param memberName The member name
     * @param cubeName The cube name
	 * @return A list of Saiku Members
	 */
	@GET
	@Produces({"application/json" })
	@Path("/{connection}/{catalog}/{schema}/{cube}/member/{member}/children")
	public List<SaikuMember> getMemberChildren(
	      @PathParam("username") String username,
	         @PathParam("connection") String connectionName, @PathParam("catalog") String catalogName,
	         @PathParam("schema") String schemaName, @PathParam("cube") String cubeName,
	         @PathParam("member") String memberName)
	{
//		if ("null".equals(schemaName)) {
//			schemaName = "";
//		}
//		SaikuCube cube = new SaikuCube(connectionName, cubeName,cubeName,cubeName, catalogName, schemaName);
//		try {
//			return olapDiscoverService.getMemberChildren(cube, memberName);
//		} catch (Exception e) {
//			log.error(this.getClass().getName(),e);
//		}
//		return new ArrayList<>();
	   
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
	      if (!authenticatorService.get().isAuthenticated())
	         throw new IllegalStateException("not authenticated");

	      SaikuReport report = saikuSessionContainer.get().getReport(username);

	      securityService.assertRights(report, Execute.class);

	      return report;
	   }

	   private Cube getCube(String username) throws ClassNotFoundException, IOException, SQLException {
	      SaikuReport report = getReport(username);
	      Cube cube = olapUtilService.getCube(report);
	      return cube;
	   }

}
