package net.datenwerke.rs.utils.hibernate;

import java.sql.Types;

import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.type.descriptor.sql.LongVarcharTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

public class MySQL5Dialect extends org.hibernate.dialect.MySQL5Dialect {
	
	public MySQL5Dialect() {
		super();
		registerColumnType( Types.BOOLEAN, "BIT(1)" );
	}

	public String getCastTypeName(int code) {
		if ( code==Types.INTEGER || code == Types.BIGINT ) { return "signed"; }
		else if ( code==Types.VARCHAR ) { return "char"; }
		else if ( code==Types.VARBINARY ) { return "binary"; }
		else { return super.getCastTypeName( code ); }
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
