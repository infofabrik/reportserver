package net.datenwerke.rs.configservice.service.configservice;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.Selectors;
import org.apache.commons.vfs2.VFS;

public class LibDirClasspathHelper {

	private ConfigDirService configDirService;


	public LibDirClasspathHelper(ConfigDirService configDirService) {
		this.configDirService = configDirService;
	}
	
	
	public void loadLibs(){
		ClassLoader cl = getClassloader();

		if(null != cl) {
			try {
				if(configDirService.isEnabled()){
					File libDir = new File(configDirService.getConfigDir(), ConfigStartup.LIB_DIR);
					if(libDir.exists() && libDir.isDirectory()){
						try{
							FileObject fileObject = VFS.getManager().resolveFile(libDir.getCanonicalPath());
							FileObject[] files = fileObject.findFiles(Selectors.EXCLUDE_SELF);
							for(FileObject f : files){
								if("jar".equals(f.getName().getExtension().toLowerCase())){
									try {
										addUrl(cl, new File(f.getName().getPath()).toURI().toURL());
									} catch (IllegalAccessException e) {
										e.printStackTrace();
									} catch (IllegalArgumentException e) {
										e.printStackTrace();
									} catch (InvocationTargetException e) {
										e.printStackTrace();
									}
								}
							}
						}catch(IOException e){
							e.printStackTrace();
						}
					}
				}
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}

		}
	}

	public void addUrl(ClassLoader cl, URL url) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Method addURLMethod = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
		addURLMethod.setAccessible(true);
		addURLMethod.invoke(cl, url);
	}

	public ClassLoader getClassloader() {
		URLClassLoader candidate = null;
		ClassLoader cl = this.getClass().getClassLoader();
		while(null != cl){
			if(cl instanceof URLClassLoader){
				for(URL u : ((URLClassLoader)cl).getURLs()){
					if(u.toString().contains("reportserver.jar") || u.toString().contains("/classes/") ){
						candidate = (URLClassLoader) cl;
					}
				}
			}
			cl = cl.getParent();
		}
		cl = candidate;
		return cl;
	}
}
