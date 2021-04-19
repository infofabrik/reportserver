package net.datenwerke.rs.legacysaiku.server.rest.objects.repository;

import java.util.List;

import net.datenwerke.rs.legacysaiku.server.rest.objects.acl.enumeration.AclMethod;


public class RepositoryFolderObject implements IRepositoryObject {

	private Type type;
	private String name;
	private String id;
	private String path;
	private List<IRepositoryObject> repoObjects;
	private List<AclMethod> acl;

	public RepositoryFolderObject(String name, String id, String path, List<AclMethod> acl, List<IRepositoryObject> repoObjects) {
		this.type = Type.FOLDER;
		this.name = name;
		this.id = id;
		this.path = path;
		this.repoObjects = repoObjects;
		this.acl = acl;
	}
	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
	
	public String getPath() {
		return path;
	}
	
	public List<AclMethod> getAcl() {
		return acl;
	}
	
	public List<IRepositoryObject> getRepoObjects() {
		return repoObjects;
	}

}
