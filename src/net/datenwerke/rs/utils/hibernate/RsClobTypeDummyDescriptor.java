package net.datenwerke.rs.utils.hibernate;

import org.hibernate.type.descriptor.sql.LongVarcharTypeDescriptor;

public abstract class RsClobTypeDummyDescriptor extends LongVarcharTypeDescriptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4480318908023526134L;

	@Override
	public boolean canBeRemapped() {
		return true;
	}


}
