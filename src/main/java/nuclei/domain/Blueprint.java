package nuclei.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Blueprint extends Entity {

	private String blueprint;
	private String onIaas;
	private String script;

	private String isDeleted;

	@Relationship(type = "iaaSTemplate", direction = Relationship.INCOMING)	
	IaaSTemplate iaaSTemplate = new IaaSTemplate();;

	@Relationship(type = "iaasParameters")
	List<IaasParameters> iaasParameters = new ArrayList<IaasParameters>();

	@Relationship(type = "tasks")	
	List<Tasks> tasks = new ArrayList<Tasks>();

	@Relationship(type = "RELATED_TO_ScTaxonomy", direction = Relationship.INCOMING)
	@JsonIgnore
	private Set<CatalogueBlueprint> relations = new HashSet<>();

	public Blueprint() {

	}

	public Blueprint(String blueprint, String onIaas, String script,
			String isDeleted) {
		this.blueprint = blueprint;
		this.onIaas = onIaas;
		this.script = script;
		this.isDeleted = isDeleted;
	}

	public String getBlueprint() {
		return blueprint;
	}

	public void setBlueprint(String blueprint) {
		this.blueprint = blueprint;
	}

	public String getOnIaas() {
		return onIaas;
	}

	public void setOnIaas(String onIaas) {
		this.onIaas = onIaas;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<IaasParameters> getIaasParameters() {
		return iaasParameters;
	}

	public void setIaasParameters(List<IaasParameters> iaasParameters) {
		this.iaasParameters = iaasParameters;
	}

	public List<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

	public IaaSTemplate getIaaSTemplate() {
		return iaaSTemplate;
	}

	public void setIaaSTemplate(IaaSTemplate iaaSTemplate) {
		this.iaaSTemplate = iaaSTemplate;
	}

	public Set<CatalogueBlueprint> getRelations() {
		return relations;
	}

	public void setRelations(Set<CatalogueBlueprint> relations) {
		this.relations = relations;
	}

	public void addRelations(CatalogueBlueprint relation) {
		relations.add(relation);
	}

}
