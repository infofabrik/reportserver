package net.datenwerke.rs.utils.misc;

public class IPUtils {

	/**
	 * 
	 * @param ip an IP in format 1.2.3.4/32
	 * @param contained an IP in format 1.2.3.4.
	 */
	public boolean contains(String ip, String contained){
		validate(ip, true);
		validate(contained, false);
		
		if(! ip.contains("/"))
			return ip.equals(contained);
		else {
			/* find relevant bits */
			int bits = Integer.parseInt(ip.substring(ip.indexOf("/")+1));
			
			String[] ipGroups = ip.substring(0,ip.indexOf("/")).split("\\.");
			String[] containedGroups = contained.split("\\.");
			
			long ipValue = 0;
			long containedValue = 0;
			for(int i = 0 ; i <= 3; i++){
				ipValue += (long) (Integer.parseInt(ipGroups[i]) * Math.pow(255, 3-i));
				containedValue += (long) (Integer.parseInt(containedGroups[i]) * Math.pow(255, 3-i));
			}
			
			if( bits > 32 || bits < 0)
				throw new IllegalArgumentException("no valid bitmask");
			
			long bitmask = (long)(((long)Math.pow(2, bits)-1) << (32 - bits));
			
			ipValue &= bitmask;
			containedValue &= bitmask;
			
			return ipValue == containedValue;
		}
		
	}

	private void validate(String ip, boolean bitmask) {
		boolean valid = ip.matches("\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b");
		if(! valid &&  bitmask)
			valid = ip.matches("\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\/\\d{1,2}\\b");
		
		if(! valid){
			throw new IllegalArgumentException("no valid ip address");
		}
	}
}
