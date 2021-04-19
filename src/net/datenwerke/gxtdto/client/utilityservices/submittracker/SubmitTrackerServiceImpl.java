package net.datenwerke.gxtdto.client.utilityservices.submittracker;

import java.util.Arrays;
import java.util.Collection;

import com.google.gwt.user.client.Timer;

/**
 * 
 *
 */
public class SubmitTrackerServiceImpl implements SubmitTrackerService {

	public static final int RUNS_UNTIL_FAILURE = 120;
	private long ticketNr = 1;
	
	public SubmitTrackerServiceImpl(){
		
	}

	@Override
	public SubmitTracker createTracker(){
		return new SubmitTracker(this);
	}
	
	public synchronized SubmitTrackerToken createToken(){
		return new SubmitTrackerToken(++ticketNr);
	}
	
	@Override
	public void trackSubmit(SubmitCompleteCallback callback, SubmitTrackerToken... trackers){
		trackSubmit(callback, Arrays.asList(trackers));
	}
	
	@Override
	public void trackSubmit(final SubmitCompleteCallback callback, final Collection<SubmitTrackerToken> trackers){
		Timer timer = new Timer() {
			private int run = 0;
			
			@Override
			public void run() {
				if(++run > RUNS_UNTIL_FAILURE){
					this.cancel();
					callback.onFailure(new RuntimeException("timeout"));
				}
				
				for(SubmitTrackerToken tracker : trackers)
					if(! tracker.isRequestComplete())
						return;
				this.cancel();
				callback.onSuccess();
			}
		};
		
		timer.scheduleRepeating(500);
	}
	


}
