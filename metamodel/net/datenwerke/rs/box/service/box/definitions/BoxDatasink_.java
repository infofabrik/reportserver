package net.datenwerke.rs.box.service.box.definitions;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BoxDatasink.class)
public abstract class BoxDatasink_ extends net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition_ {

	public static volatile SingularAttribute<BoxDatasink, String> folder;
	public static volatile SingularAttribute<BoxDatasink, String> secretKey;
	public static volatile SingularAttribute<BoxDatasink, String> appKey;
	public static volatile SingularAttribute<BoxDatasink, String> refreshToken;

}

