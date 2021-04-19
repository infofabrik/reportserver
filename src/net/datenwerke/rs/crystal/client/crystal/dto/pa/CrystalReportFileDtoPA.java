package net.datenwerke.rs.crystal.client.crystal.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Long;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.crystal.service.crystal.entities.CrystalReportFile.class)
public interface CrystalReportFileDtoPA extends PropertyAccess<CrystalReportFileDto> {


	public static final CrystalReportFileDtoPA INSTANCE = GWT.create(CrystalReportFileDtoPA.class);

	@Path("dtoId")
	public ModelKeyProvider<CrystalReportFileDto> dtoId();

	/* Properties */
	public ValueProvider<CrystalReportFileDto,Long> id();
	public ValueProvider<CrystalReportFileDto,String> name();


}
