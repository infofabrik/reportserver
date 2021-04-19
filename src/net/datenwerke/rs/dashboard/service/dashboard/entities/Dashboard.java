package net.datenwerke.rs.dashboard.service.dashboard.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.rs.utils.entitycloner.annotation.EnclosedEntity;
import net.datenwerke.rs.utils.instancedescription.annotations.Description;
import net.datenwerke.rs.utils.instancedescription.annotations.Title;

@Audited
@Entity
@Table(name="DASHBOARD")
@Inheritance(strategy=InheritanceType.JOINED)
@GenerateDto(
	dtoPackage="net.datenwerke.rs.dashboard.client.dashboard.dto",
	createDecorator=true
)
public class Dashboard implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7723151130710002611L;

	@JoinTable(name="DASHBOARD_2_DADGET")
	@EnclosedEntity
	@ExposeToClient
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy("n")
	private List<Dadget> dadgets = new ArrayList<Dadget>();
	
	@ExposeToClient(view=DtoView.MINIMAL)
	@Title
	private String name;
	
	@ExposeToClient
	@Lob
	@Type(type = "net.datenwerke.rs.utils.hibernate.RsClobType")
	@Description
    private String description;
	
	@ExposeToClient
	private int n;
	
	@ExposeToClient
	private LayoutType layout = LayoutType.TWO_COLUMN_EQUI;
	
	@Version
	private Long version;
	
	@ExposeToClient(id=true)
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ExposeToClient
	private boolean singlePage = true;
	
	@ExposeToClient
	@Transient
	private boolean primary = false;
	
	@ExposeToClient
	private long reloadInterval = -1;

	public boolean isPrimary() {
		return primary;
	}

	public void setPrimary(boolean primary) {
		this.primary = primary;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
    public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getN() {
		return n;
	}

	public void setDadgets(List<Dadget> dadgets) {
		this.dadgets = dadgets;
	}

	public List<Dadget> getDadgets() {
		return dadgets;
	}
	
	public void addDadget(Dadget dadget){
		this.dadgets.add(0,dadget);
		renumber();
	}
	
	public void addDadgetPlain(Dadget dadget){
		this.dadgets.add(dadget);
	}
	
	
	private void renumber(){
		int c = 0;
		for(Dadget d : this.dadgets)
			d.setN(c++);
	}
	
	public boolean removeDadget(Dadget dadget){
		boolean remove = this.dadgets.remove(dadget);
		renumber();
		return remove;
	}

	public void setLayout(LayoutType layout) {
		if(null == layout)
			layout = LayoutType.TWO_COLUMN_EQUI;
		this.layout = layout;
	}

	public LayoutType getLayout() {
		return layout;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public boolean contains(Dadget dadget) {
		return dadgets.contains(dadget);
	}

	public List<Dadget> getDadgetsInColumn(int col) {
		List<Dadget> dadgets = new ArrayList<Dadget>();
		
		for(Dadget d : this.dadgets){
			if(col == d.getCol() || 
			  (col == 1 && d.getCol() > 1 && layout == LayoutType.ONE_COLUMN) || 
			  (col == 2 && d.getCol() > 2 && (layout == LayoutType.TWO_COLUMN_EQUI || layout == LayoutType.TWO_COLUMN_LEFT_LARGE || layout == LayoutType.TWO_COLUMN_RIGHT_LARGE)) ||
			  (col == 3 && d.getCol() > 3 && layout == LayoutType.THREE_COLUMN)){
				dadgets.add(d);
			}
		}
		
		return dadgets;
	}
	
	public boolean isSinglePage() {
		return singlePage;
	}
	
	public void setSinglePage(boolean singlePage) {
		this.singlePage = singlePage;
	}
	
	public long getReloadInterval() {
		return reloadInterval;
	}
	
	public void setReloadInterval(long reloadInterval) {
		this.reloadInterval = reloadInterval;
	}
}
