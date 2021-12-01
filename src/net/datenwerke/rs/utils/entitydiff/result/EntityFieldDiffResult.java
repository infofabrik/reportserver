package net.datenwerke.rs.utils.entitydiff.result;

import java.lang.reflect.Field;

public class EntityFieldDiffResult extends FieldDiffResult {

	protected final EntityDiffResult diffResult;
	
	public EntityFieldDiffResult(Field field, Object valueA, Object valueB, EntityDiffResult diffResult) {
		super(field, valueA, valueB);
		
		this.diffResult = diffResult;
	}

	public EntityDiffResult getDiffResult() {
		return diffResult;
	}

	@Override
	public boolean isEqual() {
		return diffResult.isEqual();
	}

}
