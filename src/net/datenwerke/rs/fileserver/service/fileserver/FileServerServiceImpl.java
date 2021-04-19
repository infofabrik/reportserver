package net.datenwerke.rs.fileserver.service.fileserver;

import java.util.Iterator;
import java.util.List;

import org.hibernate.proxy.HibernateProxy;

import com.google.inject.Inject;

import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode__;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.fileserver.service.fileserver.vfs.FileServerVfs;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.helper.PathHelper;
import net.datenwerke.rs.utils.simplequery.PredicateType;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.exceptions.ViolatedSecurityException;
import net.datenwerke.security.service.treedb.SecuredTreeDBManagerImpl;
import net.datenwerke.security.service.treedb.actions.InsertAction;

public class FileServerServiceImpl extends SecuredTreeDBManagerImpl<AbstractFileServerNode> implements FileServerService {

	@Inject
	private TerminalService terminalService;
	
	@Inject
	private SecurityService securityService;
	
	@Override
	@QueryByAttribute(where=AbstractFileServerNode__.parent,type=PredicateType.IS_NULL)
	public List<AbstractFileServerNode> getRoots() {
		return null; // magic
	}
	
	@Override
	@SimpleQuery
	public List<AbstractFileServerNode> getAllNodes(){
		return null;
	}
	
	@Override
	@QueryById
	public AbstractFileServerNode getNodeById(long id) {
		return null; // magic
	}
	
	@Override
	public AbstractFileServerNode getNodeByPath(String path){
		return getNodeByPath(path, true);
	}
	
	@Override
	public AbstractFileServerNode getNodeByPath(String path, boolean checkRights) {
		if(path.startsWith("/"))
			path = "/" + FileServerVfs.FILESYSTEM_NAME + path;
		else
			path = "/" + FileServerVfs.FILESYSTEM_NAME + path + "/";
		
		try {
			Object object = terminalService.getObjectByLocation(path, checkRights);
			if(object instanceof AbstractFileServerNode)
				return (AbstractFileServerNode) object;
			return null;
		} catch (ObjectResolverException e) {
			return null;
		}
	}
	
	public AbstractFileServerNode copy(AbstractFileServerNode source, AbstractFileServerNode target, boolean deep, boolean checkRights) {
		if(checkRights)
			testCopyRights(source, target);
		AbstractFileServerNode copiedNode = copy(source, target);
		
		if(deep)
			for(AbstractFileServerNode child : source.getChildren())
				copy(child, copiedNode, true, checkRights);
		
		return copiedNode;
	}
	
	protected AbstractFileServerNode copy(AbstractFileServerNode source, AbstractFileServerNode target) {
		/* reuse existing folders */
		if(source instanceof FileServerFolder){
			for(FileServerFolder childfolder : target.getChildrenOfType(FileServerFolder.class)){
				if(source.getName().equals(childfolder.getName()))
					return childfolder;
			}
		}
		
		AbstractFileServerNode cloned = cloneNode(source);
		target.addChild(cloned);
		persist(cloned);
		merge(target);
		
		return cloned;
	}

	@Override
	public FileServerFile createFileAtLocation(VFSLocation location) {
		return createFileAtLocation(location, true);
	}
	
	@Override
	public FileServerFile createFileAtLocation(VFSLocation location, boolean checkRights) {
		return createFileAtLocation(location.getAbsolutePath(), checkRights);
	}
	
	@Override
	public FileServerFile createFileAtLocation(String locationPath) {
		return createFileAtLocation(locationPath, true);
	}
	
	@Override
	public FileServerFile createFileAtLocation(String locationPath, boolean checkRights) {
		PathHelper helper = new PathHelper(locationPath);
		
		Iterator<String> paths = helper.getPathways().iterator();
		
		Object root = getRoots().get(0);
		if(root instanceof HibernateProxy)
			root = ((HibernateProxy)root).getHibernateLazyInitializer().getImplementation();
		
		FileServerFolder parent = (FileServerFolder) root;
		
		String currentPath = "";
		while(paths.hasNext()){
			String path = paths.next();
			currentPath += "/" + path;

			if(paths.hasNext()){
				FileServerFolder newParent = null;
				try {
					newParent = (FileServerFolder) terminalService.getObjectByLocation(currentPath);
				} catch (ObjectResolverException e) {}

				if(null == newParent){
					if(checkRights && ! securityService.checkActions(parent, InsertAction.class))
						throw new ViolatedSecurityException("Cannot create file at: " + parent);
										
					newParent = new FileServerFolder();
					newParent.setName(path);
					if(null != parent)
						parent.addChild(newParent);
					persist(newParent);
					merge(parent);
				} 
					
				parent = newParent;
				
			} else {
				Object obj = null;
				try {
					obj = terminalService.getObjectByLocation(currentPath);
				} catch (ObjectResolverException e) {}
				
				if(null != obj && !(obj instanceof FileServerFile))
					throw new IllegalArgumentException("Expected null or file");
				if(null != obj && (obj instanceof FileServerFile))
					return (FileServerFile) obj;
				
				if(checkRights && ! securityService.checkActions(parent, InsertAction.class))
					throw new ViolatedSecurityException("Cannot create file at: " + parent);
				
				FileServerFile file = new FileServerFile();
				file.setName(path);
				parent.addChild(file);
				persist(file);
				merge(parent);
				return file;
			}
		}
		return null;
	}

	
}
