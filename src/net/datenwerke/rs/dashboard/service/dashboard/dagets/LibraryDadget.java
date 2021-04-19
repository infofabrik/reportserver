package net.datenwerke.rs.dashboard.service.dashboard.dagets;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.dashboard.service.dashboard.dagets.post.LibraryDadget2DtoPost;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;

@Entity
@Table(name="DADGET_LIBRARY")
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dashboard.client.dashboard.dto",
	createDecorator=true,
	poso2DtoPostProcessors=LibraryDadget2DtoPost.class
)
public class LibraryDadget extends Dadget {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4343069561738262668L;
	
	@ExposeToClient(inheritDtoView=true)
	@ManyToOne
	private DadgetNode dadgetNode;

	public DadgetNode getDadgetNode() {
		return dadgetNode;
	}

	public void setDadgetNode(DadgetNode dadgetNode) {
		this.dadgetNode = dadgetNode;
	}


}