package net.datenwerke.rs.birt.service.reportengine.hookers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.datenwerke.rs.birt.service.reportengine.hooks.BirtOutputGeneratorProviderHook;
import net.datenwerke.rs.birt.service.reportengine.output.generator.BirtDOCOutputGenerator;
import net.datenwerke.rs.birt.service.reportengine.output.generator.BirtHTMLOutputGenerator;
import net.datenwerke.rs.birt.service.reportengine.output.generator.BirtOutputGenerator;
import net.datenwerke.rs.birt.service.reportengine.output.generator.BirtPDFOutputGenerator;
import net.datenwerke.rs.birt.service.reportengine.output.generator.BirtPNGOutputGenerator;
import net.datenwerke.rs.birt.service.reportengine.output.generator.BirtXLSOutputGenerator;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class BaseBirtOutputGeneratorProvider implements BirtOutputGeneratorProviderHook{

	private final Provider<BirtHTMLOutputGenerator> html;
	private final Provider<BirtPDFOutputGenerator> pdf;
	private final Provider<BirtXLSOutputGenerator> xls;
	private final Provider<BirtPNGOutputGenerator> png;
	private final Provider<BirtDOCOutputGenerator> rtf;
	
	
	@Inject
	public BaseBirtOutputGeneratorProvider(
			Provider<BirtHTMLOutputGenerator> html,
			Provider<BirtPDFOutputGenerator> pdf,
			Provider<BirtXLSOutputGenerator> xls,
			Provider<BirtPNGOutputGenerator> png,
			Provider<BirtDOCOutputGenerator> rtf) {
		super();
		this.html = html;
		this.pdf = pdf;
		this.xls = xls;
		this.png = png;
		this.rtf = rtf;
	}



	@Override
	public Collection<BirtOutputGenerator> provideGenerators() {
		List<BirtOutputGenerator> generators = new ArrayList<BirtOutputGenerator>();
		
		generators.add(html.get());
		generators.add(pdf.get());
		generators.add(xls.get());
		generators.add(png.get());
		generators.add(rtf.get());
		
		return generators;
	}

}
