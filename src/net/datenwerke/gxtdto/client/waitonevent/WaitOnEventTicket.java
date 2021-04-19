package net.datenwerke.gxtdto.client.waitonevent;

public class WaitOnEventTicket {

	private static int ticketCounter;
	
	private String uid;
	private String event;
	
	public WaitOnEventTicket(String event) {
		super();
		this.uid = "ticket_" + (ticketCounter++); //$NON-NLS-1$
		this.event = event;
	}


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	
	@Override
	public int hashCode() {
		return event.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof WaitOnEventTicket))
			return false;
		
		WaitOnEventTicket ticket = (WaitOnEventTicket) obj;
		
		return ticket.getUid().equals(uid) && ticket.getEvent().equals(event);
	}
}
