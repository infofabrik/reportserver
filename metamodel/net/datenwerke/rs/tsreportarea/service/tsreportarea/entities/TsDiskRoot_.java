package net.datenwerke.rs.tsreportarea.service.tsreportarea.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TsDiskRoot.class)
public abstract class TsDiskRoot_ extends net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode_ {

	public static volatile SingularAttribute<TsDiskRoot, TeamSpace> teamSpace;
	public static volatile SingularAttribute<TsDiskRoot, String> name;
	public static volatile SingularAttribute<TsDiskRoot, String> description;

}

