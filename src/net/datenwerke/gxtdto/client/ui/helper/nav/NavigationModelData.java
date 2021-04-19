package net.datenwerke.gxtdto.client.ui.helper.nav;

import java.io.Serializable;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.core.client.ValueProvider;


public class NavigationModelData<M> implements Serializable {
	
	public static ValueProvider<NavigationModelData<?>, String> nameValueProvider = new ValueProvider<NavigationModelData<?>, String>() {
		@Override
		public String getValue(NavigationModelData<?> object) {
			return object.getName();
		}

		@Override
		public void setValue(NavigationModelData<?> object, String value) {
			object.setName(value);
		}

		@Override
		public String getPath() {
			return "name";
		}
	}; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8783945078009624370L;
	
	private M component;
	private ImageResource icon;
	private String name;
	private int id;

	public NavigationModelData(String name, M component){
		this.name = name; 
		setModel(component);
	}

	public NavigationModelData(String name, ImageResource icon, M component){
		this.name = name; 
		setModel(component);
		setIcon(icon);
	}

	
	public NavigationModelData(int id, String name, ImageResource icon, M component){
		this.id = id; 
		this.name = name; 
		setModel(component);
		setIcon(icon);
	}

	public void setModel(M Model) {
		this.component = Model;
	}

	public M getModel() {
		return component;
	}

	public void setIcon(ImageResource icon) {
		this.icon = icon;
	}

	public ImageResource getIcon() {
		return icon;
	}
	
	public int getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
}

