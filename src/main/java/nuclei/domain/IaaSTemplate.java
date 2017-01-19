/**
 * 
 */
package nuclei.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Relationship;

/**
 * @author Karthikeyan
 *
 */

public class IaaSTemplate extends Entity {

	private String IaasName;
	private String script;
	private String onIaas;
	private String isDeleted;

	@Relationship(type = "function")	
	List<TemplateFunction> templateFunction;

	public IaaSTemplate() {
		templateFunction = new ArrayList<TemplateFunction>();
	}

	public IaaSTemplate(String IaasName, String script, String onIaas,
			String isDeleted) {
		this.IaasName = IaasName;
		this.script = script;
		this.onIaas = onIaas;
		this.isDeleted = isDeleted;

	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getOnIaas() {
		return onIaas;
	}

	public void setOnIaas(String onIaas) {
		this.onIaas = onIaas;
	}

	public String getIaasName() {
		return IaasName;
	}

	public void setIaasName(String iaasName) {
		IaasName = iaasName;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<TemplateFunction> getTemplateFunction() {
		return templateFunction;
	}

	public void setTemplateFunction(List<TemplateFunction> templateFunction) {
		this.templateFunction = templateFunction;
	}

}