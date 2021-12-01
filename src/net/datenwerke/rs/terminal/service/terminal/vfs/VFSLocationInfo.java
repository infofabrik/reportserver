package net.datenwerke.rs.terminal.service.terminal.vfs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class VFSLocationInfo {

	private List<VFSObjectInfo> childInfos = new ArrayList<VFSObjectInfo>();

	private VFSObjectInfo info;
	private VFSLocation location;
	
	public VFSLocationInfo(VFSLocation location, VFSObjectInfo info){
		this.location = location;
		this.info = info;
	}
	
	public VFSLocation getLocation() {
		return location;
	}
	
	public List<VFSObjectInfo> getChildInfos() {
		return childInfos;
	}

	public void setChildInfos(List<VFSObjectInfo> childInfos) {
		this.childInfos = childInfos;
	}
	
	public void addChildInfo(VFSObjectInfo childInfo){
		childInfos.add(childInfo);
	}
	
	public void addChildInfos(Collection<VFSObjectInfo> childInfos){
		this.childInfos.addAll(childInfos);
	}
	
	public void setInfo(VFSObjectInfo info) {
		this.info = info;
	}

	public VFSObjectInfo getInfo() {
		return info;
	}
	
	@Override
	public int hashCode() {
		return location.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof VFSLocationInfo))
			return false;
		return location.equals(((VFSLocationInfo)obj).getLocation());
	}


}
