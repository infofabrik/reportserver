package net.datenwerke.rs.core.client.contexthelp.dto;

public class ContextHelpStringData implements ContextHelpData<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6609359748289888124L;
	
	private String data;
	
	public ContextHelpStringData(){
	}

	public ContextHelpStringData(String data){
		this.data = data;
	}
	
	@Override
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
}
