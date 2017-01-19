/**
 * 
 */
package nuclei.domain;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Karthikeyan
 *
 */

public class ScTaxonomy extends Entity {

	   public static final String RELATED_TO_ScTaxonomy = "RELATED_TO_ScTaxonomy";
	
	private String taxName;	
	private String description;
	private String isDeleted;
	private String step;
	private String serviceCatalogueStep;	
	private String x_axis;
	private String y_axis;

	@Relationship(type = "RELATED_TO_ScTaxonomy")
	@JsonIgnore
	private Set<CatalogueBlueprint> relations= new HashSet<>();
	
	public ScTaxonomy() {
	
	}

	public ScTaxonomy(String taxName, String description, String isDeleted,String step,String serviceCatalogueStep,
			String x_axis,String y_axis) {
		this.taxName=taxName;
		this.description = description;		
		this.isDeleted = isDeleted;	
		this.step=step;
		this.serviceCatalogueStep=serviceCatalogueStep;
		this.x_axis=x_axis;
		this.y_axis=y_axis;
	}

	//relations
	  public CatalogueBlueprint rate(Blueprint blueprint, String blueprintName,long blueprintId,long taxonomyId,String isDeleted,String x_axis,String y_axis,String step) {
	        if (relations == null) {
	        	relations = new HashSet<>();
	        }
	      
	        CatalogueBlueprint relation = new CatalogueBlueprint(this, blueprint,blueprintName,blueprintId,taxonomyId,isDeleted,x_axis,y_axis,step);
	        relations.add(relation);
	        blueprint.addRelations(relation);
	        return relation;
	    }
	  
	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
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

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getServiceCatalogueStep() {
		return serviceCatalogueStep;
	}

	public void setServiceCatalogueStep(String serviceCatalogueStep) {
		this.serviceCatalogueStep = serviceCatalogueStep;
	}

	public String getX_axis() {
		return x_axis;
	}

	public void setX_axis(String x_axis) {
		this.x_axis = x_axis;
	}

	public String getY_axis() {
		return y_axis;
	}

	public void setY_axis(String y_axis) {
		this.y_axis = y_axis;
	}

	public Set<CatalogueBlueprint> getRelations() {
		return relations;
	}

	public void setRelations(Set<CatalogueBlueprint> relations) {
		this.relations = relations;
	}

	public static String getRelatedToSctaxonomy() {
		return RELATED_TO_ScTaxonomy;
	}

}