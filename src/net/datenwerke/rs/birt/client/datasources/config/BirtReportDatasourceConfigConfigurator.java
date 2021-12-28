package net.datenwerke.rs.birt.client.datasources.config;

import java.util.List;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel.ToolBarEnhancer;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCodeMirror;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCEnumList;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceTargetTypeDto;
import net.datenwerke.rs.birt.client.datasources.dto.pa.BirtReportDatasourceConfigDtoPA;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.client.reportengines.locale.BirtMessages;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.interfaces.ReportAware;
import net.datenwerke.rs.core.client.reportmanager.provider.annotations.ReportManagerTreeFoldersAndReports;

public class BirtReportDatasourceConfigConfigurator implements DatasourceDefinitionConfigConfigurator {

   private Provider<UITree> reportManagerTreeProvider;
   private SimpleForm form;

   private String reportKey;
   private String targetTypeKey;
   private String targetKey;
   private String wrapperKey;

   @Inject
   public BirtReportDatasourceConfigConfigurator(
         @ReportManagerTreeFoldersAndReports Provider<UITree> reportManagerTree) {
      this.reportManagerTreeProvider = reportManagerTree;
   }

   @Override
   public Iterable<Widget> getOptionalAdditionalFormfields(DatasourceDefinitionConfigDto config,
         DatasourceDefinitionDto datasourceDefinitionDto, final DatasourceSelectionField datasourceSelectionField,
         final DatasourceContainerProviderDto datasourceContainerProvider) {
      form = SimpleForm.getInlineInstance();
      form.setLabelAlign(LabelAlign.TOP);
      form.setFieldWidth(190);
      form.beginFloatRow();

      BirtReportDatasourceConfigDto bConfig = (BirtReportDatasourceConfigDto) config;

      reportKey = form.addField(BirtReportDto.class, BirtReportDatasourceConfigDtoPA.INSTANCE.report(),
            BirtMessages.INSTANCE.datasourceReport(), new SFFCGenericTreeNode() {
               public UITree getTreeForPopup() {
                  return reportManagerTreeProvider.get();
               }
            });
      if (null != bConfig.getReport())
         form.setValue(reportKey, bConfig.getReport());

      form.setFieldWidth(130);
      targetTypeKey = form.addField(List.class, BirtReportDatasourceConfigDtoPA.INSTANCE.targetType(),
            BirtMessages.INSTANCE.datasourceType(), new SFFCEnumList(BirtReportDatasourceTargetTypeDto.class) {
               @Override
               public net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCStaticList.TYPE getType() {
                  return net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.lists.SFFCStaticList.TYPE.Radio;
               }
            });
      if (null != bConfig.getTargetType())
         form.setValue(targetTypeKey, bConfig.getTargetType());

      form.setFieldWidth(180);
      targetKey = form.addField(String.class, BirtReportDatasourceConfigDtoPA.INSTANCE.target(),
            BirtMessages.INSTANCE.datasourceTarget());
      if (null != bConfig.getTarget())
         form.setValue(targetKey, bConfig.getTarget());

      form.endRow();

      form.setFieldWidth(1);

      wrapperKey = form.addField(String.class, BirtReportDatasourceConfigDtoPA.INSTANCE.queryWrapper(),
            BirtMessages.INSTANCE.queryWrapper(), new SFFCCodeMirror() {
               @Override
               public int getHeight() {
                  return 150;
               }

               @Override
               public ToolBarEnhancer getEnhancer() {
                  return null;
               }

               @Override
               public String getLanguage() {
                  return "text/x-sql";
               }

               @Override
               public boolean lineNumbersVisible() {
                  return true;
               }

               @Override
               public int getWidth() {
                  return -1;
               }
            });
      if (null != bConfig.getQueryWrapper())
         form.setValue(wrapperKey, bConfig.getQueryWrapper());

      form.loadFields();

      for (String key : form.getFieldKeys()) {
         form.getResponsibleHook(key).addValueChangeHandler(new ValueChangeHandler() {
            @Override
            public void onValueChange(ValueChangeEvent event) {
               datasourceSelectionField.updateDatasourceConfig();
            }
         });
      }

      return form;
   }

   @Override
   public void inheritChanges(DatasourceDefinitionConfigDto config, DatasourceDefinitionDto datasourceDefinitionDto) {
      if (null == form)
         return;

      BirtReportDatasourceConfigDto bConfig = (BirtReportDatasourceConfigDto) config;
      bConfig.setQueryWrapper((String) form.getValue(wrapperKey));
      bConfig.setReport((BirtReportDto) form.getValue(reportKey));
      bConfig.setTarget((String) form.getValue(targetKey));
      bConfig.setTargetType((BirtReportDatasourceTargetTypeDto) form.getValue(targetTypeKey));
   }

   @Override
   public DatasourceDefinitionConfigDto createConfigObject(DatasourceDefinitionDto datasourceDefinitionDto,
         DatasourceContainerProviderDto datasourceContainerProvider) {
      BirtReportDatasourceConfigDto birtReportDatasourceConfigDto = new BirtReportDatasourceConfigDto();
      birtReportDatasourceConfigDto.setTargetType(BirtReportDatasourceTargetTypeDto.DATASET);
      if (datasourceContainerProvider instanceof ReportAware) {
         ReportDto report = ((ReportAware) datasourceContainerProvider).getReport();
         if (report instanceof BirtReportDto)
            birtReportDatasourceConfigDto.setReport((BirtReportDto) report);
      }
      if (datasourceContainerProvider instanceof ParameterDefinitionDto)
         birtReportDatasourceConfigDto.setTarget(((ParameterDefinitionDto) datasourceContainerProvider).getKey());

      return birtReportDatasourceConfigDto;
   }

   @Override
   public boolean consumes(DatasourceDefinitionDto datasourceDefinitionDto,
         DatasourceDefinitionConfigDto datasourceConfig) {
      return (null != datasourceConfig && datasourceDefinitionDto instanceof BirtReportDatasourceDefinitionDto
            && datasourceConfig instanceof BirtReportDatasourceConfigDto);
   }

   @Override
   public boolean isValid(DatasourceContainerDto container) {
      if (!consumes(container.getDatasource(), container.getDatasourceConfig()))
         return false;

      BirtReportDatasourceConfigDto config = (BirtReportDatasourceConfigDto) container.getDatasourceConfig();

      if (null == config.getReport())
         return false;

      return true;
   }

   @Override
   public boolean isReloadOnDatasourceChange() {
      return false;
   }

   @Override
   public Iterable<Widget> getDefaultAdditionalFormfields(DatasourceDefinitionConfigDto config,
         DatasourceDefinitionDto datasourceDefinitionDto, DatasourceSelectionField datasourceSelectionField,
         DatasourceContainerProviderDto datasourceContainerProvider) {
      return null;
   }

}
