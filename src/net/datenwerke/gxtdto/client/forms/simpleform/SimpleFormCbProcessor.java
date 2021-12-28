package net.datenwerke.gxtdto.client.forms.simpleform;

public interface SimpleFormCbProcessor {

   public void cbFailure(Throwable caught);

   public void cbSuccess();

}
