package net.datenwerke.rs.core.client.datasinkmanager.helper.forms.simpleform;

import java.util.Arrays;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

import net.datenwerke.gf.client.treedb.selection.SingleTreeSelectionField;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCDatasinkDao;
import net.datenwerke.rs.core.client.datasinkmanager.DatasinkUIService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerProviderDto;
import net.datenwerke.rs.core.client.datasinkmanager.helper.forms.DatasinkSelectionField;

/**
 * 
 *
 */
public class DatasinkSelectionFieldProvider extends FormFieldProviderHookImpl {

   private final DatasinkUIService datasinkService;

   private DatasinkSelectionField datasinkFieldCreator;

   @Inject
   public DatasinkSelectionFieldProvider(DatasinkUIService datasinkService) {

      /* store objects */
      this.datasinkService = datasinkService;
   }

   @Override
   public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      if (configs.length == 0 || !(configs[0] instanceof SFFCGenericTreeNode))
         return false;

      return type.equals(DatasinkSelectionField.class);
   }

   @Override
   public Widget createFormField() {
      SFFCGenericTreeNode config = (SFFCGenericTreeNode) configs[0];

      Container wrapper = new VerticalLayoutContainer();

      SFFCDatasinkDao datasinkDao = getDatasinkDaoProvider();
      datasinkFieldCreator = datasinkService.getSelectionField(datasinkDao.getDatasinkDaoProvider(),
            datasinkDao.getIcon(), wrapper, config.getTreeForPopup());

      FieldLabel label = new FieldLabel();
      if (null != form.getSField(name).getFieldLayoutConfig().getLabelText())
         label.setText(form.getSField(name).getFieldLayoutConfig().getLabelText());
      if (null != form.getSField(name).getFieldLayoutConfig().getLabelAlign())
         label.setLabelAlign(form.getSField(name).getFieldLayoutConfig().getLabelAlign());
      datasinkFieldCreator.setFieldLabel(label);

      datasinkFieldCreator.addSelectionField();
      datasinkFieldCreator.getSelectionField().setTreePanel(config.getTreeForPopup());

      datasinkFieldCreator
            .addValueChangeHandler(event -> ValueChangeEvent.fire(DatasinkSelectionFieldProvider.this, event.getValue()));

      installBlankValidation(datasinkFieldCreator.getSelectionField());

      return wrapper;
   }

   private SFFCDatasinkDao getDatasinkDaoProvider() {
      return Arrays.stream(configs).filter(config -> config instanceof SFFCDatasinkDao)
            .map(config -> ((SFFCDatasinkDao) config)).findAny()
            .orElseThrow(() -> new IllegalStateException("No SFFCDatasinkDao!"));
   }

   public void addFieldBindings(Object model, ValueProvider vp, Widget field) {
      /* get datasink container */
      DatasinkContainerProviderDto datasinkContainerProvider = (DatasinkContainerProviderDto) model;

      /* ask creator to init form binding */
      datasinkFieldCreator.initFormBinding(datasinkContainerProvider);
   }

   @Override
   public Object getValue(Widget field) {
      return datasinkFieldCreator.getDatasinkContainer().getDatasink();
   }

   public void removeFieldBindings(Object model, Widget field) {
      throw new RuntimeException("not yet implemented"); //$NON-NLS-1$
   }

   @Override
   public boolean isDecorateable() {
      return false;
   }

   public static SingleTreeSelectionField extractSingleTreeSelectionField(Widget widget) {
      if (!(widget instanceof VerticalLayoutContainer))
         throw new IllegalArgumentException("No valid widget passed");

      VerticalLayoutContainer vlc = (VerticalLayoutContainer) widget;

      if (null == vlc.getWidget(0) || !(vlc.getWidget(0) instanceof FieldLabel))
         throw new IllegalArgumentException("No valid widget passed");

      FieldLabel fl = (FieldLabel) vlc.getWidget(0);

      if (!(fl.getWidget() instanceof SingleTreeSelectionField))
         throw new IllegalArgumentException("No valid widget passed");

      return (SingleTreeSelectionField) fl.getWidget();

   }

}
