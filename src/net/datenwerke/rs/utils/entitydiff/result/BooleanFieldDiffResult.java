package net.datenwerke.rs.utils.entitydiff.result;

import java.lang.reflect.Field;

public class BooleanFieldDiffResult extends FieldDiffResult {

	protected final boolean equals;
	
	public BooleanFieldDiffResult(Field field, Object valueA, Object valueB, boolean equals) {
		super(field, valueA, valueB);
		
		this.equals = equals;
	}

	@Override
	public boolean isEqual() {
		return equals;
	}

}
