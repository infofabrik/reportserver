package net.datenwerke.rs.core.client.reportmanager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.pa.ReportDtoPA;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.theme.client.icon.CssIconImageResource;

/**
 * 
 *
 */
public class ReportManagerUIServiceImpl implements ReportManagerUIService {

	private static ReportDtoPA reportPa = GWT.create(ReportDtoPA.class);
	
	private final HookHandlerService hookHandler;
	
	@Inject
	public ReportManagerUIServiceImpl(
		HookHandlerService hookHandler	
		) {
		
		this.hookHandler = hookHandler;
	}
	
	@Override
	public List<ColumnConfig<ReportDto,?>> getColumnConfigForReportGrid(){
		return getColumnConfigForReportGrid(false, true);
	}
	
	@Override
	public List<ColumnConfig<ReportDto,?>> getColumnConfigForReportGrid(boolean includeVariants, boolean icon){
		List<ColumnConfig<ReportDto,?>> columns = new ArrayList<ColumnConfig<ReportDto,?>>();

		if(icon){
			/* icon */
			ColumnConfig<ReportDto,ReportDto> iconColumn = new ColumnConfig<ReportDto,ReportDto>(new IdentityValueProvider<ReportDto>("__icon"), 25);
			iconColumn.setMenuDisabled(true);
			iconColumn.setSortable(false);
			iconColumn.setCell(new AbstractCell<ReportDto>() {

				@Override
				public void render(
						com.google.gwt.cell.client.Cell.Context context,
						ReportDto value, SafeHtmlBuilder sb) {
					ImageResource icon= getIconFor(value);
					if(icon instanceof CssIconImageResource)
						sb.append(((CssIconImageResource)icon).getCssIcon());
					else
						sb.append(AbstractImagePrototype.create(icon).getSafeHtml());
				}
			});
			columns.add(iconColumn);
		}
		
		ColumnConfig<ReportDto, Long> ccId = new ColumnConfig<ReportDto,Long>(reportPa.id(), 50, ReportmanagerMessages.INSTANCE.ID());
		ccId.setMenuDisabled(true);
		columns.add(ccId);

		ColumnConfig<ReportDto, String> ccName = new ColumnConfig<ReportDto, String>(reportPa.name(), 170, BaseMessages.INSTANCE.name()); 
		ccName.setMenuDisabled(true);
		columns.add(ccName);
		
		if(includeVariants){
			ColumnConfig<ReportDto, ReportDto> ccIsVariant = new ColumnConfig<ReportDto, ReportDto>(new IdentityValueProvider<ReportDto>("__variantIcon"), 60, ReportmanagerMessages.INSTANCE.isVariant());
			ccIsVariant.setMenuDisabled(true);
			ccIsVariant.setCell(new AbstractCell<ReportDto>() {
				@Override
				public void render(
						com.google.gwt.cell.client.Cell.Context context,
						ReportDto value, SafeHtmlBuilder sb) {
					if(value instanceof ReportVariantDto)
						sb.append(BaseIcon.CHECK_CIRCLE_O.toSafeHtml());
				}
			});
			ccIsVariant.setSortable(false);
			columns.add(ccIsVariant);
			
			ColumnConfig<ReportDto, String> ccParent = new ColumnConfig<ReportDto, String>(reportPa.parentReportName(), 110, ReportmanagerMessages.INSTANCE.parentReportName()); 
			ccParent.setMenuDisabled(true);
			columns.add(ccParent);
		}

		ColumnConfig<ReportDto, Date> ccLastModified = new ColumnConfig<ReportDto, Date>(reportPa.lastUpdated(), 150, ReportmanagerMessages.INSTANCE.lastModified()); 
		ccLastModified.setMenuDisabled(true);
		ccLastModified.setCell(new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_MEDIUM)));
		columns.add(ccLastModified);
		
		return columns;
	}

	@Override
	public ImageResource getIconFor(ReportDto report) {
		for(ReportTypeConfigHook iconProvider : hookHandler.getHookers(ReportTypeConfigHook.class))
			if(iconProvider.consumes(report))
				return iconProvider.getReportIcon();

		return BaseIcon.NEWSPAPER_O.toImageResource();
	}

	@Override
	public ImageResource getLinkIconFor(ReportDto report) {
		for(ReportTypeConfigHook iconProvider : hookHandler.getHookers(ReportTypeConfigHook.class))
			if(iconProvider.consumes(report))
				return iconProvider.getReportLinkIcon();
		
		return BaseIcon.NEWSPAPER_LINK.toImageResource();
	}

	@Override
	public ImageResource getLargeIconFor(ReportDto report) {
		for(ReportTypeConfigHook iconProvider : hookHandler.getHookers(ReportTypeConfigHook.class))
			if(iconProvider.consumes(report))
				return iconProvider.getReportIconLarge();

		return BaseIcon.NEWSPAPER_O.toImageResource(1);
	}

	@Override
	public ImageResource getLargeLinkIconFor(ReportDto report) {
		for(ReportTypeConfigHook iconProvider : hookHandler.getHookers(ReportTypeConfigHook.class))
			if(iconProvider.consumes(report))
				return iconProvider.getReportLinkIcon();

		return BaseIcon.NEWSPAPER_LINK.toImageResource(1);
	}
	
	@Override
	public boolean supportsVariants(ReportDto report) {
		for(ReportTypeConfigHook config : hookHandler.getHookers(ReportTypeConfigHook.class))
			if(config.consumes(report))
				return null != config.instantiateReportVariant();

		return true;
	}

}
