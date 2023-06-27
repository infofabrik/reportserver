package net.datenwerke.rs.amazons3.service.amazons3.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AmazonS3Datasink.class)
public abstract class AmazonS3Datasink_ extends net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition_ {

	public static volatile SingularAttribute<AmazonS3Datasink, String> bucketName;
	public static volatile SingularAttribute<AmazonS3Datasink, String> storageClass;
	public static volatile SingularAttribute<AmazonS3Datasink, String> folder;
	public static volatile SingularAttribute<AmazonS3Datasink, String> secretKey;
	public static volatile SingularAttribute<AmazonS3Datasink, String> regionName;
	public static volatile SingularAttribute<AmazonS3Datasink, String> appKey;

}

