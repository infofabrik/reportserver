package net.datenwerke.rs.base.service.renderer;

public class RendererException extends Exception {

   private static final long serialVersionUID = -3671392323322755825L;

   public RendererException(String msg) {
      super(msg);
   }

   public RendererException(Throwable e) {
      super(e);
   }
}
