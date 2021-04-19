package net.datenwerke.rs.tsreportarea.client.tsreportarea.objectinfo;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoAdditionalInfoProvider;
import net.datenwerke.gxtdto.client.ui.helper.info.InfoWindow;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskDao;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.TsDiskUIService;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.locale.TsFavoriteMessages;

/**
 * 
 *
 */
public class ReportInTeamSpaceObjectInfo implements ObjectInfoAdditionalInfoProvider {

	private final TsDiskDao favoriteDao;
	private final TsDiskUIService tsDiskUiService;
	
	@Inject
	public ReportInTeamSpaceObjectInfo(
		TsDiskDao favoriteDao,
		TsDiskUIService tsDiskUiService
		){
		
		/* store object */
		this.favoriteDao = favoriteDao;
		this.tsDiskUiService = tsDiskUiService;
	}
	
	@Override
	public boolean consumes(Object object) {
		return object instanceof ReportDto;
	}
	
	@Override
	public void addInfoFor(Object object, InfoWindow window) {
		final DwContentPanel panel = window.addDelayedSimpelInfoPanel("TeamSpace");
		
		favoriteDao.getTeamSpacesWithPathsThatLinkToAsHtml((ReportDto)object, new RsAsyncCallback<SafeHtml>(){
			@Override
			public void onSuccess(SafeHtml result) {
				panel.clear();
				panel.enableScrollContainer();
				
				if(null == result)
					panel.add(new Label(TsFavoriteMessages.INSTANCE.reportNotInTeamSpacesMessages()));
				else {
					SafeHtmlBuilder builder = new SafeHtmlBuilder();
					builder.appendHtmlConstant("<div class=\"rs-infopanel-reportinteamspace\">");
					
					builder.append(result);
					
					builder.appendHtmlConstant("</div>");
					
					panel.add(new HTML(builder.toSafeHtml()));
				}
					
				panel.forceLayout();
			}
		});
	}

}
