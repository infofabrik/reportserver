package net.datenwerke.rs.utils.hibernate;

import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.type.descriptor.sql.LongVarcharTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

public class PostgreSQLDialect extends org.hibernate.dialect.PostgreSQL9Dialect {

	public PostgreSQLDialect() {
		super();
	}
	
	@Override
	public boolean supportsSequences() {
		return false;
	}
	
	/* Hibernate 4 uses HiLo per default. To ensure that we get simulated sequences
	 * we now do the following.
	 */
	@Override
	public Class getNativeIdentifierGeneratorClass() {
		return SequenceStyleGenerator.class;
	}
	
	@Override
	public SqlTypeDescriptor remapSqlTypeDescriptor(SqlTypeDescriptor sqlTypeDescriptor) {
		if (sqlTypeDescriptor instanceof RsClobTypeDummyDescriptor) {
			return LongVarcharTypeDescriptor.INSTANCE;
		}
		return super.remapSqlTypeDescriptor(sqlTypeDescriptor);
	}
}
