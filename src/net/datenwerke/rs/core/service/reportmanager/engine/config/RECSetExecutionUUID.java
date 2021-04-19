package net.datenwerke.rs.core.service.reportmanager.engine.config;

public class RECSetExecutionUUID implements ReportExecutionConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1862083712620846442L;
	
	
	private String uuid;

	public RECSetExecutionUUID(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RECSetExecutionUUID other = (RECSetExecutionUUID) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
}
