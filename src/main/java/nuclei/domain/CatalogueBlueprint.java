package nuclei.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import com.fasterxml.jackson.annotation.JsonIgnore;

@RelationshipEntity(type="catalogueBlueprint")
public class CatalogueBlueprint extends Entity {

	private long blueprintId;
	private long taxonomyId;
	private String blueprintName;
	private String x_axis;
	private String y_axis;
	private String step;
	private String isDeleted;
	
	 @StartNode
	 @JsonIgnore
	 private ScTaxonomy scTaxonomyRelation;
	    
	 @EndNode
	 @JsonIgnore
	 private Blueprint blueprintRelation;
	
	public CatalogueBlueprint() {
	
	}

	public CatalogueBlueprint(ScTaxonomy from,Blueprint to, String blueprintName,long blueprintId,long taxonomyId,
			String isDeleted,String x_axis,String y_axis,String step) {
		
		this.scTaxonomyRelation=from;
		this.blueprintRelation=to;		
		this.blueprintName = blueprintName;
		this.isDeleted = isDeleted;
		this.x_axis=x_axis;
		this.y_axis=y_axis;
		this.step=step;
		this.blueprintId=blueprintId;
		this.taxonomyId=taxonomyId;
	}


	public String getBlueprintName() {
		return blueprintName;
	}

	public void setBlueprintName(String blueprintName) {
		this.blueprintName = blueprintName;
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

	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public ScTaxonomy getScTaxonomyRelation() {
		return scTaxonomyRelation;
	}

	public void setScTaxonomyRelation(ScTaxonomy scTaxonomyRelation) {
		this.scTaxonomyRelation = scTaxonomyRelation;
	}

	public Blueprint getBlueprintRelation() {
		return blueprintRelation;
	}

	public void setBlueprintRelation(Blueprint blueprintRelation) {
		this.blueprintRelation = blueprintRelation;
	}

	public long getBlueprintId() {
		return blueprintId;
	}

	public void setBlueprintId(long blueprintId) {
		this.blueprintId = blueprintId;
	}

	public long getTaxonomyId() {
		return taxonomyId;
	}

	public void setTaxonomyId(long taxonomyId) {
		this.taxonomyId = taxonomyId;
	}

}
