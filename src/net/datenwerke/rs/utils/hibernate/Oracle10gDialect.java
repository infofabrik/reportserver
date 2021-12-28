package net.datenwerke.rs.utils.hibernate;

import java.sql.Types;

import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.type.descriptor.sql.ClobTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

public class Oracle10gDialect extends org.hibernate.dialect.Oracle10gDialect {

   public Oracle10gDialect() {
      super();
      registerColumnType(Types.LONGVARCHAR, "clob");
   }

   @Override
   public boolean supportsSequences() {
      return false;
   }

   /*
    * Hibernate 4 uses HiLo per default. To ensure that we get simulated sequences
    * we now do the following.
    */
   @Override
   public Class getNativeIdentifierGeneratorClass() {
      return SequenceStyleGenerator.class;
   }

   @Override
   public SqlTypeDescriptor remapSqlTypeDescriptor(SqlTypeDescriptor sqlTypeDescriptor) {
      if (sqlTypeDescriptor instanceof RsClobTypeDummyDescriptor) {
         return ClobTypeDescriptor.DEFAULT;
      }
      return super.remapSqlTypeDescriptor(sqlTypeDescriptor);
   }

}
