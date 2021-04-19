package net.datenwerke.rs.base.client.datasources.ui;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.managerhelper.mainpanel.SimpleFormView;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.actions.ShowHideFieldAction;
import net.datenwerke.gxtdto.client.forms.simpleform.conditions.SimpleFormCondition;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCPasswordField;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.SFFCStaticLabel;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCStaticDropdownList;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.dummy.StaticLabel;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.base.client.datasources.BaseDatasourceUiService;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.base.client.datasources.dto.pa.DatabaseDatasourceDtoPA;
import net.datenwerke.rs.base.client.datasources.locale.BaseDatasourceMessages;
import net.datenwerke.rs.base.client.dbhelper.dto.DatabaseHelperDto;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;

/**
 * 
 *
 */
public class DatabaseDatasourceForm extends SimpleFormView{

	private final BaseDatasourceUiService baseDatasourceService;
	
	@Inject
	public DatabaseDatasourceForm(
			BaseDatasourceUiService baseDatasourceService	
		){
		super();
		
		/* store objects */
		this.baseDatasourceService = baseDatasourceService;
	}
	
	public void configureSimpleForm(SimpleForm form) {
		/* configure form */
		form.setHeading(DatasourcesMessages.INSTANCE.editDataSource() + (getSelectedNode() == null ? "" : " (" + getSelectedNode().getId() + ")")); 
		
		/* name name */
		form.addField(String.class, DatabaseDatasourceDtoPA.INSTANCE.name(), BaseMessages.INSTANCE.name()); 
		
		form.addField(String.class, DatabaseDatasourceDtoPA.INSTANCE.description(), BaseMessages.INSTANCE.description(), new SFFCTextAreaImpl());
		
		/* database */
		form.setFieldWidth(250);
		form.addField(
			List.class, DatabaseDatasourceDtoPA.INSTANCE.databaseDescriptor(), DatasourcesMessages.INSTANCE.database(), 
			new SFFCStaticDropdownList<String>() {
				public Map<String, String> getValues() {
					Map<String, String> map = new TreeMap<String, String>();
					
					for(DatabaseHelperDto dbh : baseDatasourceService.getDatabaseHelpers()){
						String name = dbh.getName();
						if(! dbh.isJdbcDriverAvailable())
							name += " " + BaseDatasourceMessages.INSTANCE.jdbcDriverIsNotAvailable();
						map.put(name, dbh.getDescriptor());
					}
					
					return map;
				}
		});
		
		form.setFieldWidth(250);
		form.beginFloatRow();
		
		/* username */
		form.addField(String.class, DatabaseDatasourceDtoPA.INSTANCE.username(), DatasourcesMessages.INSTANCE.username()); 
		
		/* password */
		String passwordKey = form.addField(String.class, DatabaseDatasourceDtoPA.INSTANCE.password(), DatasourcesMessages.INSTANCE.password(), new SFFCPasswordField() {
			@Override
			public Boolean isPasswordSet() {
				return ((DatabaseDatasourceDto)getSelectedNode()).isHasPassword();
			}
		}); //$NON-NLS-1$
		Menu clearPwMenu = new DwMenu();
		MenuItem clearPwItem = new DwMenuItem(DatasourcesMessages.INSTANCE.clearPassword());
		clearPwMenu.add(clearPwItem);
		clearPwItem.addSelectionHandler(event -> ((DatabaseDatasourceDto)getSelectedNode()).setPassword(null));
		form.addFieldMenu(passwordKey, clearPwMenu);
		
		form.endRow();
		
		/* url */
		form.setFieldWidth(1);
		final String urlField = form.addField(String.class, DatabaseDatasourceDtoPA.INSTANCE.url(), DatasourcesMessages.INSTANCE.url()); 

		String warningField = form.addField(StaticLabel.class,  new SFFCStaticLabel() {
			@Override
			public String getLabel() {
				return DatasourcesMessages.INSTANCE.urlContainsWhitespaceWarning();
			}
		});

		form.addCondition(urlField, new SimpleFormCondition() {
			@Override
			public boolean isMet(Widget formField, FormFieldProviderHook responsibleHook, SimpleForm form) {
				Object value = form.getValue(urlField);
				return value instanceof String && ((String)value).contains(" ");
			}
		}, new ShowHideFieldAction(warningField));
		
		/* properties */
		form.addField(String.class, DatabaseDatasourceDtoPA.INSTANCE.jdbcProperties(), BaseMessages.INSTANCE.properties(), new SFFCTextAreaImpl());

	}
	
}
