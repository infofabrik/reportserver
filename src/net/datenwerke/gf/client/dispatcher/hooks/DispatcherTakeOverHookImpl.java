package net.datenwerke.gf.client.dispatcher.hooks;

import net.datenwerke.gf.client.history.HistoryLocation;

import com.google.gwt.user.client.Window;

public abstract class DispatcherTakeOverHookImpl implements
		DispatcherTakeOverHook {

	@Override
	public boolean isActive() {
		HistoryLocation hLocation = getHistoryLocation();
		return getLocation().equals(hLocation.getLocation()) && checkParameters(hLocation);
	}
	
	protected boolean checkParameters(HistoryLocation hLocation) {
		return true;
	}

	public HistoryLocation getHistoryLocation(){
		String hash = Window.Location.getHash();
		if(null == hash || ! hash.startsWith("#"))
			return new HistoryLocation();
		
		HistoryLocation location = HistoryLocation.fromString(hash.substring(1));

		return location;
	}

	public abstract String getLocation();
	

}
