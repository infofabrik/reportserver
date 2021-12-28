package net.datenwerke.rs.scriptreport.client.scriptreport.parameters;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfiguratorImpl;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFileDto;
import net.datenwerke.rs.fileserver.client.fileserver.provider.annotations.FileServerTreeBasic;
import net.datenwerke.rs.scriptreport.client.scriptreport.locale.ScriptReportMessages;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.pa.ScriptParameterDefinitionDtoPA;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
@Singleton
public class ScriptParameterConfigurator
      extends ParameterConfiguratorImpl<ScriptParameterDefinitionDto, ScriptParameterInstanceDto> {

   private final Provider<UITree> fileTreeProvider;
   private final Provider<ScriptParameterClientEditComponent> clientEditComponentProvider;
   private final EnterpriseUiService eneterpriseService;

   @Inject
   public ScriptParameterConfigurator(@FileServerTreeBasic Provider<UITree> fileTreeProvider,
         Provider<ScriptParameterClientEditComponent> clientEditComponentProvider,
         EnterpriseUiService eneterpriseService) {

      this.fileTreeProvider = fileTreeProvider;
      this.clientEditComponentProvider = clientEditComponentProvider;
      this.eneterpriseService = eneterpriseService;
   }

   @Override
   public Widget getEditComponentForDefinition(ScriptParameterDefinitionDto definition, ReportDto report) {
      SimpleForm form = SimpleForm.getInlineInstance();

      form.beginRow();
      form.addField(Integer.class, ScriptParameterDefinitionDtoPA.INSTANCE.width(), BaseMessages.INSTANCE.width());
      form.addField(Integer.class, ScriptParameterDefinitionDtoPA.INSTANCE.height(), BaseMessages.INSTANCE.height());
      form.endRow();

      form.setFieldWidth(0.5);
      form.addField(FileServerFileDto.class, ScriptParameterDefinitionDtoPA.INSTANCE.script(),
            ScriptReportMessages.INSTANCE.script(), new SFFCGenericTreeNode() {

               @Override
               public UITree getTreeForPopup() {
                  return fileTreeProvider.get();
               }
            });

      form.setFieldWidth(1);
      form.addField(String.class, ScriptParameterDefinitionDtoPA.INSTANCE.arguments(),
            ScriptReportMessages.INSTANCE.arguments());

      form.addField(String.class, ScriptParameterDefinitionDtoPA.INSTANCE.defaultValue(),
            ScriptReportMessages.INSTANCE.parameterDefaultValue(), new SFFCTextAreaImpl());

      /* bind definition */
      form.bind(definition);

      return form;
   }

   @Override
   public Widget doGetEditComponentForInstance(final ScriptParameterInstanceDto instance,
         Collection<ParameterInstanceDto> relevantInstances, final ScriptParameterDefinitionDto definition,
         boolean initial, int labelWidth, String executeReportToken, ReportDto report) {
      return clientEditComponentProvider.get().getEditComponentForInstance(this, instance, relevantInstances,
            definition, initial, labelWidth, executeReportToken);
   }

   @Override
   public String getName() {
      return ScriptReportMessages.INSTANCE.scriptParameterName();
   }

   @Override
   protected ScriptParameterDefinitionDto doGetNewDto() {
      return new ScriptParameterDefinitionDto();
   }

   @Override
   public boolean consumes(Class<? extends ParameterDefinitionDto> type) {
      return ScriptParameterDefinitionDto.class.equals(type);
   }

   @Override
   public ImageResource getIcon() {
      return BaseIcon.SCRIPT.toImageResource();
   }

   @Override
   public ParameterType getType() {
      return ParameterType.Normal;
   }

   @Override
   public List<String> validateParameter(ScriptParameterDefinitionDto definition, ScriptParameterInstanceDto instance,
         Widget widget) {
      List<String> msgs = super.validateParameter(definition, instance, widget);
      if (widget instanceof ScriptParameterParameterWrapper) {
         ScriptParameterClientEditComponent configurator = ((ScriptParameterParameterWrapper) widget).getConfigurator();
         List<String> errors = configurator.validateParameter(definition, instance, widget);
         if (null != errors)
            msgs.addAll(errors);
      }
      return msgs;
   }

   @Override
   public boolean canDependOnParameters() {
      return true;
   }

   @Override
   public void dependeeInstanceChanged(ScriptParameterInstanceDto instance, ScriptParameterDefinitionDto aDefinition,
         Collection<ParameterInstanceDto> relevantInstances) {
      super.dependeeInstanceChanged(instance, aDefinition, relevantInstances);
   }

   @Override
   public boolean isAvailable() {
      return eneterpriseService.isEnterprise();
   }
}
