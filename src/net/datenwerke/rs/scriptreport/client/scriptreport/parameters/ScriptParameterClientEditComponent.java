package net.datenwerke.rs.scriptreport.client.scriptreport.parameters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.NamedFrame;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.WidgetComponent;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.parameters.helper.DefaultValueSetter;
import net.datenwerke.rs.core.client.parameters.helper.ParameterFieldWrapperForFrontend;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterDefinitionDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.rpc.ScriptReportServiceDao;

public class ScriptParameterClientEditComponent {

   private ScriptReportServiceDao scriptReportServiceDao;
   private EnterpriseUiService enterpriseService;

   private ScriptParameterInstanceDto instance;
   private ScriptParameterDefinitionDto definition;
   private String name;

   @Inject
   public ScriptParameterClientEditComponent(ScriptReportServiceDao scriptReportServiceDao,
         EnterpriseUiService enterpriseService) {
      this.scriptReportServiceDao = scriptReportServiceDao;
      this.enterpriseService = enterpriseService;

   }

   protected void initFrame(String name, ScriptParameterInstanceDto instance, ScriptParameterDefinitionDto definition) {
      try {
         ScriptParameterOverlay jsObj = (ScriptParameterOverlay) JavaScriptObject.createObject().cast();

         jsObj.setDefaultValue(definition.getDefaultValue());
         jsObj.setValue(instance.getValue());
         jsObj.setEditable(definition.isEditable());
         jsObj.setMandatory(definition.isMandatory());
         jsObj.setName(definition.getName());
         jsObj.setKey(definition.getKey());

         jsInitFrame(name, jsObj);
      } catch (Exception e) {
         GWT.log(e.getMessage());
      }
   }

   protected native void jsInitFrame(String name, ScriptParameterOverlay obj) /*-{
	    // get document
	    var f = $doc.getElementById(name);
	    d = (f.contentWindow || f.contentDocument);
	    
	    // init callback
	    var self = this;
  		var callbackFn = $entry(function(val) {
    		$entry(self.@net.datenwerke.rs.scriptreport.client.scriptreport.parameters.ScriptParameterClientEditComponent::setValueFromJsni(Ljava/lang/String;)(val));
  		}); 
	    
	    d.initParameter(obj, callbackFn); 
	}-*/;

   public void setValueFromJsni(String value) {
      instance.setValue(value);
      instance.setStillDefault(false);
   }

   public Widget getEditComponentForInstance(ScriptParameterConfigurator configurator,
         final ScriptParameterInstanceDto instance, final Collection<ParameterInstanceDto> relevantInstances,
         final ScriptParameterDefinitionDto definition, boolean initial, int labelWidth, String executeReportToken) {
      if (!enterpriseService.isEnterprise()) {
         return new ParameterFieldWrapperForFrontend(definition, instance,
               new WidgetComponent(new Label("Script parameter is not available in Community Edition.")), labelWidth);
      }

      this.instance = instance;
      this.definition = definition;

      this.name = "param-" + executeReportToken + "-" + definition.getId();
      NamedFrame myFrame = new NamedFrame(name);
      DOM.setElementAttribute(myFrame.getElement(), "id", name);

      myFrame.setHeight("100%");
      myFrame.setWidth("100%");
      myFrame.getElement().setAttribute("width", "100%");
      myFrame.getElement().setAttribute("height", "100%");
      myFrame.getElement().setAttribute("frameborder", "0");
      myFrame.getElement().getStyle().setProperty("border", "none");
      myFrame.getElement().getStyle().setProperty("margin", "0");

      myFrame.addLoadHandler(new LoadHandler() {
         @Override
         public void onLoad(LoadEvent event) {
            Scheduler.get().scheduleDeferred(new ScheduledCommand() {
               @Override
               public void execute() {
                  initFrame(name, instance, definition);
               }
            });
         }
      });
      myFrame.addAttachHandler(new Handler() {
         @Override
         public void onAttachOrDetach(AttachEvent event) {
            scriptReportServiceDao.getScriptParameterContents(definition, relevantInstances,
                  new RsAsyncCallback<String>() {
                     @Override
                     public void onSuccess(String result) {
                        fillIframe(name, result);
                     }
                  });
         }
      });

      DwContentPanel wrapper = DwContentPanel.newInlineInstance(myFrame);
      wrapper.setWidth(definition.getWidth());
      wrapper.setHeight(definition.getHeight());

      ScriptParameterParameterWrapper paramWrapper = new ScriptParameterParameterWrapper(definition, instance, wrapper,
            labelWidth, new DefaultValueSetter() {
               @Override
               public void setDefaultValue() {
                  setDefaultValueInInstance(instance, definition);
               }
            });
      paramWrapper.setScriptParameterConfigurator(this);
      return paramWrapper;
   }

   protected void setDefaultValueInInstance(ScriptParameterInstanceDto instance,
         ScriptParameterDefinitionDto definition) {
      boolean silent = instance.isSilenceEvents();
      instance.silenceEvents(true);
      instance.setValue(null);
      instance.setStillDefault(true);
      instance.silenceEvents(silent);

      try {
         jsResetFrame(name, definition.getDefaultValue());
      } catch (Exception e) {
         GWT.log(e.getMessage());
      }
   }

   protected native void jsResetFrame(String name, String value) /*-{
	    // get document
	    var f = $doc.getElementById(name);
	    d = (f.contentWindow || f.contentDocument);
	    
	    d.reset(value); 
	}-*/;

   public List<String> validateParameter(ScriptParameterDefinitionDto definition, ScriptParameterInstanceDto instance,
         Widget widget) {
      List<String> list = new ArrayList<String>();

      try {
         String error = doValidate(name);
         if (null != error && !"".equals(error))
            list.add(error);
      } catch (Exception e) {// swallow, no exception here
      }

      return list;

   }

   private native void fillIframe(String name, String content) /*-{
		
		var iframe = $doc.getElementById(name);
		var doc = iframe.document;

		if(iframe.contentDocument)
			doc = iframe.contentDocument;
		else if(iframe.contentWindow)
			doc = iframe.contentWindow.document;

		doc.open();
		doc.write(content);
		doc.close();
	}-*/;

   protected native String doValidate(String name) /*-{
	    // get document
	    var f = $doc.getElementById(name);
	    d = (f.contentWindow || f.contentDocument);
	    
	    return d.validate(); 
	}-*/;

}
