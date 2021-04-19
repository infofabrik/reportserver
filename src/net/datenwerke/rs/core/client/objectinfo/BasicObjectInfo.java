package net.datenwerke.rs.core.client.objectinfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.objectinformation.ObjectPreviewTabPanel;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectPreviewTabKeyInfoProvider;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectPreviewTabProviderHook;
import net.datenwerke.rs.core.client.i18tools.FormatUiHelper;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.client.treedb.dto.decorator.AbstractNodeDtoDec;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class BasicObjectInfo implements
		ObjectPreviewTabKeyInfoProvider {


	private final FormatUiHelper formatUiHelper;
	

	@Inject
	public BasicObjectInfo(FormatUiHelper formatUiHelper) {
		this.formatUiHelper = formatUiHelper;
	}
	
	

	@Override
	public boolean consumes(Object object) {
		return object instanceof AbstractNodeDto;
	}

	@Override
	public List<PreviewComponent> getInfoComponents(Object object)  {
		AbstractNodeDto node = (AbstractNodeDto) object;
		
		FlexTable table = new FlexTable();
		table.getColumnFormatter().setWidth(0, "100px");
		table.getColumnFormatter().setWidth(1, "400px");
		
		fillTable(table, node);
		
		DwContentPanel panel = new DwContentPanel();
		panel.setHeading(BaseMessages.INSTANCE.informationOn(node.toDisplayTitle()));
		panel.setLightDarkStyle();
		panel.add(table);
		
		final VerticalLayoutContainer wrapper = new VerticalLayoutContainer();
		wrapper.add(panel, new VerticalLayoutData(1,-1, new Margins(10)));
		
		List<ObjectPreviewTabProviderHook.PreviewComponent> list = new ArrayList<ObjectPreviewTabProviderHook.PreviewComponent>();
		list.add(new PreviewComponent() {
			
			@Override
			public String getInfoName() {
				return BaseMessages.INSTANCE.information();
			}
			
			@Override
			public Component getInfoComponent(Object object) {
				return wrapper;
			}
		});
		
		return list;
	}

	protected void fillTable(FlexTable table, AbstractNodeDto node) {
		int cnt = 0;
		
		HTML name = new HTML(new SafeHtmlBuilder().appendHtmlConstant("<b>").appendEscaped(BaseMessages.INSTANCE.name()).appendEscaped(":").appendHtmlConstant("</b>").toSafeHtml());
		name.setWordWrap(false);
		table.setWidget(cnt, 0, name);
		table.setText(cnt++, 1, node.toDisplayTitle());
		
		HTML description = new HTML(new SafeHtmlBuilder().appendHtmlConstant("<b>").appendEscaped(BaseMessages.INSTANCE.description()).appendEscaped(":").appendHtmlConstant("</b>").toSafeHtml());
		description.setWordWrap(false);
		table.setWidget(cnt, 0, description);
		table.setText(cnt++, 1, ((AbstractNodeDtoDec)node).getDescription());
		
		HTML createdOn = new HTML(new SafeHtmlBuilder().appendHtmlConstant("<b>").appendEscaped(BaseMessages.INSTANCE.createdOn()).appendEscaped(":").appendHtmlConstant("</b>").toSafeHtml());
		createdOn.setWordWrap(false);
		table.setWidget(cnt, 0, createdOn);
		try{
			table.setText(cnt, 1, (formatUiHelper.getShortDateTimeFormat().format(node.getLastUpdated())));
		}catch(IllegalArgumentException e){
			table.setText(cnt, 1, (BaseMessages.INSTANCE.unknown()));
		}
		cnt++;
		
		HTML type = new HTML(new SafeHtmlBuilder().appendHtmlConstant("<b>").appendEscaped(BaseMessages.INSTANCE.type()).appendEscaped(":").appendHtmlConstant("</b>").toSafeHtml());
		type.setWordWrap(false);
		table.setWidget(cnt, 0, type);
		table.setText(cnt++, 1, node.toTypeDescription());
		
		HTML id = new HTML(new SafeHtmlBuilder().appendHtmlConstant("<b>").appendEscaped(BaseMessages.INSTANCE.id()).appendEscaped(":").appendHtmlConstant("</b>").toSafeHtml());
		id.setWordWrap(false);
		table.setWidget(cnt, 0, id);
		table.setText(cnt++, 1, "" + node.getId());
	}

	@Override
	public Collection<?> getSubtypes(Object object) {
		return null;
	}

	@Override
	public void setInfoPanel(ObjectPreviewTabPanel infoPanel, Object object) {
		
	}

}
