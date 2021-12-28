package net.datenwerke.treedb.ext.client.eximport.im.ui;

import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;

/**
 * 
 *
 */
abstract public class ImporterMainPropertiesPanel<C extends ImportConfigDto> extends DwContentPanel {

	protected SimpleForm form;
	
	protected void initializeUI() {
		/* configure */
		setHeaderVisible(false);
		setBodyBorder(false);
		setBorders(false);
		
		/* create form */
		form = SimpleForm.getInlineInstance();
		form.setLabelAlign(LabelAlign.TOP);
		form.setFieldWidth(250);
		configureForm();
		form.loadFields();
		
		DwContentPanel wrapper = new DwContentPanel();
		wrapper.setLightDarkStyle();
		wrapper.setHeading(getHeadline());
		wrapper.setInfoText(getDescription());
		wrapper.add(form);
		
		VerticalLayoutContainer outerWrapper = new VerticalLayoutContainer();
		outerWrapper.setScrollMode(ScrollMode.AUTOY);
		outerWrapper.add(wrapper, new VerticalLayoutData(1,-1,new Margins(10)));
		
		add(outerWrapper);
	}

	abstract protected String getDescription();

	abstract protected String getHeadline();

	protected void configureForm() {

	}

	public void populateConfig(C config) throws NotProperlyConfiguredException {
		
	}

	public void validateConfig(C config) throws NotProperlyConfiguredException{
	}

	public void resetConfig() {
		form.getFormPanel().reset();
	}

	
}
