package net.datenwerke.gxtdto.client.objectinformation.hooks;

import java.util.Date;

import com.google.gwt.resources.client.ImageResource;


public abstract class ObjectInfoKeyInfoProviderImpl<A> implements  ObjectInfoKeyInfoProvider {

	@Override
	final public String getName(Object object){
		return doGetName((A)object);
	}
	
	protected abstract String doGetName(A object);

	@Override
	final public String getDescription(Object object){
		return doGetDescription((A)object);
	}

	protected abstract String doGetDescription(A object);
	
	@Override
	final public String getType(Object object){
		return doGetType((A)object);
	}
	
	protected abstract String doGetType(A object);
	
	@Override
	final public Date getLastUpdatedOn(Object object){
		return doGetLastUpdatedOn((A)object);
	}
	
	protected abstract Date doGetLastUpdatedOn(A object);
	
	@Override
	final public Date getCreatedOn(Object object){
		return doGetCreatedOn((A)object);
	}
	
	protected abstract Date doGetCreatedOn(A object);
	
	
	@Override
	public ImageResource getIconSmall(Object object) {
		return doGetIconSmall((A)object);
	}

	abstract protected ImageResource doGetIconSmall(A object);
}
