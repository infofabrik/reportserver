package net.datenwerke.gf.client.upload.simpleform;

import java.util.List;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FileUploadField;

import net.datenwerke.gf.client.upload.FileUploadUiService;
import net.datenwerke.gf.client.upload.HtmlUploadFieldContainer;
import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.html5.Html5FileUploadListener;
import net.datenwerke.gf.client.upload.html5.Html5FileUploadListener.UploadCallback;
import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.forms.locale.FormsMessages;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHookImpl;

public class FileUploadProvider extends FormFieldProviderHookImpl {

   private final FileUploadUiService fileUploadService;

   @Inject
   public FileUploadProvider(FileUploadUiService fileUploadService) {

      this.fileUploadService = fileUploadService;
   }

   @Override
   public boolean doConsumes(Class<?> type, SimpleFormFieldConfiguration... configs) {
      return type.equals(FileUpload.class);
   }

   @Override
   public Widget createFormField() {
      SFFCDnDFileUpload dndConfig = getDndConfig();
      if (null != dndConfig)
         return generateDndUploadField(dndConfig);

      SFFCFileUpload fileUploadConfig = getFileUploadConfig();
      if (null != fileUploadConfig)
         return generateFileUploadField(fileUploadConfig);

      FileUploadField field = new FileUploadField();
      field.setName(name);
      return field;
   }

   private Widget generateFileUploadField(SFFCFileUpload config) {
      VerticalLayoutContainer container = new VerticalLayoutContainer();

      if (config.enableHTML5()) {
         Component uploadField = fileUploadService.addCombinedUploadField(config.getProperties());
         container.add(uploadField, new VerticalLayoutData(-1, -1));
      } else {
         FileUploadField uploadField = fileUploadService.addBaseUploadField(config.getProperties(), container);
         container.add(uploadField, new VerticalLayoutData(-1, -1));
      }

      return container;
   }

   protected Widget generateDndUploadField(final SFFCDnDFileUpload dndConfig) {
      DwContentPanel panel = DwContentPanel.newHeadlessInstance();
      panel.setHeight(100);
      panel.addStyleName("filedrag");

      CenterLayoutContainer container = new CenterLayoutContainer();
      Label label = new Label(FormsMessages.INSTANCE.dropFilesHere());
      container.add(label);
      panel.add(container);

      new Html5FileUploadListener(new UploadCallback() {
         @Override
         public void fileUploaded(String name, long size, String base64) {
         }

         @Override
         public void allFilesUploaded(List<FileToUpload> list) {
            dndConfig.filesUploaded(list);
         }

         @Override
         public void fireOnDropStart(int nrOfFiles) {
         }
      }, panel);

      return panel;
   }

   protected SFFCDnDFileUpload getDndConfig() {
      for (SimpleFormFieldConfiguration config : configs)
         if (config instanceof SFFCDnDFileUpload)
            return (SFFCDnDFileUpload) config;
      return null;
   }

   protected SFFCFileUpload getFileUploadConfig() {
      for (SimpleFormFieldConfiguration config : configs)
         if (config instanceof SFFCFileUpload)
            return (SFFCFileUpload) config;
      return null;
   }

   @Override
   public Object getValue(Widget field) {
      SFFCFileUpload config = getFileUploadConfig();
      if (null != config && config.enableHTML5()) {
         VerticalLayoutContainer container = (VerticalLayoutContainer) field;
         HtmlUploadFieldContainer component = (HtmlUploadFieldContainer) container.getWidget(0);
         return component.getUploadFileName();
      }

      if (field instanceof FileUploadField)
         return ((FileUploadField) field).getValue();
      else {
         Container c = (Container) field;
         for (Widget w : c) {
            if (w instanceof FileUploadField)
               return ((FileUploadField) w).getValue();
         }
         return fileUploadService.getValueOf(c);
      }
   }

   @Override
   public void addFieldBindings(Object model, ValueProvider vp, Widget field) {
      // no field bindings
   }

   @Override
   public void removeFieldBindings(Object model, Widget field) {
      // no field bindings
   }

}
