package net.datenwerke.gxtdto.client.forms.simpleform;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitCompleteCallback;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTracker;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerService;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;


public class ChainedCallbackWrapper extends SimpleFormSubmissionCallback {
	
	@Inject
	private static SubmitTrackerService submitTrackerService;
	
	private final List<SimpleFormSubmissionCallback> callbacks;
	
	private boolean onErrorResume = false;

	public ChainedCallbackWrapper(List<SimpleFormSubmissionCallback> submissionCallbacks, SimpleForm form) {
		super(form);
		this.callbacks = submissionCallbacks;
		
	}

	public ChainedCallbackWrapper(List<SimpleFormSubmissionCallback> submissionCallbacks, SimpleForm form, boolean onErrorResume) {
		super(form);
		this.callbacks = submissionCallbacks;
		this.onErrorResume = onErrorResume;
	}

	@Override
	public void formSubmitted() {
		Queue<SimpleFormSubmissionCallback> queue = new LinkedList<SimpleFormSubmissionCallback>(callbacks);
		processQueue(queue, form);
	}
	
	
	private void processQueue(final Queue<SimpleFormSubmissionCallback> queue, final SimpleForm form){
		if(!queue.isEmpty()){
			SimpleFormSubmissionCallback cb = queue.poll();
			SubmitTracker tracker = submitTrackerService.createTracker();
			bindToToken(cb, tracker);
			tracker.beginTracking(new SubmitCompleteCallback() {
				
				@Override
				public void onSuccess() {
					processQueue(queue, form);
				}
				
				@Override
				public void onFailure(Throwable t) {
					if(onErrorResume)
						processQueue(queue, form);
				}
			});
			cb.formSubmitted();
		}
	}
	
	private SimpleFormSubmissionCallback bindToToken(final SimpleFormSubmissionCallback cb, SubmitTracker tracker){
		final SubmitTrackerToken token = tracker.createToken();
		
		SimpleFormCbProcessor cbp = new SimpleFormCbProcessor() {
			
			@Override
			public void cbSuccess() {
				 token.setCompleted();
			}
			
			@Override
			public void cbFailure(Throwable caught) {
				token.failure(caught);
			}
		};
		
		cb.setCbProcessor(cbp);
		
		return cb;
		
	}

}
