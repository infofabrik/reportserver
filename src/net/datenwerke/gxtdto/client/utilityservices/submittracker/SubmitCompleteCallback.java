package net.datenwerke.gxtdto.client.utilityservices.submittracker;

public interface SubmitCompleteCallback {

   public void onSuccess();

   public void onFailure(Throwable t);
}
