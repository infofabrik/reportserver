package net.datenwerke.gxtdto.client.dialog.properties;

import java.util.Iterator;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitCompleteCallback;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTracker;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerService;
import net.datenwerke.gxtdto.client.utilityservices.submittracker.SubmitTrackerToken;

/**
 * 
 *
 */
public class RpcPropertiesDialog extends PropertiesDialog {

   protected final SubmitTrackerService submitTrackerService;

   protected String maskOnSubmit;
   protected boolean performSubmitsConsecutively = false;
   protected boolean continueOnFailure = false;

   protected SubmitCompleteCallback submitCompleteCallback = new SubmitCompleteCallback() {
      @Override
      public void onSuccess() {
      }

      @Override
      public void onFailure(Throwable t) {
      }
   };

   @Inject
   public RpcPropertiesDialog(SubmitTrackerService submitTrackerService) {
      super();

      /* store objects */
      this.submitTrackerService = submitTrackerService;

      /* init */
      setCloseOnSubmit(false);
   }

   @Override
   protected void notifyCardsOfSubmit() {
      if (null != maskOnSubmit)
         mask(maskOnSubmit);

      if (isPerformSubmitsConsecutively())
         submitConsecutively();
      else
         submitNormal();

   }

   protected void submitConsecutively() {
      Iterator<PropertiesDialogCard> cardIterator = cards.iterator();

      if (cardIterator.hasNext())
         submitConsecutively(cardIterator.next(), cardIterator);
      else
         submitConsecutivelyDone();
   }

   protected void submitConsecutivelyDone() {
      submitCompleteCallback.onSuccess();
   }

   protected void submitConsecutively(PropertiesDialogCard card, final Iterator<PropertiesDialogCard> cardIterator) {
      if (card instanceof RpcPropertiesDialogCard) {
         SubmitTrackerToken token = submitTrackerService.createToken();
         token.setSubmitCompleteCallback(new SubmitCompleteCallback() {
            @Override
            public void onSuccess() {
               if (cardIterator.hasNext())
                  submitConsecutively(cardIterator.next(), cardIterator);
               else
                  submitConsecutivelyDone();
            }

            @Override
            public void onFailure(Throwable t) {
               submitCompleteCallback.onFailure(t);
               if (continueOnFailure)
                  onSuccess();
            }
         });
         ((RpcPropertiesDialogCard) card).submitPressed(token);
      } else {
         card.submitPressed();
         if (cardIterator.hasNext())
            submitConsecutively(cardIterator.next(), cardIterator);
         else
            submitConsecutivelyDone();
      }
   }

   protected void submitNormal() {
      SubmitTracker tracker = submitTrackerService.createTracker();
      for (PropertiesDialogCard card : cards)
         if (card instanceof RpcPropertiesDialogCard)
            ((RpcPropertiesDialogCard) card).submitPressed(tracker.createToken());
         else
            card.submitPressed();

      tracker.beginTracking(submitCompleteCallback);
   }

   public void setSubmitCompleteCallback(SubmitCompleteCallback callback) {
      this.submitCompleteCallback = callback;
   }

   public void setMaskOnSubmit(String maskOnSubmit) {
      this.maskOnSubmit = maskOnSubmit;
   }

   public boolean isPerformSubmitsConsecutively() {
      return performSubmitsConsecutively;
   }

   public void setPerformSubmitsConsecutively(boolean performSubmitsConsecutively) {
      this.performSubmitsConsecutively = performSubmitsConsecutively;
   }

   public void continueOnFailure(boolean b) {
      continueOnFailure = b;
   }

}
