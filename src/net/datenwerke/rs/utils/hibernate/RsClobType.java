package net.datenwerke.rs.utils.hibernate;

import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.descriptor.java.StringTypeDescriptor;

public class RsClobType extends AbstractSingleColumnStandardBasicType<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -348450235477510768L;
	
	public static final RsClobType INSTANCE = new RsClobType();

	public RsClobType() {
		super( RsClobTypeDummyDescriptor.INSTANCE, StringTypeDescriptor.INSTANCE );
	}

	public String getName() {
		return "text";
	}
}
