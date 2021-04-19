package net.datenwerke.gxtdto.client.model;


import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper object to wrap Strings in a BaseModel e.g. for adding them to a store.
 *
 */
public class ListStringBaseModel implements DwModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2590606516954006171L;
	
	private List<String> value;
	
	public ListStringBaseModel() {
	}
	
	public ListStringBaseModel(List<String> value) {
		this();
		setValue(value);
	}
	
	public ListStringBaseModel(Object[] row) {
		this();
		ArrayList<String> list = new ArrayList<String>();
		for(Object cell : row)
			list.add(null == cell ? null : cell.toString());
		setValue(list);
	}

	public void setValue(List<String> value){
		this.value = value; 
	}
	
	public List<String> getValue(){
		return value;
	}


}
