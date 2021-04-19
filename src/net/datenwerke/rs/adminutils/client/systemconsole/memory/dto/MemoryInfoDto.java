package net.datenwerke.rs.adminutils.client.systemconsole.memory.dto;

import java.io.Serializable;

public class MemoryInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long used;
	private long free;
	private long total;
	private long max;
	
	private long freeMb;
	private long totalMb;
	private long maxMb;
	
	private long usedMb;
	
	private Long timestamp;
	
	public MemoryInfoDto() {
	}
	
	public MemoryInfoDto(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setSampleDate(long timestamp) {
		this.timestamp = timestamp;
	}
		
	public long getUsedMb() {
		return usedMb;
	}

	public void setUsedMb(long usedMb) {
		this.usedMb = usedMb;
	}

	public long getFreeMb() {
		return freeMb;
	}

	public void setFreeMb(long freeMb) {
		this.freeMb = freeMb;
	}

	public long getTotalMb() {
		return totalMb;
	}

	public void setTotalMb(long totalMb) {
		this.totalMb = totalMb;
	}

	public long getMaxMb() {
		return maxMb;
	}

	public void setMaxMb(long maxMb) {
		this.maxMb = maxMb;
	}
	
	
	public long getUsed() {
		return used;
	}
	public void setUsed(long used) {
		this.used = used;
	}
	public long getFree() {
		return free;
	}
	public void setFree(long free) {
		this.free = free;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public long getMax() {
		return max;
	}
	public void setMax(long max) {
		this.max = max;
	}
	
}
