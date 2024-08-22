package net.datenwerke.gf.client.uiutils.date;

import java.util.Date;

import com.google.gwt.event.logical.shared.HasAttachHandlers;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class FormulaEvaluatedEvent extends GwtEvent<FormulaEvaluatedEvent.Handler> {

   static Type<FormulaEvaluatedEvent.Handler> TYPE;
   private Date date;

   public static interface Handler extends EventHandler {
      public void handleFormulaEvaluatedEvent(FormulaEvaluatedEvent formulaEvaluatedEvent);
   }

   public FormulaEvaluatedEvent(Date date) {
      this.date = date;
   }

   public static <S extends HasAttachHandlers> void fire(S source, Date date) {
      if (TYPE != null) {
         FormulaEvaluatedEvent event = new FormulaEvaluatedEvent(date);
         source.fireEvent(event);
      }
   }

   public static Type<FormulaEvaluatedEvent.Handler> getType() {
      if (TYPE == null) {
         TYPE = new Type<FormulaEvaluatedEvent.Handler>();
      }
      return TYPE;
   }

   @Override
   public com.google.gwt.event.shared.GwtEvent.Type<Handler> getAssociatedType() {
      return TYPE;
   }

   @Override
   protected void dispatch(Handler handler) {
      handler.handleFormulaEvaluatedEvent(this);
   }

   public Date getDate() {
      return date;
   }

}
