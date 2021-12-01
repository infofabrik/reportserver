package net.datenwerke.rs.saiku.client.saiku.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.forms.binding.FormBinding;
import net.datenwerke.rs.core.client.datasourcemanager.DatasourceUIService;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceTreeOnlyMondrian;
import net.datenwerke.rs.core.client.reportmanager.ui.forms.AbstractReportForm;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceDto;
import net.datenwerke.rs.saiku.client.saiku.dto.pa.SaikuReportDtoPA;
import net.datenwerke.rs.saiku.client.saiku.locale.SaikuMessages;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class SaikuReportForm extends AbstractReportForm {
	
	private final Provider<UITree> datasourceTreeProvider;
	
	private CheckBox enableMdx;

	@Inject
	public SaikuReportForm(
		DatasourceUIService datasourceService,
		@DatasourceTreeOnlyMondrian Provider<UITree> datasourceTreeProvider
	) {
		super(datasourceService, datasourceTreeProvider);
		this.datasourceTreeProvider = datasourceTreeProvider;
	}
	
	@Override
	protected void initializeForm(FormPanel form, VerticalLayoutContainer fieldWrapper) {
		super.initializeForm(form, fieldWrapper);
		
		/* cubify */
		enableMdx = new CheckBox();
		enableMdx.setBoxLabel(SaikuMessages.INSTANCE.enableMdx());
		fieldWrapper.add(new FieldLabel(enableMdx, SaikuMessages.INSTANCE.fieldLabelEnableMdx()), new VerticalLayoutData(-1, -1, new Margins(0, 0, 5, 0)));

	}
	
	@Override
	protected void doInitFormBinding(FormBinding binding, AbstractNodeDto model) {
		super.doInitFormBinding(binding, model);
		
		binding.addBinding(enableMdx, model, SaikuReportDtoPA.INSTANCE.allowMdx());
	}
	
	@Override
	protected String getHeader() {
		return SaikuMessages.INSTANCE.editReport()  + " ("+getSelectedNode().getId()+")"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	
	@Override
	protected boolean isUploadForm() {
		return false;
	}
	
	@Override
	protected boolean isDisplayOptionalAdditionalConfigFieldsForDatasource() {
		return true;
	}

	@Override
	protected void addDatasourceField(Container container, boolean displayConfigFields) {
		datasourceFieldCreator = datasourceService.getSelectionField(container, displayConfigFields, datasourceTreeProvider, MondrianDatasourceDto.class);
		datasourceFieldCreator.addSelectionField();
		datasourceFieldCreator.addDisplayDefaultButton();
	}

}
