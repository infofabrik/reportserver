package net.datenwerke.rs.utils.hibernate;

import java.sql.Types;

import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.type.descriptor.sql.ClobTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

public class DB2Dialect extends org.hibernate.dialect.DB2Dialect {

   public DB2Dialect() {
      super();
      registerColumnType(Types.LONGVARCHAR, "clob($l)");
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
