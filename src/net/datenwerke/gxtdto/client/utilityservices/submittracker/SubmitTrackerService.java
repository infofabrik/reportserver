package net.datenwerke.gxtdto.client.utilityservices.submittracker;

import java.util.Collection;

/**
 * Service that allows to track multiple server calls and be called back when
 * they are done.
 *
 */
public interface SubmitTrackerService {

   public SubmitTrackerToken createToken();

   public abstract void trackSubmit(final SubmitCompleteCallback callback, final Collection<SubmitTrackerToken> tokens);

   public abstract void trackSubmit(SubmitCompleteCallback callback, SubmitTrackerToken... tokens);

   public abstract SubmitTracker createTracker();
}
