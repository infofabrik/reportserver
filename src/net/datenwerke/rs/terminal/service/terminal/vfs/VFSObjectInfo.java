package net.datenwerke.rs.terminal.service.terminal.vfs;


public class VFSObjectInfo {

	private Class<?> type;
	private String name;
	private String id;
	private boolean isFolder;

	public VFSObjectInfo(){
	}
	
	public VFSObjectInfo(Class<?> type, String name, String id, boolean isFolder) {
		setType(type);
		setName(name);
		setId(id);
		setFolder(isFolder);
	}
	
	public Class<?> getType() {
		return type;
	}
	
	public void setType(Class<?> type) {
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof VFSObjectInfo))
			return false;
		
		VFSObjectInfo info = (VFSObjectInfo) obj;
		
		if(null != id)
			return id.equals(info.getId()) && name.equals(info.getName()) && type.equals(info.getType());
		else if(null != info.getId())
			return false;
		
		return name.equals(info.getName()) && type.equals(info.getType());
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public boolean isFolder() {
		return isFolder;
	}
	
}
