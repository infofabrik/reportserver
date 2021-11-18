package net.datenwerke.rs.utils.hibernate;

import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.type.descriptor.sql.LongVarcharTypeDescriptor;
import org.hibernate.type.descriptor.sql.SqlTypeDescriptor;

public class SQLServer2008Dialect extends org.hibernate.dialect.SQLServer2008Dialect {

	public SQLServer2008Dialect() {
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
	
    /*
     * Because of a deadlock (RS-5196) we use the old Hibernate 5.0.3 lock hint
     * behavior, taken from here:
     * https://github.com/hibernate/hibernate-orm/blob/5.0.3/hibernate-core/src/main
     * /java/org/hibernate/dialect/SQLServer2005Dialect.java
     */
	@Override
    public String appendLockHint(LockOptions lockOptions, String tableName) {
        // NOTE : since SQLServer2005 the nowait hint is supported
        if ( lockOptions.getLockMode() == LockMode.UPGRADE_NOWAIT ) {
            return tableName + " with (updlock, rowlock, nowait)";
        }

        final LockMode mode = lockOptions.getLockMode();
        final boolean isNoWait = lockOptions.getTimeOut() == LockOptions.NO_WAIT;
        final String noWaitStr = isNoWait ? ", nowait" : "";
        switch ( mode ) {
            case UPGRADE:
            case PESSIMISTIC_WRITE:
            case WRITE: {
                return tableName + " with (updlock, rowlock" + noWaitStr + " )";
            }
            case PESSIMISTIC_READ: {
                return tableName + " with (holdlock, rowlock" + noWaitStr + " )";
            }
            default: {
                return tableName;
            }
        }
    }
}
