package net.datenwerke.rs.dsbundle.client.dsbundle.hooker;

import java.util.Map;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.inject.Inject;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.form.ComboBox;

import net.datenwerke.gxtdto.client.baseex.widget.form.DwComboBox;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.model.KeyValueBaseModel;
import net.datenwerke.gxtdto.client.utils.modelkeyprovider.BasicObjectModelKeyProvider;
import net.datenwerke.rs.dsbundle.client.dsbundle.DatasourceBundleDao;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.login.hooks.AuthenticatorWindowExtraOptionHook;

public class AuthenticatorWindowExtraOptionHooker implements AuthenticatorWindowExtraOptionHook {

   private final DatasourceBundleDao datasourceBundle;
   private final EnterpriseUiService enterpriseService;

   @Inject
   public AuthenticatorWindowExtraOptionHooker(DatasourceBundleDao datasourceBundle,
         EnterpriseUiService enterpriseService) {
      this.datasourceBundle = datasourceBundle;
      this.enterpriseService = enterpriseService;
   }

   @Override
   public ExtraOptionPosition getPosition() {
      return ExtraOptionPosition.FIELD;
   }

   @Override
   public void configure(Container panel) {
      if (!enterpriseService.isEnterprise())
         return;

      final ListStore<KeyValueBaseModel<String>> store = new ListStore<KeyValueBaseModel<String>>(
            new BasicObjectModelKeyProvider<KeyValueBaseModel<String>>());
      final ComboBox<KeyValueBaseModel<String>> cb = new DwComboBox<KeyValueBaseModel<String>>(store,
            new LabelProvider<KeyValueBaseModel<String>>() {
               @Override
               public String getLabel(KeyValueBaseModel<String> item) {
                  return item.getValue();
               }
            });

      datasourceBundle.getBundleSelectorConfiguration(new AsyncCallback<Map<String, String>>() {

         @Override
         public void onSuccess(Map<String, String> result) {
            for (String s : result.keySet()) {

               KeyValueBaseModel<String> model = new KeyValueBaseModel<String>();
               model.setKey(s);
               model.setValue(result.get(s));
               store.add(model);
            }
            if (store.size() > 0)
               cb.setValue(store.get(0));
         }

         @Override
         public void onFailure(Throwable caught) {
         }
      });

      cb.addSelectionHandler(new SelectionHandler<KeyValueBaseModel<String>>() {
         @Override
         public void onSelection(SelectionEvent<KeyValueBaseModel<String>> event) {
            datasourceBundle.setSelectedBundle(event.getSelectedItem().getKey(), new RsAsyncCallback<Void>());
         }
      });

      cb.setTypeAhead(false);
      cb.setForceSelection(true);
      cb.setEditable(false);
      cb.setAllowBlank(false);
      cb.setTriggerAction(TriggerAction.ALL);
      cb.setWidth(220);
      cb.addStyleName("rs-login-db");

      cb.setStore(store);

      HTML dbIcon = new HTML(BaseIcon.DATABASE.toSafeHtml());
      dbIcon.addStyleName("rs-login-db-i");
      dbIcon.setWidth("20px");
      HBoxLayoutContainer uContainer = new HBoxLayoutContainer();
      uContainer.setHeight(30);
      uContainer.setWidth(250);
      uContainer.add(dbIcon);
      uContainer.add(cb);
      ((FlowLayoutContainer) panel).add(uContainer, new MarginData(0, 0, 10, 100));
   }

}
