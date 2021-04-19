package net.datenwerke.rs.terminal.service.terminal.vfs.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;

public class PathHelper {

	private final String completePath;
	
	public PathHelper(String path){
		this.completePath = path;
	}

	/**
	 * @return the entire path including the object descriptor (/lala/abc*)
	 */
	public String getCompletePath() {
		return completePath;
	}
	
	public String getPath(){
		if(completePath.contains("/"))
			if(0 == completePath.lastIndexOf("/"))
				return "/";
			else
				return completePath.substring(0, completePath.lastIndexOf("/"));
		return "";
	}
	
	public boolean isValid(){
		return null != completePath;
	}
	
	public boolean isAbsolute(){
		return completePath.startsWith("/");
	}

	public List<String> getPathways() {
		if("".equals(completePath))
			return Collections.emptyList();
		
		String p = completePath;
		if(isAbsolute())
			p = p.substring(1);
		
		return Arrays.asList(p.split("/"));
	}

	public List<PathHelper> getPathHelperWays() {
		List<PathHelper> helpers = new ArrayList<PathHelper>();
		for(String way : getPathways())
			helpers.add(new PathHelper(way));
		return helpers;
	}

	public String getLastPathway() {
		if(!isValid())
			return "";
		
		if(completePath.contains("/"))	
			return completePath.substring(completePath.lastIndexOf("/") + 1);
		return completePath;
	}

	public boolean isDot() {
		return ".".equals(completePath);
	}

	public boolean isDotDot() {
		return "..".equals(completePath);
	}

	public boolean isWildcard() {
		return isValid() && completePath.contains("*");
	}

	public boolean isVirtualLocation() {
		return  isValid() && (completePath.contains("/#v-") || completePath.startsWith("#v-"));
	}
	
	public String getVirtualLocationName() throws VFSException{
		if(! isVirtualLocation())
			throw new VFSException("expected virtual location");
		
		for(PathHelper way : getPathHelperWays())
			if(way.isVirtualLocation())
				return way.getLastPathway().substring(3);
		
		throw new VFSException("could not find virtual name");
	}

	public String clearEscapedVirtualLocation() {
		if(completePath.startsWith("##v-"))
			return completePath.substring(1);
		return completePath;
	}

	public PathHelper getLastPathHelperWay() {
		return new PathHelper(getLastPathway());
	}

	public String getPathInVirtualSystem() {
		StringBuilder path = new StringBuilder();
		boolean virtual = false;
		for(PathHelper way : getPathHelperWays()){
			if(virtual)
				path.append("/");
			if(way.isVirtualLocation())
				virtual = true;
			if(virtual)
				path.append(way.getCompletePath());
		}
		return path.toString();
	}

	
	
	
}
