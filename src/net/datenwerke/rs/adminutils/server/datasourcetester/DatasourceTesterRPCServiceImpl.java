package net.datenwerke.rs.adminutils.server.datasourcetester;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.adminutils.client.datasourcetester.ConnectionTestFailedException;
import net.datenwerke.rs.adminutils.client.datasourcetester.rpc.DatasourceTesterRPCService;
import net.datenwerke.rs.adminutils.service.datasourcetester.DatasourceTesterService;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.utils.exception.ExceptionServices;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

@Singleton
public class DatasourceTesterRPCServiceImpl extends SecuredRemoteServiceServlet implements DatasourceTesterRPCService {

   /**
    * 
    */
   private static final long serialVersionUID = 3945467345321105023L;

   private final DtoService dtoService;
   private final ExceptionServices exceptionServices;
   private final DatasourceTesterService datasourceTesterService;

   @Inject
   public DatasourceTesterRPCServiceImpl(
         DtoService dtoService, 
         ExceptionServices exceptionServices,
         DatasourceTesterService datasourceTesterService
         ) {

      /* store objects */
      this.dtoService = dtoService;
      this.exceptionServices = exceptionServices;
      this.datasourceTesterService = datasourceTesterService;
   }

   @SecurityChecked(argumentVerification = {
         @ArgumentVerification(
               name = "datasource", 
               isDto = true, 
               verify = @RightsVerification(
                     rights = { 
                           Read.class,
                           Execute.class 
                           })) })
   @Transactional(rollbackOn = { Exception.class })
   @Override
   public boolean testConnection(@Named("datasource") DatabaseDatasourceDto databaseDto)
         throws ConnectionTestFailedException {
      try {
         datasourceTesterService.testConnection((DatabaseDatasource) dtoService.loadPoso(databaseDto));
      } catch (Exception e) {
         ConnectionTestFailedException ex = new ConnectionTestFailedException(e.getMessage(), e);
         ex.setStackTraceAsString(exceptionServices.exceptionToString(e));
         throw ex;
      }

      return true;
   }

}
