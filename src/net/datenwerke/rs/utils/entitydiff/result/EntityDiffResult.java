package net.datenwerke.rs.utils.entitydiff.result;

import java.util.ArrayList;
import java.util.List;

public class EntityDiffResult {

	private final Object a;
	private final Object b;
	
	private List<FieldDiffResult> fieldResults = new ArrayList<FieldDiffResult>();
	
	
	public EntityDiffResult(Object a, Object b) {
		super();
		this.a = a;
		this.b = b;
	}


	public Object getA() {
		return a;
	}

	public Object getB() {
		return b;
	}

	public List<FieldDiffResult> getFieldResults(){
		return fieldResults;
	}
	
	public void addFieldResult(FieldDiffResult fieldResult) {
		fieldResults.add(fieldResult);
	}
	
	public boolean isEqual(){
		if(null == a && null == b)
			return true;
		if(null != a && null == b)
			return false;
		if(null == a && null != b)
			return false;
		
		for(FieldDiffResult fieldResult : fieldResults)
			if(! fieldResult.isEqual())
				return false;

		return true;
	}

}
