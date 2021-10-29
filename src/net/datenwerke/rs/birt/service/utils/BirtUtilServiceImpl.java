package net.datenwerke.rs.birt.service.utils;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import net.datenwerke.rs.birt.service.reportengine.BirtHelper;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReportFile;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.birt.core.exception.BirtException;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IParameterDefn;

import com.google.inject.Provider;

public class BirtUtilServiceImpl implements BirtUtilService {

	private Provider<BirtHelper> birtHelperProvider;

	@Inject
	public BirtUtilServiceImpl(Provider<BirtHelper> birtHelperProvider) {
		this.birtHelperProvider = birtHelperProvider;
	}

	@Override
	public List<BirtParameterProposal> extractParameters(BirtReportFile reportFile) {
		List<BirtParameterProposal> proposals = new ArrayList<BirtParameterProposal>();
		
		BirtHelper birtHelper = birtHelperProvider.get();
		try {
			birtHelper.loadReportDesign(new ByteArrayInputStream(reportFile.getContent().getBytes()));
			
			for(IParameterDefn pd : birtHelper.getParameterDefinitions()){
				
				BirtParameterProposal bpp = new BirtParameterProposal();
				bpp.setKey(pd.getName());
				bpp.setName(StringUtils.isEmpty(pd.getDisplayName()) ? pd.getName() : pd.getDisplayName());
				
				if (null != bpp.getKey() && 
						( bpp.getKey().trim().equals("_RS_TMP_TABLENAME")  || bpp.getKey().trim().equals("_RS_QUERY") )) {
					// these values are set automatically. We don't want to extract them and show them to the user.
					continue;
				}
		
				switch(pd.getDataType()){
				case IParameterDefn.TYPE_STRING:
					bpp.setType(String.class.getName());
					break;
				case IParameterDefn.TYPE_BOOLEAN:
					bpp.setType(Boolean.class.getName());
					break;
				case IParameterDefn.TYPE_DATE:
					bpp.setType(Date.class.getName());
					break;
				case IParameterDefn.TYPE_DECIMAL:
					bpp.setType(BigDecimal.class.getName());
					break;
				case IParameterDefn.TYPE_FLOAT:
					bpp.setType(Double.class.getName());
					break;
				case IParameterDefn.TYPE_INTEGER:
					bpp.setType(Integer.class.getName());
					break;
				}

				proposals.add(bpp);
			}
		} catch (EngineException e) {
			throw new RuntimeException(e);
		} catch (BirtException e) {
			throw new RuntimeException(e);
		} finally {
			birtHelper.cleanup();
		}

		return proposals;
	}

}
