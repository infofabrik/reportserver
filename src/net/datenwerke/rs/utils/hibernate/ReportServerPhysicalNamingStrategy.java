package net.datenwerke.rs.utils.hibernate;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class ReportServerPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl
      implements PhysicalNamingStrategy {

   private static final long serialVersionUID = 1912818083140009274L;

   @Override
   public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
      if (name.getText().toUpperCase().startsWith("RS_")) {
         return Identifier.toIdentifier(name.getText().toUpperCase());
      } else {
         return Identifier.toIdentifier("RS_" + name.getText().toUpperCase());
      }
   }

}
