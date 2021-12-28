package net.datenwerke.gxtdto.client.utilityservices.submittracker;

public class SubmitTrackerToken {

   private long id;
   private boolean requestComplete;
   private SubmitCompleteCallback submitCompleteCallback = null;

   public SubmitTrackerToken(long id) {
      this.id = id;
   }

   public long getId() {
      return id;
   }

   /**
    * Returns a hash code for this <code>Long</code>. The result is the exclusive
    * OR of the two halves of the primitive <code>long</code> value held by this
    * <code>Long</code> object. That is, the hashcode is the value of the
    * expression: <blockquote>
    * 
    * <pre>
    * (int) (this.longValue() ^ (this.longValue() &gt;&gt;&gt; 32))
    * </pre>
    * 
    * </blockquote>
    *
    * @return a hash code value for this object.
    */
   public int hashCode() {
      return (int) (id ^ (id >>> 32));
   }

   /**
    * Compares this object to the specified object. The result is <code>true</code>
    * if and only if the argument is not <code>null</code> and is a
    * <code>Long</code> object that contains the same <code>long</code> value as
    * this object.
    *
    * @param obj the object to compare with.
    * @return <code>true</code> if the objects are the same; <code>false</code>
    *         otherwise.
    */
   public boolean equals(Object obj) {
      if (obj instanceof Long) {
         return id == ((Long) obj).longValue();
      }
      return false;
   }

   public void setCompleted() {
      if (null != submitCompleteCallback)
         submitCompleteCallback.onSuccess();
      this.requestComplete = true;
   }

   public void failure(Throwable t) {
      if (null != submitCompleteCallback)
         submitCompleteCallback.onFailure(t);
      this.requestComplete = true;
   }

   public boolean isRequestComplete() {
      return requestComplete;
   }

   public void setSubmitCompleteCallback(SubmitCompleteCallback submitCompleteCallback) {
      this.submitCompleteCallback = submitCompleteCallback;
   }

   public SubmitCompleteCallback getSubmitCompleteCallback() {
      return submitCompleteCallback;
   }
}
