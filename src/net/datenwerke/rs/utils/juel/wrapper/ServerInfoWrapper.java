package net.datenwerke.rs.utils.juel.wrapper;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerInfoWrapper {

	public String hostName(){
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "unknown";
		}
	}
	
	public String hostAddress(){
		try{
			return InetAddress.getLocalHost().getHostAddress();
		} catch(Exception e){
			return "unknown";
		}
	}
}
