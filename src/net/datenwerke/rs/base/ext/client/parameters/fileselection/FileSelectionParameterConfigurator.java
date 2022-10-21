package net.datenwerke.rs.base.ext.client.parameters.fileselection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.sencha.gxt.data.shared.event.StoreAddEvent;
import com.sencha.gxt.data.shared.event.StoreClearEvent;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent;
import com.sencha.gxt.data.shared.event.StoreFilterEvent;
import com.sencha.gxt.data.shared.event.StoreHandlers;
import com.sencha.gxt.data.shared.event.StoreRecordChangeEvent;
import com.sencha.gxt.data.shared.event.StoreRemoveEvent;
import com.sencha.gxt.data.shared.event.StoreSortEvent;
import com.sencha.gxt.data.shared.event.StoreUpdateEvent;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gf.client.download.dto.DownloadProperties;
import net.datenwerke.gf.client.fileselection.FileSelectionConfig;
import net.datenwerke.gf.client.fileselection.FileSelectionWidget;
import net.datenwerke.gf.client.fileselection.FileSelectorSourceImpl;
import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gf.client.treedb.selection.SelectionFilter;
import net.datenwerke.gf.client.upload.fileselectionsource.FileUploadSource;
import net.datenwerke.gf.client.upload.filter.FileUploadFilter;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.parameters.locale.RsMessages;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterDefinitionDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.FileSelectionParameterInstanceDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.SelectedParameterFileDto;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.decorator.FileSelectionParameterInstanceDtoDec;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.pa.FileSelectionParameterDefinitionDtoPA;
import net.datenwerke.rs.base.ext.client.parameters.fileselection.locale.FileSelectionParameterMessages;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfiguratorImpl;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterProposalDto;
import net.datenwerke.rs.core.client.parameters.helper.DefaultValueSetter;
import net.datenwerke.rs.core.client.parameters.helper.ParameterFieldWrapperForFrontend;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.helper.fileselection.FileServerFileSelectorSource;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskGeneralReferenceDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.helper.fileselector.TeamSpaceFileSelectorSource;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

@Singleton
public class FileSelectionParameterConfigurator
      extends ParameterConfiguratorImpl<FileSelectionParameterDefinitionDto, FileSelectionParameterInstanceDto> {

   private static final String FILE_TYPE = "fileselectionparameter_selectiontype";

   private final Provider<FileSelectionWidget> selectionWidgetProvider;
   private final Provider<FileServerFileSelectorSource> fileServerSourceProvider;

   @Inject
   public FileSelectionParameterConfigurator(Provider<FileSelectionWidget> fileSelectionWidgetProvider,
         Provider<FileServerFileSelectorSource> fileServerSourceProvider) {
      this.selectionWidgetProvider = fileSelectionWidgetProvider;
      this.fileServerSourceProvider = fileServerSourceProvider;
   }

   @Override
   public Widget getEditComponentForDefinition(FileSelectionParameterDefinitionDto definition, ReportDto report) {
      final SimpleForm form = SimpleForm.getInlineInstance();

      form.beginRow(1, -1);
      form.addField(Integer.class, FileSelectionParameterDefinitionDtoPA.INSTANCE.width(),
            BaseMessages.INSTANCE.width());
      form.addField(Integer.class, FileSelectionParameterDefinitionDtoPA.INSTANCE.height(),
            BaseMessages.INSTANCE.height());
      form.endRow();

      form.setLabelAlign(LabelAlign.LEFT);
      form.addField(Boolean.class, FileSelectionParameterDefinitionDtoPA.INSTANCE.allowFileUpload(),
            RsMessages.INSTANCE.enableFileUpload());
      form.addField(Boolean.class, FileSelectionParameterDefinitionDtoPA.INSTANCE.allowTeamSpaceSelection(),
            RsMessages.INSTANCE.enableTeamSpaceSelection());
      form.addField(Boolean.class, FileSelectionParameterDefinitionDtoPA.INSTANCE.allowFileServerSelection(),
            RsMessages.INSTANCE.enableFileServerSelection());

      form.addField(Integer.class, FileSelectionParameterDefinitionDtoPA.INSTANCE.minNumberOfFiles(),
            RsMessages.INSTANCE.minNumberOfFiles());
      form.addField(Integer.class, FileSelectionParameterDefinitionDtoPA.INSTANCE.maxNumberOfFiles(),
            RsMessages.INSTANCE.maxNumberOfFiles());

      form.setLabelAlign(LabelAlign.TOP);
      form.addField(String.class, FileSelectionParameterDefinitionDtoPA.INSTANCE.allowedFileExtensions(),
            RsMessages.INSTANCE.allowedFileExtensions());

      form.setFieldWidth(0.5);
      form.addField(String.class, FileSelectionParameterDefinitionDtoPA.INSTANCE.fileSizeString(),
            RsMessages.INSTANCE.maximalFileSize());
      form.setFieldWidth(1);

      form.addField(Boolean.class, FileSelectionParameterDefinitionDtoPA.INSTANCE.allowDownload(),
            RsMessages.INSTANCE.enableDownload());

//		form.addCondition(modeKey, radioCheckCond, new ShowHideFieldAction(boxPackColSizeKey));

      /* bind definition */
      form.bind(definition);

      return form;
   }

   @Override
   public Widget doGetEditComponentForInstance(final FileSelectionParameterInstanceDto instance,
         Collection<ParameterInstanceDto> relevantInstances, final FileSelectionParameterDefinitionDto definition,
         boolean initial, int labelWidth, String executeReportToken, ReportDto report) {
      final FileSelectionWidget selectionWidget = selectionWidgetProvider.get();
      selectionWidget.setWidth(definition.getWidth());
      selectionWidget.setGridHeight(definition.getHeight());

      Integer minFiles = definition.getMinNumberOfFiles();
      Integer maxFiles = definition.getMaxNumberOfFiles();
      if (null != minFiles)
         selectionWidget.setMinFiles(minFiles);
      if (null != maxFiles)
         selectionWidget.setMaxFiles(maxFiles);

      /* configure uploads */
      if (definition.isAllowFileUpload()) {
         FileUploadSource uploadSource = new FileUploadSource();
         uploadSource.setUploadFilter(new FileUploadFilter() {
            @Override
            public String doProcess(String name, long size, String base64) {
               if (size > 0 && definition.getFileSize() > 0 && definition.getFileSize() < size)
                  return FileSelectionParameterMessages.INSTANCE.maxFileSizeExceeded(definition.getFileSizeString());

               String extensions = definition.getAllowedFileExtensions();
               if (null != extensions && !"".equals(extensions.trim())) {
                  for (String ext : extensions.split(","))
                     if (name.endsWith(ext.trim()))
                        return null;
                  return FileSelectionParameterMessages.INSTANCE.mismatchingFileExtension(extensions);
               }

               return null;
            }
         });

         selectionWidget.addSource(uploadSource);
      }

      /* configure teamspace */
      if (definition.isAllowTeamSpaceSelection()) {
         TeamSpaceFileSelectorSource tsSource = new TeamSpaceFileSelectorSource();
         selectionWidget.addSource(tsSource);
         final String extensions = definition.getAllowedFileExtensions();
         if (null != extensions && !"".equals(extensions.trim())) {
            tsSource.setSelectionFilter(new SelectionFilter() {
               @Override
               public String allowSelectionOf(AbstractNodeDto node) {
                  if (!(node instanceof TsDiskGeneralReferenceDto))
                     return FileSelectionParameterMessages.INSTANCE.mismatchingFileExtension(extensions);
                  String name = ((TsDiskGeneralReferenceDto) node).getName();
                  for (String ext : extensions.split(","))
                     if (null != name && name.endsWith(ext.trim()))
                        return null;
                  return FileSelectionParameterMessages.INSTANCE.mismatchingFileExtension(extensions);
               }
            });
         }
      }

      /* configure file selection */
      if (definition.isAllowFileServerSelection()) {
         FileServerFileSelectorSource fsSource = fileServerSourceProvider.get();
         selectionWidget.addSource(fsSource);
         final String extensions = definition.getAllowedFileExtensions();
         if (null != extensions && !"".equals(extensions.trim())) {
            fsSource.setSelectionFilter(new SelectionFilter() {
               @Override
               public String allowSelectionOf(AbstractNodeDto node) {
                  if (!(node instanceof FileServerFileDto))
                     return FileSelectionParameterMessages.INSTANCE.mismatchingFileExtension(extensions);
                  String name = ((FileServerFileDto) node).getName();
                  for (String ext : extensions.split(","))
                     if (null != name && name.endsWith(ext.trim()))
                        return null;
                  return FileSelectionParameterMessages.INSTANCE.mismatchingFileExtension(extensions);
               }
            });

         }
      }

      /* own source */
      selectionWidget.addSource(new FileSelectorSourceImpl() {
         @Override
         public boolean consumes(SelectedFileWrapper value) {
            return null != value && FILE_TYPE.equals(value.getType());
         }

         @Override
         public boolean isDownloadEnabled(SelectedFileWrapper item) {
            if (!definition.isAllowDownload())
               return false;

            SelectedParameterFileDto file = (SelectedParameterFileDto) item.getOriginalDto();
            if (null != file.getTeamSpaceFile() && !(file.getTeamSpaceFile() instanceof TsDiskGeneralReferenceDto))
               return false;

            return true;
         }

         @Override
         public boolean isEditNameEnabled(SelectedFileWrapper selectedItem) {
            return true;
         }

         @Override
         public String getTypeDescription(SelectedFileWrapper value) {
            if (null == value)
               return null;

            SelectedParameterFileDto file = (SelectedParameterFileDto) value.getOriginalDto();
            if (null != file.getUploadedFile())
               return RsMessages.INSTANCE.uploadedFile();
            if (null != file.getTeamSpaceFile())
               return RsMessages.INSTANCE.teamspaceFile();
            if (null != file.getFileServerFile())
               return RsMessages.INSTANCE.fileserverFile();

            return super.getTypeDescription(value);
         }

         @Override
         public DownloadProperties getDownloadPropertiesFor(SelectedFileWrapper selectedItem) {
            SelectedParameterFileDto file = (SelectedParameterFileDto) selectedItem.getOriginalDto();

            DownloadProperties properties = new DownloadProperties(String.valueOf(file.getId()),
                  FileSelectionParameterUiModule.SELECTED_FILE_DOWNLOAD_HANDLER);
            return properties;

         }
      });

      /* construct widget */
      FileSelectionConfig config = new FileSelectionConfig(FileSelectionParameterUiModule.FILE_SELECTION_HANDLER);
      config.addMetadata(FileSelectionParameterUiModule.PARAMETER_INSTANCE_ID, String.valueOf(instance.getId()));
      selectionWidget.build(config);

      /* add files */
      for (SelectedParameterFileDto file : instance.getSelectedFiles()) {
         SelectedFileWrapper wrapper = new SelectedFileWrapper(FILE_TYPE, file, file.getName());
         selectionWidget.add(wrapper);
      }

      /* disable edit form, if parameter is not editable */
      if (!definition.isEditable())
         selectionWidget.disable();
      else {
         selectionWidget.addStoreHandler(new StoreHandlers<SelectedFileWrapper>() {

            @Override
            public void onAdd(StoreAddEvent<SelectedFileWrapper> event) {
               updateData();
            }

            @Override
            public void onRemove(StoreRemoveEvent<SelectedFileWrapper> event) {
               updateData();
            }

            @Override
            public void onFilter(StoreFilterEvent<SelectedFileWrapper> event) {
            }

            @Override
            public void onClear(StoreClearEvent<SelectedFileWrapper> event) {
               updateData();
            }

            @Override
            public void onUpdate(StoreUpdateEvent<SelectedFileWrapper> event) {
               updateData();
            }

            @Override
            public void onDataChange(StoreDataChangeEvent<SelectedFileWrapper> event) {
               updateData();
            }

            @Override
            public void onRecordChange(StoreRecordChangeEvent<SelectedFileWrapper> event) {
               updateData();
            }

            @Override
            public void onSort(StoreSortEvent<SelectedFileWrapper> event) {
            }

            private void updateData() {
               ArrayList<SelectedFileWrapper> data = new ArrayList<SelectedFileWrapper>(
                     selectionWidget.getFileStore().getAll());
               ((FileSelectionParameterInstanceDtoDec) instance).setTempSelection(data);
               instance.setStillDefault(false);

               instance.fireObjectChangedEvent();
            }

         });
      }

      return new ParameterFieldWrapperForFrontend(definition, instance, selectionWidget, labelWidth,
            new DefaultValueSetter() {
               @Override
               public void setDefaultValue() {
                  setDefaultValueInInstance(instance, definition);
               }
            });
   }

   @Override
   protected void doSetDefaultValueInInstance(FileSelectionParameterInstanceDto instance,
         FileSelectionParameterDefinitionDto definition) {
   }

   public String getName() {
      return ReportmanagerMessages.INSTANCE.fileSelectionParameterName();
   }

   @Override
   protected FileSelectionParameterDefinitionDto doGetNewDto() {
      return new FileSelectionParameterDefinitionDto();
   }

   @Override
   public boolean consumes(Class<? extends ParameterDefinitionDto> type) {
      return FileSelectionParameterDefinitionDto.class.equals(type);
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.ARROW_UP.toImageResource();
   }

   @Override
   public boolean canHandle(ParameterProposalDto proposal) {
      return false;
   }

   @Override
   public ParameterDefinitionDto getNewDto(ParameterProposalDto proposal, ReportDto report) {
      FileSelectionParameterDefinitionDto definition = (FileSelectionParameterDefinitionDto) getNewDto(report);

      return definition;
   }

   @Override
   public List<String> validateParameter(FileSelectionParameterDefinitionDto definition,
         FileSelectionParameterInstanceDto instance, Widget widget) {
      List<String> errList = super.validateParameter(definition, instance, widget);

      if (!((FileSelectionWidget) ((ParameterFieldWrapperForFrontend) widget).getComponent()).isValid())
         errList.add(RsMessages.INSTANCE.invalidParameter(definition.getName()));

      return errList;
   }

}
