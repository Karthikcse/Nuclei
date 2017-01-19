package nuclei.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Relationship;

public class ServiceCatalogue extends Entity {

	private String scName;
	private String shortDesc;
	private String description;
	private String isDeleted;	

	@Relationship(type = "scTaxonomy")	
	List<ScTaxonomy> scTaxonomy;

	public ServiceCatalogue() {		
	
		scTaxonomy = new ArrayList<ScTaxonomy>();
	}

	public ServiceCatalogue(String scName, String shortDesc, String description,String isDeleted) {
		this.scName = scName;
		this.shortDesc = shortDesc;
		this.description = description;
		this.isDeleted = isDeleted;
	}

	public String getScName() {
		return scName;
	}

	public void setScName(String scName) {
		this.scName = scName;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<ScTaxonomy> getScTaxonomy() {
		return scTaxonomy;
	}

	public void setScTaxonomy(List<ScTaxonomy> scTaxonomy) {
		this.scTaxonomy = scTaxonomy;
	}

}
