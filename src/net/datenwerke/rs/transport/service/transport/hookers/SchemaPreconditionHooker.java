package net.datenwerke.rs.transport.service.transport.hookers;

import java.sql.SQLException;
import java.util.Optional;

import com.google.inject.Inject;

import net.datenwerke.rs.EnvironmentValidatorHelperService;
import net.datenwerke.rs.transport.service.transport.PreconditionResult;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.hooks.ApplyPreconditionHook;

public class SchemaPreconditionHooker implements ApplyPreconditionHook {

   private final EnvironmentValidatorHelperService envValidatorHelperService;
   
   @Inject
   public SchemaPreconditionHooker(
         EnvironmentValidatorHelperService envValidatorHelperService
         ) {
      this.envValidatorHelperService = envValidatorHelperService;
   }
   
   @Override
   public String getKey() {
      return "DB_SCHEMA_CHECK";
   }

   @Override
   public PreconditionResult analyze(Transport transport) {
      try {
         String schema = envValidatorHelperService.getSchemaVersion();
         String transportSchema = transport.getRsSchemaVersion();
         if (!schema.equals(transportSchema))
            return new PreconditionResult(Optional
                  .of("Schema versions do not match. Local: '" + schema + "', transport: '" + transportSchema + "'"));
      } catch (SQLException e) {
         throw new IllegalStateException("DB schema could not be fetched");
      }
      
      return new PreconditionResult(Optional.empty());
   }

}
