package net.datenwerke.rs.amazons3.client.amazons3.dto.pa;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import java.lang.Boolean;
import java.lang.String;
import net.datenwerke.dtoservices.dtogenerator.annotations.CorrespondingPoso;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.pa.DatasinkDefinitionDtoPA;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
@CorrespondingPoso(net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink.class)
public interface AmazonS3DatasinkDtoPA extends DatasinkDefinitionDtoPA {


	public static final AmazonS3DatasinkDtoPA INSTANCE = GWT.create(AmazonS3DatasinkDtoPA.class);


	/* Properties */
	public ValueProvider<AmazonS3DatasinkDto,String> appKey();
	public ValueProvider<AmazonS3DatasinkDto,String> bucketName();
	public ValueProvider<AmazonS3DatasinkDto,String> folder();
	public ValueProvider<AmazonS3DatasinkDto,String> regionName();
	public ValueProvider<AmazonS3DatasinkDto,String> secretKey();
	public ValueProvider<AmazonS3DatasinkDto,String> storageClass();
	public ValueProvider<AmazonS3DatasinkDto,Boolean> hasSecretKey();


}
