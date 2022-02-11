package net.datenwerke.rs.core.client.datasourcemanager.helper.forms.simpleform;

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
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCDatasourceDao;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;

/**
 * 
 *
 */
public class DatasourceSelectionFieldProvider extends FormFieldProviderHookImpl {

   private final DatasourceUIService datasourceService;

   private DatasourceSelectionField datasourceFieldCreator;

   @Inject
   public DatasourceSelectionFieldProvider(
         DatasourceUIService datasourceService
         ) {

      /* store objects */
      this.datasourceService = datasourceService;
   }

   @Override
   public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      if (configs.length == 0 || !(configs[0] instanceof SFFCGenericTreeNode))
         return false;

      return type.equals(DatasourceSelectionField.class);
   }

   @Override
   public Widget createFormField() {
      SFFCGenericTreeNode config = (SFFCGenericTreeNode) configs[0];

      Container wrapper = new VerticalLayoutContainer();

      SFFCDatasourceDao datasourceDao = getDatasourceDaoProvider();
      datasourceFieldCreator = datasourceService.getSelectionField(datasourceDao.getIcon(),
            wrapper, config.getTreeForPopup());

      FieldLabel label = new FieldLabel();
      if (null != form.getSField(name).getFieldLayoutConfig().getLabelText())
         label.setText(form.getSField(name).getFieldLayoutConfig().getLabelText());
      if (null != form.getSField(name).getFieldLayoutConfig().getLabelAlign())
         label.setLabelAlign(form.getSField(name).getFieldLayoutConfig().getLabelAlign());
      datasourceFieldCreator.setFieldLabel(label);

      datasourceFieldCreator.addSelectionField();
      datasourceFieldCreator.getSelectionField().setTreePanel(config.getTreeForPopup());

      datasourceFieldCreator
            .addValueChangeHandler(event -> ValueChangeEvent.fire(DatasourceSelectionFieldProvider.this, event.getValue()));

      installBlankValidation(datasourceFieldCreator.getSelectionField());

      return wrapper;
   }

   private SFFCDatasourceDao getDatasourceDaoProvider() {
      return Arrays.stream(configs).filter(config -> config instanceof SFFCDatasourceDao)
            .map(config -> ((SFFCDatasourceDao) config)).findAny()
            .orElseThrow(() -> new IllegalStateException("No SFFCDatasourceDao!"));
   }

   public void addFieldBindings(Object model, ValueProvider vp, Widget field) {
      /* get datasource container */
      DatasourceContainerProviderDto datasourceContainerProvider = (DatasourceContainerProviderDto) model;

      /* ask creator to init form binding */
      datasourceFieldCreator.initFormBinding(datasourceContainerProvider);
   }

   @Override
   public Object getValue(Widget field) {
      return datasourceFieldCreator.getDatasourceContainer().getDatasource();
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
