package net.datenwerke.rs.configservice.service.configservice;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ClassloaderHelper {

	@Inject
	public ClassloaderHelper() {
		// TODO Auto-generated constructor stub
	}
	
	
	private void addFileCp(File f) throws MalformedURLException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		addURLCp(f.toURI().toURL());
	}

	private void addURLCp(URL u) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		URLClassLoader sysloader = (URLClassLoader)ClassLoader.getSystemClassLoader();
		Class sysclass = URLClassLoader.class;

		java.lang.reflect.Method method = sysclass.getDeclaredMethod("addURL", URL.class);
		method.setAccessible(true);
		method.invoke(sysloader, u);
	}
	
	private URLClassLoader findWebappClassLoader() {
		
		URLClassLoader candidate = null;
		ClassLoader cl = this.getClass().getClassLoader();
		while(null != cl){
			if(cl instanceof URLClassLoader){
				for(URL u : ((URLClassLoader)cl).getURLs()){
					if(u.toString().contains("rscore.jar") || u.toString().contains("/RsCore/")){
						candidate = (URLClassLoader) cl;
					}
				}
			}

			cl = cl.getParent();
		}
		return candidate;
	}
}
