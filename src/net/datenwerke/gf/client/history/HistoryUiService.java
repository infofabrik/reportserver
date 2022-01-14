package net.datenwerke.gf.client.history;

import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.history.dto.HistoryLinkDto;
import net.datenwerke.gxtdto.client.dtomanager.Dto;

public interface HistoryUiService extends ValueChangeHandler<String> {

   public interface JumpToObjectResultCallback {
      void setResult(Dto dto);
   }

   public interface JumpToObjectCallback {
      void getDtoTarget(JumpToObjectResultCallback callback);

      boolean haveToUpdate();
   }

   public abstract class JumpToObjectCallbackImpl implements JumpToObjectCallback {
      private Dto lastSelection;

      @Override
      final public boolean haveToUpdate() {
         return null == getDtoTarget() || lastSelection != getDtoTarget();
      }

      @Override
      final public void getDtoTarget(JumpToObjectResultCallback callback) {
         lastSelection = getDtoTarget();
         callback.setResult(lastSelection);
      }

      public abstract Dto getDtoTarget();
   }

   public void addHistoryCallback(String location, HistoryCallback callback);

   public MenuItem getJumpToMenuItem(JumpToObjectCallback jumpToObjectCallback);

   void fire(HistoryLinkDto link);

   public void jumpTo(Dto node);

}
