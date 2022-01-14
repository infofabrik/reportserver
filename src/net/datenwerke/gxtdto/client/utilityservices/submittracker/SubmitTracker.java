package net.datenwerke.gxtdto.client.utilityservices.submittracker;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 *
 */
public class SubmitTracker {

   private final SubmitTrackerService service;

   private Collection<SubmitTrackerToken> tokens = new ArrayList<SubmitTrackerToken>();

   public SubmitTracker(SubmitTrackerService service) {
      this.service = service;
   }

   public SubmitTrackerToken createToken() {
      SubmitTrackerToken token = service.createToken();
      tokens.add(token);
      return token;
   }

   public void beginTracking(SubmitCompleteCallback callback) {
      service.trackSubmit(callback, tokens);
   }
}
