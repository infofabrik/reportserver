package net.datenwerke.rs.incubator.client.scriptdatasource.config;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;

import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel;
import net.datenwerke.gxtdto.client.codemirror.CodeMirrorPanel.ToolBarEnhancer;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCCodeMirror;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.rs.core.client.datasourcemanager.config.DatasourceDefinitionConfigConfigurator;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerProviderDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.DatasourceSelectionField;
import net.datenwerke.rs.incubator.client.scriptdatasource.dto.ScriptDatasourceConfigDto;
import net.datenwerke.rs.incubator.client.scriptdatasource.dto.ScriptDatasourceDto;
import net.datenwerke.rs.incubator.client.scriptdatasource.locale.ScriptDatasourceMessages;

public class ScriptDatasourceConfigConfigurator implements DatasourceDefinitionConfigConfigurator {

   private TextArea textField;
   private CodeMirrorPanel scriptField;
   private CodeMirrorPanel wrapperField;

   public List<Widget> getOptionalAdditionalFormfields(DatasourceDefinitionConfigDto config,
         DatasourceDefinitionDto datasourceDefinitionDto, final DatasourceSelectionField datasourceSelectionField,
         final DatasourceContainerProviderDto datasourceContainerProvider) {
      if (!(config instanceof ScriptDatasourceConfigDto))
         throw new IllegalArgumentException("Expected " + ScriptDatasourceConfigDto.class.getName()); //$NON-NLS-1$

      /* script */
      if (((ScriptDatasourceDto) datasourceDefinitionDto).isDefineAtTarget()) {
         scriptField = (CodeMirrorPanel) SimpleForm.createFormlessField(String.class, new SFFCCodeMirror() {
            @Override
            public int getHeight() {
               return 250;
            }

            @Override
            public ToolBarEnhancer getEnhancer() {
               return null;
            }

            @Override
            public String getLanguage() {
               return "text/x-groovy";
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

         if (null != ((ScriptDatasourceConfigDto) config).getScript())
            scriptField.setValue(((ScriptDatasourceConfigDto) config).getScript());

         scriptField.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
               datasourceSelectionField.updateDatasourceConfig();
            }
         });
      }

      wrapperField = (CodeMirrorPanel) SimpleForm.createFormlessField(String.class, new SFFCCodeMirror() {
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

      if (null != ((ScriptDatasourceConfigDto) config).getQueryWrapper())
         wrapperField.setValue(((ScriptDatasourceConfigDto) config).getQueryWrapper());

      wrapperField.addValueChangeHandler(new ValueChangeHandler<String>() {
         @Override
         public void onValueChange(ValueChangeEvent<String> event) {
            datasourceSelectionField.updateDatasourceConfig();
         }
      });

      ArrayList<Widget> widgets = new ArrayList<Widget>();

      if (((ScriptDatasourceDto) datasourceDefinitionDto).isDefineAtTarget())
         widgets.add(new FieldLabel(scriptField, ScriptDatasourceMessages.INSTANCE.scriptLabel()));

      widgets.add(new FieldLabel(wrapperField, ScriptDatasourceMessages.INSTANCE.queryWrapperLabel()));

      return widgets;
   }

   public void inheritChanges(DatasourceDefinitionConfigDto config, DatasourceDefinitionDto datasourceDefinitionDto) {
      /* are we rendered */
      if (((ScriptDatasourceDto) datasourceDefinitionDto).isDefineAtTarget()) {
         if (null == scriptField)
            ((ScriptDatasourceConfigDto) config).setScript(null);
         else
            ((ScriptDatasourceConfigDto) config).setScript(scriptField.getCurrentValue());
      } else {
         if (null == textField)
            ((ScriptDatasourceConfigDto) config).setArguments(null);
         else
            ((ScriptDatasourceConfigDto) config).setArguments(textField.getCurrentValue());
      }

      if (null == wrapperField)
         ((ScriptDatasourceConfigDto) config).setQueryWrapper(null);
      else
         ((ScriptDatasourceConfigDto) config).setQueryWrapper(wrapperField.getValue());
   }

   @Override
   public DatasourceDefinitionConfigDto createConfigObject(DatasourceDefinitionDto datasourceDefinitionDto,
         DatasourceContainerProviderDto datasourceContainerProvider) {
      return new ScriptDatasourceConfigDto();
   }

   @Override
   public boolean consumes(DatasourceDefinitionDto datasourceDefinitionDto,
         DatasourceDefinitionConfigDto datasourceConfig) {
      return null != datasourceConfig && ScriptDatasourceConfigDto.class.equals(datasourceConfig.getClass());
   }

   @Override
   public boolean isValid(DatasourceContainerDto container) {
      if (!consumes(container.getDatasource(), container.getDatasourceConfig()))
         return false;
      return true;
   }

   @Override
   public boolean isReloadOnDatasourceChange() {
      return false;
   }

   @Override
   public Iterable<Widget> getDefaultAdditionalFormfields(DatasourceDefinitionConfigDto config,
         DatasourceDefinitionDto datasourceDefinitionDto, final DatasourceSelectionField datasourceSelectionField,
         DatasourceContainerProviderDto datasourceContainerProvider) {

      /* arguments */
      textField = (TextArea) SimpleForm.createFormlessField(String.class, new SFFCTextAreaImpl() {
         @Override
         public int getHeight() {
            return 80;
         }
      });

      if (null != ((ScriptDatasourceConfigDto) config).getArguments())
         textField.setValue(((ScriptDatasourceConfigDto) config).getArguments());

      textField.addValueChangeHandler(new ValueChangeHandler<String>() {
         @Override
         public void onValueChange(ValueChangeEvent<String> event) {
            datasourceSelectionField.updateDatasourceConfig();
         }
      });

      ArrayList<Widget> widgets = new ArrayList<Widget>();
      widgets.add(new FieldLabel(textField, ScriptDatasourceMessages.INSTANCE.argumentsLabel()));

      return widgets;
   }

}
